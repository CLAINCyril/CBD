package fr.excilys.persistence;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;

import fr.excilys.model.QUserCbd;
import fr.excilys.model.UserCbd;

@Repository
public class UserDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void persist(UserCbd user) {
		entityManager.persist(user);
	}
	
	public void deleteUser(String name) {
		QUserCbd user = QUserCbd.userCbd;

		new JPADeleteClause(entityManager, user).where(user.name.eq(name)).execute();

	}

	public Optional<UserCbd> getUser(String name) {
		Optional<UserCbd> optionalUser = Optional.empty();
		QUserCbd user = QUserCbd.userCbd;
		JPAQuery<UserCbd> query = new JPAQuery<UserCbd>(entityManager);
		optionalUser = Optional.ofNullable(query.from(user).where(user.name.eq(name)).fetchOne());
		return optionalUser;
	}
	

	public void updateUser(UserCbd user) {

		QUserCbd qUser = QUserCbd.userCbd;
		new JPAUpdateClause(entityManager, qUser).where(qUser.name.eq(user.getName()))
				.set(qUser.name, user.getName()).set(qUser.password, user.getPassword())
				.execute();

	}
}
