package simu.data;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import simu.model.SimulatorSettings;

public class Database {

	public Database() {
		// TODO Auto-generated constructor stub
	}

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet rS = null;
	private Secrets secrets = new Secrets();

	/**
	 * method to save data to sql database. this saves first data collected from
	 * simulation and then settings using id of saved data as foreign key to link
	 * two tables
	 * 
	 * @param simdata is data collected from simulation run
	 * @param s       is simulation run settings given by user
	 * @throws Exception sql exeption
	 */
	public void writeToDatabase(SimulationData simdata, SimulatorSettings s) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection(secrets.DatabaseAdress, secrets.username, secrets.password);

			preparedStatement = connect.prepareStatement("insert into  test values (default, ?,?,?,?,?,?,?,?,?,?,?)");
			// "insert test data to test database");

			// Parameters start with 1

			preparedStatement.setDouble(1, simdata.getCheckinAverage());
			preparedStatement.setDouble(2, simdata.getCheckinmedian());
			preparedStatement.setDouble(3, simdata.getBaggagedropAverage());
			preparedStatement.setDouble(4, simdata.getBaggagedropmedian());
			preparedStatement.setDouble(5, simdata.getSecuritycheckAverage());
			preparedStatement.setDouble(6, simdata.getSecuritycheckmedian());
			preparedStatement.setDouble(7, simdata.getPassportcontrolAverage());
			preparedStatement.setDouble(8, simdata.getPassportcontrolmedian());
			preparedStatement.setDouble(9, simdata.getTicketinspectionAverage());
			preparedStatement.setDouble(10, simdata.getTicketinspectionmedian());
			preparedStatement.setDouble(11, simdata.getCustomerRunTimeAverage());
			preparedStatement.executeUpdate();
			preparedStatement = connect
					.prepareStatement("insert into  settings values (default, ?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setLong(1, s.getSimulationDurationSeconds());
			preparedStatement.setLong(2, s.getSimulationDelay());
			preparedStatement.setDouble(3, s.getMeanSecondsBetweenCustomers());
			preparedStatement.setInt(4, s.getPlanesPerDay());
			preparedStatement.setInt(5, s.getCheckInAmount());
			preparedStatement.setInt(6, s.getBaggageDropAmount());
			preparedStatement.setInt(7, s.getSecurityCheckAmount());
			preparedStatement.setInt(8, s.getPassportControlAmount());
			preparedStatement.setInt(9, s.getTicketInspectionAmount());
			preparedStatement.setDouble(10, s.getShengenProbability());
			preparedStatement.setDouble(11, s.getBaggageProbability());
			preparedStatement.setInt(12, getLatestId()); // use getLatestId() to link settings to simulation data using
															// id
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	/**
	 * method to get all simulation data from database
	 * 
	 * @return returns array that includes all data from database
	 * @throws Exception sql exception
	 */
	public SimulationData[] getAllFromDatabase() throws Exception {
		try {
			// This will load the Mariadb driver, each DB has its own driver
			Class.forName("org.mariadb.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection(secrets.DatabaseAdress, secrets.username, secrets.password);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			rS = statement.executeQuery("select * from test");
			ArrayList<SimulationData> dataArray = new ArrayList<>();
			while (rS.next()) {
				dataArray.add(new SimulationData(rS.getInt("ID"), rS.getDouble("PassportA"),
						rS.getDouble("passportMed"), rS.getDouble("baggageA"), rS.getDouble("baggageMed"),
						rS.getDouble("securityA"), rS.getDouble("securityMed"), rS.getDouble("TicketA"),
						rS.getDouble("TicketMed"), rS.getDouble("checkinA"), rS.getDouble("checkinMed"),
						rS.getDouble("AsiakasAv"), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
			}
			SimulationData[] returnArray = new SimulationData[dataArray.size()];
			for (int i = 0; i < dataArray.size(); i++) {
				returnArray[i] = dataArray.get(i);
			}
			return returnArray;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	/**
	 * method to get settings used in simulation with id
	 * 
	 * @param num is the id simulation run
	 * @return settings corresponding to id
	 * @throws Exception sql exception
	 */
	public SimulatorSettings getSettingsFromRunId(int num) throws Exception {

		try {
			// This will load the Mariadb driver, each DB has its own driver
			Class.forName("org.mariadb.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection(secrets.DatabaseAdress, secrets.username, secrets.password);
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			rS = statement.executeQuery("select * from settings where RunID=" + num);
			return writeIdResultSet(rS);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	/**
	 * method used to get latest id number from database
	 * 
	 * @return Latest id in database
	 * @throws Exception sql Exception
	 */
	public int getLatestId() throws Exception {

		try {
			// This will load the Mariadb driver, each DB has its own driver
			Class.forName("org.mariadb.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection(secrets.DatabaseAdress, secrets.username, secrets.password);
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			rS = statement.executeQuery("SELECT * FROM test WHERE ID = (SELECT MAX(ID) FROM test)");
			return writeId(rS);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	/**
	 * legacy
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private SimulationData writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		SimulationData yeet = new SimulationData();
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			yeet.setCheckinAverage(resultSet.getDouble("checkinA"));
			yeet.setCheckinmedian(resultSet.getDouble("checkinMed"));
			yeet.setBaggagedropAverage(resultSet.getDouble("baggageA"));
			yeet.setBaggagedropmedian(resultSet.getDouble("baggageMed"));
			yeet.setSecuritycheckAverage(resultSet.getDouble("securityA"));
			yeet.setSecuritycheckmedian(resultSet.getDouble("securityMed"));
			yeet.setPassportcontrolAverage(resultSet.getDouble("PassportA"));
			yeet.setPassportcontrolmedian(resultSet.getDouble("passportMed"));
			yeet.setTicketinspectionAverage(resultSet.getDouble("TicketA"));
			yeet.setTicketinspectionmedian(resultSet.getDouble("TicketMed"));
			yeet.setCustomerRunTimeAverage(resultSet.getDouble("AsiakasAv"));

		}
		return yeet;
	}

	/**
	 * method to process resultset got from database
	 * 
	 * @param rs resultset containing all settings got from database
	 * @return
	 * @throws SQLException
	 */
	private SimulatorSettings writeIdResultSet(ResultSet rs) throws SQLException {
		// ResultSet is initially before the first data set
		SimulatorSettings settings = new SimulatorSettings(rs.getLong("simulationDurationSeconds"),
				rs.getLong("simulationDelay"), rs.getDouble("meanSecondsBetweenCustomers"), rs.getInt("planesPerDay"),
				rs.getInt("checkInAmount"), rs.getInt("baggageDropAmount"), rs.getInt("securityCheckAmount"),
				rs.getInt("passportControlAmount"), rs.getInt("ticketInspectionAmount"),
				rs.getDouble("shengenProbability"), rs.getDouble("baggageProbability"));

		return settings;
	}

	private int writeId(ResultSet rs) throws SQLException {
		// ResultSet is initially before the first data set
		int yeet = 1;
		while (rs.next()) {
			yeet = rs.getInt("ID");
		}
		return yeet;
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (rS != null) {
				rS.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
