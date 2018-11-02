
/*=============================================================================  
|   Source code:  Die.java
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
| 	javac Die.java
|	
| 
|  +-----------------------------------------------------------------------------  
|  
|  Description:  This class is directly taken from the sample
|				 provided in the book in section 6.9.1 .
|  
|        Input:  No input received.
|  
|       Output:  Outputs iterations of this class to the
|				 Craps class.
|  
|      Process:    
|  
|   Required Features Not Included: Uses Random library
|  
|   Known Bugs:   
|
|  *===========================================================================*/


//Random utility used to randomize dice roll
import java.util.Random;

public class Die 
{
	private Random generator;
	private int sides;
	private final int DEFAULT_SIDES = 6;
	private final int MIN_SIDES=2;
	private final int MAX_SIDES=100;
	
	public Die()
	{
		sides = DEFAULT_SIDES;
		generator = new Random();
	}
	public Die(int s)
	{
		if(s < MIN_SIDES || s > MAX_SIDES)
		{
			sides = DEFAULT_SIDES;
		}
		else
		{
		sides = s;
		}
		generator = new Random();
	}
	
	public int rollDie()
	{
		return generator.nextInt(sides) + 1;
	}
}
