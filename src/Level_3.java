import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Level_3 extends Level {
	
	//=======================================================================================
	
	public Level_3() {
		background = Toolkit.getDefaultToolkit().getImage(".\\Background\\level3.png");
		enemiesInLevel = 34;
		enemies = new EnemyShip[30];
		ufos = new UFO[30];
		meteors = new Meteor[300];
		outroText = "Game Complete!";
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
			
			enemies[currentEnemy] = new EnemyShip(-50, 100, "green4", "shooter");
			enemies[currentEnemy].setTargetCoords(900, 100);
			enemies[currentEnemy].coolDownTime = 700;
			
			enemies[currentEnemy+1] = new EnemyShip(-300, 100, "green4", "shooter");
			enemies[currentEnemy+1].setTargetCoords(300, 100);
			enemies[currentEnemy+1].coolDownTime = 700;
			currentEnemy ++;
			
			ufos[currentUFO] = new UFO(1900, 200,"green", "teleport");
			ufos[currentUFO].setTeleportVariables(75, 133);
			ufos[currentUFO].setTargetCoords(200, 200);
			
			ufos[currentUFO+1] = new UFO(1950, 200,"green", "teleport");
			ufos[currentUFO+1].setTeleportVariables(90, 77);
			ufos[currentUFO+1].setTargetCoords(400, 200);
			currentUFO ++;
			
			ufos[currentUFO+1] = new UFO(2000, 200,"green", "teleport");
			ufos[currentUFO+1].setTeleportVariables(100, 150);
			ufos[currentUFO+1].setTargetCoords(600, 200);
			currentUFO ++;
			
			ufos[currentUFO+1] = new UFO(2100, 200,"green", "teleport");
			ufos[currentUFO+1].setTeleportVariables(40, 250);
			ufos[currentUFO+1].setTargetCoords(800, 200);
			currentUFO ++;
			
			ufos[currentUFO+1] = new UFO(2150, 200,"green", "teleport");
			ufos[currentUFO+1].setTeleportVariables(56, 200);
			ufos[currentUFO+1].setTargetCoords(1000, 200);
			currentUFO ++;
			
			ufos[currentUFO+1] = new UFO(2200, 200,"green", "teleport");
			ufos[currentUFO+1].setTeleportVariables(84, 500);
			ufos[currentUFO+1].setTargetCoords(1200, 200);
			currentUFO ++;
			
			waveNum ++;
		}// Closes out wavenum == 1
		
		
		
		if(waveNum == 2 && enemiesKilled >= 8) {
			
			//Spawns 70 meteors
			for(int i = 0; i < 70; i ++) {
				if(i == 0) {
					meteors[i] = new Meteor(i*150,-50,"big1");
					meteors[i].setTargetCoords(i*150, 800);
				}
				if(i < 10) {
					meteors[i] = new Meteor(i*150,-50,"big2");
					meteors[i].setTargetCoords(i*150, 800);
					currentMeteor ++;
				}
				else if(i < 20) {
					meteors[i] = new Meteor((i-10)*200,-700,"big3");
					meteors[i].setTargetCoords((i-10)*200, 800);
					currentMeteor ++;
				}
				else if(i < 30) {
					meteors[i] = new Meteor((i-20)*150,-1200,"big4");
					meteors[i].setTargetCoords((i-20)*150, 800);
					currentMeteor ++;
				}
				else if(i < 40) {
					meteors[i] = new Meteor((i-30)*200,-1700,"big4");
					meteors[i].setTargetCoords((i-30)*200, 800);
					currentMeteor ++;
				}
				else if(i < 50) {
					meteors[i] = new Meteor((i-40)*150,-2200,"big4");
					meteors[i].setTargetCoords((i-40)*150, 800);
					currentMeteor ++;
				}
				else if(i < 60) {
					meteors[i] = new Meteor((i-50)*200,-2700,"big4");
					meteors[i].setTargetCoords((i-50)*200, 800);
					currentMeteor ++;
				}
				else if(i < 70) {
					meteors[i] = new Meteor((i-60)*150,-3200,"big4");
					meteors[i].setTargetCoords((i-60)*150, 800);
					currentMeteor ++;
				}
			}// Closes for loop that spawns meteors
			
			enemies[currentEnemy+1] = new EnemyShip(-50, 50, "red2", "side2side");
			enemies[currentEnemy+1].movingRight = true;
			enemies[currentEnemy+1].setTargetCoords(100, 50);
			enemies[currentEnemy+1].coolDownTime = 350;
			enemies[currentEnemy+1].health = 300;
			enemies[currentEnemy+1].speed = 2;
			enemies[currentEnemy+1].powerupDropRate = 8;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(1450, 100, "red2", "side2side");
			enemies[currentEnemy+1].movingLeft = true;
			enemies[currentEnemy+1].setTargetCoords(1180, 100);
			enemies[currentEnemy+1].coolDownTime = 275;
			enemies[currentEnemy+1].health = 300;
			enemies[currentEnemy+1].speed = 2;
			enemies[currentEnemy+1].powerupDropRate = 8;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(100, -4000, "red1", "chase");
			enemies[currentEnemy+1].setTargetCoords(100, 225);
			enemies[currentEnemy+1].health = 200;
			enemies[currentEnemy+1].powerupDropRate = 4;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(1200, -4000, "red1", "chase");
			enemies[currentEnemy+1].setTargetCoords(1200, 225);
			enemies[currentEnemy+1].health = 200;
			enemies[currentEnemy+1].powerupDropRate = 4;
			currentEnemy ++;
			
			waveNum ++;
		}// Closes out wavenum == 2
		
		
		
		if(waveNum == 3 && enemiesKilled >= 12) {
			
			ufos[currentUFO+1] = new UFO(200, -50,"yellow", "moveaway");
			ufos[currentUFO+1].setTargetCoords(200, 355);
			ufos[currentUFO+1].powerupDropRate = 4;
			currentUFO ++;
			
			ufos[currentUFO+1] = new UFO(500, -500,"yellow", "moveaway");
			ufos[currentUFO+1].setTargetCoords(500, 355);
			ufos[currentUFO+1].powerupDropRate = 4;
			currentUFO ++;
			
			ufos[currentUFO+1] = new UFO(800, -500,"yellow", "moveaway");
			ufos[currentUFO+1].setTargetCoords(800, 355);
			ufos[currentUFO+1].powerupDropRate = 4;
			currentUFO ++;
			
			ufos[currentUFO+1] = new UFO(1100, -50,"yellow", "moveaway");
			ufos[currentUFO+1].setTargetCoords(1100, 355);
			ufos[currentUFO+1].powerupDropRate = 4;
			currentUFO ++;
			
			waveNum ++;
		}// Closes out wavenum == 3
		
		
		
		if(waveNum == 4 && enemiesKilled >= 16) {
			
			enemies[currentEnemy+1] = new EnemyShip(1200, -200, "black4", "chase");
			enemies[currentEnemy+1].setTargetCoords(0, 300);
			enemies[currentEnemy+1].health = 200;
			enemies[currentEnemy+1].powerupDropRate = 4;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(0, -200, "black4", "chase");
			enemies[currentEnemy+1].setTargetCoords(1300, 300);
			enemies[currentEnemy+1].health = 200;
			enemies[currentEnemy+1].powerupDropRate = 4;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(1300, -300, "black4", "chase");
			enemies[currentEnemy+1].setTargetCoords(0, 150);
			enemies[currentEnemy+1].health = 200;
			enemies[currentEnemy+1].speed = 2;
			enemies[currentEnemy+1].powerupDropRate = 4;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(0, -300, "black4", "chase");
			enemies[currentEnemy+1].setTargetCoords(1300, 150);
			enemies[currentEnemy+1].health = 200;
			enemies[currentEnemy+1].speed = 2;
			enemies[currentEnemy+1].powerupDropRate = 4;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(1400, 900, "black4", "chase");
			enemies[currentEnemy+1].setTargetCoords(70, 10);
			enemies[currentEnemy+1].health = 200;
			enemies[currentEnemy+1].powerupDropRate = 4;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(0, 900, "black4", "chase");
			enemies[currentEnemy+1].setTargetCoords(900, 10);
			enemies[currentEnemy+1].health = 200;
			enemies[currentEnemy+1].powerupDropRate = 4;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(1200, 1100, "black4", "chase");
			enemies[currentEnemy+1].setTargetCoords(70, 90);
			enemies[currentEnemy+1].health = 200;
			enemies[currentEnemy+1].speed = 2;
			enemies[currentEnemy+1].powerupDropRate = 4;
			currentEnemy ++;
			
			enemies[currentEnemy+1] = new EnemyShip(0, 1100, "black4", "chase");
			enemies[currentEnemy+1].setTargetCoords(1200, 90);
			enemies[currentEnemy+1].health = 200;
			enemies[currentEnemy+1].speed = 2;
			enemies[currentEnemy+1].powerupDropRate = 4;
			currentEnemy ++;
			
			ufos[currentUFO+1] = new UFO(9100, -9000,"red", "teleport");
			ufos[currentUFO+1].setTargetCoords(100, 100);
			ufos[currentUFO+1].setTeleportVariables(55, 222);
			ufos[currentUFO+1].powerupDropRate = 2;
			currentUFO ++;
			
			ufos[currentUFO+1] = new UFO(-8100, -9000,"red", "teleport");
			ufos[currentUFO+1].setTargetCoords(900, 100);
			ufos[currentUFO+1].setTeleportVariables(44, 150);
			ufos[currentUFO+1].powerupDropRate = 8;
			currentUFO ++;
			
			ufos[currentUFO+1] = new UFO(11100, -11000,"red", "teleport");
			ufos[currentUFO+1].setTargetCoords(100, 370);
			ufos[currentUFO+1].setTeleportVariables(62, 80);
			ufos[currentUFO+1].powerupDropRate = 4;
			currentUFO ++;
			
			ufos[currentUFO+1] = new UFO(-11100, -11000,"red", "teleport");
			ufos[currentUFO+1].setTargetCoords(900, 370);
			ufos[currentUFO+1].setTeleportVariables(37, 330);
			ufos[currentUFO+1].powerupDropRate = 6;
			currentUFO ++;
			
			waveNum ++;
		}// Closes out wavenum == 4
		
		if(waveNum == 5 && enemiesKilled >= 28) {
			
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