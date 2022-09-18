package simu.model;

public class Lentokone {
	private int id;
	private int totalPlanes = 0;
	private FlightType flightType;
	private int passengerCount;

	public Lentokone() {
		totalPlanes++;
		id = totalPlanes;
	}

	public Lentokone(FlightType flightType, int passengerCount) {
		totalPlanes++;
		id = totalPlanes;
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
