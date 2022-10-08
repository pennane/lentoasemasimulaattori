package simu.model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Asiakkaan jatke, jonka ominaisuutena on lentokoneen ja matkatavaroiden
 * tietojen pitäminen.
 */
public class LentoasemaAsiakas extends Asiakas {
	private boolean hasMatakatavat; // muuttuja jolla ilmaistaan onko asiakkaalla matkatavaroita
	private Lentokone lentokone;

	public LentoasemaAsiakas(Lentokone lentokone) {
		this.hasMatakatavat = ThreadLocalRandom.current().nextBoolean();
		this.lentokone = lentokone;
	}

	public FlightType getFlightType() {
		return lentokone.getFlightType();
	}

	public int getFlightId() {
		return lentokone.getId();
	}

	public Lentokone getLentokone() {
		return lentokone;
	}

	public boolean getHasMatkatavarat() {
		return hasMatakatavat;
	}
}
