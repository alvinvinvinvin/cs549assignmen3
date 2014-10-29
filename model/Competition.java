package model;
import java.util.Random;

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
*   The model class of Competition. I supposed to declared a comparable
*    interface for sorting competition list by scores. However for approaching
*    the requirement to reuse the code from classmates I chose to keep this function
*    but to reuse her code to sort the competition list.
* Modification History:   
* Date    			Author      Version     Description   
* ----------------------------------------------------------------- 
* Oct 24, 2014  Han Chen      1.0     1.0 Version   
*/
public class Competition implements Comparable<Competition> {
	
	private static int lowerBound = 0;//The lower bound of score.
	private static int upperBound = 100;//The upper bound of score.
	private static int scoreRange = upperBound-lowerBound;//The range of a random score could be generated.

	private int score =0;
	private String school = null;
	private String name = null;
	private LEVEL level = null;
	
	/**
	 * Constructors.
	 */
	public Competition() {}
	/**
	 * This constructor will be used for that when user wants to 
	 * generate a competition with random score.
	 * @param score
	 * @param school
	 * @param name
	 * @param level
	 */
	public Competition(String school, String name, String level) {
		setScore();//This is for generating random score competitions.
		this.school = school;
		this.name = name;
		this.level = convertLv(level);
	}
	
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
		this.level = convertLv(level);//Convert string from database to enumeration.
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * This set method will generate a random score.
	 * @param score the score to set
	 */
	public void setScore() {
		this.score = generateScore();
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
	 * This method will get the name of corresponding enumeration of level
	 * to save it as level field in database.
	 * @param lv
	 * @return
	 */
	public String lvToDB(LEVEL lv){
		return lv.name();
	}
	
	/**
	 * This method will return corresponding enumeration value based on the input string,
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
	 * compareTo method, which was meant to be used for sorting competitions by score.
	 * According to reuse the sorting method from other classmate, I didn't use this method yet.
	 * @param o
	 * @return res
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
	
	/**
	 * Randomly generating a score based on the range set previously.
	 * @return random score
	 */
	private static int generateScore(){
		return (new Random().nextInt(scoreRange))+ lowerBound;
	}
	
	
	

}
 