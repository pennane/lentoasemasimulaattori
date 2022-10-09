package simu.model;

import java.util.Optional;
import java.util.PriorityQueue;

public class LentoLista {

	// Don't ever remove stuff from the queue.
	// The departed planes are still used for calculating stuff.
	// TODO: The data structure could be changed though, as the queue functionality
	// is not needed.

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

	public Optional<Lentokone> findNextAvailable() {
		return lennot.stream().sorted().filter(l -> l.hasAvailableSeats() && !l.getHasDeparted()).findFirst();
	}

	public int findShengenCustomersInAirportCount() {
		return lennot.stream().sorted().filter(l -> l.getFlightType() == FlightType.Shengen && !l.getHasDeparted())
				.mapToInt(l -> l.getPassengersInAirport()).sum();

	}

	public int findInternationalCustomersInAirportCount() {
		return lennot.stream().sorted()
				.filter(l -> l.getFlightType() == FlightType.International && !l.getHasDeparted())
				.mapToInt(l -> l.getPassengersInAirport()).sum();

	}

	public int findDepartedShengenPlanesCount() {
		return (int) lennot.stream().sorted().filter(l -> l.getFlightType() == FlightType.Shengen && l.getHasDeparted())
				.count();
	}

	public int findDepartedInternationalPlanesCount() {
		return (int) lennot.stream().sorted()
				.filter(l -> l.getFlightType() == FlightType.International && l.getHasDeparted()).count();
	}

	public int findDepartedShengenCustomersCount() {
		return lennot.stream().sorted().filter(l -> l.getFlightType() == FlightType.Shengen && l.getHasDeparted())
				.mapToInt(l -> l.getPassengersWaiting()).sum();
	}

	public int findDepartedInternationalCustomersCount() {
		return lennot.stream().sorted().filter(l -> l.getFlightType() == FlightType.International && l.getHasDeparted())
				.mapToInt(l -> l.getPassengersWaiting()).sum();
	}
}
