package salvage;
import java.util.ArrayList;
import java.util.Timer;

import jig.Entity;
import jig.ResourceManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class SalvageGame extends StateBasedGame {
	
	/**
	 * A Simple Game of Bounce.
	 * 
	 * The game has three states: StartUp, Playing, and GameOver, the game
	 * progresses through these states based on the user's input and the events that
	 * occur. Each state is modestly different in terms of what is displayed and
	 * what input is accepted.
	 * 
	 * In the playing state, our game displays a moving rectangular "ball" that
	 * bounces off the sides of the game container. The ball can be controlled by
	 * input from the user.
	 * 
	 * When the ball bounces, it appears broken for a short time afterwards and an
	 * explosion animation is played at the impact site to add a bit of eye-candy
	 * additionally, we play a short explosion sound effect when the game is
	 * actively being played.
	 * 
	 * Our game also tracks the number of bounces and syncs the game update loop
	 * with the monitor's refresh rate.
	 * 
	 * Graphics resources courtesy of qubodup:
	 * http://opengameart.org/content/bomb-explosion-animation
	 * 
	 * Sound resources courtesy of DJ Chronos:
	 * http://www.freesound.org/people/DJ%20Chronos/sounds/123236/
	 * 
	 * 
	 * @author wallaces
	 * 
	 */

		
		public static final int STARTUPSTATE = 0;
		public static final int PLAYINGSTATE = 1;
		public static final int GAMEOVERSTATE = 2;
		public static final int NEXTLEVELSTATE = 3;
//		
		public static final String SPACESHIP_SHIPIMG_RSC = "salvage/resources/spaceBlock.png";
		public static final String ASTRONAUTH_ASTRIMG_RSC = "salvage/resources/astronautBlock.png";
		public static final String PLANET1_PLANETIMG_RSC = "salvage/resources/Planet-11.png";
		public static final String MOON_MOONIMG_RSC = "salvage/resources/moon.png";
		public static final String GEAR_GEARIMG_RSC = "salvage/resources/gear.png";
		public static final String SHEILD_SHEILDIMG_RSC = "salvage/resources/shield.png";
		public static final String HEALTH1_HEALTHIMG_RSC = "salvage/resources/health1.png";
		public static final String HEALTH2_HEALTHIMG_RSC = "salvage/resources/health2.png";
		public static final String HEALTH3_HEALTHIMG_RSC = "salvage/resources/health3.png";
		public static final String HEALTH4_HEALTHIMG_RSC = "salvage/resources/health4.png";
		public static final String HEALTH5_HEALTHIMG_RSC = "salvage/resources/health5.png";
		public static final String HEALTH6_HEALTHIMG_RSC = "salvage/resources/health6.png";
		public static final String HEALTH7_HEALTHIMG_RSC = "salvage/resources/health7.png";
		public static final String HEALTH8_HEALTHIMG_RSC = "salvage/resources/health8.png";
		public static final String HEALTH9_HEALTHIMG_RSC = "salvage/resources/health9.png";
		public static final String HEALTH10_HEALTHIMG_RSC = "salvage/resources/health10.png";
		public static final String HEALTH0_HEALTHIMG_RSC = "salvage/resources/health0.png";
		public static final String SHIELDAN_SHIELDANIMG_RSC = "salvage/resources/ShieldAnimation.png";
//		public static final String BANG_EXPLOSIONSND_RSC = "bounce/resource/explosion.wav";
//		public static final String PING_EXPLOSIONSND_RSC = "bounce/resource/ping.ogg";
//		
		
		public final int ScreenWidth;
		public final int ScreenHeight;
		public final int AvailableLevels; //number of levels with corresponding text files
		public Integer currentLevel;

		public Spaceship ship;
		public Astronaut astronaut;
		public Planet planet;
		public ArrayList<Moon> moon;
		public ArrayList<Gear> gear;
		public int duration;

		/**
		 * Create the BounceGame frame, saving the width and height for later use.
		 * 
		 * @param title
		 *            the window's title
		 * @param width
		 *            the window's width
		 * @param height
		 *            the window's height
		 */
		public SalvageGame(String title, int width, int height, int levelNum) {
			super(title);
			ScreenHeight = height;
			ScreenWidth = width;
			AvailableLevels = levelNum;
			currentLevel= 1;
			Entity.setCoarseGrainedCollisionBoundary(Entity.AABB);
		
		}


		@Override
		public void initStatesList(GameContainer container) throws SlickException {
			addState(new StartUpState());
			//addState(new GameOverState());
			addState(new PlayingState());
			//addState(new NextLevelState());*/
			
			// the sound resource takes a particularly long time to load,
			// we preload it here to (1) reduce latency when we first play it
			// and (2) because loading it will load the audio libraries and
			// unless that is done now, we can't *disable* sound as we
			// attempt to do in the startUp() method.
		//	ResourceManager.loadSound(BANG_EXPLOSIONSND_RSC);		

			// preload all the resources to avoid warnings & minimize latency...
			ResourceManager.loadImage(SPACESHIP_SHIPIMG_RSC);
			ResourceManager.loadImage(ASTRONAUTH_ASTRIMG_RSC);
			ResourceManager.loadImage(PLANET1_PLANETIMG_RSC);
			ResourceManager.loadImage(MOON_MOONIMG_RSC);
			ResourceManager.loadImage(GEAR_GEARIMG_RSC);
			ResourceManager.loadImage(HEALTH1_HEALTHIMG_RSC);
			ResourceManager.loadImage(HEALTH2_HEALTHIMG_RSC);
			ResourceManager.loadImage(HEALTH3_HEALTHIMG_RSC);
			ResourceManager.loadImage(HEALTH4_HEALTHIMG_RSC);
			ResourceManager.loadImage(HEALTH5_HEALTHIMG_RSC);
			ResourceManager.loadImage(HEALTH6_HEALTHIMG_RSC);
			ResourceManager.loadImage(HEALTH7_HEALTHIMG_RSC);
			ResourceManager.loadImage(HEALTH8_HEALTHIMG_RSC);
			ResourceManager.loadImage(HEALTH9_HEALTHIMG_RSC);
			ResourceManager.loadImage(HEALTH10_HEALTHIMG_RSC);
			ResourceManager.loadImage(SHEILD_SHEILDIMG_RSC);
			ResourceManager.getImage(SHEILD_SHEILDIMG_RSC);
			ResourceManager.loadImage(SHIELDAN_SHIELDANIMG_RSC);
			duration = 600;
			ship = new Spaceship(ScreenWidth/2, 125);
			Shield shield = new Shield(ScreenWidth/2 + 50, 40);
			astronaut = new Astronaut(ScreenWidth/2, ScreenHeight/2, 0f, 0f, shield);
			planet = new Planet(ScreenWidth/2, (1.5f)*ScreenHeight, 1, 1000, 600);
			moon = new ArrayList<Moon>(10);
			moon.add(new Moon(ScreenWidth/4,ScreenHeight/2, 100));
			gear = new ArrayList<Gear>(10);
			gear.add(new Gear(ScreenWidth/4, ScreenHeight/2-(0.5f*moon.get(0).getCoarseGrainedWidth())-25, 0f, 0f));
		}
		
		public static void main(String[] args) {
			AppGameContainer app;
			try {
				app = new AppGameContainer(new SalvageGame("Salvage", 1280, 800, 1));
				app.setDisplayMode(1280, 800, false);
				app.setVSync(true);
				app.setShowFPS(false);
				app.start();
			} catch (SlickException e) {
				e.printStackTrace();
			}

		}

		
	}


