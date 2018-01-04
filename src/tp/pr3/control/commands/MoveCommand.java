package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.control.Controller;
import tp.pr3.logic.Direction;
import tp.pr3.logic.multigames.Game;
import tp.pr3.exceptions.TooManyArgumentsException;

/**
 * Contains the information and implementation of the command Move.
 */
public class MoveCommand extends Command
{
	private static final String moveHelp = "Move <direction>: Execute a move in one of the directions: up, down, left, right.";
	private static final String commandInfo = "move";
	
	private Direction direction;
	
	/**
	 * Calls the constructor of the superclass to create a command
	 * with the info corresponding to the move command. Also initializes non-static 
	 * variables.
	 */
	public MoveCommand()
	{
		super(commandInfo, moveHelp);
		direction = null;
	}
	
	/**
	 * Executes the "move" commands, given the parameters are correct.
	 */
	public boolean execute(Game game, Controller controller)
	{	       	
		if(direction != null) 
		{
			game.move(direction);
			controller.setNoPrintGameState(true);
		}
		else
		{
		        controller.setNoPrintGameState(false);
			System.out.println("Not a valid direction!");
		}
		
		return true;				
	}
	
	/**
	 * Parses the "move" command.
	 */
	public Command parse(String[] commandWords, Scanner in) throws TooManyArgumentsException
	{ 
		Command ret = null;
	    Direction dir = null;
		
		if(commandWords[0].equals("move")) 
		{
			if(commandWords.length > 2)
			{
				throw new TooManyArgumentsException("This command only accepts one parameter!");
			}
			else if(commandWords.length == 2)
			{
				//ADD EXCEPTION FOR NOT ENOUGH ARGUMENTS???
				ret = this;
				for(Direction d : Direction.values())
					{
						if(d.toString().equals(commandWords[1]))
							dir = d;
					}
				
			}
		}
		this.direction = dir;
		return ret;
	}
	
}
