package simu.model;

import java.util.LinkedList;

public class LentoLista {

	LinkedList<Lentokone> lennot;
	
	public LentoLista() {
		this.lennot = new LinkedList<>();
	}
	public void lisaaListaan(Lentokone lentokone) {
		lennot.add(lentokone);
	}
	public void poistaListalta() {
		lennot.remove();
	}
	public Lentokone getSeuraava() {
		return lennot.getFirst();
	}
	public Lentokone getViimeinen() {
		return lennot.getLast();
	}
}
