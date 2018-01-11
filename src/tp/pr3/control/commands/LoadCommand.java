package tp.pr3.control.commands;

import java.util.Scanner;
import java.io.File;

import tp.pr3.control.Controller;
import tp.pr3.exceptions.CustomInvalidFilenameException;
import tp.pr3.exceptions.TooManyArgumentsException;
import tp.pr3.exceptions.FileNotFoundException;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logic.util.MyStringUtils;

public class LoadCommand extends Command 
{
	private static final String COMMAND_INFO = "load";
	private static final String LOAD_HELP = "load <filename>: Loads a previously saved game from an existing"
			+ "file.";
	
	public LoadCommand()
	{
		super(COMMAND_INFO, LOAD_HELP);
	}
	
	public boolean execute(Game game, Controller controller)
	{
		
		return true;
	}
	
	public Command parse(String[] commandWords, Scanner in) 
			throws TooManyArgumentsException, CustomInvalidFilenameException, FileNotFoundException
	{
		Command ret = null;
		File file = null;
		
		System.out.println(commandWords[0]);
		if(commandWords[0].equals("load"))
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
					file = new File(commandWords[1]);
					if(file.exists())
					{
						ret = this;						
					}
					else
					{
						throw new FileNotFoundException();
					}
					
				}
			}
		}
		
		return ret;
	}
}
