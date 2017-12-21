package tp.pr3.control.commands;


import tp.pr3.control.Controller;
import tp.pr3.logic.multigames.Game;

/**
 * Contains the information and implementation of the undo command.
 */
public class UndoCommand extends NoParamsCommand
{
	private static final String undoHelp = "Undo: Undoes the last move, up to 20 moves";
	private static final String commandInfo = "undo";
	
	/**
	 * Calls the constructor of the superclass to create a command without parameters
	 * with the info corresponding to the undo command.
	 */
	public UndoCommand()
	{
		super(commandInfo, undoHelp);
	}
	
	/**
	 * Undoes the last move, given it exists.
	 */
	public void execute(Game game, Controller controller)
	{
		game.undo();
		
		//DO WE NEED TO THROW THE EXCEPTION??????!!!
	}

}
