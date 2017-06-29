package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ControlPanel;
import application.SignIn;
import classes.Exceptions;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class Backup implements Initializable {
	@FXML
	MenuButton backups;
	@FXML
	DatePicker datePicker;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		backups.getItems().addAll(finder());
	}
	
	public ArrayList<MenuItem> finder(){
        ArrayList<String>files= SystemCreator.getAllDBS();
        ArrayList<MenuItem> items=new ArrayList<>();
        for (String file : files) {
        	MenuItem m=new MenuItem(file);
        	m.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					backups.setText(m.getText());
				}
			});
        	items.add(m);
		}
        return items;
    }
	
	public void userBackup(Event event){
		String db;
		if(!(db=backups.getText()).equals("Select Backup")){
			SystemCreator.changeDatabaseTo(db);
			new SignIn();
		}
		else
			Exceptions.showInfo("select database");
	}
	
	public void createBackup(Event event){
		LocalDate s;
		if((s=datePicker.getValue())!=null)
			SystemCreator.createSystemWithData(s);
		else
			SystemCreator.createSystem();
		new SignIn();
	}
	 public void back(Event event){
		 new ControlPanel();
	 }
}
