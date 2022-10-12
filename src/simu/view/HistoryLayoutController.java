package simu.view;

import java.util.LinkedList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import simu.data.Datadaoimpl;
import simu.data.SimulationData;

public class HistoryLayoutController {

	private Datadaoimpl dao;
	private SimulatorGUI simulatorGUI;
	private final String historyText = "Simulation id: ";
	SimulationData selectedData;
	LinkedList<SimulationData> allData;
	@FXML
	private ListView<String> historyListView;
	@FXML
	private ListView<String> historyDataListView;
	

	SimulationData testData, testData2;
	
	//Initialize HistoryLayout
	public void initialize(SimulatorGUI simulatorGUI) {
		this.simulatorGUI = simulatorGUI;
		dao = new Datadaoimpl();
		allData = new LinkedList<>();
		
		historyDataListView.setFocusTraversable(false);
		
		refreshData();
	}
	//Generates a String array for the ListView that displays the previous simulations
	private String[] generateHistoryList() {
		String[] dataString = new String[allData.size()];
		for(int i = 0; i < dataString.length; i++) {
			dataString[i] = historyText + allData.get(i).getId();
		}
 		return dataString;
	}
	//Generates a String array for the ListView that displays the selected simulations data
 	private String[] generateDataList(SimulationData data) {
		return new String[]{
				"Passport control average: " + data.getPassportcontrolAverage(),
				"Passport control median: " + data.getPassportcontrolmedian(),
				"Bag drop average: " + data.getBaggagedropAverage(),
				"Bag drop median: " + data.getBaggagedropmedian(),
				"Security check average: " + data.getSecuritycheckAverage(),
				"Security check median: " + data.getSecuritycheckmedian(),
				"Ticket inspection average: " + data.getTicketinspectionAverage(),
				"Ticket inspection median: " + data.getTicketinspectionmedian(),
				"Check in average: " + data.getCheckinAverage(),
				"Check in median: " + data.getCheckinmedian(),
				"Customer run time average: " + data.getCustomerRunTimeAverage(),
				"Arived customer amount: " + data.getArivedCustomerAmount(),
				"Serviced customer amount: " + data.getServicedCustomerAmount(),
				"Passport control customer amount: " + data.getPassportcontrolCustomerAmount(),
				"Check in customer amount: " + data.getCheckinCustomerAmount(),
				"Bag drop customer amount: " + data.getBaggagedropCustomerAmount(),
				"Ticket inspection customer amount: " + data.getTicketinspectionCustomerAmount(),
				"Security check customer amount: " + data.getSecuritycheckCustomerAmount(),
				"Simulation total time: " + data.getSimulationTotaltime(),
				"Check in busy time: " + data.getCheckinBusyTime(),
				"Bag drop busy time: " + data.getBaggagedropBusyTime(),
				"Security check busy time: " + data.getSecuritycheckBusyTime(),
				"Passport control busy time: " + data.getPassportcontrolBusyTime(),
				"Ticket inspection busy time: " + data.getTicketinspectionBusyTime()
		};
	}
 	//Gets the data from dao and adds listeners to ListView items
	public void refreshData() {
		allData.clear();
		SimulationData[] daoData = dao.getAllData();
		for(SimulationData d : daoData) {
			allData.add(d);
		}
		historyListView.getItems().clear();
		historyDataListView.getItems().clear();
		historyListView.getItems().addAll(generateHistoryList());
		historyListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				try {
					String selectedString = historyListView.getSelectionModel().getSelectedItem();
					if(selectedString != null) {
						int selectedId = Integer.parseInt(selectedString.replace(historyText, ""));
						SimulationData data = (SimulationData) allData.stream().filter(d -> selectedId == d.getId()).findAny().orElse(null);
						historyDataListView.getItems().clear();
						historyDataListView.getItems().addAll(generateDataList(data));	
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
