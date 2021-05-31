package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.RentaEquiposDelValle;

public class RentaGUI {

	private RentaEquiposDelValle rentaEquipos;
	private String currentUser;

	public RentaGUI() {
		rentaEquipos = new RentaEquiposDelValle();
	}
	
	public void initialize () throws ClassNotFoundException, IOException {
		rentaEquipos.load();
	}
	
	//------------------------------------------LOGIN INTERFACE----------------------------------

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
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void loginUser(ActionEvent event) throws IOException {
    	if(txtUsername.getText().equals(rentaEquipos.searchUserName(txtUsername.getText()))) {
    		if(pfPassword.getText().equals(rentaEquipos.searchUserPassword(pfPassword.getText()))) {
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
				stage.setTitle("La casa Dorada");
				// stage.setResizable(false);
				stage.show();

			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Wrong username or password");
				alert.setHeaderText("Wrong username or password");
				alert.setContentText("Wrong username or password");
				alert.showAndWait();
				txtUsername.clear();
				pfPassword.clear();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Wrong username or password");
			alert.setHeaderText("Wrong username or password");
			alert.setContentText("Wrong username or password");
			alert.showAndWait();
			txtUsername.clear();
			pfPassword.clear();
		}
    	currentUser_Username.setText(currentUser);
	}

	//------------------------------------------Add new user GUI-------------------------------------------
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
    
  	//add contact in the model        
	rentaEquipos.addUser(userName.getText(),userLastname.getText(),Integer.parseInt(userId.getText()),userUser.getText(),userPassword.getText());
	//clean the fields in the gui       
	userName.setText("");
	userLastname.setText("");
	userId.setText("");
	userUser.setText("");
	userPassword.setText("");
	//show the success message
	statusUser.setText("Agregado!");
    }
	
	//--------------------------------------------------------------INDEX INTERFACE-------------------------------------------------------


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
    void HyperLinkDevolucion(ActionEvent event) {

    }
    
    @FXML
    void hyperLinkRemision(ActionEvent event) {

    }

    @FXML
    void hyperLinkClients(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("users.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent addPane = fxmlLoader.load();
    	
    	changePane.setCenter(addPane);
    }

    @FXML
    void hyperLinkEmployees(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("users.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent addPane = fxmlLoader.load();
    	
    	changePane.setCenter(addPane);
    }

    @FXML
    void hyperLinkUsers(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("users.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent addPane = fxmlLoader.load();
    	
    	changePane.setCenter(addPane);
    }
    

    @FXML
    void hyperLinkInv(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inventory.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent addPane = fxmlLoader.load();
    	
    	changePane.setCenter(addPane);
    }
    
    //---------------------------------------------------------------Inventory--------------------------------------------------------
    
    @FXML
    private TableView<?> tvInv;

    @FXML
    private TableColumn<?, ?> tvType;

    @FXML
    private TableColumn<?, ?> tvName;

    @FXML
    private TableColumn<?, ?> tvNumber;

    @FXML
    private TableColumn<?, ?> tvStatus;

    @FXML
    private TableColumn<?, ?> tvMoreInfo;

    @FXML
    private Label moreInfoLabel;

    @FXML
    private ComboBox<?> InvNavType;

    @FXML
    private TextField InvNavName;

    @FXML
    private ComboBox<?> InvAddType;

    @FXML
    private TextField InvAddName;

    @FXML
    private TextField InvAddNumber;

    @FXML
    private TextField InvAddSerial;

    @FXML
    private Button InvAddAdd;

    @FXML
    void InvAddType(ActionEvent event) {

    }

    @FXML
    void InvNavName(ActionEvent event) {

    }

    @FXML
    void InvNavSearchBtn(ActionEvent event) {

    }

    @FXML
    void InvNavType(ActionEvent event) {

    }
    
    //------------------------------------------------------------USERS-------------------------------------------
    
    @FXML
    private TextField UserNavName;

    @FXML
    private Label UserInfo;

    @FXML
    void UserDelete(ActionEvent event) {

    }

    @FXML
    void UserNavSearch(ActionEvent event) {

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

}
