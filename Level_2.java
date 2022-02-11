import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Level_2 extends Level {
	
	//=======================================================================================
	
	public Level_2() {
		background = Toolkit.getDefaultToolkit().getImage(".\\Background\\level2.png");
		enemiesInLevel = 22;
		enemies = new EnemyShip[30];
		ufos = new UFO[30];
		meteors = new Meteor[100];
	}
	
	//=======================================================================================
	
	public void updateEnemies() {
		
		if(enemies != null) {
			for(int i = 0; i <= currentEnemy; i ++) {
				if(enemies[i] != null && enemies[i].isAlive) {enemies[i].update();}
				if(enemies[i] != null) {enemies[i].updatePowerups();}
			}
		}
		
		if(ufos != null) {
			for(int i = 0; i <= currentUFO; i ++) {
				if(ufos[i] != null) {ufos[i].update();  ufos[i].updatePowerups();}
			}
		}
		
		if(meteors != null) {
			for(int i = 0; i <= currentMeteor; i ++) {
				if(meteors[i] != null) {meteors[i].update();}
			}
		}
	}
	
	//=======================================================================================
	
	public void spawnEnemies() {

		if(waveNum == 1) {
			ufos[currentUFO] = new UFO(-50,300,"red", "teleport");
			ufos[currentUFO].setTeleportVariables(200, 200);
			ufos[currentUFO].setTargetCoords(315, 300);
			
			ufos[currentUFO+1] = new UFO(1400,300,"green", "teleport");
			ufos[currentUFO+1].setTeleportVariables(315, 75);
			ufos[currentUFO+1].setTargetCoords(950, 300);
			currentUFO++;
			
			ufos[currentUFO+1] = new UFO(500,-1000,"blue", "default");
			ufos[currentUFO+1].setTargetCoords(500, 100);
			currentUFO++;
			
			ufos[currentUFO+1] = new UFO(700,-1000,"yellow", "default");
			ufos[currentUFO+1].setTargetCoords(700, 100);
			currentUFO++; waveNum ++;
			
		}// Closes out wavenum == 1
		
		
		
		if(waveNum == 2 && enemiesKilled >= 4) {
			
			enemies[currentEnemy] = new EnemyShip(1366, 750, "black3", "side2side");
			enemies[currentEnemy].movingRight = true;
			enemies[currentEnemy].setTargetCoords(100, 250);
			enemies[currentEnemy].coolDownTime = 350;
			enemies[currentEnemy].health = 350;
			enemies[currentEnemy].speed = 2;
			//currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(0, 750, "red4", "side2side");
			enemies[currentEnemy+1].movingLeft = true;
			enemies[currentEnemy+1].setTargetCoords(1200, 200);
			enemies[currentEnemy+1].coolDownTime = 350;
			enemies[currentEnemy+1].health = 350;
			enemies[currentEnemy+1].speed = 2;
			currentEnemy ++;
			
			ufos[currentUFO+1] = new UFO(200,-1000,"red", "teleport");
			ufos[currentUFO+1].setTeleportVariables(315, 75);
			ufos[currentUFO+1].setTargetCoords(200, 70);
			currentUFO++;
			
			ufos[currentUFO+1] = new UFO(700,-1500,"yellow", "teleport");
			ufos[currentUFO+1].setTeleportVariables(100, 200);
			ufos[currentUFO+1].setTargetCoords(700, 70);
			currentUFO++; waveNum ++;
				
				
		}// Closes out wavenum == 2
		
		
		
		if(waveNum == 3 && enemiesKilled >= 8) {
			
			//Spawns 30 meteors
			for(int i = 0; i < 30; i ++) {
				if(i == 0) {
					meteors[i] = new Meteor(i*150,-50,"med1");
					meteors[i].setTargetCoords(i*150, 800);
				}
				if(i < 10) {
					meteors[i] = new Meteor(i*150,-50,"med1");
					meteors[i].setTargetCoords(i*150, 800);
					currentMeteor ++;
				}
				else if(i < 20) {
					meteors[i] = new Meteor((i-10)*150,-700,"med1");
					meteors[i].setTargetCoords((i-10)*150, 800);
					currentMeteor ++;
				}
				else {
					meteors[i] = new Meteor((i-20)*150,-1200,"med1");
					meteors[i].setTargetCoords((i-20)*150, 800);
					currentMeteor ++;
				}
			}// Closes for loop that spawns meteors
			
			enemies[currentEnemy+1] = new EnemyShip(200, -3300, "green4", "chase");
			enemies[currentEnemy+1].setTargetCoords(200, 200);
			enemies[currentEnemy+1].health = 200;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(600, -4000, "green4", "chase");
			enemies[currentEnemy+1].setTargetCoords(600, 200);
			enemies[currentEnemy+1].health = 200;
			enemies[currentEnemy+1].speed = 2;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(800, -4000, "green4", "chase");
			enemies[currentEnemy+1].setTargetCoords(800, 200);
			enemies[currentEnemy+1].health = 200;
			enemies[currentEnemy+1].speed = 2;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(1000, -3300, "green4", "chase");
			enemies[currentEnemy+1].setTargetCoords(1000, 200);
			enemies[currentEnemy+1].health = 200;
			currentEnemy ++; waveNum++;
			
		}// Closes out wavenum == 3
		
		
		
		if(waveNum == 4 && enemiesKilled >= 12) {
			
			enemies[currentEnemy+1] = new EnemyShip(1500, 70, "blue4", "side2side");
			enemies[currentEnemy+1].movingRight = true;
			enemies[currentEnemy+1].setTargetCoords(100, 70);
			enemies[currentEnemy+1].coolDownTime = 300;
			enemies[currentEnemy+1].health = 100;
			enemies[currentEnemy+1].speed = 2;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(-100, 130, "green4", "side2side");
			enemies[currentEnemy+1].movingLeft = true;
			enemies[currentEnemy+1].setTargetCoords(1190, 130);
			enemies[currentEnemy+1].coolDownTime = 300;
			enemies[currentEnemy+1].health = 100;
			enemies[currentEnemy+1].speed = 2;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(3500, 180, "red4", "side2side");
			enemies[currentEnemy+1].movingRight = true;
			enemies[currentEnemy+1].setTargetCoords(100, 180);
			enemies[currentEnemy+1].coolDownTime = 300;
			enemies[currentEnemy+1].health = 100;
			enemies[currentEnemy+1].speed = 2;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(-3000, 230, "black4", "side2side");
			enemies[currentEnemy+1].movingLeft = true;
			enemies[currentEnemy+1].setTargetCoords(1200, 200);
			enemies[currentEnemy+1].coolDownTime = 300;
			enemies[currentEnemy+1].health = 100;
			enemies[currentEnemy+1].speed = 2;
			currentEnemy ++;
			
			waveNum ++;
		}// Closes out wavenum == 4
		
		if(waveNum == 5 && enemiesKilled >= 16) {
			
			ufos[currentUFO+1] = new UFO(1700, 70,"blue", "teleport");
			ufos[currentUFO+1].setTeleportVariables(100, 10);
			ufos[currentUFO+1].setTargetCoords(100, 150);
			currentUFO++;
			
			ufos[currentUFO+1] = new UFO(1500, 70,"green", "teleport");
			ufos[currentUFO+1].setTeleportVariables(422, 100);
			ufos[currentUFO+1].setTargetCoords(450, 150);
			currentUFO++;
			
			ufos[currentUFO+1] = new UFO(-200, 70,"yellow", "teleport");
			ufos[currentUFO+1].setTeleportVariables(90, 100);
			ufos[currentUFO+1].setTargetCoords(750, 150);
			currentUFO++;
			
			ufos[currentUFO+1] = new UFO(-400, 70,"red", "teleport");
			ufos[currentUFO+1].setTeleportVariables(200, 35);
			ufos[currentUFO+1].setTargetCoords(1100, 150);
			currentUFO++;
			
			ufos[currentUFO+1] = new UFO(-500, 1700,"red", "moveaway");
			ufos[currentUFO+1].setTargetCoords(850, 300);
			currentUFO++;
			
			ufos[currentUFO+1] = new UFO(2000, 1700,"yellow", "moveaway");
			ufos[currentUFO+1].setTargetCoords(300, 300);
			currentUFO++;
			
			waveNum ++;
		}// Closes out wavenum == 5
		
	}
	
	//=======================================================================================
	
	public void draw(Graphics g) {
		
		// Draws the outro once level is completed
		if(levelCompleted) {
			drawOutro(g);  
		}
		
		// Draws the enemy ships and its power ups
		if(enemies != null) {
			for(int i = 0; i <= currentEnemy; i++) {
				if(enemies[i] != null && enemies[i].isAlive) {
					enemies[i].draw(g);
				}
				if(enemies[i] != null) {enemies[i].drawPowerups(g);}
			}	
		} // Closes enemy ships
		
		// Draws the ufos and its powerups
		if(ufos != null) {
			for(int i = 0; i <= currentUFO; i++) {
				if(ufos[i] != null) {
					ufos[i].draw(g);  ufos[i].drawPowerups(g);
				}
			}	
		} // Closes ufos
		
		
		// Draws the meteors
		if(meteors != null) {
			for(int i = 0; i <= currentMeteor; i++) {
				if(meteors[i] != null && meteors[i].isAlive) {
					meteors[i].draw(g);
				}
			}	
		} // Closes meteors
	}
	
	//=======================================================================================

}
