package world;

import processing.core.PGraphics;
import simpleecosystem.Configurations;

public abstract class AMap
{
	protected Configurations mapConfigurations;
	protected Configurations configurations;
	
	public AMap()
	{		
		mapConfigurations = Configurations.GetMapConfigurations(this.getClass().getSimpleName());
		if(mapConfigurations == null)
		{
			System.out.println("null je " + this.getClass().getSimpleName());
		}
		configurations = Configurations.Root;
	}
	
	// update calculation
	public abstract void update();
	// draw the map
	public abstract void draw(PGraphics g);
}
