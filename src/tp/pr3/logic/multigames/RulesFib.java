package tp.pr3.logic.multigames;

import java.util.Random;

import tp.pr3.logic.Board;
import tp.pr3.logic.Cell;
import tp.pr3.logic.Position;
import tp.pr3.logic.util.MyMathsUtil;

/**
 * Sets the standard rules for the Fibonacci 2048-like game.
 */
public class RulesFib implements GameRules
{
	private static final int WIN_VALUE = 144;
	
	/**
	 * Creates a 1 or 2 at the given position with 90%, 10% probability respectively
	 */
	public void addNewCellAt(Board board, Position pos, Random rand)
	{
		//Creates 1 with 90% probability and 2 with 10%
		int val = rand.nextInt(10);
		if(val == 0)
		{
			val = 2;
		}
		else
		{
			val = 1;
		}		
		board.setCell(pos,  val);
		board.setEmptyCells(board.getEmptyCells()-1);
		board.updateMaxMinValue(val);
	}
	
	/**
	 * Returns whether the given cells can be merged. This is, if they are consecutive in
	 * the Fibonacci sequence.
	 */
	public boolean canMergeNeighbor(Cell self, Cell other)
	{
		int selfVal, otherVal, selfNextFib, otherNextFib;
		
		selfVal = self.getVal();
	    otherVal = other.getVal();
		selfNextFib = MyMathsUtil.nextFib(selfVal);
		otherNextFib = MyMathsUtil.nextFib(otherVal);
		
		return (!self.isEmpty()) && ((selfVal == otherVal && selfVal == 1) || 
			   (otherVal == selfNextFib ||	(selfVal == otherNextFib)));
	}
	
	/**
	 * Merges 2 cells and returns the score (The sum of the scores of the two
	 * given cells).
	 */
	public int merge(Cell self, Cell other)
	{
		//Careful -> 1 and 1 should merge to 2
		//else simply check with MyMathUtils
		int val = 0;
		val = self.getVal() + other.getVal();
		self.setVal(val);
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
	 * Returns whether the best value of the board is 144.
	 */
	public boolean win(Board board)
	{
		boolean b;
		
		if(getWinValue(board) == WIN_VALUE)
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
