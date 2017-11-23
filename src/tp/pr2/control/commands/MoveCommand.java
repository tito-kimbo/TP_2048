package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

/**
 * Contains the information of the command Move
 */

public class MoveCommand extends Command
{
	public MoveCommand(String commandInfo, String helpInfo)
	{
		super(commandInfo, helpInfo);
	}
	
	public void execute(Game game, Controller controller)
	{
		//
	}
	
	public Command parse(String[] commandWords, Controller controller)
	{
		return null;
	}
}
