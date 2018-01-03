package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.control.commands.*;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logic.multigames.GameRules;
import tp.pr3.logic.util.*;
import tp.pr3.exceptions.CustomEmptyStackException;
import tp.pr3.exceptions.TooManyArgumentsException;

/**
*	Interface between the user and the game. Interprets the user input.
**/
public class Controller 
{;	
	private long seed;

	private boolean print;
	private boolean exit = false;
	
	private Game game;
	private Scanner in;
	
	/**
	*	Constructor called from Game2048. Creates a Random object with the specified seed and initializes the Game oject.
	**/
	public Controller(GameRules rules, int size, int initCells, long seed)
	{
		
		print = true;
		exit = false;
		this.seed = seed;
		in = new Scanner(System.in);
	      		
		game = new Game(rules, size, initCells, seed);
		
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
				try
				{
					cmd = CommandParser.parseCommand(cmdWords, in);
					if(cmd == null) 
					{
						System.out.println("Not a valid command!");
					}
					else 
					{
						setNoPrintGameState(cmd.execute(game, this));
					}
				}
				catch(TooManyArgumentsException e)
				{
					print = false;
					System.out.println(e.getMessage());
				}			       
				catch(CustomEmptyStackException e)
				{
					System.out.println(e.getMessage());
				}

				if(print) System.out.println(game);
			}
			
		} while(!game.win() && !stuck && !exit);
		//The game loop keeps going until the objective (2048) is reached,
		//the game is stuck, or the command exit is introduced

		System.out.println("Game Over");
	}

	/**
	 * Changes the exit value to true. The main loop will stop after this iteration.
	 */
	public void exit() 
	{
		//This function is only called by ExitCommand.execute(), which is
		//in turn called from the main loop of this class. The loop checks
		//exit just after calling execute(), stopping the game.
		exit = true;
	}

	/**
	 * Returns the PRNG seed introduced at the start of the game.
	 */
	public long getSeed() 
	{
		return seed;
	}

	/**
	 * Provides access to the scanner.
	 */
	public Scanner getScanner() 
	{
		//Needed by PlayCommand to read input
		return this.in;
	}

	/**
	 * Changes the current game to the specified type.
	 */
	public void setGame(GameRules rules, int size, int initCells, long seed) 
	{
		game = new Game(rules, size, initCells, seed);
		this.seed = seed;

		game.reset();
	}
};
