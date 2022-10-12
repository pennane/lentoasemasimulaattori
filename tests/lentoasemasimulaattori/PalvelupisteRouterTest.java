package lentoasemasimulaattori;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import simu.constants.Constants;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import simu.model.LentoasemaAsiakas;
import simu.model.PalvelupisteRouter;
import simu.model.TapahtumanTyyppi;
import simu.util.Time;

/**
 * Test router usage
 * 
 * @author arttupennanen
 *
 */
class PalvelupisteRouterTest {
	private static final TapahtumanTyyppi TEST_TAPAHTUMA_TYYPPI = TapahtumanTyyppi.BGGAGE_START;
	private static final int TEST_INTERNAL_SERVICE_POINT_COUNT = 10;
	private static final String TEST_SERVICE_POINT_DESCRIPTION = "unbelievable test service point";

	ContinuousGenerator testGenerator;
	Tapahtumalista testEventList;
	PalvelupisteRouter testRouter;

	@BeforeEach
	void initialize() {
		Trace.setTraceLevel(Level.ERR);
		testEventList = new Tapahtumalista();
		testGenerator = new Normal(Time.minutes(60), 2);
		testRouter = new PalvelupisteRouter(testGenerator, testEventList, TEST_TAPAHTUMA_TYYPPI,
				TEST_INTERNAL_SERVICE_POINT_COUNT, TEST_SERVICE_POINT_DESCRIPTION);
	}

	@Test
	@DisplayName("It should increment id")
	void idIncrementTest() {

		int foundMaxId = testRouter.getDebugStream()
				.mapToInt(e -> Integer.parseInt(e.getId().substring(TEST_SERVICE_POINT_DESCRIPTION.length()))).max()
				.orElseThrow();

		assertEquals(TEST_INTERNAL_SERVICE_POINT_COUNT, foundMaxId, "Id did not increment correctly");
	}

	@Test
	@DisplayName("It should occupy and unoccupy service points")
	void occupiedTest() {

		for (int i = 0; i < TEST_INTERNAL_SERVICE_POINT_COUNT; i++) {
			testRouter.lisaaJonoon(new LentoasemaAsiakas(null, Constants.DEFAULT_BAGGAGE_DROP_PROBABILITY));
		}
		for (int i = 0; i < TEST_INTERNAL_SERVICE_POINT_COUNT; i++) {
			assertTrue("Was not available even when should", testRouter.pisteVapaana());
			testRouter.aloitaPalvelu();
		}
		assertTrue("Was available when should not", !testRouter.pisteVapaana());
		for (int i = 1; i <= TEST_INTERNAL_SERVICE_POINT_COUNT; i++) {
			testRouter.lopetaPalvelu(TEST_SERVICE_POINT_DESCRIPTION + i);
		}
		assertTrue("Some failed to unoccupy", testRouter.getDebugStream().allMatch(e -> !e.getVarattu()));
	}

}
