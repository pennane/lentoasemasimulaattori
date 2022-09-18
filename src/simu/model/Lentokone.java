package simu.model;

public class Lentokone {
	private int id;
	private int totalplanes = 0;
	private FlightType flightType;
	private int passengerCount;

	public Lentokone() {
		totalplanes++;
		id = totalplanes;
	}

	public Lentokone(FlightType flightType, int passengerCount) {
		totalplanes++;
		id = totalplanes;
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
