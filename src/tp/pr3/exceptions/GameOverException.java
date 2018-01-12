package tp.pr3.exceptions;

public class GameOverException extends RuntimeException
{
	//Generated serialVersionUID
	private static final long serialVersionUID = 7329608758198834211L;
	private boolean win;
	private int score, highest;

	public GameOverException(boolean win, int score, int highest)
	{
		super();
		this.win = win;
		this.score = score;
		this.highest = highest;
	}

	public boolean win()
	{
		return win;
	}

	public String toString()
	{
		String message;
		if(win) message = "Congratulations, you won the game!\n";
		else message = "Game Over... :(\n";
		return message + "Score: " + score + " | Best value: " + highest;
	}
}