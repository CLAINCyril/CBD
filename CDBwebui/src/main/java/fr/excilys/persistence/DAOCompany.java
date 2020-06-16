package fr.excilys.persistence;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;

import fr.excilys.mapper.CompanyMapper;
import fr.excilys.modele.Company;
import fr.excilys.modele.QCompany;

/**
 * Classe d'accès aux données de l'objet Company. Permets les verbes CRUD.
 * 
 * @author cyril
 *
 */

@Repository
public class DAOCompany {

	@PersistenceContext
	EntityManager entityManager;

	CompanyMapper companyMapper;
	DAOComputer daoComputer;
	
	public DAOCompany(DAOComputer daoComputer, CompanyMapper companyMapper) {
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
		entityManager.persist(company);
	}

	/**
	 * Supprime un element de "company" par Id.
	 * 
	 * @author cyril
	 * @param Id
	 */
	
	@Transactional
	public void deleteCompany(int id) {
		QCompany company = QCompany.company;

		new JPADeleteClause(entityManager, company).where(company.id.eq(id)).execute();

	}

	/**
	 * Récupère un element de "company" par Id.
	 * 
	 * @param Id
	 * @return Company
	 */
	public Optional<Company> getCompany(int id) {		
		Optional<Company> optionalCompany= Optional.empty();
		QCompany company = QCompany.company;
		JPAQuery<Company> query = new JPAQuery<Company>(entityManager);

		optionalCompany = Optional.of(query.from(company).where(company.id.eq(id)).fetchOne());

		return optionalCompany;
		
	}
	
	/**
	 * Modifie un element la table "company".
	 * 
	 * @param company
	 */
	public void updateCompany(Company company) {
		QCompany qCompany = QCompany.company;
		new JPAUpdateClause(entityManager, qCompany)
		.set(qCompany.name,company.getName())
		.set(qCompany.id,company.getId())
		.execute();
	}

	/**
	 * Interroge la BDD et retourne la liste de toutes les company.
	 * 
	 * @return List
	 */
	public List<Company> getAllCompany() {
		JPAQuery<Company> query = new JPAQuery<Company>(entityManager);
		QCompany company = QCompany.company;
		
		return query.from(company).fetch();
	}

	/**
	 * Interroge la BDD et retourne la liste de toutes les company pagine.
	 * 
	 * @param int
	 * @param int
	 * @return List
	 */
	public List<Company> getPageCompany(int offset, int number) {
 
		JPAQuery<Company> query = new JPAQuery<Company>(entityManager);
		QCompany company = QCompany.company;
		
		return query.from(company)
				.limit(number).offset(offset).fetch();
	}

}
