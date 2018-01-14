package tp.pr3.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;

import tp.pr3.exceptions.InvalidNumberOfArgumentsException;
import tp.pr3.exceptions.CustomInvalidFilenameException;
import tp.pr3.exceptions.CustomIOException;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logic.util.MyStringUtils;

public class SaveCommand extends Command 
{
	private static final String COMMAND_INFO = "save";
	private static final String SAVE_HELP = "Save <filename>: Saves the current state of the game"
			+ " in the given file if it is possible.";
	private String filename;
	
	public SaveCommand()
	{
		super(COMMAND_INFO, SAVE_HELP);
	}
	
	public boolean execute(Game game, Scanner in) throws CustomIOException
	{		
		//OBS: This try{}catch{} is used to avoid compilation error (due to unhandled exceptions). However, on execution
		//the exception will never be thrown since it is ensured by the parse method
		try(BufferedWriter out = new BufferedWriter(new FileWriter(filename)))
		{
			game.store(out);
			System.out.println("Game successfully saved to file. Use load command to reload it.");
		}
		catch(IOException e)
		{
			throw new CustomIOException("There was a problem writing the file.");
		}
		
		return false;
	}
	
	
	public Command parse(String[] commandWords, Scanner in) throws InvalidNumberOfArgumentsException, CustomInvalidFilenameException
	{
		Command ret = null;
		File file;
		boolean confirmed = false;
		String line;
		String[] filename = {};

		if(commandWords[0].equals("save"))
		{
			if(commandWords.length > 1)
			{
				filename = Arrays.copyOfRange(commandWords, 1, commandWords.length);
			}

			while(!confirmed)
			{
				if(filename.length > 1)
				{
					throw new InvalidNumberOfArgumentsException("File path can't contain spaces!");
				}
				else if(filename.length < 1)
				{
					throw new InvalidNumberOfArgumentsException("No file specified!");
				}
				else
				{
					if(!MyStringUtils.validFileName(filename[0]))
					{
						throw new CustomInvalidFilenameException();
					}
					else
					{
						file = new File(filename[0]);
						
						if(file.exists())
						{
							
							System.out.print("That file already exists. Do you want to overwrite it? (Y/N) : ");
							line = in.nextLine().toLowerCase();
							if(line.equals("y"))
							{
								confirmed = true;
							}
							else if(line.equals("n"))
							{
								System.out.print("Please introduce another file: ");
								filename = in.nextLine().toLowerCase().split("\\s+");
							}
							else
							{
								System.out.println("Please introduce 'Y' or 'N'");
							}
						}
						else
						{
							confirmed = true;
						}
					}
				}
			}
			this.filename = filename[0];
			ret = this;
		}
		
		return ret;
	}
}
