package tp.pr2.control.commands;

import tp.pr2.control.Controller;

/**
 * Abstract class to implement commands without parameters
 */

public abstract class NoParamsCommand extends Command
{
	public NoParamsCommand(String commandInfo, String helpInfo)
	{
		super(commandInfo, helpInfo);
	}
	
	public Command parse(String[] commandWords, Controller controller)
	{
		Command c = null;
		if(commandWords[0].equals(getCommandName()))
		{
			c = this;
			//controller.setNoPrintGameState()
		}
		
		return c;
	}
}
