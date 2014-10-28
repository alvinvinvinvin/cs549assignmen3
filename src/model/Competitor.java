package model;
/**   
* Filename:    Competitor.java   
* Copyright:   Copyright (c)2014  
* Company:   UWL
* @author: Han Chen
* @version:    1.0   
* @since:  JDK 1.7
* Create at:   Oct 24, 2014 10:56:17 PM   
* Description:  
* 
*   This class is the model class of Competitor. It is hard to 
*   say whether it is reused from my classmate's Card class 
*   because basically model classes look similar.
*   
* Modification History:   
* Date    			Author      Version     Description   
* ----------------------------------------------------------------- 
* Oct 24, 2014  Han Chen      1.0     1.0 Version   
*/  
public class Competitor{
	
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
	 * Constructors.
	 */
	public Competitor(){}
	
	/**
	 * 
	 * @param name
	 */
	public Competitor(String name){
		this.name = name;
	}
	/**
	 * @param name
	 * @param school
	 */
	public Competitor(String name, String school) {
		this.name = name;
		this.school = school;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "<" + this.getName() + "	     " + this.getSchool() + "> \n";
	}
	
	private String name = null;
	private String school = null;
}
 