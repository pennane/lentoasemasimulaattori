package controller;

import constants.Constants;
import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.model.FlightType;
import simu.model.OmaMoottori;
import view.ISimulatorGUI;

public class Controller implements IControllerVtoM, IControllerMtoV {

	private IMoottori moottori;
	private ISimulatorGUI ui;

	public Controller(ISimulatorGUI ui) {
		this.ui = ui;
	}

	// Moottorin ohjausta:

	@Override
	public void launchSimulation() {
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		moottori.setSimulointiaika(Constants.SIMULATION_DURATION);
		moottori.setViive(Constants.SIMULATION_DELAY);

		ui.getVisualization().drawBase();

		((Thread) moottori).start();
	}

	@Override
	public void visualizeTime(long timeStampSeconds) {
		Platform.runLater(() -> ui.getVisualization().displayTime(timeStampSeconds));
	}

	@Override
	public void visualizeCustomer() {
		Platform.runLater(() -> ui.getVisualization().newCustomer());
	}

	@Override
	public void visualizeAirplane(FlightType flightType) {
		switch (flightType) {
		case International: {
			Platform.runLater(() -> ui.getVisualization().internationalDepart());
			break;
		}
		case Shengen: {
			Platform.runLater(() -> ui.getVisualization().shengeDepart());
			break;
		}
		default: {
			System.out.println(flightType);
			throw new UnsupportedOperationException();
		}
		}

	}

	@Override
	public void visualizeFinish() {
		Platform.runLater(() -> ui.getVisualization().finish());
	}

	@Override
	public void accellerateSimulation() {
		moottori.setViive((long) (moottori.getViive() * 1.10));
	}

	@Override
	public void decelerateSimulation() {
		moottori.setViive((long) (moottori.getViive() * 0.9));
	}

}
