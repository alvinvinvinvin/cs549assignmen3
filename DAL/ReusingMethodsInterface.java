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
	
	/** $REUSING method. I basically rewrite the original method totally
	 *  because the the original one cannot approach the purpose.
	 * Divide the competitions to several lists based on the school
	 * @param input
	 * @return a list which contains several lists divided by school
	 */
	public List<List<Competition>> divide(List<Competition> input);
	
	/** $REUSING method. Without changing it too much.
	 * Sort the competitions ascendingly based on the score.
	 * @param input
	 * @return the list contains the ascending sort competitions
	 */
	public List<Competition> ascendSort(List<Competition> input);
	
	/** $REUSING method. No changing basically.
	 * Calculate the total score of the Competitions in the list
	 * @param input
	 * @return the total score
	 */
	public int getTotalScore(List<Competition> list);
	
	/** $REUSING method. No changing except variable name changing.
	 * Get the winner
	 * @param score[], schools[]
	 * @return the result with the winner
	 */
	public String getWinner(int score[], String schools[]);
	/**
	 * $ REUSING method. Basically I didn't change it except some variable names.
	 * sort the competition order and show the result.
	 * @param input
	 * @return each school of the competitions with 
	 */
	public String toString(List<List<Competition>> input);
	
	/**
	 * $REUSING I rewrite the @toString method from above to print out the winning student in each competition.
	 * The method works same way with  @toString method. If you want more details please check it above.
	 * @param input
	 * @return winner
	 */
	public String printWinner(List<Competition> input);

}
 