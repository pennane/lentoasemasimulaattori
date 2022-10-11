package simu.view;

import static simu.constants.Constants.DEFAULT_BAGGAGE_DROP_AMOUNT;
import static simu.constants.Constants.DEFAULT_BAGGAGE_DROP_PROBABILITY;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import simu.model.IntermediateStats;
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

	@FXML
	private Label totalInQueue;
	@FXML
	private Label totalServed;
	@FXML
	private Label totalAvgLeadTime;
	@FXML
	private Label checkInInQueue;
	@FXML
	private Label checkInServed;
	@FXML
	private Label checkInAvgLeadTime;
	@FXML
	private Label baggageDropInQueue;
	@FXML
	private Label baggageDropServed;
	@FXML
	private Label baggageDropAvgLeadTime;
	@FXML
	private Label securityCheckInQueue;
	@FXML
	private Label securityCheckServed;
	@FXML
	private Label securityCheckAvgLeadTime;
	@FXML
	private Label passportControlInQueue;
	@FXML
	private Label passportControlServed;
	@FXML
	private Label passportControlAvgLeadTime;
	@FXML
	private Label ticketInspectionInQueue;
	@FXML
	private Label ticketInspectionServed;
	@FXML
	private Label ticketInspectionAvgLeadTime;

	@FXML
	private Label shengePlanesLeft;
	@FXML
	private Label shengeCustomersServed;
	@FXML
	private Label shengeCustomersInAirport;
	@FXML
	private Label internationalPlanesLeft;
	@FXML
	private Label internationalCustomersServed;
	@FXML
	private Label internationalCustomersInAirport;

	private SimulatorGUI simulatorGUI;

	public void initialize(SimulatorGUI simulatorGUI) {
		this.simulatorGUI = simulatorGUI;
		
		if (visualization == null) {
			visualization = new Visualization(simulationRoot);
			this.simulatorGUI.setVisualization(visualization);
		}

		this.simulatorGUI.setSimulationLayoutController(this);
		
		if (simulatorGUI.getController().isSimulationRunning()) {
			showSimulationRunningButtonState();
		} else {
			showSimulationStoppedButtonState();
		}

		meanSecondsBetweenCustomers.setText(DEFAULT_MEAN_SECONDS_BETWEEN_CUSTOMERS.toString());
		planesPerDay.setText(DEFAULT_PLANES_PER_DAY.toString());
		checkInAmount.setText(DEFAULT_CHECKIN_AMOUNT.toString());
		baggageDropAmount.setText(DEFAULT_BAGGAGE_DROP_AMOUNT.toString());
		securityCheckAmount.setText(DEFAULT_SECURITY_CHECK_AMOUNT.toString());
		passportControlAmount.setText(DEFAULT_PASSPORT_CONTROL_AMOUNT.toString());
		ticketInspectionAmount.setText(DEFAULT_TICKET_INSPECTION_AMOUNT.toString());
		shengenProbability.setText(DEFAULT_SHENGEN_PROBABILITY.toString());
		baggageProbability.setText(DEFAULT_BAGGAGE_DROP_PROBABILITY.toString());
	}

	public void handleLaunchSimulation() {
		showSimulationRunningButtonState();

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

	public void visualizeIntermediateStats(IntermediateStats stats) {
		totalInQueue.setText(stats.getTotalInQueue().toString());
		totalServed.setText(stats.getTotalServed().toString());
		totalAvgLeadTime.setText(stats.getTotalAvgLeadTime().toString());

		checkInInQueue.setText(stats.getCheckInInQueue().toString());
		checkInServed.setText(stats.getCheckInServed().toString());
		checkInAvgLeadTime.setText(stats.getCheckInAvgLeadTime().toString());
		baggageDropInQueue.setText(stats.getBaggageDropInQueue().toString());
		baggageDropServed.setText(stats.getBaggageDropServed().toString());
		baggageDropAvgLeadTime.setText(stats.getBaggageDropAvgLeadTime().toString());
		securityCheckInQueue.setText(stats.getSecurityCheckInQueue().toString());
		securityCheckServed.setText(stats.getSecurityCheckServed().toString());
		securityCheckAvgLeadTime.setText(stats.getSecurityCheckAvgLeadTime().toString());
		passportControlInQueue.setText(stats.getPassportControlInQueue().toString());
		passportControlServed.setText(stats.getPassportControlServed().toString());
		passportControlAvgLeadTime.setText(stats.getPassportControlAvgLeadTime().toString());
		ticketInspectionInQueue.setText(stats.getTicketInspectionInQueue().toString());
		ticketInspectionServed.setText(stats.getTicketInspectionServed().toString());
		ticketInspectionAvgLeadTime.setText(stats.getTicketInspectionAvgLeadTime().toString());

		shengePlanesLeft.setText(stats.getShengePlanesLeft().toString());
		shengeCustomersServed.setText(stats.getShengeCustomersServed().toString());
		shengeCustomersInAirport.setText(stats.getShengeCustomersInAirport().toString());
		internationalPlanesLeft.setText(stats.getInternationalPlanesLeft().toString());
		internationalCustomersServed.setText(stats.getInternationalCustomersServed().toString());
		internationalCustomersInAirport.setText(stats.getInternationalCustomersInAirport().toString());
	}

	public void handleFinish() {
		showSimulationStoppedButtonState();
	}
	

	public void showSimulationRunningButtonState() {
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
	}
	
	public void showSimulationStoppedButtonState() {
		startButton.setDisable(false);

		meanSecondsBetweenCustomers.setDisable(false);
		planesPerDay.setDisable(false);
		checkInAmount.setDisable(false);
		baggageDropAmount.setDisable(false);
		securityCheckAmount.setDisable(false);
		passportControlAmount.setDisable(false);
		ticketInspectionAmount.setDisable(false);
		shengenProbability.setDisable(false);
		baggageProbability.setDisable(false);

		accellerateButton.setDisable(true);
		decelerateButton.setDisable(true);
	}
}
