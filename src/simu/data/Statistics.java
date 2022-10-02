package simu.data;

import java.util.HashMap;

import simu.model.LentoasemaAsiakas;
import simu.model.Palvelupiste;

public class Statistics {
	private static Statistics instanssi;
	public HashMap<String, Long> tulokset = new HashMap<String, Long>();

	private Statistics() {

	}

	public static Statistics getInstance() {
		if (instanssi == null) {
			instanssi = new Statistics();
		}
		return instanssi;
	}

	public void getPalvelupisteValues(Palvelupiste p) {
		tulokset.put(p.getPalvelupisteDescription() + " keskiarvo", (long) p.findPalveluajanKeskiarvo());
		tulokset.put(p.getPalvelupisteDescription() + " mediaani", (long) p.findMedian());

	}

	public void getAsiakasValues(LentoasemaAsiakas p) {
		tulokset.put("asiakkaan läpimeno keskiarvo", (long) p.getAverageLeadtime());

	}

	public HashMap<String, Long> getTulokset() {
		return this.tulokset;

	}
}
