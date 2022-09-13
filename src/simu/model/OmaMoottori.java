package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori {

	private Saapumisprosessi saapumisprosessi;
	Palvelupiste checkIn;
	Palvelupiste baggageDrop;
	Palvelupiste passportControl;
	Palvelupiste ticketInspection;
	Palvelupiste securityCheck;

	public OmaMoottori() {

		

		checkIn = new CheckinPalvelupiste(new Normal(10, 10), tapahtumalista);
		baggageDrop = new Palvelupiste(new Normal(10, 10), tapahtumalista, TapahtumanTyyppi.BAGGAGE_END);
		passportControl = new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.PASSPORTCONTROL_END);
		ticketInspection = new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.TICKETINSPECTION_END);
		securityCheck = new SecurityPalvelupiste(new Normal(5, 3), tapahtumalista);

		saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.CHECKIN_ENTER);

		palvelupisteet.add(checkIn);
		palvelupisteet.add(baggageDrop);
		palvelupisteet.add(passportControl);
		palvelupisteet.add(ticketInspection);
		palvelupisteet.add(securityCheck);
		
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
			break;
		case CHECKIN_END_SELF:
			a = checkIn.otaJonosta();
			securityCheck.lisaaJonoon(a);
			break;
		case CHECKIN_END_BAGGAGE:
			a = checkIn.otaJonosta();
			securityCheck.lisaaJonoon(a);
			break;
		case SECURITYCHECK_END_SCHENGE:
			a = securityCheck.otaJonosta();
			ticketInspection.lisaaJonoon(a);
			break;
		case SECURITYCHECK_END_INTERNATIONAL:
			a = securityCheck.otaJonosta();
			passportControl.lisaaJonoon(a);
			break;
		case BAGGAGE_END:
			a = baggageDrop.otaJonosta();
			securityCheck.lisaaJonoon(a);
			break;
		case PASSPORTCONTROL_END:
			a = passportControl.otaJonosta();
			ticketInspection.lisaaJonoon(a);
			break;
		case TICKETINSPECTION_END:
			a = ticketInspection.otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			a.raportti();
			break;
		default:
			System.out.println(t.getTyyppi());
			throw new UnsupportedOperationException();
		}
	}

	@Override
	protected void tulokset() {
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset ... puuttuvat vielä");
	}

}
