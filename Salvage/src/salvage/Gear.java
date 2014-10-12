package salvage;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class Gear extends Entity{
		private Boolean isHeld;
		private Vector velocity;
		
		public Gear(final float x, final float y, final float xv, final float yv){
			super(x,y);
			isHeld = false;
			velocity = new Vector(xv, yv);
			addImageWithBoundingBox(ResourceManager
					.getImage(SalvageGame.GEAR_GEARIMG_RSC));
			
		}
		
		public Boolean isHeld(){
			return isHeld;
		}
		
		public void pickUp(){
			isHeld = true;
		}
		
		public Vector getVelocity(){
			return velocity;
		}
		
		public void setVelocity(Vector newVelocity){
			velocity = newVelocity;
		}
		
		public void update(final int delta) {
			translate(velocity.scale(delta));
		}
		
	
}
