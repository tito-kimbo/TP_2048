package tp.pr2.control;

import java.util.Scanner;

import tp.pr2.control.commands.*;
import tp.pr2.logic.util.*;

import java.util.Random;

import tp.pr2.logic.Direction;
import tp.pr2.logic.multigames.Game;


/**
*	Interface between the user and the game. Interprets the user input.
**/
public class Controller 
{
	private final int objective = 2048;
	
	private long seed;
	private boolean print;
	private boolean exit = false;
	
	private Game game;
	private Scanner in;

	//availableCommands is used in both CommandParser and HelpCommand. In order to not have
	//two duplicate arrays for the same purpose, I have put it here.

	/**
	* List of available commands.
	*/
	private static Command[] availableCommands = { new HelpCommand(), new ResetCommand(), new ExitCommand(), new MoveCommand() };
	
	/**
	*	Constructor called from Game2048. Creates a Random object with the specified seed and initializes the Game oject.
	**/
	public Controller(int size, int initCells, long seed)
	{
		Random r;
		
		print = true;
		this.seed = seed;
		in = new Scanner(System.in);
		r = new Random(seed);
		
		game = new Game(size, initCells, r);
		game.reset(seed);
		
	}
	
	/**
	 * Indicates the program that the board should not be printed on the current iteration.
	 */
	public void setNoPrintGameState(boolean b)
	{
		print = b;
	}
	
	/**
	*	Main loop of the program. Reads input, writes output and checks if the game is over.
	**/
	public void run()
	{
		Command cmd = null;
		String[] cmdWords;
		boolean stuck;
		
		System.out.println(game);
		System.out.println("Type help for a list of commands");

		do
		{
			//Do not print the game by default, in case an incorrect command is introduced.
			print = false; 
			stuck = game.isStuck();
			
			if(!stuck)	
			{	
				System.out.print("Command > ");
				cmdWords = in.nextLine().toLowerCase().split("\\s+");
				cmd = CommandParser.parseCommand(cmdWords, this);
				if(cmd == null) {
					System.out.println("Not a valid command!");
				}
				else {
					cmd.execute(game, this);
				}
				
				if(print) System.out.println(game);
			}
			
		} while(game.getMaxToken() < objective && !stuck && !exit);
		//The game loop keeps going until the objective (2048) is reached,
		//the game is stuck, or the command exit is introduced

		System.out.println("Game Over");
	}

	/**
	 * Accesor method for availableCommands.
	 */
	public Command[] getAvailableCommands() {
		return availableCommands;
	}

	/**
	 * Changes the exit value to true. The main loop will stop after this iteration.
	 */
	public void exit() {
		//This function is only called by ExitCommand.execute(), which is
		//in turn called from the main loop of this class. The loop checks
		//exit just after calling execute(), stopping the game.
		exit = true;
	}

	/**
	 * Accessor method for seed.
	 */
	public long getSeed() {
		return seed;
	}
};
