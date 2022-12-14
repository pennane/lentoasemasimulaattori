package simu.model;

import static simu.util.Time.minutes;

import java.util.Optional;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import eduni.distributions.Poisson;
import simu.controller.IControllerMtoV;
import simu.data.Datadaoimpl;
import simu.data.Statistics;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;
import simu.framework.Trace;
import simu.framework.Trace.Level;

public class OmaMoottori extends Moottori implements IOmaMoottori {

	private SimulatorSettings settings;

	private LentoLista lentoLista;

	private Saapumisprosessi saapumisprosessi;
	private PalvelupisteRouter checkIn;
	private PalvelupisteRouter baggageDrop;
	private PalvelupisteRouter passportControl;
	private PalvelupisteRouter ticketInspection;
	private PalvelupisteRouter securityCheck;

	private int completedBEvents;

	IntermediateStats intermediateStats;
	LentoasemaAsiakas a;

	public OmaMoottori(IControllerMtoV controller, SimulatorSettings settings) {
		super(controller);

		this.settings = settings;

		completedBEvents = 0;

		lentoLista = new LentoLista();

		checkIn = new CheckinRouter(new Normal(minutes(3), 2), tapahtumalista, settings.getCheckInAmount(), "checkin");
		baggageDrop = new PalvelupisteRouter(new Normal(minutes(7), 2), tapahtumalista, TapahtumanTyyppi.BAGGAGE_END,
				settings.getBaggageDropAmount(), "baggagedrop");
		securityCheck = new SecurityRouter(new Negexp(minutes(2)), tapahtumalista, settings.getSecurityCheckAmount(),
				"securitycheck");
		passportControl = new PalvelupisteRouter(new Normal(minutes(1), 2), tapahtumalista,
				TapahtumanTyyppi.PASSPORTCONTROL_END, settings.getPassportControlAmount(), "passportcontrol");
		ticketInspection = new PalvelupisteRouter(new Normal(minutes(1), 2), tapahtumalista,
				TapahtumanTyyppi.TICKETINSPECTION_END, settings.getTicketInspectionAmount(), "ticketinspection");

		saapumisprosessi = new Saapumisprosessi(new Poisson(settings.getMeanSecondsBetweenCustomers()), tapahtumalista,
				TapahtumanTyyppi.CHECKIN_ENTER);

		palvelupisteet.add(checkIn);
		palvelupisteet.add(baggageDrop);
		palvelupisteet.add(securityCheck);
		palvelupisteet.add(passportControl);
		palvelupisteet.add(ticketInspection);
	}

	@Override
	protected void alustukset() {
		kello.setAika(0);
		LentoasemaAsiakas.reset();
		Lentokone.reset();
		saapumisprosessi.generoiSeuraava(); // Ensimm??inen saapuminen j??rjestelm????n
		new LentokoneGeneraattori(lentoLista, settings).generoi(Math.round(settings.getPlanesPerDay()));
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat

		switch (t.getTyyppi()) {

		case CHECKIN_ENTER:
			Optional<Lentokone> maybeLentokone = lentoLista.findNextAvailable();

			// This happens when all of the lentokonees are filled
			if (maybeLentokone.isEmpty())
				break;

			Lentokone lentokone = maybeLentokone.get();
			lentokone.incrementPassengersInAirport();

			checkIn.lisaaJonoon(new LentoasemaAsiakas(lentokone, settings.getBaggageProbability()));

			saapumisprosessi.generoiSeuraava();
			controller.visualizeCustomer();

			break;
		case CHECKIN_END_SELF:
			a = checkIn.lopetaPalvelu(t.getPalvelupisteId());
			securityCheck.lisaaJonoon(a);
			break;
		case CHECKIN_END_BAGGAGE:
			a = checkIn.lopetaPalvelu(t.getPalvelupisteId());
			baggageDrop.lisaaJonoon(a);
			break;
		case SECURITYCHECK_END_SCHENGE:
			a = securityCheck.lopetaPalvelu(t.getPalvelupisteId());
			ticketInspection.lisaaJonoon(a);
			break;
		case SECURITYCHECK_END_INTERNATIONAL:
			a = securityCheck.lopetaPalvelu(t.getPalvelupisteId());
			passportControl.lisaaJonoon(a);
			break;
		case BAGGAGE_END:
			a = baggageDrop.lopetaPalvelu(t.getPalvelupisteId());
			securityCheck.lisaaJonoon(a);
			break;
		case PASSPORTCONTROL_END:
			a = passportControl.lopetaPalvelu(t.getPalvelupisteId());
			ticketInspection.lisaaJonoon(a);
			break;
		case TICKETINSPECTION_END:
			a = ticketInspection.lopetaPalvelu(t.getPalvelupisteId());
			a.getLentokone().incrementWaitingPassengers();
			a.setPoistumisaika(kello.getAika());
			a.raportti();
			Statistics.getInstance().getAsiakasValues(a);

			break;
		case SCHENGE_PLANE_DEPARTING:
			controller.visualizeAirplane(FlightType.Shengen);
			break;
		case INTERNATIONAL_PLANE_DEPARTING:
			controller.visualizeAirplane(FlightType.International);
			break;
		default:
			System.out.println(t.getTyyppi());
			throw new UnsupportedOperationException();
		}
		completedBEvents++;

		// Visualizations or other third party things that don't need to be run for
		// every event
		if (completedBEvents % 10 == 0) {
			controller.visualizeCurrentTime(kello.getAika());

			IntermediateStats intermediateStats = new IntermediateStats();
			intermediateStats.buildRouterStats(checkIn, baggageDrop, securityCheck, passportControl, ticketInspection);
			intermediateStats.buildPlaneStats(lentoLista);
			intermediateStats.buildTotalStats();
			controller.visualizeIntermediateStats(intermediateStats);
		}
	}

	@Override
	protected void suoritaBTapahtumat() {
		while (!tapahtumalista.isEmpty() && tapahtumalista.getSeuraavanAika() == kello.getAika()) {
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	@Override
	protected void yritaCTapahtumat() { // m????rittele protectediksi, josa haluat ylikirjoittaa
		for (PalvelupisteRouter p : palvelupisteet) {
			if (p.pisteVapaana() && p.onJonossa()) {
				p.aloitaPalvelu();
			}
		}
		for (Lentokone l : lentoLista.getLennot()) {
			if (l.canDepart()) {
				Trace.out(Trace.Level.INFO,
						l.getFlightType() + " lento l??htee " + kello.getAika() + ". Kapasiteetti: "
								+ l.getPassengerCount() + ", Kyydiss??: " + l.getPassengersWaiting() + ", J??i kyydist??: "
								+ (l.getPassengersInAirport() - l.getPassengersWaiting()));
				tapahtumalista.lisaa(l.startDeparting());
			}
		}
	}

	@Override
	public void run() {
		alustukset();
		while (this.simuloidaan()) {
			Trace.out(Trace.Level.INFO, "\nA-vaihe: kello on " + kello.getAika());
			viive();
			kello.setAika(nykyaika());

			Trace.out(Trace.Level.INFO, "\nB-vaihe:");
			suoritaBTapahtumat();

			Trace.out(Trace.Level.INFO, "\nC-vaihe:");
			yritaCTapahtumat();
		}
		tulokset();

	}

	@Override
	protected void tulokset() {
		System.out.println("Simulointi p????ttyi kello " + kello.getAika());
		try {
			Statistics.getInstance().getCheckinValues(checkIn);
			Statistics.getInstance().getbaggagedropValues(baggageDrop);
			Statistics.getInstance().getSecuritycheckValues(securityCheck);
			Statistics.getInstance().getPassportValues(passportControl);
			Statistics.getInstance().getTicketinspectionValues(ticketInspection);

			Datadaoimpl dao = new Datadaoimpl();

			dao.SaveSimulationData(Statistics.getInstance().getTulokset(), settings);

			// System.out.println(dao.getAllData().getBaggagedropAverage());
		} catch (Exception e) {
			Trace.out(Level.ERR, "Db stuff failed " + e.toString());
		}

		controller.visualizeCurrentTime(kello.getAika());

		controller.visualizeFinish();
	}

	@Override
	public void setSimulointiaika(long aika) {
		this.settings.setSimulationDurationSeconds(aika);
	}

	public long getSimulointiaika() {
		return this.settings.getSimulationDurationSeconds();
	}

	@Override
	protected boolean simuloidaan() {
		Trace.out(Trace.Level.INFO, "Kello on: " + kello.getAika());
		return !tapahtumalista.isEmpty() && kello.getAika() < getSimulointiaika();
	}

	@Override
	public boolean isSimulationRunning() {
		return kello.getAika() != 0 && kello.getAika() < getSimulointiaika();
	}

	@Override
	public void setSettingsViive(long viive) {
		settings.setSimulationDelay(viive);
	}

	@Override
	public long getSettingsViive() {
		return settings.getSimulationDelay();
	}

	@Override
	protected void viive() {
		try {
			sleep(getSettingsViive());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public SimulatorSettings getSimulatorSettings() {
		return settings;
	}

}
