package fr.excilys.cclain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Company;
import persistence.DAOCompany;

public class DAOCompanyTest {
	DAOCompany daoCompany;
	
//	@Before
//	public void init() {
//		daoCompany = new DAOCompany();
//	}
//
//	@Test
//	public void testGetCompanyById() {
//		Company company1 = new Company.CompanyBuilder().setId(4).setName("Netronics").build();
//		Company company2 = daoCompany.getCompany(4).get();
//		assertTrue(company1.equals(company2));
//	}
//
//	@Test
//	public void testGetListCompany() {
//		List<Company> companyList = new ArrayList<>();
//		companyList = daoCompany.getAllCompany();
//		companyList.stream().forEach(companyDetails -> assertTrue(companyDetails instanceof Company));
//	}
//
//	@Test
//	public void testDeleteCompany() {
//		daoCompany.deleteCompany(1);
//		assertFalse(daoCompany.getCompany(1).isPresent());
//	}
//
//	@Test
//	public void testAddCompany() {
//		Company company = new Company.CompanyBuilder().setId(3).setName("RCA").build();
//		daoCompany.persisteCompany(company);
//		Company company2 = daoCompany.getCompany(3).get();
//		assertEquals(company, company2);
//	}

}
