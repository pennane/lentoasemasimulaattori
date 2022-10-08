package simu.model;

import java.util.ArrayList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;

/**
 * Allows to have multiple service points for a single service point type. i.e.
 * airport has one area for passport checking but the area might have multiple
 * service points
 * 
 * @author arttupennanen
 *
 */
public class PalvelupisteRouter extends Palvelupiste {
	private int nextId;
	protected ArrayList<PalvelupisteEntiteetti> palveluPisteet;

	/**
	 * Fill the servicepoint array with unoccupied servicepoints *
	 */
	private void instantiatePalveluPisteet(int palvelupisteidenMaara, String kuvaus) {
		nextId = 0;
		palveluPisteet = new ArrayList<>();
		for (int i = 0; i < palvelupisteidenMaara; i++) {
			palveluPisteet.add(new PalvelupisteEntiteetti(kuvaus + (nextId++)));
		}

	}

	public PalvelupisteRouter(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi,
			int palvelupisteidenMaara, String kuvaus) {
		super(generator, tapahtumalista, tyyppi, kuvaus);
		instantiatePalveluPisteet(palvelupisteidenMaara, kuvaus);
	}

	public PalvelupisteRouter(ContinuousGenerator generator, Tapahtumalista tapahtumalista, int palvelupisteidenMaara,
			String kuvaus) {
		super(generator, tapahtumalista, kuvaus);
		instantiatePalveluPisteet(palvelupisteidenMaara, kuvaus);
	}

	/**
	 * Returns true if any of the inner service points are unoccupied
	 * 
	 * @return
	 */
	public Boolean pisteVapaana() {
		return palveluPisteet.stream().anyMatch(p -> p.getVarattu() == false);
	}

	@Override
	public void aloitaPalvelu() {

		// This method should never be called if there is no unoccupied service points.
		// The orElseThrow is to catch possible developer errors
		PalvelupisteEntiteetti palvelupiste = palveluPisteet.stream().filter(p -> p.getVarattu() == false).findAny()
				.orElseThrow();

		Long palveluaika = (long) generator.sample();

		palvelupiste.palvele(jono.poll());
		palveluajat.add(palveluaika);

		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika,
				palvelupiste.getId()));
	}

	@Override
	public void aloitaPalvelu(TapahtumanTyyppi tapahtumanTyyppi) {
		PalvelupisteEntiteetti palvelupiste = palveluPisteet.stream().filter(p -> p.getVarattu() == false).findAny()
				.orElseThrow();

		Long palveluaika = (long) generator.sample();

		palvelupiste.palvele(jono.poll());
		palveluajat.add(palveluaika);

		tapahtumalista.lisaa(
				new Tapahtuma(tapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika, palvelupiste.getId()));
	}

	public LentoasemaAsiakas lopetaPalvelu(String palvelupisteID) {
		palvellutAsiakkaat++;

		PalvelupisteEntiteetti palvelupiste = palveluPisteet.stream().filter(p -> p.getId().equals(palvelupisteID))
				.findAny().orElseThrow();

		return palvelupiste.lopetaPalvelu();
	}

}
