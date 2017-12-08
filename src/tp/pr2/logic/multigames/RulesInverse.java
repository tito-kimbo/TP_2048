package tp.pr2.logic.multigames;

import java.util.Random;

import tp.pr2.logic.Board;
import tp.pr2.logic.Cell;
import tp.pr2.logic.Position;

public class RulesInverse implements GameRules
{
	private static final int winnerValue = 2;
	
	public void addNewCellAt(Board board, Position pos, Random rand)
	{
		//Creates 2048 with 90% probability and 1024 with 10%
		int val = rand.nextInt() % 10;
		if(val == 0)
		{
			val = 1024;
		}
		else
		{
			val = 2048;
		}		
		board.setCell(pos,  val);
	}
	
	public int merge(Cell self, Cell other)
	{
		int val;
		
		val = 4*(2048/self.getVal());
		
		return val;
	}
	
	public int getWinValue(Board board)
	{
		//LOWEST (ABOVE 0)
		return 0; //???????????????????????????????????????????????????????
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
