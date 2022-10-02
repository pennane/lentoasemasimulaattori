package simu.model;

import static constants.Constants.minutes;
import static constants.Constants.seconds;

import controller.IControllerMtoV;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.data.Statistics;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;
import simu.framework.Trace;

public class OmaMoottori extends Moottori {

	private Saapumisprosessi saapumisprosessi;
	PalvelupisteRouter checkIn;
	PalvelupisteRouter baggageDrop;
	PalvelupisteRouter passportControl;
	PalvelupisteRouter ticketInspection;
	PalvelupisteRouter securityCheck;

	public OmaMoottori(IControllerMtoV controller) {
		super(controller);

		checkIn = new CheckinRouter(new Normal(minutes(3), 2), tapahtumalista, 10, "checkin");
		baggageDrop = new PalvelupisteRouter(new Normal(minutes(7), 2), tapahtumalista, TapahtumanTyyppi.BAGGAGE_END, 8,
				"baggagedrop");
		securityCheck = new SecurityRouter(new Negexp(minutes(2)), tapahtumalista, 4, );
		passportControl = new PalvelupisteRouter(new Normal(minutes(1), 2), tapahtumalista,
				TapahtumanTyyppi.PASSPORTCONTROL_END, 4, "passportcontrol");
		ticketInspection = new PalvelupisteRouter(new Normal(minutes(1), 2), tapahtumalista,
				TapahtumanTyyppi.TICKETINSPECTION_END, 20, "ticketinspection");

		saapumisprosessi = new Saapumisprosessi(new Negexp(seconds(10)), tapahtumalista,
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
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat

		LentoasemaAsiakas a;
		switch (t.getTyyppi()) {

		case CHECKIN_ENTER:
			checkIn.lisaaJonoon(new LentoasemaAsiakas());
			saapumisprosessi.generoiSeuraava();
			controller.visualizeCustomer();
			controller.visualizeCurrentTime(Kello.getInstance().getAika());
			break;
		case CHECKIN_END_SELF:
			a = checkIn.lopetaPalvelu(t.getPalvelupisteId());
			securityCheck.lisaaJonoon(a);
			break;
		case CHECKIN_END_BAGGAGE:
			a = checkIn.lopetaPalvelu(t.getPalvelupisteId());
			securityCheck.lisaaJonoon(a);
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
			a.setPoistumisaika(Kello.getInstance().getAika());
			controller.visualizeAirplane(a.getFlightType());
			a.raportti();
			Statistics.getInstance().getAsiakasValues(a);
			break;
		default:
			System.out.println(t.getTyyppi());
			throw new UnsupportedOperationException();
		}
	}

	@Override
	protected void yritaCTapahtumat() { // määrittele protectediksi, josa haluat ylikirjoittaa
		for (PalvelupisteRouter p : palvelupisteet) {
			if (p.pisteVapaana() && p.onJonossa()) {
				p.aloitaPalvelu();
			}
		}
	}
	
	@Override
	public void run() { // Entinen aja()
		alustukset(); // luodaan mm. ensimmäinen tapahtuma
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
		System.out.println("Tulokset ... puuttuvat vielä");
		controller.visualizeFinish();
		for (Palvelupiste p : palvelupisteet) {
			Statistics.getInstance().getPalvelupisteValues(p);
		}
	}

}
