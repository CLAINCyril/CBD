package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configuration.SpringConfig;
import persistence.DAOCompany;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class DAOCompanyTest {
	@Autowired
	private	DAOCompany daoCompany;

	
	@Test
	public void testGetById5Present() {
		assertTrue(daoCompany.getCompany(5).isPresent());
	}
	
	@Test
	public void testGetById5Empty() {
		assertFalse(daoCompany.getCompany(5).isEmpty());
	}
	
	@Test
	public void testGetById50000() {
		assertTrue(daoCompany.getCompany(50000).isEmpty());
	}
	
	@Test
	public void getAllCompany() {
		assertEquals(daoCompany.getAllCompany().size(), 20);
	}
	
}