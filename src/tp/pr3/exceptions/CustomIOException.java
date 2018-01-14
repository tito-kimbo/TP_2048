package tp.pr3.exceptions;

public class CustomIOException extends RuntimeException
{
	//Generated serialVersionUID
	private static final long serialVersionUID = 8733565177132738351L;
	private String message;
	
	public CustomIOException(){}
	
	public CustomIOException(String m)
	{
		message = m;
	}
	
	public String toString()
	{
		return message;
	}
	
}
