package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public abstract class SystemCreator {
	private static Connection con;
	private static void getConnection(String s){
		try{
			con= DriverManager.getConnection("jdbc:sqlite:src/controllers/"+s+"");
		}
		catch(Exception ex){
			
		}
	}
	
	private static void closeCon(){
		try {
			con.close();
		} catch (Exception e) {
		}
	}
	
	/**==========================================================================================**/
	
	private static ResultSet select(String db,String sql){
		try{
			getConnection(db);
			return con.createStatement().executeQuery(sql);
		}catch (Exception  e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	private static boolean editDataBase(String db,String sql){
		try {
			getConnection(db);
			con.createStatement().execute(sql);
			closeCon();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static String getSelectedDB(){
		ResultSet resultSet=select("system.db","select name from selectedDB where selected=1");
		try{
			resultSet.next();
			String s= resultSet.getString(1);
			closeCon();
			return s;
		}catch(Exception e){
			return null;
		}
	}

	public static String createSystem() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String name=""+dateFormat.format(date)+".db";
		createDatabases(name);
		return name;
	}

	private static void createDatabases(String name) {
		String sql[]={"CREATE TABLE `GroupT` ("
				+ " `groupId` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ " `groupName` TEXT NOT NULL UNIQUE, "
				+ " `groupPrice` FLOAT NOT NULL,"
				+ " `grouphours` FLOAT NOT NULL,"
				+ " `NoTests` INTEGER NOT NULL )"		,
				"CREATE TABLE `Normal` ("
				+ " `normalId` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ " `testId` INTEGER NOT NULL,"
				+ " `ageType` INTEGER NOT NULL,"
				+ " `ageFrom` FLOAT NOT NULL,"
				+ " `ageTo` FLOAT NOT NULL,"
				+ " `sex` INTEGER NOT NULL,"
				+ " `normalFrom` FLOAT NOT NULL,"
				+ " `normalTo` FLOAT NOT NULL,"
				+ " `comment` TEXT )"					,
				"CREATE TABLE `Patient` ("
				+ " `patientId` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ " `patientName` TEXT NOT NULL UNIQUE,"
				+ " `patientAgeType` INTEGER NOT NULL,"
				+ " `patientDOB` DATE NOT NULL )"		,
				"CREATE TABLE `PatientSource` ("
				+ " `patientId` INTEGER NOT NULL,"
				+ " `sourceId` INTEGER NOT NULL )"		,
				"CREATE TABLE `Phone` ("
				+ " `patientId` INTEGER NOT NULL,"
				+ " `patientPhone` INTEGER NOT NULL )"	,
				"CREATE TABLE `Process` ("
				+ " `processId` INTEGER NOT NULL,"
				+ " `patientId` INTEGER NOT NULL,"
				+ " `processPrice` INTEGER NOT NULL,"
				+ " `processInDate` TEXT NOT NULL,"
				+ " `processOutDate` TEXT NOT NULL,"
				+ " `processPaid` INTEGER NOT NULL,"
				+ " PRIMARY KEY(`processId`) )"			,
				"CREATE TABLE `ProcessGroup` ("
				+ " `processId` INTEGER NOT NULL,"
				+ " `groupId` INTEGER NOT NULL )"		,
				"CREATE TABLE `ProcessGroupResult` ("
				+ " `processId` INTEGER NOT NULL,"
				+ " `groupId` INTEGER NOT NULL,"
				+ " `testId` INTEGER NOT NULL,"
				+ " `testValue` TEXT NOT NULL )"		,
				"CREATE TABLE `ProcessReference` ("
				+ " `processId` INTEGER NOT NULL,"
				+ " `processReference` TEXT NOT NULL )"	,
				"CREATE TABLE `ProcessTest` ("
				+ " `processId` INTEGER NOT NULL,"
				+ " `testId` INTEGER NOT NULL )"		,
				"CREATE TABLE `ProcessTestResult` ("
				+ " `processId` INTEGER NOT NULL,"
				+ " `testId` INTEGER NOT NULL,"
				+ " `testValue` TEXT NOT NULL )"		,
				"CREATE TABLE `Source` ("
				+ " `sourceId` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ " `sourceName` TEXT NOT NULL UNIQUE,"
				+ " `sourceDiscount` INTEGER NOT NULL )",
				"CREATE TABLE `Test` ("
				+ " `testId` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ " `name` TEXT NOT NULL UNIQUE,"
				+ " `price` INTEGER NOT NULL,"
				+ " `hours` FLOAT NOT NULL,"
				+ " `comment` TEXT )"					,
				"CREATE TABLE `TestGroup` ("
				+ " `testId` INTEGER NOT NULL,"
				+ " `groupId` INTEGER NOT NULL )",
				"CREATE TABLE `user` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ " `name` TEXT NOT NULL,"
				+ " `role` INTEGER NOT NULL,"
				+ " `password` TEXT NOT NULL )"			,
				"insert into user(name,role,password) values('root',0,'123')"
			};
		for (String string : sql) {
			editDataBase(name, string);
		}
		editDataBase("system.db", "update selectedDB set selected=0");
		editDataBase("system.db", "insert into selectedDB(name,selected)values('"+name+"',1)");
	}

	public static void changeDatabaseTo(String db) {
		editDataBase("system.db", "update selectedDB set selected=0");
		editDataBase("system.db", "update selectedDB set selected=1 where name='"+db+"'");
		DB.setDB(db);
	}

	public static ArrayList<String> getAllDBS() {
		ResultSet resultSet=select("system.db", "select name from selectedDB where selected=0");
		ArrayList<String> files=new ArrayList<>();
		try {
			while(resultSet.next()){
				String s;
				if((s=resultSet.getString(1))!=null)
					files.add(s);
			}
		} catch (Exception e) {
		}
		return files;
	}

	public static void createSystemWithData(LocalDate s) {
		String p=DB.getDB();
		String n=createSystem();
		try {
			new GroupT(p, n);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		try {
			new Normal(p, n);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		try {
			new Patient(p, n);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		try {
			new PatientSource(p, n);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		try {
			new Phone(p, n);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		try {
			new Process(p, n,s.toString());
			
		} catch (Exception e2) {
			
			System.out.println(e2.getMessage());
		}
		try {
			new ProcessGroup(p, n,s.toString());
			
		} catch (Exception e1) {
			
			System.out.println(e1.getMessage());
		}
		try {
			new ProcessGroupResult(p, n,s.toString());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		try {
			new ProcessReference(p, n,s.toString());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		try {
			new ProcessTest(p, n,s.toString());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		try {
			new ProcessTestResult(p, n,s.toString());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		try {
			new Source(p, n);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		try {
			new Test(p, n);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		try {
			new TestGroup(p, n);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		DB.setDB(n);
	}
	
	public static class GroupT{
		public GroupT(String p,String n) throws Exception{
			ResultSet r=select(p, "select * from GroupT");
			String sql="insert into GroupT(groupId,groupName,groupPrice,grouphours,NoTests) values";
			while(r.next()){
				sql+="("+r.getInt(1)+",'"+r.getString(2)+"',"+r.getFloat(3)+","+r.getFloat(4)+","+r.getInt(5)+"),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
	}
	private static class Normal{
		public Normal(String p,String n) throws Exception{
			ResultSet r=select(p, "select * from Normal");
			String sql="insert into Normal(normalId,testId,ageType,ageFrom,ageTo,sex,normalFrom,normalTo,comment) values";
			while(r.next()){
				
					sql+="("+r.getInt(1)+","+r.getInt(2)+","+r.getInt(3)+","+r.getFloat(4)+","+r.getFloat(5)+","+r.getInt(6)+","
							+ ""+r.getFloat(7)+","+r.getFloat(8)+",'"+r.getString(9)+"'),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
	}
		
	private static class Patient{
		public Patient(String p,String n) throws Exception{
			ResultSet r=select(p, "select * from Patient");
			String sql="insert into Patient(patientId,patientName,patientAgeType,patientDOB) values";
			while(r.next()){
				sql+="("+r.getInt(1)+",'"+r.getString(2)+"',"+r.getInt(3)+",'"+r.getString(4)+"'),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
	}
	private static class PatientSource{
		public PatientSource(String p,String n) throws Exception{
			ResultSet r=select(p, "select * from PatientSource");
			String sql="insert into PatientSource(patientId,sourceId) values";
			while(r.next()){
				sql+="("+r.getInt(1)+","+r.getInt(2)+"),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
	}
	private static class Phone{
		public Phone(String p,String n) throws Exception{
			ResultSet r=select(p, "select * from Phone");
			String sql="insert into Phone(patientId,patientPhone) values";
			while(r.next()){
				sql+="("+r.getInt(1)+",'"+r.getString(2)+"'),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
		
	}
	private static class Process{
		public Process(String p,String n,String date) throws Exception{
			ResultSet r=select(p, "select * from Process where Process.processInDate>'"+date+"'");
			String sql="insert into Process(processId,patientId,processPrice,processInDate,processOutDate,processPaid) values";
			
			while(r.next()){
				sql+="("+r.getInt(1)+","+r.getInt(2)+","+r.getFloat(3)+",'"+r.getString(4)+"','"+r.getString(5)+"',"+r.getFloat(6)+"),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
	}
	private static class ProcessGroup{
		public ProcessGroup(String p,String n,String date) throws Exception{
			ResultSet r=select(p, "select * from ProcessGroup where ProcessGroup.processId in (select Process.processId from Process where"
					+ " Process.processInDate>'"+date+"')");
			String sql="insert into ProcessGroup(processId,groupId) values";
			while(r.next()){
				sql+="("+r.getInt(1)+","+r.getInt(2)+"),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
	}
	private static class ProcessGroupResult{
		public ProcessGroupResult(String p,String n,String date) throws Exception{
			ResultSet r=select(p, "select * from ProcessGroupResult where ProcessGroupResult.processId in (select processId from Process where"
					+ " Process.processInDate>'"+date+"')");
			String sql="insert into ProcessGroupResult(processId,groupId,testId,testValue) values";
			while(r.next()){
				sql+="("+r.getInt(1)+","+r.getInt(2)+","+r.getInt(3)+",'"+r.getString(4)+"'),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
	}
	private static class ProcessReference{
		public ProcessReference(String p,String n,String date) throws Exception{
			ResultSet r=select(p, "select * from ProcessReference where ProcessReference.processId in (select processId from Process where"
					+ " Process.processInDate>'"+date+"')");
			String sql="insert into ProcessReference(processId,processReference) values";
			while(r.next()){
				sql+="("+r.getInt(1)+",'"+r.getString(2)+"'),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
	}
	private static class ProcessTest{
		public ProcessTest(String p,String n,String date) throws Exception{
			ResultSet r=select(p, "select * from ProcessTest where ProcessTest.processId in (select processId from Process where"
					+ " Process.processInDate>'"+date+"')");
			String sql="insert into ProcessTest(processId,testId) values";
			while(r.next()){
				sql+="("+r.getInt(1)+","+r.getInt(2)+"),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
	}
	private static class ProcessTestResult{
		public ProcessTestResult(String p,String n,String date) throws Exception{
			ResultSet r=select(p, "select * from ProcessTestResult where ProcessTestResult.processId in (select processId from Process where"
					+ " Process.processInDate>'"+date+"')");
			String sql="insert into ProcessTestResult(processId,testId,testValue) values";
			while(r.next()){
				sql+="("+r.getInt(1)+","+r.getInt(2)+",'"+r.getString(3)+"'),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
	}
	private static class Source{
		public Source(String p,String n) throws Exception{
			ResultSet r=select(p, "select * from Source");
			String sql="insert into Source(sourceId,sourceName,sourceDiscount) values";
			while(r.next()){
				sql+="("+r.getInt(1)+",'"+r.getString(2)+"',"+r.getFloat(3)+"),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
	}
	private static class Test{
		public Test(String p,String n) throws Exception{
			ResultSet r=select(p, "select * from Test");
			String sql="insert into Test(testId,name,price,hours,comment) values";
			while(r.next()){
				sql+="("+r.getInt(1)+",'"+r.getString(2)+"',"+r.getFloat(3)+","+r.getFloat(4)+",'"+r.getFloat(5)+"'),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
		
	}
	private static class TestGroup{
		public TestGroup(String p,String n) throws Exception{
			ResultSet r=select(p, "select * from TestGroup");
			String sql="insert into TestGroup(testId,groupId) values";
			while(r.next()){
				sql+="("+r.getInt(1)+","+r.getInt(2)+"),";
			}
			sql=sql.substring(0, sql.length()-1);
			editDataBase(n, sql);
		}
		
	}
}
