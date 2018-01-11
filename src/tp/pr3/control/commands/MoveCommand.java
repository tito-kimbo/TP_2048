package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.control.Controller;
import tp.pr3.logic.Direction;
import tp.pr3.logic.multigames.Game;
import tp.pr3.exceptions.InvalidNumberOfArgumentsException;
import java.lang.IllegalArgumentException;

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
	public boolean execute(Game game, Scanner in)
	{
		game.move(direction);
		return true;
	}
	
	/**
	 * Parses the "move" command.
	 */
	public Command parse(String[] commandWords, Scanner in) throws InvalidNumberOfArgumentsException, IllegalArgumentException
	{ 
		Command ret = null;
		Direction dir = null;
		
		if(commandWords[0].equals("move")) 
		{
			if(commandWords.length > 2)
			{
				throw new InvalidNumberOfArgumentsException("This command only accepts one parameter!");
			}
			else if(commandWords.length < 2)
			{
				throw new InvalidNumberOfArgumentsException("No direction specified!");
			}
			else
			{
				ret = this;
			        dir = Direction.fromString(commandWords[1]);
				if(dir == null)
				{
					throw new IllegalArgumentException("Not a valid direction!");
				}
				
			}
		}
		this.direction = dir;
		return ret;
	}
	
}
