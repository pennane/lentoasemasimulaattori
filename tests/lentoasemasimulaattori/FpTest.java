package lentoasemasimulaattori;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import simu.util.Fp;

class FpTest {

	@Test
	@DisplayName("It should use presented value")
	void valueOrUseValueTest() {
		Integer value = 123;
		Integer fallback = 321;
		assertEquals(value, Fp.valueOr(value, fallback), "Failed to get value with valueOr");
	}

	@Test
	@DisplayName("It should use default value if presented is null")
	void valueOrUseDefaultTest() {
		Integer value = null;
		Integer fallback = 321;
		assertEquals(fallback, Fp.valueOr(value, fallback), "Failed to use default value if");
	}

	@Test
	@DisplayName("It should return null if both are null")
	void valueOrNeitherExistTest() {
		Integer value = null;
		Integer fallback = null;
		assertEquals(null, Fp.valueOr(value, fallback), "Failed to be null when neither value exist");
	}

}
