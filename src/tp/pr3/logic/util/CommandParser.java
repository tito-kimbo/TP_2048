package tp.pr3.logic.util;

import tp.pr3.control.Controller;
import tp.pr3.control.commands.*;

/**
* Parses the given command.
*/

public class CommandParser {

	/**
	* List of available commands.
	*/
	private static Command[] availableCommands = { new HelpCommand(), new ResetCommand(), new ExitCommand(), new MoveCommand(), new UndoCommand(), new RedoCommand(), new PlayCommand()};
	
	/**
	* Searches the available commands and returns an instance of the matching Command.
	*/
	public static Command parseCommand(String[] commandWords, Controller controller) {
		Command result = null, aux = null;
		for (Command com : availableCommands) {
			aux = com.parse(commandWords, controller);
			if(aux != null) result = aux;
		}
		return result;
	}

	/**
	* Searches the available commands and returns the help text of the matching Command.
	*/
	public static String commandHelp(String[] commandWords, Controller controller) {
		String help = "";
		Command result = null, aux = null;
		for (Command com : availableCommands) {
			aux = com.parse(commandWords, controller);
			if(aux != null) result = aux;
		}
		if(result != null) {
			help = result.helpText();
		}
		return help;
	}

	/**
	 * Accesor method for availableCommands.
	 */
	public static Command[] getAvailableCommands() {
		return availableCommands;
	}
}
