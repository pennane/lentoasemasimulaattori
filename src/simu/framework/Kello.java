package simu.framework;
//Singleton for time for the simulation
public class Kello {

	private long aika;
	private static Kello instanssi;

	private Kello() {
		aika = 0;
	}

	public static Kello getInstance() {
		if (instanssi == null) {
			instanssi = new Kello();
		}
		return instanssi;
	}

	public void setAika(long aika) {
		this.aika = aika;
	}

	public long getAika() {
		return aika;
	}
}
