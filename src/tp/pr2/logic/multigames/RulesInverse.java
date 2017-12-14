package tp.pr2.logic.multigames;

import java.util.Random;

import tp.pr2.logic.Board;
import tp.pr2.logic.Cell;
import tp.pr2.logic.Position;

/**
 * Sets the standard rules for the inverse 2048 game.
 */
public class RulesInverse implements GameRules
{
	private static final int winnerValue = 2;
	
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
	}
	
	/**
	 * Returns whether the given cells can be merged. This is, if they are equal.
	 */
	public boolean canMerge(Cell self, Cell other)
	{
		return (!self.isEmpty() && self.getVal() == other.getVal());
	}
	
	/**
	 * If possible, merges 2 cells and returns the score (see formula in implementation).
	 */
	public int merge(Cell self, Cell other)
	{
		int val = 0;

		if(canMerge(self, other))
		{
			val = 4*(2048/self.getVal());

			self.setVal(self.getVal()/2);
			other.setVal(0);
		}
		
		return val;
	}
	
	/**
	 * Returns the lowest value on the board.
	 */
	public int getWinValue(Board board)
	{
		//LOWEST (ABOVE 0)
		
		int min = 2048, aux = 2048;
		Position pos = new Position();
		
		for(int i = 0; i < board.getBoardSize(); i++)
		{
			pos.setRow(i);
			for(int j = 0; j < board.getBoardSize(); j++)
			{
				pos.setCol(j);
				aux = board.getCell(pos).getVal();
				
				if(aux > 0 && aux < min)
				{
					min = aux;
				}
			}
		}
		
		return min;
	}
	
	/**
	 * Returns whether the best value of the board is 2.
	 */
	public boolean win(Board board)
	{
		boolean b;
		
		if(getWinValue(board) == winnerValue)
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
