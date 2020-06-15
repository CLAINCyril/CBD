package persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configuration.SpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class DAOComputerTest{
	
	@Autowired
	private DAOComputer daoComputer;

	
	@Test
	public void testGetById5Present() {
		assertTrue(daoComputer.getComputer(5).isPresent());
	}
	
	@Test
	public void testGetById5Empty() {
		assertFalse(daoComputer.getComputer(5).isEmpty());
	}
	
	@Test
	public void testGetById50000() {
		assertTrue(daoComputer.getComputer(50000).isEmpty());
	}
	
	
}