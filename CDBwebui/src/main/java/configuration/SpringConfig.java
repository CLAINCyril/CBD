package configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


@Configuration
@ComponentScan(basePackages = {"service","persistence","controller","client"})
@PropertySource("classpath:datasource.properties")
public class SpringConfig extends AbstractContextLoaderInitializer{
    private static final Logger LOG = LoggerFactory.getLogger(SpringConfig.class);
    
    @Autowired
    Environment environment;


	private final String DRIVER = "dataSource.driverClassName";
	private final String URL = "dataSource.jdbcUrl";
	private final String USER = "dataSource.user";
	private final String PASSWORD = "dataSource.password";
	

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		 AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
       rootContext.register(SpringConfig.class);
       return rootContext;
	}
	
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

		driverManagerDataSource.setDriverClassName(environment.getRequiredProperty(DRIVER));
		driverManagerDataSource.setUrl(environment.getRequiredProperty(URL));
		driverManagerDataSource.setUsername(environment.getRequiredProperty(USER));
		driverManagerDataSource.setPassword(environment.getRequiredProperty(PASSWORD));
		
		return driverManagerDataSource;
	}	
    
}
