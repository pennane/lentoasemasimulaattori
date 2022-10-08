package simu.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Database {

	public Database() {
		// TODO Auto-generated constructor stub
	}

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private HashMap<String, Long> tiedot = Statistics.getInstance().getTulokset();

	public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root&password=root");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from test");
			writeResultSet(resultSet);

			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect.prepareStatement("insert into  test values (default, ?)");
			// "insert test data to test database");
			// Parameters start with 1
			preparedStatement.setString(1, "yeet");
			preparedStatement.executeUpdate();

			preparedStatement = connect.prepareStatement("SELECT yeet from test");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);

			// resultSet = statement.executeQuery("select * test");
			// writeMetaData(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public void writeToDatabase(SimulationData simdata) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root&password=root");

			preparedStatement = connect.prepareStatement("insert into  test values (default, ?,?,?,?,?,?,?,?,?,?,?)");
			// "insert test data to test database");

			// Parameters start with 1
			int yeet = 1;
			for (String i : tiedot.keySet()) {
				System.out.println("key: " + i + " value: " + tiedot.get(i));
				preparedStatement.setDouble(yeet, tiedot.get(i));
				yeet++;
			}
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public void getAllFromDatabase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root&password=root");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from test");
			writeResultSet(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String data = resultSet.getString("yeet");
			System.out.println("tiedot: " + data);

		}
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
