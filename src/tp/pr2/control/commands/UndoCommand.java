package tp.pr2.control.commands;


import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

/**
 * Contains the information about the undo command.
 */
public class UndoCommand extends NoParamsCommand
{
	private static final String undoHelp = "Undo: Undoes the last move, up to 20 moves";
	private static final String commandInfo = "undo";
	
	public UndoCommand()
	{
		super(commandInfo, undoHelp);
	}
	
	public void execute(Game game, Controller controller)
	{
		if(game.undo()) {
			System.out.println("Undoing move...");
			controller.setNoPrintGameState(true);
		}
		else {
			System.out.println("Undo is not available");
			controller.setNoPrintGameState(false);
		}
		
	}

}
