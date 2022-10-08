package simu.view;

public interface IVisualization {	
	public void setSimulationTimeSeconds(long timeStampSeconds);

	public void newCustomer();
	public void shengeDepart();
	public void internationalDepart();
	
	public void finish();

}
