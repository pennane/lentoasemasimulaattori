package controller;

import constants.Constants;
import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV {

	private IMoottori moottori;
	private ISimulaattorinUI ui;

	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
	}

	// Moottorin ohjausta:

	@Override
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		moottori.setSimulointiaika(Constants.SIMULATION_DURATION);
		
		moottori.setViive(Constants.SIMULATION_DELAY);
		
		
		ui.getVisualisointi().tyhjennaNaytto();

		((Thread) moottori).start();
	}

	@Override
	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long) (moottori.getViive() * 1.10));
	}

	@Override
	public void nopeuta() { // nopeutetaan moottorisäiettä
		moottori.setViive((long) (moottori.getViive() * 0.9));
	}

	// Simulointitulosten välittämistä käyttöliittymään.
	// Koska FX-ui:n päivitykset tulevat moottorisäikeestä, ne pitää ohjata
	// JavaFX-säikeeseen:

	@Override
	public void naytaLoppuaika(double aika) {
		Platform.runLater(() -> ui.setLoppuaika(aika));
	}

	@Override
	public void visualisoiAsiakas() {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.getVisualisointi().uusiAsiakas();
			}
		});
	}

}
