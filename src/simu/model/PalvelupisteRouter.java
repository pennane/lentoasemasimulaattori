package simu.model;

import java.util.ArrayList;
import java.util.stream.Stream;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;

/**
 * Allows to have multiple service points for a single service point type. i.e.
 * airport has one area for passport checking but the area might have multiple
 * service points
 * 
 * Sorry for Finglish
 * 
 * @author arttupennanen
 *
 */
public class PalvelupisteRouter extends Palvelupiste {
	private int nextId;
	protected ArrayList<PalvelupisteEntiteetti> servicePoints;

	/**
	 * Fill the servicepoint array with new unoccupied servicepoints
	 */
	private void instantiatePalveluPisteet(int servicepointCount, String servicePointDescription) {
		nextId = 1;
		servicePoints = new ArrayList<>();
		for (int i = 0; i < servicepointCount; i++) {
			servicePoints.add(new PalvelupisteEntiteetti(servicePointDescription + (nextId++)));
		}

	}

	/**
	 * Constructor for usage without a subclass
	 */
	public PalvelupisteRouter(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi,
			int servicepointCount, String description) {
		super(generator, tapahtumalista, tyyppi, description);
		instantiatePalveluPisteet(servicepointCount, description);
	}

	/**
	 * Constructor for sublclasses that do not introduce the event type yet
	 */
	protected PalvelupisteRouter(ContinuousGenerator generator, Tapahtumalista tapahtumalista, int servicepointCount,
			String description) {
		super(generator, tapahtumalista, description);
		instantiatePalveluPisteet(servicepointCount, description);
	}

	/**
	 * Returns true if any of the inner service points are unoccupied
	 * 
	 * @return
	 */
	public Boolean pisteVapaana() {
		return servicePoints.stream().anyMatch(p -> p.getVarattu() == false);
	}

	@Override
	public void aloitaPalvelu() {

		// This method should never be called if there is no unoccupied service points.
		// The orElseThrow is to catch possible developer errors
		PalvelupisteEntiteetti palvelupiste = servicePoints.stream().filter(p -> p.getVarattu() == false).findAny()
				.orElseThrow();

		Long palveluaika = (long) generator.sample();

		palvelupiste.palvele(jono.poll());
		palveluajat.add(palveluaika);

		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika,
				palvelupiste.getId()));
	}

	@Override
	public void aloitaPalvelu(TapahtumanTyyppi tapahtumanTyyppi) {
		PalvelupisteEntiteetti palvelupiste = servicePoints.stream().filter(p -> p.getVarattu() == false).findAny()
				.orElseThrow();

		Long palveluaika = (long) generator.sample();

		palvelupiste.palvele(jono.poll());
		palveluajat.add(palveluaika);

		tapahtumalista.lisaa(
				new Tapahtuma(tapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika, palvelupiste.getId()));
	}

	public LentoasemaAsiakas lopetaPalvelu(String palvelupisteID) {
		palvellutAsiakkaat++;

		PalvelupisteEntiteetti palvelupiste = servicePoints.stream().filter(p -> p.getId().equals(palvelupisteID))
				.findAny().orElseThrow();

		return palvelupiste.lopetaPalvelu();
	}

	public Stream<PalvelupisteEntiteetti> getDebugStream() {
		return servicePoints.stream();
	}

}
