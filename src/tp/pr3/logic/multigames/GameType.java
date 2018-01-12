package tp.pr3.logic.multigames;

public enum GameType
{
	ORIG("2048, original version", "original", new Rules2048()),
	FIB("2048, Fibonacci version", "fib", new RulesFib()),
	INV("2048, inverse version", "inverse", new RulesInverse());
	
	private String userFriendlyName;
	private String parameterName;
	private GameRules correspondingRules;
	
	private GameType(String friendly, String param, GameRules rules)
	{
		userFriendlyName = friendly;
		parameterName = param;
		correspondingRules = rules;
	}
	
	/**
	 * Returns the type corresponding to the given string.
	 */
	public static GameType fromString(String text)
	{
		GameType result = null;
		for(GameType t : GameType.values())
			if(t.parameterName.equalsIgnoreCase(text))
				result = t;
		return result;
	}

	// used in PlayCommand to build help message, and in parse method exception msg
	public static String externaliseAll () 
	{
		String s = "";
		
		for (GameType type : GameType.values())
		{
			s = s + " " + type.parameterName + ",";	
		}
		
		return s.substring(1, s.length()-1);
	}
	// used in Game when constructing object and when executing play command
	public GameRules getRules() 
	{
		return correspondingRules;
	}
	
	public static GameType setType(GameRules rules)
	{
		GameType t;
		
		if(rules instanceof RulesFib)
		{
			t = FIB;
		}
		else if (rules instanceof RulesInverse)
		{
			t = INV;
		}
		else
		{
			t = ORIG;
		}
		
		return t;
	}
	
	public static GameType setType(String rules)
	{
		GameType t;
		
		if(rules.equals("fib"))
		{
			t = FIB;
		}
		else if (rules.equals("inverse"))
		{
			t = INV;
		}
		else
		{
			t = ORIG;
		}
		
		return t;
	}
	
	// used in Game in store method
	public String externalise () 
	{
		return parameterName;
	}
	
	// used PlayCommand and LoadCommand, in parse methods
	// in ack message and success message, respectively
	public String toString() 
	{
		return userFriendlyName;
	}
}
