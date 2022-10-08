package simu.model;

public class SimulatorSettings {
	private double simulationDurationSeconds;
	private double simulationDelay;
	private double customersPerMinute;
	private double planesPerDay;
	private double checkInAmount;
	private double baggageDropAmount;
	private double securityCheckAmount;
	private double passportControlAmount;
	private double ticketInspectionAmount;
	private double shengenProbability; // 0-1
	private double baggageProbability; // 0-1

	public SimulatorSettings(double simulationDurationSeconds, double simulationDelay, double customersPerMinute,
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
	
	public double getSimulationDurationSeconds() {
		return simulationDurationSeconds;
	}

	public double getSimulationDelay() {
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
}
