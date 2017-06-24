package controllers;

import classes.Main;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Loading {
	
	public static BackgroundImage myBI= new BackgroundImage(new Image("/design/mov.jpg",Main.window.getWidth(),Main.window.getHeight(),false,true),
		BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
				true, true,true, true));
	public static Scene load(){
		myBI=new BackgroundImage(new Image("/design/mov.jpg",Main.window.getWidth(),Main.window.getHeight(),false,true),
			BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
					true, true,true, true));
		AnchorPane pane=new AnchorPane();
		pane.setBackground(new Background(myBI));
		return new Scene(pane,Main.window.getWidth(),Main.window.getHeight());
	}
}