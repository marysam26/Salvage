package salvage;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class StartUpState extends BasicGameState {
	private Timer timer;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		final SalvageGame sg = (SalvageGame)game;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
            	sg.duration--;
                if (sg.duration< 0)
                    timer.cancel();
            }
        }, 0, 1000);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		SalvageGame sg = (SalvageGame)game;
		g.drawString("Gears: 0", 10, 30);
		g.drawString("Lives Remaining: 3", 110, 30);
		g.drawString("Time Left: "+sg.duration/60 +":" +sg.duration%60  , 310, 30);
		g.drawString("Shield", 500, 30);
		sg.ship.render(g);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return SalvageGame.STARTUPSTATE;
	}

}
