package simu.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public class Palvelupiste {

	protected LinkedList<LentoasemaAsiakas> jono = new LinkedList<>(); // Tietorakennetoteutus
	private ArrayList<Double> palveluajat = new ArrayList<Double>();

	protected ContinuousGenerator generator;
	protected Tapahtumalista tapahtumalista;
	protected TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;

	private int palvellutAsiakkaat = 0;

	private boolean varattu = false;

	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
	}

	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
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
		double palveluaika = generator.sample();
		palveluajat.add(palveluaika);
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));

	}

	public void aloitaPalvelu(TapahtumanTyyppi skeduloitavanTapahtumanTyyppi) {

		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());

		varattu = true;
		double palveluaika = generator.sample();
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

	public double findPalveluajanKeskiarvo() {
		double keskiarvo = 0;
		for (int i = 0; i < palveluajat.size(); i++) {
			keskiarvo = keskiarvo + palveluajat.get(i);
		}
		return keskiarvo / palveluajat.size();
	}

	public double findMedian() {
		ArrayList<Double> palveluajat = new ArrayList<>(this.palveluajat);
		Collections.sort(palveluajat);

		if (palveluajat.size() % 2 == 1)
			return palveluajat.get((palveluajat.size() + 1) / 2 - 1);
		else {
			double lower = palveluajat.get(palveluajat.size() / 2 - 1);
			double upper = palveluajat.get(palveluajat.size() / 2);

			return (lower + upper) / 2.0;
		}
	}

}
