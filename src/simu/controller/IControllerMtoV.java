package simu.controller;

import simu.model.FlightType;
import simu.model.IntermediateStats;

public interface IControllerMtoV {
	public void visualizeCurrentTime(long timeStampSeconds );
	
	public void visualizeCustomer();
	
	public void visualizeAirplane(FlightType flightType);
	
	public void visualizeIntermediateStats(IntermediateStats stats);
	
	public void visualizeFinish();
}
