package salvage;



import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class Astronaut extends Entity {
	private Shield shield;
	private Boolean hasGear;
	private Vector velocity;
	
	private final float MAX_VELOCITY = 20;

	
	public Astronaut(final float x, final float y, final float vx, final float vy){
		super(x,y);
		shield = new Shield();
		hasGear = false;
		velocity = new Vector(vx, vy);
		addImageWithBoundingBox(ResourceManager
				.getImage(SalvageGame.ASTRONAUTH_ASTRIMG_RSC));
	
	}
	public Boolean hasGear(){
		return hasGear;
	}
	
	public void pickUp(){
		hasGear = true;
	}
	
	public void drop(){
		hasGear = false;
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
