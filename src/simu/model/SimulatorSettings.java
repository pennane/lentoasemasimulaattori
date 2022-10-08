package simu.model;

public class SimulatorSettings {
	private long simulationDurationSeconds;
	private long simulationDelay;
	
	private double customersPerMinute;
	private double planesPerDay;
	private double checkInAmount;
	private double baggageDropAmount;
	private double securityCheckAmount;
	private double passportControlAmount;
	private double ticketInspectionAmount;
	private double shengenProbability; // 0-1
	private double baggageProbability; // 0-1

	public SimulatorSettings(long simulationDurationSeconds, long simulationDelay, double customersPerMinute,
			double planesPerDay, double checkInAmount, double baggageDropAmount, double securityCheckAmount,
			double passportControlAmount, double ticketInspectionAmount, double shengenProbability,
			double baggageProbability) {
		this.simulationDurationSeconds = simulationDurationSeconds;
		this.simulationDelay = simulationDelay;
		this.customersPerMinute = customersPerMinute;
		this.planesPerDay = planesPerDay;
		this.checkInAmount = checkInAmount;
		this.baggageDropAmount = baggageDropAmount;
		this.securityCheckAmount = securityCheckAmount;
		this.passportControlAmount = passportControlAmount;
		this.ticketInspectionAmount = ticketInspectionAmount;
		this.shengenProbability = shengenProbability;
		this.baggageProbability = baggageProbability;
	}
	
	public long getSimulationDurationSeconds() {
		return simulationDurationSeconds;
	}

	public long getSimulationDelay() {
		return simulationDelay;
	}

	public double getCustomersPerMinute() {
		return customersPerMinute;
	}

	public double getPlanesPerDay() {
		return planesPerDay;
	}

	public double getCheckInAmount() {
		return checkInAmount;
	}

	public double getBaggageDropAmount() {
		return baggageDropAmount;
	}

	public double getSecurityCheckAmount() {
		return securityCheckAmount;
	}

	public double getPassportControlAmount() {
		return passportControlAmount;
	}

	public double getTicketInspectionAmount() {
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
	
	
}
