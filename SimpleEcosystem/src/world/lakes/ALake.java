package world.lakes;

import processing.core.PGraphics;
import processing.core.PVector;
import simpleecosystem.Configurations;
import simpleecosystem.Utils;

public abstract class ALake
{
	protected Configurations lakeConfigurations;
	protected Configurations configurations;	
	
	PVector center;
	int lakeColor;
	
	public ALake(Configurations lakeConfigurations)
	{
		this.lakeConfigurations = lakeConfigurations;
		configurations = Configurations.Root;
		
		center = Utils.ReadPointConfigurations(lakeConfigurations.<Configurations>Get("Center"));
		lakeColor = Utils.ReadColorConfigurations(lakeConfigurations.<Configurations>Get("LakeColor"));
	}

	public abstract void update();
	public abstract void draw(PGraphics g);
}
