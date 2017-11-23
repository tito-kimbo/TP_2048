package tp.pr2.logic.multigames;

import tp.pr1.Board;
import tp.pr1.Direction;
import tp.pr1.MoveResults;
import tp.pr1.Position;
import java.util.Random;

/**
*	Stores the current state of the game.
*/
public class Game
{
	private Board _board;
	private Random _myRandom;
	private int _size;
	private int _initCells;
	private int _score;  
	private int _maxToken; 

	/**
	*	Constructor called from Controller. Instantiates the board.
	*/
	public Game(int size, int initCells, Random myRandom)
	{
		_size = size;
		_initCells = initCells;
		_myRandom = myRandom;
		_board = new Board(size);
	}

	/**
	*	Executes a move in the given direction, updates the score and maximum token and
	*	creates a new Cell.
	*/
	public void move(Direction dir)
	{
		MoveResults results = _board.executeMove(dir);
		
		//If there was a move, updates the data
		if(results.getMoved()) 
		{
			_score += results.getPoints();
			
			if(_maxToken < results.getMaxToken())
			{
				_maxToken = results.getMaxToken();
			}	
			
			newCell();
		}
	}
	
	/**
	*	Resets the game to its initial state.
	*/
	public void reset(long seed)
	{

		_score = 0;
		_maxToken = 0;
		_board = new Board(_size);
		_myRandom.setSeed(seed);

		for(int i = 0; (i < _initCells && i < _size*_size); ++i) 
		{
			newCell();
		}
	}
			
	/**
	*	Creates a new cell in a random empty position. The cell has a value of 2
	*	with probability 90% or 4 with probability 10%.
	*/
	public void newCell() {

		Position pos = new Position(0,0);
		int counter  = _myRandom.nextInt(_board.getEmptyCells())+1;
		int valor = 0, i=0, j;

		//Searches the board for the (counter) empty position
		while(i < _size && counter > 0) 
		{
			j = 0;
			
			while(j < _size && counter > 0) 
			{
				pos.setRow(i);
				pos.setCol(j);
				
				if(_board.isEmptyCell(pos)) 
				{
					if(counter > 0)	
					{
						counter--;		
					}				
				}
				
				j++;
			}
			
			i++;
		}

		//Generates a 2 with probability 0.9, or a 4 with probability 0.1
		if(counter == 0)
		{
			if(_myRandom.nextInt(10) < 9) 
			{
				_board.setCell(pos, 2);
				valor = 2;
			}
			else 
			{
				_board.setCell(pos, 4);
				valor = 4;
			}
			
			if(_maxToken < valor)
			{
				_maxToken = valor;
			}				

			_board.setEmptyCells(_board.getEmptyCells()-1);
		}
	}

	/**
	*	Returns the maximum token in the board.
	*/
	public int getMaxToken() 
	{
		return _maxToken;
	}

	/**
	*	Returns the current score.
	*/
	public int getScore() 
	{
		return _score;
	}

	/**
	*	Checks if the game is stuck (there are no possible moves).
	*/
	public boolean isStuck() 
	{
		return _board.isStuck();
	}
	
	/**
	*	Returns a string representation of the game.
	*/
	public String toString()
	{
		String margin = "   ";
   		String gameString = String.format("%1$s highest: %2$-5d %1$s score: %3$-5d%n", margin, _maxToken, _score);
		
		return gameString + _board.toString();
	}
}
