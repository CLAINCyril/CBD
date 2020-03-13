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
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String PERSISTE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id)"
			+ " VALUES (:computerName, :introduced, :discontinued, :companyId);";
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id=:id";
	private static final String GET_COMPUTER = "SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.id = :computer.id;";
	private static final String GET_ALL_COMPUTER = "SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id";
	private static final String GET_PAGE_COMPUTER = "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id  LIMIT :offset,:number;";
	private static final String GET_PAGE_ORDER_BY = "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id ORDER BY ";
	private static final String GET_PAGE_COMPUTER_NAME = "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.name LIKE :like LIMIT :offset,:number;";
	protected static final String DELETE_ALL_COMPUTER_WHERE_COMPANY_EGALE = " DELETE FROM computer WHERE company_id = :id;";
	protected static final String DELETE_ALL_COMPUTER_BY_ID = "DELETE FROM computer WHERE id IN (:id)";
	private static final String UPDATE_COMPUTER = "UPDATE computer " + "SET  name = :name, Introduced = :Introduced,"
			+ "Discontinued = :Discontinued,company_id = :company_id WHERE Id = :Id";

	private ComputerMapper computerMapper = new ComputerMapper();

	public DAOComputer(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public void persisteComputer(Computer computer) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("computerName", computer.getName())
				.addValue("introduced", computer.getIntroduced())
				.addValue("discontinued", computer.getDiscontinued())
				.addValue("companyId", computer.getCompany().getId());
		this.namedParameterJdbcTemplate.update(PERSISTE_COMPUTER, namedParameters);
	}

	public void deleteComputer(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		this.namedParameterJdbcTemplate.update(DELETE_COMPUTER, namedParameters);

	}

	public void deleteComputerListe(List<String> listIdComputer) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", listIdComputer);
		this.namedParameterJdbcTemplate.update(DELETE_ALL_COMPUTER_BY_ID, namedParameters);

	}

	public Optional<Computer> getComputer(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("computer.id", id);

		return Optional.of(this.namedParameterJdbcTemplate
				.queryForObject(GET_COMPUTER, namedParameters,this.computerMapper));

	}

	public void updateComputer(Computer computer) {

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("Id", computer.getId())
				.addValue("name", computer.getName())
				.addValue("Introduced", computer.getIntroduced())
				.addValue("Discontinued", computer.getDiscontinued())
				.addValue("company_id", computer.getCompany().getId());
		this.namedParameterJdbcTemplate.update(UPDATE_COMPUTER, namedParameters);
	}

	public List<Computer> getAllComputer() {
		return this.namedParameterJdbcTemplate.query(GET_ALL_COMPUTER, this.computerMapper);
	}

	public List<Computer> getPageComputer(int offset, int number) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("offset", offset).addValue("number",
				number);
		return this.namedParameterJdbcTemplate.query(GET_PAGE_COMPUTER, namedParameters, this.computerMapper);

	}

	public List<Computer> getPageComputerByName(String search, int offset, int number) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("like", search)
				.addValue("number", number).addValue("offset", offset);
		return this.namedParameterJdbcTemplate.query(GET_PAGE_COMPUTER_NAME, namedParameters, this.computerMapper);

	}

	public List<Computer> getPageComputerOrder(int offset, int number, String order) {
		String sqlOrder = GET_PAGE_ORDER_BY + order;
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("offset", offset).addValue("number",
				number);

		return this.namedParameterJdbcTemplate.query(sqlOrder, namedParameters, this.computerMapper);

	}

	@Transactional
	public void deleteComputerWhereCompany(int IdCompany) {
		Map<String, Integer> namedParameters = Collections.singletonMap("id", IdCompany);
		this.namedParameterJdbcTemplate.update(DELETE_ALL_COMPUTER_WHERE_COMPANY_EGALE, namedParameters);
	}
}