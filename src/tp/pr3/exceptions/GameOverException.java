package tp.pr3.exceptions;

public class GameOverException extends RuntimeException
{
	//Generated serialVersionUID
	private static final long serialVersionUID = 7329608758198834211L;
	private boolean win;
	private boolean exit;
	private int score, highest;

	public GameOverException(boolean win, int score, int highest)
	{
		super();
		this.win = win;
		this.exit = false;
		this.score = score;
		this.highest = highest;
	}
	
	public GameOverException()
	{
		super();
		win = false;
		exit = true;
		score = 0;
		highest = 0;
	}
		
	public boolean win()
	{
		return win;
	}

	public boolean exit()
	{
		return exit;
	}

	public String getMessage()
	{
		String message;
		if(win) message = "Congratulations, you won the game!\n";
		else message = "Game Over... :(\n";
		return message + "Score: " + score + " | Best value: " + highest;
	}
}
