package fr.excilys.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompanyTest {
	Company company = new Company.CompanyBuilder()
			.setId(1)
			.setName("totoCompany")
			.build();
	
	@Test
	public void assertNewCompanyMakeCompany() {
		Company company = new Company();
		
		assertEquals(Company.class, company.getClass());
	}
	
	@Test
	public void assertNewCompanyByIdSetId() {
		Company company = new Company(2);

		assertEquals(2, company.getId());
	}
	
	
	@Test
	public void assertNewCompanyByIdMakeCompany() {
		Company company = new Company(2);

		assertEquals(Company.class, company.getClass());
	}
	
	@Test
	public void assertSetIdChangeCompanyId() {
		company.setId(34);
		
		assertEquals(34, company.getId());
	}
	
	@Test
	public void assertSetnameChangeCompanyName() {
		company.setName("someName");
		
		assertEquals("someName", company.getName());
	}
	
	@Test
	public void assertBuilderMakeACompany() {
		
		assertEquals(company.getClass(), Company.class);
	}
}
