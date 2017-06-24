package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Exceptions;
import classes.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

public class SignIn implements Initializable {
	@FXML
	Button SignIn;
	@FXML
	TextField user;
	@FXML
	TextField password;
	@FXML
	GridPane sign;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		password.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		sign.setBackground(new Background(Loading.myBI));
	}
	public void signIn(Event event){
		String username=user.getText(),pass=password.getText();
		if(username!=null&&pass!=null){
			User log=DB.getUser(username);
			if(log!=null){
				if(log.getPassword().equals(pass))
					application.SignIn.OpenWindowFor(log);
				else
					System.out.println("wrong password");
			}else{
				Exceptions.showInfo("Not user");
				new application.SignIn();
			}
		}
	}
	public void worker(){
		application.SignIn.OpenWindowForWorker();
	}

}
