package simu.data;

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
	private ResultSet resultSet = null;

	public void writeToDatabase(SimulationData simdata) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root&password=root");

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

	public SimulationData getAllFromDatabase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root&password=root");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from test where ID=1");
			return writeResultSet(resultSet);
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
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root&password=root");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select ID from test");

			return writeIdResultSet(resultSet);
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
			if (resultSet != null) {
				resultSet.close();
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
