package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import classes.Exceptions;
import classes.Group;
import classes.Normal;
import classes.Patient;
import classes.Process;
import classes.Result;
import classes.Source;
import classes.Test;
import classes.User;
import javafx.collections.ObservableList;

public abstract class DB {

	private static Connection con;
	private static String DB;
	public static void setDB(String s){
		DB=s;
	}
	public static String getDB(){
		return DB;
	}
	private static void getConnection(){
		try{
			con= DriverManager.getConnection("jdbc:sqlite:C:\\LabData\\"+DB+"");
		}
		catch(SQLException ex){
			
		}
	}
	
	private static void closeCon(){
		try {
			con.close();
		} catch (SQLException e) {
		}
	}
	
	/**==========================================================================================**/
	
	private static ResultSet select(String sql){
		try{
			getConnection();
			return con.createStatement().executeQuery(sql);
		}catch (SQLException | NullPointerException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	private static boolean editDataBase(String sql){
		try {
			getConnection();
			con.createStatement().execute(sql);
			closeCon();
			return true;
		} catch (SQLException | NullPointerException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**============================================USER===========================================**/
	
	public static User getUser(String username) {
		ResultSet result=select("select * from user where user.name='"+username+"'");
		try {
			if(result.next()){
				User user=new User(result.getInt(1),result.getString(2),result.getShort(3),result.getString(4));
				closeCon();
				return user;
			}
		} catch (SQLException|NullPointerException e) {		}
		closeCon();
		return null;
	}

	public static boolean saveNewTest(String testName, float testPrice, String testComment, float testhours) {
		return editDataBase("insert into Test(name,price,hours,comment) values('"+testName+"',"+testPrice+","+testhours+",'"+testComment+"')");
	}

	public static ArrayList<Test> getAllTests() {
		ResultSet resultSet=select("select * from Test");
		ArrayList<Test> tests=new ArrayList<>();
		try{
			while(resultSet.next()){
				Test test;
				if((test=createTest(resultSet))!=null)
					tests.add(test);
			}
		}catch(Exception e){}
		closeCon();
		return tests;
	}

	private static Test createTest(ResultSet resultSet) {
		try {
			return new Test(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(5),resultSet.getShort(4), resultSet.getFloat(3));
		} catch (SQLException e) {
			return null;
		}
	}

	public static boolean Delete(Long testId) {
		return editDataBase("delete from Test where testId="+testId+"");
	}

	public static boolean saveNewGroup(String groupName, ArrayList<Test> deleted) {
		float groupPrice = 0,NoTests=deleted.size();
		Long groupId=(long) 0;
		ResultSet resultSet=select("select MAX(groupId)Id from GroupT");
		try {
			resultSet.next();
			groupId=resultSet.getLong(1)+1;
			closeCon();
		} catch (SQLException | NullPointerException e) {
			groupId=(long) 1;
		}
		Collections.sort(deleted, new Comparator<Test>() {
			@Override
			public int compare(Test o1, Test o2) {
				return (int) (o1.getTestId()-o2.getTestId());
			}
		});
		for (Test x:deleted){
			editDataBase("insert into TestGroup(testId,groupId) values("+x.getTestId()+","+groupId+")");
			groupPrice+=x.getPrice();
		}
		
		float grouphours=deleted.get((int) (NoTests-1)).getHoursTaken();
		return editDataBase("insert into GroupT(groupId,groupName,groupPrice,grouphours,NoTests) values ("+groupId+",'"+groupName+"',"
				+ ""+groupPrice+","+grouphours+","+NoTests+")");
		
	}

	public static Long getNextTestID() {
		ResultSet resultSet=select("select Max(Test.testId)ID from Test");
		try {
			resultSet.next();
			Long x=resultSet.getLong(1)+1;
			closeCon();
			return x;
		} catch (SQLException |NullPointerException e) {
			closeCon();
			return (long) 1;
		}
	}

	public static ArrayList<Group> getGroups() {
		ResultSet set=select("select * from GroupT");
		ArrayList<Group>groups=new ArrayList<>();
		try {
			while(set.next()){
				Group group;
				if((group=createGroup(set))!=null)
					groups.add(group);	
			}
		} catch (SQLException |NullPointerException e) {		}
		closeCon();
		return groups;
	}

	private static Group createGroup(ResultSet set) {
		try {
			return new Group(set.getInt(1), set.getInt(5), set.getString(2), set.getFloat(3), set.getFloat(4));
		} catch (SQLException e) {
			return null;
		}
	}

	public static boolean DeleteGroup(Integer groupId) {
		editDataBase("delete from TestGroup where groupId="+groupId+"");
		return editDataBase("delete from GroupT where groupId="+groupId+"");
	}

	public static ArrayList<Test> groupTests(int groupId) {
		getConnection();
		ResultSet set=select("select * from Test inner join TestGroup on Test.testId = TestGroup.testId where TestGroup.groupId="+groupId+"");
		ArrayList<Test> tests= new ArrayList<>();
		try {
			while(set.next()){
				Test test;
				if((test=createTest(set))!=null)
					tests.add(test);
			}
		} catch (SQLException | NullPointerException e) {
		}
		closeCon();
		return tests;
	}

	public static boolean editGroup(int Id, String groupName, float groupPrice, ArrayList<Test> deleted) {
		Collections.sort(deleted, new Comparator<Test>() {
		@Override
		public int compare(Test o1, Test o2) {
			return (int) (o1.getHoursTaken()-o2.getHoursTaken());
		}
	});
		editDataBase("update GroupT  set groupName='"+groupName+"', groupPrice="+groupPrice+", "
				+ "grouphours="+deleted.get((deleted.size()-1)).getHoursTaken()+",NoTests="+deleted.size()+" where groupId="+Id+"");
		editDataBase("delete from TestGroup where groupId="+Id+"");
		for(Test x:deleted)
			editDataBase("insert into TestGroup(testId,groupId)values("+x.getTestId()+","+Id+")");
		return true;
	}
	
	public static boolean updateTest(int testId, String name, float hours, String comment, float price) {
		boolean x= editDataBase("update Test set name='"+name+"', price="+price+", hours="+hours+",comment='"+comment+"' where testId="+testId+"");
		editDataBase("delete from Normal where testId="+testId+"");
		new Thread(new Runnable() {
					@Override
					public void run() {
						ArrayList<Integer>list=new ArrayList<>();
						ResultSet result=select("select groupId from TestGroup where TestGroup.testId="+testId+"");
						try {
							while(result.next()){
								list.add(result.getInt(1));
							}
						} catch (SQLException|NullPointerException e) {}
						closeCon();
						for(Integer x:list)
							editDataBase("update GroupT set groupPrice =(select sum(price) from Test "
									+ "inner join TestGroup on Test.testId=TestGroup.testId where TestGroup.groupId ="+x+"),"
									+ "grouphours=(select max(hours) from Test inner join TestGroup on Test.testId=TestGroup.testId"
									+ " where TestGroup.groupId ="+x+") where GroupT.groupId="+x+"");
					}
				}).start();
		return x;
	}

	public static boolean updateUser(String name, String password) {
		return editDataBase("update user set name='"+name+"',password='"+password+"' where user.id=1");
	}

	public static boolean saveNormal(int testId, short age, float ageFrom, float ageTo, short sex, float normalFrom,
			float normalTo, String comment) {
		return editDataBase("insert into Normal(testId,ageType,ageFrom,ageTo,sex,normalFrom,normalTo,comment)values"
				+ "("+testId+","+age+","+ageFrom+","+ageTo+","+sex+","+normalFrom+","+normalTo+",'"+comment+"')");
	}

	public static ArrayList<Normal> getTestNormals(int parseInt) {
		ResultSet result=select("select * from Normal where testId="+parseInt+"");
		ArrayList<Normal> normals=new ArrayList<>();
		try {
			while(result.next()){
				Normal normal;
				if((normal=createNormal(result))!=null)
					normals.add(normal);
			}
		} catch (SQLException |NullPointerException e) {}
		closeCon();
		return normals;
	}

	private static Normal createNormal(ResultSet result) {
		try {
			return new Normal(result.getFloat(4),result.getFloat(5),result.getFloat(7),result.getFloat(8),result.getInt(2),result.getInt(1), result.getShort(6),
					result.getShort(3),result.getString(9));
		} catch (SQLException|NullPointerException e) {
			return null;
		}
	}

	public static void editNormal(int testId,short age, float ageFrom, float ageTo, short sex, float normalFrom,
			float normalTo, String comment) {
		editDataBase("insert into Normal(testId,ageType,ageFrom,ageTo,sex,normalFrom,normalTo,comment) values"
				+ "("+testId+","+age+","+ageFrom+","+ageTo+","+sex+","+normalFrom+","+normalTo+",'"+comment+"')");
	}

	public static String getNextNormalId() {
		ResultSet resultSet=select("select Max(Normal.normalId)ID from Normal");
			try {
				resultSet.next();
				Long x=resultSet.getLong(1)+1;
				closeCon();
				return x.toString();
			} catch (SQLException |NullPointerException e) {
				closeCon();
				return "1";
			}
	}

	public static ArrayList<Test> getAllTests(String text) {
		ResultSet result=select("select * from Test where name like '"+text+"%'");
		ArrayList<Test>tests=new ArrayList<>();
		try{
			while(result.next()){
				Test test;
				if((test=createTest(result))!=null)
					tests.add(test);
			}
		}catch(Exception e){}
		closeCon();
		return tests;
	}

	public static ArrayList<Group> getAllGroups(String text) {
		ResultSet result=select("select * from GroupT where groupName like '"+text+"%'");
		ArrayList<Group>groups=new ArrayList<>();
		try{
			while(result.next()){
				Group group;
				if((group=createGroup(result))!=null)
					groups.add(group);
			}
		}catch(Exception e){}
		closeCon();
		return groups;
	}

	public static boolean saveNewPatient(int patientId, String patientName, String ageType, float age, short sexId) {
		short t=0;
		if(ageType.equals("Month"))
			t=1;
		else if(ageType.equals("Year"))
			t=0;
		else
			t=2;
		return editDataBase("insert into Patient(patientId,patientName,patientAgeType,patientDOB,patientSex) values"
				+ "("+patientId+",'"+patientName+"',"+t+",(select  date('now' ,'-"+age+" "+ageType+"')),"+sexId+")");
	}

	public static Long getNextPatientId() {
		ResultSet result=select("select MAX(patientId) from Patient");
		Long x=1l;
		try {
			result.next();
			x=result.getLong(1)+1;
		} catch (SQLException |NullPointerException e) {
		}
		closeCon();
		return x;
	}

	public static void addPatientPhone(int id, String text) {
		editDataBase("insert into Phone(patientId,patientPhone) values("+id+","+Long.parseLong(text)+")");
	}

	public static void addPatientSource(int id, int source){
		editDataBase("insert into PatientSource(patientId,sourceId)values("+id+","+source+")");
	}

	public static int getSourceID(String text) {
		ResultSet result=select("select sourceId from Source where sourceName='"+text+"'");
		try {
			result.next();
			int x= result.getInt(1);
			closeCon();
			return x;
		} catch (SQLException|NullPointerException e) {
			closeCon();
			return -1;
		}
	}

	public static ArrayList<Source> getAllSources() {
		ResultSet result=select("select * from Source");
		ArrayList<Source>sources=new ArrayList<>();
		try {
			while(result.next()){
				Source source;
				if((source=createSource(result))!=null)
					sources.add(source);
			}
		} catch (SQLException |NullPointerException e) {}
		closeCon();
		return sources;
	}

	private static Source createSource(ResultSet result) {
		try {
			return new Source(result.getString(2), result.getInt(1), result.getFloat(3));
		} catch (SQLException|NullPointerException e) {
			return null;
		}
	}

	public static ArrayList<Patient> getAllPatients() {
		ResultSet result=select("select * from Patient");
		ArrayList<Patient>patients=new ArrayList<>();
		try {
			while(result.next()){
				Patient patient;
				if((patient=createPatient(result))!=null)
					patients.add(patient);
			}
			closeCon();
		} catch (SQLException|NullPointerException e) {	}
		closeCon();
		return patients;
	}

	private static Patient createPatient(ResultSet result) {
		try {
			return new Patient(result.getInt(1), result.getString(2),result.getShort(3),result.getString(4));
		} catch (SQLException|NullPointerException e) {return null;}
	}

	public static ArrayList<Patient> getPatients(String text) {
		ResultSet result=select("select * from Patient where patientName like '"+text+"%'");
		ArrayList<Patient>patients=new ArrayList<>();
		try {
			while(result.next()){
				Patient patient;
				if((patient=createPatient(result))!=null)
					patients.add(patient);
			}
		} catch (SQLException|NullPointerException e) {		}
		closeCon();
		return patients;
	}

	public static Integer getThisAge(String age, short s,String x) {
		
		ResultSet result=select("select  date('now' ,'-"+(age.split("-"))[s]+" "+x+"')");
		try {
			result.next();
			x= result.getString(1);
		} catch (SQLException|NullPointerException e) {}
		closeCon();
		return Integer.parseInt((x.split("-"))[s]);
		
	}

	public static String getSource(Integer patientId) {
		ResultSet result=select("select sourceName from Source inner join PatientSource on "
				+ "Source.sourceId=PatientSource.sourceId where PatientSource.patientId = "+patientId+"");
		try {
			result.next();
			String s=result.getString(1);
			closeCon();
			return s;
		} catch (SQLException|NullPointerException e) {
			closeCon();
			return "No Source";
			}
		
	}

	public static Float getSourceDiscount(String text) {
		ResultSet result=select("select sourceDiscount from Source where sourceName='"+text+"'");
		try {
			result.next();
			float x=result.getFloat(1);
			closeCon();
			return x/100f;
		} catch (SQLException|NullPointerException e) {
			closeCon();
			return 0f;
		}
	}

	public static boolean saveNewProcess(int patientId, float price,float paid, Long[] tests,
			Integer[] groups, String reference,String outDate) {
		int x=saveProcess(patientId, price, paid, tests,groups,outDate);
		return editDataBase("insert into ProcessReference(processId,processReference)values("+x+",'"+reference+"')");
	}
	private static int saveProcess(int patientId, float price,float paid, Long[] tests,
			Integer[] groups,String outDate){
		int x=getNextProcess();
		String sql="insert into ProcessGroup(processId,groupId)values";
		int length=groups.length;
		for (int i = 0; i < length; i++) {
			if(i<length-1)
				sql+="("+x+","+groups[i]+"),";
			else
				sql+="("+x+","+groups[i]+");";
		}
		editDataBase(sql);
		sql="insert into ProcessTest(processId,testId)values";
		length=tests.length;
		for (int i = 0; i < length; i++) {
			if(i<(length-1))
				sql+="("+x+","+tests[i]+"),";
			else
				sql+="("+x+","+tests[i]+");";
		}
		editDataBase(sql);
		sql="insert into Process(processId,patientId,processPrice,processInDate,processOutDate,processPaid)values"
				+ "("+x+","+patientId+","+price+",(select date('now')),'"+outDate+"',"+paid+")";
		editDataBase(sql);
		return x;
	}

	private static int getNextProcess() {
		ResultSet result=select("select MAX(processId) from Process");
		try {
			result.next();
			int x=result.getInt(1)+1;
			closeCon();
			return x;
		} catch (SQLException|NullPointerException e) {
			closeCon();
			return 1;
		}
	}

	public static boolean saveNewProcess(int patientId, float price,float paid, Long[] tests,
			Integer[] groups,String outDate) {
		saveProcess(patientId, price, paid, tests,groups,outDate);
		return true;
	}

	public static String getDateFromNow(Float max) {
		ResultSet result=select("select datetime('now','+"+max+" hour')");
		try{
			result.next();
			String s=result.getString(1);
			closeCon();
			return s;
		}catch(Exception e){
			return "Out date";
		}
	}

	public static void deleteSource(Integer id) {
		editDataBase("delete from Source where sourceId="+id+"");
	}

	public static boolean saveNewSource(String name, float discount) {
		return editDataBase("insert into Source(sourceName,sourceDiscount)values('"+name+"',"+discount+")");
	}

	public static boolean editSourceSource(Integer id, String text, float parseFloat) {
		return editDataBase("update Source set sourceName='"+text+"', sourceDiscount="+parseFloat+" where sourceId="+id+"");
	}

	public static ArrayList<Result> getProcessTestsResult(int parseInt) {
		ResultSet resultSet=select("select ProcessTest.testId, Test.name, ProcessTestResult.testValue from ProcessTest inner join "
				+ "Test on Test.testId = ProcessTest.testId left join ProcessTestResult "
				+ "on ProcessTestResult.processId= "+parseInt+" and ProcessTestResult.testId=ProcessTest.testId where ProcessTest.processId="+parseInt+"");
		ArrayList<Result>results=new ArrayList<>();
		try {
			while(resultSet.next()){
				Result result;
				if((result=createResult(resultSet))!=null){
					results.add(result);
				}
			}
		} catch (SQLException|NullPointerException e) {		}
		closeCon();
		return results;
	}

	private static Result createResult(ResultSet resultSet) {
		String result="";
		try {
			if(resultSet.getString(3)!=null)
				result=resultSet.getString(3);
			return new Result(result, resultSet.getString(2), resultSet.getInt(1));
		} catch (SQLException|NullPointerException e) {		
			return null;
		}
	}
	private static int groupId,index;
	public static ArrayList<Integer>groups;
	public static ArrayList<String>groupsName;
	public static ArrayList<ArrayList<Result>> getProcessGroupsResult(int parseInt) {
		groupId=-1;
		index=-1;
		groups=new ArrayList<>();
		groupsName=new ArrayList<>();
		ArrayList<ArrayList<Result>> lists=new ArrayList<>();
		ResultSet resultSet=select("select Test.testId, Test.name, ProcessGroupResult.testValue ,ProcessGroup.groupId,GroupT.groupName from ProcessGroup"
				+ " inner join GroupT on GroupT.groupId=ProcessGroup.groupId inner join TestGroup on TestGroup.groupId=GroupT.groupId"
				+ " inner join Test on Test.testId=TestGroup.testId left join ProcessGroupResult on ProcessGroupResult.processId="+parseInt+" and"
				+ " Test.testId=ProcessGroupResult.testId where ProcessGroup.processId = "+parseInt+" order by ProcessGroup.groupId");
		try {
			while(resultSet.next()){
				Result result;
				int currentGroup=resultSet.getInt(4);
				if(currentGroup!=groupId){
					groupId=currentGroup;
					index++;
					lists.add(index,new ArrayList<>());
					groups.add(groupId);
					groupsName.add(resultSet.getString(5));
				}
				if((result=createResult(resultSet))!=null){
					lists.get(index).add(result);
				}
			}
		} catch (SQLException|NullPointerException e) {		}
		closeCon();
		return lists;
	}

	public static boolean saveGroupResult(int currentProcess, ArrayList<ArrayList<Result>> groups2,
			ArrayList<Integer> groupsId) {
		editDataBase("delete from ProcessGroupResult where processId="+currentProcess+"");
		String sql = "insert into ProcessGroupResult(processId,groupId,testId,testValue) values";
		int size=groupsId.size(),counter=0;
		for (ArrayList<Result> results :groups2 ) {
			size--;
			if(size>0){
				for(Result result: results){
					if(Exceptions.isWord(result.getTestValue()))
					sql+="("+currentProcess+","+groupsId.get(counter)+","+result.getTestId()+",'"+result.getTestValue()+"'),";
				}
			}else{
				int last=results.size();
				for(Result result: results){
					last--;
					if(last>0){
						if(Exceptions.isWord(result.getTestValue()))
							sql+="("+currentProcess+","+groupsId.get(counter)+","+result.getTestId()+",'"+result.getTestValue()+"'),";
						}
					else
						sql+="("+currentProcess+","+groupsId.get(counter)+","+result.getTestId()+",'"+result.getTestValue()+"');";
				}
			}
			counter++;
		}
		return editDataBase(sql);
	}

	public static boolean saveTestResult(int currentProcess, ObservableList<Result> x) {
		editDataBase("delete from ProcessTestResult where processId="+currentProcess+"");
		int size=x.size();
		String sql="insert into ProcessTestResult(processId,testId,testValue) values";
		for (Result result : x) {
			size--;
			if(size>0){
				if(Exceptions.isWord(result.getTestValue()))
				sql+="("+currentProcess+","+result.getTestId()+",'"+result.getTestValue()+"'),";
			}else
				sql+="("+currentProcess+","+result.getTestId()+",'"+result.getTestValue()+"');";
		}
		return editDataBase(sql);
	}

	public static ArrayList<Process> getPatientProcess(Integer patientId) {
		ResultSet resultSet=select("select * from Process where Process.patientId="+patientId+"");
		ArrayList<Process>processes=new ArrayList<>();
		try {
			while(resultSet.next()){
				Process process;
				if((process=createProcess(resultSet))!=null){
					processes.add(process);
				}
			}
		} catch (SQLException|NullPointerException e) {		}
		closeCon();
		return processes;
	}
	
	private static Process createProcess(ResultSet resultSet) {
		try {
			return new Process(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(3), resultSet.getFloat(6));
		} catch (SQLException|NullPointerException e) {return null;}
	}
	
	public static void payForProcess(Integer processId, Float pay) {
		editDataBase("update Process set processPaid = processPaid+"+pay+" where Process.processId="+processId+"");
	}
	public static short getPatientAgeType(int currentProcess) {
		ResultSet result=select("select patientAgeType from Patient inner join Process on Patient.patientId =Process.patientId "
				+ "where Process.processId="+currentProcess+"");
		try {
			result.next();
			short x= result.getShort(1);
			closeCon();
			return x;
		} catch (SQLException|NullPointerException e) {
		}
		closeCon();
		return -1;
	}
	public static boolean resultOverNormal(int currentProcess,int currentTest,String value) {
		ResultSet result=select("select Normal.normalId "+
					"from Patient "+
					"inner join Normal on  Patient.patientSex =Normal.sex and (select case "+
					"when Patient.patientAgeType=0 then strftime('%Y','now')-strftime('%Y',(select Patient.patientDOB)) "+
					"when Patient.patientAgeType=1 then strftime('%m','now')-strftime('%m',(select Patient.patientDOB)) "+
					"when Patient.patientAgeType=2 then strftime('%d','now')-strftime('%d',(select Patient.patientDOB)) "+
					"END) between Normal.ageFrom and Normal.ageTo and Normal.testId = "+currentTest+" and "+
					"('"+value+"' between Normal.normalFrom and Normal.normalTo or '"+value+"'= Normal.normalFrom or '"+value+"'= Normal.normalTo) "+
					"where Patient.patientId=(select Process.patientId from Process where Process.processId ="+currentProcess+")");
		try {
			if(result.next()){
				boolean x=false;
				closeCon();
				return x;
			}
		} catch (SQLException|NullPointerException e) {		}

		closeCon();
		return true;	
	}
	public static void getPrintInfo(int currentProcess) {
		String[]info=new String[4];
		ResultSet result=select("select Patient.patientName ,case "+
			" when Patient.patientAgeType=0 then (strftime('%Y','now')-strftime('%Y',(select Patient.patientDOB)))||' Years' "+
			" when Patient.patientAgeType=1 then (strftime('%m','now')-strftime('%m',(select Patient.patientDOB)))||' Months' "+
			" when Patient.patientAgeType=2 then (strftime('%d','now')-strftime('%d',(select Patient.patientDOB)))||' Days' "+
			" END, Process.processOutDate,ProcessReference.processReference from Patient inner join Process on Process.patientId=Patient.patientId "+
			" left join ProcessReference on ProcessReference.processId=Process.processId where Process.processId="+currentProcess+"");
		try {
			result.next();
			info[0]=result.getString(1);
			info[1]=result.getString(2);
			info[2]=result.getString(3);
			info[3]=result.getString(4);
		} catch (SQLException|NullPointerException e) {
		}
		closeCon();
		PrintControl.info=info;
	}
}
