package view;

public interface IVisualization {

	public void drawBase();
	
	public void setSimulationTime(long timeStampSeconds);

	public void newCustomer();
	public void shengeDepart();
	public void internationalDepart();
	
	public void finish();

}
