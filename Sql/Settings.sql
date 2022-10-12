CREATE TABLE settings (
    ID int NOT NULL AUTO_INCREMENT,
    simulationDurationSeconds Long ,
	simulationDelay Long ,
	meanSecondsBetweenCustomers double, 
	planesPerDay int,
	checkInAmount  int,
	baggageDropAmount  int,
	securityCheckAmount  int,
	passportControlAmount  int,
	ticketInspectionAmount  int,
	shengenProbability double,
	baggageProbability double, 
    RunID int,
    PRIMARY KEY (ID),
    FOREIGN KEY (RunID) REFERENCES test(ID)
);
