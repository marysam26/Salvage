package salvage;

import jig.Entity;
import jig.ResourceManager;

public class Planet extends Entity {
	private float mass;
	private float gravityDistance;
	
	public Planet(final float x, final float y, int planet, float mass,
			float gravityDistance){
		super(x,y);
		addImageWithBoundingBox(ResourceManager
				.getImage(SalvageGame.PLANET1_PLANETIMG_RSC));
		this.mass = mass;
		this.gravityDistance = gravityDistance;
	}
	
	public float getMass(){
		return mass;
	}
	
	public float getDistance(){
		return gravityDistance;
	}

}
