package simu.data;

import java.util.HashMap;

public interface dataDao {

	public SimulationData getAllData();

	public void SaveSimulationData(SimulationData simdata);
}
