package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
	/**
	 * 
	 * @author Samuel Guerrero // Isabella Naranjo
	 * Main Class
	 *
	 */
public class Main extends Application{
	RentaGUI rentaGUI;

	public Main(){
		rentaGUI = new RentaGUI();
			}
	
	
	public static void main(String[] args)  {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RevLoginPane.fxml"));

		fxmlLoader.setController(rentaGUI);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Renta Equipos del Valle S.A.S");
		primaryStage.show();
		
	}

}
