package tp.pr2.control.commands;

 import tp.pr2.control.Controller;
 import tp.pr2.logic.multigames.Game;

 /**
  * Implements the general scheme of a Command
  */
 
public abstract class Command 
{
	private String helpText;
	private String commandText;
	protected final String commandName;

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
	 * Executes the corresponding command
	 */
	public abstract void execute(Game game, Controller controller);
	
	/**
	 * Parses the corresponding command
	 */
	public abstract Command parse(String[] commandWords, Controller controller);
	
	/**
	 * Returns a String with the help information
	 */
	public String helpText()
	{
		return " " + commandText + ": " + helpText;
	}
}
