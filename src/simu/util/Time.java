package simu.util;

import simu.constants.Constants;

public class Time {
	/**
	 * Create n amount of minutes in seconds
	 * @param minutes how many minutes should be converted into seconds
	 * @return seconds
	 */
	public static int minutes(int minutes) {
		return Constants.SECONDS_IN_MINUTE * minutes;
	}

	/**
	 * Create n amount of seconds from seconds
	 * @param how many seconds
	 * @return identity
	 */
	public static int seconds(int seconds) {
		return seconds;
	}
}
