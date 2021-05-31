package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClientTest {

	public Client setupScenary1() {
		return new Client(null, null, 0);
	}
	
	@Test
	void setAndGetAddressTest() {
		Client c = setupScenary1();
		assertNull(c.getAddress());
		c.setAddress("Cra 1C");
		assertEquals("Cra 1C", c.getAddress());
	}
	
	@Test
	void setAndGetPhoneTest() {
		Client c = setupScenary1();
		assertNull(c.getPhone());
		c.setPhone("12345");
		assertEquals("12345", c.getPhone());
	}

}
