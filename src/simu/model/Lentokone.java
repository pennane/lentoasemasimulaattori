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
	//Constructor for Lentokone
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
	//Reset plane
	public static void reset() {
		totalPlanes = 0;
	}
	//Get this planes ID
	public int getId() {
		return id;
	}
	//Get the FlighType of this flight
	public FlightType getFlightType() {
		return flightType;
	}
	//Get how many passengers have been assigned to this flight
	public int getPassengerCount() {
		return passengerCount;
	}
	//Get how many passengers are waiting
	public int getPassengersWaiting() {
		return passengersWaiting;
	}
	//Get how many passengers are in airport
	public int getPassengersInAirport() {
		return passengersInAirport;
	}
	//Increase how many passengers are waiting
	public void incrementWaitingPassengers() {
		passengersWaiting++;
	}
	//Increase how many passengers in airport
	public void incrementPassengersInAirport() {
		passengersInAirport++;
	}
	//Has the flight departed
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
	//Returns wether the conditions are met for the flight to depart
	public boolean canDepart() {
		return !hasDeparted && (Kello.getInstance().getAika() >= departingTime || passengersWaiting >= passengerCount);
	}

	/**
	 * For determining should new customers still be brought in
	 * 
	 * @return boolean
	 */
	//Returns if plane has seats left or not
	public boolean hasAvailableSeats() {
		return passengerCount > passengersInAirport;
	}

	/**
	 * Create a B event for the plane departure 1 second in to the future
	 * 
	 * @return B event for the plane departure
	 */
	//Returns Tapahtuma for planes set to depart
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
	//Compare function
	public int compareTo(Lentokone o) {
		return (int) (o.departingTime - this.departingTime);
	}
}
