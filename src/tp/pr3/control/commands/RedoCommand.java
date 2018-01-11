package tp.pr3.control.commands;

import java.util.Scanner;
import tp.pr3.logic.multigames.Game;

/**
 * Contains the information and implementation of the redo command.
 */
public class RedoCommand extends NoParamsCommand
{
	private static final String REDO_HELP = "Redo: Redoes the last undone move";
	private static final String COMMAND_INFO = "redo";
	
	/**
	 * Calls the constructor of the superclass to create a command without parameters
	 * with the info corresponding to the redo command.
	 */
	public RedoCommand()
	{
		super(COMMAND_INFO, REDO_HELP);
	}
	
	/**
	 * Redoes the last undone move, given it exists.
	 */
	public boolean execute(Game game, Scanner in)
	{
		game.redo();
		
		return true;
	}

}
