package tp.pr3;

import tp.pr3.control.Controller;
import tp.pr3.logic.multigames.GameType;
import tp.pr3.exceptions.InvalidNumberOfArgumentsException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;

/**
*	Entry point of the game. Creates a controller with the specified parameters and runs the game.
*/
public class Game2048 
{

	private static final GameType GAME = GameType.ORIG;
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
				case 0: break;
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
					throw new InvalidNumberOfArgumentsException("Too many arguments!");
				}
			}
			if(size < 2)
			{
				throw new IllegalArgumentException("Board size must be greater or equal to two! Using default values.");
			}
			if(initCells <= 0)
			{
				throw new IllegalArgumentException("The number of initial cells must be positive! Using default values.");
			}
		}
		catch(NumberFormatException e) 
		{
			System.out.println("Invalid parameter format. Using default values.");
			size = DEFAULT_SIZE;
			initCells = DEFAULT_INIT_CELLS;
			seed = DEFAULT_SEED;
		}

		catch(RuntimeException e)
		{
			System.out.println(e.getMessage());
			size = DEFAULT_SIZE;
			initCells = DEFAULT_INIT_CELLS;
			seed = DEFAULT_SEED;
		}
		
		if(args.length <= 3) 
		{
			Controller controller = new Controller(GAME, size, initCells, seed);			  		
			controller.run();
		}
    }
};
