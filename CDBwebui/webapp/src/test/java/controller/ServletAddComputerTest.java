package controller;


import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.excilys.configuration.PersistenceConfig;
import fr.excilys.configuration.SecurityConfigInitializer;
import fr.excilys.configuration.SpringConfig;
import fr.excilys.configuration.WebConfig;
import fr.excilys.configuration.WebSecurityConfig;
import fr.excilys.controller.ServletAddComputer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, SpringConfig.class, PersistenceConfig.class,
		SecurityConfigInitializer.class,WebSecurityConfig.class})
public class ServletAddComputerTest extends Mockito{
	
	@Test
	public void testServletAddComputer() throws ServletException, IOException{

		ServletAddComputer servletAddComputer;
	}

}
