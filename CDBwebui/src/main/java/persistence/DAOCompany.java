package persistence;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
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


	CompanyMapper companyMapper;
	DAOComputer daoComputer;
	
	public DAOCompany(DAOComputer daoComputer, CompanyMapper companyMapper, NamedParameterJdbcTemplate nameParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = nameParameterJdbcTemplate;
		this.daoComputer = daoComputer;
		this.companyMapper = companyMapper;
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
		this.namedParameterJdbcTemplate.update(SQLRequest.PERSISTE_COMPANY.getQuery(), namedParameters);
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
		this.namedParameterJdbcTemplate.update(SQLRequest.DELETE_COMPANY.getQuery(),namedParameters);
	}

	/**
	 * Récupère un element de "company" par Id.
	 * 
	 * @param Id
	 * @return Company
	 */
	public Optional<Company> getCompany(int Id) {		
		Map<String, Integer> idParameters = Collections.singletonMap("id", Id);
		Optional<Company> optionalCompany= Optional.empty();
		try {
			Company company = this.namedParameterJdbcTemplate.queryForObject(SQLRequest.GET_By_ID.getQuery(), idParameters, this.companyMapper);
			return Optional.of(company);
		}
		catch (EmptyResultDataAccessException emptyResultCompany) {
			return optionalCompany;
		}
	}
	
	/**
	 * Modifie un element la table "company".
	 * 
	 * @param company
	 */
	public void updateCompany(Company company) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ID",company.getId()).addValue("name", company.getName());
		this.namedParameterJdbcTemplate.update(SQLRequest.UPDATE_COMPANY.getQuery(), namedParameters);
	}

	/**
	 * Interroge la BDD et retourne la liste de toutes les company.
	 * 
	 * @return List
	 */
	public List<Company> getAllCompany() {
				
		return this.namedParameterJdbcTemplate.query(SQLRequest.GET_ALL_COMPANY.getQuery(),this.companyMapper);
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
		return this.namedParameterJdbcTemplate.query(SQLRequest.SELECT_COMPANY_PAGE.getQuery(), namedParameters, this.companyMapper);
 
	}

}
