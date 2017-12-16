package tp.pr2.logic.multigames;

import tp.pr2.logic.Board;
import tp.pr2.logic.MoveResults;
import tp.pr2.logic.GameState;
import tp.pr2.logic.GameStateStack;
import tp.pr2.logic.Cell;
import tp.pr2.logic.Direction;
import java.util.Random;

/**
*	Stores the current state of the game and contains useful methods for its management.
*/
public class Game
{
	private Board _board;
	private Random _myRandom;
	
	private int _size;
	private int _initCells;
	private int _score;
	private int _winValue;
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
	*	Executes a move in the given direction, updating the relevant information.
	*/
	public void move(Direction dir)
	{
	    GameState currentState = this.getState();
		MoveResults results = _board.executeMove(dir, _currentRules);
		
		//If there was a move, updates the data, saves the previous state and clears
		//the undone stack (you can't redo after moving)
		if(results.getMoved()) 
		{
			_score += results.getPoints();
			
			_currentRules.addNewCell(_board, _myRandom);
			_winValue = _currentRules.getWinValue(_board);
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
		_myRandom.setSeed(_seed);
		
		_mainStack = new GameStateStack();
		_undoneStack = new GameStateStack();

		_board = _currentRules.createBoard(_size);
		_currentRules.initBoard(_board, _initCells, _myRandom);

		_winValue = _currentRules.getWinValue(_board);
	}

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
	*	Returns the current score.
	*/
	public int getScore() 
	{
		return _score;
	}

	/**
	 * Returns the PRNG.
	 */
	public Random getRandom()
	{
		return _myRandom;
	}
	
	/**
	 * Stores the game state into a GameState object.
	 */
	public GameState getState()
	{
		GameState state = new GameState(_board.getState(), _score, _winValue);
		return state;
	}

	/**
	 * Returns the Board.
	 */
	public Board getBoard() {
		return _board;
	}

	/**
	 * Returns the current GameRules.
	 */
	public GameRules getRules() {
		return _currentRules;
	}

	/**
	 * Loads the GameState into the game. 
	 */
	public void setState(GameState aState)
	{
		_board.setState(aState.getBoardState());
		_score = aState.getScore();
		_winValue = aState.getWinValue();
	}

	/**
	*	Checks if the game is stuck (there are no possible moves).
	*/
	public boolean isStuck() 
	{
		return _currentRules.lose(_board);
	}

	/**
	 * Checks if the player has won the game.
	 */
	public boolean win() 
	{
		return _currentRules.win(_board);
	}

	/**
	 * Merges the second Cell into the first one and returns the resulting score.
	 */
	public int merge(Cell c1, Cell c2) 
	{
		return _currentRules.merge(c1, c2);
	}
	
	/**
	*	Returns a string representation of the game.
	*/
	public String toString()
	{
		String margin = "   ";
   		String gameString = String.format("%1$s best value: %2$-5d %1$s score: %3$-5d%n", margin, _winValue, _score);
		
		return gameString + _board.toString();
	}
}
