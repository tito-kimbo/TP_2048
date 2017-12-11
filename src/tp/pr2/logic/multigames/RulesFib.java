package tp.pr2.logic.multigames;

import java.util.Random;

import tp.pr2.logic.Board;
import tp.pr2.logic.Cell;
import tp.pr2.logic.Position;
import tp.pr2.util.MyMathsUtil;

public class RulesFib implements GameRules
{
	private static final int winnerValue = 144;
	
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
	}
	
	public int merge(Cell self, Cell other)
	{
		//Careful -> 1 and 1 should merge to 2
		//else simply check with MyMathUtils
		int val = 0, selfVal = self.getVal(), otherVal = other.getVal();
		int selfNextFib, otherNextFib;
		
		selfNextFib = MyMathsUtil.nextFib(selfVal);
		otherNextFib = MyMathsUtil.nextFib(otherVal);
		
		//CHECK FORMAT
		if( 
			(selfVal == otherVal && selfVal == 1) || 
			(otherVal == selfNextFib ||
			(selfVal == otherNextFib)) 
		  )
		{
			val = self.getVal() + other.getVal(); //CORRECTO?
			self.setVal( MyMathsUtil.nextFib(MyMathsUtil.max(selfVal, otherVal)) );
			other.setVal(0);
		}
		
		return val;
	}
	
	public int getWinValue(Board board)
	{
		//HIGHEST
		int max = 1, aux = 1;
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
