package tp.pr3.control.commands;

import java.util.Scanner;
import tp.pr3.logic.multigames.Game;
import tp.pr3.exceptions.GameOverException;

/**
 * Contains the information and implementation of the exit command.
 *
 */
public class ExitCommand extends NoParamsCommand
{
	private static final String EXIT_HELP = "Exit: Terminate the program.";
	private static final String COMMAND_INFO = "exit";
	
	/**
	 * Calls the constructor of the superclass to create a command without parameters
	 * with the info corresponding to the exit command.
	 */
	public ExitCommand()
	{
		super(COMMAND_INFO, EXIT_HELP);
	}

	/**
	 * Exits the game.
	 */
	public boolean execute(Game game, Scanner in) throws GameOverException
	{
		throw new GameOverException();
		
	}
}
