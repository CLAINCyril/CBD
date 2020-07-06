package fr.excilys.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserCdbTest {
	
	UserCdb userCdb = new UserCdb.Builder()
			.setName("toto")
			.setPassword("toto")
			.setRole("toto")
			.build();

	@Test
	public void assertNewUserMakeUserCdb() {
		UserCdb user = new UserCdb();
		
		assertEquals(user.getClass(), UserCdb.class);
	}
	
	@Test
	public void assertSetNameUpdateUserName() {
		userCdb.setName("tata");
		
		assertEquals("tata", userCdb.getName());
	}
	
	@Test
	public void assertSetPasswordUpdateUserPassword() {
		userCdb.setPassword("tata");
		
		assertEquals("tata", userCdb.getPassword());
	}
	
	@Test
	public void assertSetRoleUpdateUserRole() {
		userCdb.setRole("tata");
		
		assertEquals("tata", userCdb.getRole());
	}
	
	@Test
	public void assertGetVariableGiveVariable() {
		String variableFromUserCdb = userCdb.getName()+userCdb.getPassword()+userCdb.getRole();
		
		assertEquals(variableFromUserCdb, "totototototo");	
	}
	
	@Test
	public void assertGetIdReurn0() {
		assertEquals(0, userCdb.getId());
	}
	
	@Test
	public void assertSetIdUpdateId() {
		userCdb.setId(12);
		
		assertEquals(12, userCdb.getId());
	}
	
	@Test
	public void assertEqualsUseName() {
		UserCdb userEquals = new UserCdb();
		userEquals.setName("toto");
		
		assertTrue(userEquals.equals(userCdb));
	}
	
	@Test
	public void assertEqualsUseNameOnly() {
		UserCdb userEquals = new UserCdb();
		userEquals.setId(0);
		userEquals.setPassword("toto");
		userEquals.setRole("toto");
		
		assertFalse(userEquals.equals(userCdb));
	}
}
