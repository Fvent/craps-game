
/*=============================================================================  
|   Source code:  Craps.java
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
| 	javac Craps.java
| 
|  +-----------------------------------------------------------------------------  
|  
|  Description:  Class takes care of the game itself.
|  
|        Input:  Uses the Die class to create two objects.
|  
|       Output:  Provides an object to be used in the 
|				 Analyzer class along with its methods.
|  
|      Process:    
|  
|   Required Features Not Included:  
|  
|   Known Bugs:   
|
|  *===========================================================================*/

public class Craps
{
	private Die die1 = new Die();
	private Die die2 = new Die();
	private int num1 = 0;
	private int num2 = 0;
	private int roll = 0;
	private int rollCount = 0;
	private int point = 0;
	private final int CONTINUE = 0;
	private final int WIN = 1;
	private final int LOSE = 2;
	private final int SNAKE_EYES = 2;
	private final int ACE_DEUCE = 3;
	private final int NATURAL = 7;
	private final int YO_LEVEN = 11;
	private final int BOXCARS = 12;
	public Craps()
	{
		num1 = die1.rollDie();
		num2 = die2.rollDie();
		roll = num1 + num2;
		rollCount = 1;
	}
	
	/**
	 * Establishes whether a game is a win or loss
	 * @return "WIN" or "LOSS"
	 */
	public String playGame()
	{
		resetGame();
		int status = CONTINUE;
		do
		{
			if(rollCount == 1)
			{
				if(roll == NATURAL || roll == YO_LEVEN)
				{
					status = WIN;
				}
				else if(roll == SNAKE_EYES || roll == ACE_DEUCE || roll == BOXCARS)
				{
					status = LOSE;
				}
				else
				{
					status = CONTINUE;
					point = roll;
					continueGame();
				}
			}
			else
			{
				if(roll == point)
				{
					status = WIN;
				}
				else if(roll == NATURAL)
				{
					status = LOSE;
				}
				else
				{
					status = CONTINUE;
					continueGame();
				}
			}
		}while(status == CONTINUE);
		
		if(status == WIN)
		{
			return "WIN";
		}
		else
		{
			return "LOSS";
		}
		
	}
	
	/**
	 * Restarts the game.
	 */
	private void resetGame()
	{
		num1 = die1.rollDie();
		num2 = die2.rollDie();
		roll = num1 + num2;
		rollCount = 1;
		point = 0;
	}
	
	/**
	 * Continues the game if roll is not WIN or LOSS
	 */
	private void continueGame()
	{
		num1 = die1.rollDie();
		num2 = die2.rollDie();
		roll = num1 + num2;
		rollCount++;
	}
	
	public int getRollCount()
	{
		return rollCount;
	}
	
	public int getLastRoll()
	{
		return roll;
	}
	
	public int getPoint()
	{
		return point;
	}
}
