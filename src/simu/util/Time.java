package simu.util;

import simu.constants.Constants;

/**
 * Home of static time related utility methods
 */
public class Time {
	/**
	 * @param minutes
	 * @return seconds worth of asked minutes
	 */
	public static int minutes(int minutes) {
		return Constants.SECONDS_IN_MINUTE * minutes;
	}

	/**
	 * Beautiful abstraction
	 * 
	 * @param seconds
	 * @return seconds worth of asked seconds
	 * 
	 */
	public static int seconds(int seconds) {
		return seconds;
	}
}