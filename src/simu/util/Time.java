package simu.util;

import simu.constants.Constants;

public class Time {
	public static int minutes(int seconds) {
		return Constants.SECONDS_IN_MINUTE * seconds;
	}

	public static int seconds(int seconds) {
		return seconds;
	}
}
