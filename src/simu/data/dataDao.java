package simu.data;

import java.util.ArrayList;

import simu.model.SimulatorSettings;

public interface dataDao {
	/**
	 * get list of id's in database
	 * 
	 * @return arraylist containing integers that are id's from database
	 */
	public ArrayList<Integer> getSimulationRunId();

	/**
	 * get all data collected to database in array
	 * 
	 * @return array containing all data from database
	 */
	public SimulationData[] getAllData();

	/**
	 * get settings of specific id from database
	 * 
	 * @param i is id of simulation run that settings want to be retrieved
	 * @return returns SimulatorSettings object containing simulation settings of
	 *         run
	 */
	public SimulatorSettings getSimulationSettings(int i);

	/**
	 * save data collected from simulation and it's settings
	 * 
	 * @param simdata is the data collected
	 * @param s       is the settings of simulation given by user
	 */
	public void SaveSimulationData(SimulationData simdata, SimulatorSettings s);
}
