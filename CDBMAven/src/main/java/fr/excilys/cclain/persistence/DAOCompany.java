package fr.excilys.cclain.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exception.Loggin;
import fr.excilys.cclain.mapper.CompanyMapper;
import fr.excilys.cclain.modele.Company;

/**
 * Classe d'accès aux données de l'objet Company. Permets les verbes CRUD.
 * 
 * @author cyril
 *
 */
public final class DAOCompany {

	private static volatile DAOCompany instance = null;
	private static final String PERSISTE_COMPANY = "INSERT INTO company (name)" + " values( ?)";
	private static final String DELETE_COMPANY = "DELETE FROM company WHERE id=?";
	private static final String GET_By_ID = " SELECT id,name FROM company WHERE id=?";
	private static final String UPDATE_COMPANY = "UPDATE company " + "SET name = ? WHERE Id = ?";
	private static final String SELECT_ALL_COMPANY = "SELECT id,name FROM company";
	private static final String SELECT_COMPANY_PAGE = "SELECT * FROM company LIMIT ?,? ";

	private DAOCompany() {
	}

	public final static DAOCompany getInstance() {
		if (DAOCompany.instance == null) {
			synchronized (DAOCompany.class) {
				if (DAOCompany.instance == null) {
					DAOCompany.instance = new DAOCompany();
				}
			}
		}
		return DAOCompany.instance;
	}

	/**
	 * Persiste un element de "company" par Id.
	 * 
	 * @author cyril
	 * @param id
	 * @param nom
	 * @return
	 */
	public void persisteCompany(Company company) {
		try (Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementPersisteCompany = conn.prepareStatement(PERSISTE_COMPANY);) {
			statementPersisteCompany.setString(1, company.getName());
			statementPersisteCompany.executeUpdate();
			statementPersisteCompany.close();

		} catch (SQLException e) {
			Loggin.display(e.getMessage());

		}
	}
	/**
	 * Persiste un element de "company" par Id.
	 * 
	 * @author cyril
	 * @param id
	 * @param nom
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public void persisteCompanyTest(Company company) throws ClassNotFoundException {
		try (Connection conn = Connexion.getInstance().getconnTest();
				PreparedStatement statementPersisteCompany = conn.prepareStatement(PERSISTE_COMPANY);) {
			statementPersisteCompany.setString(1, company.getName());
			statementPersisteCompany.executeUpdate();
			statementPersisteCompany.close();

		} catch (SQLException e) {
			Loggin.display(e.getMessage());

		}
	}

	/**
	 * Supprime un element de "company" par Id.
	 * 
	 * @author cyril
	 * @param Id
	 */
	public void deleteCompany(int Id) {
		try (Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementSupresisoncompany = conn.prepareStatement(DELETE_COMPANY);) {
			statementSupresisoncompany.setInt(1, Id);
			statementSupresisoncompany.executeUpdate();
			statementSupresisoncompany.close();
		} catch (Exception e) {
			Loggin.display(e.getMessage());
		}
	}

	/**
	 * Supprime un element de "company" par Id pour test h2.
	 * 
	 * @author cyril
	 * @param Id
	 */
	public void deleteCompanyTest(int Id) {
		try (Connection conn = Connexion.getInstance().getconnTest();
				PreparedStatement statementSupresisoncompany = conn.prepareStatement(DELETE_COMPANY);) {
			statementSupresisoncompany.setInt(1, Id);
			statementSupresisoncompany.executeUpdate();
			statementSupresisoncompany.close();
		} catch (Exception e) {
			Loggin.display(e.getMessage());
		}
	}
	/**
	 * Récupère un element de "company" par Id.
	 * 
	 * @param Id
	 * @return Company
	 */
	public Optional<Company> getCompany(int Id) {

		Optional<Company> company = Optional.empty();

		try (Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementGetCompany = conn.prepareStatement(GET_By_ID);) {
			statementGetCompany.setInt(1, Id);
			ResultSet resDetailCompany = statementGetCompany.executeQuery();
			resDetailCompany.next();
			company = CompanyMapper.getInstance().getCompany(resDetailCompany);
			statementGetCompany.close();
			resDetailCompany.close();


		} catch (Exception e) {
			e.printStackTrace();

		}
		return company;

	}

	/**
	 * Récupère un element de "company" par Id pour les test h2db
	 * 
	 * @param Id
	 * @return Company
	 */
	public Optional<Company> getCompanyTest(int Id) {

		Optional<Company> company = Optional.empty();

		try (Connection conn = Connexion.getInstance().getconnTest();
				PreparedStatement statementGetCompany = conn.prepareStatement(GET_By_ID);) {
			statementGetCompany.setInt(1, Id);
			ResultSet resDetailCompany = statementGetCompany.executeQuery();
			resDetailCompany.next();
			company = CompanyMapper.getInstance().getCompany(resDetailCompany);
			statementGetCompany.close();
			resDetailCompany.close();


		} catch (Exception e) {
			e.printStackTrace();

		}
		return company;

	}
	
	/**
	 * Modifie un element la table "company".
	 * 
	 * @param company
	 */
	public void updateCompany(Company company) {

		try (Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementUpdatecompany = conn.prepareStatement(UPDATE_COMPANY);) {

			statementUpdatecompany.setString(1, company.getName());
			statementUpdatecompany.executeUpdate();
			statementUpdatecompany.close();

		} catch (SQLException e) {
			Loggin.display(e.getMessage());
		}
	}

	/**
	 * Interroge la BDD et retourne la liste de toutes les company.
	 * 
	 * @return List
	 */
	public List<Company> getAllCompany() {

		List<Company> companylist = new ArrayList<Company>();

		try (Connection conn = Connexion.getInstance().getConn();
				Statement statementSelectall = conn.createStatement();) {

			ResultSet resListeCompany = statementSelectall.executeQuery(SELECT_ALL_COMPANY);
			while (resListeCompany.next()) {

				companylist.add(CompanyMapper.getInstance().getCompany(resListeCompany).get());
			}

			resListeCompany.close();

		} catch (SQLException e) {
			Loggin.display(e.getMessage());
		}
		return companylist;

	}
	

	/**
	 * Interroge la BDD et retourne la liste de toutes les company pour les test h2db.
	 * 
	 * @return List
	 * @throws ClassNotFoundException 
	 */
	public List<Company> getAllCompanyTest() throws ClassNotFoundException {

		List<Company> companylist = new ArrayList<Company>();

		try (Connection conn = Connexion.getInstance().getconnTest();
				Statement statementSelectall = conn.createStatement();) {

			ResultSet resListeCompany = statementSelectall.executeQuery(SELECT_ALL_COMPANY);
			while (resListeCompany.next()) {

				companylist.add(CompanyMapper.getInstance().getCompany(resListeCompany).get());
			}

			resListeCompany.close();

		} catch (SQLException e) {
			Loggin.display(e.getMessage());
		}
		return companylist;

	}

	/**
	 * Interroge la BDD et retourne la liste de toutes les company pagine.
	 * 
	 * @param int
	 * @param int
	 * @return List
	 */
	public List<Company> getPageCompany(int offset, int number) {

		List<Company> companylist = new ArrayList<Company>();

		try (Connection conn = Connexion.getInstance().getConn();
				PreparedStatement statementSelectPage = conn.prepareStatement(SELECT_COMPANY_PAGE);) {
			statementSelectPage.setInt(1, offset);
			statementSelectPage.setInt(2, number);
			ResultSet resListeCompany = statementSelectPage.executeQuery();

			while (resListeCompany.next()) {
				companylist.add(CompanyMapper.getInstance().getCompany(resListeCompany).get());
			}

			statementSelectPage.close();

		} catch (SQLException e) {
			Loggin.display(e.getMessage());
		}
		return companylist;
	}

}
