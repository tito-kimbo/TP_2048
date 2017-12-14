package tp.pr2.logic;

import tp.pr2.logic.multigames.*;

/**
 * Enum indicating the available types of games.
 */
public enum GameType
{
	ORIG, FIB, INV;

	/**
	 * Returns a string representation of the type.
	 */
	public String toString()
	{
		String s = "";
		
		switch(this)
		{
			case ORIG:
			{
				s = "original";
			} break;
			
			case FIB:
			{
				s = "fib";
			} break;

			case INV:
			{
				s = "inverse";
			} break;
		}
		
		return s;
	}

	/**
	 * Returns the rules associated with this game.
	 */
	public GameRules toRules() 
	{
		GameRules rules = null;
		
		switch(this)
		{
			case ORIG:
			{
				rules = new Rules2048();
			} break;
			
			case FIB:
			{
				rules = new RulesFib();
			} break;

			case INV:
			{
				rules = new RulesInverse();
			} break;
		}
		
		return rules;
	}
	
};
