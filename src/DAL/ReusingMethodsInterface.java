package DAL;   

import java.util.List;

import model.Competition;

/**   
* Filename:    ReusingMethodsInterface.java   
* Copyright:   Copyright (c)2014  
* Company:   UWL
* @author: Han Chen
* @version:    1.0   
* @since:  JDK 1.7
* Create at:   Oct 26, 2014 10:23:27 PM   
* Description:  
*   
* Modification History:   
* Date    Author      Version     Description   
* ----------------------------------------------------------------- 
* Oct 26, 2014 Han Chen      1.0     1.0 Version   
*/     
public interface ReusingMethodsInterface {
	
	/** $REUSING
	 * divide the suit to several lists based on the suit
	 * @param arrayList
	 * @return a list which contains several lists divided by suit
	 */
	public List<List<Competition>> divide(List<Competition> input);
	
	/** $REUSING
	 * ascend the cards based on the value
	 * @param list
	 * @return the list contains the ascending sort cards
	 */
	public List<Competition> ascendSort(List<Competition> input);
	
	/** $REUSING
	 * calculate the total score of the cards in the list
	 * @param list
	 * @return the total score
	 */
	public int getTotalScore(List<Competition> list);
	
	/**
	 * get the winner
	 * @param score[], suitType[]
	 * @return the result with the winner
	 */
	public String getWinner(int score[], String schools[]);
	/**
	 *sort the card order and show the result.
	 * @param cardBySuit
	 * @return each suit of the cards with 
	 */
	public String toString(List<List<Competition>> input);
	
	/**
	 * $REUSING
	 * @param input
	 * @return winner
	 */
	public String printWinner(List<Competition> input);

}
 