package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Backup;
import application.ControlPanel;
import classes.Exceptions;
import classes.Group;
import classes.Normal;
import classes.Source;
import classes.Test;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ControlePanel implements Initializable{
	@FXML
	Label editTestID,addTestID,editGroupId;
	@FXML
	Button addTest,plusNormal,editTestSave,editInfo,addGroupSave,editInfoSave;
	@FXML
	TextField hours,editTestHours,editTestPrice,editTestName,editTestComment,
	price,comment,name,normalComment,normalFrom,normalTo,age,addGroupName,addGroupSearch,
	editGroupName,editGroupPrice,editInfoUsername,newSourceEditName,newSourceEditDiscount,
	newSourceName,newSourceDiscount,testsTestSearch,groupssearch,editGroupSearchTest;
	@FXML
	PasswordField editInfoPassword;
	@FXML
	TabPane pan;
	@FXML
	Tab tab6,tab7;
	@FXML
	TableView<Test> testsTable,addGroupTestTable,addGroupTable,editGroupTable,editGroupTestTable;
	@FXML
	TableView<Group> groupsTable;
	@FXML
	TableColumn<Test, Long> tableTestID,groupTestTableTestID,groupTableTestID,groupsGroupId;
	@FXML
	TableColumn<Test, String>tableTestName,tableTestEdit,tableTestDelete,groupTestTableTestName,groupTableTestName,
	groupsGroupName,groupsGroupDelete,groupsGroupEdit,editGroupTableName,editGroupTestName;
	@FXML
	TableColumn<Test, Float>tableTestPrice,tableTestHours,groupsGroupPrice,groupsGroupHours,groupsGroupTests;
	@FXML
	GridPane editInfoGrid,addSourceGrid,editSourceGrid;
	@FXML
	VBox addVBox,editVBox;
	@FXML
	MenuButton addTestAgeType0,addTestGender0;
	@FXML 
	TableView<Source> SourceTable;
	@FXML TableColumn<Source,Integer>sourceIdCol;
	@FXML TableColumn<Source,String>sourceNameCol,sourceEditCol,sourceDeleteCol;
	@FXML TableColumn<Source,Float>sourceDiscountCol;
	@FXML GridPane tab1l;
	@FXML SplitPane tab2l,tab6l,tab8l;
	@FXML BorderPane tab3l,tab4l,tab5l,tab7l;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tab1l.setBackground(new Background(Loading.myBI));
		tab2l.setBackground(new Background(Loading.myBI));
		tab3l.setBackground(new Background(Loading.myBI));
		tab4l.setBackground(new Background(Loading.myBI));
		tab5l.setBackground(new Background(Loading.myBI));
		tab6l.setBackground(new Background(Loading.myBI));
		tab7l.setBackground(new Background(Loading.myBI));
		tab8l.setBackground(new Background(Loading.myBI));
		hours.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		price.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		editTestHours.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		editTestPrice.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		editGroupPrice.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		editInfoUsername.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		editInfoPassword.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		name.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		comment.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		addGroupName.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		addGroupSearch.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		testsTestSearch.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		groupssearch.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		editTestName.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		editTestComment.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		editTestHours.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		editTestPrice.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		editGroupName.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		editGroupPrice.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		editGroupSearchTest.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		newSourceName.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		newSourceEditName.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		newSourceDiscount.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		newSourceEditDiscount.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
	}


	public void logout(Event event){
		ControlPanel.logout();
	}
	
	private boolean b=true,firstCall=true,firstCall2=true,firstCall4=true,firstCall6=true;
	private String normalToText="",normalFromText="",ageToText="",ageFromText="",commentText="",ageTypeText="Select",sexText="Select";
	private static ArrayList<Test> deleted=new ArrayList<>();
	
	public void change(Event event){
		if(b){
			short cause=(short) pan.getSelectionModel().getSelectedIndex();
			if(cause==1)
				addTestTab();
			else if(cause==2)
				addGroupTab();
			else if(cause==3)
				testsTab();
			else if(cause==4)
				groupsTab();
			else if(cause==5)
				testEditTab();
			else if(cause==6)
				editGroupTab();
			else if(cause==7)
				sourceTab();
			b=false;
		}else{
			b=true;
			if(pan.getSelectionModel().getSelectedIndex()==5)
				editVBox.getChildren().removeAll(editVBox.getChildren());
			tab7.setDisable(true);
			tab6.setDisable(true);
		}
	}
	boolean firstSource=true;Source selectedSource;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void sourceTab() {
		addSourceGrid.setVisible(true);
		editSourceGrid.setVisible(false);
		if(firstSource){
			sourceIdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
			sourceNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
			sourceDiscountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));
			sourceEditCol.setCellValueFactory(new PropertyValueFactory<>("edit"));
			sourceDeleteCol.setCellValueFactory(new PropertyValueFactory<>("delete"));
			SourceTable.setItems(FXCollections.observableList(DB.getAllSources()));
			SourceTable.getSelectionModel().setCellSelectionEnabled(true);
			ObservableList selectedCells = SourceTable.getSelectionModel().getSelectedCells();
			selectedCells.addListener(new ListChangeListener() {
			    @Override
			    public void onChanged(Change c) {
			    	try{
				        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
				        Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
			        	selectedSource=SourceTable.getSelectionModel().getSelectedItem();
				        if(val.toString().equals("Edit")){
							addSourceGrid.setVisible(false);
							editSourceGrid.setVisible(true);
							newSourceEditName.setText(selectedSource.getName());
							newSourceEditDiscount.setText(selectedSource.getDiscount().toString());
				        }
				        else if(val.toString().equals("Delete")){
				        	DB.deleteSource(selectedSource.getId());
				        	Exceptions.showInfo("Done");
				        	SourceTable.setItems(FXCollections.observableList(DB.getAllSources()));
				        }
			    	}catch(Exception e){}
			    }
			});
		}
		
	}
	
	public void saveNewSource(Event event){
		if(Exceptions.isWord(newSourceName.getText())&&Exceptions.isWord(newSourceDiscount.getText())){
			if(DB.saveNewSource(newSourceName.getText(),Float.parseFloat(newSourceDiscount.getText()))){
				Exceptions.showInfo("Done");
				SourceTable.setItems(FXCollections.observableList(DB.getAllSources()));
				newSourceName.setText("");
				newSourceDiscount.setText("");
			}
			else
				Exceptions.showInfo("Source was found");
		}else
			Exceptions.showInfo("fill the fields");
	}
	
	public void saveEditSource(Event event){
		if(Exceptions.isWord(newSourceEditName.getText())&&Exceptions.isWord(newSourceEditDiscount.getText())){
			if(DB.editSourceSource(selectedSource.getId(),newSourceEditName.getText(),Float.parseFloat(newSourceEditDiscount.getText()))){
				Exceptions.showInfo("Done");
				SourceTable.setItems(FXCollections.observableList(DB.getAllSources()));
				addSourceGrid.setVisible(true);
				editSourceGrid.setVisible(false);
				newSourceEditName.setText("");
				newSourceEditDiscount.setText("");
			}
			else
				Exceptions.showInfo("Source was found");
		}else
			Exceptions.showInfo("fill the fields");
	}
	
	public void cancelEditSource(Event event){
		addSourceGrid.setVisible(true);
		editSourceGrid.setVisible(false);
		newSourceEditName.setText("");
		newSourceEditDiscount.setText("");
	}

	private void testEditTab() {
		ArrayList<Normal> normals=DB.getTestNormals(Integer.parseInt(editTestID.getText()));
		int x=normals.size()-1;
		for (int i = 0; i < normals.size(); i++) {
			NoGrid++;
			if(i==x)
				editVBox.getChildren().add(fill(normals.get(i),false));
			else
				editVBox.getChildren().add(fill(normals.get(i),true));
		} 
		if(normals.isEmpty())
			editVBox.getChildren().add(newNormalGrid(false,false));
		editVBox.setSpacing(30);
	}

	private GridPane fill(Normal normal, boolean c) {
		normalToText=normal.getNormalTo().toString();
		normalFromText=normal.getNormalFrom().toString();
		ageToText=normal.getAgeTo().toString();
		ageFromText=normal.getAgeFrom().toString();
		commentText=normal.getComment();
		short a=normal.getAgeType(),s=normal.getSex();
		if(a==0)
			ageTypeText="Year";
		else if(a==1)
			ageTypeText="Month";
		else if(a==2)
			ageTypeText="Day";
		if(s==0)
			sexText="Male";
		else
			sexText="Female";
		GridPane grid=newNormalGrid(false,c);
		normalToText="";
		normalFromText="";
		ageToText="";
		ageFromText="";
		commentText="";
		ageTypeText="Select";
		sexText="Select";
		return grid;
	}

	public void saveTestEdit(Event event){
		if(DB.updateTest(Integer.parseInt(editTestID.getText()),editTestName.getText(),Float.parseFloat(editTestHours.getText()),
				editTestComment.getText(),Float.parseFloat(editTestPrice.getText()))){
			try{
				for (Node node : editVBox.getChildren()) {
					float ageFrom =-1,ageTo=-1,normalFrom=-1,normalTo=-1;
					String comment="-1";
					short age=-1,sex=-1;
					for(Node elem:((GridPane)node).getChildren()){
						int row=GridPane.getRowIndex(elem),col=GridPane.getColumnIndex(elem);
						if(row==0&&col==1){
							String m=((MenuButton) elem).getText();
							if(m.equals("Year"))
								age=0;
							else if(m.equals("Month"))
								age=1;
							else if(m.equals("Day"))
								age=2;
						}else if(row==0&&col==3){
							String m=((MenuButton) elem).getText();
							if(m.equals("Male"))
								sex=0;
							else if(m.equals("Female"))
								sex=1;
						}else if(row==1&&col==1){
							ageFrom=Float.parseFloat(((TextField) elem).getText());
						}else if(row==1&&col==3){
							ageTo=Float.parseFloat(((TextField) elem).getText());
						}else if(row==2&&col==1){
							normalFrom=Float.parseFloat(((TextField) elem).getText());
						}else if(row==2&&col==3){
							normalTo=Float.parseFloat(((TextField) elem).getText());
						}else if(row==3&&col==1){
							comment=((TextArea)elem).getText();
						}
					}
					DB.editNormal(Integer.parseInt(editTestID.getText()),age,ageFrom,ageTo,sex,normalFrom,normalTo,comment);
				}
			}catch(Exception e){}
			Exceptions.showInfo("Done");
			testsTable.refresh();
			pan.getSelectionModel().select(3);
		}
	}
	
	private void editGroupTab() {
		deleted=new ArrayList<>();
		if(firstCall6){
			editGroupTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
			editGroupTestName.setCellValueFactory(new PropertyValueFactory<>("name"));
			setListenerEditGroup();
			firstCall6=false;
		}
		deleted=DB.groupTests(Integer.parseInt(editGroupId.getText()));
		editGroupTable.setItems(FXCollections.observableList(deleted));
		ArrayList<Test>tests=DB.getAllTests();
		tests.removeAll(deleted);
		editGroupTestTable.setItems(FXCollections.observableList(tests));
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setListenerEditGroup() {
		ObservableList tests=editGroupTestTable.getSelectionModel().getSelectedItems();
		tests.addListener(new ListChangeListener() {

			@Override
			public void onChanged(Change c) {
				Test test=editGroupTestTable.getSelectionModel().getSelectedItem();
				if(test!=null){
					deleted.add(test);
					ArrayList<Test>t=DB.getAllTests();
					t.removeAll(deleted);
					editGroupPrice.setText(cost(deleted));
					editGroupTable.setItems(FXCollections.observableList(deleted));
					editGroupTestTable.setItems(FXCollections.observableList(t));
				}
			}
			
		});
		ObservableList chosen=editGroupTable.getSelectionModel().getSelectedItems();
		chosen.addListener(new ListChangeListener() {
			@Override
			public void onChanged(Change c) {
				Test test=editGroupTable.getSelectionModel().getSelectedItem();
				if(test!=null){
					deleted.remove(test);
					ArrayList<Test>t=DB.getAllTests();
					t.removeAll(deleted);
					editGroupPrice.setText(cost(deleted));
					editGroupTable.setItems(FXCollections.observableList(deleted));
					editGroupTestTable.setItems(FXCollections.observableList(t));
				}
			}
		});
	}
	
	private String cost(ArrayList<Test> deleted) {
		Float a = new Float(0);
		for(Test x:deleted){
			a+=x.getPrice();
		}
		return a.toString();
	}
	
	private void addTestTab() {
		addTestID.setText(DB.getNextTestID().toString());
		if(addVBox.getChildren().isEmpty()){
			addVBox.setSpacing(30);
			addVBox.getChildren().add(newNormalGrid(true,false));
			NoGrid++;
		}
	}
	
	private void groupsTab() {
		if(firstCall4){
			groupsGroupHours.setCellValueFactory(new PropertyValueFactory<>("hours"));
			groupsGroupId.setCellValueFactory(new PropertyValueFactory<>("groupId"));
			groupsGroupName.setCellValueFactory(new PropertyValueFactory<>("groupName"));
			groupsGroupPrice.setCellValueFactory(new PropertyValueFactory<>("groupPrice"));
			groupsGroupTests.setCellValueFactory(new PropertyValueFactory<>("tests"));
			groupsGroupDelete.setCellValueFactory(new PropertyValueFactory<>("Delete"));
			groupsGroupEdit.setCellValueFactory(new PropertyValueFactory<>("Edit"));
			setListenerGroup();
			firstCall4=false;
		}
		groupsTable.setItems(FXCollections.observableList(DB.getGroups()));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setListenerGroup() {
		groupsTable.getSelectionModel().setCellSelectionEnabled(true);
		ObservableList selectedCells = groupsTable.getSelectionModel().getSelectedCells();
		selectedCells.addListener(new ListChangeListener() {
		    @Override
		    public void onChanged(Change c) {
		    	try{
			        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
			        Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
			        if(val.toString().equals("Edit")){
			        	Group group=groupsTable.getSelectionModel().getSelectedItem();
			        	editGroupId.setText(group.getGroupId().toString());
			        	editGroupName.setText(group.getGroupName());
			        	editGroupPrice.setText(group.getGroupPrice().toString());
			        	pan.getSelectionModel().select(6);
			        	tab7.setDisable(false);
			        	
			        }
			        else if(val.toString().equals("Delete")){
			        	if(DB.DeleteGroup(groupsTable.getSelectionModel().getSelectedItem().getGroupId()))
			        		Exceptions.showInfo("Deleted");
			        	groupsTable.setItems(FXCollections.observableList(DB.getGroups()));
			        }
		    	}catch(Exception e){}
		    }
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testsTab() {
		if(firstCall){
			tableTestID.setCellValueFactory(new PropertyValueFactory<>("testId"));
			tableTestName.setCellValueFactory(new PropertyValueFactory<>("name"));
			tableTestPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
			tableTestHours.setCellValueFactory(new PropertyValueFactory<>("hoursTaken"));
			tableTestEdit.setCellValueFactory(new PropertyValueFactory<>("Edit"));
			tableTestDelete.setCellValueFactory(new PropertyValueFactory<>("Delete"));
		firstCall=false;
		}
		testsTable.setItems(FXCollections.observableList(DB.getAllTests()));
		testsTable.getSelectionModel().setCellSelectionEnabled(true);
		ObservableList selectedCells = testsTable.getSelectionModel().getSelectedCells();
		selectedCells.addListener(new ListChangeListener() {
		    @Override
		    public void onChanged(Change c) {
		    	try{
			        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
			        Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
			        if(val.toString().equals("Edit")){
			        	Test test=testsTable.getSelectionModel().getSelectedItem();
			        	editTestComment.setText(test.getComment());
			        	editTestHours.setText(test.getHoursTaken().toString());
			        	editTestID.setText(test.getTestId().toString());
			        	editTestName.setText(test.getName());
			        	editTestPrice.setText(test.getPrice().toString());
			        	pan.getSelectionModel().select(5);
			        	tab6.setDisable(false);
			        }
			        else if(val.toString().equals("Delete")){
			        	if(DB.Delete(testsTable.getSelectionModel().getSelectedItem().getTestId()))
			        		Exceptions.showInfo("Deleted");
			        	testsTable.setItems(FXCollections.observableList(DB.getAllTests()));
			        }
		    	}catch(Exception e){}
		    }
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addGroupTab() {
		if(firstCall2){
			groupTestTableTestID.setCellValueFactory(new PropertyValueFactory<>("testId"));
			groupTestTableTestName.setCellValueFactory(new PropertyValueFactory<>("name"));
			groupTableTestID.setCellValueFactory(new PropertyValueFactory<>("testId"));
			groupTableTestName.setCellValueFactory(new PropertyValueFactory<>("name"));
			firstCall2=false;
		}
		addGroupTestTable.setItems(FXCollections.observableList(DB.getAllTests()));
		ObservableList tests=addGroupTestTable.getSelectionModel().getSelectedItems();
		tests.addListener(new ListChangeListener() {

			@Override
			public void onChanged(Change c) {
				Test test=addGroupTestTable.getSelectionModel().getSelectedItem();
				if(test!=null){
					deleted.add(test);
					ArrayList<Test>t=DB.getAllTests();
					t.removeAll(deleted);
					addGroupTable.setItems(FXCollections.observableList(deleted));
					addGroupTestTable.setItems(FXCollections.observableList(t));
				}
			}
			
		});
		ObservableList chosen=addGroupTable.getSelectionModel().getSelectedItems();
		chosen.addListener(new ListChangeListener() {

			@Override
			public void onChanged(Change c) {
				Test test=addGroupTable.getSelectionModel().getSelectedItem();
				if(test!=null){
					deleted.remove(test);
					ArrayList<Test>t=DB.getAllTests();
					t.removeAll(deleted);
					addGroupTable.setItems(FXCollections.observableList(deleted));
					addGroupTestTable.setItems(FXCollections.observableList(t));
				}
			}
			
		});
	}
	
	public void addTest(Event event){
		try{
			Float testhours=Float.parseFloat(hours.getText()),testPrice=Float.parseFloat(price.getText());
			String testName=name.getText(),testComment=comment.getText();
			if(DB.saveNewTest(testName,testPrice,testComment,testhours)){
				try{
					for (Node node : addVBox.getChildren()) {
						float ageFrom =-1,ageTo=-1,normalFrom=-1,normalTo=-1;
						String comment="-1";
						short age=-1,sex=-1;
						for(Node elem:((GridPane)node).getChildren()){
							int row=GridPane.getRowIndex(elem),col=GridPane.getColumnIndex(elem);
							if(row==0&&col==1){
								String m=((MenuButton) elem).getText();
								if(m.equals("Year"))
									age=0;
								else if(m.equals("Month"))
									age=1;
								else if(m.equals("Day"))
									age=2;
							}else if(row==0&&col==3){
								String m=((MenuButton) elem).getText();
								if(m.equals("Male"))
									sex=0;
								else if(m.equals("Female"))
									sex=1;
							}else if(row==1&&col==1){
								ageFrom=Float.parseFloat(((TextField) elem).getText());
							}else if(row==1&&col==3){
								ageTo=Float.parseFloat(((TextField) elem).getText());
							}else if(row==2&&col==1){
								normalFrom=Float.parseFloat(((TextField) elem).getText());
							}else if(row==2&&col==3){
								normalTo=Float.parseFloat(((TextField) elem).getText());
							}else if(row==3&&col==1){
								comment=((TextArea)elem).getText();
							}
						}
						DB.saveNormal(Integer.parseInt(addTestID.getText()),age,ageFrom,ageTo,sex,normalFrom,normalTo,comment);
					}
				}catch(Exception e){}
				Exceptions.showInfo("Done");
				addVBox.getChildren().removeAll(addVBox.getChildren());
				newNormalGrid(true,false);
				name.setText("");
				comment.setText("");
				hours.setText("");
				price.setText("");
				addTestID.setText(DB.getNextTestID().toString());
				if(addVBox.getChildren().isEmpty()){
					addVBox.setSpacing(30);
					addVBox.getChildren().add(newNormalGrid(true,false));
					NoGrid++;
				}
			}
			else
				Exceptions.showInfo("This test was saved before");
		}catch(Exception e){
			Exceptions.showInfo(e.getMessage());
		}
	}
	
	public void saveGroup(Event event){
		String name=addGroupName.getText();
		if(Exceptions.isWord(name)){
			if(DB.saveNewGroup(name,deleted)){
				Exceptions.showInfo("Done");
				addGroupName.setText("");
				deleted=new ArrayList<>();
				addGroupTable.setItems(FXCollections.observableList(deleted));
				addGroupTestTable.setItems(FXCollections.observableList(DB.getAllTests()));
			}
			else
				Exceptions.showInfo("Group was found");
		}else{
			Exceptions.showInfo("fill the fields");
		}
	}
	
	public void editGroupSave(Event event){
		if(DB.editGroup(Integer.parseInt(editGroupId.getText()),editGroupName.getText(),Float.parseFloat(editGroupPrice.getText()),deleted)){
			Exceptions.showInfo("done");
			deleted=new ArrayList<>();
			pan.getSelectionModel().select(4);
			groupsTable.refresh();
			groupsTable.setItems(FXCollections.observableList(DB.getGroups()));
		}else
			Exceptions.showInfo("Something went wrong");
	}
	
	public void editInfo(Event event){
		if(editInfoGrid.isVisible())
			editInfoGrid.setVisible(false);
		else{
			editInfoUsername.setText("");
			editInfoPassword.setText("");
			editInfoGrid.setVisible(true);
		}
	}
	
	public void editInfoSave(Event event){
		if(Exceptions.isWord(editInfoUsername.getText())&&Exceptions.isWord(editInfoPassword.getText())){
			if(DB.updateUser(editInfoUsername.getText(),editInfoPassword.getText())){
				Exceptions.showInfo("Done");
				editInfoGrid.setVisible(false);
			}
		}else{
			Exceptions.showInfo("please enter a valid username and password");
		}
	}
	
	public static short NoGrid=0;
	
	public void addMoreNormal(Event event){
		Button b=(Button) event.getSource();
		String s=b.getId();
		s=s.substring(13);
		NoGrid=Short.parseShort(s);
		if(b.getText().equals("+")){
			if(Exceptions.isEmpty((GridPane)addVBox.lookup("#grid"+NoGrid))){
				Exceptions.showInfo("can't leave fields empty");
			}
			else{
				NoGrid++;
				GridPane grid=newNormalGrid(true,false);
				addVBox.setSpacing(30);
				addVBox.getChildren().add(grid);
				b.setText("-");
			}
		}else
			addVBox.getChildren().remove(addVBox.lookup("#grid"+NoGrid));
	}
	
	private GridPane newNormalGrid(boolean x, boolean c) {
		GridPane grid=new GridPane();
		grid.setId("grid"+NoGrid);
		Label l1=new Label("Age Type"),l2=new Label("Gender"),l3=new Label("Age from"),l4=new Label("Age to"),
				l5=new Label("Normal from"),l6=new Label("Normal to"),l7=new Label("Comment");
		Button b1;
		if(c)
			b1=new Button("-");
		else
			b1=new Button("+");
		b1.setId("addMoreNormal"+NoGrid);
		TextField t1=new TextField(ageFromText),t2=new TextField(ageToText),t3=new TextField(normalFromText),t4=new TextField(normalToText);
		t1.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		t2.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		t3.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		t4.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		TextArea tA=new TextArea(commentText);
		tA.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		t1.setId("addTestAgeFrom"+NoGrid);
		t2.setId("addTestAgeTo"+NoGrid);
		t3.setId("addTestNormalFrom"+NoGrid);
		t4.setId("addTestNormalTo"+NoGrid);
		tA.setId("addTestComment"+NoGrid);
		MenuButton m1=new MenuButton(ageTypeText),m2=new MenuButton(sexText);
		m1.setId("addTestAgeType"+NoGrid);
		m2.setId("addTestGender"+NoGrid);
		l1.setPrefWidth(150); l1.setPrefHeight(31);
		l1.setAlignment(Pos.CENTER);
		l2.setPrefWidth(150); l2.setPrefHeight(31);
		l2.setAlignment(Pos.CENTER);
		l3.setPrefWidth(150); l3.setPrefHeight(31);
		l3.setAlignment(Pos.CENTER);
		l4.setPrefWidth(150); l4.setPrefHeight(31);
		l4.setAlignment(Pos.CENTER);
		l5.setPrefWidth(150); l5.setPrefHeight(31);
		l5.setAlignment(Pos.CENTER);
		l6.setPrefWidth(150); l6.setPrefHeight(31);
		l6.setAlignment(Pos.CENTER);
		l7.setPrefWidth(150); l7.setPrefHeight(31);
		l7.setAlignment(Pos.CENTER);
		b1.setPrefWidth(31); b1.setPrefHeight(31);
		b1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(x)
					addMoreNormal(event);
				else
					editMoreNormal(event);
			}
		});
		t1.setPrefWidth(150); t1.setPrefHeight(31);		t1.setAlignment(Pos.CENTER);
		t2.setPrefWidth(150); t2.setPrefHeight(31);		t2.setAlignment(Pos.CENTER);
		t3.setPrefWidth(150); t3.setPrefHeight(31);		t3.setAlignment(Pos.CENTER);
		t4.setPrefWidth(150); t4.setPrefHeight(31);		t4.setAlignment(Pos.CENTER);
		tA.setPrefWidth(150); tA.setPrefHeight(31);
		javafx.scene.control.MenuItem mi1=new javafx.scene.control.MenuItem("Year");
		javafx.scene.control.MenuItem mi2=new javafx.scene.control.MenuItem("Month");
		javafx.scene.control.MenuItem mi3=new javafx.scene.control.MenuItem("Day");
		javafx.scene.control.MenuItem mi4=new javafx.scene.control.MenuItem("Male");
		javafx.scene.control.MenuItem mi5=new javafx.scene.control.MenuItem("Female");
		m1.getItems().add(mi1);		m1.getItems().add(mi2);		m1.getItems().add(mi3);
		m2.getItems().add(mi4);		m2.getItems().add(mi5);
		mi1.setOnAction(e->m1.setText(mi1.getText()));
		mi2.setOnAction(e->m1.setText(mi2.getText()));
		mi3.setOnAction(e->m1.setText(mi3.getText()));
		mi4.setOnAction(e->m2.setText(mi4.getText()));
		mi5.setOnAction(e->m2.setText(mi5.getText()));
		grid.add(l1, 0, 0);	grid.add(m1, 1, 0);	grid.add(l2, 2, 0);	grid.add(m2, 3, 0);
		grid.add(l3, 0, 1);	grid.add(t1, 1, 1);	grid.add(l4, 2, 1);	grid.add(t2, 3, 1);
		grid.add(l5, 0, 2);	grid.add(t3, 1, 2);	grid.add(l6, 2, 2);	grid.add(t4, 3, 2);
		grid.add(l7, 0, 3);	grid.add(tA, 1, 3);						grid.add(b1, 3, 3);
		b1.setAlignment(Pos.CENTER);
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(3);
		return grid;
	}

	private void editMoreNormal(ActionEvent event) {
		Button b=(Button) event.getSource();
		String s=b.getId();
		s=s.substring(13);
		NoGrid=Short.parseShort(s);
		if(b.getText().equals("+")){
			if(Exceptions.isEmpty((GridPane)editVBox.lookup("#grid"+NoGrid))){
				Exceptions.showInfo("can't leave fields empty");
			}
			else{
				NoGrid++;
				GridPane grid=newNormalGrid(false,false);
				editVBox.setSpacing(30);
				editVBox.getChildren().add(grid);
				b.setText("-");
			}
		}else
			editVBox.getChildren().remove(editVBox.lookup("#grid"+NoGrid));
	}
	
	public void menuChanges(Event event){
		addTestGender0.setText(((javafx.scene.control.MenuItem) event.getSource()).getText());
	}
	
	public void ageChanges(Event event){
		addTestAgeType0.setText(((javafx.scene.control.MenuItem) event.getSource()).getText());
	}
	
	public void searchTestAdd(Event event){
		ArrayList<Test>tests=DB.getAllTests(((TextField)event.getSource()).getText());
		tests.removeAll(deleted);
		addGroupTestTable.setItems(FXCollections.observableList(tests));
	}
	
	public void searchTestEdit(Event event){
		ArrayList<Test>tests=DB.getAllTests(((TextField)event.getSource()).getText());
		tests.removeAll(deleted);
		editGroupTestTable.setItems(FXCollections.observableList(tests));
	}
	
	public void searchTest(Event event){
		testsTable.setItems(FXCollections.observableList(DB.getAllTests(((TextField)event.getSource()).getText())));
	}
	
	public void searchGroup(Event event){
		groupsTable.setItems(FXCollections.observableList(DB.getAllGroups(((TextField)event.getSource()).getText())));
	}
	
	public void backups(Event event){
		new Backup();
	}
}
