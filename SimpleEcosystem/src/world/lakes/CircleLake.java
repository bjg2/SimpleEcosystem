package world.lakes;

import processing.core.PGraphics;
import processing.core.PVector;
import simpleecosystem.Configurations;
import simpleecosystem.Utils;

public class CircleLake extends ALake
{	
	float r;
	float minR;
	float maxR;
	float updateR;
	float drinkUpdateR;
	
	public CircleLake(Configurations lakeConfigurations)
	{
		super(lakeConfigurations);
		
		r = lakeConfigurations.<Float>Get("InitialR");
		minR = lakeConfigurations.<Float>Get("MinR");
		maxR = lakeConfigurations.<Float>Get("MaxR");
		updateR = lakeConfigurations.<Float>Get("UpdateR");
		drinkUpdateR = lakeConfigurations.<Float>Get("DrinkUpdateR");
	}
	
	@Override
	public void update()
	{
		r += updateR;
		if(r < minR)
		{
			r = minR;
		}
		if(r > maxR)
		{
			r = maxR;
		}
	}

	@Override
	public void draw(PGraphics g)
	{
		g.noStroke();
		g.fill(lakeColor);
		g.ellipse(center.x, center.y, 2 * r, 2 * r);
	}

}
