package tp.pr2.control;

import java.util.Scanner;

import tp.pr2.control.commands.Command;
import tp.pr2.logic.util.*;

import java.util.Random;

import tp.pr2.logic.Direction;
import tp.pr2.logic.multigames.Game;


/**
*	Interface between the user and the game. Interprets the user input
**/
public class Controller 
{
	private final int objective = 2048;
	
	private long seed;
	private boolean print, exit;
	
	private Game game;
	private Scanner in;
	
	/**
	*	Constructor called from Game2048. Creates a Random object with the specified seed and initializes the Game oject
	**/
	public Controller(int size, int initCells, long seed)
	{
		Random r;
		
		print = true;
		exit = false;
		this.seed = seed;
		in = new Scanner(System.in);
		r = new Random(seed);
		
		game = new Game(size, initCells, r);
		game.reset(seed);
		
	}
	
	/**
	 * Indicates the program that the board should not be printed on the current iteration
	 */
	public void SetNoPrintGameState(boolean b)
	{
		print = b;
	}
	
	public void setExit(boolean b)
	{
		exit = b;
	}
	
	
	/**
	*	Main loop of the program. Reads input, writes output and checks if the game is over
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
					System.out.println(cmd.helpText());
					if(cmd instanceof tp.pr2.control.commands.ExitCommand) exit = true;
					//command.execute();
				}
				
		    }		
		} while(game.getMaxToken() < objective && !stuck && !exit);
		//The game loop keeps going until the objective (2048) is reached, the game is stuck, or the command exit is introduced

		System.out.println("Game Over");
	}
	    
};
