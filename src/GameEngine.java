import java.awt.*;
import java.awt.event.*;

import java.util.*;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class GameEngine extends GameApplet{

	// Menu States and background
	public static enum STATE{MENU, GAME, PAUSE, OPTIONS, HELP};
	public static STATE state = STATE.MENU;
	String menuBackground = ".\\Background\\Starset.png";
	Image starBackground = Toolkit.getDefaultToolkit().getImage(menuBackground);
	
	// Menu States
		private Menu menu;
		private PauseState pause;
		private HelpState help;
		private Options options;

	
	//Audio
	AudioHandler gameSound = new AudioHandler();
  	
	// Preparing the background image
	String backgroundPath = ".\\Background\\level1.png";
	Image background = Toolkit.getDefaultToolkit().getImage(backgroundPath);
	
	// Pause Menu Background
		String pauseMenuBackground = ".\\Background\\pauseMenuBackground.png";
		Image pauseMenu = Toolkit.getDefaultToolkit().getImage(pauseMenuBackground);

	
	// Player objects
	public static Spaceship spaceship = new Spaceship(700, 600, "orange1");
	
	// Level Variables
	public static Level[] level = new Level[3];
	public static int currLvl = 0;
	
   //=======================================================================================
	
	public void init() {
		//Menu
		setSize(1440, 900);
		
		
		
		// Declaring the levels
		level[currLvl] = new Level_1();
		level[1]       = new Level_2();
		level[2]       = new Level_3();
		
		menu = new Menu();
		pause = new PauseState();
		help = new HelpState();
		options = new Options();
		this.addMouseListener(new MouseInput());
		super.init();
		
	}
	
	//=======================================================================================
	
	public void inGameLoop() {
		
		if (state == STATE.MENU) {
			gameSound.stopTheme();//stop in Game theme
			gameSound.playMenuTheme();//play Menu Theme
			this.addMouseListener(new MouseInput());
		}
		
		else if(state == STATE.GAME) {
			gameSound.stopMenuTheme();		//stop Menu Theme while in game
			gameSound.playTheme();			//play Game theme
			gameSound.stopPauseMenuTheme(); // Plays Pause Menu Them
			if(pressing[UP]) {spaceship.moveUp();}
			if(pressing[DN]) {spaceship.moveDown();}
			if(pressing[LT]) {spaceship.moveLeft();}		
			if(pressing[RT]) {spaceship.moveRight();}
			if(pressing[SPACE]) {spaceship.shoot();}
			if(pressing[_W]) {
				spaceship.resetLasers(); 
				
				if (currLvl==2) {
					currLvl =0;}
				else {
					currLvl++;
				}
					
			}

			if (pressing[_P]){
				state = STATE.PAUSE;
				gameSound.stopTheme();
				gameSound.playPauseMenuTheme();
				try
				{
					Thread.sleep(100);
				}
				catch(InterruptedException x) {};	
			}

			// Starts the level if it hasn't started yet
			if(!level[currLvl].levelStarted) {level[currLvl].start();}
			
			// Updates the level
			level[currLvl].update();
			
			// Updates the spaceship lasers
			spaceship.updateLasers();
			
			// If you beat the current level, update/reset variables
			if(level[currLvl].exitLevel) {
				if(currLvl >= 3) {System.exit(0);}
				currLvl ++;
				spaceship.resetLasers();
				Spaceship.health = 100;
				Spaceship.isShielded = false;
				Spaceship.shield = 100;
			}
				
		}
		
		
		
	}
	
	//=======================================================================================

	public void paint(Graphics g) {
		if (state == STATE.MENU) {
			g.drawImage(starBackground, 0, 0, this);
			menu.render(g);
		}
		if(state == STATE.PAUSE) {
			g.drawImage(pauseMenu, 0, 0, this);
			pause.render(g);
		}
		
		if(state == STATE.HELP) {
			g.drawImage(pauseMenu, 0, 0, this);
			help.render(g);
		}
		
		if(state == STATE.OPTIONS) {
			g.drawImage(pauseMenu, 0, 0, this);
			options.render(g);
		}
		else if(state == STATE.GAME && level[currLvl].levelStarted) {
				
			// Draws the level's background
			level[currLvl].drawBackground(g);
			
			// Draws the level's objects
			level[currLvl].draw(g);
			
			// Draws the spaceship
			if (spaceship.isAlive) {spaceship.draw(g);}

		}// Closes out game state
		
	}// Closes draw method
	
	//=======================================================================================

}

