package enums;   
/**   
* Filename:    Levels.java   
* Copyright:   Copyright (c)2014  
* Company:   UWL
* @author: Han Chen
* @version:    1.0   
* @since:  JDK 1.7
* Create at:   Oct 25, 2014 2:37:47 AM   
* Description:  
*   The class contains the enumeration of levels of competition based on requirement.
* Modification History:   
* Date    			Author      Version     Description   
* ----------------------------------------------------------------- 
* Oct 25, 2014   Han Chen      1.0     1.0 Version   
*/ 
public class Levels {
	public enum LEVEL{
		VSB {public String toString(){return "Violin (Solo) for beginners";}},
		VSI {public String toString(){return "Violin (Solo) for intermediates";}},
		VSE {public String toString(){return "Violin (Solo) for experts";}},
		PSB {public String toString(){return "Piano (Solo) for beginners";}},
		PSI {public String toString(){return "Piano (Solo) for intermediates";}},
		PSE {public String toString(){return "Piano (Solo) for experts";}},
		PD {public String toString(){return "Piano (duet)";}}
	}
}
 