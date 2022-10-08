package simu.data;

import java.util.HashMap;

import simu.model.LentoasemaAsiakas;
import simu.model.Palvelupiste;

public class Statistics {
	private static Statistics instanssi;
	public HashMap<String, Long> tulokset = new HashMap<String, Long>();
	SimulationData data = new SimulationData();

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

	public void getCheckinValues(Palvelupiste p) {
		data.setCheckinAverage(p.findPalveluajanKeskiarvo());
		data.setCheckinmedian(p.findMedian());

	}

	public void getbaggagedropValues(Palvelupiste p) {
		data.setBaggagedropAverage(p.findPalveluajanKeskiarvo());
		data.setBaggagedropmedian(p.findMedian());

	}

	public void getSecuritycheckValues(Palvelupiste p) {
		data.setSecuritycheckAverage(p.findPalveluajanKeskiarvo());
		data.setSecuritycheckmedian(p.findMedian());

	}

	public void getPassportValues(Palvelupiste p) {
		data.setPassportcontrolAverage(p.findPalveluajanKeskiarvo());
		data.setPassportcontrolmedian(p.findMedian());

	}

	public void getTicketinspectionValues(Palvelupiste p) {
		data.setTicketinspectionAverage(p.findPalveluajanKeskiarvo());
		data.setTicketinspectionmedian(p.findMedian());

	}

	public void getAsiakasValues(LentoasemaAsiakas p) {
		// tulokset.put("asiakkaan läpimeno keskiarvo", (long) p.getAverageLeadtime());
		data.setCustomerRunTimeAverage(p.getAverageLeadtime());

	}

	public HashMap<String, Long> getTulokset() {
		return this.tulokset;

	}
}
