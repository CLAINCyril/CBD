package persistence;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mapper.ComputerMapper;
import modele.Computer;

/**
 * Classe d'accès aux données de l'objet computer. Permets les verbes CRUD.
 * 
 * @author cyril
 *
 */
@Repository
public final class DAOComputer {
	
	@PersistenceContext
	EntityManager entityManager;
	
	ComputerMapper computerMapper;

	public DAOComputer(ComputerMapper computerMapper) {
		this.computerMapper = computerMapper;
	}
	
	public void persisteComputer(Computer computer) {
		entityManager.persist(computer);
	}

	public void deleteComputer(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		this.namedParameterJdbcTemplate.update(SQLRequest.DELETE_COMPUTER.getQuery(), namedParameters);

	}

	public void deleteComputerListe(List<String> listIdComputer) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", listIdComputer);
		this.namedParameterJdbcTemplate.update(SQLRequest.DELETE_ALL_COMPUTER_BY_ID.getQuery(), namedParameters);
		
	}

	public Optional<Computer> getComputer(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("computer.id", id);
		Optional<Computer> optionalComputer = Optional.empty();
		try {
			optionalComputer = Optional.ofNullable(this.namedParameterJdbcTemplate
					.queryForObject(SQLRequest.GET_COMPUTER.getQuery(), namedParameters,this.computerMapper));
			return optionalComputer;
		}
		catch (EmptyResultDataAccessException emptyResult) {
		return optionalComputer;
		}
	}

	public void updateComputer(Computer computer) {

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("Id", computer.getId())
				.addValue("name", computer.getName())
				.addValue("Introduced", computer.getIntroduced())
				.addValue("Discontinued", computer.getDiscontinued())
				.addValue("company_id", computer.getCompany().getId());
		this.namedParameterJdbcTemplate.update(SQLRequest.UPDATE_COMPUTER.getQuery(), namedParameters);
	}

	public List<Computer> getAllComputer() {
		return this.namedParameterJdbcTemplate.query(SQLRequest.GET_ALL_COMPUTER.getQuery(), this.computerMapper);
	}

	public List<Computer> getPageComputer(int offset, int number) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("offset", offset).addValue("number",
				number);
		return this.namedParameterJdbcTemplate.query(SQLRequest.GET_PAGE_COMPUTER.getQuery(), namedParameters, this.computerMapper);

	}

	public List<Computer> getPageComputerByName(String search, int offset, int number) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("search", search)
				.addValue("number", number).addValue("offset", offset);
		return this.namedParameterJdbcTemplate.query(SQLRequest.GET_PAGE_COMPUTER_NAME.getQuery(), namedParameters, this.computerMapper);

	}

	public List<Computer> getPageComputerOrder(int offset, int number, String order) {
		String sqlOrder = SQLRequest.GET_PAGE_ORDER_BY.getQuery() + order + " LIMIT :offset,:number;";
		System.out.println(sqlOrder);
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("offset", offset).addValue("number",
				number);

		return this.namedParameterJdbcTemplate.query(sqlOrder, namedParameters, this.computerMapper);

	}

	@Transactional
	public void deleteComputerWhereCompany(int IdCompany) {
		Map<String, Integer> namedParameters = Collections.singletonMap("id", IdCompany);
		this.namedParameterJdbcTemplate.update(SQLRequest.DELETE_ALL_COMPUTER_WHERE_COMPANY_EGALE.getQuery(), namedParameters);
	}
}