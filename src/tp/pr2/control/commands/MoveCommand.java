package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;
import tp.pr2.logic.Direction;

/**
 * Contains the information of the command Move
 */

public class MoveCommand extends Command
{
	private static final String moveHelp = "Execute a move in one of the directions: up, down, left, right.";
	private static final String commandInfo = "move up right down left";
	
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
		//
	}
	
	/**
	 * Parses the "move" command.
	 */
	public Command parse(String[] commandWords, Controller controller)
	{
		Command c;
		boolean isDir = false;
		
		if(commandWords.length > 1)
		{
			for(Direction d : Direction.values())
			{
				if(d.toString().equals(commandWords[1]))
				{
						isDir = true;
				}
			}
			
			if(isDir)
			{
				c = this;
			}
			else
			{
				c = null;
			}
		}
		else
		{
			c = null;
		}
		
		return c;
	}
	
	public void setDirection(Direction d)
	{
		direction = d;
	}
	
	public Direction getDirection()
	{
		return direction;
	}
}
