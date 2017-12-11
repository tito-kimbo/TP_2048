package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;
import tp.pr2.logic.Direction;

/**
 * Contains the information of the command Move
 */

public class MoveCommand extends Command
{
	private static final String moveHelp = "Move <direction>: Execute a move in one of the directions: up, down, left, right.";
	private static final String commandInfo = "move";
	
	private Direction direction;
	
	public MoveCommand()
	{
		super(commandInfo, moveHelp);
		direction = null;
	}
	
	/**
	 * Executes the "move" commands, given the parameters are correct
	 */
	public void execute(Game game, Controller controller)
	{
		if(direction != null) {
			game.move(direction);
			controller.setNoPrintGameState(true);
		}
		else {
			controller.setNoPrintGameState(false);
			System.out.println("Not a valid direction!");
		}
	}
	
	/**
	 * Parses the "move" command.
	 */
	public Command parse(String[] commandWords, Controller controller)
	{
		Command ret = null;
	        Direction dir = null;
		
		if(commandWords[0].equals("move")) {
			ret = this;
			if(commandWords.length > 1)
				{
					for(Direction d : Direction.values())
						{
							if(d.toString().equals(commandWords[1]))
								dir = d;
						}
				}
		
			this.direction = dir;
		}
		return ret;
	}
	
	public Direction getDirection()
	{
		return direction;
	}
}
