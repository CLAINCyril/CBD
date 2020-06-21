package fr.excilys.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@ComponentScan(basePackages = { "fr.excilys.model", "fr.excilys.persistence","fr.excilys.mapper" })
@PropertySource("classpath:datasource.properties")
@EnableTransactionManagement
public class PersistenceConfig {

	@Autowired
	Environment environment;

	private final String DRIVER = "driverClassName";
	private final String URL = "jdbcUrl";
	private final String USER = "username2";
	private final String PASSWORD = "password";
	private final String dialect = "hibernate.dialect";

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
		localContainerEntityManagerFactoryBean.setPackagesToScan("fr.excilys.model");
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", environment.getRequiredProperty(dialect));
		localContainerEntityManagerFactoryBean.setJpaProperties(properties);

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

		return localContainerEntityManagerFactoryBean;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
