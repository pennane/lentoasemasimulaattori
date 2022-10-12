package simu.model;

import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Stream;

//List for Lentokone
public class LentoLista {

	// Don't ever remove stuff from the queue.
	// The departed planes are still used for calculating stuff.
	// TODO: The data structure could be changed though, as the queue functionality
	// is not necessarily needed, only the priority is.

	PriorityQueue<Lentokone> lennot;

	public LentoLista() {
		this.lennot = new PriorityQueue<>();
	}

	public void lisaaListaan(Lentokone lentokone) {
		lennot.add(lentokone);
	}

	public PriorityQueue<Lentokone> getLennot() {
		return lennot;
	}

	private Stream<Lentokone> planesStream() {
		return lennot.stream().sorted();
	}

	public Optional<Lentokone> findNextAvailable() {
		return planesStream().filter(l -> l.hasAvailableSeats() && !l.getHasDeparted()).findFirst();
	}

	/**
	 * love the stream api and somewhat functional programming
	 * 
	 * this can be read like a book
	 */
	public int findShengenCustomersInAirportCount() {
		return planesStream().filter(l -> l.getFlightType() == FlightType.Shengen && !l.getHasDeparted())
				.mapToInt(l -> l.getPassengersInAirport()).sum();

	}

	public int findInternationalCustomersInAirportCount() {
		return planesStream().filter(l -> l.getFlightType() == FlightType.International && !l.getHasDeparted())
				.mapToInt(l -> l.getPassengersInAirport()).sum();

	}

	public int findDepartedShengenPlanesCount() {
		return (int) planesStream().filter(l -> l.getFlightType() == FlightType.Shengen && l.getHasDeparted()).count();
	}

	public int findDepartedInternationalPlanesCount() {
		return (int) planesStream().filter(l -> l.getFlightType() == FlightType.International && l.getHasDeparted())
				.count();
	}

	public int findDepartedShengenCustomersCount() {
		return planesStream().filter(l -> l.getFlightType() == FlightType.Shengen && l.getHasDeparted())
				.mapToInt(l -> l.getPassengersWaiting()).sum();
	}

	public int findDepartedInternationalCustomersCount() {
		return planesStream().filter(l -> l.getFlightType() == FlightType.International && l.getHasDeparted())
				.mapToInt(l -> l.getPassengersWaiting()).sum();
	}
}
