package tp.pr3.logic;

import tp.pr3.logic.multigames.*;

/**
 * Enum indicating the available types of games.
 */
public enum GameType
{
	ORIG("original", new Rules2048()), FIB("fib", new RulesFib()), INV("inverse", new RulesInverse());

	private final String text;
	private final GameRules rules;
	
	GameType(String text, GameRules rules) {
		this.text = text;
		this.rules = rules;
	}

	/**
	 * Returns a string representation of the type.
	 */
	public String toString()
	{
		return this.text;
	}

	/**
	 * Returns the type corresponding to the given string.
	 */
	public static GameType fromString(String text)
	{
		GameType result = null;
		for(GameType t : GameType.values())
			if(t.text.equalsIgnoreCase(text))
				result = t;
		return result;
	}

	/**
	 * Returns the rules associated with this game.
	 */
	public GameRules toRules() 
	{
		return this.rules;
	}
	
};
