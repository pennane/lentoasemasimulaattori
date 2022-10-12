package simu.model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Extension of Asiakas It holds information of baggage and its airplane
 */
public class LentoasemaAsiakas extends Asiakas {
	private boolean hasMatakatavat; // muuttuja jolla ilmaistaan onko asiakkaalla matkatavaroita
	private Lentokone lentokone;

	public LentoasemaAsiakas(Lentokone lentokone, double baggageProbability) {

		double rand = ThreadLocalRandom.current().nextDouble();
		this.hasMatakatavat = rand < baggageProbability;
		this.lentokone = lentokone;
	}

	public static void reset() {
		Asiakas.totalAsiakkaat = 0;
		Asiakas.sumLeadtime = 0;
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
