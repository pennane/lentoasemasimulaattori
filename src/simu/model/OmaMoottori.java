package simu.model;

import static simu.model.Constants.minutes;
import static simu.model.Constants.seconds;

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

		checkIn = new CheckinPalvelupiste(new Normal(minutes(3), 2), tapahtumalista);
		baggageDrop = new Palvelupiste(new Normal(minutes(7), 2), tapahtumalista, TapahtumanTyyppi.BAGGAGE_END);
		securityCheck = new SecurityPalvelupiste(new Negexp(minutes(2)), tapahtumalista);
		passportControl = new Palvelupiste(new Normal(minutes(1), 2), tapahtumalista,
				TapahtumanTyyppi.PASSPORTCONTROL_END);
		ticketInspection = new Palvelupiste(new Normal(minutes(1), 2), tapahtumalista,
				TapahtumanTyyppi.TICKETINSPECTION_END);

		saapumisprosessi = new Saapumisprosessi(new Negexp(seconds(3)), tapahtumalista, TapahtumanTyyppi.CHECKIN_ENTER);

		palvelupisteet.add(checkIn);
		palvelupisteet.add(baggageDrop);
		palvelupisteet.add(securityCheck);
		palvelupisteet.add(passportControl);
		palvelupisteet.add(ticketInspection);

	}

	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään

		// TODO: luo lentokoneet
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat

		LentoasemaAsiakas a;
		switch (t.getTyyppi()) { // TODO: Joku abstractio tälle tai jotain et ei oo nii paljon kohtia jossa voi
									// mennä rikki

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
