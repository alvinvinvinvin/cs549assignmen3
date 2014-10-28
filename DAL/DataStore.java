package DAL;   
import java.sql.*;
import java.util.*;
import model.*;
import enums.Levels.LEVEL;
/**   
* Filename:    CompetitorDAL.java   
* Copyright:   Copyright (c)2014  
* Company:   UWL
* @author: Han Chen
* @version:    1.0   
* @since:  JDK 1.7
* Create at:   Oct 24, 2014 11:16:02 PM   
* Description:  
*   This class will play the role to communicate with database and
*   to deal with the data gotten from database.
* Modification History:   
* Date    Author      Version     Description   
* ----------------------------------------------------------------- 
* Oct 24, 2014 Han Chen      1.0     1.0 Version   
*/  

public class DataStore {
	
	//Instantiate a connection for connecting to database.
	static Connection con = null;
	//Set tables names as variables for extensibility
	private static final String COMPETITOR_TABLE_NAME = "competitor";
	private static final String COMPETITION_TABLE_NAME = "competition";
	
	/**
	 * Statements for connecting to database.
	 * @param db Data file name.
	 */
	public DataStore(String db){	
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + db);
		} catch (Exception e) {
			System.err.println(e.getClass().getName()+ ":"+e.getMessage()+"\n");
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * For ensuring the name of student is unique.
	 * @param name Student name.
	 * @return true/false.
	 */
	public boolean checkExistingOfCompetitor(String name){
		try {
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery("select * from "+
					COMPETITOR_TABLE_NAME+" where name='"+name+"'");
			if(r.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * For ensuring whether the student is in any competition or not.
	 * @param name Student name.
	 * @return true/false.
	 */
	public boolean checkExistingOfCompetition(String name){
		try {
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery("select * from "+
					COMPETITION_TABLE_NAME+" where name='"+name+"'");
			if(r.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Checking whether the student has already enrolled in particular level of competition or not.
	 * @param name Student name.
	 * @param lv The level of competition.
	 * @return true/false.
	 */
	public boolean checkExistingOfCompetitionWithLv(String name, String lv){
		try {
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery("select * from "+
					COMPETITION_TABLE_NAME+" where name='"
					+name+"' and level='"+lv+"'");
			if(r.next())
				return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	/**
	 * Adding a new student to competitor table.
	 * @param c The competitor going to be added.
	 * @return true/false The result of operation.
	 */
	public void addCompetitor(Competitor c){
		try {
			Statement s = con.createStatement();
			s.executeUpdate("insert into "+COMPETITOR_TABLE_NAME
					+" values('"+c.getName()+"','"+c.getSchool()+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will get a initialized Competitor from competitor table by student name.
	 * @param name
	 * @return c The competitor.
	 */
	public Competitor getCompetitorFromCompetitorTableByName(String name){
		Competitor c = new Competitor();
		try {
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery("select * from "+COMPETITOR_TABLE_NAME+" where name='"+name+"'");
			c.setName(r.getString("name"));
			c.setSchool(r.getString("school"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	/**
	 * This method will return a list of competitions which the particular student has enrolled getting by student name
	 * @param name Student name.
	 * @return c The list of competitions.
	 */
	public List<Competition> getCompetitionsFromCompetitionTableByName(String name){
		List<Competition> c = new ArrayList<Competition>();
		try {
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery("select * from "+COMPETITION_TABLE_NAME+" where name='"+name+"'");
			while(r.next()){
				c.add(
						new Competition(
								Integer.parseInt(r.getString("score")),
								r.getString("school"),
								r.getString("name"),
								r.getString("level")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	/**
	 * Counting how many levels has already been enrolled by competitor from input competition list.
	 * @param c Competition list.
	 * @return lv The levels' list.
	 */
	public List<LEVEL> getLevelsFromCompetition(List<Competition> c){
		List<LEVEL> lv = new ArrayList<LEVEL>();
		for(Competition lc: c){
			lv.add(lc.getlv());
		}
		return lv;
	}
	
	/**
	 * To delete a competitor from competitor table by his/her name.
	 * @param name Student name.
	 */
	public void deleteCompetitorFromCompetitorTable(String name){
		try {
			Statement s = con.createStatement();
			s.executeUpdate("delete from "+COMPETITOR_TABLE_NAME
					+" where name='"+name+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Adding a competitor to a instantiated competition.
	 * @param n The competition which has been instantiated previously.
	 */
	public void addCompetitorToCompetition(Competition n){
		try {
			Statement s = con.createStatement();
			s.executeUpdate("insert into "+COMPETITION_TABLE_NAME+" values('"
					+n.getScore()+"','"+n.getSchool()+"','"+n.getName()+"','"+n.lvToDB(n.getlv())+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Deleting specific competitor's all records from competition table by his/her name.
	 * @param name
	 */
	public void deleteCompetitorFromCompetitionTable(String name){
		try {
			Statement s = con.createStatement();
			s.executeUpdate("delete from "+COMPETITION_TABLE_NAME
					+" where name='"+name+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Deleting competitor's record from particular level of competition by his name and the level user chose.
	 * @param name Student name.
	 * @param lv The level user chose.
	 */
	public void deleteCompetitorFromCompetitionTableByLv(String name, String lv){
		try {
			Statement s = con.createStatement();
			s.executeUpdate("delete from "+COMPETITION_TABLE_NAME
					+" where name='"+name+"' and level='"+lv+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Getting all competitors instantiation from competitor table and set them to be a list.
	 * @return competitors The list of instantiated competitors.
	 */
	public List<Competitor> getAllCompetitor(){
		Statement s;
		List<Competitor> competitors = new ArrayList<Competitor>();
		try {
			s = con.createStatement();
			ResultSet r = s.executeQuery("select * from "+COMPETITOR_TABLE_NAME);
			while(r.next()){
				competitors.add(new Competitor(r.getString("name"),r.getString("school")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return competitors;
	}
	
	/**
	 * Getting all competitions instantiation from competition table and set them to be a list.
	 * @return competitions The list of instantiated competitions.
	 */
	public List<Competition> getAllCompetition(){
		Statement s;
		List<Competition> competitions = new ArrayList<Competition>();
		try {
			s=con.createStatement();
			ResultSet r = s.executeQuery("select * from "+COMPETITION_TABLE_NAME);
			while(r.next()){
				competitions.add(new Competition(Integer.parseInt(r.getString("score")), 
								r.getString("school"),r.getString("name"),r.getString("level")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return competitions;
	}
	
	/**
	 * This method will return a list of competitions by the specific level inputed by user.
	 * @param lv The level of competition.
	 * @return competitions The result of querying.
	 */
	public List<Competition> getCompetitionByLv(String lv){
		Statement s;
		List<Competition> competitions = new ArrayList<Competition>();
		try {
			s=con.createStatement();
			ResultSet r = s.executeQuery("select * from "+COMPETITION_TABLE_NAME+" where level='"+lv+"'");
			while(r.next()){
				competitions.add(new Competition(Integer.parseInt(r.getString("score")), 
								r.getString("school"),r.getString("name"),r.getString("level")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return competitions;
	}
	
	/**
	 * The method will print the list of competitors
	 * @param competitors The list of competitors we will print.
	 */
	public void printCompetitors(List<Competitor> competitors){
		System.out.println("----------------------------");
		System.out.println("Competitors:");
		System.out.println("----------------------------");
		System.out.printf("%-13s%-13s\n","name","school");
		System.out.println("----------------------------");
		for(Competitor c: competitors){
			System.out.printf("%-13s%-13s\n", c.getName(),c.getSchool());
		}
		System.out.println("----------------------------");
	}
	
	/**
	 * The method will print the list of competitors.
	 * @param competitions The list of competitors we will print.
	 */
	public void printCompetitions(List<Competition> competitions){
		System.out.println();
		System.out.println("Competitions:");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.printf("%-40s%-13s%-13s%-13s\n","level","name","school","score");
		System.out.println("-------------------------------------------------------------------------------");
		for(Competition c: competitions){
			System.out.printf("%-40s%-13s%-13s%-13s\n",c.getLevel(),c.getName(),c.getSchool(),c.getScore());
		}
		System.out.println("-------------------------------------------------------------------------------");
	}
	
	
}
 