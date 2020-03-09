package fr.excilys.cclain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import modele.Company;
import persistence.ConnexionTest;
import persistence.DAOCompany;

public class DAOCompanyTest {

	@Test
	public void testGetCompanyById() {
		Company company1 = new Company.CompanyBuilder().setId(4).setName("Netronics").build();
		Company company2 = DAOCompany.getInstance().getCompany(4).get();
		assertTrue(company1.equals(company2));
	}

	@Test
	public void testGetListCompany() {
		List<Company> companyList = new ArrayList<>();
		companyList = DAOCompany.getInstance().getAllCompany();
		companyList.stream().forEach(companyDetails -> assertTrue(companyDetails instanceof Company));
	}

	@Test
	public void testDeleteCompany() {
		DAOCompany.getInstance().deleteCompany(1);
		assertFalse(DAOCompany.getInstance().getCompany(1).isPresent());
	}

	@Test
	public void testAddCompany() {
		Company company = new Company.CompanyBuilder().setId(3).setName("RCA").build();
		DAOCompany.getInstance().persisteCompany(company);
		Company company2 = DAOCompany.getInstance().getCompany(3).get();
		assertEquals(company, company2);
	}

}
