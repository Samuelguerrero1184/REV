package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersonTest {

	private Employee setupScenary1() {
		return new Employee(null, null, 0);
	}
	
	@Test
	void setAndGetNameTest() {
		Employee e = setupScenary1();
		assertNull(e.getName());
		e.setName("Isabella");
		assertEquals("Isabella", e.getName());
	}
	
	@Test
	void setAndGetLastnameTest() {
		Employee e = setupScenary1();
		assertNull(e.getLastname());
		e.setLastname("Naranjo");
		assertEquals("Naranjo", e.getLastname());
	}
	
	@Test
	void setAndGetIdTest() {
		Employee e = setupScenary1();
		assertEquals(0, e.getId());
		e.setId(1003);
		assertEquals(1003, e.getId());
	}

}
