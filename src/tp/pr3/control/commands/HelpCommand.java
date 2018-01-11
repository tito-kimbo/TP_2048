package tp.pr3.control.commands;

import java.util.Scanner;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logic.util.CommandParser;

/**
 * Contains the information and implementation of the command help.
 */

public class HelpCommand extends NoParamsCommand
{
	private static final String HELP_HELP = "Help: Print this help message.";
	private static final String COMMAND_INFO = "help";
	
	/**
	 * Calls the constructor of the superclass to create a command without parameters
	 * with the info corresponding to the help command.
	 */
	public HelpCommand()
	{
		super(COMMAND_INFO, HELP_HELP);
	}

	/**
	 * Prints the name and help text of the available commands.
	 */
	public boolean execute(Game game, Scanner in)
	{
		for(Command cmd : CommandParser.getAvailableCommands()) 
		{
			System.out.println(cmd.helpText());
		}
	        
		return false;
	}
}
