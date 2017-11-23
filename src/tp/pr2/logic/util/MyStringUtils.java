package tp.pr2.logic.util;

/**
*	Contains useful methods for manipulating strings.
*/
public class MyStringUtils 
{
	
	/**
	*	Returns the given String repeated as many times as indicated.
	*/
	public static String repeat(String elmnt, int length) 
	{
		String result = "";
		
		for (int i = 0; i < length; i++) 
		{
			result += elmnt;
		}
		
		return result;
	}
	
	/**
	*	Returns the given text centered and with the given length.
	*/
	public static String centre(String text, int len)
	{
		String out = String.format(" %"+len+"s %s %"+len+"s", "",text,"");
		float mid = (out.length()/2);
		float start = mid - (len/2);
		float end = start + len;
		
		return out.substring((int)start, (int)end);
	}
}
