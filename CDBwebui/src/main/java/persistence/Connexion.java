package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Classe de connection au server MySql à la table "computer-database-db".<br/>
 * 
 * Exemple:<br/>
 * Connection conn = new Connexion();<br/>
 * conn.getconn();<br/>
 * // do your things <br/>
 * conn.close();<br/>
 * 
 * @author cyril
 *
 */
public final class Connexion {

	private static Logger logger = LoggerFactory.getLogger(Connexion.class);
	private static volatile Connexion instance = null;

	static HikariConfig hikariConfig;
	Properties props = new Properties();
	private static HikariDataSource dataSource;
	static Connection conn;
	static {
		hikariConfig = new HikariConfig("/datasource.properties");
		dataSource = new HikariDataSource(hikariConfig);
	}

	private Connexion() {

	}

	public final static Connexion getInstance() {
		if (Connexion.instance == null) {
			synchronized (Connexion.class) {
				if (Connexion.instance == null) {
					Connexion.instance = new Connexion();
				}
			}
		}
		return Connexion.instance;
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