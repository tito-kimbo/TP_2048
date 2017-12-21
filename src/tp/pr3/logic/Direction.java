package tp.pr3.logic;

/** 
*	Enum indicating the four possible directions: up, down, right and left.
*/
public enum Direction 
{
	UP, DOWN, LEFT, RIGHT;
	
	public String toString()
	{
		String s = "";
		
		switch(this)
			{
			case UP:
				{
					s = "up";
				}break;
			case DOWN:
				{
					s = "down";
				}break;
			case LEFT:
				{
					s = "left";
				}break;
			case RIGHT:
				{
					s = "right";
				}break;
			}
		
		return s;
	}
};
