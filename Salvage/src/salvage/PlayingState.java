package salvage;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import jig.ResourceManager;
import jig.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class PlayingState extends BasicGameState {
	private Timer timer;
	private Gear heldGear;
	private int duration;
	private int numGears;
	private int livesLeft;
	private boolean isFloating;
	private boolean timesUp;
	private Moon moon;
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		timer = new Timer();
		isFloating = true;
		timesUp = false;
	}
	public void setGears(int gears){
		numGears = gears;
	}
	
	public void setLives(int lives){
		livesLeft = lives;
	}
	public void setTimer( final int countDown){ 
        duration = countDown;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
            	duration--;
                if (duration< 0){
                    timer.cancel();
                    timesUp  = true;	
                }
            }
        }, 0, 1000);
	}
	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		SalvageGame sg = (SalvageGame)game;
		g.drawImage(ResourceManager.getImage(SalvageGame.SPACE_SPACEIMG_RSC), 0, 0);
		//g.drawString("Playing State", 0, 10);
		g.drawString("Gears: "+numGears, 10, 30);
		g.drawString("Lives Remaining: "+livesLeft, 110, 30);
		g.drawString("Time Left: "+duration/60 +":" +duration%60  , 310, 30);
		g.drawString("Shield", 500, 30);
		sg.ship.render(g);
		sg.astronaut.render(g);
		sg.planet.render(g);
		sg.astronaut.getShield().render(g);
		for( Moon m : sg.moon){
			m.render(g);
		}
		for(Gear gr : sg.gear){
			gr.render(g);
		}
		for(Asteroid ast : sg.asteroids){
			ast.render(g);
		}
	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
		 {
		Input input = container.getInput();
		SalvageGame sg = (SalvageGame)game;
		if(isFloating){		
			if(sg.astronaut.hasGear()){
				if(input.isKeyDown(Input.KEY_LEFT) && input.isKeyDown(Input.KEY_RIGHT)){
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTROG_BANNER_RSC));
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTROGL_BANNER_RSC));
					if(sg.astronaut.getVelocity().getX() > sg.astronaut.getVelocity().getY()){
						sg.astronaut.addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTROGL_BANNER_RSC));
					}
					else{
						sg.astronaut.addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTROG_BANNER_RSC));
					}
				}

				if(input.isKeyDown(Input.KEY_RIGHT)){
					sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(-.01f,0)));
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTROG_BANNER_RSC));
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTROGL_BANNER_RSC));
					sg.astronaut.addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTROGL_BANNER_RSC));

				}
				if(input.isKeyDown(Input.KEY_LEFT)){
					sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(.01f,0)));
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTROGL_BANNER_RSC));
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTROG_BANNER_RSC));
					sg.astronaut.addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTROG_BANNER_RSC));

				}
				if(input.isKeyDown(Input.KEY_UP)){
					sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(0f,0.01f)));
				}
				if(input.isKeyDown(Input.KEY_DOWN)){
					sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(0f,-0.01f)));
				}
				if(!(input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_UP) ||
						input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT)))
				{
					sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(sg.astronaut.getVelocity().negate().scale((float)(0.001*delta))));
				}
				
			}
	
			if(!sg.astronaut.hasGear()){
				if(input.isKeyDown(Input.KEY_LEFT) && input.isKeyDown(Input.KEY_RIGHT)){
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTRONAUT_ASTROIMG_RSC));
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTRONAUTL_ASTROLIMG_RSC));
					if(sg.astronaut.getVelocity().getX() > sg.astronaut.getVelocity().getY()){
						sg.astronaut.addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTRONAUTL_ASTROLIMG_RSC));
					}
					else{
						sg.astronaut.addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTRONAUT_ASTROIMG_RSC));
					}
				}

				if(input.isKeyDown(Input.KEY_RIGHT)){
					sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(-.01f,0)));
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTRONAUT_ASTROIMG_RSC));
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTRONAUTL_ASTROLIMG_RSC));
					sg.astronaut.addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTRONAUTL_ASTROLIMG_RSC));

				}
				if(input.isKeyDown(Input.KEY_LEFT)){
					sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(.01f,0)));
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTRONAUTL_ASTROLIMG_RSC));
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTRONAUT_ASTROIMG_RSC));
					sg.astronaut.addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTRONAUT_ASTROIMG_RSC));

				}
				if(input.isKeyDown(Input.KEY_UP)){
					sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(0f,0.01f)));
				}
				if(input.isKeyDown(Input.KEY_DOWN)){
					sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(0f,-0.01f)));
				}
				if(!(input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_UP) ||
						input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT)))
				{
					sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(sg.astronaut.getVelocity().negate().scale((float)(0.001*delta))));
				}
			}
		}
		else{

			if(input.isKeyDown(Input.KEY_LEFT)){
			/*	sg.astronaut.setTheta(sg.astronaut.getTheta() + Math.toRadians((10 *(delta/1000.0))));*/
				sg.astronaut.updateTheta(moon.getX(), moon.getY(), true);
				//sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(.01f,0)));
			}
			if(input.isKeyDown(Input.KEY_RIGHT)){
/*				sg.astronaut.setTheta(sg.astronaut.getTheta() + Math.toRadians((-10*(delta/1000.0))));*/
				sg.astronaut.updateTheta(moon.getX(), moon.getY(), false);
			}
			if(input.isKeyDown(Input.KEY_DOWN)){
				if(sg.astronaut.getY() <= (moon.getY()))
					sg.astronaut.setVelocity((new Vector(0f,-0.01f)));
				else
					sg.astronaut.setVelocity((new Vector(0f,0.2f)));
				isFloating = true;
			}
							
		}
		
		if(input.isKeyPressed(Input.KEY_S)){
			sg.astronaut.getShield().shieldHit(1, delta);
		}
		if(isFloating){
			for(Moon mn : sg.moon)
				if(sg.astronaut.collides(mn) != null){
					if(sg.astronaut.getVelocity().length() > 0.25)
						sg.astronaut.getShield().shieldHit(1, delta);
					else
						if(withinDistance(sg.astronaut, mn.getPosition(), mn.getCoarseGrainedWidth()/2, true)){
							isFloating = false;
							moon = mn;
							double theta = (Math.toDegrees( Math.atan2(((double)sg.astronaut.getY()-moon.getY()), (double)((moon.getX()-sg.astronaut.getX() )))) + 360.0) % 360.0;
							//double theta = ((Math.atan2((double)((sg.astronaut.getY()-moon.getY())), (double)((sg.astronaut.getX()-moon.getX())))*(180)));
			/*				if(sg.astronaut.getY() > (mn.getY() + mn.getCoarseGrainedHeight()/2) )
								theta = theta+180;*/
							sg.astronaut.setTheta(theta);
							float orbit = 
									(float) Math.sqrt((sg.astronaut.getX()-(moon.getX()))*
									(sg.astronaut.getX()-(moon.getX())) + 
									(sg.astronaut.getY()-(moon.getY()))*
									(sg.astronaut.getY()-(moon.getY())));
							sg.astronaut.setOrbit(orbit);
						}
				}
		}
		
		if(!sg.astronaut.hasGear() && !isFloating){
			for (Gear gr : sg.gear){
				if(sg.astronaut.collides(gr) != null){
					gr.pickUp();	
					gr.removeImage(ResourceManager.getImage(SalvageGame.GEAR_GEARIMG_RSC));
					sg.astronaut.pickUp();
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTRONAUT_ASTROIMG_RSC));
					sg.astronaut.removeImage(ResourceManager.getImage(SalvageGame.ASTRONAUTL_ASTROLIMG_RSC));
				if(sg.astronaut.getVelocity().getX() > sg.astronaut.getVelocity().getY()){
					sg.astronaut.addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTROGL_BANNER_RSC));
				}
				else{
					sg.astronaut.addImageWithBoundingBox(ResourceManager.getImage(SalvageGame.ASTROG_BANNER_RSC));
				}

				}
			}
				
		}
		if(sg.astronaut.getCoarseGrainedMinX() > sg.ScreenWidth)
			sg.astronaut.setX(-32);
		if(sg.astronaut.getCoarseGrainedMaxY() < 0)
			sg.astronaut.setX(sg.ScreenWidth+32);
		/*if(ast.getCoarseGrainedMinY() > sg.ScreenHeight)
			ast.setY(-50);
		if(ast.getCoarseGrainedMaxY() < 0)
			ast.setX(sg.ScreenHeight+50);*/
		
		if(sg.astronaut.collides(sg.ship)!= null){
			if(sg.astronaut.hasGear()){
				numGears -=1;
				sg.astronaut.drop();
				heldGear = null;
				for (Iterator<Gear> i = sg.gear.iterator(); i.hasNext();) {
					if (i.next().isHeld()) {
						i.remove();
					}
				}
			}
			if(sg.astronaut.getShield().getShieldHealth() != 10)
					sg.astronaut.getShield().shieldCharge();
		}
		if(isFloating){		
		sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(applyGravity(sg.astronaut.getX(),
				sg.planet.getX(),sg.astronaut.getY(), sg.planet.getY(), sg.planet.getMass(), sg.planet.getDistance())));
		}
		if(isFloating){
			sg.astronaut.update(delta);
		}

		for( Asteroid ast : sg.asteroids){
			/*if(ast.getCoarseGrainedMinX() > sg.ScreenWidth)
				ast.setX(-50);
			if(ast.getCoarseGrainedMaxY() < 0)
				ast.setX(sg.ScreenWidth+50);
			if(ast.getCoarseGrainedMinY() > sg.ScreenHeight)
				ast.setY(-50);
			if(ast.getCoarseGrainedMaxY() < 0)
				ast.setX(sg.ScreenHeight+50);*/
			if(ast.getCoarseGrainedMinX() > sg.ScreenWidth)
				ast.setVelocity(ast.getVelocity().negate());
			if(ast.getCoarseGrainedMaxY() < 0)
				ast.setVelocity(ast.getVelocity().negate());
			if(ast.getCoarseGrainedMinY() > sg.ScreenHeight)
				ast.setVelocity(ast.getVelocity().negate());
			if(ast.getCoarseGrainedMaxY() < 0)
				ast.setVelocity(ast.getVelocity().negate());
			ast.update(delta);
			
			if(ast.collides(sg.astronaut) != null)
				sg.astronaut.getShield().shieldHit(1, delta);
			
			if(sg.astronaut.getShield().getShieldHealth() == 0){
				livesLeft--;
			sg.astronaut.getShield().shieldCharge();
			
			}
			
			if(livesLeft == 0){
				((GameOverState)game.getState(SalvageGame.GAMEOVERSTATE)).setUserLive(livesLeft);
				sg.enterState(SalvageGame.GAMEOVERSTATE);	
			}
			if(timesUp){
				((GameOverState)game.getState(SalvageGame.GAMEOVERSTATE)).setUserTimer(0);
				sg.enterState(SalvageGame.GAMEOVERSTATE);	
			}
			if(numGears == 0 && sg.currentLevel == sg.AvailableLevels){
				((GameOverState)game.getState(SalvageGame.GAMEOVERSTATE)).setUserLive(livesLeft);
				((GameOverState)game.getState(SalvageGame.GAMEOVERSTATE)).setUserTimer(1);
				sg.enterState(SalvageGame.GAMEOVERSTATE);	
			}
			if(sg.astronaut.getCoarseGrainedMinY() > sg.planet.getCoarseGrainedMinY()){
				if(withinDistance(sg.astronaut, sg.planet.getPosition(), sg.planet.getCoarseGrainedWidth()/2, false)){
				livesLeft--;
				sg.astronaut.setPosition(new Vector(sg.ScreenWidth/2,sg.ScreenHeight/2));
				}
			}
		}
	}
	
	public boolean withinDistance(Astronaut astro, Vector planet, float radius, boolean moon){
		
		float x = astro.getX() - planet.getX();
		float y;
		if(moon)
			y = astro.getY() - planet.getY();
		else
			y = astro.getCoarseGrainedMinY() - planet.getY();
		double distance= Math.sqrt(x*x + y*y);
		if(distance <= radius)
			return true;
		return false;
	}
	
	public Vector applyGravity(float asPosX, float planPosX, float asPosY, float planPosY,
			float mass, float distance){
		float G = 0.00067f;
		float aMass = 1;
		float pMass = mass;
		float rX = asPosX - planPosX;
		float rY = asPosY - planPosY;
		float r = (float)Math.sqrt((rX*rX)+(rY*rY));
		if (r > distance){
			return new Vector(0f,0f);
		}
		float forceX = G*(aMass*pMass)/(r*r);
		float forceY = G*(aMass*pMass)/(r*r);
		return new Vector(-forceX*rX, -forceY*rY);
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return SalvageGame.PLAYINGSTATE;
	}

}

