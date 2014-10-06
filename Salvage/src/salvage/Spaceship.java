package salvage;


import jig.Entity;
import jig.ResourceManager;

public class Spaceship extends Entity{

	
public Spaceship(final float x, final float y){
	super(x,y);
	addImageWithBoundingBox(ResourceManager
			.getImage(SalvageGame.SPACESHIP_SHIPIMG_RSC));
	
}

}
