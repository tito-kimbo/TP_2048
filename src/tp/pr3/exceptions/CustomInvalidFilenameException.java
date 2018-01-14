package tp.pr3.exceptions;

public class CustomInvalidFilenameException extends RuntimeException
{
	//Generated serialVersionUID
	private static final long serialVersionUID = -1910408327810463809L;
	
	public CustomInvalidFilenameException()
	{
		super("Invalid filename!");
	}
	
	public CustomInvalidFilenameException(String text)
	{
		super(text);
	}
}
