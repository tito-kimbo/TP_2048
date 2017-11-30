package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

/**
 * Contains the information of the command help
 */

public class HelpCommand extends NoParamsCommand
{
	private static final String helpHelp = "Print this help message.";
	private static final String commandInfo = "help";
	
	public HelpCommand()
	{
		super(commandInfo, helpHelp);
	}
	
	public void execute(Game game, Controller controller)
	{
		
	}
}
