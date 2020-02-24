package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exception.Loggin;
import mapper.ComputerMapper;
import modele.Company;
import modele.Computer;
import service.ServiceCompany;

/**
 * Classe d'accès aux données de l'objet computer. Permets les verbes CRUD.
 * 
 * @author cyril
 *
 */
public final class DAOComputer {

	private static volatile DAOComputer instance = null;

	private Connection conn;

	private static final String PERSISTE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id=?";
	private static final String GET_COMPUTER = "SELECT * FROM computer "
			+ "LEFT JOIN company ON company_id = company.id WHERE computer.id = ?;";
	private static final String GET_ALL_COMPUTER = "SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id";
	private static final String GET_PAGE_COMPUTER = "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id  LIMIT ?,?;";
	private static final String UPDATE_COMPUTER = "UPDATE computer " + "SET  name = ?, Introduced = ?,"
			+ "Discontinued = ?,company_id = ? WHERE Id = ?";

	private DAOComputer(Connection conn) {
		this.conn = conn;
	}

	public final static DAOComputer getInstance(Connection conn) {

		if (DAOComputer.instance == null) {
			synchronized (DAOComputer.class) {
				if (DAOComputer.instance == null) {
					DAOComputer.instance = new DAOComputer(conn);
				}
			}
		}
		return DAOComputer.instance;
	}

	/**
	 * Persiste un element de "computer" par Id.
	 * 
	 * @author cyril
	 * @param id
	 * @param nom
	 * @return
	 */
	public void persisteComputer(Computer computer) {

		try (
				PreparedStatement statementPersisteComputer = conn.prepareStatement(PERSISTE_COMPUTER);) {
			
			LocalDateTime introduced = computer.getIntroduced();
			LocalDateTime Discontinued = computer.getDiscontinued();

			statementPersisteComputer.setString(1, computer.getName());
			statementPersisteComputer.setTimestamp(2, introduced!=null?Timestamp.valueOf(computer.getIntroduced()):null);
			statementPersisteComputer.setTimestamp(3, introduced!=null?Timestamp.valueOf(computer.getIntroduced()):null);
			statementPersisteComputer.setInt(4, computer.getCompany().getId());
			statementPersisteComputer.executeUpdate();
			
            statementPersisteComputer.close();

		} catch (SQLException e) {
			Loggin.display(e.getMessage());

		}

	}

	/**
	 * Supprime un element de "computer" par Id.
	 * 
	 * @author cyril
	 * @param id
	 * @return
	 */
	public void deleteComputer(int id) {

		try (
				PreparedStatement statementDeleteComputer = conn.prepareStatement(DELETE_COMPUTER);) {
			statementDeleteComputer.setInt(1, id);
			statementDeleteComputer.executeUpdate();
			statementDeleteComputer.close();
			
		} catch (SQLException e) {
			Loggin.display(e.getMessage());
		}
	}

	/**
	 * Récupère un element de "computer" par Id.
	 * 
	 * @param id
	 * @return computer
	 */
	public Optional<Computer> getComputer(int id) {
		Optional<Computer> computer = Optional.empty();
		try (
				PreparedStatement statementGetcomputer = conn.prepareStatement(GET_COMPUTER);) {
			statementGetcomputer.setInt(1, id);
			ResultSet resDetailcomputer = statementGetcomputer.executeQuery();
			if(resDetailcomputer.next()) {
			computer = ComputerMapper.getInstance().getComputer(resDetailcomputer);
			}
			resDetailcomputer.close();

		} catch (SQLException e) {
			Loggin.display(e.getMessage());

		}
		return computer;

	}

	/**
	 * Modifie un element la table "computer".
	 * 
	 * @param computer
	 */
	public void updateComputer(Computer computer) {

		try (
				PreparedStatement statementUpdatecomputer = conn.prepareStatement(UPDATE_COMPUTER);) {
			LocalDateTime introduced = computer.getIntroduced();
			LocalDateTime Discontinued = computer.getDiscontinued();

			statementUpdatecomputer.setString(1, computer.getName());
			statementUpdatecomputer.setTimestamp(2, introduced!=null?Timestamp.valueOf(computer.getIntroduced()):null);
			statementUpdatecomputer.setTimestamp(3, introduced!=null?Timestamp.valueOf(computer.getIntroduced()):null);
			statementUpdatecomputer.setInt(4, computer.getCompany().getId());
			statementUpdatecomputer.setInt(5, computer.getId());
			statementUpdatecomputer.executeUpdate();

			statementUpdatecomputer.close();

		} catch (SQLException e) {
			Loggin.display(e.getMessage());
		}

	}

	/**
	 * Interroge la BDD et retourne la liste de tous les computers.
	 * 
	 * @return List computer
	 */
	public List<Computer> getAllComputer() {

//		 this.conn = Connexion.getInstance();
//	     conn.connect();
		Company company = new Company();

		List<Computer> computerlist = new ArrayList<Computer>();

		try (
				PreparedStatement statementSelectall = conn.prepareStatement(GET_ALL_COMPUTER);) {
			ResultSet resListecomputer = statementSelectall.executeQuery();

			while (resListecomputer.next()) {
				Computer computer = ComputerMapper.getInstance().getComputer(resListecomputer).get();
				computerlist.add(computer);

			}

			statementSelectall.close();
			resListecomputer.close();

		} catch (SQLException e) {
			Loggin.display(e.getMessage());
		}
		return computerlist;
	}


	/**
	 * Interroge la BDD et retourne la liste de tous les computers pagine.
	 * 
	 * @return List computer
	 */
	public Optional<List<Computer>> getPageComputer(int offset, int number) {

		Company company = new Company();

		List<Computer> computerlist = new ArrayList<Computer>();

		try (
				PreparedStatement statementSelecPage = conn.prepareStatement(GET_PAGE_COMPUTER);) {

			statementSelecPage.setInt(1, offset);
			statementSelecPage.setInt(2, number);
			ResultSet resListecomputer = statementSelecPage.executeQuery();
			while (resListecomputer.next()) {
				Computer computer = ComputerMapper.getInstance().getComputer(resListecomputer).get();

				computerlist.add(computer);
			}

			statementSelecPage.close();
			resListecomputer.close();

		} catch (SQLException e) {
			Loggin.display(e.getMessage());
		}
		return Optional.ofNullable(computerlist);
	}

}