package world.objects;

import processing.core.PGraphics;
import processing.core.PVector;
import simpleecosystem.Configurations;
import simpleecosystem.Utils;
import world.Map;

public abstract class AObject
{
	protected Configurations objectConfigurations;
	protected Configurations configurations;
	protected Map map;

	// specie parameters
	float growthUpdate;
	float growthWhenEating;
	float speed;
	float dieR;
	float divideR;
	int color;
	
	// object parameters
	PVector center;
	float ang;
	float r;
	
	PVector moveVector;
	
	public AObject(Map map, PVector center, float ang)
	{		
		objectConfigurations = Configurations.GetConfigurationsByName("Object", this.getClass().getSimpleName());
		configurations = Configurations.Root;
		this.map = map;		
		
		// specie parameters read
		growthUpdate = objectConfigurations.<Float>Get("GrowthUpdate");
		growthWhenEating = objectConfigurations.<Float>Get("GrowthWhenEating");
		speed = objectConfigurations.<Float>Get("Speed");
		dieR = objectConfigurations.<Float>Get("DieR");
		divideR = objectConfigurations.<Float>Get("DivideR");
		color = Utils.ReadColorConfigurations(objectConfigurations.<Configurations>Get("Color"));

		this.center = center;
		this.ang = ang;
		r = objectConfigurations.<Float>Get("StartR");
	}
	
	protected void shrinkingUpdate()
	{
		r += growthUpdate;
	}
	
	protected abstract void eatUpdate();

	protected abstract void prepareMoveUpdate();
	
	// update calculation
	public void update()
	{
		shrinkingUpdate();
		eatUpdate();		
		prepareMoveUpdate();
	}
	
	public void moveUpdate()
	{
		if(moveVector != null)
		{
			center.add(moveVector);			
		}
	}
	// draw the map
	public abstract void draw(PGraphics g);
	
	public boolean isAlive()
	{
		return r > dieR;
	}
}
