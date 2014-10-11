package salvage;

import jig.Entity;
import jig.ResourceManager;

public class Planet extends Entity {
	private float gravity;
	
	public Planet(final float x, final float y, int planet, float gravity){
		super(x,y);
		addImageWithBoundingBox(ResourceManager
				.getImage(SalvageGame.PLANET1_PLANETIMG_RSC));
		gravity = gravity;
	}

}
