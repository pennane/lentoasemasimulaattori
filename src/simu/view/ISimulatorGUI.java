package simu.view;

import simu.controller.IControllerVtoM;

public interface ISimulatorGUI {
	public IVisualization getVisualization();
	public void setVisualization(IVisualization visualization);
	public void setSimulationLayoutController(SimulationLayoutController controller);
	public SimulationLayoutController getSimulationLayoutController();
	public IControllerVtoM getController();
}
