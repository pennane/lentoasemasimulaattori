package simu.model;

/**
 * To be used inside PalvelupisteRouter
 * 
 * Simple service point
 *
 * Acts as a single service point, eq single desk or single gate whereas the
 * PalvelupisteRouter is the whole collection of these said service points
 * 
 * Better name would have been just Palvelupiste, but that could have caused
 * misunderstandings in what is the responsibility of this class
 *
 */
public class PalvelupisteEntiteetti {
	private Boolean varattu;
	private String id;
	private LentoasemaAsiakas asiakas;

	public PalvelupisteEntiteetti(String id) {
		this.varattu = false;
		this.id = id;
	}

	public Boolean getVarattu() {
		return varattu;
	}

	public String getId() {
		return id;
	}

	public void palvele(LentoasemaAsiakas asiakas) {
		this.asiakas = asiakas;
		this.varattu = true;
	}

	public LentoasemaAsiakas lopetaPalvelu() {

		this.varattu = false;
		LentoasemaAsiakas asiakas = this.asiakas;
		this.asiakas = null;
		return asiakas;
	}

	@Override
	public String toString() {
		return "PalvelupisteEntiteetti [varattu=" + varattu + ", id=" + id + ", asiakas=" + asiakas + "]";
	}
}
