import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public abstract class Level {
	
	// Background
	public Image background;
	
	// Wave variables
	public int waveNum = 1;
	
	// Boolean variables
	public boolean levelStarted = false;
	public boolean levelCompleted = false;
	
	// Outro variables
	public String outroText = "Level Completed!";
	public boolean exitLevel = false;
	public int textX = 300;
	public int textY = -50;
	
	// Useful variables
	public boolean enemiesSpawned = false;
	public int enemiesInLevel;
	public int enemiesKilled = 0;
	
	// Enemy variables
	public EnemyShip[] enemies;
	public int currentEnemy = 0;
	
	public UFO[] ufos;
	public int currentUFO = 0;
	
	public Meteor[] meteors;
	public int currentMeteor = 0;
	
	//=======================================================================================
	
	public Level() {}
	
	//=======================================================================================
	
	public void start() {
		levelStarted = true;
		update();
	}
	
	//=======================================================================================
	
	public void update() {
		if(levelStarted) { 
			if(!enemiesSpawned) {spawnEnemies();}
			updateEnemies();
		}
		
		if(enemiesKilled >= enemiesInLevel) {
			levelCompleted = true; enemiesSpawned = true; outro();
		}
	}
	
	//=======================================================================================
	
	public abstract void updateEnemies();
	
	//=======================================================================================
	
	public void outro() {
		if(textY <= 400) {textY += 2;}
		else {exitLevel = true;}
	}
	
	//=======================================================================================
	public abstract void spawnEnemies();
	
	//=======================================================================================
	
	public void drawBackground(Graphics g) {
		// Draws the background multiple times to cover entire screen
			for(int i = -2; i < 2; i ++) {
				g.drawImage(background, (1366 * i), 0, null);
			}
	}
	
	//=======================================================================================
	
	public void drawOutro(Graphics g) {
		if(levelCompleted) {
			g.setFont(new Font("SansSerif", Font.BOLD,100));
			g.setColor(Color.WHITE);
			g.drawString(outroText, textX, textY);  
		}
	}
	
	//=======================================================================================
	
	public abstract void draw(Graphics g);
	
	//=======================================================================================

}
