package fr.excilys.persistence;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;

import fr.excilys.model.QUser;
import fr.excilys.model.User;

public class UserDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	public void persist(User user) {
		entityManager.persist(user);
	}
	
	public void deleteUser(String name) {
		QUser user = QUser.user;

		new JPADeleteClause(entityManager, user).where(user.name.eq(name)).execute();

	}

	public Optional<User> getUser(String name) {
		Optional<User> optionalUser = Optional.empty();
		QUser user = QUser.user;
		JPAQuery<User> query = new JPAQuery<User>(entityManager);
		optionalUser = Optional.ofNullable(query.from(user).where(user.name.eq(name)).fetchOne());
		return optionalUser;
	}
	

	public void updateUser(User user) {

		QUser qUser = QUser.user;
		new JPAUpdateClause(entityManager, qUser).where(qUser.name.eq(user.getName()))
				.set(qUser.name, user.getName()).set(qUser.password, user.getPassword())
				.execute();

	}
}
