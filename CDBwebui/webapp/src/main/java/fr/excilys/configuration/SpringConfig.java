package fr.excilys.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan(basePackages = {
		"fr.excilys.model",
		"fr.excilys.service",
		"fr.excilys.persistence",
		"fr.excilys.controller",
		"fr.excilys.client",
		"fr.excilys.mapper" })

public class SpringConfig implements WebApplicationInitializer {

	private final int initializationPriority = 1;
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext AppContext = new AnnotationConfigWebApplicationContext();
		AppContext.register(WebConfig.class, SpringConfig.class, PersistenceConfig.class,
				SecurityConfigInitializer.class,WebSecurityConfig.class);
		AppContext.setServletContext(servletContext);
		// Create and register the DispatcherServlet
		DispatcherServlet servlet = new DispatcherServlet(AppContext);
		ServletRegistration.Dynamic registration = servletContext.addServlet("dynamicServlet", servlet);
		registration.setLoadOnStartup(initializationPriority);
		registration.addMapping("/");
	}
	

}
