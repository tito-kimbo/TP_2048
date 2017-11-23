package tp.pr2.control;

import java.util.Scanner;
import java.util.Random;
import tp.pr1.Direction;
import tp.pr2.logic.multigames.Game;


/**
*	Interface between the user and the game. Interprets the user input
**/
public class Controller 
{
	private Game game;
	private Scanner in;
	private long seed;
	private final int objective = 2048;
	
	/**
	*	Constructor called from Game2048. Creates a Random object with the specified seed and initializes the Game oject
	**/
	public Controller(int size, int initCells, long seed)
	{
		Random r;
		
		this.seed = seed;
		in = new Scanner(System.in);
		r = new Random(seed);
		
		game = new Game(size, initCells, r);
		game.reset(seed);
		
	}
	
	/**
	*	Main loop of the program. Reads input, writes output and checks if the game is over
	**/
	public void run()
	{
		
		String cmd = "", dirString;
		Direction dir = Direction.UP; //FOR INITIALIZATION
		boolean stuck;
		
		System.out.println(game);
		System.out.println("Type help for a list of commands");

		do
		{
			stuck = game.isStuck();
			
			if(!stuck)	
			{	
				System.out.print("Command > ");
				cmd = in.next();

				//Checks for a valid command (if it is not, returns error message )
				if(cmd.equals("move")) 
				{				
					boolean valid = true;
					
					dirString = in.next();
					dirString = dirString.toUpperCase();
					
					//Checks if the string read is valid and, if it is, assigns a direction (else returns an error message)
					if(dirString.equals("UP"))
					{
						dir = Direction.UP;
					}						
					else if(dirString.equals("DOWN")) 
					{
						dir = Direction.DOWN;	
					}
					else if(dirString.equals("LEFT"))
					{
						 dir = Direction.LEFT;
					}					
					else if(dirString.equals("RIGHT"))
					{
						dir = Direction.RIGHT;
					}
					else
					{
						valid = false;	
					}
				
					if(valid) 
					{
						game.move(dir);
						System.out.print(game);
					}
					else 
					{
						System.out.println("Not a valid move");	
					}				
				}
				else if (cmd.equals("reset")) 
				{
					System.out.println("Resetting board...");
					game.reset(seed);
					System.out.println(game);
				}		
				else if (cmd.equals("help")) 
				{
					System.out.println("Commands: move <dir>, help, exit");
				}
				else if (!cmd.equals("exit"))
				{
					System.out.println("Not a valid command");	
				}
		    }		
		} while(game.getMaxToken() < objective && !stuck && !cmd.equals("exit"));
		//The game loop keeps going until the objective (2048) is reached, the game is stuck, or the command exit is introduced

		System.out.println("Game Over");
	}
	    
};
