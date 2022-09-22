package simu.model;

public class Lentokone {
	private int id;
	private static int totalPlanes = 0;
	private FlightType flightType;
	private int passengerCount;

	public Lentokone() {
		id = totalPlanes;
		totalPlanes++;
	}

	public Lentokone(FlightType flightType, int passengerCount) {
		id = totalPlanes;
		totalPlanes++;
		this.flightType = flightType;
		this.passengerCount = passengerCount;
	}

	public int getId() {
		return id;
	}

	public FlightType getFlightType() {
		return flightType;
	}

	public int getPassengerCount() {
		return passengerCount;
	}

}
