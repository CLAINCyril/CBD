package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
@PropertySource("classpath:/datasourceTest.properties")
public class ConnexionTest {

	private static Logger logger = LoggerFactory.getLogger(ConnexionTest.class);
	
	Properties props = new Properties();
	static HikariConfig hikariConfig = new HikariConfig();
	private static HikariDataSource dataSource;
	static Connection conn;
	
	@Bean
	public static DataSource hikariDataSource() {
		hikariConfig = new HikariConfig("/datasourceTest.properties");
		dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
	
	public ConnexionTest() {

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
