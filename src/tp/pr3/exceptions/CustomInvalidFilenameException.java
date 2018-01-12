package tp.pr3.exceptions;

public class CustomInvalidFilenameException extends RuntimeException
{
	//Generated serialVersionUID
	private static final long serialVersionUID = -1910408327810463809L;
	private static String message = null;
	
	public CustomInvalidFilenameException()
	{
		message = "Invalid filename!";
	}
	
	public CustomInvalidFilenameException(String text)
	{
		message = text;
	}
	
	public String toString()
	{
		return message;
	}
}
