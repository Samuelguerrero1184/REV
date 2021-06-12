package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import exceptions.ClientNotExistsException;

public class RentaEquiposDelValle {
	
	
	private final String CSV_PATH_MACHINES = "data/machinesData.csv";
	private final String CSV_PATH_CLIENTS = "data/clientsData.csv";

	private List<User> users;
	private List<Client> clients;
	private List<Employee> employee;
	private List<Machine> machines;
	private Machine root;
	private User rootU;

	public RentaEquiposDelValle() {
		users = new ArrayList<>();
		employee = new ArrayList<>();
		clients = new ArrayList<>();
		machines = new ArrayList<>();
	}

	// ---------------------------------------------GETTERS &SETTERS------------------------------------
	public List<User> getUsers() {
		return users;
	}

	public List<Client> getClients() {
		return clients;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public Machine getRoot() {
		return root;
	}
	//--------------------------------------------IMPORT EXPORT-----------------------------------------
	
	public void exportDataMachines() throws FileNotFoundException {
		String s = File.pathSeparator;
		PrintWriter pw = new PrintWriter(CSV_PATH_MACHINES);
		pw.print("Nombre" + s + "Marca" + s + "Serial" + s + "Número" + s +"Tipo_máquina" + s +"Tipo_Gasolina\n");
		for(int i = 0; i < machines.size(); i++) {
			Machine current = machines.get(i);
			pw.print(current.getName()+s+current.getBrand()+s+current.getSerial()+s+current.getInternalNumber()+s+current.getTypeMachine()+s+current.getTypeGasoline()+"\n");
		}
		pw.close();
	}
	
	public void exportDataClients() throws FileNotFoundException {
		String s = File.pathSeparator;
		PrintWriter pw = new PrintWriter(CSV_PATH_CLIENTS);
		pw.print("Name" + s + "Lastname" + s + "Identification" + s + "Address" + s +"Phone\n");
		for(int i = 0; i < clients.size(); i++) {
			Client current = clients.get(i);
			pw.print(current.getName()+s+current.getLastname()+s+current.getId()+s+current.getAddress()+s+current.getPhone()+"\n");
		}
		pw.close();
	}
	
	public void importDataClient() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(CSV_PATH_CLIENTS));
		String line = br.readLine();
		String [] info;
		line = br.readLine();
		clients.clear();
		while(line != null) {
			info = line.split(";");
			clients.add(new Client(info[0], info[1], info[2], info[3], info[4]));
			line = br.readLine();
		}
		br.close();
	}
	
	public void importDataMachines() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(CSV_PATH_MACHINES));
		String line = br.readLine();
		String [] info;
		line = br.readLine();
		clients.clear();
		while(line != null) {
			info = line.split(";");
			machines.add(new Machine(info[0], info[1], info[2], Integer.parseInt(info[3]), info[4], info[5]));
			line = br.readLine();
		}
		br.close();
	}

	// ----------------------------------------------SERIALIZATION--------------------------------------

	// -----------------------------SAVE

	public void saveUsers() throws IOException {
		FileOutputStream fos = new FileOutputStream("data/Saved_users.va");
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(users);
		out.close();
	}

	public void saveEmployees() throws IOException {
		FileOutputStream fos = new FileOutputStream("data/Saved_employees.va");
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(employee);
		out.close();
	}

	public void saveClients() throws IOException {
		FileOutputStream fos = new FileOutputStream("data/Saved_clients.va");
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(clients);
		out.close();
	}

	/*public void saveMachines() throws IOException {
		FileOutputStream fos = new FileOutputStream("data/Saved_machines.va");
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(machines);
		out.close();
	}*/
	// -----------------------------LOAD-----------------------------

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
		File cload = new File("data/Saved_clients.va");
		if (cload.exists()) {
			FileInputStream fis = new FileInputStream(cload);
			ObjectInputStream input = new ObjectInputStream(fis);
			clients = (ArrayList<Client>) input.readObject();
			input.close();
		}
		/*File mload = new File("data/Saved_machines.va");
		if (mload.exists()) {
			FileInputStream fis = new FileInputStream(mload);
			ObjectInputStream input = new ObjectInputStream(fis);
			machines = (ArrayList<Machine>) input.readObject();
			input.close();
		}*/
	}

	// ---------------------------------------------------------------------------------------------------------------
	
	/**
	 * addUser
	 * pre:
	 * pos: adds a new user to a list in an ordered way
	 * @param user the new user
	 * @throws IOException an IOException
	 */
	
	public void addUser(User user) throws IOException {
		if (users.isEmpty()) {
			users.add(user);
		} else {
			int c = 0;
			while (c <= users.size() && comparatorAddUser(user.getUserName(), (users.get(c)).getUserName()) > 1) {
				c++;
			}
			users.add(c, user);
		}
		saveUsers();
	}
	
	/**
	 * addEmployee
	 * pre:
	 * pos: adds a new employee to a list in an ordered way
	 * @param employee the new employee
	 * @throws IOException 
	 */

	public void addEmployee(Employee newEmployee) throws IOException {
		if (employee.isEmpty()) {
			employee.add(newEmployee);
		} else {
			int i = 0;
			while (i < employee.size()
					&& comparatorAddEmployee(newEmployee.getLastname(), employee.get(i).getLastname()) > 1) {
				i++;
			}
			employee.add(i, newEmployee);
		}
		saveEmployees();
	}

	/**
	 * addClient
	 * pre:
	 * pos: adds a new client to a list in an ordered way
	 * @param client the new client
	 * @throws IOException  
	 */
	
	public void addClient(Client client) throws IOException {
		if (clients.isEmpty()) {
			clients.add(client);
		} else {
			int i = 0;
			while (i < clients.size() && comparatorAddClient(client.getId(), clients.get(i).getId()) > 1) {
				i++;
			}
			clients.add(i, client);
		}
		saveClients();
	}

	// ------------------------------------------------------------------------------------------------

	/**
	 * comparatorAddUser
	 * pre: called by addUser
	 * pos: compares usernames for addUser
	 * @param username1 current user
	 * @param username2 new username
	 * @return int username1.compareToIgnoreCase(username2)
	 */
	
	private int comparatorAddUser(String username1, String username2) {
		return username1.compareToIgnoreCase(username2);
	}

	/**
	 * comparatorAddEmployee
	 * pre:
	 * pos: compares names 
	 * @param name1 current employees name
	 * @param name2 new employees name
	 * @return int name1.compareToIgnoreCase(name2);
	 */
	
	private int comparatorAddEmployee(String name1, String name2) {
		return name1.compareToIgnoreCase(name2);
	}
	
	/**
	 * comparatorAddClient
	 * pre:
	 * pos: compares names
	 * @param name1 current client name
	 * @param name2 new client name
	 * @return int name1.compareToIgnoreCase(name2);
	 */

	private int comparatorAddClient(String name1, String name2) {
		return name1.compareToIgnoreCase(name2);
	}
	// ------------------------------------------------------------------------------------------------

	public User binarySearchUser(String username) {
		User userSeek = null;
		if (users.size() > 0) {
			boolean find = false;
			int in = 0;
			int fin = users.size();

			while (in <= fin && !find) {
				int pos = (int) Math.floor((in + fin) / 2);
				if (pos != users.size()) {
					String el = users.get(pos).getUserName();
					int compar = username.compareToIgnoreCase(el);
					if (compar == 0) {
						userSeek = users.get(pos);
						find = true;
					} else if (compar < 0) {
						fin = pos - 1;
					} else if (compar > 0) {
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
						string = ("Esta es la informcaion del empleado : " + employeeSeek.getName() + "\n"
								+ employeeSeek.getLastname() + " " + employeeSeek.getId() + " "
								+ employeeSeek.getShirtSize() + " " + employeeSeek.getPantsSize() + " "
								+ employeeSeek.getShoesSize() + " " + employeeSeek.getBirthDay() + " "
								+ employeeSeek.getEntryDate());
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

	public Client binarySearchClient(String identification) throws ClientNotExistsException {
		Client client = null;
		if (clients.size() > 0) {
			boolean find = false;
			int in = 0;
			int fin = clients.size();
			while (in <= fin && !find) {
				int pos = (int) Math.floor((in + fin) / 2);
				if (pos != clients.size()) {
					int compar = 0;
					String el = clients.get(pos).getId();
					compar = identification.compareToIgnoreCase(el);
					if (compar == 0) {
						client = clients.get(pos);
						find = true;
					} else if (compar < 0) {
						fin = pos - 1;
					} else if (compar > 0) {
						in = pos + 1;
					}
				}
			}
		}
		if(client == null) {
			throw new ClientNotExistsException();
		}
		return client;
	}
	
	public Client searchClient(String name) {
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getName().equalsIgnoreCase(name)) {
				return clients.get(i);
			}
		}
		return null;
	}
	
	public Machine searchMachineSimple(String name) {
		for (int i = 0; i < machines.size(); i++) {
			if(machines.get(i).getName().equalsIgnoreCase(name)) {
				return machines.get(i);
			}
		}
		return null;
	}
	// ---------------------------------------------------------------------------------

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

	// ----------------------------------------------------------------------
	public String binaryDeleteUser(String username) throws IOException {
		String string = "";
		if (users.size() > 0) {
			boolean find = false;
			int in = 0;
			int fin = users.size();

			while (in <= fin && !find) {
				int pos = (int) Math.floor((in + fin) / 2);
				if (pos != users.size()) {
					String el = users.get(pos).getUserName();
					int compar = username.compareToIgnoreCase(el);
					if (compar == 0) {
						string = "El siguiene usuario se ha eliminado del sistema :\n" + toStringUser(users.get(pos));
						users.remove(pos);
						saveUsers();
						find = true;
					} else if (compar < 0) {
						fin = pos - 1;
					} else if (compar > 0) {
						in = pos + 1;
					}
				}
			}
		}
		return string;
	}

	public String binaryDeleteEmployee(String entryDate) throws IOException {
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
						string = "El siguiene empleado se ha eliminado del sistema :\n" + (employee.get(pos).getName()
								+ " " + employee.get(pos).getLastname() + " " + employee.get(pos).getId() + " "
								+ employee.get(pos).getShirtSize() + " " + employee.get(pos).getPantsSize() + " "
								+ employee.get(pos).getShoesSize() + " " + employee.get(pos).getBirthDay() + " "
								+ employee.get(pos).getEntryDate());
						employee.remove(pos);
						saveEmployees();
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

	public String binaryDeleteCliente(String id) throws IOException {
		String string = "";
		if (clients.size() > 0) {
			boolean find = false;
			int in = 0;
			int fin = clients.size();

			while (in <= fin && !find) {
				int pos = (int) Math.floor((in + fin) / 2);
				if (pos != clients.size()) {
					String el = clients.get(pos).getId();
					int compar = id.compareToIgnoreCase(el);
					if (compar == 0) {
						string = "El siguiene usuario se ha eliminado del sistema :\n"
								+ toStringClient(clients.get(pos));
						clients.remove(pos);
						saveClients();
						find = true;
					} else if (compar < 0) {
						fin = pos - 1;
					} else if (compar > 0) {
						in = pos + 1;
					}
				}
			}
		}
		return string;
	}
	
	public String binaryDeleteMachine(String username) throws IOException {
		String string = "";
		if (machines.size() > 0) {
			boolean find = false;
			int in = 0;
			int fin = machines.size();

			while (in <= fin && !find) {
				int pos = (int) Math.floor((in + fin) / 2);
				if (pos != machines.size()) {
					String el = machines.get(pos).getName();
					int compar = username.compareToIgnoreCase(el);
					if (compar == 0) {
						machines.remove(pos);
						//saveMachines();
						find = true;
					} else if (compar < 0) {
						fin = pos - 1;
					} else if (compar > 0) {
						in = pos + 1;
					}
				}
			}
		}
		return string;
	}

	// -----------------------------------------------------------------------
	public String toStringUser(User user) {
		return "Esta es la informcaion del usuario :" + user.getUserName() + " \n" + user.getName() + " "
				+ user.getLastname() + " " + user.getId() + " " + user.getUserName();
	}

	public String toStringClient(Client client) {
		return "Esta es la informcaion del Cliente :" + " \n" + client.getName() + " " + client.getLastname() + " "
				+ client.getId() + " " + client.getAddress() + " " + client.getPhone();
	}

	// ---------------------------------BINARY
	// TREE---------------------------------------

	public void listToTree() throws IOException {
		for (int i = 0; i < machines.size(); i++) {
			machines.get(i);
			addCartBinaryTree(root);
		}
	}
	
	
	public void addCartBinaryTree(Machine newMachine) throws IOException {
		if (root == null) {
			root = newMachine;
			machines.add(newMachine);
			inorden(root);
			preorden(root);
			//saveMachines();
		} else {
			addCartBinaryTree(root, newMachine);
		}
	}

	private void addCartBinaryTree(Machine current, Machine newMachine) throws IOException {
		if (current.getName().compareToIgnoreCase(newMachine.getName()) < 1) {
			if (current.getLeft() == null) {
				current.setLeft(newMachine);
				machines.add(newMachine);
				//saveMachines();
				newMachine.setFather(current);
			} else {
				addCartBinaryTree(current.getLeft(), newMachine);
			}
		} else {
			if (current.getRight() == null) {
				current.setRight(newMachine);
				machines.add(newMachine);
				//saveMachines();
				newMachine.setFather(current);
			} else {
				addCartBinaryTree(current.getRight(), newMachine);
			}
		}
	}

	public Machine searchMachine(String name) {
		return searchMachine(root, name);
	}

	private Machine searchMachine(Machine current, String name) {
		if (current == null || current.getName().equalsIgnoreCase(name)) {
			return current;
		} else {
			if (name.compareToIgnoreCase(current.getName()) < 1) {
				return searchMachine(current.getLeft(), name);
			} else {
				return searchMachine(current.getRight(), name);
			}
		}
	}
	
	private void inorden(Machine current) throws IOException {
	    if (current != null) {
	        inorden(current.getLeft());
	        inorden(current.getRight());  
	    }
	}
	
	private void preorden(Machine current) {
	    if (current != null) {
	    	System.out.println(current);
	    	preorden(current.getLeft());
	    	preorden(current.getRight());
	    }
	    
	}
	
	public void addUserBinaryTree(User user) throws IOException {
		if (rootU == null) {
			rootU = user;
			inorden(root);
			preorden(root);
			//saveMachines();
		} else {
			addUserBinaryTree(rootU, user);
		}
	}

	private void addUserBinaryTree(User current, User user) throws IOException {
		if (current.getName().compareToIgnoreCase(user.getName()) < 1) {
			if (current.getLeft() == null) {
				current.setLeft(user);
				user.setFather(current);
			} else {
				addUserBinaryTree(current.getLeft(), user);
			}
		} else {
			if (current.getRight() == null) {
				current.setRight(user);
				user.setFather(current);
			} else {
				addUserBinaryTree(current.getRight(), user);
			}
		}
	}
}
