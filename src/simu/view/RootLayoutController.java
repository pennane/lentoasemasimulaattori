package simu.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class RootLayoutController {

	private SimulatorGUI simulatorGUI;

	public void initialize(SimulatorGUI simulatorGUI) {
		this.simulatorGUI = simulatorGUI;
	}

	public void showSimulationView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SimulatorGUI.class.getResource("SimulationLayout.fxml"));
			BorderPane simulationView = (BorderPane) loader.load();
			simulatorGUI.setCenterView(simulationView);

			SimulationLayoutController viewController = loader.getController();
			viewController.initialize(simulatorGUI);
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	public void showHistoryView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SimulatorGUI.class.getResource("HistoryLayout.fxml"));
			BorderPane historyView = (BorderPane) loader.load();

			simulatorGUI.setCenterView(historyView);

			HistoryLayoutController viewController = loader.getController();
			viewController.initialize(simulatorGUI);
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

}
