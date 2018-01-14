package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.control.commands.*;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logic.multigames.GameType;
import tp.pr3.logic.util.*;
import tp.pr3.exceptions.GameOverException;

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
	public Controller(GameType type, int size, int initCells, long seed)
	{
		in = new Scanner(System.in);
	      	
		game = new Game(type, size, initCells, seed);
		
	}
   	
	/**
	*	Main loop of the program. Reads input, writes output and checks if the game is over.
	**/
	public void run()
	{
		Command cmd = null;
		String[] cmdWords = new String[1];
		boolean exit = false;

		System.out.println(game);
		System.out.println("Type help for a list of commands");

		do
		{
			System.out.print("Command > ");
			cmdWords = in.nextLine().toLowerCase().split("\\s+");
			try
			{
				cmd = CommandParser.parseCommand(cmdWords, in);
				if(cmd.execute(game, in)) System.out.println(game);
			}
			catch(GameOverException e)
			{
				exit = true;
				if(!e.exit())
				{
					System.out.print(game);
					System.out.println(e.getMessage());
				}
			}
			catch(RuntimeException e)
			{
				System.out.println(e.getMessage());	
			}
			/*
			catch(CustomInvalidFilenameException e)
			{
				System.out.println("Not a valid filename.");
			}
			catch(FileNotFoundException e)
			{
				System.out.println("The file does not exist or can't be opened.");
			}
			*/     
				
		} while(!exit);
	}
};
