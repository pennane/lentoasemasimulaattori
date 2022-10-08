package simu.model;

import java.util.Optional;
import java.util.PriorityQueue;

public class LentoLista {

	// TODO: remove planes from the queue after the planes have departed

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
}
