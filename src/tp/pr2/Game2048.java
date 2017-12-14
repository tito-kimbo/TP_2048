package tp.pr2;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Rules2048;

/**
*	Entry point of the game. Creates a controller with the specified parameters and runs the game.
*/
public class Game2048 
{

	private static final Rules2048 rules = new Rules2048();
	private static final int DEFAULT_SIZE = 4;
	private static final int DEFAULT_INIT_CELLS = 2;
	private static final long DEFAULT_SEED = 123946871;

	/**
	*	Creates the Controller with the given (or default) parameters and executes the 2048 game loop.
	*/
    public static void main(String[] args)
	{
		int size = DEFAULT_SIZE, initCells  = DEFAULT_INIT_CELLS;
		long seed = DEFAULT_SEED;

		switch(args.length) {
		case 0: {} break;
		case 1: 
		{			
			size = Integer.parseInt(args[0]);
		} break;
		case 2: {
			size = Integer.parseInt(args[0]);
			initCells = Integer.parseInt(args[1]);
		} break;
		case 3: {
			size = Integer.parseInt(args[0]);
			initCells = Integer.parseInt(args[1]);
			seed = Long.parseLong(args[2]);
		} break;
		default:
		{
			System.out.println("Too many arguments");
		}
		}
		
		if(args.length <= 3) 
		{
			if(size <= 0) { size = DEFAULT_SIZE; }
			if(initCells <= 0) { initCells = DEFAULT_INIT_CELLS; }
			
			Controller controller = new Controller(rules, size, initCells, seed);			  		
			controller.run();
		}
    }
};
