package simu.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * Controls the layout that allows changing between the selected main layout eg.
 * Simulator and history
 *
 */
public class RootLayoutController {

	private SimulatorGUI simulatorGUI;

	/**
	 * This has to be ran before functionality can be safely accessed
	 */
	public void initialize(SimulatorGUI simulatorGUI) {
		this.simulatorGUI = simulatorGUI;
	}

	/**
	 * Set simulation as the selected view
	 */
	public void showSimulationView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RootLayoutController.class.getResource("SimulationLayout.fxml"));
			BorderPane simulationView = (BorderPane) loader.load();
			simulatorGUI.setCenterView(simulationView);

			SimulationLayoutController viewController = loader.getController();
			viewController.initialize(simulatorGUI);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set history as the selected view
	 */
	public void showHistoryView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RootLayoutController.class.getResource("HistoryLayout.fxml"));
			BorderPane historyView = (BorderPane) loader.load();

			simulatorGUI.setCenterView(historyView);

			HistoryLayoutController viewController = loader.getController();
			viewController.initialize(simulatorGUI);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
