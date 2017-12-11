package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;

/**
 * Contains the information about the redo command.
 */
public class RedoCommand extends NoParamsCommand
{
	private static final String redoHelp = "Redo: Redoes the last undone move";
	private static final String commandInfo = "redo";
	
	public RedoCommand()
	{
		super(commandInfo, redoHelp);
	}
	
	public void execute(Game game, Controller controller)
	{
		if(game.redo()) {
			System.out.println("Redoing move...");
			controller.setNoPrintGameState(true);
		}
		else {
			System.out.println("Nothing to redo");
			controller.setNoPrintGameState(false);
		}
	}

}
