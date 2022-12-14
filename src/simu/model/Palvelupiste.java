package simu.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;

/**
 * This has been made abstract so that it is not accidentally used in code by
 * itself.
 * 
 * The simulator only uses PalvelupisteRouter now.
 */
public abstract class Palvelupiste {

	protected LinkedList<LentoasemaAsiakas> jono = new LinkedList<>();
	protected ArrayList<Long> palveluajat = new ArrayList<Long>();
	public String palvelupisteDescription;
	protected ContinuousGenerator generator;
	protected Tapahtumalista tapahtumalista;
	protected TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;

	protected int palvellutAsiakkaat = 0;

	private boolean varattu = false;

	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi,
			String tiedot) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.palvelupisteDescription = tiedot;
	}

	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, String tiedot) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.palvelupisteDescription = tiedot;
	}

	public void lisaaJonoon(LentoasemaAsiakas a) { // Jonon 1. asiakas aina palvelussa
		jono.add(a);
	}

	public LentoasemaAsiakas otaJonosta() { // Poistetaan palvelussa ollut
		varattu = false;
		palvellutAsiakkaat++;
		return jono.poll();

	}

	public void aloitaPalvelu() { // Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana

		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());

		varattu = true;
		Long palveluaika = (long) generator.sample();
		palveluajat.add(palveluaika);
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));

	}

	public void aloitaPalvelu(TapahtumanTyyppi skeduloitavanTapahtumanTyyppi) {

		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());

		varattu = true;
		long palveluaika = (long) generator.sample();
		palveluajat.add(palveluaika);
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));

	}

	public boolean onVarattu() {
		return varattu;
	}

	public boolean onJonossa() {
		return !jono.isEmpty();
	}

	public int getPalvellutAsiakkaat() {
		return palvellutAsiakkaat;
	}

	public double findPalveluaikaKeskiarvo() {
		double keskiarvo = 0;
		for (int i = 0; i < palveluajat.size(); i++) {
			keskiarvo = keskiarvo + palveluajat.get(i);
		}
		return keskiarvo / palveluajat.size();
	}

	public double findPalveluaikaAverage() {

		ArrayList<Long> palveluajat = new ArrayList<Long>(this.palveluajat);
		Collections.sort(palveluajat);

		if (palveluajat.size() % 2 == 1)
			return palveluajat.get((palveluajat.size() + 1) / 2 - 1);
		else {
			double lower = palveluajat.get(palveluajat.size() / 2 - 1);
			double upper = palveluajat.get(palveluajat.size() / 2);

			return (lower + upper) / 2.0;
		}
	}

	public String getPalvelupisteDescription() {
		return palvelupisteDescription;
	}

}
