package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

/**
 * Contains the information and implementation of the reset command.
 */
public class ResetCommand extends NoParamsCommand
{
	private static final String resetHelp = "Reset: Start a new game";
	private static final String commandInfo = "reset";
	
	/**
	 * Calls the constructor of the superclass to create a command without parameters
	 * with the info corresponding to the reset command.
	 */
	public ResetCommand()
	{
		super(commandInfo, resetHelp);
	}
	
	/**
	 * Resets the game to its initial values.
	 */
	public void execute(Game game, Controller controller)
	{
		game.reset();
		controller.setNoPrintGameState(true);
	}
}
