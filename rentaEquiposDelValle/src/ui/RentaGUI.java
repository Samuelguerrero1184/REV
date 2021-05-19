package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.RentaEquiposDelValle;

public class RentaGUI {
	private RentaEquiposDelValle rentaEquipos;
	
	public RentaGUI(){
		rentaEquipos = new RentaEquiposDelValle();
	}
	
	
	
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
    void loginUser(ActionEvent event) {

    }
    
    
    //Add new user GUI
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

    }
}
