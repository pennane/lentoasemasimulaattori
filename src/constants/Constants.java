package constants;

public class Constants {
	public static final int SECONDS_IN_MINUTE = 60;
	public static final int SECONDS_IN_HOUR = SECONDS_IN_MINUTE * 60;
	public static final int SECONDS_IN_DAY = SECONDS_IN_HOUR * 24;
	
	public static final int SIMULATION_DURATION = SECONDS_IN_DAY;
	public static final int SIMULATION_DELAY = 10;

	public static int minutes(int seconds) {
		return SECONDS_IN_MINUTE * seconds;
	}

	public static int seconds(int seconds) {
		return seconds;
	}
}
