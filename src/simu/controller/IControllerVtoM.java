package simu.controller;

import simu.model.SimulatorSettings;

public interface IControllerVtoM {
	public void launchSimulation(SimulatorSettings settings);
	
	public void accellerateSimulation();
	public void decelerateSimulation();
	
}
