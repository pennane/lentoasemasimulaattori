package simu.constants;

/**
 * Centralized place for constants for mitigating magic numbers.
 *
 */
public class Constants {
	public static final Integer SECONDS_IN_MINUTE = 60;
	public static final Integer SECONDS_IN_HOUR = SECONDS_IN_MINUTE * 60;
	public static final Integer SECONDS_IN_DAY = SECONDS_IN_HOUR * 24;

	public static final Integer DEFAULT_SIMULATION_DURATION_SECONDS = SECONDS_IN_DAY;
	public static final Integer DEFAULT_SIMULATION_DELAY = 10;

	public static final Long DEFAULT_SEED = 12346l;

	public static final Double DEFAULT_MEAN_SECONDS_BETWEEN_CUSTOMERS = 8.7d;
	public static final Integer DEFAULT_PLANES_PER_DAY = 100; // this probably should be dynamically computed from
																// customers per minute in settings or somewhere?
	public static final Integer DEFAULT_CHECKIN_AMOUNT = 21;
	public static final Integer DEFAULT_BAGGAGE_DROP_AMOUNT = 27;
	public static final Integer DEFAULT_SECURITY_CHECK_AMOUNT = 14;
	public static final Integer DEFAULT_PASSPORT_CONTROL_AMOUNT = 4;
	public static final Integer DEFAULT_TICKET_INSPECTION_AMOUNT = 10;
	public static final Double DEFAULT_SHENGEN_PROBABILITY = 0.75;
	public static final Double DEFAULT_BAGGAGE_DROP_PROBABILITY = 0.55;

	public static final int DEFAULT_QUARTERS_BEFORE_FIRST_PLANE = 4;
}
