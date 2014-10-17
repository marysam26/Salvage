package salvage;

import jig.Entity;
import jig.ResourceManager;

public class Shield extends Entity {
	private int health;
	
	public Shield(final float x, final float y){
		super(x,y);
		health = 10;
		addImageWithBoundingBox(ResourceManager
				.getImage("salvage/resources/health10.png"));
		addImageWithBoundingBox(ResourceManager
				.getImage(SalvageGame.SHEILD_SHEILDIMG_RSC));
	
	}
	
	public void shieldHit(int damage){
		removeImage(ResourceManager.getImage("salvage/resources/health"+health+".png"));
		health -= damage;
		if (health == -1){
			health = 10;
		}
		addImageWithBoundingBox(ResourceManager.getImage("salvage/resources/health"+health+".png"));
		}
	
	public int getShield(){
		return health;
	}
 
}
