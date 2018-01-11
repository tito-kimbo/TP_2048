package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.control.Controller;
import tp.pr3.exceptions.TooManyArgumentsException;
import tp.pr3.exceptions.CustomInvalidFilenameException;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logic.util.MyStringUtils;

public class SaveCommand extends Command 
{
	private static final String COMMAND_INFO = "save";
	private static final String SAVE_HELP = "Save <filename>: Saves the current state of the game"
			+ "in the given file if it is possible.";
	
	public SaveCommand()
	{
		super(COMMAND_INFO, SAVE_HELP);
	}
	
	public boolean execute(Game game, Controller controller)
	{
		
		return true;
	}
	
	
	public Command parse(String[] commandWords, Scanner in) throws TooManyArgumentsException, CustomInvalidFilenameException
	{
		Command ret = null;
		
		if(commandWords[0].equals("save"))
		{
			if(commandWords.length > 2)
			{
				throw new TooManyArgumentsException("Filename can't contain spaces!");
			}
			else if(commandWords.length == 2)
			{		
				if(!MyStringUtils.validFileName(commandWords[1]))
				{
					throw new CustomInvalidFilenameException();
				}
				else
				{
					ret = this;		
				}
			}
		}
		
		return ret;
	}
}
