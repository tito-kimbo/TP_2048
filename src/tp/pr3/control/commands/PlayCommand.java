package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.control.Controller;
import tp.pr3.control.commands.Command;
import tp.pr3.logic.GameType;
import tp.pr3.logic.multigames.Game;

/**
 * Contains the information and implementation of the command play.
 */
public class PlayCommand extends Command
{

	private static final int DEFAULT_SIZE = 4, DEFAULT_CELLS = 2;

	private static final String playHelp = "Play <game>: Change play mode to one of the following: original, fib and inverse.", commandInfo = "play";
	private static final String askForSize = "Please enter the size of the board: ";
	private static final String askForCells = "Please enter the number of initial cells: ";
	private static final String askForSeed = "Please enter the initial seed: ";
	
	private GameType type = null;

	private int size, cells;
	private long seed;

	/**
	 * Calls the constructor of the superclass to create a command
	 * with the info corresponding to the play command. Also initializes non-static variables.
	 */
	public PlayCommand()
	{
		super(commandInfo, playHelp);
		type = null;
	}
	
	/**
	 * Changes the current game based on the user-given parameters.
	 */
	public void execute(Game game, Controller controller) 
	{
		if(type != null) 
		{
			Scanner in = controller.getScanner();
			String line = ""; //For safety
			
			size = 0;
			cells = 0;
			
			while(size <= 0)
			{
				System.out.print(askForSize);
				line = in.nextLine();
				
				if(!line.equals(""))
				{
					if(line.matches("[0123456789]+")) 
					{
						size = Integer.parseInt(line);
					}
					else 
					{
						System.out.println("Please introduce a single positive integer");
					}

				}
				else
				{
					size = DEFAULT_SIZE;
					System.out.println("Using the default size of the board: " + DEFAULT_SIZE);
				}
			}
			
			while(cells <= 0)
			{
				System.out.print(askForCells);
				line = in.nextLine();
				
				if(!line.equals("")) 
				{
					if(line.matches("[0123456789]+")) 
					{
						cells = Integer.parseInt(line);
					}
					else 
					{
						System.out.println("Please introduce a single positive integer");
					}
			
				}
				else 
				{
					cells = DEFAULT_CELLS;
					System.out.println("Using the default number of initial cells: " + DEFAULT_CELLS);
				}
			}

			System.out.print(askForSeed);
			line = in.nextLine();
			if(line.matches("-?[0123456789]+")) 
			{
				seed = Long.parseLong(line);
			}
			else {
				if(!line.equals(""))
				{
					System.out.println("Not a valid seed");
				}
				seed = controller.getSeed();
				System.out.println("Using the default seed for the PRNG: " + seed);
			}			
		
			controller.setGame(type.toRules(), size, cells, seed);
			controller.setNoPrintGameState(true);
		}
		else 
		{
			System.out.println("Not a valid game type!");
			controller.setNoPrintGameState(false);
		}
	}

	/**
	 * Parses the play command, taking into account.
	 */
	public Command parse(String [] commandWords, Controller controller) 
	{

		Command com = null;
		GameType gameType = null;
		if(commandWords.length == 2 && commandWords[0].equals("play")) 
		{
			com = this;

			for (GameType t : GameType.values()) 
			{
				if(commandWords[1].equals(t.toString())) gameType = t;
			}
		}

		type = gameType;
		return com;
	}
	
};
