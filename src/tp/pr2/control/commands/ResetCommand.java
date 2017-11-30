package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

/**
 * Contains the information about the reset command
 */

public class ResetCommand extends NoParamsCommand
{
	private static final String resetHelp = "Start a new game";
	private static final String commandInfo = "reset";
	
	public ResetCommand()
	{
		super(resetHelp, commandInfo);
	}
	
	public void execute(Game game, Controller controller)
	{
		
	}
}
