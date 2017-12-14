package tp.pr2.control.commands;

import tp.pr2.control.Controller;
import tp.pr2.logic.multigames.Game;
import tp.pr2.logic.Direction;
import tp.pr2.logic.Position;
import tp.pr2.logic.MoveResults;
import tp.pr2.logic.Board;
import tp.pr2.logic.GameState;

/**
 * Contains the information and implementation of the command Move.
 */
public class MoveCommand extends Command
{
	private static final String moveHelp = "Move <direction>: Execute a move in one of the directions: up, down, left, right.";
	private static final String commandInfo = "move";
	
	private Direction direction;
	
	/**
	 * Calls the constructor of the superclass to create a command
	 * with the info corresponding to the move command. Also initializes non-static 
	 * variables.
	 */
	public MoveCommand()
	{
		super(commandInfo, moveHelp);
		direction = null;
	}
	
	/**
	 * Executes the "move" commands, given the parameters are correct.
	 */
	public void execute(Game game, Controller controller)
	{
		if(direction != null) 
		{
			
			MoveResults results = new MoveResults();
			GameState currentState = game.getState();
			Board b = game.getBoard();
			switch(direction) 
			{
			case RIGHT:
				{
					move_right(game, results);
				}break;
			case LEFT:
				{
				        b.reflect();
					move_right(game, results);
					b.reflect();
				}break;
			case UP:
				{
				        b.transpose();
					b.reflect();
					move_right(game, results);
					b.reflect();
					b.transpose();
				}break;
			case DOWN:
				{
				        b.transpose();
					move_right(game, results);
					b.transpose();
				}break;
			}

			game.updateAfterMove(results, currentState);
				
			
			controller.setNoPrintGameState(true);
		}
		else 
		{
			controller.setNoPrintGameState(false);
			System.out.println("Not a valid direction!");
		}
	}
	
	/**
	 * Parses the "move" command.
	 */
	public Command parse(String[] commandWords, Controller controller)
	{
		Command ret = null;
	        Direction dir = null;
		
		if(commandWords.length == 2 && commandWords[0].equals("move")) 
		{
			ret = this;
			for(Direction d : Direction.values())
			{
				if(d.toString().equals(commandWords[1]))
					dir = d;
			}
		
			this.direction = dir;
		}
		return ret;
	}
	
	/**
	*	Moves every possible Cell to the right, saving the results in a given MoveResults.
	*/
	public void move_right(Game game, MoveResults m)
	{
		Board board = game.getBoard();
		int aux1, size = board.getBoardSize(), points;
		Position auxPos1, auxPos2;
		boolean merged;
		
		for(int i = 0; i < size; i++)
		{
			aux1 = size-1;
			merged = false;		
			
			for(int j = size-1; j >= 0; j--)
			{
				auxPos1 = new Position(i, j);
				//Moves the cell in position (i,j) to the cell in position (i, aux1)
				//Note that either j == aux1 or (i, aux1) is empty, so that two nonempty cells will never be swapped
				
				if( !( board.isEmptyCell(auxPos1)) )
				{
					if(aux1 != j)
					{
						auxPos2 = new Position(i, aux1);
						board.setCell(auxPos2, board.getCell(auxPos1).getVal());
					        board.setCell(auxPos1, 0);
						
					        m.setMoved(true);
					}

					//If the cell in (i, aux1) has already been merged, do not merge again
					
					if(!merged && aux1 < size-1)
					{
						auxPos1 = new Position(i, aux1+1);
						auxPos2 = new Position(i, aux1);

						points = game.merge(board.getCell(auxPos1), board.getCell(auxPos2));
						merged = (points > 0);
						
						if(merged)
						{
							m.setPoints(m.getPoints() + points);
							m.setMoved(true);
							
							board.setEmptyCells(board.getEmptyCells()+1);
							aux1++;
						}
					}
					else merged = false;
					
					aux1--;
				}
			}
		}
	}
	
}
