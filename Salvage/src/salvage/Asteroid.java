package salvage;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;


public class Asteroid extends Entity {

	private Vector velocity;
	private int countdown;

	public Asteroid(final float x, final float y, final float vx, final float vy) {
		super(x, y);
		addImageWithBoundingBox(ResourceManager
				.getImage(SalvageGame.ASTEROID_ASTEROIDIMG_RSC));
		velocity = new Vector(vx, vy);
		countdown = 0;
	}

	public void setVelocity(final Vector v) {
		velocity = v;
	}

	public Vector getVelocity() {
		return velocity;
	}

	public void bounce(float surfaceTangent) {
		countdown = 500;
		velocity = velocity.bounce(surfaceTangent);
	}

	public void update(final int delta) {
		translate(velocity.scale(delta));
		
		}
}

