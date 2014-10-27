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
*   
* Modification History:   
* Date    Author      Version     Description   
* ----------------------------------------------------------------- 
* Oct 24, 2014 Han Chen      1.0     1.0 Version   
*/  

public class DataStore {
	
	static Connection con = null;
	private static final String COMPETITOR_TABLE_NAME = "competitor";
	private static final String COMPETITION_TABLE_NAME = "competition";
	
	/**
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
	 * 
	 * @param name
	 * @return
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
	 * 
	 * @param c
	 * @return
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
	 * 
	 * @param name
	 * @param lv
	 * @return
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
	 * 
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
	 * 
	 * @param name
	 * @return
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
	 * 
	 * @param name
	 * @return
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
	 * 
	 * @param c
	 * @return lv
	 */
	public List<LEVEL> getLevelsFromCompetition(List<Competition> c){
		List<LEVEL> lv = new ArrayList<LEVEL>();
		for(Competition lc: c){
			lv.add(lc.getlv());
		}
		return lv;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void deleteCompetitorFromCompetitorTable(String name){
		try {
			Statement s = con.createStatement();
			s.executeUpdate("delet from "+COMPETITOR_TABLE_NAME
					+" where name='"+name+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param n
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
	 * 
	 * @param name
	 */
	public void deleteCompetitorFromCompetitionTable(String name){
		try {
			Statement s = con.createStatement();
			s.executeUpdate("delet from "+COMPETITION_TABLE_NAME
					+" where name='"+name+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param name
	 * @param lv
	 */
	public void deleteCompetitorFromCompetitionTableByLv(String name, String lv){
		try {
			Statement s = con.createStatement();
			s.executeUpdate("delet from "+COMPETITION_TABLE_NAME
					+" where name='"+name+"' and level='"+lv+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return
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
	 * 
	 * @return
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
	 * 
	 * @param lv
	 * @return competitions
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
	 * 
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
	 * 
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
	
	/**
	 * 
	 * @return
	 */
	public List<String> getSchools(){
		List<String> schools = new ArrayList<String>();
		try {
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery("select distinct school from "+COMPETITION_TABLE_NAME);
			while(r.next()){
				schools.add(r.getString(0));
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schools;
	}
	
	
}
 