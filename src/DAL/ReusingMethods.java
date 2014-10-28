package DAL;   
import java.util.ArrayList;
import java.util.List;
import model.Competition;

/**   
* Filename:    ReusingMethods.java   
* Copyright:   Copyright (c)2014  
* Company:   UWL
* @author: Han Chen
* @version:    1.0   
* @since:  JDK 1.7
* Create at:   Oct 25, 2014 11:10:11 PM   
* Description:  
*   This class basically is a reused or modified class from my classmate Yuanyuan Kang.
*   I kept most of those methods same with slight modification such as changing variables' 
*   names and etc..
* Modification History:   
* Date    Author      Version     Description   
* ----------------------------------------------------------------- 
* Oct 25, 2014 Han Chen      1.0     1.0 Version   
*/    
public class ReusingMethods implements ReusingMethodsInterface {
	/** $REUSING method. I basically rewrite the original method totally
	 *  because the the original one cannot approach the purpose.
	 * divide the competitions to several lists based on the school
	 * @param arrayList
	 * @return a list which contains several lists divided by school
	 */
	public List<List<Competition>> divide(List<Competition> input)
	{
		//Create the list which contains the competitions divided by school.
		List<List<Competition>> output = new ArrayList<List<Competition>>();
		String school;
		
		//Create a list to memorize the school has been added.
		List<String> gettenSchools = new ArrayList<String>();
		for(Competition competition : input)
		{
	        //The the school of each competition.
			school = competition.getSchool();
			
			/**
			 * If school has already been added to @output list, the competition which the school belongs to 
			 * will be added to the list of that school's competitions. OW a new list will be created as the new
			 * element of output list right behind the last existed one. The order of adding new competition to
			 * @gettenSchools list and @output list is same because the not existed school will be added to each list
			 * at same time.
			 */
			if(gettenSchools.contains(school)){//If the school has already been added to @output list.
				int index = gettenSchools.indexOf(school);//Get the @index of the school which has already been added.
				output.get(index).add(competition);//Use the @index to add this competition to the list of which it belongs to.
			}
			else{//If the school has never been added to @output list.
				gettenSchools.add(school);//Mark this school in @gettenSchools list right behind the last one.
				//Create a new list to contain this competition for being allowed to be added into @output list.
				List<Competition> temp = new ArrayList<Competition>();
				temp.add(competition);//Add this competition to the @temp list.
				//Add this @temp list to @output list right behind the last one. Therefore the order will be same with @gettenSchools list.
				output.add(temp);
			}
		}
		return output;
	}
	
	/** $REUSING method. Without changing it too much.
	 * Sort the competitions ascendingly based on the score.
	 * @param list
	 * @return the list contains the ascending sort cards
	 */
	public List<Competition> ascendSort(List<Competition> input)
	{
		Competition temp = new Competition();
			
			for(int i=0; i<input.size(); i++)
			{
				for(int j=0; j<input.size()-i-1; j++)
				{
					if(input.get(j+1).getScore()<input.get(j).getScore())
					{
						temp=input.get(j);
						
						input.set(j, input.get(j+1));
						
						input.set(j+1, temp);
					}
				}
			}
			return input;
	}
	
	/** $REUSING method. No changing basically.
	 * Calculate the total score of the Competitions in the list
	 * @param list
	 * @return the total score
	 */
	public int getTotalScore(List<Competition> list)
	{
		int score = 0;
		
		for(Competition competitions : list)
		{
			score += competitions.getScore();
		}
		
		return score;
	}

	/**$REUSING method. No changing except variable name changing.
	 * Get the winner
	 * @param score[], schools[]
	 * @return the result with the winner
	 */
	public String getWinner(int score[], String schools[])
	{
		//put the first value in score to max
		int max = score[0];
		
		//put the first value of schools to school
		String school = schools[0];
		
		int index = 0;
		String result = "";
		
		//compare the score and get the max score
		for(int i=1; i<score.length; i++)
		{
			if(score[i]>max)
			{
				max = score[i];
				
				school = schools[i];
				
				index = i;
			}
		}
		
		//if exists the same max score in the end of list, it shows no winner. 
		for(int i=index+1; i<score.length; i++)
		{
			if(score[i] == max)
			{
				result = "No winners!";
				return result;
			}
		}
		result = school+" has won this competition and the score is "+max;
		
		return result;
	}
	
	/**
	 * $ REUSING method. Basically I didn't change it except some variable names.
	 * sort the card order and show the result.
	 * @param cardBySuit
	 * @return each school of the cards with 
	 */
	public String toString(List<List<Competition>> input)
	{
		String result = "";
		
		//put the total score of each school into an array
		int score[] = new int[input.size()];
		
		//put the exist schools into an array, the index is corresponding to the array of score
		String schools[] = new String[input.size()];
		
		//traverse the each list in the list of input divided by the school
		for(int i=0; i<input.size(); i++)
		{
			//get each list based on school in the ascending order 
			List<Competition> sortedCompetition = ascendSort(input.get(i));
			
			//get the first competition of each list
			Competition competition = (Competition) sortedCompetition.get(0);
			
			//convert the school to String
			String school = competition.getSchool();
			
			result += school+":  "+competition.getScore()+" ";
			
			//traverse the list and show the result
			for(int j=1; j<sortedCompetition.size(); j++)
			{
				Competition infor = (Competition) sortedCompetition.get(j);
				
				result += "+ "+infor.getScore()+" ";
			}
			//get the TotalScore of each school 
			int totalScore = getTotalScore(sortedCompetition);
			
			//put the totalScore and school into the array
			score[i] = totalScore;
			schools[i] = competition.getSchool();
			
			//print out the formula
			result += "= "+totalScore+"\n";
		}
		//If there is no record of this competition at all.
		if(score.length<1||schools.length<1) return "There is no school enrolled in this competition yet.";
		//Get the highest score. For more details please check corresponding method.
		result += getWinner(score, schools);
		
		return result;
	}
	
	/**
	 * $REUSING I rewrite the @toString method from above to print out the winning student in each competition.
	 * The method works same way with  @toString method. If you want more details please check it above.
	 * @param input
	 * @return winner
	 */
	public String printWinner(List<Competition> input){
		String winner="";
		List<Competition> temp = ascendSort(input);
		int[] scores = new int[input.size()];
		String[] names = new String[input.size()];
		int i = 0;
		for(Competition n: temp){
			scores[i] = n.getScore();
			names[i] = n.getName();
			i++;
		}
		if(scores.length<1||names.length<1) return "There is no student enrolled in this competition yet.";
		winner += getWinner(scores, names);
		return winner;
	}

}
 