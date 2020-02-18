package fr.excilys.cclain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.excilys.cclain.modele.Company;
import fr.excilys.cclain.persistence.DAOCompany;

public class DAOCompanyTest {
	
	@Test
	public void testGetCompanyById() {
		Company company1 = new Company.CompanyBuilder().setId(1).setName("Apple Inc.").build();
		Company company2 = DAOCompany.getInstance().getCompany(1).get();
		assertEquals(company1, company2);
	}
	
	@Test
	public void testGetListCompany() {
		List<Company> companyList = new ArrayList<>();
		companyList = DAOCompany.getInstance().getAllCompany();
		companyList.stream().forEach(companyDetails-> assertTrue(companyDetails instanceof Company));
	}
}
