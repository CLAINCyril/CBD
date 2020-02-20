package fr.excilys.cclain;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import fr.excilys.cclain.modele.Company;
import fr.excilys.cclain.modele.Computer;
import fr.excilys.cclain.persistence.Connexion;
import fr.excilys.cclain.persistence.ConnexionTest;
import fr.excilys.cclain.service.ServiceCompany;
import fr.excilys.cclain.service.ServiceComputer;

enum ACTION {
	LISTCOMPUTERS, LISTCOMPANIES, SHOWCOMPUTERDETAILS, CREATECOMPUTER, UPDATECOMPUTER, DELETECOMPUTER;

	static String value(int entree) {
		switch (entree) {
		case 1:
			return ("LISTCOMPUTERS");
		case 2:
			return ("LISTCOMPANIES");
		case 3:
			return ("SHOWCOMPUTERDETAILS");
		case 4:
			return ("CREATECOMPUTER");
		case 5:
			return ("UPDATECOMPUTER");
		case 6:
			return ("DELETECOMPUTER");

		}
		return null;
	}
};

public class CliUI {
	List<Company> companys;
	List<Computer> computers;
	Computer computer;
	Company company;
	Boolean tache;

	/**
	 * Transform un type String en type LocalDatetime.
	 * 
	 * @param date
	 * @return
	 */
	private static LocalDateTime ConvertLocalDateTime(String date) {
		if (date.isEmpty()) {
			return null;
		}
		date = date + " 00:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime datetime = LocalDateTime.parse(date, formatter);
		return datetime;
	}

	/**
	 * retourne une page d'ordinateur
	 * 
	 * @param offset
	 * @param number
	 * @return
	 * @throws SQLException 
	 */
	public List<Computer> getOnePAgeOfComputer(int offset, int number) throws SQLException {
		Connection conn = Connexion.getInstance().getConn();
		return (ServiceComputer.getInstance(conn).getPageComputer(offset, number));
	}

	/**
	 * retourne une page de company.
	 * 
	 * @param offset
	 * @param number
	 * @return
	 * @throws SQLException 
	 */
	public List<Company> getOnePAgeOfCompany(int offset, int number) throws SQLException {
		Connection conn = Connexion.getInstance().getConn();
		return (ServiceCompany.getInstance(conn).getPageCompany(offset, number));
	}

	/**
	 * Creer un ordinateur a l'aide des params saisie a l'entrée.
	 * 
	 * @param sc
	 * @throws SQLException 
	 */
	public void createComputer(Scanner sc) throws SQLException {
		
		Connection conn = Connexion.getInstance().getConn();

		System.out.println("Veuillez saisir le nom :\n");
		computer.setName(sc.next());
		System.out.println("Veuillez saisir la date de sortie :\n");
		computer.setIntroduced(ConvertLocalDateTime(sc.next()));
		System.out.println("Veuillez saisir la date de fin de serie :\n");
		computer.setDiscontinued(ConvertLocalDateTime(sc.next()));
		System.out.println("Veuillez saisir l'id company:\n");
		company = ServiceCompany.getInstance(conn).getCompany(sc.nextInt());
		computer.setCompany(company);
		ServiceComputer.getInstance(conn).persisteComputer(computer);

	}

	public void updateComputer(Scanner sc) throws SQLException {
		Connection conn = Connexion.getInstance().getConn();

		System.out.println("Veuillez saisir l'id :\n");
		Computer computer = new Computer.ComputerBuilder().setId(sc.nextInt()).build();
		System.out.println("Veuillez saisir le nom :\n");
		computer.setName(sc.next());
		System.out.println("Veuillez saisir la date de sortie :\n");
		computer.setIntroduced(ConvertLocalDateTime(sc.next()));
		System.out.println("Veuillez saisir la date de fin de serie :\n");
		computer.setDiscontinued(ConvertLocalDateTime(sc.next()));
		System.out.println("Veuillez saisir l'id company:\n");
		company = ServiceCompany.getInstance(conn).getCompany(sc.nextInt());
		computer.setCompany(company);
		ServiceComputer.getInstance(conn).updateComputer(computer);
	}

	/**
	 * methode de pagination des computers verifie que la page suivante/precedante
	 * existe.
	 * 
	 * @param sc
	 * @throws SQLException 
	 */
	public void pagineCompute(Scanner sc) throws SQLException {
		List<Computer> computs = new ArrayList();
		Connection conn = Connexion.getInstance().getConn();

		boolean condition = true;
		String saisie;
		int offset = 0;
		int number = 20;
		
		int tailleL = ServiceComputer.getInstance(conn).getlength();

		computs = getOnePAgeOfComputer(offset, number);
		System.out.println(computs);
		while (condition) {
			System.out.println("pres n for next p for previous page s fort stop");
			saisie = sc.next();
			condition = (saisie.contentEquals("n")) || (saisie.contentEquals("p"));
			if (saisie.equals("n")) {
				if (20 <= tailleL - number) {

					offset += 20;
					number += 20;
					computs = getOnePAgeOfComputer(offset, number);
				} else {
					System.out.println("vous etes a la derniere page!");
				}
			}
			if (saisie.equals("p")) {
				if (offset <= 20) {
					System.out.println("vous etes a la premiere page!");
				} else {
					offset -= 20;
					number -= 20;
					computs = getOnePAgeOfComputer(offset, number);
				}

			}
			System.out.println(computs);
		}
	}

	public void printComputer(Scanner sc) throws SQLException {
		Connection conn = Connexion.getInstance().getConn();

		Optional<Computer> computer = ServiceComputer.getInstance(conn).getComputer(sc.nextInt());
		if (computer.isPresent()) {
			System.out.println(computer);
		}
		System.out.println("non présent");
	}

	public void deleteComputer(Scanner sc) throws SQLException {
		Connection conn = Connexion.getInstance().getConn();

		ServiceComputer.getInstance(conn).deleteComputer(sc.nextInt());
	}

	/**
	 * Methode de pagination des companys. verifie que la page suivante/precedante
	 * existe.
	 * 
	 * @param sc
	 * @throws SQLException 
	 */
	public void pagineCompany(Scanner sc) throws SQLException {
		Connection conn = Connexion.getInstance().getConn();

		
		List<Company> company = new ArrayList();
		boolean condition = true;
		String saisie;
		int offset = 0;
		int number = 20;
		int tailleL = ServiceCompany.getInstance(conn).getlength();

		company = getOnePAgeOfCompany(offset, number);
		System.out.println(company);
		while (condition) {
			System.out.println("pres n for next p for previous page s fort stop");
			saisie = sc.next();
			condition = (saisie.contentEquals("n")) || (saisie.contentEquals("p"));
			if (saisie == "n") {
				if (20 > tailleL - number) {

					offset += 20;
					number += 20;
					company = getOnePAgeOfCompany(offset, number);
				} else {
					System.out.println("vous etes a la derniere page!");
				}
			}
			if (saisie == "p") {
				if (offset < 20) {
					System.out.println("vous etes a la premiere page!");
				} else {
					offset -= 20;
					number -= 20;
					company = getOnePAgeOfCompany(offset, number);
				}

			}
			System.out.println(company);
		}
	}

	public void UpdateComputer(Scanner sc) {

	}

	public CliUI() throws SQLException {
		this.computer = new Computer();
		this.company = new Company();
		this.tache = false;
		Scanner sc = new Scanner(System.in);
		boolean test = true;
		while (test) {
			System.out.println("============================");
			System.out.println("1 : List computers");
			System.out.println("============================");
			System.out.println("2 : List companies");
			System.out.println("============================");
			System.out.println("3 : Show computer details ");
			System.out.println("============================");
			System.out.println("4 : Create a computer");
			System.out.println("============================");
			System.out.println("5 : Update a computer");
			System.out.println("============================");
			System.out.println("6 :  Delete a computer");
			System.out.println("============================");

			int cases = sc.nextInt();
			switch (ACTION.value(cases)) {
			case "LISTCOMPUTERS":
				pagineCompute(sc);
				break;
			case "LISTCOMPANIES":
				System.out.println("pagine company");
				pagineCompany(sc);
				break;
			case "SHOWCOMPUTERDETAILS":
				System.out.println("saisir Id Computer");
				printComputer(sc);
				break;
			case "CREATECOMPUTER":
				createComputer(sc);
				break;
			case "UPDATECOMPUTER":
				System.out.println("saisissez l'ID computer");
				updateComputer(sc);
				break;
			case "DELETECOMPUTER":
				System.out.println("saisissez l'ID computer");
				deleteComputer(sc);
				break;

			}

		}
		sc.close();

	}
}
