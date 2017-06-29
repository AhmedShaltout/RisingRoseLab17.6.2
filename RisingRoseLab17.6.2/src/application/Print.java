package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Print implements Initializable{
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	public static Stage win;
	public Print(){
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/design/Print.fxml"));
			Scene scene = new Scene(root,504,690);
	        win = new Stage(StageStyle.UNDECORATED);
	        win.initModality(Modality.APPLICATION_MODAL);	        win.setTitle("Print");
	        win.getIcons().add(new Image("/design/atom5.png"));		win.setScene(scene);
	        win.showAndWait();								        win.setAlwaysOnTop(true);
		} catch (Exception e){e.printStackTrace();}
		
	}
}
