package application;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ControlPanel implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public ControlPanel() {
		try{
			Parent root =FXMLLoader.load(getClass().getResource("/design/controlPanel.fxml"));
			Scene scene = new Scene(root,1269,613);
			Main.window.setScene(scene);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void logout() {
		new SignIn();
	}
}

