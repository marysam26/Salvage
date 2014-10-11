package salvage;

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
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		timer = new Timer();
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
		g.drawString("Gears: 0", 10, 30);
		g.drawString("Lives Remaining: 3", 110, 30);
		g.drawString("Time Left: "+duration/60 +":" +duration%60  , 310, 30);
		g.drawString("Shield", 500, 30);
		sg.ship.render(g);
		sg.astronaut.render(g);
		sg.planet.render(g);
		for( Moon m : sg.moon){
			m.render(g);
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
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
		
		sg.astronaut.update(delta);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return SalvageGame.PLAYINGSTATE;
	}

}

