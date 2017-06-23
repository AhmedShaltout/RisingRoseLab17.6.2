package controllers;

import classes.Main;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;

public class Loading {
	
	public static BackgroundImage myBI= new BackgroundImage(new Image("/design/mov.jpg",(Main.window.getWidth()+1),(Main.window.getHeight()+1),false,true),
		BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
				true, true,true, true));
	public static Scene load(){
		myBI=new BackgroundImage(new Image("/design/mov.jpg",(Main.window.getWidth()+1),(Main.window.getHeight()+1),false,true),
			BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
					true, true,true, true));
		StackPane pane=new StackPane();
		pane.setMinWidth(1269);
		pane.setMinHeight(614);
		pane.setBackground(new Background(myBI));
		return new Scene(pane);
	}
}



/*
 
		BackgroundImage myBI=new BackgroundImage(new Image("/design/mov.jpg",(Main.window.getWidth()+1),(Main.window.getHeight()+1),false,true),
			BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
			
*/
