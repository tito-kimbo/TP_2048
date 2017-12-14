package tp.pr2.logic;

import tp.pr2.logic.Position;
import tp.pr2.logic.Cell;
import tp.pr2.logic.util.MyStringUtils;

/**
*	Represents a Board of Cells and has the main movement methods.
*/

public class Board 
{
	private Cell[][] _board; 
	private int _boardSize; 
	private int _emptyCells;		
	
	/**
	* Creates a new Board of the given size, initializing all of its Cells to empty Cells.
	*/
	public Board(int size)
	{
		_boardSize = size;
		_board = new Cell[_boardSize][_boardSize];
		_emptyCells = _boardSize*_boardSize;

		for(int i = 0; i < _boardSize; i++)
		{
		        for(int j = 0; j < _boardSize; j++)
			{
				_board[i][j] = new Cell();
			}
		}
	}
	
	/**
	*	Sets the empty Cells counter to the given value.
	*/
	public void setEmptyCells(int cells)
	{
		_emptyCells = cells;
	}	

	/**
	*	Sets the Cell at a given Position to a given value.
	*/
	public void setCell(Position pos, int value)
	{
		_board[pos.getRow()][ pos.getCol()].setVal(value);
	}

	/**
	 * Loads the given state into the board.
	 */
	public void setState(int[][] aState)
	{
		_board = new Cell[aState.length][aState.length];
		for(int i = 0; i < _boardSize; i++)
			for(int j = 0; j < _boardSize; j++)
				_board[i][j] = new Cell(aState[i][j]);
	}

	/**
	*	Returns the Cell at a given Poisition.
	*/
	public Cell getCell(Position pos) 
	{
		return _board[pos.getRow()][pos.getCol()];
	}

	/**
	*	Returns the current number of empty Cells in the Board.
	*/
	public int getEmptyCells() 
	{
		return _emptyCells;
	}
	
	/**
	*	Returns the size of the Board.
	*/
	public int getBoardSize() 
	{
		return _boardSize;
	}

	/**
	 * Returns a representation of the board as a matrix of integers.
	 */
	public int[][] getState()
	{
		int[][] state = new int[_boardSize][_boardSize];
		for(int i = 0; i < _boardSize; i++)
			for(int j = 0; j < _boardSize; j++)
				state[i][j] = _board[i][j].getVal();
		return state;
	}

	/**
	*	Returns whether the Cell at a given Position is empty.
	*/
	public boolean isEmptyCell(Position pos) 
	{
		return _board[pos.getRow()][pos.getCol()].isEmpty();
	}
        
	/**
	*	Transposes the Board. Sends every Cell in the Position (i,j) to (j,i).
	*/
	public void transpose()
	{
		Cell aux;
		
		for(int i = 0; i < _boardSize-1; i++)
		{
			for(int j = i+1; j < _boardSize; j++)
			{
				aux = _board[i][j];
				
				_board[i][j] = _board[j][i];
				_board[j][i] = aux;
			}
		}
	}
	
	/**
	*	Reflects the Board. Sends every Cell in the Position (i,j) to (i, SIZE-1-j).
	*/
	public void reflect()
	{
		Cell aux;
		
		for(int i = 0; i < _boardSize; i++)
		{
			for(int j = 0; j < _boardSize/2; j++) 
			{
				aux = _board[i][j];
				
				_board[i][j] = _board[i][_boardSize-1-j];
				_board[i][_boardSize-1-j] = aux;
			}
		}
	}

	/**
	*	Returns the Board as a String.
	*/
	public String toString() 
	{
		int cellSize = 7;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String board = "";
		
		for(int i = 0; i < _boardSize; i++)
		{
			//Horizontal delimiters on top of the row
			for(int j = 0; j <= (cellSize-1)*_boardSize; j++)
			{
				board += hDelimiter;	
			}
			
			board += "\n";
			
			//Vertical Cell delimiters and the centered values
			for(int j = 0; j < _boardSize; j++)
			{
				board += vDelimiter;
				board += MyStringUtils.centre(String.valueOf(_board[i][j].getVal() ), 5);	
			}
			
			board += vDelimiter + "\n";
		}
		
		//Horizontal delimiters marking the end of the board
		for(int j = 0; j <= (cellSize-1)*_boardSize; j++)
		{
			board += hDelimiter;	
		}
			
		board += "\n";
		
		return board;
	}
	

};
