package simu.framework;

import java.util.PriorityQueue;

public class Tapahtumalista {
	private PriorityQueue<Tapahtuma> lista = new PriorityQueue<Tapahtuma>();

	public Tapahtumalista() {

	}

	public Tapahtuma poista() {
		return lista.remove();
	}

	public void lisaa(Tapahtuma t) {
		Trace.out(Trace.Level.INFO, "Tapahtumalistaan lisätään uusi " + t.getTyyppi() + " " + t.getAika());
		lista.add(t);
	}

	public long getSeuraavanAika() {
		return lista.peek().getAika();
	}

}
