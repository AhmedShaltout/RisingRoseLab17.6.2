package classes;

import controllers.Loading;
import controllers.WorkPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Exceptions {
	private static boolean answer=false;
	
    public static boolean showYesNo(String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        Label label = new Label(message);
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
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yes,no);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
    
    public static void showInfo(String message) {
    	Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        Label label = new Label(message);
        Button ok=new Button("ok");
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, ok);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
	}

	public static boolean isEmpty(GridPane grid) {
			for(Node elem:grid.getChildren()){
				int row=GridPane.getRowIndex(elem),col=GridPane.getColumnIndex(elem);
				if(row==0&&col==1){
					String m=((MenuButton) elem).getText();
					if(!m.equals("Year")&&!m.equals("Month")&&!m.equals("Day"))
						return true;
				}else if(row==0&&col==3){
					String m=((MenuButton) elem).getText();
					if(!m.equals("Male")&&!m.equals("Female"))
						return true;
				}else if(row==1&&col==1){
					try{Float.parseFloat(((TextField) elem).getText());}catch(Exception e){return true;}
				}else if(row==1&&col==3){
					try{Float.parseFloat(((TextField) elem).getText());}catch(Exception e){return true;}
				}else if(row==2&&col==1){
					try{Float.parseFloat(((TextField) elem).getText());}catch(Exception e){return true;}
				}else if(row==2&&col==3){
					try{Float.parseFloat(((TextField) elem).getText());}catch(Exception e){return true;}
				}else if(row==3&&col==1){
					try{String s=((TextArea) elem).getText();if(s.equals(" ")){}}catch(Exception e){return true;}
				}
			}
		return false;
	}
	
	public static EventHandler<KeyEvent> numeric_Validation() {
	    return new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent e) {
	            TextField txt_TextField = (TextField) e.getSource();
	            if (txt_TextField.getText().length() >= 10) {                   
	                e.consume();
	            }
	            if(e.getCharacter().matches("[0-9.]")){ 
	                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
	                    e.consume();
	                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
	                    e.consume(); 
	                }
	            }else{
	                e.consume();
	            }
	        }
	    };
	}
	
	public static EventHandler<KeyEvent> phone_Validation() {
		
	    return new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent keyEvent) {
	        	TextField txt_TextField = (TextField) keyEvent.getSource();
	            if (txt_TextField.getText().length() >= 11) {                   
	            	keyEvent.consume();
	            }
                if (!"0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();

                }
	        }
	    };
	}
	
	public static EventHandler<KeyEvent>letter_Validation() {
		
	    return new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent e) {
	            if(e.getCharacter().matches("[A-Za-z ا-ي0-9+-/*ئ]")){ 
	            }else{
	                e.consume();
	            }
	        }
	    };
	}

	public static boolean notEmpty(String[] s) {
		try{
			for (int i = 0; i < s.length; i++) {
				if(s[i].length()==0)
					return false;
		}
		}catch(Exception e){return false;}
		return true;
	}

	public static boolean isWord(String text) {
		try{
			if(text.equals("Select")||text.length()==0)
				return false;
		}catch(Exception e){return false;}
		return true;
	}

	private static Float money=0f;
	public static void getPaid(Integer processId) {
    	Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        Label label = new Label("How Much to pay?");
        TextField total=new TextField();
        total.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
        total.setAlignment(Pos.CENTER);
        total.setPromptText("Money");
        Button pay=new Button("Pay");
        pay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try{
            		money=Float.parseFloat(total.getText());
                	WorkPanel.pay(processId, money);
            	}catch(Exception e){
            		money=0f;
            	}
                window.close();
            }
        });
        Button cancel=new Button("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				money=0f;
				window.close();
			}
		});
        HBox buttons=new HBox(20,pay,cancel);
        buttons.setAlignment(Pos.CENTER);
        VBox layout = new VBox(20,label,total,buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
	}
	
	public static void getSerial() {
		Stage window = new Stage(StageStyle.UNDECORATED);
	    window.initModality(Modality.APPLICATION_MODAL);
	    window.setMinWidth(300);
	    window.setTitle("Activation");
	    Label label = new Label("Enter the serial code");
	    label.setPadding(new Insets(5));
	    label.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	    TextField code=new TextField();
	    code.setAlignment(Pos.CENTER);
	    code.setPromptText("Serial isn't valid");
	    Button pay=new Button("activate");
	    pay.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	try{
	        		if(code.getText().equals("ArrayList<>();")){
	        			Main.startAll();
	    	            window.close();
	        		}
	        		code.setText("");
	        	}catch(Exception e){
	        	}
	        }
	    });
	    Button cancel=new Button("Cancel");
	    cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				money=0f;
				window.close();
			}
		});
	    HBox buttons=new HBox(20,pay,cancel);
	    buttons.setAlignment(Pos.CENTER);
	    VBox layout = new VBox(20,label,code,buttons);
	    layout.setAlignment(Pos.CENTER);
	    layout.setPadding(new Insets(20));
	    layout.setBackground(new Background(Loading.myBI));
	    Scene scene = new Scene(layout);
	    window.setScene(scene);
	    window.showAndWait();
	}

    
}
