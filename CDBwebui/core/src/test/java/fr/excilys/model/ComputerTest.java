package fr.excilys.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.Test;

public class ComputerTest {
	
	Company company = new Company.CompanyBuilder()
			.setId(1)
			.setName("totoCompany")
			.build();
	Company companyForSet = new Company.CompanyBuilder()
			.setId(2)
			.setName("tataCompany")
			.build();
	
	LocalDate introduced = LocalDate.of(2020,04,05);
	LocalDate discontinued = LocalDate.of(2021,04,05);
	
	Computer computer = new Computer.ComputerBuilder()
			.setCompany(company)
			.setId(1)
			.setDiscontinued(discontinued)
			.setIntroduced(introduced)
			.setName("toto")
			.build();

	@Test
	public void assertBuilderMakeComputer(){
		Computer computer = new Computer.ComputerBuilder().build();
		assertEquals(Computer.class, computer.getClass());
	}
	
	@Test
	public void assertBuilderMakeComputerWithParameterId(){
		Computer computer = new Computer.ComputerBuilder()
				.setId(1)
				.build();
		assertEquals(1, computer.getId());
	}
	
	@Test
	public void assertBuilderMakeComputerWithParameterName(){
		Computer computer = new Computer.ComputerBuilder()
				.setName("toto")
				.build();
		assertEquals("toto", computer.getName());
	}
	
	@Test
	public void assertBuilderMakeComputerWithParameterIntroduced(){
		Computer computer = new Computer.ComputerBuilder()
				.setIntroduced(introduced)
				.build();
		assertEquals(introduced, computer.getIntroduced());
	}
	
	@Test
	public void assertBuilderMakeComputerWithParameterDiscontinued(){
		Computer computer = new Computer.ComputerBuilder()
				.setDiscontinued(discontinued)
				.build();
		assertEquals(discontinued, computer.getDiscontinued());
	}
	
	@Test
	public void assertBuilderMakeComputerWithParameterCompany(){

		Computer computer = new Computer.ComputerBuilder()
				.setCompany(company)
				.build();
		assertEquals(company, computer.getCompany());
	}
	
	@Test
	public void assertGetIntroducedGiveIntroduced() {
		assertEquals(introduced, computer.getIntroduced());
	}
	
	@Test
	public void assertGetDiscontinuedGiveDiscontinued() {
		assertEquals(discontinued, computer.getDiscontinued());
	}
	
	@Test
	public void assertGetNameGiveName() {
		assertEquals("toto", computer.getName());
	}
	
	@Test
	public void assertGetIdGiveID() {
		assertEquals(1, computer.getId());
	}
	
	@Test
	public void assertSetCompanyUpdateComputerCompany() {
		computer.setCompany(companyForSet);	
	
		assertEquals(companyForSet ,computer.getCompany() );
	}
	
	@Test
	public void assertSetIdUpdateComputerId() {
		computer.setId(2);
	
		assertEquals(2, computer.getId());
	}
	
	@Test
	public void assertSetNameUpdateComputerName() {
		computer.setName("tata");
	
		assertEquals("tata", computer.getName());
	}
	
	@Test
	public void assertSetIntroducedUpdateComputerIntroduced() {
		computer.setIntroduced(introduced.plusDays(2));
		
		assertEquals(introduced.plusDays(2), computer.getIntroduced());
	}
	
	@Test
	public void assertSetIntroducedUpdateIntroduced() {
		computer.setDiscontinued(discontinued.plusDays(3));

		assertEquals(discontinued.plusDays(3), computer.getDiscontinued());
	}

	@Test
	public void assertNewComputerMakeComputer() {
		Computer computer = new Computer();
		assertEquals(Computer.class, computer.getClass());
	}
}
