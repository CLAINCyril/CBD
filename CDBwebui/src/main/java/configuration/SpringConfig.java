package configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


@Configuration
@ComponentScan(basePackages = {"com.excilys.cclain.service","com.excilys.cclain.persistence","com.excilys.cclain.controller"})
public class SpringConfig extends AbstractContextLoaderInitializer{
    private static final Logger LOG = LoggerFactory.getLogger(SpringConfig.class);

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		 AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
       rootContext.register(SpringConfig.class);
       return rootContext;
	}
    
    

}
