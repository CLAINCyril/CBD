package fr.excilys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.configuration.PersistenceConfig;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@Transactional
public class DAOComputerTest {

	@Autowired
	private DAOComputer daoComputer;
	
	Company company1 = new Company.CompanyBuilder()
			.setId(1)
			.setName("Apple Inc.")
			.build();
	
	Computer computer21 = new Computer.ComputerBuilder()
			.setCompany(company1)
			.setName("Apple III")
			.setDiscontinued(LocalDate.of(1984, 04, 01))
			.setIntroduced(LocalDate.of(1980, 05, 01))
			.build();
	
	Computer computer12 = new Computer.ComputerBuilder()
			.setCompany(company1)
			.setName("Apple III")
			.setId(12)
			.setDiscontinued(LocalDate.of(1984, 04, 01))
			.setIntroduced(LocalDate.of(1980, 05, 01))
			.build();
	
	@Mock
	Computer computer;

	@Test
	public void testGetById5Present() {
		assertTrue(daoComputer.getComputer(5).isPresent());
	}

	@Test
	public void testGetById5Empty() {
		assertFalse(daoComputer.getComputer(5).isEmpty());
	}

	@Test
	public void testGetByIdEmpty() {
		assertTrue(daoComputer.getComputer(40).isEmpty());
	}
	
	@Test
	public void testGetByID12() {


		
		assertEquals(computer12, daoComputer.getComputer(12).get());;
	}

	@Test
	public void testGetByID12False() {

		Computer falseComputer12 = new Computer.ComputerBuilder()
				.setCompany(company1)
				.setName("Apple III")
				.setId(13)
				.setDiscontinued(LocalDate.of(1984, 04, 01))
				.setIntroduced(LocalDate.of(1980, 05, 01))
				.build();
		
		assertNotEquals(falseComputer12, daoComputer.getComputer(12).get());;
	}


	@Test
	public void testGetPage20() {
		assertEquals(20, daoComputer.getPageComputer(0, 20).size());
	}
	
	@Test
	public void testGetallComputerTrue() {
		assertEquals(20, daoComputer.countComputer());
	}
	@Test
	public void testGetallComputerFalse() {
		assertNotEquals(21, daoComputer.countComputer());
	}
	
	@Test
	public void assertTruePageContainApple() {
		List<Computer> computers = daoComputer.getPageComputerByName("Apple", 0, 20,"name");
		assertTrue(computers.stream()
				.map(computer -> computer.getName())
				.allMatch(s -> s.contains("Apple")));
	}

	@Test
	public void assertTruePageOrderByComputer() {
		List<Computer> computers = daoComputer.getPageComputerOrder(0, 20, "name");
		List<String> computerOrderByName = computers.stream()
						.map(computer -> computer.getName())
						.sorted()
						.collect(Collectors.toList());
		
		assertEquals(computerOrderByName, computers.stream()
				.map(computer -> computer.getName())
				.collect(Collectors.toList()));
	}
	
	@Test
	public void assertPersistComputer() {

		daoComputer.persisteComputer(computer21);
		computer21.setId(21);
		
		assertEquals(computer21,daoComputer.getComputer(21).get());
	}
	
	@Test
	public void assertPersistComputerAddComputerInBase() {

		daoComputer.persisteComputer(computer21);		
		assertEquals(21,daoComputer.countComputer());
	}
	
	@Test
	public void assertDeleteComputer12Work() {
		daoComputer.deleteComputer(12);
		
		assertTrue(daoComputer.getComputer(21).isEmpty());
	}
	
	@Test
	public void assertDeleteComputer12ReduceNbComputer() {
		daoComputer.deleteComputer(12);
		
		assertEquals(19, daoComputer.countComputer());
	}
	
	@Test
	public void assertTrueUpdateNameComputer12() {
		computer12.setName("toto");
		daoComputer.updateComputer(computer12);
		Computer computer = daoComputer.getComputer(12).get();
		
		assertTrue(computer.getName().equals("toto"));
	}
	
	@Test
	public void assertTrueUpdateIntroducedComputer12() {
		computer12.setIntroduced(LocalDate.of(1980, 06, 01));
		daoComputer.updateComputer(computer12);
		Computer computer = daoComputer.getComputer(12).get();
		
		assertTrue(computer.getIntroduced().equals(LocalDate.of(1980, 06, 01)));
	}
	
	@Test
	public void assertTrueUpdateDiscontinuedComputer12() {
		computer12.setDiscontinued(LocalDate.of(1984, 06, 01));
		daoComputer.updateComputer(computer12);
		Computer computer = daoComputer.getComputer(12).get();
		
		assertTrue(computer.getDiscontinued().equals(LocalDate.of(1984, 06, 01)));
	}
	
	@Test
	public void assertDeleteComputerListDeleteGoodComputer() {
		List<String> listIdComputer = new ArrayList<String>();
		listIdComputer.add("1");
		daoComputer.deleteComputerListe(listIdComputer);
		
		List<Computer> computers = daoComputer.getPageComputer(0, 20);
		
		assertFalse(computers.stream().map(computer -> computer.getId())
				.collect(Collectors.toList()).contains(1));
	}
	
	@Test
	public void assertDeleteComputerListDeleteGoodNumberOfComputer() {
		List<String> listIdComputer = new ArrayList<String>();
		listIdComputer.add("1");
		daoComputer.deleteComputerListe(listIdComputer);
		
		assertEquals(19,  daoComputer.getPageComputer(0, 20).size());
		
	}
	
	@Test
	public void assertResultEqual14DeleteComputerWhereCompany() {
		daoComputer.deleteComputerWhereCompany(1);
		assertEquals(14,  daoComputer.getPageComputer(0, 20).size());
		
	}
}