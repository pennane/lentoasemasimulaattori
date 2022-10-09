package simu.view;

import static simu.constants.Constants.DEFAULT_BAGGAGE_DROP_AMOUNT;
import static simu.constants.Constants.DEFAULT_CHECKIN_AMOUNT;
import static simu.constants.Constants.DEFAULT_MEAN_SECONDS_BETWEEN_CUSTOMERS;
import static simu.constants.Constants.DEFAULT_PASSPORT_CONTROL_AMOUNT;
import static simu.constants.Constants.DEFAULT_PLANES_PER_DAY;
import static simu.constants.Constants.DEFAULT_SECURITY_CHECK_AMOUNT;
import static simu.constants.Constants.DEFAULT_SHENGEN_PROBABILITY;
import static simu.constants.Constants.DEFAULT_TICKET_INSPECTION_AMOUNT;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import simu.model.SimulatorSettings;

public class SimulationLayoutController {

	@FXML
	private Canvas simulationRoot; // Käyttöliittymäkomponentti

	private IVisualization visualization = null; // Työjuhta

	@FXML
	private TextField meanSecondsBetweenCustomers;
	@FXML
	private TextField planesPerDay;
	@FXML
	private TextField checkInAmount;
	@FXML
	private TextField baggageDropAmount;
	@FXML
	private TextField securityCheckAmount;
	@FXML
	private TextField passportControlAmount;
	@FXML
	private TextField ticketInspectionAmount;
	@FXML
	private TextField shengenProbability;
	@FXML
	private TextField baggageProbability;

	@FXML
	private Button startButton;
	@FXML
	private Button accellerateButton;
	@FXML
	private Button decelerateButton;

	private SimulatorGUI simulatorGUI;

	public void initialize(SimulatorGUI simulatorGUI) {
		this.simulatorGUI = simulatorGUI;

		if (visualization == null) {
			visualization = new Visualization(simulationRoot);
			this.simulatorGUI.setVisualization(visualization);
		}

		meanSecondsBetweenCustomers.setText(DEFAULT_MEAN_SECONDS_BETWEEN_CUSTOMERS.toString());
		planesPerDay.setText(DEFAULT_PLANES_PER_DAY.toString());
		checkInAmount.setText(DEFAULT_CHECKIN_AMOUNT.toString());
		baggageDropAmount.setText(DEFAULT_BAGGAGE_DROP_AMOUNT.toString());
		securityCheckAmount.setText(DEFAULT_SECURITY_CHECK_AMOUNT.toString());
		passportControlAmount.setText(DEFAULT_PASSPORT_CONTROL_AMOUNT.toString());
		ticketInspectionAmount.setText(DEFAULT_TICKET_INSPECTION_AMOUNT.toString());
		shengenProbability.setText(DEFAULT_SHENGEN_PROBABILITY.toString());
		baggageProbability.setText(DEFAULT_BAGGAGE_DROP_AMOUNT.toString());
	}

	public void handleLaunchSimulation() {
		startButton.setDisable(true);
		meanSecondsBetweenCustomers.setDisable(true);
		planesPerDay.setDisable(true);
		checkInAmount.setDisable(true);
		baggageDropAmount.setDisable(true);
		securityCheckAmount.setDisable(true);
		passportControlAmount.setDisable(true);
		ticketInspectionAmount.setDisable(true);
		shengenProbability.setDisable(true);
		baggageProbability.setDisable(true);

		accellerateButton.setDisable(false);
		decelerateButton.setDisable(false);

		SimulatorSettings settings = new SimulatorSettings(null, null,
				Double.parseDouble(meanSecondsBetweenCustomers.getText()), Integer.parseInt(planesPerDay.getText()),
				Integer.parseInt(checkInAmount.getText()), Integer.parseInt(baggageDropAmount.getText()),
				Integer.parseInt(securityCheckAmount.getText()), Integer.parseInt(passportControlAmount.getText()),
				Integer.parseInt(ticketInspectionAmount.getText()), Double.parseDouble(shengenProbability.getText()),
				Double.parseDouble(baggageProbability.getText()));
		simulatorGUI.getController().launchSimulation(settings);
	}

	public void handleAccelerateSimulation() {
		simulatorGUI.getController().accellerateSimulation();
	}

	public void handleDecelerateSimulation() {
		simulatorGUI.getController().decelerateSimulation();
	}

	public IVisualization getVisualization() {
		return visualization;
	}
}
