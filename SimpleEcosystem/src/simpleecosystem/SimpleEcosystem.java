package simpleecosystem;

import processing.core.PApplet;
import processing.core.PVector;
import world.AMap;
import world.CircleLakeMap;


public class SimpleEcosystem extends PApplet
{
	private static final long serialVersionUID = 5665757813984678131L;
	
	public static SimpleEcosystem Instance;
	
	AMap map;

	public void setup()
	{
		Instance = this;
		
		size(displayWidth, displayHeight);
		
		try
		{
			Configurations.Init();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		map = new CircleLakeMap(new PVector(width / 2, height / 2));
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