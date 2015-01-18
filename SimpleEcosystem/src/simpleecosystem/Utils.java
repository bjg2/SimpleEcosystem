package simpleecosystem;
import processing.core.PVector;

public class Utils
{
	public static int ReadColorConfigurations(Configurations configs)
	{
		Float red = configs.<Float>Get("Red");
		Float green = configs.<Float>Get("Green");
		Float blue = configs.<Float>Get("Blue");
		Float alpha = configs.<Float>Get("Alpha");
		
		if(alpha == null)
		{
			return SimpleEcosystem.Instance.color(red, green, blue);
		}

		return SimpleEcosystem.Instance.color(red, green, blue, alpha);
	}
	
	public static PVector ReadPointConfigurations(Configurations configs)
	{
		Object xObj = configs.<Object>Get("X");
		Object yObj = configs.<Object>Get("Y");
		
		float x;
		if(xObj instanceof Float)
		{
			x = (Float) xObj;
		}
		else if(xObj instanceof String && ((String)xObj).toLowerCase().equals("center"))
		{
			x = SimpleEcosystem.Instance.width / 2.0f;
		}
		else
		{
			return null;
		}
		
		float y;
		if(xObj instanceof Float)
		{
			y = (Float) yObj;
		}
		else if(xObj instanceof String && ((String)xObj).toLowerCase().equals("center"))
		{
			y = SimpleEcosystem.Instance.height / 2.0f;
		}
		else
		{
			return null;
		}

		if(x < 0)
		{
			x += SimpleEcosystem.Instance.width;
		}

		if(y < 0)
		{
			y += SimpleEcosystem.Instance.height;
		}
		
		return new PVector(x, y);
	}
	
}
