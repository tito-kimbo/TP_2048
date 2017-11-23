package tp.pr2.logic;

/**
*	Indicates if a move has taken effect and its results (the current maximum token and the score value of said movement).
*/
public class MoveResults 
{
	private boolean moved;
	private int points, maxToken;
	
	/**
	*	Creates a new MoveResults with default values.
	*/
	public MoveResults()
	{
		moved = false;
		points = 0;
		maxToken = 0;
	}
	

	/**
	*	Returns whether the movement has taken effect.
	*/
	public boolean getMoved()
	{
		return moved;
	}
	
	/**
	*	Returns the score value of the movement.
	*/
	public int getPoints()
	{
		return points;
	}
	
	/**
	*	Returns the current maximum Token.
	*/
	public int getMaxToken()
	{
		return maxToken;
	}
	
	/**
	*	Sets the moved value to a given boolean.
	*/
	public void setMoved(boolean b)
	{
		moved = b;
	}
	
	/**
	*	Sets the score value to a given int.
	*/
	public void setPoints(int p)
	{
		points = p;
	}
	
	/**
	*	Sets the maximum token value to a given int.
	*/
	public void setMaxToken(int t)
	{
		maxToken = t;
	}
	
};
