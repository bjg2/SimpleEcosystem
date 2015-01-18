package world;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import processing.core.PGraphics;
import processing.core.PVector;
import simpleecosystem.Configurations;
import simpleecosystem.Utils;
import world.lakes.ALake;
import world.objects.AObject;

public class Map
{
	protected Configurations mapConfigurations;
	protected Configurations configurations;
	
	int groundColor;

	ArrayList<ALake> lakes = new ArrayList<ALake>();
	ArrayList<SpawnPoint> spawnPoints = new ArrayList<SpawnPoint>();	
	ArrayList<AObject> objects = new ArrayList<AObject>();
	
	public Map() throws Exception
	{		
		mapConfigurations = Configurations.GetConfigurationsByName("Map", this.getClass().getSimpleName());
		configurations = Configurations.Root;
		
		groundColor = Utils.ReadColorConfigurations(configurations.<Configurations>Get("GroundColor"));
		
		ArrayList<Configurations> allLakeConfigurations = configurations.<Configurations>GetAll("Lake");
		for(Configurations lakeConfigurations : allLakeConfigurations)
		{
			if(lakeConfigurations.<Boolean>Get("Enabled"))
			{
				// refrection part
				String name = lakeConfigurations.<String>Get("Name");
				Class<?> clazz = Class.forName("world.lakes." + name);
				Constructor<?> ctor = clazz.getConstructor(Configurations.class);
				
				ALake newLake = (ALake) ctor.newInstance(new Object[] { lakeConfigurations });
				lakes.add(newLake);
			}
		}
		
		ArrayList<Configurations> allSpawnPointConfigurations = configurations.<Configurations>GetAll("SpawnPoint");
		for(Configurations spawnPointConfigurations : allSpawnPointConfigurations)
		{
			if(spawnPointConfigurations.<Boolean>Get("Enabled"))
			{
				SpawnPoint newSpawnPoint = new SpawnPoint(spawnPointConfigurations, this);
				spawnPoints.add(newSpawnPoint);
				objects.addAll(newSpawnPoint.spawnInitialObjects());
			}
		}
	}
	
	// update calculation
	public void update()
	{	
		for(AObject obj : objects)
		{
			obj.update();
		}
		
		for(AObject obj : objects)
		{
			obj.moveUpdate();
		}
		
		for(int i = 0; i < objects.size(); i++)
		{
			if(!objects.get(i).isAlive())
			{
				objects.remove(i);
				i--;
			}
		}

		for(ALake lake : lakes)
		{
			lake.update();
		}
	}
	
	public void draw(PGraphics g)
	{
		g.background(groundColor);
		
		drawLakes(g);
		drawSpawnPoints(g);
		drawObjects(g);
	}
	
	public void drawLakes(PGraphics g)
	{
		for(ALake lake : lakes)
		{
			lake.draw(g);
		}		
	}
	
	protected void drawObjects(PGraphics g)
	{
		for(AObject obj : objects)
		{
			obj.draw(g);
		}
	}
	
	public void drawSpawnPoints(PGraphics g)
	{
		for(SpawnPoint spawnPoint : spawnPoints)
		{
			spawnPoint.draw(g);
		}
	}
}
