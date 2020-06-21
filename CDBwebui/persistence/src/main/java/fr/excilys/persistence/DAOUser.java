package fr.excilys.persistence;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;

import fr.excilys.model.QUserCdb;
import fr.excilys.model.UserCdb;

@Repository
public class DAOUser {
	
	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void persist(UserCdb user) {
		entityManager.persist(user);
	}
	
	public void deleteUser(String name) {
		QUserCdb user = QUserCdb.userCdb;

		new JPADeleteClause(entityManager, user).where(user.name.eq(name)).execute();

	}

	public Optional<UserCdb> getUser(String name) {
		Optional<UserCdb> optionalUser = Optional.empty();
		QUserCdb user = QUserCdb.userCdb;
		JPAQuery<UserCdb> query = new JPAQuery<UserCdb>(entityManager);
		optionalUser = Optional.ofNullable(query.from(user).where(user.name.eq(name)).fetchOne());
		return optionalUser;
	}
	

	public void updateUser(UserCdb user) {

		QUserCdb qUser = QUserCdb.userCdb;
		new JPAUpdateClause(entityManager, qUser).where(qUser.name.eq(user.getName()))
				.set(qUser.name, user.getName()).set(qUser.password, user.getPassword())
				.execute();

	}
}
