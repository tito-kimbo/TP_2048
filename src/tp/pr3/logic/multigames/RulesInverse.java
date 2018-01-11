package tp.pr3.logic.multigames;

import java.util.Random;

import tp.pr3.logic.Board;
import tp.pr3.logic.Cell;
import tp.pr3.logic.Position;

/**
 * Sets the standard rules for the inverse 2048 game.
 */
public class RulesInverse implements GameRules
{
	private static final int WINVALUE = 2;
	
	/**
	 * Creates a 2048 or 1024 at the given position with 90%, 10% probability respectively.
	 */
	public void addNewCellAt(Board board, Position pos, Random rand)
	{
		//Creates 2048 with 90% probability and 1024 with 10%
		int val = rand.nextInt(10);
		if(val == 0)
		{
			val = 1024;
		}
		else
		{
			val = 2048;
		}		
		board.setCell(pos,  val);
		board.setEmptyCells(board.getEmptyCells()-1);
		board.updateMaxMinValue(val);
	}
	
	/**
	 * Merges 2 cells and returns the score (see formula in implementation).
	 */
	public int merge(Cell self, Cell other)
	{
		int val = 0;

		val = 2*(2048/self.getVal());
		
		self.setVal(self.getVal()/2);
		other.setVal(0);
		
		
		return val;
	}
	
	/**
	 * Returns the lowest value on the board.
	 */
	public int getWinValue(Board board)
	{
		return board.getMinValue();
	}
	
	/**
	 * Returns whether the best value of the board is 2.
	 */
	public boolean win(Board board)
	{
		boolean b;
		
		if(getWinValue(board) == WINVALUE)
		{
			b = true;
		}
		else
		{
			b = false;
		}
		
		return b;
	}
}
