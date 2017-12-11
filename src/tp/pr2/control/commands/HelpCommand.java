package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;
import tp.pr2.logic.util.CommandParser;

/**
 * Contains the information of the command help
 */

public class HelpCommand extends NoParamsCommand
{
	private static final String helpHelp = "Help: Print this help message.";
	private static final String commandInfo = "help";
	
	public HelpCommand()
	{
		super(commandInfo, helpHelp);
	}

	/**
	 * Prints the name and help text of the available commands
	 */
	public void execute(Game game, Controller controller)
	{
		for(Command cmd : CommandParser.getAvailableCommands()) {
			System.out.println(cmd.helpText());
		}
		
		//Do not print board after executing this command
		controller.setNoPrintGameState(false);
	}
}
