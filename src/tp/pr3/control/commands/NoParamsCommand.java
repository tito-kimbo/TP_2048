package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.exceptions.InvalidNumberOfArgumentsException;

/**
 * Contains a scheme to implement commands without parameters.
 */

public abstract class NoParamsCommand extends Command
{
	/**
	 * Calls the constructor of the superclass Command to create a command with the given.
	 * information
	 */
	public NoParamsCommand(String commandInfo, String helpInfo)
	{
		super(commandInfo, helpInfo);
	}
	
	/**
	 * Parses a command, taking into account that it has no parameters.
	 */
	public Command parse(String[] commandWords, Scanner in) throws InvalidNumberOfArgumentsException
	{
		Command c = null;
		
		if(commandWords[0].equals(getCommandName()))	
		{
			if(commandWords.length == 1)
				{
					c = this;
				}
			else
			{
				throw new InvalidNumberOfArgumentsException("This command does not accept parameters!");
			}
		}
	       return c;
	}
}