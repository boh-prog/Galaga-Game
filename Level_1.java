import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Level_1 extends Level {
	
	//=======================================================================================
	
	public Level_1() {
		background = Toolkit.getDefaultToolkit().getImage(".\\Background\\level1.png");
		enemiesInLevel = 22;
		enemies = new EnemyShip[22];
		ufos = new UFO[2];
	}
	
	//=======================================================================================
	
	public void updateEnemies() {
		
		if(enemies != null) {
			for(int i = 0; i <= currentEnemy; i ++) {
				if(enemies[i] != null && enemies[i].isAlive) {enemies[i].update();}
				if(enemies[i] != null) {enemies[i].updatePowerups();}
			}
		}
	}
	
	//=======================================================================================
	
	public void spawnEnemies() {

		if(waveNum == 1) {
			
			enemies[currentEnemy] = new EnemyShip(0*200, -100,"blue1", "shooter");
			enemies[currentEnemy].setTargetCoords(0*200, 100);
			
			enemies[currentEnemy+1] = new EnemyShip(1*200, -100,"blue1", "shooter");
			enemies[currentEnemy+1].setTargetCoords(1*200, 100);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(2*200, -100,"blue1", "shooter");
			enemies[currentEnemy+1].setTargetCoords(2*200, 100);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(3*200, -100,"blue1", "shooter");
			enemies[currentEnemy+1].setTargetCoords(3*200, 100);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(4*200, -100,"blue1", "shooter");
			enemies[currentEnemy+1].setTargetCoords(4*200, 100);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(5*200, -100,"blue1", "shooter");
			enemies[currentEnemy+1].setTargetCoords(5*200, 100);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(6*200, -100,"blue1", "shooter");
			enemies[currentEnemy+1].setTargetCoords(6*200, 100);
			currentEnemy ++; waveNum ++;
			
		}// Closes out wavenum == 1
		
		
		
		if(waveNum == 2 && enemiesKilled >= 7) {
				
			enemies[currentEnemy+1] = new EnemyShip(1366+50, 175, "red2", "chase");
			enemies[currentEnemy+1].setTargetCoords(900, 175);
			enemies[currentEnemy+1].health = 250;
			currentEnemy ++;
				
			enemies[currentEnemy+1] = new EnemyShip(-50, 175,"red2", "chase");
			enemies[currentEnemy+1].setTargetCoords(500, 175);
			enemies[currentEnemy+1].health = 250;
			currentEnemy ++; waveNum ++;
				
				
		}// Closes out wavenum == 2
		
		
		
		if(waveNum == 3 && enemiesKilled >= 9) {
			
			enemies[currentEnemy+1] = new EnemyShip(1366, 750, "black3", "side2side");
			enemies[currentEnemy+1].movingRight = true;
			enemies[currentEnemy+1].setTargetCoords(100, 250);
			enemies[currentEnemy+1].coolDownTime = 400;
			enemies[currentEnemy+1].health = 400;
			currentEnemy ++;
				
			enemies[currentEnemy+1] = new EnemyShip(0, 750,"black3", "side2side");
			enemies[currentEnemy+1].movingLeft = true;
			enemies[currentEnemy+1].setTargetCoords((6*200)-100, 250);
			enemies[currentEnemy+1].coolDownTime = 400;
			enemies[currentEnemy+1].health = 400;
			currentEnemy ++; waveNum ++;
			
		}// Closes out wavenum == 3
		
		
		
		if(waveNum == 4 && enemiesKilled >= 11) {
			
			enemies[currentEnemy+1] = new EnemyShip(0*200, -100,"blue5", "default2chase");
			enemies[currentEnemy+1].setTargetCoords(0*200, 100);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(1*200, -100,"blue5", "default");
			enemies[currentEnemy+1].setTargetCoords(1*200, 100);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(2*200, -100,"blue5", "default");
			enemies[currentEnemy+1].setTargetCoords(2*200, 100);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(3*200, -100,"blue5", "default");
			enemies[currentEnemy+1].setTargetCoords(3*200, 100);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(4*200, -100,"blue5", "default");
			enemies[currentEnemy+1].setTargetCoords(4*200, 100);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(5*200, -100,"blue5", "default");
			enemies[currentEnemy+1].setTargetCoords(5*200, 100);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(6*200, -100,"blue5", "default2chase");
			enemies[currentEnemy+1].setTargetCoords(6*200, 100);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(1366+50, 175, "red2", "chase");
			enemies[currentEnemy+1].setTargetCoords(900, 175);
			currentEnemy ++;
					
			enemies[currentEnemy+1] = new EnemyShip(-50, 175,"red2", "chase");
			enemies[currentEnemy+1].setTargetCoords(500, 175);
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(1366, 750, "black3", "side2side");
			enemies[currentEnemy+1].movingRight = true;
			enemies[currentEnemy+1].setTargetCoords(100, 250);
			enemies[currentEnemy+1].coolDownTime = 400;
			enemies[currentEnemy+1].health = 400;
			currentEnemy ++;
				
			enemies[currentEnemy+1] = new EnemyShip(0, 750,"black3", "side2side");
			enemies[currentEnemy+1].movingLeft = true;
			enemies[currentEnemy+1].setTargetCoords((6*200)-100, 250);
			enemies[currentEnemy+1].coolDownTime = 400;
			enemies[currentEnemy+1].health = 400;
			currentEnemy ++; waveNum ++;
		}// Closes out wavenum == 4
	}
	
	//=======================================================================================
	
	public void draw(Graphics g) {
		
		// Draws the outro once level is completed
		if(levelCompleted) {
			drawOutro(g);  
		}
		
		// Draws the enemy ships and its powerups
		if(enemies != null) {
			for(int i = 0; i <= currentEnemy; i++) {
				if(enemies[i] != null && enemies[i].isAlive) {
					enemies[i].draw(g);
				}
				if(enemies[i] != null) {enemies[i].drawPowerups(g);}
			}	
		} // Closes enemy ships
	}
	
	//=======================================================================================

}