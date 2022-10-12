package simu.model;

public class IntermediateStats {
	private Integer totalInQueue;
	private Integer totalServed;
	private Integer totalAvgLeadTime;

	private Integer checkInInQueue;
	private Integer checkInServed;
	private Integer checkInAvgLeadTime;
	private Integer baggageDropInQueue;
	private Integer baggageDropServed;
	private Integer baggageDropAvgLeadTime;
	private Integer securityCheckInQueue;
	private Integer securityCheckServed;
	private Integer securityCheckAvgLeadTime;
	private Integer passportControlInQueue;
	private Integer passportControlServed;
	private Integer passportControlAvgLeadTime;
	private Integer ticketInspectionInQueue;
	private Integer ticketInspectionServed;
	private Integer ticketInspectionAvgLeadTime;

	private Integer shengePlanesLeft;
	private Integer shengeCustomersServed;
	private Integer shengeCustomersInAirport;
	private Integer internationalPlanesLeft;
	private Integer internationalCustomersServed;
	private Integer internationalCustomersInAirport;

	public IntermediateStats() {

	}

	public void buildRouterStats(PalvelupisteRouter checkInRouter, PalvelupisteRouter baggageDropRouter,
			PalvelupisteRouter securityCheckRouter, PalvelupisteRouter passportControlRouter,
			PalvelupisteRouter ticketInspectionRouter) {

		checkInInQueue = checkInRouter.jono.size();
		checkInServed = checkInRouter.getPalvellutAsiakkaat();
		checkInAvgLeadTime = (int) checkInRouter.findPalveluaikaKeskiarvo();

		baggageDropInQueue = baggageDropRouter.jono.size();
		baggageDropServed = baggageDropRouter.getPalvellutAsiakkaat();
		baggageDropAvgLeadTime = (int) baggageDropRouter.findPalveluaikaKeskiarvo();

		securityCheckInQueue = securityCheckRouter.jono.size();
		securityCheckServed = securityCheckRouter.getPalvellutAsiakkaat();
		securityCheckAvgLeadTime = (int) securityCheckRouter.findPalveluaikaKeskiarvo();

		passportControlInQueue = passportControlRouter.jono.size();
		passportControlServed = passportControlRouter.getPalvellutAsiakkaat();
		passportControlAvgLeadTime = (int) passportControlRouter.findPalveluaikaKeskiarvo();

		ticketInspectionInQueue = ticketInspectionRouter.jono.size();
		ticketInspectionServed = ticketInspectionRouter.getPalvellutAsiakkaat();
		ticketInspectionAvgLeadTime = (int) ticketInspectionRouter.findPalveluaikaKeskiarvo();
	}

	public void buildPlaneStats(LentoLista planes) {
		shengePlanesLeft = planes.findDepartedShengenPlanesCount();
		shengeCustomersServed = planes.findDepartedShengenCustomersCount();
		shengeCustomersInAirport = planes.findShengenCustomersInAirportCount();
		internationalPlanesLeft = planes.findDepartedInternationalPlanesCount();
		internationalCustomersServed = planes.findDepartedInternationalCustomersCount();
		internationalCustomersInAirport = planes.findInternationalCustomersInAirportCount();
	}

	public void buildTotalStats() {
		totalInQueue = checkInInQueue + baggageDropInQueue + securityCheckInQueue + passportControlInQueue
				+ ticketInspectionInQueue;

		totalServed = ticketInspectionServed;

		totalAvgLeadTime = (int) LentoasemaAsiakas.getAverageLeadtime();
	}

	public Integer getTotalInQueue() {
		return totalInQueue;
	}

	public Integer getTotalServed() {
		return totalServed;
	}

	public Integer getTotalAvgLeadTime() {
		return totalAvgLeadTime;
	}

	public Integer getCheckInInQueue() {
		return checkInInQueue;
	}

	public Integer getCheckInServed() {
		return checkInServed;
	}

	public Integer getCheckInAvgLeadTime() {
		return checkInAvgLeadTime;
	}

	public Integer getBaggageDropInQueue() {
		return baggageDropInQueue;
	}

	public Integer getBaggageDropServed() {
		return baggageDropServed;
	}

	public Integer getBaggageDropAvgLeadTime() {
		return baggageDropAvgLeadTime;
	}

	public Integer getSecurityCheckInQueue() {
		return securityCheckInQueue;
	}

	public Integer getSecurityCheckServed() {
		return securityCheckServed;
	}

	public Integer getSecurityCheckAvgLeadTime() {
		return securityCheckAvgLeadTime;
	}

	public Integer getPassportControlInQueue() {
		return passportControlInQueue;
	}

	public Integer getPassportControlServed() {
		return passportControlServed;
	}

	public Integer getPassportControlAvgLeadTime() {
		return passportControlAvgLeadTime;
	}

	public Integer getTicketInspectionInQueue() {
		return ticketInspectionInQueue;
	}

	public Integer getTicketInspectionServed() {
		return ticketInspectionServed;
	}

	public Integer getTicketInspectionAvgLeadTime() {
		return ticketInspectionAvgLeadTime;
	}

	public Integer getShengePlanesLeft() {
		return shengePlanesLeft;
	}

	public Integer getShengeCustomersServed() {
		return shengeCustomersServed;
	}

	public Integer getShengeCustomersInAirport() {
		return shengeCustomersInAirport;
	}

	public Integer getInternationalPlanesLeft() {
		return internationalPlanesLeft;
	}

	public Integer getInternationalCustomersServed() {
		return internationalCustomersServed;
	}

	public Integer getInternationalCustomersInAirport() {
		return internationalCustomersInAirport;
	}

}
