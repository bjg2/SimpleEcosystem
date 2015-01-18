package simpleecosystem;

import processing.core.PApplet;
import world.Map;
import world.lakes.CircleLake;

public class SimpleEcosystem extends PApplet
{
	private static final long serialVersionUID = 5665757813984678131L;
	
	public static SimpleEcosystem Instance;
	
	Map map;

	public void setup()
	{
		Instance = this;
				
		size(displayWidth, displayHeight);
		
		try
		{
			Configurations.Init();
			map = new Map();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void update()
	{
		map.update();
	}

	public void draw()
	{
		update();
		
		map.draw(g);
	}
	
	public boolean sketchFullScreen()
	{
		return true;
	}
	
	public static void main(String _args[])
	{
		PApplet.main(new String[] { simpleecosystem.SimpleEcosystem.class.getName() });
	}
}