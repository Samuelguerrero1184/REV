package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RentaEquiposDelValle {

	private List<User> users;
	private List<Client>clients;
	private List<Employee>employee;

	public RentaEquiposDelValle() {
		users = new ArrayList<>();
	}

	// ---------------------------------------------GETTERS & SETTERS-----------------------------------------------
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	// ----------------------------------------------SERIALIZATION---------------------------------------------------

	// -----------------------------SAVE



	public void saveUsers() throws IOException {
		FileOutputStream fos = new FileOutputStream("data/Saved_users.va");
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(users);
		out.close();
	}

	// -----------------------------LOAD

	@SuppressWarnings("unchecked")
	public void load() throws ClassNotFoundException, IOException {
		File uload = new File("data/Saved_users.va");
		if (uload.exists()) {
			FileInputStream fis = new FileInputStream(uload);
			ObjectInputStream input = new ObjectInputStream(fis);
			users = (ArrayList<User>) input.readObject();
			input.close();
		}
	}
	
	//---------------------------------------------------------------------------------------------------------------

	public void addUser(String name, String lName, int id, String user, String psw) throws IOException {
		users.add(new User(name, lName, id, user, psw));
		saveUsers();
	}

	public String searchUserName(String user) throws IOException {
		String veh = null;
		boolean found = false;
		float start = 0;
		float end = users.size();
		while (start <= end && !found) {
			int medium = (int) Math.floor((start + end) / 2);

			if (medium != users.size()) {
				String mediumElement = users.get(medium).getUserName();
				float compareResult = user.compareToIgnoreCase(mediumElement);
				if (compareResult == 0) {
					found = true;
					veh = users.get(medium).getUserName();

				} else if (compareResult < 0)
					end = medium - 1;
				else if (compareResult > 0)
					start = medium + 1;
			}
		}
		if (found == false) {
			return " ";
		}
		return veh;
	}

	public String searchUserPassword(String ps) throws IOException {
		String veh = null;
		boolean found = false;
		float start = 0;
		float end = users.size();
		while (start <= end && !found) {
			int medium = (int) Math.floor((start + end) / 2);
			if (medium != users.size()) {
				String mediumElement = users.get(medium).getUserPassword();
				float compareResult = ps.compareToIgnoreCase(mediumElement);
				if (compareResult == 0) {
					found = true;
					veh = users.get(medium).getUserPassword();

				} else if (compareResult < 0)
					end = medium - 1;
				else if (compareResult > 0)
					start = medium + 1;
			}
		}
		if (found == false) {
			return " ";
		}
		return veh;
	}

}
