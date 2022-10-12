package simu.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.controller.Controller;
import simu.controller.IControllerVtoM;

/**
 * The root of the whole application.
 * 
 * Contains the javafx application. Sends messages between controller and the
 * view
 * 
 *
 */
public class SimulatorGUI extends Application implements ISimulatorGUI {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private IControllerVtoM controller;

	private IVisualization visualization; // Työjuhta
	private SimulationLayoutController simulationLayoutController;

	public SimulatorGUI() {
	}

	/**
	 * This method has to be run before the functionality can be safely accessed
	 * 
	 * Alot of stuff is initialized here
	 * 
	 * The simulation view is set as the default view
	 */
	public void initializeRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SimulatorGUI.class.getResource("RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false); // NO RESIZING ON MY PROPERTY !!!

			// Add event handler for quitting the simulation once the window is closed
			primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent window) {
					System.exit(1);
				}
			});

			RootLayoutController rootLayoutController = loader.getController();
			rootLayoutController.initialize(this);
			this.primaryStage.getIcons().add(new Image("file:simu/view/images/icon.png"));
			this.primaryStage.getIcons().add(new Image("file:simu/view/images/icon512.png"));
			this.primaryStage.getIcons().add(new Image("file:simu/view/images/icon256.png"));
			this.primaryStage.getIcons().add(new Image("file:simu/view/images/icon128.png"));
			this.primaryStage.getIcons().add(new Image("file:simu/view/images/icon64.png"));
			this.primaryStage.getIcons().add(new Image("file:simu/view/images/icon32.png"));
			this.primaryStage.getIcons().add(new Image("file:simu/view/images/icon16.png"));
			primaryStage.show();
			rootLayoutController.showSimulationView();

		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	/**
	 * Set a view into the root layout
	 * 
	 * @param view
	 */
	public void setCenterView(Node view) {
		rootLayout.setCenter(view);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Access the canvas visualization
	 */
	@Override
	public IVisualization getVisualization() {
		return visualization;
	}

	@Override
	public void setVisualization(IVisualization visualization) {
		this.visualization = visualization;
	}

	@Override
	public void setSimulationLayoutController(SimulationLayoutController controller) {
		this.simulationLayoutController = controller;
	}

	@Override
	public SimulationLayoutController getSimulationLayoutController() {
		return simulationLayoutController;
	}

	/**
	 * Retuns the MVC controller
	 * 
	 * Accessed by view
	 */
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

	public static void main(String[] args) {
		launch(args);
	}

}
