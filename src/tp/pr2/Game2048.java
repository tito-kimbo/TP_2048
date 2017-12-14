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
		String sizeStr = "", initCellsStr = "", seedStr = "";
		int size = DEFAULT_SIZE, initCells = DEFAULT_INIT_CELLS;
		long seed = DEFAULT_SEED;

		switch(args.length) {
		case 0: {} break;
		case 1: 
		{			
			sizeStr = args[0];
		} break;

		case 2: {
			sizeStr = args[0];
			initCellsStr = args[1];
		} break;

		case 3: {
			sizeStr = args[0];
			initCellsStr = args[1];
			seedStr = args[2];
		} break;
		default:

		{
			System.out.println("Too many arguments");
		}
		}
		
		if(args.length <= 3) 
		{
			if(sizeStr.length() > 0)
			{
				if(sizeStr.matches("[0123456789]+")) { size = Integer.parseInt(sizeStr); }
				else {size = 0;}

				if(size == 0)
				{
				System.out.println("Not a valid size, using default value " + DEFAULT_SIZE);
				size = DEFAULT_SIZE;
				}
			}

			

			if(initCellsStr.length() > 0)
			{
				if(initCellsStr.matches("[0123456789]+")) { initCells = Integer.parseInt(initCellsStr); }
				else {initCells = 0;}

				if(initCells == 0)
				{
				System.out.println("Not a valid number, using default value " + DEFAULT_INIT_CELLS);
				initCells = DEFAULT_INIT_CELLS;
				}
			}

			

			if(seedStr.length() > 0)
			{
				if(seedStr.matches("-?[0123456789]+")) { seed = Long.parseLong(seedStr); }
				else {
					System.out.println("Not a valid seed, using default value " + DEFAULT_SEED);
					seed = DEFAULT_SEED;
				}
			}
			
			Controller controller = new Controller(rules, size, initCells, seed);			  		
			controller.run();
		}
    }
};
