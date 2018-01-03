package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.control.Controller;
import tp.pr3.logic.multigames.Game;

/**
 * Contains the information and implementation of the exit command.
 *
 */
public class ExitCommand extends NoParamsCommand
{
	private static final String exitHelp = "Exit: Terminate the program";
	private static final String commandInfo = "exit";
	
	/**
	 * Calls the constructor of the superclass to create a command without parameters
	 * with the info corresponding to the exit command.
	 */
	public ExitCommand()
	{
		super(commandInfo, exitHelp);
	}

	/**
	 * Exits the game.
	 */
	public boolean execute(Game game, Controller controller)
	{
		controller.exit();

		return false;
	}
}
