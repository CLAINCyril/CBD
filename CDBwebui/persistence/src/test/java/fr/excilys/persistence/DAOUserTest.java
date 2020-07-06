package fr.excilys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.configuration.PersistenceConfig;
import fr.excilys.model.UserCdb;
import fr.excilys.model.UserCdb.Builder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@Transactional
public class DAOUserTest {
	
	@Autowired
	DAOUser daoUser;
	
	UserCdb userNoInBase = new UserCdb(new Builder()
			.setName("tototo")
			.setPassword("titi")
			.setRole("USER"));
	
	UserCdb userInBase = new UserCdb(new Builder()
			.setName("toto")
			.setPassword("toto")
			.setRole("USER"));

	@Test
	public void assertPersitUser() {
		daoUser.persist(userNoInBase);
		
		assertTrue(daoUser.getUser(userNoInBase.getName()).isPresent());
	}
	@Test
	public void assertPersitGoodUser() {
		daoUser.persist(userNoInBase);
		
		assertEquals(userNoInBase,  daoUser.getUser(userNoInBase.getName()).get());
	}
	
	
	@Test
	public void assertDeleteUser1() {
		daoUser.deleteUser("toto");
		assertTrue(daoUser.getUser("toto").isEmpty());
	}
	
	@Test
	public void assertUpdateDosntAddUser() {
		userInBase.setPassword("somePassword");
		daoUser.updateUser(userInBase);
		assertEquals(4, daoUser.countUser());
	}
	
	//update doesn't work 
	//change code update by id , name isn't 
	// the primary anymore
	@Test
	@Deprecated
	public void assertUpdateUserUpdatePassword() {
		userInBase.setPassword("somePassword");
		daoUser.updateUser(userInBase);
		assertEquals(userInBase.getPassword(),
				daoUser.getUser("toto").get().getName());
	}
	
	@Test
	public void assertCountReturn4() {
		assertEquals(4, daoUser.countUser());
	}
	
	@Test
	@Deprecated
	public void assertUpdateUserUpdateRole() {
		userInBase.setRole("ADMIN");
		daoUser.updateUser(userInBase);
		assertEquals(userInBase.getRole(), daoUser.getUser("toto").get().getRole());
	}
}
