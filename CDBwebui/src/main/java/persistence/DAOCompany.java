package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mapper.CompanyMapper;
import modele.Company;

/**
 * Classe d'accès aux données de l'objet Company. Permets les verbes CRUD.
 * 
 * @author cyril
 *
 */
@Repository
public final class DAOCompany {
	private static Logger logger = LoggerFactory.getLogger(DAOCompany.class);

	private static volatile DAOCompany instance = null;
	private static final String PERSISTE_COMPANY = "INSERT INTO company (name)" + " values( ?)";
	private static final String DELETE_COMPANY = "DELETE FROM company WHERE id=?";
	private static final String GET_By_ID = " SELECT id,name FROM company WHERE id=?";
	private static final String UPDATE_COMPANY = "UPDATE company " + "SET name = ? WHERE Id = ?";
	private static final String SELECT_ALL_COMPANY = "SELECT id,name FROM company";
	private static final String SELECT_COMPANY_PAGE = "SELECT * FROM company LIMIT ?,? ";

	private static CompanyMapper companyMapper = new CompanyMapper();
	private Connexion connexion;
	
	@Autowired
	public DAOCompany(Connexion connexion) {
		this.connexion = connexion;
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
		try (	Connection conn = connexion.getConn();
				PreparedStatement statementPersisteCompany = conn.prepareStatement(PERSISTE_COMPANY);) {
			statementPersisteCompany.setString(1, company.getName());
			statementPersisteCompany.executeUpdate();
			statementPersisteCompany.close();

		} catch (SQLException sql) {
			logger.error(sql.getMessage());

		}
	}

	/**
	 * Supprime un element de "company" par Id.
	 * 
	 * @author cyril
	 * @param Id
	 */
	public void deleteCompany(int IdCompany) {
		try (	Connection conn = connexion.getConn();
				PreparedStatement statementSuppressioncompany = conn.prepareStatement(DELETE_COMPANY);
				PreparedStatement statementSuppressionComputer = 
						conn.prepareStatement(DAOComputer.DELETE_ALL_COMPUTER_WHERE_COMPANY_EGALE)) {
			conn.setAutoCommit(false);
			statementSuppressionComputer.setInt(1, IdCompany);
			statementSuppressioncompany.setInt(1, IdCompany);
			statementSuppressionComputer.executeUpdate();
			statementSuppressioncompany.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException sql) {
			logger.error(sql.getMessage());
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

		try (Connection conn = connexion.getConn();
				PreparedStatement statementGetCompany = conn.prepareStatement(GET_By_ID);
				ResultSet resDetailCompany = setIdCompany(Id, statementGetCompany);) {
				resDetailCompany.next();
				company = companyMapper.getCompany(resDetailCompany);
				resDetailCompany.close();

		} catch (Exception sql) {
			logger.error(sql.getMessage());

		}
		return company;

	}

	private ResultSet setIdCompany(int Id, PreparedStatement statementGetCompany) throws SQLException {
		statementGetCompany.setInt(1, Id);
		ResultSet resDetailCompany = statementGetCompany.executeQuery();
		return resDetailCompany;
	}

	/**
	 * Modifie un element la table "company".
	 * 
	 * @param company
	 */
	public void updateCompany(Company company) {

		try (Connection conn = connexion.getConn();
				PreparedStatement statementUpdatecompany = conn.prepareStatement(UPDATE_COMPANY);) {

			statementUpdatecompany.setString(1, company.getName());
			statementUpdatecompany.executeUpdate();
			statementUpdatecompany.close();

		} catch (SQLException sql) {
			logger.error(sql.getMessage());
		}
	}

	/**
	 * Interroge la BDD et retourne la liste de toutes les company.
	 * 
	 * @return List
	 */
	public List<Company> getAllCompany() {

		List<Company> companylist = new ArrayList<Company>();

		try (Connection conn = connexion.getConn();
				Statement statementSelectall = conn.createStatement();) {

			ResultSet resListeCompany = statementSelectall.executeQuery(SELECT_ALL_COMPANY);
			while (resListeCompany.next()) {

				companylist.add(companyMapper.getCompany(resListeCompany).get());
			}

			resListeCompany.close();

		} catch (SQLException sql) {
			logger.error(sql.getMessage());
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

		try (Connection conn = connexion.getConn();
				PreparedStatement statementSelectPage = conn.prepareStatement(SELECT_COMPANY_PAGE);) {
			statementSelectPage.setInt(1, offset);
			statementSelectPage.setInt(2, number);
			ResultSet resListeCompany = statementSelectPage.executeQuery();

			while (resListeCompany.next()) {
				companylist.add(companyMapper.getCompany(resListeCompany).get());
			}

			statementSelectPage.close();

		} catch (SQLException sql) {
			logger.error(sql.getMessage());
		}
		return companylist;
	}

}
