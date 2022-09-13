package simu.model;

import java.util.Random;

public class LentoasemaAsiakas extends Asiakas {
	private boolean matkatavara; // muuttuja jolla ilmaistaan onko asiakkaalla matkatavaroita
	private int lentoid; // TODO lento johon asiakas on menossa

	Random temprand = new Random(); // TODO: This is for replicating lentokone, remove later

	public LentoasemaAsiakas() {
		this.matkatavara = temprand.nextBoolean();
	}

	public FlightType getFlightType() {
		return temprand.nextBoolean() ? FlightType.International : FlightType.Shengen;
	}

	public boolean getMatkatavara() {
		return matkatavara;
	}
}
