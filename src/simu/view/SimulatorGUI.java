package simu.view;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import simu.controller.Controller;
import simu.controller.IControllerVtoM;

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
		System.out.println("Start");
		if (visualization == null) {
			visualization = new Visualization(simulationRoot);
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
