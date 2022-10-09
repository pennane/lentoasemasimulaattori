package simu.model;

import eduni.distributions.RandomGenerator;
import eduni.distributions.Uniform;
import simu.constants.Constants;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import simu.util.Time;

public class LentokoneGeneraattori {
	private SimulatorSettings settings;
	private LentoLista lista;

	public LentokoneGeneraattori(LentoLista lista, SimulatorSettings settings) {
		this.lista = lista;
		this.settings = settings;
	}

	public void generoi(int maara) {
		FlightType type;
		RandomGenerator rnd = new RandomGenerator();
		Uniform urnd = new Uniform(100, 300);

		int simulationHours = (int) (settings.getSimulationDurationSeconds() / (long) Constants.SECONDS_IN_HOUR);
		int simulationQuarters = (int) Math.floor(simulationHours * 4);
		
		
		Uniform departingQuarterGenerator = new Uniform(Constants.DEFAULT_QUARTERS_BEFORE_FIRST_PLANE,
				simulationQuarters);

		for (int i = 1; i <= maara; i++) {
			type = (rnd.sample() <= settings.getShengenProbability()) ? FlightType.Shengen : FlightType.International;
			int seatCount = (int) urnd.sample();
			long departingTime = (long) (departingQuarterGenerator.sample() * Time.minutes(15));
			Trace.out(Level.INFO,
					"Luodaan uusi " + type + " lento matkustajamäärällä: " + seatCount + " ja ajalle " + departingTime);
			lista.lisaaListaan(new Lentokone(type, seatCount, departingTime));
		}
	}

}
