package tp.pr1;

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
	 *	Returns false if the neighbouring Cell is empty or its value is different from the one of the
	 *	current Cell. Else it merges the two cells in the current one (the neighbour becomes empty)
	 *	and returns true.
	 */
	public boolean doMerge(Cell neighbour) 
	{
		boolean done;
		
		if(neighbour.isEmpty() || neighbour.getVal() != value)
		{
			done = false;
		}			
		else
		{
			value = 2*value;
			neighbour.setVal(0);
			done = true;
		}
		
		return done;
	}
	
	/**
	*	Returns the value of the Cell as a String.
	*/
	public String toString() 
	{
		return String.valueOf(value);
	}
	
};
