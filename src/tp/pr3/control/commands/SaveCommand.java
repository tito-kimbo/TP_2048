package tp.pr3.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import tp.pr3.exceptions.InvalidNumberOfArgumentsException;
import tp.pr3.exceptions.CustomInvalidFilenameException;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logic.util.MyStringUtils;

public class SaveCommand extends Command 
{
	private static final String COMMAND_INFO = "save";
	private static final String SAVE_HELP = "Save <filename>: Saves the current state of the game"
			+ "in the given file if it is possible.";
	private String filename;
	
	public SaveCommand()
	{
		super(COMMAND_INFO, SAVE_HELP);
	}
	
	public boolean execute(Game game, Scanner in)
	{
		BufferedWriter out = null;
		
		//OBS: This try{}catch{} is used to avoid compilation error (due to unhandled exceptions). However, on execution
		//the exception will never be thrown since it is ensured by the parse method
		try
		{
			out = new BufferedWriter(new FileWriter(filename)); 
			
			
			game.store(out);
			
			out.close(); //IOException?
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	
	public Command parse(String[] commandWords, Scanner in) throws InvalidNumberOfArgumentsException, CustomInvalidFilenameException
	{
		Command ret = null;
		
		if(commandWords[0].equals("save"))
		{
			if(commandWords.length > 2)
			{
				throw new InvalidNumberOfArgumentsException("File path can't contain spaces!");
			}
			else if(commandWords.length < 2)
			{
				throw new InvalidNumberOfArgumentsException("No file specified!");
			}
			else
			{		
				if(!MyStringUtils.validFileName(commandWords[1]))
				{
					throw new CustomInvalidFilenameException();
				}
				else
				{
					filename = commandWords[1];
					ret = this;		
				}
			}
		}
		
		return ret;
	}
}
