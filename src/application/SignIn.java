package application;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Main;
import classes.User;
import controllers.ConfirmExit;
import controllers.Loading;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.WindowEvent;

	
public class SignIn implements Initializable {
	
	public SignIn(){
		try{
			Parent root =FXMLLoader.load(getClass().getResource("/design/SignIn.fxml"));
			Scene scene = new Scene(root,1269,613);
			Main.window.setScene(scene);
		}catch(Exception e ){
			System.out.println(e.getMessage());
		}
		Main.window.setTitle("RisingRoseLab17.6.2");
		Main.window.getIcons().add(new Image("/design/atom5.png"));
		Main.window.show();
		Main.window.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Scene scene=Main.window.getScene();
				Main.window.setScene(Loading.load());
				event.consume();
				boolean result=ConfirmExit.exit();
		        if(result){
		        	Main.window.close();
		        	System.exit(0);
		        }else
		        	Main.window.setScene(scene);
			}
		});
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public static void OpenWindowFor(User user){
		if(user.getRole()==0){
			new ControlPanel();
		}else
			System.out.println("User");
	}
	
	public static void OpenWindowForWorker(){
		new WorkPanel();
	}
	
}
