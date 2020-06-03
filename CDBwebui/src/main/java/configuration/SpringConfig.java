package configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import javax.servlet.ServletRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan(basePackages = {"service", "persistence", "controller", "client", "mapper"})
@PropertySource("classpath:datasource.properties")
public class SpringConfig implements WebApplicationInitializer{
    
	@Autowired
	Environment environment;

	private final int initializationPriority = 1;
	private final String DRIVER = "driverClassName";
	private final String URL = "jdbcUrl";
	private final String USER = "username2";
	private final String PASSWORD = "password";

	
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

		driverManagerDataSource.setDriverClassName(environment.getRequiredProperty(DRIVER));
		driverManagerDataSource.setUrl(environment.getRequiredProperty(URL));
		driverManagerDataSource.setUsername(environment.getRequiredProperty(USER));
		driverManagerDataSource.setPassword(environment.getRequiredProperty(PASSWORD));
		
		return driverManagerDataSource;
	}
	
	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
		}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext AppContext = new AnnotationConfigWebApplicationContext();
		AppContext.register(WebConfig.class, SpringConfig.class);
		AppContext.setServletContext(servletContext);
        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(AppContext);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dynamicServlet", servlet);
        registration.setLoadOnStartup(initializationPriority);
        registration.addMapping("/");
	}
 
}
