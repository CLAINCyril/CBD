package fr.excilys.cclain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import fr.excilys.cclain.modele.Company;
import fr.excilys.cclain.modele.Computer;
import fr.excilys.cclain.persistence.Connexion;
import fr.excilys.cclain.persistence.ConnexionTest;
import fr.excilys.cclain.persistence.DAOComputer;

public class DAOComputerTest {

	@Test
	public void testGetComputerById() throws ClassNotFoundException, SQLException {
		Connection conn = ConnexionTest.getInstance().getConn();
		Computer computer1 = new Computer.ComputerBuilder().setId(2).build();
		Computer computer2 = DAOComputer.getInstance(conn).getComputer(2).get();
		assertEquals(computer1, computer2);
	}

	@Test
	public void testAddComputer() throws ClassNotFoundException, SQLException {
		Connection conn = ConnexionTest.getInstance().getConn();
		Computer computer1 = new Computer.ComputerBuilder()
				.setCompany(new Company.CompanyBuilder().setId(5).build()).setName("toto").setDiscontinued(null)
				.setIntroduced(null).build();
		DAOComputer.getInstance(conn).persisteComputer(computer1);
		assertTrue((DAOComputer.getInstance(conn).getComputer(21).isPresent()));

	}
	
	@Test 
	public void testupdateComputer()throws ClassNotFoundException, SQLException {
		Connection conn = ConnexionTest.getInstance().getConn();
		Computer computer1 = DAOComputer.getInstance(conn).getComputer(5).get();
		Computer computer2 = new Computer.ComputerBuilder().setId(5)
				.setCompany(new Company.CompanyBuilder().setId(5).build()).setName("toto").setDiscontinued(null)
				.setIntroduced(null).build();
		DAOComputer.getInstance(conn).updateComputer(computer2);
		computer2 = DAOComputer.getInstance(conn).getComputer(5).get();
		assertNotEquals(computer1.getName(), computer2.getName());
	}

	@Test
	public void testGetListCompany() throws ClassNotFoundException, SQLException {
		Connection conn = ConnexionTest.getInstance().getConn();

		List<Computer> companyList = new ArrayList<>();
		companyList = DAOComputer.getInstance(conn).getAllComputer();
		companyList.stream().forEach(companyDetails -> assertTrue(companyDetails instanceof Computer));
	}

	@After
	public void tearDown() throws Exception {
		assertTrue(1 == 1);
	}
}
