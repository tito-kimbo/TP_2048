package tp.pr3.control.commands;

import tp.pr3.control.Controller;
import tp.pr3.logic.multigames.Game;

/**
 * Contains the information and implementation of the redo command.
 */
public class RedoCommand extends NoParamsCommand
{
	private static final String redoHelp = "Redo: Redoes the last undone move";
	private static final String commandInfo = "redo";
	
	/**
	 * Calls the constructor of the superclass to create a command without parameters
	 * with the info corresponding to the redo command.
	 */
	public RedoCommand()
	{
		super(commandInfo, redoHelp);
	}
	
	/**
	 * Redoes the last undone move, given it exists.
	 */
	public boolean execute(Game game, Controller controller)
	{
		game.redo();
		
		return true;
	}

}
