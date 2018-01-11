package tp.pr3.logic.multigames;

import tp.pr3.logic.Board;
import tp.pr3.logic.Cell;
import tp.pr3.logic.Position;

import java.util.Random;

/**
 * Sets a general scheme for making the rules for a 2048-like game.
 */

public interface GameRules 
{
	
	/**
	 *	Adds a new cell with random value at the given Position in the given Board.
	 **/
	void addNewCellAt(Board board, Position pos, Random rand);
	
	/**
	 * Returns whether the cells can be merged based on the current rules.
	 */
	default boolean canMergeNeighbor(Cell self, Cell other) {
		//Default implementation for Rules2048 and RulesInverse
		return !self.isEmpty() && self.getVal() == other.getVal();
	}

	
	/**
	 * Merges two cells and returns the score value of said merge.
	 */
	int merge(Cell self, Cell other);
	
	/**
	 * Returns the best value of the Board, checking whether it is a winning value.
	 */
	int getWinValue(Board board);
	
	/**
	 * Returns whether the game has been won.
	 */
	boolean win(Board board);
	
	/**
	 * Returns whether the game has been lost.
	 */
	default boolean lose(Board board)
	{
		return (board.getEmptyCells() == 0 && !board.canMerge(this));
	}
	
	/**
	 * Creates and returns a Board of the given size.
	 */
	default Board createBoard(int size)
	{
		Board b = new Board(size);
		
		return b;
	}
	
	/**
	 * Chooses an empty position of Board and and adds a new cell invoking 
	 * the method addNewCellAt.
	 */
	default void addNewCell(Board board, Random rand)
	{
		//ESSENTIALLY DOES THE WORK OF THE BOARD CODE
		Position pos = new Position();
		int counter, i, j, size; 
		
		if(board.getEmptyCells() > 0)
		{	
			counter = rand.nextInt(board.getEmptyCells())+1;;
			
			i = 0;
			size = board.getBoardSize();
			while(i < size && counter > 0)
			{
				pos.setRow(i);
				j = 0;
				while(j < size && counter > 0)
				{
					pos.setCol(j);
					
					if(board.isEmptyCell(pos)) 
					{
						if(counter > 0)	//IS THIS CHECK REALLY NECESSARY (PROB NOT)
						{
							counter--;		
						}				
					}
					
					j++;
				}
				i++;
			}
			
			addNewCellAt(board, pos, rand);
		}
		
	}
	
	/**
	 * Initializes the Board spawning the given number of Cells at empty positions.
	 */
	default void initBoard(Board board, int numCells, Random rand)
	{
		for(int i = 0; i < numCells; i++)
		{
			addNewCell(board, rand);
		}
	}
}