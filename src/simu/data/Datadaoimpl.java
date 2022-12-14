package simu.data;

import java.util.ArrayList;

import simu.model.SimulatorSettings;

/**
 * file contains implementation on dataDao
 * 
 * @author henri vuento
 *
 */
public class Datadaoimpl implements dataDao {
	Database database = new Database();

	@Override
	public SimulationData[] getAllData() {
		SimulationData[] data = null;
		try {
			data = database.getAllFromDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void SaveSimulationData(SimulationData simdata, SimulatorSettings s) {
		try {
			database.writeToDatabase(simdata, s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Integer> getSimulationRunId() {

		return null;
	}

	@Override
	public SimulatorSettings getSimulationSettings(int i) {

		try {
			return database.getSettingsFromRunId(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}