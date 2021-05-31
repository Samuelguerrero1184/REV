package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class RentaEquiposDelValleTest {

	public RentaEquiposDelValle setupScenary1() {
		return new RentaEquiposDelValle();
	}
	
	@Test
	void setAndGetUsersTest() {
		RentaEquiposDelValle rd = setupScenary1();
		List<User> users = rd.getUsers();
		assertEquals(0, users.size());
		List<User> usersAdd = new ArrayList<>();
		usersAdd.add(new User(null, null, 0, null, null));
		rd.setUsers(usersAdd);
		users = rd.getUsers();
		assertEquals(1, users.size());
	}
	
	@Test
	void setAndGetClientsTest() {
		RentaEquiposDelValle rd = setupScenary1();
		rd.setClients(new ArrayList<>());
		List<Client> clients = rd.getClients();
		assertEquals(0, clients.size());
		List<Client> clientsAdd = new ArrayList<>();
		clientsAdd.add(new Client(null, null, 0));
		rd.setClients(clientsAdd);
		clients = rd.getClients();
		assertNotEquals(0, clients.size());
	}
	
	@Test
	void setAndGetEmployeeTest() {
		RentaEquiposDelValle rd = setupScenary1();
		rd.setEmployee(new ArrayList<>());
		List<Employee> employee = rd.getEmployee();
		assertEquals(0, employee.size());
		List<Employee> employeeAdd = new ArrayList<>();
		employeeAdd.add(new Employee(null, null, 0));
		rd.setEmployee(employeeAdd);
		employee = rd.getEmployee();
		assertNotEquals(0, employee.size());
	}
	
	@Test
	void loadTest() throws ClassNotFoundException, IOException {
		RentaEquiposDelValle rd = setupScenary1();
		List<User> users = rd.getUsers();
		assertEquals(0, users.size());
		rd.load();
		users = rd.getUsers();
		assertNotEquals(0, users.size());
	}
	
	@Test
	void addUserTest() throws IOException {
		RentaEquiposDelValle rd = setupScenary1();
		List<User> users = rd.getUsers();
		assertEquals(0, users.size());
		rd.addUser("Isabella", "Naranjo", 123, "Narajov", "123");
		users = rd.getUsers();
		assertNotEquals(0, users.size());
	}
	
	/*@Test
	void searchUsernameTest() throws IOException {
		RentaEquiposDelValle rd = setupScenary1();
		assertEquals(" ", rd.searchUserName("itugeuf"));
		rd.addUser("Isabella", "Naranjo", 123, "Narajov", "123");
		assertEquals("Narajov", rd.searchUserName("Narajov"));
	}
	
	@Test
	void searchUserpasswordTest() throws IOException {
		RentaEquiposDelValle rd = setupScenary1();
		assertEquals(" ", rd.searchUserPassword("itugeuf"));
		rd.addUser("Isabella", "Naranjo", 123, "Narajov", "123");
		assertEquals("Narajov", rd.searchUserPassword("Narajov"));
	}*/

}
