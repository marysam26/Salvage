package salvage;

import org.newdawn.slick.Animation;


import jig.Entity;
import jig.ResourceManager;

public class Shield extends Entity {
	private int health;
	private Animation recharge;
	private int countdown;

	public Shield(final float x, final float y){
		super(x,y);
		health = 10;
		addImageWithBoundingBox(ResourceManager
				.getImage("salvage/resources/health10.png"));
		addImageWithBoundingBox(ResourceManager
				.getImage(SalvageGame.SHEILD_SHEILDIMG_RSC));
		countdown = 0;
	}
	
	public void shieldHit(int damage, int delta){
		if (countdown > 0) {
			countdown -= delta;
		}
		if (countdown <= 0) {

			if (recharge != null && recharge.isStopped()){
				removeAnimation(recharge);
				recharge = null;
			}
			else
				removeImage(ResourceManager.getImage("salvage/resources/health"+health+".png"));
			health -= damage;
			addImageWithBoundingBox(ResourceManager.getImage("salvage/resources/health"+health+".png"));
			countdown = 500;
		}

		return;
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
