package simu.view;

import simu.controller.IControllerVtoM;

public interface ISimulatorGUI {
	public IVisualization getVisualization();
	public void setVisualization(IVisualization visualization);
	public IControllerVtoM getController();
}
