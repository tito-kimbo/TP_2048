package tp.pr3.logic;

import tp.pr3.logic.GameState;
import tp.pr3.exceptions.CustomEmptyStackException;

/**
 * Stores a list of GameStates with a finite capacity.
 */
public class GameStateStack
{

	private static final int CAPACITY = 20;
	
	private GameState[] stack;
	private int size;
	private int lastIndex;
	//lastIndex stores the last occupied index. Each push increments it, returning
	//to 0 after CAPACITY-1. This eliminates the necessity to shift the
	//whole stack every time a push is called at full size.
	
	/**
	 * Default constructor. Initializes the stack.
	 */
	public GameStateStack()
	{
		stack = new GameState[CAPACITY];
		size = 0;
		lastIndex = -1;
	}

	/**
	 * Returns the last saved state, unless the stack is empty.
	 */
	public GameState pop() throws CustomEmptyStackException
	{
		GameState last = null;
		if(size > 0)
		{
			last = stack[lastIndex];
			lastIndex = (CAPACITY+lastIndex-1)%CAPACITY;
			size--;
		}
		else
		{
			throw new CustomEmptyStackException();
		}
		return last;
	}

	/**
	 * Stores the given state in the stack.
	 */
       	public void push(GameState state)
	{
		if(size < CAPACITY) size++;
		lastIndex = (lastIndex+1)%CAPACITY;
		stack[lastIndex] = state;
	}

	/**
	 * Checks if the stack is empty.
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}

}
