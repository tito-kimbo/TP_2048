package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

/**
 * Contains the information of the command help
 */

public class HelpCommand extends NoParamsCommand
{
	public HelpCommand(String commandInfo, String helpInfo)
	{
		super(commandInfo, helpInfo);
	}
	
	public void execute(Game game, Controller controller)
	{
		
	}
}
