package controller;

import simu.model.FlightType;

public interface IControllerMtoV {
	public void visualizeCurrentTime(long timeStampSeconds );
	
	public void visualizeCustomer();
	
	public void visualizeAirplane(FlightType flightType);
	
	public void visualizeFinish();
}
