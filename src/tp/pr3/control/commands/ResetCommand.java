package tp.pr3.control.commands;

import java.util.Scanner;
import tp.pr3.logic.multigames.Game;

/**
 * Contains the information and implementation of the reset command.
 */
public class ResetCommand extends NoParamsCommand
{
	private static final String RESET_HELP = "Reset: Start a new game";
	private static final String COMMAND_INFO = "reset";
	
	/**
	 * Calls the constructor of the superclass to create a command without parameters
	 * with the info corresponding to the reset command.
	 */
	public ResetCommand()
	{
		super(COMMAND_INFO, RESET_HELP);
	}
	
	/**
	 * Resets the game to its initial values.
	 */
	public boolean execute(Game game, Scanner in)
	{
		game.reset();
		return true;
	}
}