package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.control.Controller;
import tp.pr3.logic.multigames.Game;

 /**
  * Implements the general scheme of a Command.
  */
public abstract class Command 
{
	private String helpText;
	private String commandText;
	private final String commandName; //Modified to maintain code integrity.

	/**
	 * Constructor for the class
	 */
	public Command(String commandInfo, String helpInfo)
	{
		commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}
	
	/**
	 * Executes the corresponding command, returning a boolean that indicates whether the
	 * game should be printed.
	 */
	public abstract boolean execute(Game game, Scanner in);
	
	/**
	 * Parses the corresponding command.
	 */
	public abstract Command parse(String[] commandWords, Scanner in);
	
	/**
	 * Returns a String with the help information.
	 */
	public String helpText()
	{
		return helpText;
	}
	
	/**
	 * Returns the name of the command.
	 */
	public String getCommandName()
	{
		return commandName;
	}
}