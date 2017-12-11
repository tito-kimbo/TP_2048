package tp.pr2.util;

public class MyMathsUtil 
{
	private static double phi = 1+Math.sqrt(5); //This is for better resource usage
	//Avoids unnecessary operations
	
	public static int nextFib(int previous)
	{
		return (int)Math.round(phi*previous);
	}
	
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
