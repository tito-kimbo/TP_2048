package tp.pr2.logic.multigames;

import java.util.Random;

import tp.pr2.logic.Board;
import tp.pr2.logic.Cell;
import tp.pr2.logic.Position;

/**
 * Sets the standard rules for the regular 2048 game.
 */
public class Rules2048 implements GameRules
{
	private static final int winnerValue = 2048;
	
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
	}
	
	/**
	 * Returns whether the given cells can be merged. This is, if they are equal.
	 */
	public boolean canMerge(Cell self, Cell other)
	{
		return (!self.isEmpty() && self.getVal() == other.getVal());
	}
	
	/**
	 * If possible, merges 2 cells and returns the score (two times the value of the cells).
	 */
	public int merge(Cell self, Cell other)
	{
		//We will suppose the cells are neighbors
		int val = 0;
		
		if(canMerge(self, other))
		{
			val = 2*self.getVal();
			//NOW WE MERGE
			self.setVal(2*self.getVal());
			other.setVal(0);
		}
		
		
		return val;
	}
	
	/**
	 * Returns the highest value on the board.
	 */
	public int getWinValue(Board board)
	{
		//HIGHEST
		int max = 2, aux = 2;
		Position pos = new Position();
		
		for(int i = 0; i < board.getBoardSize(); i++)
		{
			pos.setRow(i);
			for(int j = 0; j < board.getBoardSize(); j++)
			{
				aux = board.getCell(pos).getVal();
				pos.setCol(j);
				
				if(aux > max)
				{
					max = aux;
				}
			}
		}
		
		return max;
	}
	
	/**
	 * Returns whether the best value of the board is 2048.
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
