package tp.pr2.logic.multigames;

import tp.pr2.logic.Board;
import tp.pr2.logic.Direction;
import tp.pr2.logic.MoveResults;
import tp.pr2.logic.GameState;
import tp.pr2.logic.GameStateStack;
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
	private long _seed;

	private GameStateStack _mainStack;
	//Stores up to 20 previous states
	
	private GameStateStack _undoneStack;
	//Stores up to 20 "posterior" states (after executing the undo command)
	
	private GameRules _currentRules;
	//Sets the rules for the current game

	/**
	*	Constructor called from Controller. Instantiates the board.
	*/
	public Game(GameRules rules, int size, int initCells, long seed)
	{
		_size = size;
		_initCells = initCells;
		_seed = seed;
		_myRandom = new Random(seed);
		_currentRules = rules;
		
		reset();
	}

	/**
	*	Executes a move in the given direction, updates the score and maximum token and
	*	creates a new Cell.
	*/
	public void move(Direction dir)
	{
		GameState currentState = this.getState();
		MoveResults results = _board.executeMove(dir);
		
		//If there was a move, updates the data, saves the previous state and clears
		//the undone stack (you can't redo after moving)
		if(results.getMoved()) 
		{
			_score += results.getPoints();
			
			if(_maxToken < results.getMaxToken())
			{
				_maxToken = results.getMaxToken();
			}	
			
			//newCell();
			_currentRules.addNewCell(_board, _myRandom);

			_mainStack.push(currentState);
			_undoneStack = new GameStateStack();
		}
	}
	
	/**
	*	Resets the game to its initial state.
	*/
	public void reset()
	{

		_score = 0;
		_maxToken = 0;
		_myRandom.setSeed(_seed);
		
		_mainStack = new GameStateStack();
		_undoneStack = new GameStateStack();

		_board = _currentRules.createBoard(_size);
		_currentRules.initBoard(_board, _initCells, _myRandom);
		/*for(int i = 0; (i < _initCells && i < _size*_size); ++i) 
		{
			newCell();
		}*/
	}
			
	/**
	*	Creates a new cell in a random empty position. The cell has a value of 2
	*	with probability 90% or 4 with probability 10%.
	*/
/*
	public void newCell()
	{

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
*/
	
	/**
	 * Loads the previous saved state and saves the current one (for redo).
	 */
	public boolean undo()
	{
		GameState state;
		boolean result;
		if(!_mainStack.isEmpty()) {
			state = _mainStack.pop();
			_undoneStack.push(this.getState());
			this.setState(state);
			result = true;
		}
		else result = false;
		return result;
	}

	/**
	 * Loads the next saved state and stores the current one (for undo).
	 */
	public boolean redo()
	{
		GameState state;
		boolean result;
		if(!_undoneStack.isEmpty()) {
			state = _undoneStack.pop();
			_mainStack.push(this.getState());
			this.setState(state);
			result = true;
		}
		else result = false;
		return result;
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

	public Random getRandom()
	{
		return _myRandom;
	}
	
	/**
	 * Stores the game state into a GameState object.
	 */
	public GameState getState()
	{
		GameState state = new GameState(_board.getState(), _score, _maxToken);
		return state;
	}

	/**
	 * Loads the GameState into the game. 
	 */
	public void setState(GameState aState)
	{
		_board.setState(aState.getBoardState());
		_score = aState.getScore();
		_maxToken = aState.getHighest();
	}

	/**
	*	Checks if the game is stuck (there are no possible moves).
	*/
	public boolean isStuck() 
	{
		return _board.isStuck();
	}

	/**
	 * Checks if the player has won the game.
	 */
	public boolean win() {
		return _currentRules.win(_board);
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
