package simpleecosystem;
import simpleecosystem.Configurations;
import simpleecosystem.SimpleEcosystem;

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
	
}
