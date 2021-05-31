package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	private User setupScenary1() {
		return new User(null, null, 0, null, null);
	}
	
	@Test
	void setAndGetUsernameTest() {
		User u = setupScenary1();
		assertNull(u.getUserName());
		u.setUserName("Narajov");
		assertEquals("Narajov", u.getUserName());
	}
	
	@Test
	void setAndGetUserpasswordTest() {
		User u = setupScenary1();
		assertNull(u.getUserPassword());
		u.setUserPassword("123");
		assertEquals("123", u.getUserPassword());
	}

}
