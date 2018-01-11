package tp.pr3.exceptions;

public class CustomInvalidFilenameException extends RuntimeException
{
	private static String message = null;
	
	public CustomInvalidFilenameException()
	{
		message = "Invalid filename!";
	}
	
	public CustomInvalidFilenameException(String text)
	{
		message = text;
	}
	
	public String getMessage()
	{
		return message;
	}
}
