package simu.framework;

import java.util.ArrayList;

import controller.IControllerMtoV;
import simu.model.Palvelupiste;
import simu.model.PalvelupisteRouter;

public abstract class Moottori extends Thread implements IMoottori { // UUDET MÄÄRITYKSET

	protected long simulointiaika = 0;
	protected long viive = 0;

	protected Kello kello;

	protected Tapahtumalista tapahtumalista;
	protected ArrayList<PalvelupisteRouter> palvelupisteet;

	protected IControllerMtoV controller;

	public Moottori(IControllerMtoV controller) {
		this.controller = controller;

		palvelupisteet = new ArrayList<>();
		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia

		tapahtumalista = new Tapahtumalista();

		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa

	}

	@Override
	public void setSimulointiaika(long aika) {
		simulointiaika = aika;
	}

	@Override
	public void setViive(long viive) {
		this.viive = viive;
	}

	@Override
	public long getViive() {
		return viive;
	}

	@Override
	public void run() { // Entinen aja()
		alustukset(); // luodaan mm. ensimmäinen tapahtuma
		while (simuloidaan()) {
			Trace.out(Trace.Level.INFO, "\nA-vaihe: kello on " + nykyaika());
			viive();
			kello.setAika(nykyaika());

			Trace.out(Trace.Level.INFO, "\nB-vaihe:");
			suoritaBTapahtumat();

			Trace.out(Trace.Level.INFO, "\nC-vaihe:");
			yritaCTapahtumat();

		}
		tulokset();

	}

	protected void suoritaBTapahtumat() {
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()) {
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	protected void yritaCTapahtumat() { // määrittele protectediksi, josa haluat ylikirjoittaa
		for (Palvelupiste p : palvelupisteet) {
			if (!p.onVarattu() && p.onJonossa()) {
				p.aloitaPalvelu();
			}
		}
	}

	protected long nykyaika() {
		return tapahtumalista.getSeuraavanAika();
	}

	protected boolean simuloidaan() {
		Trace.out(Trace.Level.INFO, "Kello on: " + kello.getAika());
		return kello.getAika() < simulointiaika;
	}

	protected void viive() {
		Trace.out(Trace.Level.INFO, "Viive " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa

	protected abstract void suoritaTapahtuma(Tapahtuma t); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa

	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa

}