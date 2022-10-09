package simu.model;

import simu.constants.Constants;
import simu.util.Fp;

public class SimulatorSettings {
	private Long simulationDurationSeconds;
	private Long simulationDelay;

	private double meanSecondsBetweenCustomers;
	private int planesPerDay;
	private int checkInAmount;
	private int baggageDropAmount;
	private int securityCheckAmount;
	private int passportControlAmount;
	private int ticketInspectionAmount;
	private double shengenProbability; // 0-1
	private double baggageProbability; // 0-1

	

	public SimulatorSettings(Long simulationDurationSeconds, Long simulationDelay, double meanSecondsBetweenCustomers,
			int planesPerDay, int checkInAmount, int baggageDropAmount, int securityCheckAmount,
			int passportControlAmount, int ticketInspectionAmount, double shengenProbability,
			double baggageProbability) {
		super();
		this.simulationDurationSeconds = Fp.valueOr(simulationDurationSeconds,
				(long) Constants.DEFAULT_SIMULATION_DURATION_SECONDS);
		this.simulationDelay = Fp.valueOr(simulationDelay, (long) Constants.DEFAULT_SIMULATION_DELAY);
		this.meanSecondsBetweenCustomers = Fp.valueOr(meanSecondsBetweenCustomers,
				Constants.DEFAULT_MEAN_SECONDS_BETWEEN_CUSTOMERS);
		this.planesPerDay = Fp.valueOr(planesPerDay, Constants.DEFAULT_PLANES_PER_DAY);
		this.checkInAmount = Fp.valueOr(checkInAmount, Constants.DEFAULT_CHECKIN_AMOUNT);
		this.baggageDropAmount = Fp.valueOr(baggageDropAmount, Constants.DEFAULT_BAGGAGE_DROP_AMOUNT);
		this.securityCheckAmount = Fp.valueOr(securityCheckAmount, Constants.DEFAULT_SECURITY_CHECK_AMOUNT);
		this.passportControlAmount = Fp.valueOr(passportControlAmount, Constants.DEFAULT_PASSPORT_CONTROL_AMOUNT);
		this.ticketInspectionAmount = Fp.valueOr(ticketInspectionAmount, Constants.DEFAULT_TICKET_INSPECTION_AMOUNT);
		this.shengenProbability = Fp.valueOr(shengenProbability, Constants.DEFAULT_SHENGEN_PROBABILITY);
		this.baggageProbability = Fp.valueOr(baggageProbability, Constants.DEFAULT_BAGGAGE_DROP_PROBABILITY);
	}

	public long getSimulationDurationSeconds() {
		return simulationDurationSeconds;
	}

	public long getSimulationDelay() {
		return simulationDelay;
	}

	public double getMeanSecondsBetweenCustomers() {
		return meanSecondsBetweenCustomers;
	}

	public int getPlanesPerDay() {
		return planesPerDay;
	}

	public int getCheckInAmount() {
		return checkInAmount;
	}

	public int getBaggageDropAmount() {
		return baggageDropAmount;
	}

	public int getSecurityCheckAmount() {
		return securityCheckAmount;
	}

	public int getPassportControlAmount() {
		return passportControlAmount;
	}

	public int getTicketInspectionAmount() {
		return ticketInspectionAmount;
	}

	public double getShengenProbability() {
		return shengenProbability;
	}

	public double getBaggageProbability() {
		return baggageProbability;
	}

	public void setSimulationDurationSeconds(long simulationDurationSeconds) {
		this.simulationDurationSeconds = simulationDurationSeconds;
	}

	public void setSimulationDelay(long simulationDelay) {
		this.simulationDelay = simulationDelay;
	}

	@Override
	public String toString() {
		return "SimulatorSettings [simulationDurationSeconds=" + simulationDurationSeconds + ", simulationDelay="
				+ simulationDelay + ", meanSecondsBetweenCustomers=" + meanSecondsBetweenCustomers + ", planesPerDay="
				+ planesPerDay + ", checkInAmount=" + checkInAmount + ", baggageDropAmount=" + baggageDropAmount
				+ ", securityCheckAmount=" + securityCheckAmount + ", passportControlAmount=" + passportControlAmount
				+ ", ticketInspectionAmount=" + ticketInspectionAmount + ", shengenProbability=" + shengenProbability
				+ ", baggageProbability=" + baggageProbability + "]";
	}

}
