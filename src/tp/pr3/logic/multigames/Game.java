package tp.pr3.logic.multigames;

import java.util.Random;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;

import tp.pr3.logic.Board;
import tp.pr3.logic.Cell;
import tp.pr3.logic.Direction;
import tp.pr3.logic.GameState;
import tp.pr3.logic.GameStateStack;
import tp.pr3.logic.MoveResults;
import tp.pr3.exceptions.CustomEmptyStackException;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.exceptions.CustomIOException;


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
	
	private GameType type;
	//Sets the rules for the current game

	/**
	*	Constructor called from Controller. Instantiates the board.
	*/
	public Game(GameType gameType, int size, int initCells, long seed)
	{
		reset(gameType, size, initCells, seed);
	}

	/**
	*	Executes a move in the given direction, updating the relevant information.
	*/
	public void move(Direction dir) throws GameOverException
	{
	    GameState currentState = this.getState();
		MoveResults results = _board.executeMove(dir, type.getRules());
		
		//If there was a move, updates the data, saves the previous state and clears
		//the undone stack (you can't redo after moving)
		if( results.getMoved()) 
		{
			_score += results.getPoints();
			
			type.getRules().addNewCell(_board, _myRandom);
			_winValue = type.getRules().getWinValue(_board);
			_mainStack.push(currentState);
			_undoneStack = new GameStateStack();
		}
		if(win()) throw new GameOverException(true, _score, _winValue);
		if(isStuck()) throw new GameOverException(false, _score, _winValue);
	}

	
	
	/**
	 *      Resets the game with the given parameters.
	 */
	public void reset(GameType type, int size, int initCells, long seed)
	{

		_score = 0;
		_size = size;
		_initCells = initCells;
		_seed = seed;
		
		_myRandom = new Random(seed);

		this.type = type;
		
		_mainStack = new GameStateStack();
		_undoneStack = new GameStateStack();

		_board = type.getRules().createBoard(_size);
		type.getRules().initBoard(_board, _initCells, _myRandom);

		_winValue = type.getRules().getWinValue(_board);
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

		_board = type.getRules().createBoard(_size);
		type.getRules().initBoard(_board, _initCells, _myRandom);

		_winValue = type.getRules().getWinValue(_board);
	}

	/**
	 * Loads the previous saved state and saves the current one (for redo).
	 */
	public void undo() throws CustomEmptyStackException
	{
		GameState state;
		try
		{
			state = _mainStack.pop();
			_undoneStack.push(this.getState());
			this.setState(state);	
		}
		catch(CustomEmptyStackException e)
		{
			throw new CustomEmptyStackException("Undo is not available!");
		}
	}
	
	/**
	 * Stores the state of the game in a buffer with the adequate format.
	 */
	public void store(BufferedWriter out) throws IOException
	{
		try
		{
			out.write("This file stores a saved " + type + " game\n");	
			_board.store(out);
			out.write(_score + " " + _winValue + " " + type.externalise() + "\n");
		}
		catch(IOException e)
		{
			throw e;
		}
	}
	
	/*
	 * Reads the data related to the current game
	 */
	public void loadGame(BufferedReader in) throws IOException, CustomIOException
	{
		String aux;
		String[] auxArray;
		
		GameType [] types = GameType.values();
		GameType auxType;
		String strTypes = "";
		int i = types.length;
		
		//Build a regular expression that matches any game type description
		for(int j = 0; j < i; ++j) strTypes = strTypes + "(";
		
		for(GameType t : types)
		{
			strTypes = strTypes + t.toString();
		        strTypes = strTypes + ")";
			if(i < types.length) strTypes = strTypes + ")";
			if(i > 1) strTypes = strTypes + "|(";
			--i;
		}
		
		try
		{
		        //Check first line
			aux = in.readLine();
			
			if(!aux.matches("This file stores a saved " + strTypes + " game"))
			{
				throw new CustomIOException("Invalid file format");
			}

			
			_board.load(in);
			
			aux = in.readLine();
			//Parse the line and get the relevant data
			auxArray = aux.split("\\s+");
			_score = Integer.parseInt(auxArray[0]);
			_winValue = Integer.parseInt(auxArray[1]);
			_board.updateMaxMinValue(_winValue);
			auxType = GameType.setType(auxArray[2]);
			
			if(auxType == null)
			{
				throw new CustomIOException("Invalid file format");
			}
			else
			{
				type = auxType;
			}
		}
		catch(IOException e)
		{
			throw e;
		}
	}

	/**
	 * Loads the next saved state and stores the current one (for undo).
	 */
	public void redo() throws CustomEmptyStackException
	{
		GameState state;
		try
		{
			state = _undoneStack.pop();
			_mainStack.push(this.getState());
			this.setState(state);
		}
		catch(CustomEmptyStackException e)
		{
			throw new CustomEmptyStackException("Nothing to redo!");
		}
	}

	/**
	*	Returns the current score.
	*/
	public int getScore() 
	{
		return _score;
	}

	/**
	*	Returns the current best value.
	*/
	public int getWinValue() 
	{
		return _winValue;
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
	public Board getBoard()
	{
		return _board;
	}

	/**
	 * Returns the current GameType.
	 */
	public GameType getType()
	{
		return type;
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
		return type.getRules().lose(_board);
	}

	/**
	 * Checks if the player has won the game.
	 */
	public boolean win() 
	{
		return type.getRules().win(_board);
	}

	/**
	 * Merges the second Cell into the first one and returns the resulting score.
	 */
	public int merge(Cell c1, Cell c2) 
	{
		return type.getRules().merge(c1, c2);
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
