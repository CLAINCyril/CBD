package fr.excilys.cclain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.excilys.cclain.modele.Company;
import fr.excilys.cclain.persistence.DAOCompany;

public class DAOCompanyTest {

	@Test
	public void testGetCompanyById() {
		Company company1 = new Company.CompanyBuilder().setId(1).setName("Apple Inc.").build();
		Company company2 = DAOCompany.getInstance().getCompanyTest(1).get();
		assertEquals(company1, company2);
	}

	@Test
	public void testGetListCompany() throws ClassNotFoundException {
		List<Company> companyList = new ArrayList<>();
		companyList = DAOCompany.getInstance().getAllCompanyTest();
		companyList.stream().forEach(companyDetails -> assertTrue(companyDetails instanceof Company));
	}

	@Test
	public void testDeleteCompany() {
		DAOCompany.getInstance().deleteCompanyTest(1);
		assertNull(DAOCompany.getInstance().getCompanyTest(1).get());

	}

	@Test
	public void testAddCompany() {
		Company company = new Company.CompanyBuilder().setId(1).
				setName("Apple Inc.").build();
		DAOCompany.getInstance().persisteCompany(company);
		Company company2 = DAOCompany.getInstance().getCompanyTest(1).get();
		assertEquals(company, company2);
	}
}
