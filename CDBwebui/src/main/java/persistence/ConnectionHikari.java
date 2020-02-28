package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionHikari {

	private static Logger logger = LoggerFactory.getLogger(ConnectionHikari.class);
	private static volatile ConnectionHikari instance = null;
	
	static HikariConfig hikariConfig = new HikariConfig();
	Properties props = new Properties();
	private static HikariDataSource dataSource;
	static Connection conn;
	static {
		hikariConfig = new HikariConfig("/datasource.properties");
		dataSource = new HikariDataSource(hikariConfig);
	}

	private ConnectionHikari() {

	}

	public final static ConnectionHikari getInstance() {
		if (ConnectionHikari.instance == null) {
			synchronized (ConnectionHikari.class) {
				if (ConnectionHikari.instance == null) {
					ConnectionHikari.instance = new ConnectionHikari();
				}
			}
		}
		return ConnectionHikari.instance;
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