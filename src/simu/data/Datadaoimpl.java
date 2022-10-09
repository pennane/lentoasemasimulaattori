package simu.data;

import java.util.ArrayList;

public class Datadaoimpl implements dataDao {
	Database database = new Database();

	@Override
	public SimulationData getAllData() {
		SimulationData data = null;
		try {
			data = database.getAllFromDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void SaveSimulationData(SimulationData simdata) {
		try {
			database.writeToDatabase(simdata);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Integer> getSimulationRunId() {

		return null;
	}

}
