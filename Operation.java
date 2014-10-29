import java.io.*;
import java.util.*;
import model.*;
import enums.Levels.LEVEL;
import DAL.*;

/**   
* Filename:    Operation.java   
* Copyright:   Copyright (c)2014  
* Company:   UWL
* @author: Han Chen
* @version:    1.0   
* @since:  JDK 1.7
* Create at:   Oct 26, 2014 5:03:47 PM   
* Description:  
*   The majority class by calling functions from other classes and
*   implementing most of functions to make the program runnable.
*   It will be called by main method located in Start class.
* Modification History:   
* Date    Author      Version     Description   
* ----------------------------------------------------------------- 
* Oct 26, 2014 Han Chen      1.0     1.0 Version   
*/  
public class Operation {
	private static final String db = "cs549assignment3.db";//File name of Database.
	private static DataStore ds = new DataStore(db);//Instantiate a DataStore for building connection to database.
	private static String mainMenuCmd = "bm";//The command for going back to main menu. Being used a lot during program.
	//The notation for user explaining how to go back main menu.
	private static String goMainMenu = " (Entering \""+mainMenuCmd+"\" will go back to Main Menu) ";
	
	/**
	 * This method is the main portion of this program containing all functions.
	 */
	public static void operating(){
		/**
		 * Obtain input stream and scan it.
		 */
		InputStream is = System.in;
		Scanner scan = new Scanner(is);
		System.out.println();
		System.out.println("\nWelcom to The Wisconsin Music Association for Youngsters (WMAY)!");
		System.out.println();
		System.out.println();
		System.out.println("We strongly recommend you to maximize the window size for using conveniently.");
		System.out.println();
		
		/**
		 * By using while loop the program will keep running until user intents to exit it.
		 */
		while(true){
			//Print main menu.
			printMainMenu();
			
			//Assign the content scanned from input to a string.
			String op = scan.next();
			
			//Implementing the method of different options only if the input has only one character.
			if (op.length() == 1) {
				switch (op.charAt(0)) {
				//Print all competitors.
				case '1':
					ds.printCompetitors(ds.getAllCompetitor());
					break;
				//Print all competitions.
				case '2':
					ds.printCompetitions(ds.getAllCompetition());
					break;
				//Get competition records from database based on level user input and counting the winner.
				case '3':
					printResultByLv(scan);
					break;
				//Generate the random result of each level's competition including winning student and winning school.
				case '4':
					printResult(scan);
					break;
				//Add a competitor to competitor table.
				case '5':
					addCompetitor(scan);
					break;
				//Delete a competitor from both competitor table and competition table.
				case '6':
					deleteCompetitor(scan);
					break;
				//Add a existing competitor to competition with indicating the level.
				case '7':
					addCompetitorToCompetition(scan);
					break;
				//Delete a enrolled competitor from existing competition with indicating the level.
				case '8':
					deleteCompetitorFromCompetition(scan);
					break;
				//If user wants to exit program.
				case '0':
					System.out.println("GOOD BYE!");
					System.exit(0);
				//If user inputs invalid commands.
				default:
					System.out.println("Please input valid command.");
					break;
				}
			}
			//If user inputs more than one character.
			else{
				System.out.println("Please input valid command.");
			}
		}
	}
	
	/**
	 * For printing the main menu.
	 */
	private static void printMainMenu(){
		System.out.println();
		System.out.println(
				"--------------------Main Menu----------------------\n"+
				"Please choose one option:\n"+
				"---------------------------------------------------\n"+
				"1. List all competitors.\n"+
				"2. List all competitions.\n"+
				"3. View the particular level of competition.\n"+
				"4. Generate a competiton with random results in different levels.\n"+
				"5. Add new competitors.\n"+
				"6. Delete a competitors.\n"+
				"7. Add a competitor to a competiton.\n"+
				"8. Delete a competitor from a competiton.\n"+
				"0. Exit.\n"+
				"----------------------------------------------------\n"+
				"Tips: "+goMainMenu+" whenever you want\n"+
				"----------------------------------------------------");
	}
	
	
	
	/**
	 * Checking whether the input is a numerical for determining if it is a valid option number or not.
	 * @param input
	 * @return
	 */
	private static boolean numerical(String input){
		return input.matches("\\d+");
	}
	
	/**
	 * Add a competitor to competitor table.
	 * @param scan
	 */
	private static void addCompetitor(Scanner scan){
		while(true){
			System.out.println("please input student name "+goMainMenu+":");
			String name = scan.next();
			//If the command is not the command making program going back to main menu.
			if (!name.equalsIgnoreCase(mainMenuCmd)) {
				//Checking whether the name of competitor exists or not.
				boolean isExisting = ds.checkExistingOfCompetitor(name);
				if (isExisting) {
					//If it exists program will ask user to input other name.
					System.out.println("Name has already been used, try others please "+goMainMenu+":");
				} else {
					//If it doesn't exists program will ask user to input school.
					System.out.println("please input school "+goMainMenu+":");
					String school = scan.next();
					//If user didn't input command to let program go back to main menu.
					if (!school.equalsIgnoreCase(mainMenuCmd)) {
						//Adding this instantiated competitor to competitor table.
						ds.addCompetitor(new Competitor(name, school));
						System.out.println("Successful Adding.");
						break;
					}
					else break;//If user want to go back to main menu.
				}
			}
			else break;//If user want to go back to main menu.
		}
	}
	
	/**
	 * Adding a existing competitor to competition.
	 * @param scan
	 */
	private static void addCompetitorToCompetition(Scanner scan){
		while(true){
			//Display all competitors
			ds.printCompetitors(ds.getAllCompetitor());
			System.out.println("\nPlease input a name of competitor who you want to add to competition:\n"+goMainMenu);
			String name = scan.next();
			//If user didn't want to go back main menu.
			if(!name.equalsIgnoreCase(mainMenuCmd)){
				//if the name exists in competitor table.
				if(ds.checkExistingOfCompetitor(name)){
					//A while loop to give user more chances to input valid command.
					while (true) 
					{
						System.out
								.println("\nPlease input the level of competiton which you want to add this competitor in it.");
						//Print all levels by order.
						for (LEVEL l : LEVEL.values())
						{
							System.out.println("\"" + (l.ordinal() + 1) + "\" for "
									+ l.toString());
						}
						System.out.println("\"0\" for going backward\n" + goMainMenu);//Option 0 means going backward.
						
						String lv = scan.next();
						//If user don't want to go back main menu.
						if (!lv.equalsIgnoreCase(mainMenuCmd))
						{
							//Check whether the input is numerical or not.
							if (numerical(lv))
							{
								int index = Integer.parseInt(lv);//If it is numerical, parse it to integer.
								//If the input is valid depending on the number of options.
								if (0 <index && index <= LEVEL.values().length) 
								{
									//Get the name of the level user chose.
									String level = LEVEL.values()[index-1].name();
									//Checking whether the competitor has already existed in particular level of competition or not.
									if (!ds.checkExistingOfCompetitionWithLv(name, level)) {
										Competitor c = ds.getCompetitorFromCompetitorTableByName(name);
										//Add this competitor to competition with score and level user chose.
										ds.addCompetitorToCompetition(new Competition(
												c.getSchool(), c.getName(), level));
										System.out.println("Adding successful.");
										System.out.println();
										//Display all competitions again to show user the result of adding.
										ds.printCompetitions(ds.getAllCompetition());
									}
									else{
										//If this student has already enrolled in particular level of competition.
										System.out.println(name+" has already enrolled in "+LEVEL.values()[index-1].name()+" competition");
									}
								}
								else if(index == 0){
									break;//If user chose option "0" to go backward.
								}
								else {
									//If user didn't input valid option number.
									System.out.println("Please input valid command.");
								}
							} else {
								//If user didn't input valid option number.
								System.out.println("Please input valid command.");
							}
						}
						else{
							//If user want to go back to main menu.
							return;
						}
					}
				}
				else{
					//If user input wrong name of student.
					System.out.println("System cannot find this student, please check the name you input again.");
				}
			}
			else{
				//If user want to go back to main menu.
				return;
			}
		}
	}
	
	/**
	 * Delete competitor from either competitor table or competition table.
	 * @param scan
	 */
	private static void deleteCompetitor(Scanner scan){
		//If there are some competitors in table.
		while (!ds.getAllCompetitor().isEmpty()) {
			//Display all competitor for conveniently picking a name.
			ds.printCompetitors(ds.getAllCompetitor());
			System.out.println("please input the name of competitor whom you want to delete:\n"+ goMainMenu);
			String name = scan.next();
			//If user doesn't want to go back to main menu.
			if (!name.equalsIgnoreCase(mainMenuCmd)) {
				//Checking if it is a valid name.
				if (ds.checkExistingOfCompetitor(name)) {
					//While loop for giving more chances to user ensuring the deleting.
					while (true) {
						System.out.println("Deleting this competitor will also delete him from competition.");
						System.out.println("Are you sure you want to delete this competitor?\n"
										+ " \"y\" for yes, \"n\" for going backward, "
										+ goMainMenu);
						String conf = scan.next();
						//If user doesn't want to go back to main menu.
						if (!conf.equalsIgnoreCase(mainMenuCmd)) {
							//If user input "y".
							if (conf.equalsIgnoreCase("y")) {
								//Delete competitor from competitor table first by name user input.
								ds.deleteCompetitorFromCompetitorTable(name);
								System.out.println("Deleting from competitor table successful.");
								System.out.println();
								//If there are any records in competition table about this student.
								if (ds.checkExistingOfCompetition(name)) {
									//Delete them as well by student name.
									ds.deleteCompetitorFromCompetitionTable(name);
									System.out.println("Deleting from competition table successful.");
									System.out.println();
								}
								else{
									//If there is no record of competition about this student.
									System.out.println("There is no record of this competitor in competition table.");
									System.out.println();
								}
								//Print all competitors again to show user the process worked.
								ds.printCompetitors(ds.getAllCompetitor());
							} else if (conf.equalsIgnoreCase("n")) {
								break;//If user regretted then the program will go backward.
							} else {
								System.out.println("please input valid command.");
							}
						} else {
							return;
						}
					}
				}
				else{
					System.out.println("The name you input does not exist in competitor table, please check it again.");
					System.out.println();
				}
			}
			else{
				return;
			}
		}
		System.out.println("There is no any competitor in yet.");
	}
	
	/**
	 * Deleting competitor from competition.
	 * @param scan
	 */
	private static void deleteCompetitorFromCompetition(Scanner scan){
		//If there is some competition in database.
		while (true) {
			//Display all competitions
			ds.printCompetitions(ds.getAllCompetition());
			System.out
					.println("Please input name for deleting him from competition:\n"
							+ goMainMenu);
			String name = scan.next();
			//If user doesn't want to go back to main menu.
			if (!name.equalsIgnoreCase(mainMenuCmd)) {
				//Checking whether it is a valid name or not.
				if (ds.checkExistingOfCompetition(name)) {
					boolean flag = true;//Flag for skipping the lower menu.
					while (flag) {
						System.out
								.println("Do you want to delete all competitions of him "
										+ "or just want to delete his record of particular level?\n"
										+ "Enter \"1\" for deleting all record;\n"
										+ "Enter \"\2\" for deleting him from particular level;\n"
										+ "Enter \"0\" for going backward."
										+ goMainMenu);
						String cmd = scan.next();
						//If user doesn't choose to go back to main menu.
						if (!cmd.equalsIgnoreCase(mainMenuCmd)) {
							switch (cmd.charAt(0)) {
							//If user chose deleting all record
							case '1':
								while (true) {
									System.out
											.println("Are you sure you want to delete all records of this competitior from competition table?\n"
													+ " \"y\" for yes, \"n\" for going backward, "
													+ goMainMenu);
									String conf = scan.next();
									//If user doesn't want to go back to main menu.
									if (!conf.equalsIgnoreCase(mainMenuCmd)) {
										//If user chose "y".
										if (conf.equalsIgnoreCase("y")) {
											//Delete all records relating this student from competition table.
											ds.deleteCompetitorFromCompetitionTable(name);
											System.out
													.println("Deleting all records of "
															+ name
															+ " from competition successful.");
											flag = false;//set flag to be false to skip the outsider while loop.
											break;//jump out from current loop.
											//if user chose "n".
										} else if (conf.equalsIgnoreCase("n")) {
											break;//it will go backward.
										} else {
											System.out.println("Please input valid command.");
										}
									} else
										return;
								}
								break;
							//If user chose to pick one level to delete.
							case '2':
								while (true) {
									System.out
											.println("The levels of competiton that "
													+ name + " has enrolled:\n" +
															"----------------"+name+"----------------");
									//get all competitions he enrolled by name.
									List<Competition> c = ds
											.getCompetitionsFromCompetitionTableByName(name);
									int i = 0;
									//Continually display all competitions user got before.
									for (Competition n : c) {
										System.out.println(++i + ". "
												+ n.getLevel()+".");
									}
									System.out.println("0. Going backward.\n" +
											"-----------------------------------");
									System.out
											.println("Please select one for deleting.");
									String lv = scan.next();
									//If user chose to go back to main menu.
									if (lv.equalsIgnoreCase(mainMenuCmd))
										return;
									//If he didn't choose to go back to main menu.
									else {
										//If input is numerical.
										if (numerical(lv)) {
											int option = Integer.parseInt(lv);//Parse input to integer
											//if the option is in valid range.
											if (0 < option && option <= i) {
												ds.deleteCompetitorFromCompetitionTableByLv(
														//option starts from "1" so "option-1" is the order of the competition corresponding.
														name, c.get(option - 1).getlv().name());
												System.out
														.println("Deleting successful.");
											}
											else if(option == 0){
												break;//If user chose to go backward.
											}
											else System.out.println("Please input valid command.\n" +
													"-----------------------------------");
										}
									}
									System.out.println();
								}
								break;
							case '0':
								flag = false;//Set flag to false to skipper outsider while loop.
								break;
							default:
								System.out
										.println("Please input valid command.");
								break;
							}
						} else
							return;//If user wants to go back to main menu.
					}
				} else {
					System.out
							.println("The name you input does not exist in competition table, please try again.");
				}
			} else
				return;//If user wants to go back to main menu.
		}
	}
	
	/**
	 * Print the competition result based on different levels.
	 * @param scan
	 */
	private static void printResult(Scanner scan){
		while (true) {
			System.out.println();
			System.out.println("Please choose a level:");
			System.out
					.println("--------------------------------------------------------------------------------");
			//Print all levels by order from LEVEL enumeration.
			int i = 0;
			for (LEVEL l : LEVEL.values()) {
				System.out.println("" + (i + 1) + ". " + l.toString() + ".");
				i++;
			}
			System.out
					.println("--------------------------------------------------------------------------------");
			System.out.println(goMainMenu);
			String lv = scan.next();
			//If user chose to go back to main menu.
			if (lv.equalsIgnoreCase(mainMenuCmd)) {
				return;
			}
			//If user didn't choose to go back to main menu.
			else {
				//If input is numerical.
				if (numerical(lv)) {
					int option = Integer.parseInt(lv);//Parse input to integer representing the order of level.
					//If input is in valid range.
					if (0 < option && option <= LEVEL.values().length) {
						String level = LEVEL.values()[option-1].name();//Get the name of level user chose.
						String lvString = LEVEL.values()[option-1].toString();//Get the output string of level user chose.
						System.out.print("\n\n\n\n");
						System.out.println("*********************************************************");
						System.out.println("*  Notice this is a random result, NOT from database.   *");
						System.out.println("*********************************************************");
						System.out.println();
						System.out.println("The competition result of level "+lvString+" is:");
						/**
						 * Notice that the @ds.getCompetitionByLv method only generate random result for one time.
						 * In other words, this portion is the @ONLY_TIME during the whole program to generate a random 
						 * result based on the current record information which is without any score or with old score from database.
						 */
						List<Competition> randomScoreCompetitions = ds.getCompetitionByLv(level);
						//Display all competitions based on the level user chose.
						ds.printCompetitions(randomScoreCompetitions);
						/**
						 * Reusing those methods or interface below from Yuanyuan Kang.
						 * For more details please go check the corresponding class.
						 */
						ReusingMethodsInterface rmi = new ReusingMethods();//Initialize a interface.
						System.out.println(rmi.printWinner(randomScoreCompetitions));//Print the winner student in particular level.
						System.out
						.println("--------------------------------------------------------------------------------");
						//Get the competition list divided by school.
						List<List<Competition>> n = rmi.divide(randomScoreCompetitions);
						System.out.println(rmi.toString(n));//Print the winning school in particular level.
						System.out.println();
						System.out
						.println("--------------------------------------------------------------------------------");
						while (true) {
							System.out
									.println("For saving these random results to database please enter \"y\". "
											+ "Entering \"n\" for going backward.\n"
											+ goMainMenu);
							String save = scan.next();
							if (save.equalsIgnoreCase(mainMenuCmd)) {
								return;//If user wants to go back to main menu.
							} else {
								if (save.equalsIgnoreCase("y")) {
									//update these random results to database.
									ds.updateResult(randomScoreCompetitions);
									System.out.println("Updating successful.");
									break;
								} else if (save.equalsIgnoreCase("n")) {
									break;//If user does't want to update them.
								} else {
									System.out
											.println("please input valid command.");
								}
							}
						}
					} else
						System.out.println("Please input valid command.");
				}else
					System.out.println("Please input valid command.");
			}
		}
	}
	
	/**
	 * This method will get particular level competition result from database instead of generating randomly.
	 * And then it will figure out the winner and display the result.
	 * @param scan
	 */
	private static void printResultByLv(Scanner scan){
		while (true) {
			System.out.println();
			System.out.println("Please choose a level:");
			System.out
					.println("--------------------------------------------------------------------------------");
			//Print all levels by order from LEVEL enumeration.
			int i = 0;
			for (LEVEL l : LEVEL.values()) {
				System.out.println("" + (i + 1) + ". " + l.toString() + ".");
				i++;
			}
			System.out
					.println("--------------------------------------------------------------------------------");
			System.out.println(goMainMenu);
			String lv = scan.next();
			//If user chose to go back to main menu.
			if (lv.equalsIgnoreCase(mainMenuCmd)) {
				return;
			}
			//If user didn't choose to go back to main menu.
			else {
				//If input is numerical.
				if (numerical(lv)) {
					int option = Integer.parseInt(lv);//Parse input to integer representing the order of level.
					//If input is in valid range.
					if (0 < option && option <= LEVEL.values().length) {
						String level = LEVEL.values()[option-1].name();//Get the name of level user chose.
						String lvString = LEVEL.values()[option-1].toString();//Get the output string of level user chose.
						System.out.print("\n\n\n\n");
						System.out.println("**********************************************************************");
						System.out.println("* Notice this result below is from database, NOT randomly generated. *");
						System.out.println("**********************************************************************");
						System.out.println();
						System.out.println("The competition result of level "+lvString+" is:");
						/**
						 * Notice that the @ds.getCompetitionFromDbByLv method only get result from database for displaying.
						 * It is different point with which the method @printResult has above.
						 */
						List<Competition> competitions = ds.getCompetitionFromDbByLv(level);
						//Display all competitions based on the level user chose.
						ds.printCompetitions(competitions);
						/**
						 * Reusing those methods or interface below from Yuanyuan Kang.
						 * For more details please go check the corresponding class.
						 */
						ReusingMethodsInterface rmi = new ReusingMethods();//Initialize a interface.
						System.out.println(rmi.printWinner(competitions));//Print the winner student in particular level.
						System.out
						.println("--------------------------------------------------------------------------------");
						//Get the competition list divided by school.
						List<List<Competition>> n = rmi.divide(competitions);
						System.out.println(rmi.toString(n));//Print the winning school in particular level.
						System.out.println();
						System.out
						.println("--------------------------------------------------------------------------------");
					} else
						System.out.println("Please input valid command.");
				}else
					System.out.println("Please input valid command.");
			}
		}
	}
}
 