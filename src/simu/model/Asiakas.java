package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;

public class Asiakas {
	private long saapumisaika;
	private long poistumisaika;
	private int id;
	private static int totalAsiakkaat = 0;
	private static long sumLeadtime = 0; // lead time = läpimenoaika

	public static int getTotalAsiakkaat() {
		return Asiakas.totalAsiakkaat;
	}

	public Asiakas() {
		totalAsiakkaat++;
		id = totalAsiakkaat;

		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo " + saapumisaika);
	}

	public double getPoistumisaika() {
		return poistumisaika;
	}

	public void setPoistumisaika(long poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(long saapumisaika) {
		this.saapumisaika = saapumisaika;
	}

	public int getId() {
		return id;
	}

	public static double getAverageLeadtime() {
		return sumLeadtime / getTotalAsiakkaat();
	}

	public void raportti() {
		Trace.out(Trace.Level.INFO, "\nAsiakas " + id + " valmis! ");
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " saapui: " + saapumisaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " poistui: " + poistumisaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " viipyi: " + (poistumisaika - saapumisaika));
		long leadtime = poistumisaika - saapumisaika;
		sumLeadtime += leadtime;
		Trace.out(Trace.Level.INFO, "Asiakkaiden läpimenoaikojen keskiarvo tähän asti " + getAverageLeadtime());
	}

}
