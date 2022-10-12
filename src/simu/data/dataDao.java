package simu.data;

import java.util.ArrayList;

import simu.model.SimulatorSettings;

public interface dataDao {

	public ArrayList<Integer> getSimulationRunId();

	public SimulationData[] getAllData();

	public SimulatorSettings getSimulationSettings(int i);

	public void SaveSimulationData(SimulationData simdata, SimulatorSettings s);
}
