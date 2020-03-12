package configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import persistence.Connexion;
import persistence.DAOCompany;
import persistence.DAOComputer;
import service.ServiceCompany;
import service.ServiceComputer;


@Configuration
@ComponentScan(basePackages = {"com.excilys.cclain.service","com.excilys.cclain.persistence","com.excilys.cclain.controller,com.excilys.cclain.client,com.excilys.cclain.mapper"})
public class SpringConfig extends AbstractContextLoaderInitializer{
    private static final Logger LOG = LoggerFactory.getLogger(SpringConfig.class);

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		 AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
       rootContext.register(SpringConfig.class);
       return rootContext;
	}
    
}