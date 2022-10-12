package lentoasemasimulaattori;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import simu.framework.Kello;
import simu.framework.Trace;
import simu.model.Asiakas;

class AsiakasTest {
	private final double DELTA = 0.000001;
	private static Asiakas asiakas;
	
	@BeforeAll
	static void initialize() {
		Trace.setTraceLevel(Trace.Level.ERR);
		Kello.getInstance().setAika(100);
		asiakas = new Asiakas();
	}
	
	@Test
	@DisplayName("Testaa onko saapumisaika oikea")
	void getSaapumisaikaTest() {
		assertEquals(100, asiakas.getSaapumisaika(), "Väärä saapumisaika");
	}
	
	@Test
	@DisplayName("Testaa onnistuuko poistumisajan asettaminen ja hakeminen")
	void setPoistumisaikaTest() {
		asiakas.setPoistumisaika(500);
		assertEquals(500, asiakas.getPoistumisaika(), "Väärä poistumisaika");
		
	}
	@Test
	@DisplayName("Testaa määrittyykö ID oikein")
	void getIDTest() {
		Asiakas testiAsiakas = new Asiakas();
		assertEquals(5, testiAsiakas.getId(), "Väärä ID");
	}
	@Test
	@DisplayName("Testaa onko keskimääräinen läpimenoaika oikein")
	void getAverageLeadTimeTesti() {
		asiakas.setPoistumisaika(300); //200
		asiakas.raportti();
		
		Kello.getInstance().setAika(200);
		Asiakas testiAsiakas1 = new Asiakas();
		testiAsiakas1.setPoistumisaika(500); //300 
		testiAsiakas1.raportti();
		
		Kello.getInstance().setAika(300);
		Asiakas testiAsiakas2 = new Asiakas();
		testiAsiakas2.setPoistumisaika(700); //400
		testiAsiakas2.raportti();
		
		Kello.getInstance().setAika(400);
		Asiakas testiAsiakas3 = new Asiakas(); //400
		testiAsiakas3.setPoistumisaika(800);
		testiAsiakas3.raportti();
		
		assertEquals(325.0, Asiakas.getAverageLeadtime(), DELTA, "Väärä keskimääräinen läpimenoaika");
	}
}
