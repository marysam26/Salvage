package salvage;




import jig.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class StartUpState extends BasicGameState {
private boolean readingInstruct; 
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		readingInstruct = false;
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		if(readingInstruct){
			g.drawImage(ResourceManager.getImage(SalvageGame.INSTRUCT_BANNER_RSC), 0, 0);
		}
		else{
			g.drawImage(ResourceManager.getImage(SalvageGame.START_BANNER_RSC), 0, 0);
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		Input input = container.getInput();
		SalvageGame sg = (SalvageGame)game;

		boolean startGame = false;
		/*if(input.isKeyDown(Input.KEY_1)){
			bg.currentLevel = 1;
			startGame = true;
		}
		if(input.isKeyDown(Input.KEY_2)){
			bg.currentLevel = 2;
			startGame = true;
		}
		if(input.isKeyDown(Input.KEY_3)){
			bg.currentLevel = 3;
			startGame = true;
		}
		if(input.isKeyDown(Input.KEY_4)){
			bg.currentLevel = 4;
			startGame = true;
		}
		if(input.isKeyDown(Input.KEY_5)){
			bg.currentLevel = 5;
			startGame = true;
		}*/
		
		if(input.isKeyPressed(Input.KEY_I)){
			readingInstruct = !readingInstruct;
		}
		if (input.isKeyPressed(Input.KEY_SPACE) && !readingInstruct){
			startGame = true;
		}
		if(startGame){
			((PlayingState)game.getState(SalvageGame.PLAYINGSTATE)).setTimer(sg.duration);
			((PlayingState)game.getState(SalvageGame.PLAYINGSTATE)).setGears(sg.gear.size());
			((PlayingState)game.getState(SalvageGame.PLAYINGSTATE)).setLives(3);
			sg.enterState(SalvageGame.PLAYINGSTATE);	
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return SalvageGame.STARTUPSTATE;
	}

}
