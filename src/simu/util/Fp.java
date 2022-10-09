package simu.util;

import java.util.Optional;

/**
 * Some general logic methods
 * @author arttupennanen
 *
 */
public class Fp {
	/**
	 * @return Default to defaultValue if value is null
	 */
	public static <T> T valueOr(T value, T defaultValue) {
		return Optional.ofNullable(value).orElse(defaultValue);
	}
}
