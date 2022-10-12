package lentoasemasimulaattori;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import simu.data.Database;
import simu.data.SimulationData;

class DatabaseTest {
	SimulationData yeet = new SimulationData();
	Database dao = new Database();

	@BeforeAll
	void setUpBeforeClass() throws Exception {
		yeet.setId(1);
		yeet.setCheckinAverage(2.0);
		yeet.setCheckinmedian(2.0);
		yeet.setBaggagedropAverage(100);
		yeet.setBaggagedropmedian(50.34);
		yeet.setSecuritycheckAverage(35.45);
		yeet.setSecuritycheckmedian(5665);
		yeet.setPassportcontrolAverage(806);
		yeet.setPassportcontrolmedian(345.54);
		yeet.setTicketinspectionAverage(0);
		yeet.setTicketinspectionmedian(0);
		yeet.setCustomerRunTimeAverage(806.2);
	}

	@Test
	@DisplayName("Testaa toimiiko tietokannasta hakeminen")
	void testGetAllFromDatabase() throws Exception {
		assertNotNull(dao.getAllFromDatabase(), "data tyhjä ei toimi oikein");
	}

	@Test
	@DisplayName("Testaa toimiiko settingien hakeminen")
	void testGetSettingsFromRunId() throws Exception {
		assertNotNull(dao.getSettingsFromRunId(1), "settings tyhjä ei toimi oikein");
	}

	@Test
	@DisplayName("Testaa toimiiko id hakeminen")
	void testGetLatestId() throws Exception {
		assertNotNull(dao.getLatestId(), "id tyhjä ei toimi oikein");
	}

}
