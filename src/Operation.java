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
*   
* Modification History:   
* Date    Author      Version     Description   
* ----------------------------------------------------------------- 
* Oct 26, 2014 Han Chen      1.0     1.0 Version   
*/  
public class Operation {
	private static final String db = "cs549assignment3.db";
	private static DataStore ds = new DataStore(db);
	private static int lowerBound = 0;
	private static int upperBound = 100;
	private static int scoreRange = upperBound-lowerBound;
	private static String mainMenuCmd = "bm";
	private static String goMainMenu = " (Entering \""+mainMenuCmd+"\" will go back to Main Menu) ";
	
	/**
	 * 
	 */
	public static void operating(){
		InputStream is = System.in;
		Scanner scan = new Scanner(is);
		System.out.println();
		System.out.println("\nWelcom to The Wisconsin Music Association for Youngsters (WMAY)!");
		System.out.println();
		while(true){
			printMainMenu();
			switch(scan.next().charAt(0)){
			case '1':
				ds.printCompetitors(ds.getAllCompetitor());
				break;
			case '2':
				ds.printCompetitions(ds.getAllCompetition());
				break;
			case '3':
				addCompetitor(scan);
				break;
			case '4':
				deleteCompetitor(scan);
				break;
			case '5':
				addCompetitorToCompetition(scan);
				break;
			case '6':
				deleteCompetitorFromCompetition(scan);
				break;
			case '7':
				printResult(scan);
				break;
			case '0':
				System.out.println("GOOD BYE!");
				System.exit(0);
			default:
				System.out.println("Please input valid command.");
				break;
			}
		}
	}
	
	/**
	 * 
	 */
	private static void printMainMenu(){
		System.out.println();
		System.out.println(
				"------------------Main Menu--------------------\n"+
				"Please choose one option:\n"+
				"---------------------------------------------------\n"+
				"1. List all competitors.\n"+
				"2. List all competitions.\n"+
				"3. Add new competitors.\n"+
				"4. Delete a competitors.\n"+
				"5. Add a competitor to a competiton.\n"+
				"6. Delete a competitor from a competiton.\n"+
				"7. Generate a competiton with results.\n"+
				"0. Exit.\n"+
				"----------------------------------------------------\n"+
				"Tips: "+goMainMenu+" whenever you want\n"+
				"----------------------------------------------------");
	}
	
	/**
	 * 
	 * @return random score
	 */
	private static int getScore(){
		return (new Random().nextInt(scoreRange))+ lowerBound;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private static boolean numerical(String input){
		return input.matches("\\d+");
	}
	
	/**
	 * @param scan
	 */
	private static void addCompetitor(Scanner scan){
		while(true){
			System.out.println("please input student name "+goMainMenu+":");
			String name = scan.next();
			if (!name.equalsIgnoreCase(mainMenuCmd)) {
				boolean isExisting = ds
						.checkExistingOfCompetitor(name);
				if (isExisting) {
					System.out
							.println("Name has already been used, try others please "+goMainMenu+":");
				} else {
					System.out.println("please input school "+goMainMenu+":");
					String school = scan.next();
					if (!school.equalsIgnoreCase(mainMenuCmd)) {
						ds.addCompetitor(new Competitor(name, school));
						System.out.println("Successful Adding.");
						break;
					}
					else break;
				}
			}
			else break;
		}
	}
	
	/**
	 * 
	 * @param scan
	 */
	private static void addCompetitorToCompetition(Scanner scan){
		while(true)
		{
			ds.printCompetitors(ds.getAllCompetitor());
			System.out.println("\nPlease input a name of competitor who you want to add to competition:\n"+goMainMenu);
			String name = scan.next();
			if(!name.equalsIgnoreCase(mainMenuCmd))
			{
				if(ds.checkExistingOfCompetitor(name))
				{					
					while (true) 
					{
						System.out
								.println("\nPlease input the level of competiton which you want to add this competitor in it.");
						
						for (LEVEL l : LEVEL.values())
						{
							System.out.println("\"" + (l.ordinal() + 1) + "\" for "
									+ l.toString());
						}
						System.out.println("\"0\" for going backward\n" + goMainMenu);
						
						String lv = scan.next();
						if (!lv.equalsIgnoreCase(mainMenuCmd))
						{

							if (numerical(lv))
							{
								int index = Integer.parseInt(lv);
								if (0 <index && index <= LEVEL.values().length) 
								{
									String level = LEVEL.values()[index-1].name();
									if (!ds.checkExistingOfCompetitionWithLv(name, level)) {
										int score =getScore();
										Competitor c = ds
												.getCompetitorFromCompetitorTableByName(name);
										ds.addCompetitorToCompetition(new Competition(
												score, c.getSchool(), c
														.getName(), level));
										System.out
												.println("Adding successful.");
										System.out.println();
										ds.printCompetitions(ds.getAllCompetition());
										//return;
									}
									else{
										System.out.println(name+" has already enrolled in "+LEVEL.values()[index-1].name()+" competition");
									}
								}
								else if(index == 0){
									break;
								}
								else {
									System.out.println("Please input valid command.");
								}
							} else {
								System.out.println("Please input valid command.");
							}
						}
						else{
							return;
						}
					}
				}
				else{
					System.out.println("System cannot find this student, please check the name you input again.");
				}
			}
			else{
				return;
			}
		}
	}
	
	/**
	 * 
	 * @param scan
	 */
	private static void deleteCompetitor(Scanner scan){
		while (true) {
			ds.getAllCompetitor();
			System.out
					.println("please input the name of competitor whom you want to delete:\n"
							+ goMainMenu);
			String name = scan.next();
			if (!name.equalsIgnoreCase(mainMenuCmd)) {
				if (ds.checkExistingOfCompetitor(name)) {
					while (true) {
						System.out
								.println("Deleting this competitor will also delete him from competition.");
						System.out
								.println("Are you sure you want to delete this competitor?\n"
										+ " \"y\" for yes, \"n\" for going backward, "
										+ goMainMenu);
						String conf = scan.next();
						if (!conf.equalsIgnoreCase(mainMenuCmd)) {
							if (conf.equalsIgnoreCase("y")) {
								ds.deleteCompetitorFromCompetitorTable(name);
								System.out
										.println("Deleting from competitor table successful.");
								System.out.println();
								if (ds.checkExistingOfCompetition(name)) {
									ds.deleteCompetitorFromCompetitionTable(name);
									System.out
											.println("Deleting from competition table successful.");
									System.out.println();
								}
								else{
									System.out
											.println("There is no record of this competitor in competition table.");
									System.out.println();
								}
								ds.printCompetitors(ds.getAllCompetitor());
							} else if (conf.equalsIgnoreCase("n")) {
								break;
							} else {
								System.out
										.println("please input valid command.");
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
	}
	
	/**
	 * 
	 * @param scan
	 */
	private static void deleteCompetitorFromCompetition(Scanner scan){
		while (true) {
			ds.printCompetitions(ds.getAllCompetition());
			System.out
					.println("Please input name for deleting him from competition:\n"
							+ goMainMenu);
			String name = scan.next();
			if (!name.equalsIgnoreCase(mainMenuCmd)) {
				if (ds.checkExistingOfCompetition(name)) {
					boolean flag = true;
					while (flag) {
						System.out
								.println("Do you want to delete all competitions of him "
										+ "or just want to delete his record of particular level?\n"
										+ "Enter \"1\" for deleting all record;\n"
										+ "Enter \"\2\" for deleting him from particular level;\n"
										+ "Enter \"0\" for going backward."
										+ goMainMenu);
						String cmd = scan.next();
						if (!cmd.equalsIgnoreCase(mainMenuCmd)) {
							switch (cmd.charAt(0)) {
							case '1':
								while (true) {
									System.out
											.println("Are you sure you want to delete all records of this competitior from competition table?\n"
													+ " \"y\" for yes, \"n\" for going backward, "
													+ goMainMenu);
									String conf = scan.next();
									if (!conf.equalsIgnoreCase(mainMenuCmd)) {
										if (conf.equalsIgnoreCase("y")) {
											ds.deleteCompetitorFromCompetitionTable(name);
											System.out
													.println("Deleting all records of "
															+ name
															+ " from competition successful.");
											flag = false;
											break;
										} else if (conf.equalsIgnoreCase("n")) {
											break;
										} else {
											System.out
													.println("Please input valid command.");
										}
									} else
										return;
								}
								break;
							case '2':
								while (true) {
									System.out
											.println("The levels of competiton that "
													+ name + " has enrolled:\n" +
															"----------------"+name+"----------------");
									List<Competition> c = ds
											.getCompetitionsFromCompetitionTableByName(name);
									int i = 0;
									for (Competition n : c) {
										System.out.println(++i + ". "
												+ n.getLevel()+".");
									}
									System.out.println("0. Going backward.\n" +
											"-----------------------------------");
									System.out
											.println("Please select one for deleting.");
									String lv = scan.next();
									if (lv.equalsIgnoreCase(mainMenuCmd))
										return;
									else {
										if (numerical(lv)) {
											int option = Integer.parseInt(lv);
											if (0 < option && option <= i) {
												ds.deleteCompetitorFromCompetitionTableByLv(
														name, c.get(option - 1)
																.getLevel());
												System.out
														.println("Deleting successful.");
											}
											else if(option == 0){
												break;
											}
											else System.out.println("Please input valid command.\n" +
													"-----------------------------------");
										}
									}
									System.out.println();
								}
								break;
							case '0':
								flag = false;
								break;
							default:
								System.out
										.println("Please input valid command.");
								break;
							}
						} else
							return;
					}
				} else {
					System.out
							.println("The name you input does not exist in competition table, please try again.");
				}
			} else
				return;
		}
	}
	
	/**
	 * 
	 * @param scan
	 */
	private static void printResult(Scanner scan){
		while (true) {
			System.out.println();
			System.out.println("Please choose a level:");
			System.out
					.println("--------------------------------------------------------------------------------");
			int i = 0;
			for (LEVEL l : LEVEL.values()) {
				System.out.println("" + (i + 1) + ". " + l.toString() + ".");
				i++;
			}
			System.out
					.println("--------------------------------------------------------------------------------");
			System.out.println(goMainMenu);
			String lv = scan.next();
			if (lv.equalsIgnoreCase(mainMenuCmd)) {
				return;
			} else {
				if (numerical(lv)) {
					int option = Integer.parseInt(lv);
					if (0 < option && option <= LEVEL.values().length) {
						String level = LEVEL.values()[option-1].name();
						String lvString = LEVEL.values()[option-1].toString();
						System.out.println("The competition result of level "+lvString+" is:");
						//TODO: display result.
						ds.printCompetitions(ds.getCompetitionByLv(level));
						/**
						 * Reusing
						 */
						ReusingMethodsInterface rmi = new ReusingMethods();
						System.out.println(rmi.printWinner(ds.getCompetitionByLv(level)));
						System.out
						.println("--------------------------------------------------------------------------------");
						List<List<Competition>> n = rmi.divide(ds.getCompetitionByLv(level));
						System.out.println(rmi.toString(n));
					} else
						System.out.println("Please input valid command.");
				}else
					System.out.println("Please input valid command.");
			}
		}
	}
}
 