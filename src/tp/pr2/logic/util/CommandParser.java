package tp.pr2.logic.util;

import tp.pr2.control.commands.Command;
import tp.pr2.control.commands.HelpCommand;
import tp.pr2.control.commands.ResetCommand;
import tp.pr2.control.commands.NoParamsCommand;
import tp.pr2.control.commands.ExitCommand;
import tp.pr2.control.commands.MoveCommand;
import tp.pr2.control.Controller;

/**
* Parses the given command.
*/

public class CommandParser {

	//availableCommands ahora está en Controller
	
	/**
	* Searches the available commands and returns an instance of the matching Command
	*/
	public static Command parseCommand(String[] commandWords, Controller controller) {
		Command result = null, aux = null;
		for (Command com : controller.getAvailableCommands()) {
			aux = com.parse(commandWords, controller);
			if(aux != null) result = aux;
		}
		return result;
	}

	/**
	* Searches the available commands and returns the help text of the matching Command
	*/
	public static String commandHelp(String[] commandWords, Controller controller) {
		String help = "";
		Command result = null, aux = null;
		for (Command com : controller.getAvailableCommands()) {
			aux = com.parse(commandWords, controller);
			if(aux != null) result = aux;
		}
		if(result != null) {
			help = result.helpText();
		}
		return help;
	}
}
