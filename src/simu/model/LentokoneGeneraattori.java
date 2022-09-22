package simu.model;
import eduni.distributions.*;

public class LentokoneGeneraattori {

	private double shengen_weight = 0.5; // Mikä osuus lentokoneista keskimäärin lentää shengen alueella 0.0 ... 1.0
	private double international_weight = 1.0 - shengen_weight;
	private LentoLista lista;
	
	
	public LentokoneGeneraattori(LentoLista lista) {
		this.lista = lista;
	}
	
	public void Generoi(int maara) {
		FlightType type;
		RandomGenerator rnd = new RandomGenerator();
		Uniform urnd = new Uniform(100, 300);
		for(int i = 1; i <= maara; i++) {
			type = (rnd.sample() <= shengen_weight) ? FlightType.Shengen : FlightType.International;
			int seatCount = (int) urnd.sample(); 
			System.out.println("Luodaan uusi " + type + " lento matkustajamäärällä: " + seatCount);
			lista.lisaaListaan(new Lentokone(type, seatCount));
		}
	}
	
}
