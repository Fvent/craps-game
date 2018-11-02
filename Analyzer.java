

/*=============================================================================  
|   Source code:  Analyzer.java
|	Author:  Francis Ventura
|   Student ID:  5147088
|  	Assignment:  Program #3 - Craps Analyzer 
|  
|	Course:  COP 3337 (Intermediate Programming)
|   Section:  U08
|	Instructor:  William Feild  
|   Due Date:  9 October 2018, by the beginning of class 
|
|	I hereby certify that this collective work is my own
|	and none of it is the work of any other person or entity.
|	
|	
|	______________________________________ [Signature]
|  
|	Language:  Java
|  	Compile/Run: 
| 	javac Die.java Craps.java Analyzer.java
|	java Analyzer
| 
|  +-----------------------------------------------------------------------------  
|  
|  Description:  Prints data regarding the outcome of Craps games
|				 specified by the user.
|  
|        Input:  Input is passed from the Craps class and the user
|				 is prompted to type the number of games to be
|				 played.
|  
|       Output:  Prints game statistics on the screen
|  
|      Process: 1. Validates user input.
|				2. Runs game specified number of times.
|				3. Counts wins and losses.
|				4. Performs operations.
|				5. Prints statistics.
|
|				Answers 6, 10, 12 and 15 obtained from the following site:   
|				//obtained from https://www.mscs.dal.ca/~hoshino/book/ch20craps.pdf
|  				
|   Required Features Not Included:  
|  
|   Known Bugs:   
|
|  *===========================================================================*/

//Scanner utility used for user input
import java.util.Scanner; 

public class Analyzer
{

	public static void main(String[] args)
	{
		final int MAX_GAMES = 1000000; //maximum number of allowed games
		final int MIN_GAMES = 1; //minimum number of allowed games
		final int MAX_ROLLS_INDEX = 21; 
		
		Craps game = new Craps();
		int winCounter[] = new int[MAX_ROLLS_INDEX];
		
		int lossCounter = 0;
		int totalRolls = 0;
		int highestRollCount = 0;
		int totalWins = 0;
		int comingOutLoss = 0;
		
		int allGames;
		Scanner in = new Scanner(System.in);
		do{
		
		do{
		in = new Scanner(System.in);
		System.out.print("Input number of Craps games to be played.");
		System.out.println(" (Integer between 1-1000000).");
		}while(!in.hasNextInt());
		
		allGames = in.nextInt();
		}while(allGames > MAX_GAMES || allGames < MIN_GAMES);
		
		int count = 1;
		
		while(count <= allGames)
		{
			String result = game.playGame();
			int rollCount = game.getRollCount();
			
			if(rollCount > highestRollCount)
			{
				highestRollCount=rollCount;
			}
			if(result.equals("WIN") && rollCount < MAX_ROLLS_INDEX)
			{
				winCounter[rollCount - 1]++;
				totalWins++;
			}
			else if(result.equals("WIN") && rollCount >= MAX_ROLLS_INDEX)
			{
				winCounter[MAX_ROLLS_INDEX - 1]++;
				totalWins++;
			}
			else if(result.equals("LOSS") && rollCount == 1)
			{
				comingOutLoss++;
				lossCounter++;
			}
			else if(result.equals("LOSS"))
			{
				lossCounter++;
			}
			totalRolls = totalRolls + rollCount;
			count++;
		}
		
		int comingOutGames = winCounter[0] + comingOutLoss;
		
		printSummary(allGames, totalRolls, highestRollCount);
		winStatistics(totalWins, allGames, winCounter,comingOutGames);
		endStatistics(allGames, comingOutGames);
		summaryOfLengths(MAX_ROLLS_INDEX, winCounter);
		
	}
	
	/**
	 * Prints game summary.
	 * @param totalGames
	 * @param totalRolls
	 * @param highestRollCount
	 */
	public static void printSummary(int totalGames, int totalRolls, int highestRollCount)
	{
		System.out.println();
		System.out.println("-Summary of Game Statistics-");
		System.out.println("(1) Total Games : " + totalGames);
		System.out.println("(2) Total Rolls : " + totalRolls);
		System.out.printf("(3) Average Rolls : %.4f \n", (double) totalRolls / totalGames);
		System.out.println("(4) Longest Game (in rolls): " + highestRollCount);
		System.out.println();
	}
	
	/**
	 * Prints winning statistics
	 * @param totalWins
	 * @param allGames
	 * @param winCounter
	 * @param comingOutGames
	 */
	public static void winStatistics(int totalWins, int allGames, int[]winCounter,int comingOutGames)
	{
		final double WINNING_ODDS = 0.4929; //obtained from URL at heading
		final double WIN_ROLL_ONE = 0.6666; //obtained from URL at heading
		final double END_ROLL_ONE = 0.3333; //obtained from URL at heading
		double winRate = (double) totalWins / allGames;
		if(comingOutGames == 0)
		{
			comingOutGames = 1;
		}
		System.out.println("-Summary of Win Statistics- (5-13)");
		System.out.println("Stat\t\t\tGames\t\tOutcome\t\tExpected");
		System.out.print("Total Wins");
		System.out.print("\t\t" + totalWins + "\t\t");
		System.out.printf("%.4f", winRate);
		System.out.print("\t\t" + WINNING_ODDS);
		System.out.print("\nComing Out Wins");
		System.out.print("\t\t" + winCounter[0] + "\t\t");
		System.out.printf("%.4f", (double) winCounter[0] / comingOutGames);
		System.out.print("\t\t" + WIN_ROLL_ONE);
		System.out.print("\nComing Out Games");
		System.out.print("\t" + comingOutGames + "\t\t");
		System.out.printf("%.4f", (double) comingOutGames / allGames);
		System.out.print("\t\t" + END_ROLL_ONE);
		System.out.println("\n");
	}
	
	/**
	 * Prints ending statistics
	 * @param allGames
	 * @param comingOutGames
	 */
	public static void endStatistics(int allGames, int comingOutGames)
	{
		final double END_ROLL_ONE = 0.3333; //obtained from URL at heading
		System.out.println("-Summary of Ending Statistics- (14-16)");
		System.out.println("Stat\t\t\tGames\t\tOutcome\t\tExpected");
		System.out.print("Continuing on Game\t");
		System.out.print(allGames - comingOutGames + "\t\t");
		System.out.printf("%.4f\t\t", (double) (allGames - comingOutGames) / allGames);
		System.out.printf("%.4f", 1 - END_ROLL_ONE);
		System.out.println("\n");
	}
	
	/**
	 * Prints a tally of the length of the games
	 * @param MAX_ROLLS_INDEX
	 * @param winCounter
	 */
	public static void summaryOfLengths(int MAX_ROLLS_INDEX, int[] winCounter)
	{
		System.out.println("-Summary of Game Lengths in Rolls- (17)");
		int index = 0;
		do{
			if(index == MAX_ROLLS_INDEX - 1)
			{
				System.out.println("Wins at roll " + MAX_ROLLS_INDEX + "+" + " : " + winCounter[index]);
			}
			else
			{
			System.out.println("Wins at roll " + (index + 1) + " : " + winCounter[index]);
			}
			index++;
		}while(index < MAX_ROLLS_INDEX);
	}
}
