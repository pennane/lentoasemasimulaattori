package simu.data;

public class SimulationData {

	private int id;
	private double passportcontrolAverage;
	private double passportcontrolmedian;
	private double baggagedropAverage;
	private double baggagedropmedian;
	private double securitycheckAverage;
	private double securitycheckmedian;
	private double ticketinspectionAverage;
	private double ticketinspectionmedian;
	private double checkinAverage;
	private double checkinmedian;
	private double CustomerRunTimeAverage;
	private int arivedCustomerAmount;
	private int servicedCustomerAmount;
	private int passportcontrolCustomerAmount;
	private int checkinCustomerAmount;
	private int baggagedropCustomerAmount;
	private int ticketinspectionCustomerAmount;
	private int securitycheckCustomerAmount;
	private double simulationTotaltime;
	private double checkinBusyTime;
	private double baggagedropBusyTime;
	private double securitycheckBusyTime;
	private double passportcontrolBusyTime;
	private double ticketinspectionBusyTime;

	public SimulationData(int id, double passportcontrolAverage, double passportcontrolmedian, double baggagedropAverage,
			double baggagedropmedian, double securitycheckAverage, double securitycheckmedian,
			double ticketinspectionAverage, double ticketinspectionmedian, double checkinAverage, double checkinmedian,
			double customerRunTimeAverage, int arivedCustomerAmount, int servicedCustomerAmount,
			int passportcontrolCustomerAmount, int checkinCustomerAmount, int baggagedropCustomerAmount,
			int ticketinspectionCustomerAmount, int securitycheckCustomerAmount, double simulationTotaltime,
			double checkinBusyTime, double baggagedropBusyTime, double securitycheckBusyTime,
			double passportcontrolBusyTime, double ticketinspectionBusyTime) {
		super();
		this.id = id;
		this.passportcontrolAverage = passportcontrolAverage;
		this.passportcontrolmedian = passportcontrolmedian;
		this.baggagedropAverage = baggagedropAverage;
		this.baggagedropmedian = baggagedropmedian;
		this.securitycheckAverage = securitycheckAverage;
		this.securitycheckmedian = securitycheckmedian;
		this.ticketinspectionAverage = ticketinspectionAverage;
		this.ticketinspectionmedian = ticketinspectionmedian;
		this.checkinAverage = checkinAverage;
		this.checkinmedian = checkinmedian;
		CustomerRunTimeAverage = customerRunTimeAverage;
		this.arivedCustomerAmount = arivedCustomerAmount;
		this.servicedCustomerAmount = servicedCustomerAmount;
		this.passportcontrolCustomerAmount = passportcontrolCustomerAmount;
		this.checkinCustomerAmount = checkinCustomerAmount;
		this.baggagedropCustomerAmount = baggagedropCustomerAmount;
		this.ticketinspectionCustomerAmount = ticketinspectionCustomerAmount;
		this.securitycheckCustomerAmount = securitycheckCustomerAmount;
		this.simulationTotaltime = simulationTotaltime;
		this.checkinBusyTime = checkinBusyTime;
		this.baggagedropBusyTime = baggagedropBusyTime;
		this.securitycheckBusyTime = securitycheckBusyTime;
		this.passportcontrolBusyTime = passportcontrolBusyTime;
		this.ticketinspectionBusyTime = ticketinspectionBusyTime;
	}

	public SimulationData() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPassportcontrolAverage() {
		return passportcontrolAverage;
	}

	public void setPassportcontrolAverage(double passportcontrolAverage) {
		this.passportcontrolAverage = passportcontrolAverage;
	}

	public double getPassportcontrolmedian() {
		return passportcontrolmedian;
	}

	public void setPassportcontrolmedian(double passportcontrolmedian) {
		this.passportcontrolmedian = passportcontrolmedian;
	}

	public double getBaggagedropAverage() {
		return baggagedropAverage;
	}

	public void setBaggagedropAverage(double baggagedropAverage) {
		this.baggagedropAverage = baggagedropAverage;
	}

	public double getBaggagedropmedian() {
		return baggagedropmedian;
	}

	public void setBaggagedropmedian(double baggagedropmedian) {
		this.baggagedropmedian = baggagedropmedian;
	}

	public double getSecuritycheckAverage() {
		return securitycheckAverage;
	}

	public void setSecuritycheckAverage(double securitycheckAverage) {
		this.securitycheckAverage = securitycheckAverage;
	}

	public double getSecuritycheckmedian() {
		return securitycheckmedian;
	}

	public void setSecuritycheckmedian(double securitycheckmedian) {
		this.securitycheckmedian = securitycheckmedian;
	}

	public double getTicketinspectionAverage() {
		return ticketinspectionAverage;
	}

	public void setTicketinspectionAverage(double ticketinspectionAverage) {
		this.ticketinspectionAverage = ticketinspectionAverage;
	}

	public double getTicketinspectionmedian() {
		return ticketinspectionmedian;
	}

	public void setTicketinspectionmedian(double ticketinspectionmedian) {
		this.ticketinspectionmedian = ticketinspectionmedian;
	}

	public double getCheckinAverage() {
		return checkinAverage;
	}

	public void setCheckinAverage(double checkinAverage) {
		this.checkinAverage = checkinAverage;
	}

	public double getCheckinmedian() {
		return checkinmedian;
	}

	public void setCheckinmedian(double checkinmedian) {
		this.checkinmedian = checkinmedian;
	}

	public double getCustomerRunTimeAverage() {
		return CustomerRunTimeAverage;
	}

	public void setCustomerRunTimeAverage(double customerRunTimeAverage) {
		CustomerRunTimeAverage = customerRunTimeAverage;
	}

	public int getArivedCustomerAmount() {
		return arivedCustomerAmount;
	}

	public void setArivedCustomerAmount(int arivedCustomerAmount) {
		this.arivedCustomerAmount = arivedCustomerAmount;
	}

	public int getServicedCustomerAmount() {
		return servicedCustomerAmount;
	}

	public void setServicedCustomerAmount(int servicedCustomerAmount) {
		this.servicedCustomerAmount = servicedCustomerAmount;
	}

	public int getPassportcontrolCustomerAmount() {
		return passportcontrolCustomerAmount;
	}

	public void setPassportcontrolCustomerAmount(int passportcontrolCustomerAmount) {
		this.passportcontrolCustomerAmount = passportcontrolCustomerAmount;
	}

	public int getCheckinCustomerAmount() {
		return checkinCustomerAmount;
	}

	public void setCheckinCustomerAmount(int checkinCustomerAmount) {
		this.checkinCustomerAmount = checkinCustomerAmount;
	}

	public int getBaggagedropCustomerAmount() {
		return baggagedropCustomerAmount;
	}

	public void setBaggagedropCustomerAmount(int baggagedropCustomerAmount) {
		this.baggagedropCustomerAmount = baggagedropCustomerAmount;
	}

	public int getTicketinspectionCustomerAmount() {
		return ticketinspectionCustomerAmount;
	}

	public void setTicketinspectionCustomerAmount(int ticketinspectionCustomerAmount) {
		this.ticketinspectionCustomerAmount = ticketinspectionCustomerAmount;
	}

	public int getSecuritycheckCustomerAmount() {
		return securitycheckCustomerAmount;
	}

	public void setSecuritycheckCustomerAmount(int securitycheckCustomerAmount) {
		this.securitycheckCustomerAmount = securitycheckCustomerAmount;
	}

	public double getSimulationTotaltime() {
		return simulationTotaltime;
	}

	public void setSimulationTotaltime(double simulationTotaltime) {
		this.simulationTotaltime = simulationTotaltime;
	}

	public double getCheckinBusyTime() {
		return checkinBusyTime;
	}

	public void setCheckinBusyTime(double checkinBusyTime) {
		this.checkinBusyTime = checkinBusyTime;
	}

	public double getBaggagedropBusyTime() {
		return baggagedropBusyTime;
	}

	public void setBaggagedropBusyTime(double baggagedropBusyTime) {
		this.baggagedropBusyTime = baggagedropBusyTime;
	}

	public double getSecuritycheckBusyTime() {
		return securitycheckBusyTime;
	}

	public void setSecuritycheckBusyTime(double securitycheckBusyTime) {
		this.securitycheckBusyTime = securitycheckBusyTime;
	}

	public double getPassportcontrolBusyTime() {
		return passportcontrolBusyTime;
	}

	public void setPassportcontrolBusyTime(double passportcontrolBusyTime) {
		this.passportcontrolBusyTime = passportcontrolBusyTime;
	}

	public double getTicketinspectionBusyTime() {
		return ticketinspectionBusyTime;
	}

	public void setTicketinspectionBusyTime(double ticketinspectionBusyTime) {
		this.ticketinspectionBusyTime = ticketinspectionBusyTime;
	}

}
