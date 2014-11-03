package salvage;





import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class Astronaut extends Entity {
	private Shield shield;
	private Boolean hasGear;
	private Vector velocity;
	private double theta;
	
	private final float MAX_VELOCITY = 0.25f;

	
	public Astronaut(final float x, final float y, final float vx, final float vy,
			Shield shield){
		super(x,y);
		this.shield = shield;
		hasGear = false;
		velocity = new Vector(vx, vy);
		addImageWithBoundingBox(ResourceManager
				.getImage(SalvageGame.ASTRONAUT_ASTROIMG_RSC));
		theta = 0;
	}
	
	public void setTheta(double theta){
		this.theta = theta;
	}
	
	public double getTheta(){
		return theta;
	}
	
	public Boolean hasGear(){
		return hasGear;
	}
	
	public Shield getShield(){
		return shield;
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
/*		if(newVelocity.getX() < velocity.getX()){
			removeImage(ResourceManager.getImage(SalvageGame.ASTRONAUT_ASTROIMG_RSC));
			addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTRONAUTL_ASTROLIMG_RSC));
		}
		else if(newVelocity.getX() > velocity.getX()){
			removeImage(ResourceManager.getImage(SalvageGame.ASTRONAUTL_ASTROLIMG_RSC));
			addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTRONAUT_ASTROIMG_RSC));
		}*/
		if(Math.abs(newVelocity.getX()) < MAX_VELOCITY && Math.abs(newVelocity.getY()) < MAX_VELOCITY)
			velocity = newVelocity;
		
	}
	
	public void update(final int delta) {
		translate(velocity.scale(delta));
	}
	
	public void updateTheta(double radius){
		 
		  this.setPosition((float)(this.getX() + radius*Math.cos(theta)),(float)(this.getY() + radius*Math.sin(theta)));
	}
}
