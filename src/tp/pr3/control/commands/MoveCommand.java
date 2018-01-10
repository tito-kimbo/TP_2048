package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.control.Controller;
import tp.pr3.logic.Direction;
import tp.pr3.logic.multigames.Game;
import tp.pr3.exceptions.InvalidNumberOfArgumentsException;

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
		boolean printGame;
		if(direction != null) 
		{
			game.move(direction);
			printGame = true;
		}
		else
		{
		        printGame = false;
			System.out.println("Not a valid direction!");
		}
		
		return printGame;				
	}
	
	/**
	 * Parses the "move" command.
	 */
	public Command parse(String[] commandWords, Scanner in) throws InvalidNumberOfArgumentsException
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
