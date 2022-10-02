package simu.framework;

import simu.model.TapahtumanTyyppi;

public class Tapahtuma implements Comparable<Tapahtuma> {

	private TapahtumanTyyppi tyyppi;
	private long aika;
	private String palvelupisteId;
	
	public Tapahtuma(TapahtumanTyyppi tyyppi, long aika) {
		this.tyyppi = tyyppi;
		this.aika = aika;
		this.palvelupisteId = null;
	}
	
	public Tapahtuma(TapahtumanTyyppi tyyppi, long aika, String palvelupisteId) {
		this.tyyppi = tyyppi;
		this.aika = aika;
		this.palvelupisteId = palvelupisteId;
	}

	public void setTyyppi(TapahtumanTyyppi tyyppi) {
		this.tyyppi = tyyppi;
	}

	public TapahtumanTyyppi getTyyppi() {
		return tyyppi;
	}

	public void setAika(long aika) {
		this.aika = aika;
	}

	public long getAika() {
		return aika;
	}

	public String getPalvelupisteId() {
		return palvelupisteId;
	}

	@Override
	public int compareTo(Tapahtuma arg) {
		if (this.aika < arg.aika)
			return -1;
		else if (this.aika > arg.aika)
			return 1;
		return 0;
	}

}
