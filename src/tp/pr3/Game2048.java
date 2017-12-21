package tp.pr3;

import tp.pr3.control.Controller;
import tp.pr3.logic.multigames.Rules2048;
import tp.pr3.exceptions.TooManyArgumentsException;
import java.lang.NumberFormatException;

/**
*	Entry point of the game. Creates a controller with the specified parameters and runs the game.
*/
public class Game2048 
{

	private static final Rules2048 RULES = new Rules2048();
	private static final int DEFAULT_SIZE = 4;
	private static final int DEFAULT_INIT_CELLS = 2;
	private static final long DEFAULT_SEED = 123946871;

	/**
	*	Creates the Controller with the given (or default) parameters and executes the 2048 game loop.
	*/
    public static void main(String[] args)
	{
		int size = DEFAULT_SIZE, initCells = DEFAULT_INIT_CELLS;
		long seed = DEFAULT_SEED;

		try 
		{
			switch(args.length)
			{
				case 0: {} break;
				case 1: 
				{			
					size = Integer.parseInt(args[0]);
				} break;
				case 2: 
				{
					size = Integer.parseInt(args[0]);
					initCells = Integer.parseInt(args[1]);
				} break;
				case 3: 
				{
					size = Integer.parseInt(args[0]);
					initCells = Integer.parseInt(args[1]);
					seed = Long.parseLong(args[2]);
				} break;
				default:
				{
					throw new TooManyArgumentsException("Too many arguments.");
				}
			}
		}
		catch(NumberFormatException e) 
		{
			System.out.println("Invalid parameter format. Using default values.");
		}
		
		catch(TooManyArgumentsException e) 
		{
			System.out.println(e.getMessage());
		}
		
		if(args.length <= 3) 
		{
			Controller controller = new Controller(RULES, size, initCells, seed);			  		
			controller.run();
		}
    }
};
