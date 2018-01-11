package tp.pr3.exceptions;

import java.util.EmptyStackException;

public class CustomEmptyStackException extends EmptyStackException
{
	private static String message = null;
	
	public CustomEmptyStackException()
	{
		super();
	}
	
	public CustomEmptyStackException(String text)
	{
		super();
		message = text;
	}
	
	public String getMessage()
	{
		return message;
	}
}
