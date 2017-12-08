package tp.pr2.logic.multigames;

import java.util.Random;

import tp.pr2.logic.Board;
import tp.pr2.logic.Cell;
import tp.pr2.logic.Position;

/**
 * Sets the standard rules for the regular 2048 game
 */
public class Rules2048 implements GameRules
{
	private static final int winnerValue = 2048;
	
	public void addNewCellAt(Board board, Position pos, Random rand)
	{
		//GENERATES RANDOMLY 2 or 4 WITH 90%,10% RESPECTIVELY
		int val = rand.nextInt() % 10;
		if(val == 0)
		{
			val = 4;
		}
		else
		{
			val = 2;
		}		
		board.setCell(pos,  val);
	}
	
	public int merge(Cell self, Cell other)
	{
		//We will suppose the cells can be merged (?)
		int val;
		
		val = 2*self.getVal();
		
		
		return val;
	}
	
	public int getWinValue(Board board)
	{
		//HIGHEST
		
		return 0;
	}
	
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
