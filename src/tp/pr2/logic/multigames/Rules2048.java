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
		//We will suppose the cells are neighbors
		int val = 0;
		
		if(self.getVal() == other.getVal())
		{
			val = 2*self.getVal();
			//NOW WE MERGE
			self.setVal(2*self.getVal());
			other.setVal(0);
		}
		
		
		return val;
	}
	
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
				aux = board.getCell(pos);
				pos.setCol(j);
				
				if(aux > max)
				{
					max = aux;
				}
			}
		}
		
		return max;
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
