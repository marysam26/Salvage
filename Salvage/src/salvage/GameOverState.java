package salvage;

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
		g.drawImage(ResourceManager.getImage(SalvageGame.GAMEOVER_BANNER_RSC), 225,
				270);
		}
		else{
			g.drawImage(ResourceManager.getImage(SalvageGame.WINNER_BANNER_RSC), 0,
					0);
		}
	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {

		
		timer -= delta;
		if (timer <= 0){
			game.enterState(SalvageGame.STARTUPSTATE, new EmptyTransition(), new HorizontalSplitTransition() );
		}
		// check if there are any finished explosions, if so remove them
		

	}

	@Override
	public int getID() {
		return SalvageGame.GAMEOVERSTATE;
	}
	
}