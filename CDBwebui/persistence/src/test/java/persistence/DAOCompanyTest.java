package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.configuration.PersistenceConfig;
import fr.excilys.model.Company;
import fr.excilys.persistence.DAOCompany;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@Transactional
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
	public void getAllCompany() {
		assertEquals(daoCompany.getAllCompany().size(), 20);
	}

	@Test
	public void persistCompany() {
		Company company = new Company();
		company.setName("toto");
		daoCompany.persisteCompany(company);
	}
	
	@Test
	public void updateCompany() {
		Company company = new Company();
		company.setName("toto");
		company.setId(1);
		daoCompany.updateCompany(company);
	}
}