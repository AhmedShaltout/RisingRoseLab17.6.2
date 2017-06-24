package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import application.Print;
import classes.Exceptions;
import classes.Group;
import classes.Patient;
import classes.Process;
import classes.Result;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class WorkPanel implements Initializable {
	@FXML
	TabPane pan;
	@FXML
	TextField addPatientName,addPatientAge,addPatientPhone,newProcessPatientSearch,newProcessPrice,
	newProcessReference,newProcessPay,newProcessGroupSearch,newProcessTestSearch,addResultProcessId,
	groupListName,processId,patientListPatientName,testListTestName;
	@FXML
	MenuButton addPatientAgeType,addPatientSource;
	@FXML
	Label addPatientId,newProcessPatientName,newProcessPatientAge,newProcessPatientSource,
	newProcessPatientId,newProcessOutDate;
	@FXML
	TableView<Patient> newProcessPatientTable,patientListTable;
	@FXML
	TableView<Test> newProcessTestTable,addProcessSelectedTestTable,groupListTestTable,testListTable;
	@FXML
	TableView<Result>addResultTestTable,ProcessTableTests;
	@FXML
	TableView<Group> newProcessGroupTable,addProcessSelectedGroupTable,groupTable;
	@FXML 
	TableView<Process> patientHistory;
	@FXML
	TableColumn<Process,Integer>patientHistoryId;
	@FXML
	TableColumn<Process,String>patientHistoryInDate,patientHistoryOutDate,patientHistoryDetails,patientHistoryPrint,patientHistoryPay;
	@FXML
	TableColumn<Process,Float>patientHistoryPrice,patientHistoryPaid;
	@FXML
	TableColumn<Patient,Integer>newProcessPatientIdCol,patientListTableId;
	@FXML
	TableColumn<Patient,String> newProcessPatientCol,patientListTableName;
	@FXML
	TableColumn<Test,Integer>newProcessTestIdCol,addProcessSelectedTestIdCol,groupListTestTableId,testListTableId;
	@FXML
	TableColumn<Test,String> newProcessTestCol,addProcessSelectedTestCol,groupListTestTableName,testListTableName,testListTableComment;
	@FXML
	TableColumn<Test,Float>testListTableHours,testListTablePrice;
	@FXML
	TableColumn<Result,Integer>addResultTestTableId,ProcessTableTestsId;
	@FXML
	TableColumn<Result,String>addResultTestTableName,addResultTestTableValue,ProcessTableTestsName,ProcessTableTestsValue;
	@FXML
	TableColumn<Group,Integer>newProcessGroupIdCol,addProcessSelectedGroupIdCol,groupsGroupId,groupsGroupTests;
	@FXML
	TableColumn<Group,String>newProcessGroupCol,addProcessSelectedGroupCol,groupsGroupName;
	@FXML
	TableColumn<Group,Float>groupsGroupPrice,groupsGroupHours;
	@FXML
	VBox addResultVBox,processVBox;
	@FXML GridPane qq1,qq2;
	@FXML BorderPane qq3,qq4,qq5,qq6,qq7,qq8;
	

	public static  ObservableList<Result> separatedTests;
	public static ArrayList<String> namesGroup;
	public static ArrayList<ArrayList<Result>> groupedTests;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		qq1.setBackground(new Background(Loading.myBI));
		qq2.setBackground(new Background(Loading.myBI));
		qq3.setBackground(new Background(Loading.myBI));
		qq4.setBackground(new Background(Loading.myBI));
		qq5.setBackground(new Background(Loading.myBI));
		qq6.setBackground(new Background(Loading.myBI));
		qq7.setBackground(new Background(Loading.myBI));
		qq8.setBackground(new Background(Loading.myBI));
		addPatientAge.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		addPatientPhone.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.phone_Validation());
		addPatientName.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		newProcessPatientSearch.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		newProcessPrice.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		addResultProcessId.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		newProcessReference.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		newProcessPay.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		newProcessGroupSearch.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		newProcessTestSearch.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		addResultProcessId.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		processId.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.numeric_Validation());
		patientListPatientName.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		testListTestName.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		groupListName.addEventFilter(KeyEvent.KEY_TYPED,Exceptions.letter_Validation());
		addResultProcessId.setOnKeyPressed(addResultListener());
		addResultTestTableValue.setCellValueFactory(new PropertyValueFactory<>("testValue"));
		addResultTestTableName.setCellValueFactory(new PropertyValueFactory<>("testName"));
		addResultTestTableId.setCellValueFactory(new PropertyValueFactory<>("testId"));
		addResultTestTableValue.setCellFactory(TextFieldTableCell.forTableColumn());
		addResultTestTableValue.setOnEditCommit(setEditableCell());
		addResultTestTable.setEditable(true);
		groupsGroupId.setCellValueFactory(new PropertyValueFactory<>("groupId"));
		groupsGroupName.setCellValueFactory(new PropertyValueFactory<>("groupName"));
		groupsGroupPrice.setCellValueFactory(new PropertyValueFactory<>("groupPrice"));
		groupsGroupHours.setCellValueFactory(new PropertyValueFactory<>("hours"));
		groupsGroupTests.setCellValueFactory(new PropertyValueFactory<>("tests"));
		groupTable.getSelectionModel().getSelectedItems().addListener(listChangeListener());
		groupListTestTableId.setCellValueFactory(new PropertyValueFactory<>("testId"));
		groupListTestTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
		testListTableId.setCellValueFactory(new PropertyValueFactory<>("testId"));
		testListTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
		testListTableHours.setCellValueFactory(new PropertyValueFactory<>("hoursTaken"));
		testListTablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		testListTableComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
		patientListTableId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
		patientListTableName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
		patientListTable.getSelectionModel().getSelectedItems().addListener(patientListTableListener());
		patientHistoryId.setCellValueFactory(new PropertyValueFactory<>("processId"));
		patientHistoryInDate.setCellValueFactory(new PropertyValueFactory<>("processInDate"));
		patientHistoryOutDate.setCellValueFactory(new PropertyValueFactory<>("processOutDate"));
		patientHistoryDetails.setCellValueFactory(new PropertyValueFactory<>("processDetails"));
		patientHistoryPrint.setCellValueFactory(new PropertyValueFactory<>("processPrint"));
		patientHistoryPay.setCellValueFactory(new PropertyValueFactory<>("processPay"));
		patientHistoryPrice.setCellValueFactory(new PropertyValueFactory<>("processPrice"));
		patientHistoryPaid.setCellValueFactory(new PropertyValueFactory<>("processPaid"));
		patientHistory.getSelectionModel().setCellSelectionEnabled(true);
		ProcessTableTestsValue.setCellValueFactory(new PropertyValueFactory<>("testValue"));
		ProcessTableTestsName.setCellValueFactory(new PropertyValueFactory<>("testName"));
		ProcessTableTestsId.setCellValueFactory(new PropertyValueFactory<>("testId"));
		ProcessTableTests.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		ObservableList<TablePosition> selectedCells=patientHistory.getSelectionModel().getSelectedCells();
		selectedCells.addListener(new ListChangeListener(){
			@Override
			public void onChanged(Change c) {
				if(selectedCells.size()>0){
					TablePosition tablePosition = (TablePosition) selectedCells.get(0);
			        Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
		        	Process process=patientHistory.getSelectionModel().getSelectedItem();
		        	if(val.toString().equals("Check")){
		        		pan.getSelectionModel().select(4);
		        		ProcessTableTests.getItems().removeAll(ProcessTableTests.getItems());
		        		processVBox.getChildren().removeAll(processVBox.getChildren());
		        		processId.setText(process.getProcessId().toString());
		        		ProcessTableTests.setItems(FXCollections.observableList(DB.getProcessTestsResult(process.getProcessId())));
						ArrayList<ArrayList<Result>>groups=DB.getProcessGroupsResult(process.getProcessId());
						groupsId=DB.groups;
						ArrayList<String> groupsName=DB.groupsName;
						int length=groupsId.size();
						for (int i = 0; i < length; i++) {
							GridPane grid=addGridToVBox(groups.get(i),groupsName.get(i),groupsId.get(i),false);
							processVBox.getChildren().add(grid);
						}
		        	}else if(val.toString().equals("Print")){
		        		Exceptions.showInfo("Will add printer later");
		        	}else if(val.toString().equals("Pay")){
		        		Exceptions.getPaid(process.getProcessId());patientHistory.setItems(FXCollections.observableList(DB.getPatientProcess(x)));
		        		patientHistory.refresh();
		        	}
				}
			}
			
		});
		
	}
	
	public static void pay(Integer processId,Float pay){
		DB.payForProcess(processId,pay);
	}
	private int x=0;
	private ListChangeListener<? super Patient> patientListTableListener() {
		return new ListChangeListener<Patient>(){

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Patient> c) {
				Patient patient;
				if((patient=patientListTable.getSelectionModel().getSelectedItem())!=null){
					x=patient.getPatientId();
					patientHistory.setItems(FXCollections.observableList(DB.getPatientProcess(x)));
				}
			}
			
		};
	}


	private ListChangeListener<? super Group> listChangeListener() {
		return new ListChangeListener<Group>(){

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Group> c) {
				if(c!=null){
					Group group;
					if((group=groupTable.getSelectionModel().getSelectedItem())!=null)
						groupListTestTable.setItems(FXCollections.observableList(DB.groupTests(group.getGroupId())));
				}
			}
			
		};
	}


	///////////////////////Event Handlers///////////////////
	private boolean b=true;
	public void change(Event event){
		if(b){
			short cause=(short) pan.getSelectionModel().getSelectedIndex();
			if(cause==1)
				addPatientTab();
			else if(cause==2)
				newProcess();
			else if(cause==5)
				patienList();
			else if(cause==6)
				testList();
			else if(cause==7)
				groupList();
			b=false;
		}else
			b=true;
	}


	public void searchProcess(Event event) {
		ProcessTableTests.getItems().removeAll(ProcessTableTests.getItems());
		processVBox.getChildren().removeAll(processVBox.getChildren());
		String s=((TextField)event.getSource()).getText();
		if(Exceptions.isWord(s)){
			ArrayList<Result> results=DB.getProcessTestsResult(Integer.parseInt(s));
			if(!results.isEmpty()){
				ProcessTableTests.setItems(FXCollections.observableList(results));
				ArrayList<ArrayList<Result>>groups=DB.getProcessGroupsResult(Integer.parseInt(s));
				groupsId=DB.groups;
				ArrayList<String> groupsName=DB.groupsName;
				int length=groupsId.size();
				for (int i = 0; i < length; i++) {
					GridPane grid=addGridToVBox(groups.get(i),groupsName.get(i),groupsId.get(i),false);
					processVBox.getChildren().add(grid);
				}
			}else{
				Exceptions.showInfo("No such Process");
				processId.setText("");
			}
		}
	}


	private EventHandler<? super KeyEvent> addResultListener() {
		return new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER))
					addResultSearchProcess();
			}
		};
	}
	
	//////////////////////group list/////////////////////
	public void groupListSearch(Event event){
		String s=((TextField)event.getSource()).getText();
		groupListTestTable.getItems().removeAll(groupListTestTable.getItems());
		if(Exceptions.isWord(s)){
			groupTable.setItems(FXCollections.observableList(DB.getAllGroups(s)));
		}else
			groupTable.setItems(FXCollections.observableList(DB.getGroups()));
	}
	
	public void testListSearch(Event event){
		String s=((TextField)event.getSource()).getText();
		if(Exceptions.isWord(s))
			testListTable.setItems(FXCollections.observableList(DB.getAllTests(s)));
		else
			testListTable.setItems(FXCollections.observableList(DB.getAllTests()));
	}

	private boolean firstCall3=true;
	private void patienList() {
		if(firstCall3)
			patientListTable.setItems(FXCollections.observableList(DB.getAllPatients()));
		firstCall3=false;
	}
	
	public void patientListSearch(Event event){
		String s=((TextField)event.getSource()).getText();
		patientHistory.getItems().removeAll(patientHistory.getItems());
		if(Exceptions.isWord(s)){
			patientListTable.setItems(FXCollections.observableList(DB.getPatients(s)));
		}else
			patientListTable.setItems(FXCollections.observableList(DB.getAllPatients()));
	}
	
	
	
	private boolean firstCall2=true;
	private void testList(){
		if(firstCall2)
			testListTable.setItems(FXCollections.observableList(DB.getAllTests()));
		firstCall2=false;
	}
	private boolean firstCall=true;
	private void groupList() {
		if(firstCall)
			groupTable.setItems(FXCollections.observableList(DB.getGroups()));
		firstCall=false;
	}
	
	@SuppressWarnings("unchecked")
	public void printSelected(Event event){
		int index=-1;
		ArrayList<ArrayList<Result>>results=new ArrayList<>();
		ArrayList<String>groupsName=new ArrayList<>();
		for (Node node : processVBox.getChildren()) {
			if(node instanceof GridPane){
				boolean x=false;
				ObservableList<Result>list =null;
				for (Node item : ((GridPane)node).getChildren()) {
					if(x){
						x=false;
						list=((TableView<Result>)item).getSelectionModel().getSelectedItems();
					}else{
						x=true;
						groupsName.add(((Label)item).getText().split(":-")[1]);
					}
				}
				index++;
				results.add(index,new ArrayList<>());
				results.get(index).addAll(list);
			}
		}
		index=-1;
		for (ArrayList<Result> result : results) {
			index++;
			if(!result.isEmpty()){
				System.out.println("The results of : "+groupsName.get(index));
				for(Result r:result){
					System.out.println(r);
				}
				System.out.println("------------------------------------------------");
			}
		}
		System.out.println("Separated tests\n\n");
		ObservableList<Result>results2=ProcessTableTests.getSelectionModel().getSelectedItems();
		for (Result result : results2) {
			System.out.println(result);
		}
		separatedTests=results2;
		namesGroup=groupsName;
		groupedTests=results;
		new Print();
	}
	///////////////////////add patient///////////////////
	
	private void addPatientTab() {
		addPatientId.setText((DB.getNextPatientId()).toString());
		ArrayList<Source>sources=DB.getAllSources();
		for (Source source : sources) {
			addPatientSource.getItems().add(setSourceMenuListener(new MenuItem(source.getName())));
		}
	}
	
	public void logout(Event event){
		application.WorkPanel.logout();
	}
	
	public void addNewPatient(Event event){
		String s[]={addPatientName.getText(),addPatientAge.getText()};
		if(Exceptions.notEmpty(s)&&Exceptions.isWord(addPatientAgeType.getText())){
			int id=Integer.parseInt(addPatientId.getText());
			if(DB.saveNewPatient(id,s[0],addPatientAgeType.getText(),Float.parseFloat(s[1]))){
				if(Exceptions.isWord(addPatientPhone.getText())){
					DB.addPatientPhone(id,addPatientPhone.getText());
				}
				if(Exceptions.isWord(addPatientSource.getText())){
					DB.addPatientSource(id,DB.getSourceID(addPatientSource.getText()));
				}
				Exceptions.showInfo("Done");
				addPatientName.setText("");
				addPatientAge.setText("");
				addPatientAgeType.setText("Select");
				addPatientSource.setText("Select");
				addPatientPhone.setText("");
				addPatientId.setText((DB.getNextPatientId()).toString());
			}else
				Exceptions.showInfo("patient found");
		}else
			Exceptions.showInfo("fill all required fields");
	}
	
	public void ageTypeChanges(Event event){
		addPatientAgeType.setText(((MenuItem)event.getSource()).getText());
	}
	
	public void sourceChange(Event event){
		addPatientSource.setText(((MenuItem)event.getSource()).getText());
	}
	
	private MenuItem setSourceMenuListener(MenuItem menuItem) {
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				addPatientSource.setText(menuItem.getText());
			}
		});
		return menuItem;
	}
	/////////////////////////add result//////////////////////////////
	private ArrayList<Integer> groupsId=new ArrayList<>();
	private int currentProcess=-1;
	private void addResultSearchProcess() {
		groupsId=new ArrayList<>();
		currentProcess=-1;
		addResultTestTable.getItems().removeAll(addResultTestTable.getItems());
		addResultVBox.getChildren().removeAll(addResultVBox.getChildren());
		if(Exceptions.isWord(addResultProcessId.getText())){
			currentProcess=Integer.parseInt(addResultProcessId.getText());
			addResultTestTable.setItems(FXCollections.observableList(DB.getProcessTestsResult(currentProcess)));
			if(addResultTestTable.getItems().size()>0){
				ArrayList<ArrayList<Result>> groups=DB.getProcessGroupsResult(currentProcess);
				groupsId=DB.groups;
				ArrayList<String> groupsName=DB.groupsName;
				int length=groupsId.size();
				for (int i = 0; i < length; i++) {
					GridPane grid=addGridToVBox(groups.get(i),groupsName.get(i),groupsId.get(i),true);
					addResultVBox.getChildren().add(grid);
				}
			}else{
				addResultVBox.getChildren().removeAll(addResultVBox.getChildren());
				Exceptions.showInfo("No such process");
			}
		}else{
			addResultTestTable.getItems().removeAll(addResultTestTable.getItems());
			addResultVBox.getChildren().removeAll(addResultVBox.getChildren());
			Exceptions.showInfo("Enter process Id");
		}
	}
	
	public void saveAddResult(Event event){
		ObservableList<Result> x=addResultTestTable.getItems();
		ArrayList<ArrayList<Result>>groups=getGroupsResult();
		if(DB.saveTestResult(currentProcess,x)&&DB.saveGroupResult(currentProcess,groups,groupsId)){
			Exceptions.showInfo("Done");
			addResultProcessId.setText("");
			addResultTestTable.getItems().removeAll(addResultTestTable.getItems());
			addResultVBox.getChildren().removeAll(addResultVBox.getChildren());
		}
		else
			Exceptions.showInfo("Something went wrong");
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<ArrayList<Result>> getGroupsResult() {
		int index=-1;
		ArrayList<ArrayList<Result>>results=new ArrayList<>();
		for (Node node : addResultVBox.getChildren()) {
			if(node instanceof GridPane){
				boolean x=false;
				ObservableList<Result>list =null;
				for (Node item : ((GridPane)node).getChildren()) {
					if(x){
						x=false;
						list=((TableView<Result>)item).getItems();
					}else
						x=true;
				}
				index++;
				results.add(index,new ArrayList<>());
				results.get(index).addAll(list);
			}
		}
		return results;
	}
	@SuppressWarnings("unchecked")
	private GridPane addGridToVBox(ArrayList<Result> arrayList,String s, Integer integer, boolean c) {
		GridPane grid=new GridPane();
		Label groupId=new Label(integer.toString()+":- "+s);
		TableView<Result> tableView = new TableView<>();
		tableView.setMinHeight(220);
		TableColumn<Result, Integer> id=new TableColumn<>("Test Id");
		id.setCellValueFactory(new PropertyValueFactory<>("testId"));
		id.setPrefWidth(80);
		TableColumn<Result,String> name=new TableColumn<>("Test name");
		name.setCellValueFactory(new PropertyValueFactory<>("testName"));
		name.setPrefWidth(370);
		TableColumn<Result, String> value= new TableColumn<>("Value");
		value.setCellValueFactory(new PropertyValueFactory<>("testValue"));
		value.setPrefWidth(140);
		if(c){
			value.setCellFactory(TextFieldTableCell.forTableColumn());
			value.setOnEditCommit(setEditableCell());
			tableView.setEditable(true);
		}
		tableView.getColumns().addAll(id,name,value);
		grid.add(groupId, 0, 0);	grid.add(tableView, 0, 1);
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(5);
		tableView.setItems(FXCollections.observableList(arrayList));
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		return grid;
	}
	
	private EventHandler<CellEditEvent<Result, String>> setEditableCell(){
		return new EventHandler<CellEditEvent<Result, String>>() {
	        @Override
	        public void handle(CellEditEvent<Result, String> t) {
	            ((Result) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTestValue(t.getNewValue());
	        }
	    };
	}
	
	
	
	
	/////////////////////////add process////////////////////////////////
	
	

	private void newProcess() {
		newProcessPatientTable();
		newProcessGroupTable();
		newProcessTestTable();
	}

	private Float totalPrice=0f;
	private ArrayList<Test>selectedTests=new ArrayList<>();
	private void newProcessTestTable() {
		if(first){
			first=false;
			newProcessTestCol.setCellValueFactory(new PropertyValueFactory<>("name"));
			newProcessTestIdCol.setCellValueFactory(new PropertyValueFactory<>("testId"));
			addProcessSelectedTestCol.setCellValueFactory(new PropertyValueFactory<>("name"));
			addProcessSelectedTestIdCol.setCellValueFactory(new PropertyValueFactory<>("testId"));
			newProcessTestTable.setItems(FXCollections.observableList(DB.getAllTests()));
			newProcessTestTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Test>(){

				@Override
				public void onChanged(Change<? extends Test> c) {
					
					Test test;
					if((test=newProcessTestTable.getSelectionModel().getSelectedItem())!=null){
						ArrayList<Test>allTests=DB.getAllTests();
						selectedTests.add(test);
						totalPrice+=test.getPrice();
						allTests.removeAll(selectedTests);
						addProcessSelectedTestTable.setItems(FXCollections.observableList(selectedTests));
						newProcessTestTable.setItems(FXCollections.observableList(allTests));
						calculateDiscount();
						setOutDate();
					}
				}
				
			});
			
			addProcessSelectedTestTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Test>(){

				@Override
				public void onChanged(Change<? extends Test> c) {
					
					Test test;
					if((test=addProcessSelectedTestTable.getSelectionModel().getSelectedItem())!=null){
						ArrayList<Test>allTests=DB.getAllTests();
						selectedTests.remove(test);
						totalPrice-=test.getPrice();
						allTests.removeAll(selectedTests);
						newProcessTestTable.setItems(FXCollections.observableList(allTests));
						addProcessSelectedTestTable.setItems(FXCollections.observableList(selectedTests));
						calculateDiscount();
						setOutDate();
					}
				}
				
			});
		}
	}

	protected void calculateDiscount() {
		Float discount=totalPrice-(totalPrice*DB.getSourceDiscount(newProcessPatientSource.getText()));
		newProcessPrice.setText(discount.toString());
	}

	private ArrayList<Group>selectedGroups=new ArrayList<>();
	private boolean first=true;
	private void newProcessGroupTable() {
		if(first){
			newProcessGroupCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
			addProcessSelectedGroupCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
			newProcessGroupIdCol.setCellValueFactory(new PropertyValueFactory<>("groupId"));
			addProcessSelectedGroupIdCol.setCellValueFactory(new PropertyValueFactory<>("groupId"));
			newProcessGroupTable.setItems(FXCollections.observableList(DB.getGroups()));
			
			newProcessGroupTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Group>() {

				@Override
				public void onChanged(Change<? extends Group> c) {
					Group group=newProcessGroupTable.getSelectionModel().getSelectedItem();
					
					if(group!=null){
						ArrayList<Group>allGroups=DB.getGroups();
						for (Group group2 : allGroups) {
							if(group.equals(group2))
								group=group2;
						}
						selectedGroups.add(group);
						totalPrice+=group.getGroupPrice();
						allGroups.removeAll(selectedGroups);
						newProcessGroupTable.setItems(FXCollections.observableList(allGroups));
						addProcessSelectedGroupTable.setItems(FXCollections.observableList(selectedGroups));
						calculateDiscount();
						setOutDate();
					}
				}
			});
			
			addProcessSelectedGroupTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Group>() {

				@Override
				public void onChanged(Change<? extends Group>  c) {
					Group group=addProcessSelectedGroupTable.getSelectionModel().getSelectedItem();
					
					if(group!=null){
						ArrayList<Group>allGroups=DB.getGroups();
						for (Group group2 : allGroups) {
							if(group.equals(group2))
								group=group2;
						}
						selectedGroups.remove(group);
						totalPrice-=group.getGroupPrice();
						allGroups.removeAll(selectedGroups);
						newProcessGroupTable.setItems(FXCollections.observableList(allGroups));
						addProcessSelectedGroupTable.setItems(FXCollections.observableList(selectedGroups));
						calculateDiscount();
						setOutDate();
					}
				}
			});
		}
	}

	private void newProcessPatientTable() {
		newProcessPatientCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
		newProcessPatientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
		newProcessPatientTable.setItems(FXCollections.observableList(DB.getAllPatients()));
		ObservableList<Patient> x=newProcessPatientTable.getSelectionModel().getSelectedItems();
		x.addListener(new ListChangeListener<Patient>() {

			@Override
			public void onChanged(ListChangeListener.Change<? extends Patient> c) {
				Patient patient=newProcessPatientTable.getSelectionModel().getSelectedItem();
				if(patient!=null){
					newProcessPatientName.setText(patient.getPatientName());
					newProcessPatientId.setText(patient.getPatientId().toString());
					short s=patient.getAgeType();
					String x="";
					if(s==0)
						x="Years";
					else if(s==1)
						x="Months";
					else
						x="Days";
					newProcessPatientAge.setText(DB.getThisAge(patient.getAge(),patient.getAgeType(),x)+"  "+x);
					newProcessPatientSource.setText(DB.getSource(patient.getPatientId()));
					calculateDiscount();
					setOutDate();
				}
			}
		});
	}
	
	public void newProcessPatientSearch(Event event){
		newProcessPatientTable.setItems(FXCollections.observableList(DB.getPatients(newProcessPatientSearch.getText())));	}
	
	public void newProcessTestSearch(Event event){
		ArrayList<Test>tests=DB.getAllTests(((TextField)event.getSource()).getText());
		tests.removeAll(selectedTests);
		newProcessTestTable.setItems(FXCollections.observableList(tests));
	}
	
	public void newProcessGroupSearch(Event event){
		ArrayList<Group>groups=DB.getAllGroups(((TextField)event.getSource()).getText());
		groups.removeAll(selectedGroups);
		newProcessGroupTable.setItems(FXCollections.observableList(groups));
	}
	
	public void newProcessSave(Event event){
		if(Exceptions.isWord(newProcessPrice.getText())&&Exceptions.isWord(newProcessPatientId.getText())){
			Float price=Float.parseFloat(newProcessPrice.getText());
			ObservableList<Test> list1=addProcessSelectedTestTable.getItems();
			Long[]tests=new Long[list1.size()];
			int counter=0;
			for (Test test:list1) {
				tests[counter]=test.getTestId();
				counter++;
			}
			ObservableList<Group> list2=addProcessSelectedGroupTable.getItems();
			counter=0;
			Integer[]groups=new Integer[list2.size()];
			for(Group group:list2){
				groups[counter]=group.getGroupId();
				counter++;
			}
			float paid=0;
			try{
				paid=Float.parseFloat(newProcessPay.getText());
			}catch(Exception e){
				paid=0;
			}
			if(Exceptions.isWord(newProcessReference.getText())){
				if(DB.saveNewProcess(Integer.parseInt(newProcessPatientId.getText()),price,paid,tests,groups,
						newProcessReference.getText(),newProcessOutDate.getText())){
					Exceptions.showInfo("Done");
					resetAddProcess();
					}
				else
					Exceptions.showInfo("something went wrong");
			}
			else{
				if(DB.saveNewProcess(Integer.parseInt(newProcessPatientId.getText()),price,paid,tests,groups,newProcessOutDate.getText())){
					Exceptions.showInfo("Done");
					resetAddProcess();
				}
				else
					Exceptions.showInfo("something went wrong");
			}
				
		}else
			Exceptions.showInfo("price can't be empty");
	}
	
	private void resetAddProcess() {
		selectedGroups=new ArrayList<>();
		selectedTests= new ArrayList<>();
		newProcessPrice.setText("");
		newProcessPay.setText("");
		newProcessReference.setText("");
		newProcessPatientSource.setText("Source");
		newProcessPatientAge.setText("Age");
		newProcessPatientName.setText("Name");
		newProcessPatientId.setText("Id");
		newProcessGroupSearch.setText("");
		newProcessTestSearch.setText("");
		newProcessPatientSearch.setText("");
		newProcessGroupTable.setItems(FXCollections.observableList(DB.getGroups()));
		newProcessTestTable.setItems(FXCollections.observableList(DB.getAllTests()));
		addProcessSelectedGroupTable.setItems(FXCollections.observableList(selectedGroups));
		addProcessSelectedTestTable.setItems(FXCollections.observableList(selectedTests));
		setOutDate();
	}

	private void setOutDate() {
		if(Exceptions.isWord(newProcessPrice.getText())&&Exceptions.isWord(newProcessPatientId.getText())){
			ArrayList<Float>hours=new ArrayList<>();
			for (Test test :selectedTests)
				hours.add(test.getHoursTaken());
			for(Group group:selectedGroups)
				hours.add(group.getHours());
			newProcessOutDate.setText(DB.getDateFromNow(Collections.max(hours)));
		}else
			newProcessOutDate.setText("Out date");
	}

}
