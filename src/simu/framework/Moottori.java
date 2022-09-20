package simu.framework;

import java.util.ArrayList;

import controller.IKontrolleriMtoV;
import simu.model.Palvelupiste;

public abstract class Moottori extends Thread implements IMoottori { // UUDET MÄÄRITYKSET

	private double simulointiaika = 0;
	private long viive = 0;

	private Kello kello;

	protected Tapahtumalista tapahtumalista;
	protected ArrayList<Palvelupiste> palvelupisteet;

	protected IKontrolleriMtoV kontrolleri;

	public Moottori(IKontrolleriMtoV kontrolleri) {

		this.kontrolleri = kontrolleri;

		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia

		tapahtumalista = new Tapahtumalista();

		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa

	}

	@Override
	public void setSimulointiaika(double aika) {
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

	private void suoritaBTapahtumat() {
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()) {
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	private void yritaCTapahtumat() { // määrittele protectediksi, josa haluat ylikirjoittaa
		for (Palvelupiste p : palvelupisteet) {
			if (!p.onVarattu() && p.onJonossa()) {
				p.aloitaPalvelu();
			}
		}
	}

	private double nykyaika() {
		return tapahtumalista.getSeuraavanAika();
	}

	private boolean simuloidaan() {
		Trace.out(Trace.Level.INFO, "Kello on: " + kello.getAika());
		return kello.getAika() < simulointiaika;
	}

	private void viive() {
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