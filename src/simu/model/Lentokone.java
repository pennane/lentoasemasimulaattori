package simu.model;

import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Trace;

/**
 * Airplane object for the simulation.
 */
public class Lentokone implements Comparable<Lentokone> {

	private int id;
	private static int totalPlanes = 0;

	private FlightType flightType;

	private long departingTime;

	private int passengerCount;
	private int passengersWaiting;
	private int passengersInAirport;

	private boolean hasDeparted;

	/**
	 * 
	 * @param flightType     Whether the plane is headed to a INTERNATIONAL or
	 *                       SHENGEN country
	 * @param passengerCount The total amount of fitting passengers
	 * @param departingTime  The timestamp in seconds when the plane is to depart
	 */
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
	
	public static void reset() {
		totalPlanes = 0;
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

	/**
	 * Boolean for whether the plane should be allowed to leave or not.
	 * 
	 * It should leave if the department time has closed in or if every passener is
	 * present ( yes that is not realistic but oh well )
	 * 
	 * @return boolean
	 */
	public boolean canDepart() {
		return !hasDeparted && (passengersWaiting >= passengerCount);
	}

	/**
	 * For determining should new customers still be brought in
	 * 
	 * @return boolean
	 */
	public boolean hasAvailableSeats() {
		return passengerCount > passengersInAirport;
	}

	/**
	 * Create a B event for the plane departure 1 second in to the future
	 * 
	 * @return B event for the plane departure
	 */
	public Tapahtuma startDeparting() {
		hasDeparted = true;

		switch (flightType) {
		case International: {
			// TODO: reimplement without using + 1 hack, figure out correct way to use
			// "kolmivaihesimulointi"
			return new Tapahtuma(TapahtumanTyyppi.INTERNATIONAL_PLANE_DEPARTING, Kello.getInstance().getAika() + 1);
		}
		case Shengen: {
			// TODO: reimplement without using + 1 hack
			return new Tapahtuma(TapahtumanTyyppi.SCHENGE_PLANE_DEPARTING, Kello.getInstance().getAika() + 1);
		}
		default: {
			Trace.out(Trace.Level.ERR, "Got unhandled FlightType " + flightType.toString());
			throw new UnsupportedOperationException();
		}
		}
	}

	/**
	 * Planes departing sooner are to appear first
	 */
	@Override
	public int compareTo(Lentokone o) {
		return (int) (o.departingTime - this.departingTime);
	}
}
