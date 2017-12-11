package tp.pr2.logic;

import tp.pr2.logic.Position;
import tp.pr2.logic.MoveResults;
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
		if(_board[pos.getRow()][pos.getCol()].isEmpty() && value != 0)
			_emptyCells--;
		
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
	public int getCell(Position pos) 
	{
		return _board[pos.getRow()][pos.getCol()].getVal();
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
	*	Moves every possible Cell to the right, saving the results in a given MoveResults.
	*/
	public void move_right(MoveResults m)
	{
		int aux1;
		Cell aux;
		boolean merged;
		
		for(int i = 0; i < _boardSize; i++)
		{
			aux1 = _boardSize-1;
			merged = false;		
			
			for(int j = _boardSize-1; j >= 0; j--)
			{
				
				//Moves the cell in position (i,j) to the cell in position (i, aux1)
				//Note that either j == aux1 or (i, aux1) is empty, so that two nonempty cells will never be swapped
				
				if( !( _board[i][j].isEmpty()) )
				{
					if(aux1 != j)
					{						
						aux = _board[i][j];
						_board[i][j] = _board[i][aux1];
						_board[i][aux1] = aux;
					        m.setMoved(true);
					}

					//If the cell in (i, aux1) has already been merged, do not merge again
					
					if(!merged && aux1 < _boardSize-1)
					{
						merged = ;
						
						if(merged)
						{
							m.setPoints(m.getPoints() + _board[i][aux1+1].getVal());
							m.setMoved(true);
							
							if(_board[i][aux1+1].getVal() > m.getMaxToken())
							{
								m.setMaxToken(_board[i][aux1+1].getVal());
							}
							
							_emptyCells++;
							aux1++;
						}
					}
					else merged = false;
					
					aux1--;
				}
			}
		}
	}
	
	/**
	*	Moves every possible Cell to the right, saving the results in a given MoveResults.
	*/
	public void move_left(MoveResults m)
	{
		reflect();
		move_right(m);
		reflect();
	}
	
	/**
	*	Moves every possible Cell upwards, saving the results in a given MoveResult.
	*/
	public void move_up(MoveResults m)
	{
		transpose();
		move_left(m);
		transpose();
	}
	
	/**
	*	Moves every possible Cell downwards, saving the results in a given MoveResult.
	*/
	public void move_down(MoveResults m)
	{
		transpose();
		move_right(m);
		transpose();
	}
	
	/**
	*	Moves the Board in the given Direction.
	*/
	public MoveResults executeMove(Direction dir)
	{
		MoveResults m = new MoveResults();
		
		switch(dir)
		{
			case RIGHT:
			{
				move_right(m);
			}break;
			case LEFT:
			{
				move_left(m);
			}break;
			case UP:
			{
				move_up(m);
			}break;
			case DOWN:
			{
				move_down(m);
			}break;
		}
		
		
		return m;
	}
	
	/**
	*	Returns whether there exist possible movements.
	*/
	public boolean isStuck() 
	{
		boolean stuck = true;
		int i = 0, j;

		if(_emptyCells == 0) 
		{
			while(stuck && i < _boardSize) 
			{
				j = 0;
				
				while(stuck && j < _boardSize) 
				{
					//Checks for a non-empty element to the right or below (if applicable ~ not out of bounds) for each cell in the board
					if( (j+1 < _boardSize && _board[i][j].getVal() == _board[i][j+1].getVal()) || (i+1 < _boardSize && _board[i][j].getVal() == _board[i+1][j].getVal()))
					{
							stuck = false;
					}
					
					j++;
				}
				
				i++;
			}
		}
		else
		{
			stuck = false;
		}	
		
		return stuck;
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
