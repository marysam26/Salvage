package salvage;

import jig.Entity;
import jig.ResourceManager;

public class Moon extends Entity {
	private float radius;
	
	public Moon (final float x, final float y, float radius){
		super(x,y);
		radius = radius;
		addImageWithBoundingBox(ResourceManager
				.getImage(SalvageGame.MOON_MOONIMG_RSC).getScaledCopy(0.4f));
		
	}
}
