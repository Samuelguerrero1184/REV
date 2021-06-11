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
		employee = new ArrayList<>();
		clients = new ArrayList<>();
	}

	// ---------------------------------------------GETTERS & SETTERS-----------------------------------------------
	public List<User> getUsers() {
		return users;
	}
	
	public List<Client> getClients() {
		return clients;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	// ----------------------------------------------SERIALIZATION---------------------------------------------------

	// -----------------------------SAVE



	public void saveUsers() throws IOException {
		FileOutputStream fos = new FileOutputStream("data/Saved_users.va");
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(users);
		out.close();
	}
	
	public void saveEmployees() throws IOException{
		FileOutputStream fos = new FileOutputStream("data/Saved_employees.va");
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(employee);
		out.close();
	}
	
	public void saveClients() throws IOException{
		FileOutputStream fos = new FileOutputStream("data/Saved_clietns.va");
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(clients);
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
		File eload = new File("data/Saved_employees.va");
		if (eload.exists()) {
			FileInputStream fis = new FileInputStream(eload);
			ObjectInputStream input = new ObjectInputStream(fis);
			employee = (ArrayList<Employee>) input.readObject();
			input.close();
		}
		File cload = new File("data/Saved_clietns.va");
		if (cload.exists()) {
			FileInputStream fis = new FileInputStream(cload);
			ObjectInputStream input = new ObjectInputStream(fis);
			clients = (ArrayList<Client>) input.readObject();
			input.close();
		}
		
	}
	
	//---------------------------------------------------------------------------------------------------------------

	public void addUser(User user) throws IOException {
		if(users.isEmpty()) {
			users.add(user);
		} else {
			int c = 0;
			while(c <= users.size() && comparatorAddUser(user.getUserName(), (users.get(c)).getUserName()) > 1) {
				c++;
			}
			users.add(c, user);
		}
		saveUsers();
	}
	
	public void addEmployee(Employee newEmployee) throws IOException {
		if(employee.isEmpty()) {
			employee.add(newEmployee);
		} else {
			int i = 0;
			while (i < employee.size() && comparatorAddEmployee(newEmployee.getLastname(), employee.get(i).getLastname()) >1) {
				i++;
			}
			employee.add(i, newEmployee);
		}
		saveEmployees();
		for(int i = 0; i<employee.size();i++) {
		System.out.println(employee.get(i).getEntryDate());
		}
		}
	
	
	public void addClient(String name, String lName, String id, String address, String phone) throws IOException {
		clients.add(new Client(name, lName, id, address, phone));
		saveClients();
	}
	
	//------------------------------------------------------------------------------------------------
	
	private int comparatorAddUser(String username1, String username2) {
		return username1.compareToIgnoreCase(username2);
	}
	
	private int comparatorAddEmployee(String name1, String name2) {
		return name1.compareToIgnoreCase(name2);
	}
	
	//------------------------------------------------------------------------------------------------

	public User binarySearchUser(String username) {
		User userSeek = null;
		if(users.size() > 0) {
			boolean find = false;
			int in = 0;
			int fin = users.size();

			while(in <= fin && !find) {
				int pos = (int) Math.floor((in+fin)/2);
				if(pos != users.size()) {
					String el = users.get(pos).getUserName();
					int compar = username.compareToIgnoreCase(el);
					if(compar == 0) {
						userSeek = users.get(pos);
						find = true;
					} else if(compar < 0) {
						fin = pos - 1;
					} else if(compar > 0) {
						in = pos + 1;
					}
				}
			}
		}
		return userSeek;
	}
	
	public String binarySearchEmployee(String entryDate) {
		Employee employeeSeek = null;
		String string = "";
		if (employee.size() > 0) {
			boolean find = false;
			int in = 0;
			int fin = employee.size();
			while (in <= fin && !find) {
				int pos = (int) Math.floor((in + fin) / 2);
				if (pos != employee.size()) {
					int compar = 0;
					String el = employee.get(pos).getLastname();
					compar = entryDate.compareToIgnoreCase(el);
					if (compar == 0) {
						employeeSeek = employee.get(pos);
						string = (employeeSeek.getName()+employeeSeek.getLastname()+employeeSeek.getId()+employeeSeek.getShirtSize()+employeeSeek.getPantsSize()+employeeSeek.getShoesSize()+employeeSeek.getBirthDay()+employeeSeek.getEntryDate());
						find = true;
					} else if (compar < 0) {
						fin = pos - 1;
					} else if (compar > 0) {
						in = pos + 1;
					}
				}
			}
			if (find == false) {
				return " ";
			}
		}
		return string;
	}
	
	//---------------------------------------------------------------------------------
	
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
	
	//----------------------------------------------------------------------
	public String binaryDeleteUser(String username) throws IOException {
		String string = "";
		if(users.size() > 0) {
			boolean find = false;
			int in = 0;
			int fin = users.size();

			while(in <= fin && !find) {
				int pos = (int) Math.floor((in+fin)/2);
				if(pos != users.size()) {
					String el = users.get(pos).getUserName();
					int compar = username.compareToIgnoreCase(el);
					if(compar == 0) {
						string = "El siguiene usuario se ha eliminado del sistema :\n"+toStringUser(users.get(pos));
						users.remove(pos);
						saveUsers();
						find = true;
					} else if(compar < 0) {
						fin = pos - 1;
					} else if(compar > 0) {
						in = pos + 1;
					}
				}
			}
		}
		return string;
	}
	
	public String binaryDeleteEmployee(String entryDate) {
		String string = "";
		if (employee.size() > 0) {
			boolean find = false;
			int in = 0;
			int fin = employee.size();
			while (in <= fin && !find) {
				int pos = (int) Math.floor((in + fin) / 2);
				if (pos != employee.size()) {
					int compar = 0;
					String el = employee.get(pos).getLastname();
					compar = entryDate.compareToIgnoreCase(el);
					if (compar == 0) {
						string = "El siguiene empleado se ha eliminado del sistema :\n"+(employee.get(pos).getName()+employee.get(pos).getLastname()+employee.get(pos).getId()+employee.get(pos).getShirtSize()+employee.get(pos).getPantsSize()+employee.get(pos).getShoesSize()+employee.get(pos).getBirthDay()+employee.get(pos).getEntryDate());
						employee.remove(pos);
						find = true;
					} else if (compar < 0) {
						fin = pos - 1;
					} else if (compar > 0) {
						in = pos + 1;
					}
				}
			}
			if (find == false) {
				return " ";
			}
		}
		return string;
	}
	
	//-----------------------------------------------------------------------
	public String toStringUser(User user) {
		return "Esta es la informcaion del usuario :"+user.getUserName()+" \n"+user.getName()+" "+user.getLastname()+" "+user.getId()+" "+user.getUserName();
	}
	
	
}
