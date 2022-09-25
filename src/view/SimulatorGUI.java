package view;

import controller.Controller;
import controller.IControllerVtoM;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import simu.framework.Trace;
import simu.framework.Trace.Level;

public class SimulatorGUI implements ISimulatorGUI {

	// Kontrollerin esittely (tarvitaan käyttöliittymässä)
	private IControllerVtoM controller;

	private SimulatorApplication application;

	@FXML
	private Canvas simulationRoot; // Käyttöliittymäkomponentti

	@FXML
	private IVisualization visualization = null; // Työjuhta

	@FXML
	private Button startButton;
	@FXML
	private Button accellerateButton;
	@FXML
	private Button decelerateButton;

	public void handleStart() {
		Trace.setTraceLevel(Level.INFO);
		System.out.println("Start");
		if (visualization == null) {
			visualization = new Visualization(simulationRoot);
			visualization.drawBase();
		}
		if (controller == null) {
			controller = new Controller(this);
		}
	}

	public void handleLaunchSimulation() {
		System.out.println("SIMULOIDAAAN :DD");
		startButton.setDisable(true);
		controller.launchSimulation();
	}

	@Override
	public IVisualization getVisualization() {
		return visualization;
	}

	public void setApplication(SimulatorApplication application) {
		this.application = application;
	}
}
