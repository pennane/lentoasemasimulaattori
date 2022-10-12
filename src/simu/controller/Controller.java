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

/**
 * Central controller between the gui and the motor of the simulator
 * 
 * The C of MVC
 *
 */
public class Controller implements IControllerVtoM, IControllerMtoV {

	private IOmaMoottori moottori;
	private ISimulatorGUI ui;

	public Controller(ISimulatorGUI ui) {
		this.ui = ui;
	}

	/**
	 * Start the simulator. Make the gears of the motor start turning.
	 */
	@Override
	public void launchSimulation(SimulatorSettings settings) {
		moottori = new OmaMoottori(this, settings);

		Trace.setTraceLevel(Level.WAR);
		((Thread) moottori).start();
	}

	/**
	 * Schedule updating the clock in the GUI visualization
	 */
	@Override
	public void visualizeCurrentTime(long timeStampSeconds) {
		Platform.runLater(() -> ui.getVisualization().setSimulationTimeSeconds(timeStampSeconds));
	}

	/**
	 * Schedule new customer to arrive in to the GUI visualization
	 */
	@Override
	public void visualizeCustomer() {
		Platform.runLater(() -> ui.getVisualization().newCustomer());
	}

	
	/**
	 * Schedule new airplane to depart in the GUI visualization
	 */
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

	/**
	 * Schedule visualization of the simulation ending in the GUI layout
	 */
	@Override
	public void visualizeFinish() {
		Platform.runLater(() -> ui.getSimulationLayoutController().handleFinish());
	}

	/**
	 * Schedule visualization of the realtime stats in the GUI layout
	 */
	@Override
	public void visualizeIntermediateStats(IntermediateStats stats) {
		Platform.runLater(() -> ui.getSimulationLayoutController().visualizeIntermediateStats(stats));
	}

	/**
	 * Accelerate the simulation speed
	 */
	@Override
	public void accellerateSimulation() {
		moottori.setSettingsViive(Math.max(moottori.getSettingsViive() * 0.9, 0.1));
	}

	/**
	 * Decelerate the simulation speed
	 */
	@Override
	public void decelerateSimulation() {
	moottori.setSettingsViive(Math.min(moottori.getSettingsViive() * 1.1, 100));
	}

	/**
	 * Allow GUI to know if the motor instance is simulating
	 */
	@Override
	public boolean isSimulationRunning() {
		return moottori != null && moottori.isSimulationRunning();
	}

	/**
	 *  Allow GUI to fetch the current motor instance settings
	 */
	@Override
	public SimulatorSettings getSimulatorSettings() {
		if (moottori == null) {
			return null;
		}
		return moottori.getSimulatorSettings();
	}

}
