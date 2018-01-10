package tp.pr3.control.commands;

import java.util.Scanner;
import tp.pr3.logic.multigames.Game;
import tp.pr3.exceptions.InvalidNumberOfArgumentsException;

public class SaveCommand extends Command
{
	private static final String loadHelp = "Save <filename>: Saves the current game in the file <filename>";
	private static final String commandInfo = "save";

	public SaveCommand()
	{
		super(commandInfo, loadHelp);
	}

	public boolean execute(Game game, Scanner in)
	{
		return false;
	}

	public Command parse(String[] commandWords, Scanner in) throws InvalidNumberOfArgumentsException
	{
		return null;
	}
}
