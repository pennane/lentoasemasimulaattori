package simu.model;

import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Trace;

public class Lentokone implements Comparable<Lentokone> {
	private int id;
	private static int totalPlanes = 0;

	private FlightType flightType;

	private long departingTime;

	private int passengerCount;
	private int passengersWaiting;
	private int passengersInAirport;

	private boolean hasDeparted;

	public Lentokone(FlightType flightType, int passengerCount, long departingTime) {
		id = totalPlanes;
		totalPlanes++;

		this.flightType = flightType;
		this.passengerCount = passengerCount;
		this.departingTime = departingTime;

		passengersWaiting = 0;
		passengersInAirport = 0;
		hasDeparted = false;
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
	
	public int getPassengersWaiting() {
		return passengersWaiting;
	}
	
	public int getPassengersInAirport() {
		return passengersInAirport;
	}
	

	public void incrementWaitingPassengers() {
		passengersWaiting++;
	}

	public void incrementPassengersInAirport() {
		passengersInAirport++;
	}
	
	public boolean getHasDeparted() {
		return hasDeparted;
	}

	public boolean canDepart() {
		return !hasDeparted && (Kello.getInstance().getAika() >= departingTime || passengersWaiting >= passengerCount);
	}

	public boolean hasAvailableSeats() {
		return passengerCount > passengersInAirport;
	}

	public Tapahtuma startDeparting() {
		hasDeparted = true;
		
		switch (flightType) {
		case International: {
			return new Tapahtuma(TapahtumanTyyppi.INTERNATIONAL_PLANE_DEPARTING, Kello.getInstance().getAika() + 1);
		}
		case Shengen: {
			return new Tapahtuma(TapahtumanTyyppi.SCHENGE_PLANE_DEPARTING, Kello.getInstance().getAika() + 1);
		}
		default: {
			Trace.out(Trace.Level.ERR, "Got unhandled FlightType " + flightType.toString());
			throw new UnsupportedOperationException();
		}
		}
	}

	@Override
	public int compareTo(Lentokone o) {
		return (int) (o.departingTime - this.departingTime);
	}
}
