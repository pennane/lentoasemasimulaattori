package simu.model;

public interface IOmaMoottori {

	// Controller uses this interface

	public void setSimulointiaika(long aika);

	public void setSettingsViive(long aika);

	public long getSettingsViive();

	public boolean isSimulationRunning();

	public SimulatorSettings getSimulatorSettings();

}