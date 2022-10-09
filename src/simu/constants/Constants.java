package simu.constants;

public class Constants {
	public static final Integer SECONDS_IN_MINUTE = 60;
	public static final Integer SECONDS_IN_HOUR = SECONDS_IN_MINUTE * 60;
	public static final Integer SECONDS_IN_DAY = SECONDS_IN_HOUR * 24;



	public static Integer minutes(int seconds) {
		return SECONDS_IN_MINUTE * seconds;
	}

	public static Integer seconds(int seconds) {
		return seconds;
	}
	
	public static final Integer DEFAULT_SIMULATION_DURATION_SECONDS = SECONDS_IN_DAY;
	public static final Integer DEFAULT_SIMULATION_DELAY = 10;

	public static final Long DEFAULT_SEED = 12346l;

	public static final Double DEFAULT_MEAN_SECONDS_BETWEEN_CUSTOMERS = 7d;
	public static final Double DEFAULT_PLANES_PER_DAY = -1d; // this probably should be dynamically computed from
																// customers per minute ?
	public static final Integer DEFAULT_CHECKIN_AMOUNT = 11;
	public static final Integer DEFAULT_BAGGAGE_DROP_AMOUNT = 12;
	public static final Integer DEFAULT_SECURITY_CHECK_AMOUNT = 5;
	public static final Integer DEFAULT_PASSPORT_CONTROL_AMOUNT = 5;
	public static final Integer DEFAULT_TICKET_INSPECTION_AMOUNT = 11;
	public static final Double DEFAULT_SHENGEN_PROBABILITY = 0.75;
	public static final Double DEFAULT_BAGGE_PROBABILITY = 0.55;
	
	public static final int DEFAULT_QUARTERS_BEFORE_FIRST_PLANE = 4;
}
