package simu.model;

import eduni.distributions.RandomGenerator;
import eduni.distributions.Uniform;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import simu.constants.Constants;

public class LentokoneGeneraattori {

	private static int QUARTERS_BEFORE_FIRST_PLANE = 6;

	private double shengen_weight = 0.5; // Mikä osuus lentokoneista keskimäärin lentää shengen alueella 0.0 ... 1.0
	private double international_weight = 1.0 - shengen_weight;
	private LentoLista lista;

	public LentokoneGeneraattori(LentoLista lista) {
		this.lista = lista;
	}

	public void generoi(int maara) {
		FlightType type;
		RandomGenerator rnd = new RandomGenerator();
		Uniform urnd = new Uniform(100, 300);

		int simulationHours = Constants.SIMULATION_DURATION / Constants.SECONDS_IN_HOUR;
		int simulationQuarters = (int) Math.floor(simulationHours * 4);
		Uniform departingQuarterGenerator = new Uniform(QUARTERS_BEFORE_FIRST_PLANE, simulationQuarters);

		for (int i = 1; i <= maara; i++) {
			type = (rnd.sample() <= shengen_weight) ? FlightType.Shengen : FlightType.International;
			int seatCount = (int) urnd.sample();
			long departingTime = (long) (departingQuarterGenerator.sample() * Constants.minutes(15));
			Trace.out(Level.INFO,
					"Luodaan uusi " + type + " lento matkustajamäärällä: " + seatCount + " ja ajalle " + departingTime);
			lista.lisaaListaan(new Lentokone(type, seatCount, departingTime));
		}
	}

}
