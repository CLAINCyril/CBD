package fr.excilys.cclain;

import static org.junit.Assert.assertEquals;
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
		Computer computer1 = new Computer.ComputerBuilder().setId(600)
				.setCompany(new Company.CompanyBuilder().setId(5).build()).setName("toto").setDiscontinued(null)
				.setIntroduced(null).build();
		DAOComputer.getInstance(conn).persisteComputer(computer1);
		System.out.println((DAOComputer.getInstance(conn).getComputer(600).isPresent()));

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
