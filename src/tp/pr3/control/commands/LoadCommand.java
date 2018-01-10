package tp.pr3.control.commands;

import java.util.Scanner;
import tp.pr3.logic.multigames.Game;
import tp.pr3.exceptions.InvalidNumberOfArgumentsException;

public class LoadCommand extends Command
{
	private static final String loadHelp = "Load <filename>: Load the game stored in the file <filename>";
	private static final String commandInfo = "load";

	public LoadCommand()
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
