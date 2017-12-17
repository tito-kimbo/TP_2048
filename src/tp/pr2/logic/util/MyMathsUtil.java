package tp.pr2.logic.util;

/**
 * Contains useful mathematical methods and functions
 */
public class MyMathsUtil 
{
	private static double phi = (1+Math.sqrt(5))/2; //This is for better resource usage
	//Avoids unnecessary operations
	
	/**
	 * Given an integer of the Fibonacci sequence, returns the next one. Note that if given
	 * 1, it will return 2.
	 */
	public static int nextFib(int previous)
	{
		return (int)Math.round(phi*previous);
	}
	
	/**
	 * Returns the maximum of 2 given values.
	 */
	public static int max(int a, int b)
	{
		int aux;
		if(a > b)
		{
			aux = a;
		}
		else
		{
			aux = b;
		}
		return aux;
	}
}
