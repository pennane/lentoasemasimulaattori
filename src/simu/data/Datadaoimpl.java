package simu.data;

public class Datadaoimpl implements dataDao {
	Database database = new Database();

	@Override
	public SimulationData getAllData() {
		try {
			database.getAllFromDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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

}
