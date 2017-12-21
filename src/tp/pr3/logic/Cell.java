package tp.pr3.logic;

import tp.pr3.logic.multigames.GameRules;
/**
 *	Represents a cell of the board, with a Position and a value.
 */

public class Cell
{
	
	private int value;
	
	/**
	*	Creates a new Cell with default value of 0.
	*/
	public Cell() 	
	{
		value = 0;
	}
	
	/**
	*	Creates a new Cell with the given value.
	*/
	public Cell(int val) 
	{
		value = val;
	}
	
	/**
	*	Returns the current value of the Cell.
	*/
	public int getVal()
	{
		return value;
	}
	
	/**
	*	Sets the value of the Cell to the given argument.
	*/
	public void setVal(int newVal)
	{
		value = newVal;
	}
	
	/**
	*	Returns true if the value of the Cell is 0.
	*/
	public boolean isEmpty()
	{
		return value == 0;
	}
	
	/**
	 *	Returns the score resulting of the merge of this cell with the neighbor cell under the
	 *      current rules.
	 */
	public int doMerge(Cell neighbor, GameRules rules) 
	{
		int val = 0;
		if(rules.canMerge(this, neighbor))
		{
			val = rules.merge(this, neighbor);
		}
		return val;
	}
	
	/**
	*	Returns the value of the Cell as a String.
	*/
	public String toString() 
	{
		return String.valueOf(value);
	}
	
};
