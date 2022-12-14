package simu.data;

import java.util.HashMap;

import simu.model.Asiakas;
import simu.model.LentoasemaAsiakas;
import simu.model.Palvelupiste;
import simu.model.SimulatorSettings;

/**
 * 
 * @author henri vuento
 *
 *         singleton class for collecting simulation data
 */
public class Statistics {
	private static Statistics instanssi;
	public HashMap<String, Long> tulokset = new HashMap<String, Long>();
	SimulationData data = new SimulationData();
	private SimulatorSettings settings;

	private Statistics() {

	}

	public static Statistics getInstance() {
		if (instanssi == null) {
			instanssi = new Statistics();
		}
		return instanssi;
	}

	/**
	 * find palvelupiste average and median
	 * 
	 * @param p palvelupiste where to get values
	 */
	public void getPalvelupisteValues(Palvelupiste p) {
		tulokset.put(p.getPalvelupisteDescription() + " keskiarvo", (long) p.findPalveluaikaKeskiarvo());
		tulokset.put(p.getPalvelupisteDescription() + " mediaani", (long) p.findPalveluaikaAverage());

	}

	/**
	 * find checkin palvelupiste average and median
	 * 
	 * @param p palvelupiste where to get values
	 */
	public void getCheckinValues(Palvelupiste p) {
		data.setCheckinAverage(p.findPalveluaikaKeskiarvo());
		data.setCheckinmedian(p.findPalveluaikaAverage());

	}

	/**
	 * find baggagedrop palvelupiste average and median
	 * 
	 * @param p palvelupiste where to get values
	 */
	public void getbaggagedropValues(Palvelupiste p) {
		data.setBaggagedropAverage(p.findPalveluaikaKeskiarvo());
		data.setBaggagedropmedian(p.findPalveluaikaAverage());

	}

	/**
	 * find securitycheck palvelupiste average and median
	 * 
	 * @param p palvelupiste where to get values
	 */
	public void getSecuritycheckValues(Palvelupiste p) {
		data.setSecuritycheckAverage(p.findPalveluaikaKeskiarvo());
		data.setSecuritycheckmedian(p.findPalveluaikaAverage());

	}

	/**
	 * find passport control palvelupiste average and median
	 * 
	 * @param p palvelupiste where to get values
	 */
	public void getPassportValues(Palvelupiste p) {
		data.setPassportcontrolAverage(p.findPalveluaikaKeskiarvo());
		data.setPassportcontrolmedian(p.findPalveluaikaAverage());

	}

	/**
	 * find ticket inspection palvelupiste average and median
	 * 
	 * @param p palvelupiste where to get values
	 */
	public void getTicketinspectionValues(Palvelupiste p) {
		data.setTicketinspectionAverage(p.findPalveluaikaKeskiarvo());
		data.setTicketinspectionmedian(p.findPalveluaikaAverage());

	}

	/**
	 * Finds average time that customer takes to get trough the airport
	 * 
	 * @param p is lentoasemaAsiakas where you can request average time
	 */
	public void getAsiakasValues(LentoasemaAsiakas p) {
		// tulokset.put("asiakkaan l??pimeno keskiarvo", (long) p.getAverageLeadtime());
		data.setCustomerRunTimeAverage(Asiakas.getAverageLeadtime());

	}

	/**
	 * Method to find simulation run settings from omaMoottori
	 * 
	 * @param s is the settings object
	 */
	public void findSimulationSettings(SimulatorSettings s) {
		settings = s;
	}

	/**
	 * method to get data collected from simulation
	 * 
	 * @return returns simulationData object
	 */
	public SimulationData getTulokset() {
		return data;

	}

	/**
	 * Method to get simulation run settings
	 * 
	 * @return returns SimulatorSettings object
	 */
	public SimulatorSettings getSettings() {
		return settings;
	}
}
