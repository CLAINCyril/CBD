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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import mapper.ComputerMapper;
import modele.Company;
import modele.Computer;

/**
 * Classe d'accès aux données de l'objet computer. Permets les verbes CRUD.
 * 
 * @author cyril
 *
 */
@Repository
public final class DAOComputer {
	private static volatile DAOComputer instance = null;
	private static Logger logger = LoggerFactory.getLogger(DAOComputer.class);

	private static final String PERSISTE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id=?";
	private static final String GET_COMPUTER = "SELECT * FROM computer "
			+ "LEFT JOIN company ON company_id = company.id WHERE computer.id = ?;";
	private static final String GET_ALL_COMPUTER = "SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id";
	private static final String GET_PAGE_COMPUTER = "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id  LIMIT ?,?;";
	private static String getPageOrderBy = "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id ORDER BY ";
	private static final String GET_PAGE_COMPUTER_NAME = "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.name LIKE ? LIMIT ?,?;";
	protected static final String DELETE_ALL_COMPUTER_WHERE_COMPANY_EGALE = " DELETE FROM computer WHERE company_id = ?;";
	protected static String DELETE_ALL_COMPUTER_BY_ID = "DELETE FROM computer WHERE id in (?";
	private static final String UPDATE_COMPUTER = "UPDATE computer " + "SET  name = ?, Introduced = ?,"
			+ "Discontinued = ?,company_id = ? WHERE Id = ?";

	private DAOComputer() {
	}

	public final static DAOComputer getInstance() {

		if (DAOComputer.instance == null) {
			synchronized (DAOComputer.class) {
				if (DAOComputer.instance == null) {
					DAOComputer.instance = new DAOComputer();
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
				Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementPersisteComputer = conn.prepareStatement(PERSISTE_COMPUTER);) {

			LocalDateTime introduced = computer.getIntroduced();
			LocalDateTime Discontinued = computer.getDiscontinued();

			statementPersisteComputer.setString(1, computer.getName());
			statementPersisteComputer.setTimestamp(2,
					introduced != null ? Timestamp.valueOf(introduced) : null);
			statementPersisteComputer.setTimestamp(3,
					introduced != null ? Timestamp.valueOf(Discontinued) : null);
			statementPersisteComputer.setInt(4, computer.getCompany().getId());
			statementPersisteComputer.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());

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

		try (	Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementDeleteComputer = conn.prepareStatement(DELETE_COMPUTER);) {
			statementDeleteComputer.setInt(1, id);
			statementDeleteComputer.executeUpdate();

		} catch (SQLException sql) {
			logger.error(sql.getMessage());
		}
	}
	
	public void deleteComputerListe(List<String> listIdComputer) {
		for (int i = 1; i < listIdComputer.size(); i++) {
			DELETE_ALL_COMPUTER_BY_ID += ",?";
		}
		DELETE_ALL_COMPUTER_BY_ID += ");";
		try(	Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementDeleteListComputer= conn.prepareStatement(DELETE_ALL_COMPUTER_BY_ID);){
			for (int i = 0; i < listIdComputer.size(); i++) {
				statementDeleteListComputer.setString(i+1, listIdComputer.get(i));
			}
			statementDeleteListComputer.executeUpdate();
		} catch (SQLException sql) {
			System.out.println(sql.getMessage());
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
		try (	Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementGetcomputer = conn.prepareStatement(GET_COMPUTER);
				ResultSet resDetailcomputer = setIntStatement(id, statementGetcomputer);) {
			if (resDetailcomputer.next()) {
				computer = ComputerMapper.getInstance().getComputer(resDetailcomputer);
			}

		} catch (SQLException sql) {
			logger.error(sql.getMessage());

		}
		return computer;

	}

	private ResultSet setIntStatement(int id, PreparedStatement statementGetcomputer) throws SQLException {
		statementGetcomputer.setInt(1, id);
		ResultSet resDetailcomputer = statementGetcomputer.executeQuery();
		return resDetailcomputer;
	}

	/**
	 * Modifie un element la table "computer".
	 * 
	 * @param computer
	 */
	public void updateComputer(Computer computer) {

		try (	Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementUpdatecomputer = conn.prepareStatement(UPDATE_COMPUTER);) {
			LocalDateTime introduced = computer.getIntroduced();
			LocalDateTime Discontinued = computer.getDiscontinued();

			statementUpdatecomputer.setString(1, computer.getName());
			statementUpdatecomputer.setTimestamp(2, introduced != null ? Timestamp.valueOf(introduced) : null);
			statementUpdatecomputer.setTimestamp(3, Discontinued != null ? Timestamp.valueOf(Discontinued) : null);
			statementUpdatecomputer.setInt(4, computer.getCompany().getId());
			statementUpdatecomputer.setInt(5, computer.getId());
			statementUpdatecomputer.executeUpdate();

		} catch (SQLException sql) {
			logger.error(sql.getMessage());
		}

	}

	/**
	 * Interroge la BDD et retourne la liste de tous les computers.
	 * 
	 * @return List computer
	 */
	public List<Computer> getAllComputer() {
		Company company = new Company();

		List<Computer> computerlist = new ArrayList<Computer>();

		try (	Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementSelectall = conn.prepareStatement(GET_ALL_COMPUTER);
				ResultSet resListecomputer = statementSelectall.executeQuery();) {

			while (resListecomputer.next()) {
				Computer computer = ComputerMapper.getInstance().getComputer(resListecomputer).get();
				computerlist.add(computer);

			}

		} catch (SQLException sql) {
			logger.error(sql.getMessage());
		}
		return computerlist;
	}

	/**
	 * Interroge la BDD et retourne la liste de tous les computers pagine.
	 * 
	 * @return List computer
	 */
	public List<Computer> getPageComputer(int offset, int number) {

		Company company = new Company();

		List<Computer> computerlist = new ArrayList<Computer>();
		try (	Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementSelecPage = conn.prepareStatement(GET_PAGE_COMPUTER);
				ResultSet resListecomputer = getPageResSet(offset, number, statementSelecPage);) {
			while (resListecomputer.next()) {
				Computer computer = ComputerMapper.getInstance().getComputer(resListecomputer).get();

				computerlist.add(computer);
			}

		} catch (SQLException sql) {
			logger.error(sql.getMessage());
		}
		return computerlist;
	}

	public List<Computer> getPageComputerByName(String search, int offset, int number) {

		Company company = new Company();

		List<Computer> computerlist = new ArrayList<Computer>();

		try (	Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementSelecPage = conn.prepareStatement(GET_PAGE_COMPUTER_NAME);
				ResultSet resListecomputer = getStatementSearch(search, offset, number, statementSelecPage);) {
				while (resListecomputer.next()) {
					Computer computer = ComputerMapper.getInstance().getComputer(resListecomputer).get();
					computerlist.add(computer);
				}
		} catch (SQLException sql) {
			logger.error(sql.getMessage());
		}
		return computerlist;
	}

	private ResultSet getStatementSearch(String search, int offset, int number, PreparedStatement statementSelecPage)
			throws SQLException {
		statementSelecPage.setString(1, search.toUpperCase());
		statementSelecPage.setInt(2, offset);
		statementSelecPage.setInt(3, number);
		ResultSet resListecomputer = statementSelecPage.executeQuery();
		return resListecomputer;
	}

	public List<Computer> getPageComputerOrder(int offset, int number, String order) {

		List<Computer> computerlist = new ArrayList<Computer>();
		
		String newGetPageOrderBy = getPageOrderBy + order+" LIMIT ?,?;";
		try (	Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementSelecPage = conn.prepareStatement(newGetPageOrderBy);
				ResultSet resListecomputer = getPageResSet(offset, number, statementSelecPage);) {
			while (resListecomputer.next()) {
				Computer computer = ComputerMapper.getInstance().getComputer(resListecomputer).get();

				computerlist.add(computer);
			}
		} catch (SQLException sql) {
			logger.error(sql.getMessage());
		}
		return computerlist;
	}

	private ResultSet getPageResSet(int offset, int number, PreparedStatement statementSelecPage) throws SQLException {
		statementSelecPage.setInt(1, offset);
		statementSelecPage.setInt(2, number);
		ResultSet resListecomputer = statementSelecPage.executeQuery();
		return resListecomputer;
	}
}