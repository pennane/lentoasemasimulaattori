package simu.model;

import java.util.ArrayList;
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
	private int palveltu = 0;

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
		palveltu++;
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
		return jono.size() != 0;
	}
	public int getPalvellutAsiakkaat() {
		return palveltu;
	}
	public double getPalveluajanKeskiarvo() {
		 double keskiarvo=0;
		 for(int i = 0;i<palveluajat.size();i++) {
			 keskiarvo = keskiarvo+palveluajat.get(i);
		 }
		 return keskiarvo/palveluajat.size();
	}

}
