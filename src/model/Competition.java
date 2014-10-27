package model;
import enums.Levels.LEVEL;
/**   
* Filename:    Competition.java   
* Copyright:   Copyright (c)2014  
* Company:   UWL
* @author: Han Chen
* @version:    1.0   
* @since:  JDK 1.7
* Create at:   Oct 24, 2014 11:11:14 PM   
* Description:  
*   
* Modification History:   
* Date    			Author      Version     Description   
* ----------------------------------------------------------------- 
* Oct 24, 2014  Han Chen      1.0     1.0 Version   
*/
public class Competition implements Comparable<Competition> {

	private int score;
	private String school;
	private String name;
	private LEVEL level;
	
	/**
	 * Constructors.
	 */
	public Competition() {}
	/**
	 * @param score
	 * @param school
	 * @param name
	 * @param level
	 */
	public Competition(int score, String school, String name, String level) {
		this.score = score;
		this.school = school;
		this.name = name;
		this.level = convertLv(level);
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * @return the school
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * @param school the school to set
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level.toString();
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = convertLv(level);
	}
	
	/**
	 * 
	 * @return
	 */
	public LEVEL getlv(){
		return level;
	}
	
	/**
	 * 
	 * @param lv
	 */
	public void setlv(LEVEL lv){
		this.level = lv;
	}
	/**
	 * 
	 * @param lv
	 * @return
	 */
	public String lvToDB(LEVEL lv){
		return lv.name();
	}
	
	/**
	 * 
	 * @param lv
	 * @return
	 */
	private LEVEL convertLv(String lv){
		if(lv.equalsIgnoreCase("VSB")) return LEVEL.VSB;
		if(lv.equalsIgnoreCase("VSI")) return LEVEL.VSI;
		if(lv.equalsIgnoreCase("VSE")) return LEVEL.VSE;
		if(lv.equalsIgnoreCase("PSB")) return LEVEL.PSB;
		if(lv.equalsIgnoreCase("PSI")) return LEVEL.PSI;
		if(lv.equalsIgnoreCase("PSE")) return LEVEL.PSE;
		return LEVEL.PD;
	}
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Competition o) {
		// TODO Auto-generated method stub
		int res = 0;
		int self = this.getScore();
		int other = o.getScore();
		res = (self==other) ? 0 : ((self>other) ? 1 : -1);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "<" + this.getLevel() + "	 " + this.getName() + "	 "
				+ this.getSchool() + " " + this.getScore() + ">\n";
	}
	
	
	

}
 