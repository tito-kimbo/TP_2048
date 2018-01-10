package tp.pr3.logic;

/** 
*	Enum indicating the four possible directions: up, down, right and left.
*/
public enum Direction 
{
	UP("up"), DOWN("down"), LEFT("left"), RIGHT("right");

	private final String text;

	Direction(String text) {
		this.text = text;
	}

	/**
	 * Returns a String representation of the direction.
	 */
	public String toString()
	{
		return this.text;
	}

	/**
	 * Returns the Direction corresponding to the given text.
	 */
	public static Direction fromString(String text)
	{
		Direction result = null;
		for(Direction d : Direction.values())
			if(d.text.equalsIgnoreCase(text))
				result = d;
		return result;
	}
};
