package tp.pr3.exceptions;

public class FileNotFoundException extends RuntimeException
{
	//Generated serialVersionUID
	private static final long serialVersionUID = 1L;

	public FileNotFoundException()
	{
		super("File not found!");
	}
}
