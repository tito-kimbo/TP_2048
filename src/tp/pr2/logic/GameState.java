package tp.pr2.logic;

/**
 * Stores a Board as a matrix of integers, a score and a maximum token.
 */
public class GameState
{
	private int[][] boardState;
	private int score;
	private int highest;

	/**
	 * Constructor for the class.
	 */
	public GameState(int[][] board, int score, int token)
	{
		this.boardState = board;
		this.score = score;
		highest = token;
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
	 * Returns the stored maximum token
	 */
	public int getHighest()
	{
		return highest;
	}

	/**
	 * Stores the given int matrix.
	 */
	public void setBoardState(int[][] aState)
	{
		boardState = aState;
	}

	/**
	 * Stores que game score.
	 */
	public void setScore(int s)
	{
		score = s;
	}

	/**
	 * Stores the maximum token of the game.
	 */
	public void setHighest(int token)
	{
		highest = token;
	}
}