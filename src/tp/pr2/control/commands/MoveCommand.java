package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

/**
 * Contains the information of the command Move
 */

public class MoveCommand extends Command
{
	private static final String moveHelp = "Execute a move in one of the directions: up, down, left, right.";
	private static final String commandInfo = "move up right down left";
	
	public MoveCommand()
	{
		super(commandInfo, moveHelp);
	}
	
	/**
	 * Executes the "move" commands, given the parameters are correct
	 */
	public void execute(Game game, Controller controller)
	{
		//
	}
	
	/**
	 * Parses the "move" command.
	 */
	public Command parse(String[] commandWords, Controller controller)
	{
		return null;
	}
}
