package tp.pr3.control.commands;


import java.util.Scanner;
import tp.pr3.logic.multigames.Game;

/**
 * Contains the information and implementation of the undo command.
 */
public class UndoCommand extends NoParamsCommand
{
	private static final String UNDO_HELP = "Undo: Undoes the last move, up to 20 moves";
	private static final String COMMAND_INFO = "undo";
	
	/**
	 * Calls the constructor of the superclass to create a command without parameters
	 * with the info corresponding to the undo command.
	 */
	public UndoCommand()
	{
		super(COMMAND_INFO, UNDO_HELP);
	}
	
	/**
	 * Undoes the last move, given it exists.
	 */
	public boolean execute(Game game, Scanner in)
	{
		game.undo();
		//DO WE NEED TO THROW THE EXCEPTION??????!!!
		return true;
	}

}
