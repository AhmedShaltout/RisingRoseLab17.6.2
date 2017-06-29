package classes;
	
import application.SignIn;
import controllers.DB;
import controllers.SystemCreator;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	public static Stage window;
	@Override
	public void start(Stage window){
		Main.window=window;
		Main.window.setTitle("RisingRoseLab17.6.2");
		Main.window.getIcons().add(new Image("/design/atom5.png"));
		String db;
		if((db=SystemCreator.getSelectedDB())==null){
			Exceptions.getSerial();
		}else{
			DB.setDB(db);
			new SignIn();
			Main.window.show();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static void startAll() {
		String db;
		SystemCreator.createSystembase();
		db=SystemCreator.createSystem();
		DB.setDB(db);
		new SignIn();
		Main.window.show();
	}
}
