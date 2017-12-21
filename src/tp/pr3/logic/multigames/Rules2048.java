package tp.pr3.logic.multigames;

import java.util.Random;

import tp.pr3.logic.Board;
import tp.pr3.logic.Cell;
import tp.pr3.logic.Position;

/**
 * Sets the standard rules for the regular 2048 game.
 */
public class Rules2048 implements GameRules
{
	private static final int WINVALUE = 2048;
	
	/**
	 * Creates a 2 or 4 at the given position with 90%, 10% probability respectively
	 */
	public void addNewCellAt(Board board, Position pos, Random rand)
	{
		int val = rand.nextInt(10);
		if(val == 0)
		{
			val = 4;
		}
		else
		{
			val = 2;
		}		
		board.setCell(pos,  val);
		board.setEmptyCells(board.getEmptyCells()-1);
		board.updateMaxMinValue(val);
	}
	
	/**
	 * Returns whether the given cells can be merged. This is, if they are equal.
	 */
	public boolean canMerge(Cell self, Cell other)
	{
		return (!self.isEmpty() && self.getVal() == other.getVal());
	}
	
	/**
	 * Merges 2 cells and returns the score (two times the value of the cells).
	 */
	public int merge(Cell self, Cell other)
	{
		//We will suppose the cells are neighbors and can be merged
		int val = 0;

	       	val = 2*self.getVal();
	       	//NOW WE MERGE
	       	self.setVal(2*self.getVal());
	       	other.setVal(0);
		
		
		return val;
	}
	
	/**
	 * Returns the highest value on the board.
	 */
	public int getWinValue(Board board)
	{
		return board.getMaxValue();
	}
	
	/**
	 * Returns whether the best value of the board is 2048.
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
