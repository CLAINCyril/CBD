package persistence;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String PERSISTE_COMPANY = "INSERT INTO company name = :name";
	private static final String DELETE_COMPANY = "DELETE FROM company WHERE id = :id";
	private static final String GET_By_ID = " SELECT id,name FROM company WHERE id = :id";
	private static final String UPDATE_COMPANY = "UPDATE company SET name = :name WHERE Id = :id";
	private static final String GET_ALL_COMPANY = "SELECT id,name FROM company";
	private static final String SELECT_COMPANY_PAGE = "SELECT * FROM company LIMIT :limit,:offset ";

	private CompanyMapper companyMapper = new CompanyMapper();

	DAOComputer daoComputer;
	
	public DAOCompany(DataSource dataSource, DAOComputer daoComputer) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.daoComputer = daoComputer;
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
		Map<String, String> namedParameters = Collections.singletonMap("name", company.getName());
		this.namedParameterJdbcTemplate.update(PERSISTE_COMPANY, namedParameters);
	}

	/**
	 * Supprime un element de "company" par Id.
	 * 
	 * @author cyril
	 * @param Id
	 */
	
	@Transactional
	public void deleteCompany(int IdCompany) {
		Map<String, Integer> namedParameters = Collections.singletonMap("id", IdCompany);
		daoComputer.deleteComputerWhereCompany(IdCompany);
		this.namedParameterJdbcTemplate.update(DELETE_COMPANY,namedParameters);
	}

	/**
	 * Récupère un element de "company" par Id.
	 * 
	 * @param Id
	 * @return Company
	 */
	public Optional<Company> getCompany(int Id) {		
		Map<String, Integer> idParameters = Collections.singletonMap("id", Id);
		Company company = this.namedParameterJdbcTemplate.queryForObject(GET_By_ID, idParameters, this.companyMapper);
		return Optional.of(company);

	}
	
	/**
	 * Modifie un element la table "company".
	 * 
	 * @param company
	 */
	public void updateCompany(Company company) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ID",company.getId()).addValue("name", company.getName());
		this.namedParameterJdbcTemplate.update(UPDATE_COMPANY, namedParameters);
	}

	/**
	 * Interroge la BDD et retourne la liste de toutes les company.
	 * 
	 * @return List
	 */
	public List<Company> getAllCompany() {
				
		return this.namedParameterJdbcTemplate.query(GET_ALL_COMPANY,this.companyMapper);
	}

	/**
	 * Interroge la BDD et retourne la liste de toutes les company pagine.
	 * 
	 * @param int
	 * @param int
	 * @return List
	 */
	public List<Company> getPageCompany(int offset, int number) {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("limit", number).addValue("offset", offset);
		return this.namedParameterJdbcTemplate.query(SELECT_COMPANY_PAGE, namedParameters, this.companyMapper);
 
	}

}
