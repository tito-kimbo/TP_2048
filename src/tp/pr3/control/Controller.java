package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.control.commands.*;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logic.multigames.GameRules;
import tp.pr3.logic.util.*;
import tp.pr3.exceptions.CustomEmptyStackException;
import tp.pr3.exceptions.InvalidNumberOfArgumentsException;
import java.nio.file.InvalidPathException;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;

/**
*	Interface between the user and the game. Interprets the user input.
**/
public class Controller 
{;      
       	private Game game;
	private Scanner in;
	
	/**
	*	Constructor called from Game2048. Creates a Random object with the specified seed and initializes the Game oject.
	**/
	public Controller(GameRules rules, int size, int initCells, long seed)
	{
		in = new Scanner(System.in);
	      	
		game = new Game(rules, size, initCells, seed);
		
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
			stuck = game.isStuck();
			
			if(!stuck)	
			{	
				System.out.print("Command > ");
				cmdWords = in.nextLine().toLowerCase().split("\\s+");
				try
				{
					cmd = CommandParser.parseCommand(cmdWords, in);
					if(cmd.execute(game, in)) System.out.println(game);
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());	
				}
				/*
				catch(InvalidPathException e)
				{
					System.out.println("Not a valid filename.");
				}
				catch(FileNotFoundException e)
				{
					System.out.println("The file does not exist or can't be opened.");
				}
				*/
			        
			}
			
		} while(!game.win() && !stuck && !(cmd instanceof ExitCommand));
		//The game loop keeps going until the objective (2048) is reached,
		//the game is stuck, or the command exit is introduced

		System.out.println("Game Over");
	}
};
