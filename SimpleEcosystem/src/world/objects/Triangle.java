package world.objects;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;
import world.Map;

public class Triangle extends AObject
{	
	public Triangle(Map map, PVector center, Float ang)
	{
		super(map, center, ang);
	}

	@Override
	public void draw(PGraphics g)
	{
		g.noStroke();
		g.fill(color);
		
		g.beginShape();
		float thisAng = ang;
		for(int i = 0; i < 3; i++, thisAng += PApplet.TWO_PI / 3)
		{
			float x = center.x + PApplet.cos(thisAng) * r;
			float y = center.y + PApplet.sin(thisAng) * r;
			g.vertex(x, y);
		}
		g.endShape(PApplet.CLOSE);
	}

	@Override
	protected void eatUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void prepareMoveUpdate() {
		// TODO Auto-generated method stub
		
	}
}
