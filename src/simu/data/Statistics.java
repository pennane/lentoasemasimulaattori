package simu.data;

import java.util.HashMap;

import simu.model.Asiakas;
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
		tulokset.put(p.getPalvelupisteDescription() + " keskiarvo", (long) p.findPalveluaikaKeskiarvo());
		tulokset.put(p.getPalvelupisteDescription() + " mediaani", (long) p.findPalveluaikaAverage());

	}

	public void getCheckinValues(Palvelupiste p) {
		data.setCheckinAverage(p.findPalveluaikaKeskiarvo());
		data.setCheckinmedian(p.findPalveluaikaAverage());

	}

	public void getbaggagedropValues(Palvelupiste p) {
		data.setBaggagedropAverage(p.findPalveluaikaKeskiarvo());
		data.setBaggagedropmedian(p.findPalveluaikaAverage());

	}

	public void getSecuritycheckValues(Palvelupiste p) {
		data.setSecuritycheckAverage(p.findPalveluaikaKeskiarvo());
		data.setSecuritycheckmedian(p.findPalveluaikaAverage());

	}

	public void getPassportValues(Palvelupiste p) {
		data.setPassportcontrolAverage(p.findPalveluaikaKeskiarvo());
		data.setPassportcontrolmedian(p.findPalveluaikaAverage());

	}

	public void getTicketinspectionValues(Palvelupiste p) {
		data.setTicketinspectionAverage(p.findPalveluaikaKeskiarvo());
		data.setTicketinspectionmedian(p.findPalveluaikaAverage());

	}

	public void getAsiakasValues(LentoasemaAsiakas p) {
		// tulokset.put("asiakkaan läpimeno keskiarvo", (long) p.getAverageLeadtime());
		data.setCustomerRunTimeAverage(Asiakas.getAverageLeadtime());

	}

	public SimulationData getTulokset() {
		return data;

	}
}
