package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.control.Controller;
import tp.pr3.control.commands.Command;
import tp.pr3.logic.GameType;
import tp.pr3.logic.multigames.Game;
import java.lang.NumberFormatException;
import tp.pr3.exceptions.TooManyArgumentsException;

/**
 * Contains the information and implementation of the command play.
 */
public class PlayCommand extends Command
{

	private static final int DEFAULT_SIZE = 4, DEFAULT_CELLS = 2;

	private static final String PLAY_HELP = "Play <game>: Change play mode to one of the following: original, fib and inverse.";
	private static final String COMMAND_INFO = "play";
	private static final String ASK_FOR_SIZE = "Please enter the size of the board: ";
	private static final String ASK_FOR_CELLS = "Please enter the number of initial cells: ";
	private static final String ASK_FOR_SEED = "Please enter the initial seed: ";
	
	private GameType type = null;

	private int size, cells;
	private long seed;

	/**
	 * Calls the constructor of the superclass to create a command
	 * with the info corresponding to the play command. Also initializes non-static variables.
	 */
	public PlayCommand()
	{
		super(COMMAND_INFO, PLAY_HELP);
		type = null;
	}
	
	/**
	 * Changes the current game based on the user-given parameters.
	 */
	public boolean execute(Game game, Controller controller) 
	{
		if(type != null) 
		{
			Scanner in = controller.getScanner();
			String line = ""; //For safety
			
			size = 0;
			cells = 0;
			
			while(size <= 0)
			{
				System.out.print(ASK_FOR_SIZE);
				line = in.nextLine();
				
				if(!line.equals(""))
				{
					try
					{
						size = Integer.parseInt(line);
					}
					catch (NumberFormatException e) {
						System.out.println("Please introduce a single positive integer");
						//THIS DOES NOT CHECK THAT THE NUMBER IS POSITIVE
						//NO ERROR MESSAGE IS DISPLAYED IN THAT CASE
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
				System.out.print(ASK_FOR_CELLS);
				line = in.nextLine();
				
				if(!line.equals(""))
				{
				        try
					{
						cells = Integer.parseInt(line);
					}
					catch (NumberFormatException e)
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

			System.out.print(ASK_FOR_SEED);
			line = in.nextLine();
			try
			{
				seed = Long.parseLong(line);
			}
			catch (NumberFormatException e) 
			{
				System.out.println("Not a valid seed");
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
		
		return true;
	}

	/**
	 * Parses the play command, taking into account.
	 */
	public Command parse(String [] commandWords, Scanner in) throws TooManyArgumentsException
	{

		Command com = null;
		GameType gameType = null;
		if(commandWords[0].equals("play")) 
		{
			if(commandWords.length > 2)
			{
				throw new TooManyArgumentsException("This command only accepts one parameter!");
			}
			else if(commandWords.length == 2)
			{
				//ADD EXCEPTION FOR NOT ENOUGH ARGUMENTS???
				com = this;
				
				for (GameType t : GameType.values()) 
					{
						if(commandWords[1].equals(t.toString())) gameType = t;
					}
			}
		}
		
		type = gameType;
		return com;
	}
	
};
