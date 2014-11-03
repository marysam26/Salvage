package salvage;

import java.util.ArrayList;
import java.util.Iterator;

import jig.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;


/**
 * This state is active when the Game is over. In this state, the ball is
 * neither drawn nor updated; and a gameover banner is displayed. A timer
 * automatically transitions back to the StartUp State.
 * 
 * Transitions From PlayingState
 * 
 * Transitions To StartUpState
 */
class GameOverState extends BasicGameState {
	

	private int timer;
	private int lastKnownTime; // the user's score, to be displayed, but not updated.
	private int lastKnownLives; //used to determine if game over or a win
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		timer = 4000;
	}
	public void setUserLive(int lives){
		lastKnownLives = lives;
	}

	public void setUserTimer(int time) {
		lastKnownTime = time;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {

		SalvageGame sg = (SalvageGame)game;
		sg.currentLevel = 1;
		if(lastKnownLives == 0 || lastKnownTime == 0){
		g.drawImage(ResourceManager.getImage(SalvageGame.GAMEOVER_BANNER_RSC), 0,
				0);
		}
		else{
			g.drawImage(ResourceManager.getImage(SalvageGame.WINNER_BANNER_RSC), 0,
					0);
		}
	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {
		SalvageGame sg = (SalvageGame)game;
		
		timer -= delta;
		if (timer <= 0){
			sg.duration = 60;
			sg.ship = new Spaceship(sg.ScreenWidth/2, 125);
			Shield shield = new Shield(sg.ScreenWidth/2 + 50, 40);
			sg.astronaut = new Astronaut(sg.ScreenWidth/4, sg.ScreenHeight/2, 0f, 0f, shield);
			sg.planet = new Planet(sg.ScreenWidth/2, (1.5f)*sg.ScreenHeight, 1, 1000, 600);
			sg.moon = new ArrayList<Moon>(10);
			sg.moon.add(new Moon(sg.ScreenWidth/4,sg.ScreenHeight/2, 100));
			sg.moon.add(new Moon(3*sg.ScreenWidth/4,sg.ScreenHeight/2, 100));
			sg.gear = new ArrayList<Gear>(10);
			sg.gear.add(new Gear(sg.ScreenWidth/4, sg.ScreenHeight/2-(0.5f*sg.moon.get(0).getCoarseGrainedWidth())-25, 0f, 0f));
			sg.gear.add(new Gear(3*sg.ScreenWidth/4+(0.5f*sg.moon.get(0).getCoarseGrainedWidth()), sg.ScreenHeight/2+(0.5f*sg.moon.get(0).getCoarseGrainedWidth())+12, 0f, 0f));

			sg.asteroids = new ArrayList<Asteroid>(3);
			sg.asteroids.add(new Asteroid(0, 0, 0.1f, 0.1f));
			sg.asteroids.add(new Asteroid(345, 653, 0.1f, -0.1f));
			((PlayingState)game.getState(SalvageGame.PLAYINGSTATE)).stopTimer();
			game.enterState(SalvageGame.STARTUPSTATE, new EmptyTransition(), new HorizontalSplitTransition() );
		}
		// check if there are any finished explosions, if so remove them
		

	}

	@Override
	public int getID() {
		return SalvageGame.GAMEOVERSTATE;
	}
	
}