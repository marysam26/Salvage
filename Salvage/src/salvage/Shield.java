package salvage;

import org.newdawn.slick.Animation;

import jig.Entity;
import jig.ResourceManager;

public class Shield extends Entity {
	private int health;
	private Animation recharge;
	
	public Shield(final float x, final float y){
		super(x,y);
		health = 10;
		addImageWithBoundingBox(ResourceManager
				.getImage("salvage/resources/health10.png"));
		addImageWithBoundingBox(ResourceManager
				.getImage(SalvageGame.SHEILD_SHEILDIMG_RSC));
	
	}
	
	public void shieldHit(int damage){
		if (recharge != null && recharge.isStopped()){
			removeAnimation(recharge);
			recharge = null;
		}
		else
			removeImage(ResourceManager.getImage("salvage/resources/health"+health+".png"));
		health -= damage;
		if (health == -1){
			health = 10;
		}
		addImageWithBoundingBox(ResourceManager.getImage("salvage/resources/health"+health+".png"));
	}
	
	public int getShieldHealth(){
		return health;
	}
	public Shield getShield(){
		return this;
	}
	
	public void shieldCharge(){
		removeImage(ResourceManager.getImage("salvage/resources/health"+health+".png"));
		health = 10;
		recharge = new Animation(ResourceManager.getSpriteSheet(
				SalvageGame.SHIELDAN_SHIELDANIMG_RSC, 200, 20), 0, 0, 0, 9, false, 100,
				true);
		addAnimation(recharge);
		recharge.setLooping(false);	
	}
	

 
}
