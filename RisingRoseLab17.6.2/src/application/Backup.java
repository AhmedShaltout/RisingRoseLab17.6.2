package application;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Backup implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	public Backup(){
		try{
			Parent root =FXMLLoader.load(getClass().getResource("/design/Backup.fxml"));
			Scene scene = new Scene(root,1269,613);
			Main.window.setScene(scene);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}
