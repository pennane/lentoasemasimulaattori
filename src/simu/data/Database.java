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

public class Database {

	public Database() {
		// TODO Auto-generated constructor stub
	}

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet rS = null;
	private Secrets secrets = new Secrets();

	public void writeToDatabase(SimulationData simdata) throws Exception {
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

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public SimulationData[] getAllFromDatabase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			// Class.forName("com.mysql.jdbc.Driver");
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

	public SimulationData getAllFromId(int num) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("org.mariadb.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection(secrets.DatabaseAdress, secrets.username, secrets.password);
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			rS = statement.executeQuery("select * from test where ID=" + num);
			return writeResultSet(rS);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

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

	public ArrayList<Integer> getAllIdFromDatabase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection(secrets.DatabaseAdress, secrets.username, secrets.password);
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			rS = statement.executeQuery("select ID from test");

			return writeIdResultSet(rS);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	private ArrayList<Integer> writeIdResultSet(ResultSet rs) throws SQLException {
		// ResultSet is initially before the first data set
		ArrayList<Integer> lista = new ArrayList();
		while (rs.next()) {
			lista.add(rs.getInt("id"));

		}
		return lista;
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
