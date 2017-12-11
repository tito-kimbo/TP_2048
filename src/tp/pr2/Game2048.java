package tp.pr2;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Rules2048;

/**move 
*	Entry point of the game. Creates a controller with the specified parameters and runs the game.
*/
public class Game2048 
{

	private static final Rules2048 rules = new Rules2048();
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

		switch(args.length) {
		case 0: {
			size = DEFAULT_SIZE;
			initCells = DEFAULT_INIT_CELLS;
			seed = DEFAULT_SEED;
		} break;
		case 1: {
			size = Integer.parseInt(args[0]);
			initCells = DEFAULT_INIT_CELLS;
			seed = DEFAULT_SEED;
		} break;
		case 2: {
			size = Integer.parseInt(args[0]);
			initCells = Integer.parseInt(args[1]);
			seed = DEFAULT_SEED;
		} break;
		default: {
			size = Integer.parseInt(args[0]);
			initCells = Integer.parseInt(args[1]);
			seed = Long.parseLong(args[2]);
		}
		}
		Controller controller = new Controller(rules, size, initCells, seed);			  		
		controller.run();
    }
};
