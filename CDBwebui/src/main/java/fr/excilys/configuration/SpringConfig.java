package fr.excilys.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan(basePackages = { "fr.excilys.modele", "fr.excilys.service", "fr.excilys.persistence", "fr.excilys.controller", "fr.excilys.client", "fr.excilys.mapper" })
@PropertySource("classpath:datasource.properties")
@EnableTransactionManagement
public class SpringConfig implements WebApplicationInitializer {

	@Autowired
	Environment environment;

	private final int initializationPriority = 1;

	@Value(value = "driverClassName")
	private String DRIVER;
	@Value(value = "jdbcUrl")
	private String URL;
	@Value(value = "username2")
	private String USER;
	@Value(value = "password")
	private String PASSWORD;

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
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
	    JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(emf);
	 
	    return transactionManager;
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean.setPackagesToScan("fr.excilys.modele");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

		return localContainerEntityManagerFactoryBean;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
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
	
	 
	 Properties additionalProperties() {
		    Properties properties = new Properties();
		    properties.setProperty("hibernate.hbm2ddl.auto", "update");
		    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		        
		    return properties;
		}

}
