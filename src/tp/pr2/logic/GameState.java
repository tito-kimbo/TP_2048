package tp.pr2.logic;

/**
 * Stores a Board as a matrix of integers, a score and a maximum token.
 */
public class GameState
{
	private int[][] boardState;
	private int score;
	private int winValue;

	/**
	 * Constructor for the class.
	 */
	public GameState(int[][] board, int score, int winValue)
	{
		this.boardState = board;
		this.score = score;
		this.winValue = winValue;
	}

	/**
	 * Returns a matrix with the stored values.
	 */
	public int[][] getBoardState()
	{
		return boardState;
	}

	/**
	 * Returns the stored score
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * Returns the win value.
	 */
	public int getWinValue()
	{
		return winValue;
	}

	/**
	 * Stores the given int matrix.
	 */
	public void setBoardState(int[][] aState)
	{
		boardState = aState;
	}

	/**
	 * Stores the game score.
	 */
	public void setScore(int s)
	{
		score = s;
	}

	/**
	 * Stores the win value.
	 */
	public void setWinValue(int value)
	{
		winValue = value;
	}
}
