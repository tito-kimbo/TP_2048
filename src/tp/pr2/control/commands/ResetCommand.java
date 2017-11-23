package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

/**
 * Contains the information about the reset command
 */

public class ResetCommand extends NoParamsCommand
{
	public ResetCommand(String commandInfo, String helpInfo)
	{
		super(commandInfo, helpInfo);
	}
	
	public void execute(Game game, Controller controller)
	{
		
	}
}
