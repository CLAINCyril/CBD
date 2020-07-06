package fr.excilys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
	public void assertPersistCompanyAddACompany() {
		Company company = new Company();
		company.setName("toto");
		daoCompany.persisteCompany(company);
		assertEquals(daoCompany.getCompany(21).get().getName(),
				company.getName());
	}
	
	@Test
	public void assertUpdateCompanyUpdateByID() {
		Company company = new Company();
		company.setName("toto");
		company.setId(1);
		daoCompany.updateCompany(company);
		assertEquals(daoCompany.getCompany(1).get(),
				company);
	}
	
	@Test
	public void assertUpdateCompanyDoesntAddCompany() {
		Company company = new Company();
		company.setName("toto");
		company.setId(1);
		daoCompany.updateCompany(company);
		assertTrue(daoCompany.getCompany(21).isEmpty());
	}
	
	@Test
	public void assertDeteleDoesntDeleteBadCompany() {
		daoCompany.deleteCompany(20);
		List<Company> companies = daoCompany.getAllCompany();
		
		assertTrue(companies.stream()
		.map(company -> company.getId())
		.allMatch(id -> id != 20));
	}
	
	@Test
	public void assertGetPageGiveGoodNumberOfCompany() {
		assertEquals(daoCompany.getPageCompany(0, 20).size(),20);
	}
	
	@Test
	public void assertGetPageStartATGoodItem() {
		assertTrue(
		daoCompany.getPageCompany(10,20).stream()
		.map(company -> company.getId())
		.allMatch(id -> ((id >= 10) && (id <=20))));
	}
}