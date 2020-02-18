package fr.excilys.cclain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.excilys.cclain.modele.Computer;
import fr.excilys.cclain.persistence.DAOComputer;

public class DAOComputerTest {

	@Test
	public void testGetComputerById() {
		Computer computer1 = new Computer.ComputerBuilder().setId(1).build();
		Computer company2 = DAOComputer.getInstance().getComputer(1).get();
		assertEquals(computer1, company2);
	}
	
	@Test
	public void testGetListCompany() {
		List<Computer> companyList = new ArrayList<>();
		companyList = DAOComputer.getInstance().getAllComputer();
		companyList.stream().forEach(companyDetails-> assertTrue(companyDetails instanceof Computer));
	}
}
