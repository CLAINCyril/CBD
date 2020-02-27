package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionTest {

	private String user = "";
	private String mdp = "sa";
	private String url = "jdbc:h2:mem:computer-database-db;INIT=RUNSCRIPT FROM 'src/test/resources/schema-creation.sql'";
	private static Connection conn;

	private static volatile ConnexionTest instance = null;

	public ConnexionTest() {
	}

	public final static ConnexionTest getInstance() {
		if (ConnexionTest.instance == null) {
			synchronized (ConnexionTest.class) {
				if (ConnexionTest.instance == null) {
					ConnexionTest.instance = new ConnexionTest();
				}
			}
		}
		return ConnexionTest.instance;
	}

	/**
	 * recupère la connection.
	 * 
	 * @return Connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConn() {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(url, mdp, user);

		} catch (SQLException e) {
			e.getMessage();
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
		return conn;
	}

}
