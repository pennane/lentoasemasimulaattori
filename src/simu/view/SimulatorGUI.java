package simu.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.controller.Controller;
import simu.controller.IControllerVtoM;

public class SimulatorGUI extends Application implements ISimulatorGUI {

	private Stage primaryStage;
	private BorderPane rootLayout;

	// Kontrollerin esittely (tarvitaan käyttöliittymässä)
	private IControllerVtoM controller;

	private IVisualization visualization; // Työjuhta
	private SimulationLayoutController simulationLayoutController;

	public SimulatorGUI() {
	}

	@Override
	public IVisualization getVisualization() {
		return visualization;
	}

	@Override
	public void setVisualization(IVisualization visualization) {
		this.visualization = visualization;
	}

	@Override
	public IControllerVtoM getController() {
		return this.controller;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Simulaattori");

		if (controller == null) {
			controller = new Controller(this);
		}

		initializeRootLayout();
	}

	public void initializeRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SimulatorGUI.class.getResource("RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			// Add event handler for quitting the simulation once the window is closed
			primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent window) {
					System.exit(1);
				}
			});
			RootLayoutController rootLayoutController = loader.getController();
			rootLayoutController.initialize(this);

			primaryStage.show();
			rootLayoutController.showSimulationView();

		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	public void setCenterView(Node view) {
		rootLayout.setCenter(view);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void setSimulationLayoutController(SimulationLayoutController controller) {
		this.simulationLayoutController = controller;
	}

	@Override
	public SimulationLayoutController getSimulationLayoutController() {
		return simulationLayoutController;
	}

}
