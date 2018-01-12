package tp.pr3.exceptions;

import java.util.EmptyStackException;

public class CustomEmptyStackException extends EmptyStackException
{
	//Generated serialVersionUID
	private static final long serialVersionUID = 1314089754841809739L;
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
	
	public String toString()
	{
		return message;
	}
}
