package simpleecosystem;

import processing.core.PApplet;


public class SimpleEcosystem extends PApplet {

	private static final long serialVersionUID = 5665757813984678131L;

	public void setup() {
		size(displayWidth, displayHeight);
		
		try {
			Configurations.initConfigurations();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void draw() {
	}
	
	public boolean sketchFullScreen()
	{
		return true;
	}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { simpleecosystem.SimpleEcosystem.class.getName() });
	}
}