package tp.pr1;

import tp.pr1.Controller;

/**
*	Entry point of the game. Creates a controller with the specified parameters and runs the game.
*/
public class Game2048 
{

	private static final int DEFAULT_SIZE = 4;
	private static final int DEFAULT_INIT_CELLS = 2;
	private static final long DEFAULT_SEED = 123946871;

	/**
	*	Creates the Controller with the given (or default) parameters and executes the 2048 game loop
	*/
    public static void main(String[] args)
	{
		int size, initCells;
		long seed;

		if(args.length < 3) 
		{
				size = DEFAULT_SIZE;
				initCells = DEFAULT_INIT_CELLS;
				seed = DEFAULT_SEED;		
		}
		else 
		{
			size = Integer.valueOf(args[0]);
			initCells = Integer.valueOf(args[1]);
			seed = Long.valueOf(args[2]);
		}
	      
		Controller controller = new Controller(size, initCells, seed);			  		
		controller.run();
    }
};
