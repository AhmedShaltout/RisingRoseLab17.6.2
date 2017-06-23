package classes;
	
import application.SignIn;
import controllers.Loading;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	public static Stage window;
	@Override
	public void start(Stage window){
		Main.window=window;
		Main.window.setWidth(1269);
		Main.window.setHeight(613);
		Main.window.setScene(Loading.load());
		new SignIn();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
