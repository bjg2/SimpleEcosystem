package world;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
import simpleecosystem.Configurations;
import simpleecosystem.SimpleEcosystem;
import simpleecosystem.Utils;
import world.objects.AObject;

public class SpawnPoint
{
	private Configurations spawnPointConfigurations;
	private Configurations configurations;
	
	Map map;
	PVector center;
	float r;
	String objectName;
	int color;
	int initialSpawn;
	
	public SpawnPoint(Configurations spawnPointConfigurations, Map map)
	{
		this.spawnPointConfigurations = spawnPointConfigurations;
		configurations = Configurations.Root;
		this.map = map;
		
		objectName = spawnPointConfigurations.<String>Get("ObjectName");
		center = Utils.ReadPointConfigurations(spawnPointConfigurations.<Configurations>Get("Center"));
		r = spawnPointConfigurations.<Float>Get("R");
		color = Utils.ReadColorConfigurations( spawnPointConfigurations.<Configurations>Get("Color") );
		initialSpawn = (int) (float) spawnPointConfigurations.<Float>Get("InitialSpawn");
	}
	
	public void draw(PGraphics g)
	{
		g.noStroke();
		g.fill(color);
		g.ellipse(center.x, center.y, 2 * r, 2 * r);
	}
	
	public PVector randomPointInside()
	{
		float ang = SimpleEcosystem.Instance.random(PApplet.TWO_PI);
		float dist = SimpleEcosystem.Instance.random(r);
		
		return PVector.add(center, 
				new PVector(PApplet.cos(ang) * dist, PApplet.sin(ang) * dist));		
	}
	
	private AObject spawnObject() throws Exception
	{
		PVector objectCenter = randomPointInside();
		float objectAng = SimpleEcosystem.Instance.random(PApplet.TWO_PI);
		
		// refrection part
		Class<?> clazz = Class.forName("world.objects." + objectName);
		Constructor<?> ctor = clazz.getConstructor(Map.class, PVector.class, Float.class);
		return (AObject) ctor.newInstance(new Object[] { map, objectCenter, objectAng });
	}
	
	public ArrayList<AObject> spawnInitialObjects() throws Exception
	{
		ArrayList<AObject> spawnedObjects = new ArrayList<AObject>();  
		for(int i = 0; i < initialSpawn; i++)
		{
			spawnedObjects.add(spawnObject());
		}
		return spawnedObjects;
	}
}
