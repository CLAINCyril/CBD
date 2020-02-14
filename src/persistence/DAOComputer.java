package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

	private ServiceCompany servCompany;

	private static final String PERSISTE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id=?";
	private static final String GET_COMPUTER = "SELECT * FROM computer "
			+ "LEFT JOIN company ON company_id = company.id WHERE computer.id = ?;";
	private static final String GET_ALL_COMPUTER = "SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id";
	private static final String GET_PAGE_COMPUTER = "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id  LIMIT ?,?;";
	private static final String UPDATE_COMPUTER = "UPDATE computer " + "SET  name = ?, Introduced = ?,"
			+ "Discontinued = ?,company_id = ? WHERE Id = ?";

	private DAOComputer() {
		this.servCompany = ServiceCompany.getInstance();
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
	public void persistecomputer(Computer computer) {

		try (Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementPersisteComputer = conn.prepareStatement(PERSISTE_COMPUTER);) {
			
			statementPersisteComputer.setString(1, computer.getName());
			statementPersisteComputer.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
			statementPersisteComputer.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
			statementPersisteComputer.setInt(4, computer.getCompany().getId());
			statementPersisteComputer.executeUpdate();
			
            statementPersisteComputer.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	/**
	 * Supprime un element de "computer" par Id.
	 * 
	 * @author cyril
	 * @param id
	 * @return
	 */
	public void deletecomputer(int id) {

		try (Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementSupresisoncomputer = conn.prepareStatement(DELETE_COMPUTER);) {
			statementSupresisoncomputer.setInt(1, id);
			statementSupresisoncomputer.executeUpdate();
			statementSupresisoncomputer.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Récupère un element de "computer" par Id.
	 * 
	 * @param id
	 * @return computer
	 */
	public Computer getComputer(int id) {
		Computer computer = new Computer();


		try (Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementGetcomputer = conn.prepareStatement(GET_COMPUTER);) {
			statementGetcomputer.setInt(1, id);
			ResultSet resDetailcomputer = statementGetcomputer.executeQuery();
			resDetailcomputer.next();
			computer = ComputerMapper.getInstance().getComputer(resDetailcomputer);

			statementGetcomputer.close();
			resDetailcomputer.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return computer;

	}

	/**
	 * Modifie un element la table "computer".
	 * 
	 * @param computer
	 */
	public void updateComputer(Computer computer) {

		Computer comp = getComputer(computer.getId());

		try (Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementUpdatecomputer = conn.prepareStatement(UPDATE_COMPUTER);) {

			statementUpdatecomputer.setString(1, computer.getName());
			statementUpdatecomputer.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
			statementUpdatecomputer.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
			statementUpdatecomputer.setInt(4, computer.getCompany().getId());
			statementUpdatecomputer.setInt(5, computer.getId());
			statementUpdatecomputer.executeUpdate();

			statementUpdatecomputer.close();

		} catch (SQLException e) {
			e.printStackTrace();
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

		try (Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementSelectall = conn.prepareStatement(GET_ALL_COMPUTER);) {
			ResultSet resListecomputer = statementSelectall.executeQuery();

			while (resListecomputer.next()) {
				Computer computer = ComputerMapper.getInstance().getComputer(resListecomputer);
				computerlist.add(computer);

			}

			statementSelectall.close();
			resListecomputer.close();

		} catch (SQLException e) {
			e.printStackTrace();
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

		try (Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementSelecPage = conn.prepareStatement(GET_PAGE_COMPUTER);) {

			statementSelecPage.setInt(1, offset);
			statementSelecPage.setInt(2, number);
			ResultSet resListecomputer = statementSelecPage.executeQuery();
			while (resListecomputer.next()) {
				Computer computer = ComputerMapper.getInstance().getComputer(resListecomputer);

				computerlist.add(computer);
			}

			statementSelecPage.close();
			resListecomputer.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computerlist;
	}

}
