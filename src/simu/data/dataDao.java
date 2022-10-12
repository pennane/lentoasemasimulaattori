package simu.data;

import java.util.ArrayList;

public interface dataDao {

	public ArrayList<Integer> getSimulationRunId();

	public SimulationData[] getAllData();

	public SimulationData getAllFromId(int num);

	public void SaveSimulationData(SimulationData simdata);
}
