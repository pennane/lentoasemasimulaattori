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
		lista.add(t);
	}

	public long getSeuraavanAika() {
		return lista.peek().getAika();
	}

	/**
	 * 
	 * @return true if the list is empty, false if it is not
	 */
	public boolean isEmpty() {
		return lista.isEmpty();
	}

}
