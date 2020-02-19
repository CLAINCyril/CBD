package fr.excilys.cclain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import fr.excilys.cclain.modele.Computer;
import fr.excilys.cclain.persistence.Connexion;
import fr.excilys.cclain.persistence.ConnexionTest;
import fr.excilys.cclain.persistence.DAOComputer;

public class DAOComputerTest {

	@Test
	public void testGetComputerById() throws ClassNotFoundException, SQLException {
		Connection conn = ConnexionTest.getInstance().getConn();
		Computer computer1 = new Computer.ComputerBuilder().setId(2).build();
		Computer computer2 = DAOComputer.getInstance(conn).getComputerTest(2).get();
		assertEquals(computer1, computer2);
	}
	
	@Test
	public void testGetListCompany() throws ClassNotFoundException, SQLException {
		Connection conn = ConnexionTest.getInstance().getConn();

		List<Computer> companyList = new ArrayList<>();
		companyList = DAOComputer.getInstance(conn).getAllComputerTest();
		companyList.stream().forEach(companyDetails-> assertTrue(companyDetails instanceof Computer));
	}
	@After
	public void tearDown() throws Exception {
		assertTrue(1==1);
	}
}
