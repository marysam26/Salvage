package salvage;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import jig.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class PlayingState extends BasicGameState {
	private Timer timer;
	private int duration;
	private int numGears;
	private int livesLeft;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		timer = new Timer();
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
                if (duration< 0)
                    timer.cancel();
            }
        }, 0, 1000);
	}
	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		SalvageGame sg = (SalvageGame)game;
		g.drawString("Playing State", 0, 10);
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
	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
		 {
		Input input = container.getInput();
		SalvageGame sg = (SalvageGame)game;
		
		if(input.isKeyDown(Input.KEY_RIGHT)){
			sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(-.001f,0)));
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(.001f,0)));
		}
		if(input.isKeyDown(Input.KEY_UP)){
			sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(0f,0.001f)));
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(new Vector(0f,-0.001f)));
		}
		if(!(input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_UP) ||
				input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT)))
				{
			sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(sg.astronaut.getVelocity().negate().scale((float)(0.001*delta))));
		}
		if(input.isKeyPressed(Input.KEY_S)){
			sg.astronaut.getShield().shieldHit(1);
		}
		
		for (Gear gr : sg.gear){
			if(sg.astronaut.collides(gr) != null){
				gr.pickUp();	
				sg.astronaut.pickUp();
			}
			if(gr.isHeld()){
				gr.setVelocity(sg.astronaut.getVelocity());
				gr.update(delta);
			}
		}
		
		if(sg.astronaut.collides(sg.ship)!= null){
			if(sg.astronaut.hasGear()){
				numGears -=1;
				sg.astronaut.drop();
				for (Iterator<Gear> i = sg.gear.iterator(); i.hasNext();) {
					if (i.next().isHeld()) {
						i.remove();
					}
				}
			}
			if(sg.astronaut.getShield().getShieldHealth() != 10)
					sg.astronaut.getShield().shieldCharge();
		}
				
		sg.astronaut.setVelocity(sg.astronaut.getVelocity().add(applyGravity(sg.astronaut.getX(),
				sg.planet.getX(),sg.astronaut.getY(), sg.planet.getY(), sg.planet.getMass(), sg.planet.getDistance())));
	
		sg.astronaut.update(delta);
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

