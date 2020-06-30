package fr.excilys.persistence;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;

import fr.excilys.mapper.ComputerMapper;
import fr.excilys.model.Computer;
import fr.excilys.model.QCompany;
import fr.excilys.model.QComputer;

/**
 * Classe d'accès aux données de l'objet computer. Permets les verbes CRUD.
 * 
 * @author cyril
 *
 */

@Repository
public class DAOComputer {

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
		QComputer computer = QComputer.computer;

		new JPADeleteClause(entityManager, computer).where(computer.id.eq(id)).execute();

	}

	public void deleteComputerListe(List<String> listIdComputer) {
		List<Integer> numbers = computerMapper.stringToIntegers(listIdComputer);
		QComputer computer = QComputer.computer;

		new JPADeleteClause(entityManager, computer).where(computer.id.in(numbers)).execute();
	}

	public Optional<Computer> getComputer(int id) {
		Optional<Computer> optionalComputer = Optional.empty();
		QComputer computer = QComputer.computer;
		JPAQuery<Computer> query = new JPAQuery<Computer>(entityManager);
		optionalComputer = Optional.ofNullable(query.from(computer).where(computer.id.eq(id)).fetchOne());
		return optionalComputer;

	}

	public void updateComputer(Computer computer) {
		QComputer qComputer = QComputer.computer;
		new JPAUpdateClause(entityManager, qComputer).where(qComputer.id.eq(computer.getId()))
				.set(qComputer.name, computer.getName()).set(qComputer.introduced, computer.getIntroduced())
				.set(qComputer.discontinued, computer.getDiscontinued())
				.set(qComputer.company.id, computer.getCompany().getId())
				.execute();

	}

	public List<Computer> getPageComputer(int offset, int number) {
		JPAQuery<Computer> query = new JPAQuery<Computer>(entityManager);
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		
		return query.from(computer)
				.leftJoin(computer.company, company)
				.limit(number).offset(offset).fetch();


	}

	public List<Computer> getPageComputerByName(String search, int offset, int number) {
		JPAQuery<Computer> query = new JPAQuery<Computer>(entityManager);
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		
		return query.from(computer).leftJoin(computer.company, company)
				.where(computer.name.like("%" + search + "%"))
				.orderBy(computer.name.asc())
				.limit(number).offset(offset).fetch();

	}

	public List<Computer> getPageComputerOrder(int offset, int number, String order) {
		JPAQuery<Computer> query = new JPAQuery<Computer>(entityManager);
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		
		return query.from(computer).leftJoin(computer.company, company)
				.orderBy(computer.name.asc())
				.limit(number).offset(offset).fetch();

	}
	
	public void deleteComputerWhereCompany(int idCompany) {
		QComputer computer = QComputer.computer;

		new JPADeleteClause(entityManager, computer).where(computer.company.id.eq(idCompany)).execute();
	}

	public long countComputer(){
		JPAQuery<Void> query = new JPAQuery<Void>(entityManager);
		QComputer computer = QComputer.computer;
		return query.from(computer)
				.fetchCount();

		}
}