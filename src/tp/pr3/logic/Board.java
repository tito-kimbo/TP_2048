package tp.pr3.logic;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;

import tp.pr3.logic.Cell;
import tp.pr3.logic.Position;
import tp.pr3.logic.util.MyStringUtils;
import tp.pr3.logic.multigames.GameRules;

/**
*	Represents a Board of Cells and has the main movement methods.
*/

public class Board 
{
	private Cell[][] _board; 
	private int _boardSize; 
	private int _emptyCells;	
	private int minValue, maxValue;
	
	/**
	* Creates a new Board of the given size, initializing all of its Cells to empty Cells.
	*/
	public Board(int size)
	{
		_boardSize = size;
		_board = new Cell[_boardSize][_boardSize];
		_emptyCells = _boardSize*_boardSize;
		minValue = Integer.MAX_VALUE;
		maxValue = Integer.MIN_VALUE;

		for(int i = 0; i < _boardSize; i++)
		{
		        for(int j = 0; j < _boardSize; j++)
			{
				_board[i][j] = new Cell();
			}
		}
	}

	/**
	*	Moves every possible Cell to the right, saving the results in a given MoveResults.
	*/
	
	public void move_right(MoveResults m, GameRules rules)
	{
		int aux1, points;
		Position auxPos1, auxPos2;
		boolean merged;
		
		for(int i = 0; i < _boardSize; i++)
		{
			aux1 = _boardSize-1;
			merged = false;		
			
			for(int j = _boardSize-1; j >= 0; j--)
			{
				auxPos1 = new Position(i, j);
				//Moves the cell in position (i,j) to the cell in position (i, aux1)
				//Note that either j == aux1 or (i, aux1) is empty, so that two nonempty cells will never be swapped
				
				if( !(isEmptyCell(auxPos1)) )
				{
					if(aux1 != j)
					{
						auxPos2 = new Position(i, aux1);
						setCell(auxPos2, getCell(auxPos1).getVal());
					        setCell(auxPos1, 0);
						
					        m.setMoved(true);
					}

					//If the cell in (i, aux1) has already been merged, do not merge again
					
					if(!merged && aux1 < _boardSize-1)
					{
						auxPos1 = new Position(i, aux1+1);
						auxPos2 = new Position(i, aux1);

						points = getCell(auxPos1).doMerge(getCell(auxPos2), rules);
						merged = (points > 0);
						
						if(merged)
						{
							m.setPoints(m.getPoints() + points);
							m.setMaxToken(getCell(auxPos1).getVal());
							m.setMoved(true);
							
							updateMaxMinValue(m.getMaxToken());
							
							_emptyCells++;
							aux1++;
						}
					}
					else
					{
						merged = false;
					}
					aux1--;
				}
			}
		}
	
	}
	
	/**
	*	Moves every possible Cell to the right, saving the results in a given MoveResults.
	*/
	public void move_left(MoveResults m, GameRules rules)
	{
		reflect();
		move_right(m, rules);
		reflect();
	}
	
	/**
	*	Moves every possible Cell upwards, saving the results in a given MoveResult.
	*/
	public void move_up(MoveResults m, GameRules rules)
	{
		transpose();
		move_left(m, rules);
		transpose();
	}
	
	/**
	*	Moves every possible Cell downwards, saving the results in a given MoveResult.
	*/
	public void move_down(MoveResults m, GameRules rules)
	{
		transpose();
		move_right(m, rules);
		transpose();
	}
	
	/**
	*	Moves the Board in the given Direction.
	*/
	public MoveResults executeMove(Direction dir, GameRules rules)
	{
		MoveResults m = new MoveResults();
		
		switch(dir)
		{
			case RIGHT:
			{
				move_right(m, rules);
			}break;
			case LEFT:
			{
				move_left(m, rules);
			}break;
			case UP:
			{
				move_up(m, rules);
			}break;
			case DOWN:
			{
				move_down(m, rules);
			}break;
		}
		
		
		return m;
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
	*  Stores the state of the board with the adequate format.
	 */
	public void store(BufferedWriter out) throws IOException
	{
		try
		{
			for(int i = 0; i < _boardSize; i++)
			{
				for(int j = 0; j < _boardSize-1; j++)
				{
					out.write(_board[i][j] + " ");
				}
				out.write(_board[i][_boardSize-1] + "\n");
			}	
		}
		catch(IOException e)
		{
			throw e;
		}
	}
	
	public void load(BufferedReader in) throws IOException
	{
		String s;
		String[] aux;
		
		try
		{
			s = in.readLine();
			aux = s.split("\\s+");
			_boardSize = aux.length;
			_board = new Cell[_boardSize][_boardSize];
			_emptyCells = _boardSize*_boardSize;
			
			minValue = Integer.MAX_VALUE;
			maxValue = Integer.MIN_VALUE;
			
			for(int i = 0; i < _boardSize; i++)
			{
				_board[0][i] = new Cell(Integer.parseInt(aux[i]));
				if(_board[0][i].getVal() != 0)
				{
					_emptyCells--;
				}
			}
			
			for(int i = 1; i < _boardSize; i++)
			{
				s = in.readLine();
				aux = s.split("\\s+");
				for(int j = 0; j < _boardSize; j++)
				{
					_board[i][j] = new Cell(Integer.parseInt(aux[j]));
					if(_board[i][j].getVal() != 0)
					{
						_emptyCells--;
					}
				}
			}
			
		}
		catch(IOException e)
		{
			throw e;
		}
		
	}
	
	/**
	*	Returns the Cell at a given Position.
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
	 * Returns the maximum value of the Board.
	 */
	public int getMaxValue()
	{
		return maxValue;
	}
	
	/**
	 * Returns the minimum value of the Board.
	 */
	public int getMinValue()
	{
		return minValue;
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
	 * Given a value, checks whether it is larger than the current maximum or smaller
	 * than the current minimum and updates them accordingly.
	 */
	public void updateMaxMinValue(int val)
	{
		if(val > maxValue)
		{
			maxValue = val;
		}
		
		if(val < minValue)
		{
			minValue = val;
		}
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
	*	Returns if there are possible moves in the Board under the given rules.
	*/
	public boolean canMerge(GameRules rules) {
		int i, j;
		Position currentCell, rightNeighbor, downNeighbor;
		boolean stuck;
		
		i = 0;
		j = 0;
		stuck = true;
		currentCell = new Position();
		rightNeighbor = new Position();
		downNeighbor = new Position();
		
		if(_emptyCells == 0)
		{
			while(stuck && i < _boardSize)
			{
				j = 0;
				currentCell.setRow(i);
				rightNeighbor.setRow(i);
				downNeighbor.setRow(i+1);
				
				while(stuck && j < _boardSize)
				{
					currentCell.setCol(j);
					rightNeighbor.setCol(j+1);
					downNeighbor.setCol(j);
					
					//Checks whether each cell can merge with its right or down neighbor
					stuck = !(  (j+1 < _boardSize && rules.canMergeNeighbor(getCell(currentCell), getCell(rightNeighbor)) )
							||  (i+1 < _boardSize && rules.canMergeNeighbor(getCell(currentCell), getCell(downNeighbor)) ) );
					
					j++;
				}
				
				i++;
			}
		}
		else
		{
			stuck = false;
		}
		
		return !stuck;
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
