package simu.framework;

import eduni.distributions.ContinuousGenerator;
import simu.model.TapahtumanTyyppi;

public class Saapumisprosessi {

	private ContinuousGenerator generaattori;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi tyyppi;

	public Saapumisprosessi(ContinuousGenerator g, Tapahtumalista tl, TapahtumanTyyppi tyyppi) {
		this.generaattori = g;
		this.tapahtumalista = tl;
		this.tyyppi = tyyppi;
	}

	public void generoiSeuraava() {
		Tapahtuma t = new Tapahtuma(tyyppi, Kello.getInstance().getAika() + (long) generaattori.sample());
		tapahtumalista.lisaa(t);
	}

}
