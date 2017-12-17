package tp.pr2.logic;

import tp.pr2.logic.Direction;

/**
 *	Represents a position on a bidimensional board, with origin (0,0) at the top
 *	left corner of the board. Does not admit negative coordinates.
 */
public class Position 
{
    
    private int row, column;
    
	/**
	*	Creates a new  Position with default initial values of (0,0).
	*/
    public Position() 
	{
		row = 0;
		column = 0;	
    }

	/**
	*	Creates a new Position with the values given if they are non-negative. If any of the values is negative, it will be set to 0.
	*/
    public Position(int x, int y) 
	{
		if(x > 0)
		{
			row = x;	
		}
		else
		{
			row = 0;
		}			
		if(y > 0)
		{
			column = y;
		}			
		else 
		{
			column = 0;
		}
    }

	/**
	*	Creates a new Position copying the internal values of the given Position.
	*/
	
    public Position(Position pos) 
	{
		row = pos.getRow();
		column = pos.getCol();
    }

	/**
	*	Returns the value of the row parameter.
	*/
    public int getRow() 
	{
		return row;
    }
	
	/** 
	*	Returns the value of the column parameter.
	*/
    public int getCol() 
	{
		return column;
    }

	/** 
	*	Sets the row parameter to a new value, given this one is non-negative.
	*/
    public void setRow(int x) 
	{
		if(x >= 0)
		{
			row = x;
		}			
    }

	/** 
	*	Sets the column parameter to a new value, given this one is non-negative.
	*/
    public void setCol(int y) 
	{
		if(y >= 0)
		{
			column = y;
		}			
    }

	/**
	*	Returns the neighboring Position if it exists, else returns the current Position.
	*/
    public Position neighbor(Direction dir, int size) 
	{
		Position newPos = new Position(row, column);
		
		switch(dir) 
		{
		case UP:
			if(column > 0)
			{
				newPos.setCol(column-1);
			}				
			break;
		case LEFT:
			if(row > 0)
			{
				newPos.setRow(row-1);
			}				
			break;
		case DOWN:
			if(column < size-1)
			{
				newPos.setCol(column+1);
			}				
			break;
		case RIGHT:
			if(row < size-1)
			{
				newPos.setRow(row+1);
			}				
			break;
		}
		
		return newPos;
    }

};
