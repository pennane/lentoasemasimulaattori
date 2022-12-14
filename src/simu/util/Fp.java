package simu.util;

import java.util.Optional;

/**
 * Some general logic methods
 */
public class Fp {
	/**
	 * 
	 * @param <T>
	 * @param value
	 * @param defaultValue
	 * @return Default to defaultValue if value is null
	 */
	public static <T> T valueOr(T value, T defaultValue) {
		return Optional.ofNullable(value).orElse(defaultValue);
	}
}
