package salvage;




import jig.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class StartUpState extends BasicGameState {

	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		SalvageGame sg = (SalvageGame)game;
		g.drawImage(ResourceManager.getImage(SalvageGame.SPACE_SPACEIMG_RSC), 0, 0);
		g.drawString("STARTUP", 10, 10);
		g.drawString("Gears: 0", 10, 30);
		g.drawString("Lives Remaining: 3", 110, 30);
		g.drawString("Time Left: "+sg.duration/60 +":" +sg.duration%60  , 310, 30);
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
		if (input.isKeyDown(Input.KEY_SPACE)){
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
