package simu.data;

import java.util.HashMap;

import simu.model.Palvelupiste;

public class Statistics {
	private static Statistics instanssi;
	private HashMap<String, Long> tulokset = new HashMap<String, Long>();
	private Statistics() {
		// TODO Auto-generated constructor stub
	}
	public static Statistics getInstance() {
		if (instanssi == null) {
			instanssi = new Statistics();
		}
		return instanssi;
	}
	public void getPalvelupisteValues(Palvelupiste p) {
		tulokset.put(p.getPalvelupisteDescription()+" keskiarvo",(long) p.findPalveluajanKeskiarvo());
		tulokset.put(p.getPalvelupisteDescription()+" mediaani",(long) p.findMedian());

	}

}
