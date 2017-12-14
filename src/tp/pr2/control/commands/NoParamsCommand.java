package tp.pr2.control.commands;

import tp.pr2.control.Controller;

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
	public Command parse(String[] commandWords, Controller controller)
	{
		Command c = null;
		if(commandWords.length == 1 && commandWords[0].equals(getCommandName()))	
		{
			c = this;
		}
		
		return c;
	}
}
