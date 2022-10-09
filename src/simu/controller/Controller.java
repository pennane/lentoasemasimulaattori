package simu.controller;

import javafx.application.Platform;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import simu.model.FlightType;
import simu.model.IOmaMoottori;
import simu.model.IntermediateStats;
import simu.model.OmaMoottori;
import simu.model.SimulatorSettings;
import simu.view.ISimulatorGUI;

public class Controller implements IControllerVtoM, IControllerMtoV {

	private IOmaMoottori moottori;
	private ISimulatorGUI ui;

	public Controller(ISimulatorGUI ui) {
		this.ui = ui;
	}

	@Override
	public void launchSimulation(SimulatorSettings settings) {
		moottori = new OmaMoottori(this, settings);

		Trace.setTraceLevel(Level.WAR);
		((Thread) moottori).start();
	}

	@Override
	public void visualizeCurrentTime(long timeStampSeconds) {
		Platform.runLater(() -> ui.getVisualization().setSimulationTimeSeconds(timeStampSeconds));
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
			Trace.out(Trace.Level.ERR, "Got unhandled FlightType " + flightType.toString());
			throw new UnsupportedOperationException();
		}
		}

	}

	@Override
	public void visualizeFinish() {
		Platform.runLater(() -> ui.getVisualization().finish());
	}

	@Override
	public void visualizeIntermediateStats(IntermediateStats stats) {
		Platform.runLater(() -> ui.getSimulationLayoutController().showIntermediateStats(stats));
	}

	@Override
	public void accellerateSimulation() {
		moottori.setSettingsViive(Math.max(moottori.getSettingsViive() - 1, 1));
	}

	@Override
	public void decelerateSimulation() {
		moottori.setSettingsViive(moottori.getSettingsViive() + 1);
	}

}
