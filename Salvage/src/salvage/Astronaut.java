package salvage;





import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class Astronaut extends Entity {
	private Shield shield;
	private Boolean hasGear;
	private Vector velocity;
	private double theta;
	private float orbit;
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
	public void setOrbit(float orbit){
		this.orbit = orbit + 32;
	}
	
	public float getOrbit(){
		return orbit;
	}
	public void setTheta(double theta){
		this.theta = theta+300;
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
		removeImage(ResourceManager.getImage(SalvageGame.ASTROG_BANNER_RSC));
		removeImage(ResourceManager.getImage(SalvageGame.ASTROGL_BANNER_RSC));
		if(this.getVelocity().getX() > this.getVelocity().getY()){
			this.addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTRONAUTL_ASTROLIMG_RSC));
		}
		else{
			this.addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTRONAUT_ASTROIMG_RSC));
		}
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
	
	public void updateTheta(float xm, float ym, boolean left){
/*		 double x = this.getX()+ Math.cos(theta);// - orbit/2;
		 double y = this.getY() + Math.sin(theta);// - orbit/2;
*/
	

		double x = xm + Math.sin(Math.toRadians((double)theta)) * orbit;
		double y = ym + Math.cos(Math.toRadians((double)theta)) * orbit;
		// draw rectangle on [x, y] coordinates
		setPosition((float)(x),(float)(y));
		if(left)
			theta++;
		else
			theta--;
		// f++;
	}
}
