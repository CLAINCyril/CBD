package controller;


import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.excilys.configuration.PersistenceConfig;
import fr.excilys.configuration.SpringConfig;
import fr.excilys.controller.ServletAddComputer;

@ContextConfiguration(classes = {PersistenceConfig.class, SpringConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)

public class ServletAddComputerTest extends Mockito{
	
	@Test
	public void testServletAddComputer() throws ServletException, IOException{

		ServletAddComputer servletAddComputer;
	}

}
