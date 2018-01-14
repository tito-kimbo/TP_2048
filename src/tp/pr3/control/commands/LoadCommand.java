package tp.pr3.control.commands;

import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import tp.pr3.exceptions.CustomInvalidFilenameException;
import tp.pr3.exceptions.InvalidNumberOfArgumentsException;
import tp.pr3.exceptions.FileNotFoundException;
import tp.pr3.exceptions.CustomIOException;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logic.util.MyStringUtils;

public class LoadCommand extends Command 
{
	private static final String COMMAND_INFO = "load";
	private static final String LOAD_HELP = "load <filename>: Loads a previously saved game from an existing"
			+ "file.";
	private String filename;
	
	public LoadCommand()
	{
		super(COMMAND_INFO, LOAD_HELP);
	}

	public boolean execute(Game game, Scanner scan) throws CustomIOException
	{
		//OBS: This try{}catch{} is used to avoid compilation error (due to unhandled exceptions). However, on execution
		//the exception will never be thrown since it is ensured by the parse method
		try(BufferedReader in = new BufferedReader(new FileReader(filename)))
		{
			game.loadGame(in);
			System.out.println("Game successfully loaded from file: " + game.getType());
		}
		catch(CustomIOException e)
		{
			throw e;
		}
		catch(IOException e)
		{
			throw new CustomIOException("There was a problem reading the file.");
		}

		return true;
	}
	
	public Command parse(String[] commandWords, Scanner in) 
			throws InvalidNumberOfArgumentsException, CustomInvalidFilenameException, FileNotFoundException
	{
		Command ret = null;
		File file = null;
		
		if(commandWords[0].equals("load"))
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
					file = new File(commandWords[1]);
					if(file.exists())
					{
						filename = commandWords[1];
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
