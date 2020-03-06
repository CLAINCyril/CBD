package fr.excilys.cclain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import modele.Company;
import modele.Computer;
import persistence.Connexion;
import persistence.ConnexionTest;
import persistence.DAOComputer;

public class DAOComputerTest {

	@Test
	public void testGetComputerById() {
		Connection conn = ConnexionTest.getInstance().getConn();
		assertTrue(DAOComputer.getInstance(conn).getComputer(5).isPresent());
	}

	@Test
	public void testAddComputer() {
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
				.setCompany(new Company.CompanyBuilder().setId(5).build()).setName("tato").setDiscontinued(null)
				.setIntroduced(null).build();
		DAOComputer.getInstance(conn).updateComputer(computer2);
		computer2 = DAOComputer.getInstance(conn).getComputer(5).get();
		assertNotEquals(computer1.getName(), computer2.getName());
	}

	@Test
	public void testGetListComputer() {
		Connection conn = ConnexionTest.getInstance().getConn();

		List<Computer> companyList = new ArrayList<>();
		companyList = DAOComputer.getInstance(conn).getAllComputer();
		companyList.stream().forEach(companyDetails -> assertTrue(companyDetails instanceof Computer));
	}
	
	@Test
	public void testDeleteListComputer() {
		Connection conn = ConnexionTest.getInstance().getConn();
		List<String> listIdComputer = new ArrayList<>();
		listIdComputer.add("400");
		listIdComputer.add("500");

		DAOComputer.getInstance(conn).deleteComputerListe(listIdComputer);
		assertFalse(DAOComputer.getInstance(conn).getComputer(400).isPresent());
	}

}
