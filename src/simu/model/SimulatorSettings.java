package simu.model;

public class SimulatorSettings {
	private long simulationDurationSeconds;
	private long simulationDelay;
	
	private double customersPerMinute;
	private double planesPerDay;
	private int checkInAmount;
	private int baggageDropAmount;
	private int securityCheckAmount;
	private int passportControlAmount;
	private int ticketInspectionAmount;
	private double shengenProbability; // 0-1
	private double baggageProbability; // 0-1


	public SimulatorSettings(long simulationDurationSeconds, long simulationDelay, double customersPerMinute,
			double planesPerDay, int checkInAmount, int baggageDropAmount, int securityCheckAmount,
			int passportControlAmount, int ticketInspectionAmount, double shengenProbability,
			double baggageProbability) {
		super();
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
	
	
}
