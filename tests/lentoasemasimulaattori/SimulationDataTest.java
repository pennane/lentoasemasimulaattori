package lentoasemasimulaattori;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import simu.data.SimulationData;

class SimulationDataTest {
	static SimulationData yeet = new SimulationData();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		yeet.setCheckinAverage(2.0);
		yeet.setCheckinmedian(2.0);
		yeet.setBaggagedropAverage(100);
		yeet.setBaggagedropmedian(50.34);
		yeet.setSecuritycheckAverage(35.45);
		yeet.setSecuritycheckmedian(5665);
		yeet.setPassportcontrolAverage(806.6);
		yeet.setPassportcontrolmedian(345.54);
		yeet.setTicketinspectionAverage(0);
		yeet.setTicketinspectionmedian(0);
		// yeet.setCustomerRunTimeAverage();
	}

	@Test
	void testGetPassportcontrolAverage() {
		assertEquals(806.6, yeet.getPassportcontrolAverage(), "Väärä keskiarvo");
	}

	@Test
	void testGetPassportcontrolmedian() {
		assertEquals(345.54, yeet.getPassportcontrolAverage(), "Väärä mediani");
	}

}
