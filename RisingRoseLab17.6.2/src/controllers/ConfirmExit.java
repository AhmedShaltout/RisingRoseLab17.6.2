package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmExit {

    private static boolean answer=false;
    public static boolean exit(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Exit");
        window.setMinWidth(300);
        Label label = new Label("do you want to close the program?");
        Button yes=new Button("Yes");
        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                answer=true;
                window.close();
            }
        });
        Button no=new Button("No");
        no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                answer=false;
                window.close();
            }
        });
		  window.getIcons().add(new Image("/design/atom5.png"));
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yes,no);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        window.setAlwaysOnTop(true);
        return answer;
    }
    
    

}
