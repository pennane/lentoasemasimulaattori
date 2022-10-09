package simu.model;

import static simu.util.Time.minutes;

import java.util.Optional;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.controller.IControllerMtoV;
import simu.data.Statistics;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;
import simu.framework.Trace;

public class OmaMoottori extends Moottori implements IOmaMoottori {

	private SimulatorSettings settings;

	private LentoLista lentoLista;

	private Saapumisprosessi saapumisprosessi;
	private PalvelupisteRouter checkIn;
	private PalvelupisteRouter baggageDrop;
	private PalvelupisteRouter passportControl;
	private PalvelupisteRouter ticketInspection;
	private PalvelupisteRouter securityCheck;

	private int completedEvents;

	public OmaMoottori(IControllerMtoV controller, SimulatorSettings settings) {

		super(controller);

		completedEvents = 0;

		this.settings = settings;

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

		saapumisprosessi = new Saapumisprosessi(new Negexp(settings.getMeanSecondsBetweenCustomers()), tapahtumalista,
				TapahtumanTyyppi.CHECKIN_ENTER);

		palvelupisteet.add(checkIn);
		palvelupisteet.add(baggageDrop);
		palvelupisteet.add(securityCheck);
		palvelupisteet.add(passportControl);
		palvelupisteet.add(ticketInspection);
	}

	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
		new LentokoneGeneraattori(lentoLista, settings).generoi((int) Math.round(settings.getPlanesPerDay()));
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat

		LentoasemaAsiakas a;
		switch (t.getTyyppi()) {

		case CHECKIN_ENTER:
			Optional<Lentokone> maybeLentokone = lentoLista.findNextAvailable();

			// This happens when all of the lentokonees are filled
			if (maybeLentokone.isEmpty())
				break;

			Lentokone lentokone = maybeLentokone.get();
			lentokone.incrementPassengersInAirport();

			checkIn.lisaaJonoon(new LentoasemaAsiakas(lentokone));

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
			a.setPoistumisaika(Kello.getInstance().getAika());
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
		completedEvents++;

		// Visualizations or other third party things that don't need to be run for every event
		if (completedEvents % 10 == 0) {
			controller.visualizeCurrentTime(Kello.getInstance().getAika());
		}
	}

	@Override
	protected void yritaCTapahtumat() { // määrittele protectediksi, josa haluat ylikirjoittaa
		for (PalvelupisteRouter p : palvelupisteet) {
			if (p.pisteVapaana() && p.onJonossa()) {
				p.aloitaPalvelu();
			}
		}
		for (Lentokone l : lentoLista.getLennot()) {
			if (l.canDepart()) {
				Trace.out(Trace.Level.INFO,
						l.getFlightType() + " lento lähtee " + nykyaika() + ". Kapasiteetti: " + l.getPassengerCount()
								+ ", Kyydissä: " + l.getPassengersWaiting() + ", Jäi kyydistä: "
								+ (l.getPassengersInAirport() - l.getPassengersWaiting()));
				tapahtumalista.lisaa(l.startDeparting());
			}
		}
	}

	@Override
	public void run() {
		alustukset();
		while (simuloidaan()) {
			Trace.out(Trace.Level.INFO, "\nA-vaihe: kello on " + nykyaika());
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
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());

		controller.visualizeCurrentTime(Kello.getInstance().getAika());
		controller.visualizeFinish();

		for (Palvelupiste p : palvelupisteet) {
			Statistics.getInstance().getPalvelupisteValues(p);
		}
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
		return kello.getAika() < getSimulointiaika();
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
			// e.printStackTrace();
		}
	}

}
