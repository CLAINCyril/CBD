package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnexionTest {

	private static Logger logger = LoggerFactory.getLogger(ConnexionTest.class);
	private static volatile ConnexionTest instance = null;
	
	static HikariConfig hikariConfig = new HikariConfig();
	Properties props = new Properties();
	private static HikariDataSource dataSource;
	static Connection conn;
	static {
		hikariConfig = new HikariConfig("/datasourceTest.properties");
		dataSource = new HikariDataSource(hikariConfig);
	}

	private ConnexionTest() {

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

	public static Connection getConn() {
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return conn;
	}
}
