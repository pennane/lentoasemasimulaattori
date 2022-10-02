package simu.model;

import java.util.concurrent.ThreadLocalRandom;

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
