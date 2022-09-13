package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori {

	private Saapumisprosessi saapumisprosessi;
	Palvelupiste Checkinpiste;
	Palvelupiste Luggagepiste;
	Palvelupiste Pasport;
	Palvelupiste portti;
	Palvelupiste turvatarkastus;

	public OmaMoottori() {

		palvelupisteet = new Palvelupiste[5];

		 Checkinpiste = new CheckinPalvelupiste(new Normal(10,10), tapahtumalista);
		 Luggagepiste = new Palvelupiste(new Normal(10, 10), tapahtumalista, TapahtumanTyyppi.LUGGAGE_END);
		 Pasport = new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.PASSPORTCONTROL_END);
		 portti = new Palvelupiste(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.TICKETINSPECTION_END);
		 turvatarkastus = new SecurityPalvelupiste(new Normal(5, 3), tapahtumalista);

		saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.CHECKIN_ENTER);
		
	}

	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat

		LentoasemaAsiakas a;
		switch (t.getTyyppi()) {

		case CHECKIN_ENTER :
			Checkinpiste.lisaaJonoon(new LentoasemaAsiakas());
			saapumisprosessi.generoiSeuraava();
			break;
		case CHECKIN_END_SELF :
			a = Checkinpiste.otaJonosta();
			 turvatarkastus.lisaaJonoon(a);
			break;
		case CHECKIN_END_LUGGAGE :
			a = Checkinpiste.otaJonosta();
			 turvatarkastus.lisaaJonoon(a);
			break;
		case SECURITYCHECK_END_SCHENGE :
			a = turvatarkastus.otaJonosta();
			 portti.lisaaJonoon(a);
			break;
		case SECURITYCHECK_END_INTERNATIONAL :
			a = turvatarkastus.otaJonosta();
			 Pasport.lisaaJonoon(a);
			break;
		case LUGGAGE_END:
			a = Luggagepiste.otaJonosta();
			 turvatarkastus.lisaaJonoon(a);
			break;
		case PASSPORTCONTROL_END :
			a =  Pasport.otaJonosta();
			portti.lisaaJonoon(a);
			break;
		case TICKETINSPECTION_END:
			a = portti.otaJonosta();
			a.setPoistumisaika(Kello.getInstance().getAika());
			a.raportti();
		}
	}

	@Override
	protected void tulokset() {
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset ... puuttuvat vielä");
	}

}
