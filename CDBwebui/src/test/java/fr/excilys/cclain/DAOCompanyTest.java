package fr.excilys.cclain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modele.Company;
import persistence.ConnexionTest;
import persistence.DAOCompany;

public class DAOCompanyTest {

	@Test
	public void testGetCompanyById() {
		Connection conn = ConnexionTest.getInstance().getConn();
		Company company1 = new Company.CompanyBuilder().setId(4).setName("Netronics").build();
		Company company2 = DAOCompany.getInstance(conn).getCompany(4).get();
		assertTrue(company1.equals(company2));
	}

	@Test
	public void testGetListCompany() {
		Connection conn = ConnexionTest.getInstance().getConn();
		List<Company> companyList = new ArrayList<>();
		companyList = DAOCompany.getInstance(conn).getAllCompany();
		companyList.stream().forEach(companyDetails -> assertTrue(companyDetails instanceof Company));
	}

	@Test
	public void testDeleteCompany() {
		Connection conn = ConnexionTest.getInstance().getConn();
		DAOCompany.getInstance(conn).deleteCompany(1);
		assertFalse(DAOCompany.getInstance(conn).getCompany(1).isPresent());
	}

	@Test
	public void testAddCompany() {
		Connection conn = ConnexionTest.getInstance().getConn();
		Company company = new Company.CompanyBuilder().setId(3).setName("RCA").build();
		DAOCompany.getInstance(conn).persisteCompany(company);
		Company company2 = DAOCompany.getInstance(conn).getCompany(3).get();
		System.out.println(company+" "+ company2);
		assertEquals(company, company2);
	}

}
