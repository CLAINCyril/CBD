package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

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
@Configuration
public final class Connexion {

	private static Logger logger = LoggerFactory.getLogger(Connexion.class);

	static HikariConfig hikariConfig;
	Properties props = new Properties();
	static Connection conn;

	@Bean
	public static DataSource hikariDataSource() {
		hikariConfig = new HikariConfig("/datasource.properties");
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}

	public Connexion() {

	}

	public static Connection getConn() {
		try {
			conn = hikariDataSource().getConnection();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return conn;
	}
}