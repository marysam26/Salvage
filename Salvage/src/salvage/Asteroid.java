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

	/**
	 * Bounce the ball off a surface. 
	 * @param surfaceTangent
	 */
	public void bounce(float surfaceTangent) {
		//ResourceManager.getSound(BounceGame.PING_EXPLOSIONSND_RSC).play();
		//removeImage(ResourceManager.getImage(BounceGame.BALL_BALLIMG_RSC));
		//addImageWithBoundingBox(ResourceManager
			//	.getImage(BounceGame.BALL_BROKENIMG_RSC));
		countdown = 500;
		velocity = velocity.bounce(surfaceTangent);
	}

	/**
	 * Update the Ball based on how much time has passed...
	 * 
	 * @param delta
	 *            the number of milliseconds since the last update
	 */
	public void update(final int delta) {
		translate(velocity.scale(delta));
		/*if (countdown > 0) {
			countdown -= delta;
			if (countdown <= 0) {
				addImageWithBoundingBox(ResourceManager
						.getImage(BounceGame.BALL_BALLIMG_RSC));
				removeImage(ResourceManager
						.getImage(BounceGame.BALL_BROKENIMG_RSC));
			}*/
		}
}

