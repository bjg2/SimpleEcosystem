package world;

import processing.core.PGraphics;
import processing.core.PVector;
import simpleecosystem.Configurations;
import simpleecosystem.Utils;

public class CircleLakeMap extends AMap
{
	PVector center;
	
	float r;
	float minR;
	float maxR;
	float updateR;
	float drinkUpdateR;
	
	int groundColor;
	int lakeColor;
	
	public CircleLakeMap(PVector center)
	{
		this.center = center;
		
		r = mapConfigurations.<Float>Get("InitialR");
		minR = mapConfigurations.<Float>Get("MinR");
		maxR = mapConfigurations.<Float>Get("MaxR");
		updateR = mapConfigurations.<Float>Get("UpdateR");
		drinkUpdateR = mapConfigurations.<Float>Get("DrinkUpdateR");

		groundColor = Utils.ReadColorConfigurations(mapConfigurations.<Configurations>Get("GroundColor"));
		lakeColor = Utils.ReadColorConfigurations(mapConfigurations.<Configurations>Get("LakeColor"));
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
		g.background(groundColor);
		g.noStroke();
		g.fill(lakeColor);
		g.ellipse(center.x, center.y, 2 * r, 2 * r);
	}

}
