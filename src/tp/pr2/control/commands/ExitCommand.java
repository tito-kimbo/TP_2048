package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

/**
 * Contains the information of the exit command
 *
 */

public class ExitCommand extends NoParamsCommand
{
	private static final String exitHelp = "Terminate the program";
	private static final String commandInfo = "exit";
	
	public ExitCommand(String commandInfo, String helpInfo)
	{
		super(commandInfo, helpInfo);
	}
	
	public void execute(Game game, Controller controller)
	{
		
	}
}
