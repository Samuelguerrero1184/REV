package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import exceptions.ClientNotExistsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Client;
import model.Employee;
import model.Machine;
import model.RentaEquiposDelValle;
import model.User;

public class RentaGUI {

	private RentaEquiposDelValle rentaEquipos;
	private String currentUser;
	private ArrayList<Machine>machines;

	public RentaGUI() {
		rentaEquipos = new RentaEquiposDelValle();
	}

	public void initialize() throws ClassNotFoundException, IOException {
		rentaEquipos.load();
		machines = new ArrayList<>();
	}

	// ------------------------------------------LOGIn
	// INTERFACE----------------------------------

	public ArrayList<Machine> getMachines() {
		return machines;
	}


	private Stage stage;

	@FXML
	private BorderPane mainPane;

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField pfPassword;

	@FXML
	private Button createUserBtn;

	@FXML
	void createUser(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("addUserGUI.fxml"));

		fxmlloader.setController(this);
		Parent menu = fxmlloader.load();
		Scene scene = new Scene(menu);
		stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void loginUser(ActionEvent event) throws IOException {
		if (txtUsername.getText().equals(rentaEquipos.searchUserName(txtUsername.getText()))) {
			if (pfPassword.getText().equals(rentaEquipos.searchUserPassword(pfPassword.getText()))) {
				// loadMenu();

				FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("index.fxml"));

				Stage stage1 = (Stage) mainPane.getScene().getWindow();
				currentUser = txtUsername.getText();

				stage1.close();

				fxmlloader.setController(this);
				Parent menu = fxmlloader.load();
				Scene scene = new Scene(menu);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("RENTA EQUIPOS DEL VALLE S.A.S");
				// stage.setResizable(false);
				stage.show();
				currentUser_Username.setText(currentUser.toUpperCase());
			}

			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Wrong username or password");
				alert.setHeaderText("Wrong username or password");
				alert.setContentText("Wrong username or password");
				alert.showAndWait();
				txtUsername.setText("");
				pfPassword.setText("");
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Wrong username or password");
			alert.setHeaderText("Wrong username or password");
			alert.setContentText("Wrong username or password");
			alert.showAndWait();
			txtUsername.setText("");
			pfPassword.setText("");
		}

	}

	// ------------------------------------------Add new user
	// GUI-------------------------------------------
	@FXML
	private BorderPane addUserPane;

	@FXML
	private TextField userName;

	@FXML
	private TextField userLastname;

	@FXML
	private TextField userId;

	@FXML
	private TextField userPassword;

	@FXML
	private TextField userUser;

	@FXML
	private Label statusUser;

	@FXML
	void addUser(ActionEvent event) throws IOException {
		User user = new User(userName.getText().toUpperCase(), userLastname.getText().toUpperCase(), userId.getText(),
				userUser.getText(), userPassword.getText());
		rentaEquipos.addUser(user);
		userName.setText("");
		userLastname.setText("");
		userId.setText("");
		userUser.setText("");
		userPassword.setText("");
		// show the success message
		statusUser.setText("Agregado!");
	}

	// -----------------------------------INDEX
	// INTERFACE---------------------------------------------------

	@FXML
	private Label currentUser_Username;

	@FXML
	private BorderPane changePane;

	@FXML
	private Hyperlink hyperLinkClients;

	@FXML
	private Hyperlink hyperLinkEmployees;

	@FXML
	private Hyperlink hyperLinkUsers;

	@FXML
	void HyperLinkDevolucion(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("devolucion.fxml"));

		fxmlLoader.setController(this);
		Parent addPane = fxmlLoader.load();

		changePane.setCenter(addPane);
	}

	@FXML
	void hyperLinkRemision(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("remision.fxml"));

		fxmlLoader.setController(this);
		Parent addPane = fxmlLoader.load();

		changePane.setCenter(addPane);
	}

	@FXML
	void hyperLinkClients(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clients.fxml"));

		fxmlLoader.setController(this);
		Parent addPane = fxmlLoader.load();

		changePane.setCenter(addPane);
		initializeTvClients();
	}

	@FXML
	void hyperLinkEmployees(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employee.fxml"));

		fxmlLoader.setController(this);
		Parent addPane = fxmlLoader.load();

		changePane.setCenter(addPane);
		initializeTvEmployees();
	}

	@FXML
	void hyperLinkUsers(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("users.fxml"));

		fxmlLoader.setController(this);
		Parent addPane = fxmlLoader.load();

		changePane.setCenter(addPane);
		initializeTvUsers();
	}

	@FXML
	void hyperLinkInv(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inventory.fxml"));

		fxmlLoader.setController(this);
		Parent addPane = fxmlLoader.load();

		changePane.setCenter(addPane);
		invAddGas.getItems().add("ACPM");
		invAddGas.getItems().add("GASOLINA");
		invAddGas.getItems().add("ELECTRICO");
		invAddGas.getItems().add("NO APLICA");

		InvAddType.getItems().add("Maquina pesada");
		InvAddType.getItems().add("Maquinas medianas");
		InvAddType.getItems().add("Maquina peque?a");
		InvAddType.getItems().add("Cantidades");
		initializeTvInventory();
	}

	@FXML
	void hyperLinkCobro(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cobro.fxml"));

		fxmlLoader.setController(this);
		Parent addPane = fxmlLoader.load();

		changePane.setCenter(addPane);
	}

	@FXML
	void hyperLinkChangePsw(ActionEvent event) {

	}

	@FXML
	void hyperLinkExportM(ActionEvent event) {
		try {
			rentaEquipos.exportDataMachines();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void hyperLinkImportM(ActionEvent event) {
		try {
			rentaEquipos.importDataMachines();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void hyperLinkExportC(ActionEvent event) {
		try {
			rentaEquipos.exportDataClients();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void hyperLinkImportC(ActionEvent event) {
		try {
			rentaEquipos.importDataClient();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void hyperLinkGraphs(ActionEvent event) {

	}

	// ---------------------------------Inventory-----------------------------------------------

	@FXML
	private TableView<Machine> tvInv;

	@FXML
	private TableColumn<Machine, String> tvType;

	@FXML
	private TableColumn<Machine, String> tvBrand;

	@FXML
	private TableColumn<Machine, String> tvName;

	@FXML
	private TableColumn<Machine, Integer> tvNumber;

	@FXML
	private TableColumn<Machine, String> tvStatus;

	@FXML
	private TableColumn<Machine, String> tvMoreInfo;

	public void initializeTvInventory() {

		ObservableList<Machine> observableList = FXCollections.observableArrayList(rentaEquipos.getMachines());

		tvInv.setItems(observableList);
		tvType.setCellValueFactory(new PropertyValueFactory<Machine, String>("typeMachine"));
		tvBrand.setCellValueFactory(new PropertyValueFactory<Machine, String>("brand"));
		tvName.setCellValueFactory(new PropertyValueFactory<Machine, String>("name"));
		tvNumber.setCellValueFactory(new PropertyValueFactory<Machine, Integer>("internalNumber"));
	}

	@FXML
	private Label moreInfoLabel;

	@FXML
	private ComboBox<String> InvNavType;

	@FXML
	private TextField InvNavName;

	@FXML
	private ComboBox<String> InvAddType;

	@FXML
	private TextField InvAddName;

	@FXML
	private TextField InvAddNumber;

	@FXML
	private TextField InvAddSerial;

	@FXML
	private Button InvAddAdd;

	@FXML
	private TextField InvAddBrand;

	@FXML
	private ComboBox<String> invAddGas;

	@FXML
	void InvAddAdd(ActionEvent event) throws IOException {
		Machine machine = new Machine(InvAddType.getValue(), InvAddBrand.getText(), InvAddName.getText().toUpperCase(),
				Integer.parseInt(InvAddNumber.getText()), InvAddSerial.getText(), invAddGas.getValue());
		rentaEquipos.addCartBinaryTree(machine);
		InvAddBrand.clear();
		InvAddName.clear();
		InvAddNumber.clear();
		InvAddSerial.clear();
	}

	@FXML
	void InvNavSearchBtn(ActionEvent event) {
		Machine machine = rentaEquipos.searchMachine(InvNavName.getText());
		moreInfoLabel.setText("La informacion de : " + machine.getName() + " " + machine.getBrand() + "\n" + "Numero : "
				+ machine.getInternalNumber() + "\n" + "Serial : " + machine.getSerial() + "\n" + "Tipo : "
				+ machine.getTypeMachine() + "\n" + "Tipo de combustible : " + machine.getTypeGasoline());
	}
	
    @FXML
    void deleteMachine(ActionEvent event) throws IOException {
    	rentaEquipos.binaryDeleteMachine(InvNavName.getText());
    }


	// ------------------------------------------------------------USERS-------------------------------------------

	@FXML
	private TextField UserNavName;

	@FXML
	private TableView<User> tvUsers;

	@FXML
	private TableColumn<User, String> tcUserName;

	@FXML
	private TableColumn<User, String> tcUserLastName;

	@FXML
	private TableColumn<User, String> tcUserId;

	@FXML
	private TableColumn<User, String> tcUserUsername;

	public void initializeTvUsers() {
		ObservableList<User> observableList = FXCollections.observableArrayList(rentaEquipos.getUsers());

		tvUsers.setItems(observableList);
		tcUserName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		tcUserLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
		tcUserId.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
		tcUserUsername.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
	}

	@FXML
	private Label UserInfo;

	@FXML
	private Button userDelete;

	@FXML
	void UserDelete(ActionEvent event) throws IOException {
		rentaEquipos.binaryDeleteUser(UserNavName.getText());
	}

	@FXML
	void UserNavSearch(ActionEvent event) {
		if (UserNavName.getText() != null) {
			UserInfo.setText(rentaEquipos.toStringUser(rentaEquipos.binarySearchUser(UserNavName.getText())));
		}
		userDelete.setVisible(true);
	}

	@FXML
	void UserSaveChanges(ActionEvent event) {

	}

	@FXML
	void hyperLinkNewUser(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("addUserGUI.fxml"));

		fxmlloader.setController(this);
		Parent menu = fxmlloader.load();
		Scene scene = new Scene(menu);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	// ------------------------------------------------------------employee-------------------------------------------
	@FXML
	private TextField EmployeeNavName;

	@FXML
	private TableView<Employee> tvEmployees;

	@FXML
	private TableColumn<Employee, String> tcEmployeeName;

	@FXML
	private TableColumn<Employee, String> tcEmployeeLastName;

	@FXML
	private TableColumn<Employee, String> tcEmployeeId;

	@FXML
	private TableColumn<Employee, LocalDate> tcEmployeeBirth;

	@FXML
	private TableColumn<Employee, String> tcEmployeeSizeShirt;

	@FXML
	private TableColumn<Employee, String> tcEmployeeSizePants;

	@FXML
	private TableColumn<Employee, String> tcEmployeeSizeShoes;

	@FXML
	private TableColumn<Employee, LocalDate> tcEmployeeEntryDate;

	@FXML
	private Label employeeInfo;

	public void initializeTvEmployees() {
		ObservableList<Employee> observableList = FXCollections.observableArrayList(rentaEquipos.getEmployee());

		tvEmployees.setItems(observableList);
		tcEmployeeName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		tcEmployeeLastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastname"));
		tcEmployeeId.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
		tcEmployeeBirth.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("birthDay"));
		tcEmployeeSizeShirt.setCellValueFactory(new PropertyValueFactory<Employee, String>("shirtSize"));
		tcEmployeeSizePants.setCellValueFactory(new PropertyValueFactory<Employee, String>("pantsSize"));
		tcEmployeeSizeShoes.setCellValueFactory(new PropertyValueFactory<Employee, String>("shoesSize"));
		tcEmployeeEntryDate.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("entryDate"));
	}

	@FXML
	void EmployeeNavSearch(ActionEvent event) {
		if (EmployeeNavName.getText() != null) {
			employeeInfo.setText(rentaEquipos.binarySearchEmployee(EmployeeNavName.getText()));
		}
		deleteEmployee.setVisible(true);
	}

	@FXML
	private Button deleteEmployee;

	@FXML
	void DeleteEmployee(ActionEvent event) throws IOException {
		employeeInfo.setText(rentaEquipos.binaryDeleteEmployee(EmployeeNavName.getText()));
	}

	private Stage popStage;

	@FXML
	void hyperLinkNewEmployee(ActionEvent event) throws IOException {

		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("addEmployee.fxml"));

		fxmlloader.setController(this);
		Parent menu = fxmlloader.load();
		Scene scene = new Scene(menu);
		popStage = new Stage();
		popStage.setScene(scene);
		popStage.show();

	}

	// --------------------------------Employee pop up
	// window-----------------------------------

	@FXML
	private TextField newEmployeeName;

	@FXML
	private TextField newEmployeeLname;

	@FXML
	private TextField newEmployeeId;

	@FXML
	private TextField newEmployeeShirtSize;

	@FXML
	private TextField newEmployeeSizePants;

	@FXML
	private TextField newEmployeeSizeShoe;

	@FXML
	private DatePicker newEmployeeEntryDate;

	@FXML
	private DatePicker newEmployeeBirthDay;

	@FXML
	void addEmployeeBtn(ActionEvent event) throws NumberFormatException, IOException {
		Employee employee = new Employee(newEmployeeName.getText().toUpperCase(),
				newEmployeeLname.getText().toUpperCase(), newEmployeeId.getText(), newEmployeeBirthDay.getValue(),
				newEmployeeShirtSize.getText(), newEmployeeSizePants.getText(), newEmployeeSizeShoe.getText(),
				newEmployeeEntryDate.getValue());
		rentaEquipos.addEmployee(employee);
		popStage.close();
		FXMLLoader fxmlloader2 = new FXMLLoader(getClass().getResource("employee.fxml"));
		fxmlloader2.setController(this);
		Parent reLoad = fxmlloader2.load();
		changePane.getChildren().clear();
		changePane.setCenter(reLoad);
		initializeTvEmployees();
	}

	@FXML
	void employeeClearBtn(ActionEvent event) {
		newEmployeeId.clear();
		newEmployeeLname.clear();
		newEmployeeName.clear();
	}

	// ------------------------------------------------------------client-------------------------------------------
	@FXML
	private TextField ClientNavName;

	@FXML
	private Label clientsInfo;

	@FXML
	private Button deleteClient;

	@FXML
	void DeleteClient(ActionEvent event) throws IOException {
		rentaEquipos.binaryDeleteCliente(ClientNavName.getText());
	}

	@FXML
	void ClientNavSearch(ActionEvent event) {
		if(ClientNavName.getText() != null) {
			try {
				clientsInfo.setText(rentaEquipos.toStringClient(rentaEquipos.binarySearchClient(ClientNavName.getText())));
			} catch(ClientNotExistsException e) {
				System.err.println(e.getMessage());
			}
		}
		deleteClient.setVisible(true);
	}

	@FXML
	private TableView<Client> tvClients;

	@FXML
	private TableColumn<Client, String> tcClientName;

	@FXML
	private TableColumn<Client, String> tcClientLastName;

	@FXML
	private TableColumn<Client, String> tcClientId;

	@FXML
	private TableColumn<Client, String> tcClientAddress;

	@FXML
	private TableColumn<Client, String> tcClientPhone;

	public void initializeTvClients() {
		ObservableList<Client> observableList = FXCollections.observableArrayList(rentaEquipos.getClients());

		tvClients.setItems(observableList);
		tcClientName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
		tcClientLastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastname"));
		tcClientId.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
		tcClientAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
		tcClientPhone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
	}

	@FXML
	void hyperlinkAddNewClient(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("addClient.fxml"));

		fxmlloader.setController(this);
		Parent menu = fxmlloader.load();
		Scene scene = new Scene(menu);
		popStage = new Stage();
		popStage.setScene(scene);
		popStage.show();
	}

	// -------------------------------------------------------------------------------

	@FXML
	private TextField newClientName;

	@FXML
	private TextField newClientLname;

	@FXML
	private TextField newClientId;

	@FXML
	private TextField newClientPhone;

	@FXML
	private TextField newClientAddress;

	@FXML
	void addClientBtn(ActionEvent event) throws IOException {
		Client client = new Client(newClientName.getText().toUpperCase(), newClientLname.getText().toUpperCase(),
				newClientId.getText(), newClientAddress.getText(), newClientPhone.getText());
		rentaEquipos.addClient(client);
		popStage.close();
	}

	@FXML
	void clientClearBtn(ActionEvent event) {
		newClientAddress.clear();
		newClientId.clear();
		newClientLname.clear();
		newClientName.clear();
		newClientPhone.clear();
	}

	// -----------------------------------------------------R&D-----------------------

	// Remision
	@FXML
	private BorderPane remisionPane;

	@FXML
	private DatePicker rDate;

	@FXML
	private TextField rRazonsocial;

	@FXML
	private TextField rContact;

	@FXML
	private TextField rAddress;

	@FXML
	private TextField rTime;

	@FXML
	private TextField rId;

	@FXML
	private TextField rConstruction;

	@FXML
	private TextField rPhone;

	@FXML
	private TextField rNumber;

	@FXML
	private TableView<Machine> tvR;

	@FXML
	private TableColumn<?, ?> tcRcuantity;

	@FXML
	private TableColumn<Machine, String> tcRbrand;

	@FXML
	private TableColumn<Machine, String> tcRname;

	@FXML
	private TableColumn<Machine, Integer> tcRnumber;

	@FXML
	private TableColumn<?, ?> tcRobservations;

	@FXML
	private TextField searchClientField;

	@FXML
	private Label autoCompleteLabel;

	@FXML
	void rAutoComplete(ActionEvent event) throws ClientNotExistsException {
		if(searchClientField.getText() != null) {
			Client cliente = rentaEquipos.searchClient(searchClientField.getText());
			rRazonsocial.setText(cliente.getName());
			rAddress.setText(cliente.getAddress());
			rId.setText(cliente.getId());
			rPhone.setText(cliente.getPhone());
			LocalDate date = LocalDate.now();
			rDate.setValue(date);
			LocalTime time = LocalTime.now();
			rTime.setText(time.toString());
			Random rand = new Random();
			int randNum = rand.nextInt(100000);
			rNumber.setText(String.valueOf(randNum));

		}
	}

	@FXML
	void searchClientBtn(ActionEvent event) throws ClientNotExistsException {
		if(searchClientField.getText() != null) {
			rentaEquipos.searchClient(searchClientField.getText());
			autoCompleteLabel.setText("Cliente encontrado");
		}else {
		autoCompleteLabel.setText("Cliente no encontrado");
		}
	}

	@FXML
	void rClear(ActionEvent event) {
		rRazonsocial.clear();
		rAddress.clear();
		rId.clear();
		rPhone.clear();
		rDate.setValue(null);
		rTime.clear();
	}

	@FXML
	void rSave(ActionEvent event) {

	}

	@FXML
	void seekClient(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("remisionPane.fxml"));
		FXMLLoader fxmlloader2 = new FXMLLoader(getClass().getResource("clientAutocomplete.fxml"));
		fxmlloader.setController(this);
		fxmlloader2.setController(this);
		Parent menu = fxmlloader.load();
		Parent menu2 = fxmlloader2.load();
		Scene scene = new Scene(menu);
		remisionPane.setCenter(menu2);
		popStage = new Stage();
		popStage.setScene(scene);
		popStage.show();
	}

	@FXML
	void seekInv(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("remisionPane.fxml"));
		FXMLLoader fxmlloader2 = new FXMLLoader(getClass().getResource("machineAutocomplete.fxml"));
		fxmlloader.setController(this);
		fxmlloader2.setController(this);
		Parent menu = fxmlloader.load();
		Parent menu2 = fxmlloader2.load();
		Scene scene = new Scene(menu);
		remisionPane.getChildren().clear();
		remisionPane.setCenter(menu2);
		Stage popStage2 = new Stage();
		popStage2.setScene(scene);
		popStage2.show();
	}

	@FXML
	void remisionPrint(ActionEvent event) {

	}
	
	public void initializeTvRemision() {
		ObservableList<Machine> observableList = FXCollections.observableArrayList(getMachines());

		tvR.setItems(observableList);
		tcRbrand.setCellValueFactory(new PropertyValueFactory<Machine, String>("brand"));
		tcRname.setCellValueFactory(new PropertyValueFactory<Machine, String>("name"));
		tcRnumber.setCellValueFactory(new PropertyValueFactory<Machine, Integer>("internalNumber"));
	}
	
    @FXML
    private TextField searchMachineField;

    @FXML
    void rInsertMachine(ActionEvent event) {
    	if(searchMachineField.getText()!="") {
    	Machine machine = rentaEquipos.searchMachineSimple(searchMachineField.getText());
    	machines.add(machine);
    	initializeTvRemision();
    	}
    }

	// Devolucion

	@FXML
	void devolucionPrint(ActionEvent event) {

	}

	// ------------------------------------------------------COBRO----------------------

	@FXML
	private TextField cobroSearch;

	@FXML
	private Button cobroSearchBtn;

	@FXML
	private Label cobroEstado;

	@FXML
	void generateCobro(ActionEvent event) {

	}

}
