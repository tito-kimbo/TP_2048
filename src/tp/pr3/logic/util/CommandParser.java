package tp.pr3.logic.util;

import java.util.Scanner;

import tp.pr3.control.commands.*;

/**
* Parses the given command.
*/

public class CommandParser {

	/**
	* List of available commands.
	*/
	private static Command[] availableCommands = 
		{ 	
			new HelpCommand(), new ResetCommand(),new ExitCommand(), new MoveCommand(),
			new UndoCommand(), new RedoCommand(), new LoadCommand(), new PlayCommand(), 
			new SaveCommand()
		};
	
	/**
	* Searches the available commands and returns an instance of the matching Command.
	*/
	public static Command parseCommand(String[] commandWords, Scanner in) {
		Command result = null, aux = null;
		
		for (Command com : availableCommands) 
		{
			aux = com.parse(commandWords, in);
			if(aux != null) result = aux;
		}
		
		return result;
	}

	/**
	* Searches the available commands and returns the help text of the matching Command.
	*/
	public static String commandHelp(String[] commandWords, Scanner in) {
		String help = "";
		Command result = null, aux = null;
		
		for (Command com : availableCommands) 
		{
			aux = com.parse(commandWords, in);
			if(aux != null) result = aux;
		}
		
		if(result != null) 
		{
			help = result.helpText();
		}
		
		return help;
	}

	/**
	 * Accesor method for availableCommands.
	 */
	public static Command[] getAvailableCommands() 
	{
		return availableCommands;
	}
}
