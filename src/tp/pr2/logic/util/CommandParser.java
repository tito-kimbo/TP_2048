package tp.pr2.logic.util;

import tp.pr2.control.commands.*;
import tp.pr2.control.Controller;

/**
* Parses the given command.
*/

public class CommandParser {

	/**
	* List of available commands
	*/
	private static Command[] availableCommands = { new HelpCommand(), new ResetCommand(), new ExitCommand(), new MoveCommand() };

	/**
	* Searches the available commands and returns an instance of the matching Command
	*/
	public static Command parseCommand(String[] commandWords, Controller controller) {
		Command result;
		for (Command com : availableCommands) {
			result = com.parse(commandWords, controller);
			if(com != null) result = com;
		}
		return result;
	}

	/**
	* Searches the available commands and returns the helo text of the matching Command
	*/
	public static String commandHelp() {
		String help = "";
		Command result;
		for (Command com : availableCommands) {
			result = com.parse(commandWords, controller);
			if(com != null) result = com;
		}
		if(result != null) {
			help = result.helpText();
		}
		return help;
	}
}