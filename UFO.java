import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

public class UFO {
	//UFO is 91 x 91
	
		// Position
		int x;
		int y;
		
		int width = 46;
		int height = 46;
		
		int health = 100;
		
		int bottom;
		int middle;
		
		int speed = 1;
		int currentUFO;
		
		boolean imagesLoaded;
		boolean isAlive = true;
		boolean dying = false;
		
		public static Image[] images;
		
		// Powerups
		public HealthPowerup   powerupHealth = new HealthPowerup(-2000, -2000);
		public ShieldPowerup   powerupShield = new ShieldPowerup(-2000, -2000);
		public int powerupDropRate = 20; // 20 = 10% because there are 2 power ups
		public int randPowerup;
		
		// Explosion kamikaze variables
		public static Image[] explosions;
		public int currentExplosion = 0;
		public int explosionCooldown = 2;
		public boolean explosionFinish = false;
		
		// State variables
		public enum BEHAVIOR{DEFAULT, TELEPORT, KAMIKAZE, MOVEAWAY, EXPLODED};
		public BEHAVIOR behavior = BEHAVIOR.DEFAULT;
		public boolean hasSpawned = false;
		
		// Route variables
		public int targetX;
		public int targetY;
		
		// Teleport variables
		public boolean teleportLeft = true;
		public boolean teleportRight = false;
		public int teleportCooldown = -1;
		public int teleportX;
		public int OGteleportCooldown;
		
		// Randomizer
		Random rand = new Random();
		int randomX;
		int randomY;
		
		//Ammunition
		LaserEnemyShip[] lasers = new LaserEnemyShip[40]; 
		int currentLaser = 0;
		
		// Cool down variables
		long lastShotTime;
		
		int blue    = 0;
		int green   = 1;
		int red     = 2;
		int yellow  = 3;
		
		//======================================================================================
		
		public UFO(int x, int y, String name, String behaviorName) {
			
			this.x = x;
			this.y = y;
			
			bottom = y + height;
			middle = x + width/2;
					
			images = new Image[4];
			explosions = new Image[89];
			
			setBehavior(behaviorName);
			loadImages();
			createLasers();
			getUFO(name);
		}
		
		//=================================================================================
		
		public void update() {
			if(dying) {updateExplosion(); return;}
			if(isAlive) {
				bottom = y + height;
				middle = x + width/2;
			
				updateLasers();
			
				if(!hasSpawned) {move();}
				
				// Automatically sets behavior to kamikaze if health is 20 or below
				if(health <= 30 && !(behavior == BEHAVIOR.EXPLODED)) {behavior = BEHAVIOR.KAMIKAZE;}
			
				if(hasSpawned) {
					if(behavior == BEHAVIOR.DEFAULT) {
						shoot();    
						if(health <= 50) {wiggle();}
					}
					
					else if(behavior == BEHAVIOR.EXPLODED) {updateExplosion();}
					
					else if(behavior == BEHAVIOR.TELEPORT) {
						shoot(); teleport();    
						if(health <= 50) {wiggle();}
					}
					
					else if(behavior == BEHAVIOR.KAMIKAZE) {kamikaze();}
					else if(behavior == BEHAVIOR.MOVEAWAY) {shoot(); moveAway();}
				}
			}
		}
		
		//=================================================================================
		
		public void updateExplosion() {
			explosionCooldown --;
			
			if(explosionCooldown <= 0) {
				
				currentExplosion ++;
				explosionCooldown = 1;
				
				if(currentExplosion >= explosions.length) {
					currentExplosion = 0;
					x = -5000; y = - 5000; 
					explosionFinish = true;
					dying = false;
					
					// Increase the enemy count variable
					GameEngine.level[GameEngine.currLvl].enemiesKilled ++;
				}
			}
		}
		
		//=================================================================================
		
		public void move() {
			// If spawn-point reached, exit spawn state
			if(targetX == 0 && targetY == 0) {hasSpawned = true; return;}
			if(Math.abs(x-targetX) <= 5 && Math.abs(y-targetY) <= 5) {hasSpawned = true; return;}
			
			if(x > targetX) {x -= speed*2;}
			if(x < targetX) {x += speed*2;}
			
			if(y > targetY) {y -= speed*2;}
			if(y < targetY) {y += speed*2;}
		}
		
		//=================================================================================
		
		public void setTargetCoords(int targetX, int targetY) {
			this.targetX = targetX;
			this.targetY = targetY;
		}
		
		//=================================================================================
		
		public void setTeleportVariables(int cooldown, int moveX) {
			teleportCooldown = cooldown;
			teleportX = moveX;
			OGteleportCooldown = cooldown;
		}
		
		//=================================================================================
		
		public void setBehavior(String input) {
			String behaviorName = input.toUpperCase();
			
			if     (behaviorName.equals("DEFAULT"))  {behavior = BEHAVIOR.DEFAULT;}
			else if(behaviorName.equals("TELEPORT")) {behavior = BEHAVIOR.TELEPORT;}
			else if(behaviorName.equals("KAMIKAZE")) {behavior = BEHAVIOR.KAMIKAZE;}
			else if(behaviorName.equals("MOVEAWAY")) {behavior = BEHAVIOR.MOVEAWAY;}
			
			else {behavior = BEHAVIOR.DEFAULT;}
		}
		
		//=================================================================================
		
		public void createLasers() {
			int laserSpeed = 7;

			for(int i = 0; i < lasers.length; i++) {
				lasers[i]   = new LaserEnemyShip(-30000, -30000, laserSpeed);
			}
		}
		
		//=================================================================================
		
		public void updateLasers() {
			
			for(int i = 0; i < lasers.length; i++) {
				
				// If the laser was shot move it
				if(lasers[i].isShooting) {lasers[i].shoot();}
				
				// If enemy laser went off screen, reset its location
				if(lasers[i].wentOffScreen()) {
					lasers[i].isShooting = false;
					lasers[i].x = -30000;
					lasers[i].y = -30000;
				}

				// Checking if laser hit spaceship
				if(lasers[i].withinRange(GameEngine.spaceship) && Spaceship.isAlive) {
					lasers[i].hit(GameEngine.spaceship);
				}
			}
			
		}
		
		//=================================================================================
		
		public void shoot() {
			//if ((Spaceship.x-x >=-250 && Spaceship.x-x <=250) && isAlive) {

				// Tracking the time, only shooting if cool down time has passed
				if(Objects.isNull(lastShotTime)) {lastShotTime = Calendar.getInstance().getTimeInMillis();}
				long currentTime = Calendar.getInstance().getTimeInMillis();

				long difference = Math.abs(currentTime - lastShotTime);

				// coolDownTime is in miliseconds (1000 = 1 second)
				long coolDownTime = 850;

				if(difference >= coolDownTime) {

					// Calculating where the laser should spawn (under enemy ship)
					int laserX = middle;
					int laserY = bottom;

					// Placing the laser under enemy ship
					lasers[currentLaser].x = laserX;
					lasers[currentLaser].y = laserY;

					// Shooting the laser
					lasers[currentLaser].isShooting = true;
					lasers[currentLaser].shoot();

					// Update currentLaser value and lastShotTime
					currentLaser ++;
					if(currentLaser >= lasers.length) {currentLaser = 0;}
					lastShotTime = Calendar.getInstance().getTimeInMillis();
				}
			//}
		}
		
		//=================================================================================
		
		public void wiggle() {
			randomX = rand.nextInt(11) + 1;   randomY = rand.nextInt(11) + 1;
			x += randomX;
			y -= randomY;
			
			if(Math.abs(x-targetX) >= 15 && Math.abs(y-targetY) >= 15) {
				x = targetX; y = targetY;
			}
			
			randomX = rand.nextInt(11) + 1;   randomY = rand.nextInt(11) + 1;
			x -= randomX;
			y+= randomY;
		}
		
		//=================================================================================
		
		public void kamikaze() {
			if(!isAlive) {explosionFinish = true; return;}
			if(isAlive) {
			
				if(Spaceship.x > x)  { x += speed*2;}
				if(Spaceship.x < x)  { x -= speed*2;}
				
				if(bottom < Spaceship.y) { y += speed*2;}
				
				// Prevents UFO from going under spaceship
				if(bottom > Spaceship.y) {y -= 7;}
				
				// If near spaceship
				if(Math.abs(middle-(Spaceship.x)) <= 30 && Math.abs((y+height/2)-Spaceship.y) <= 30) {
					behavior = BEHAVIOR.EXPLODED;
					explode();
				}
			}
		}
		
		//=================================================================================
		
		public void explode() {
			health = 0;
			die();
			Spaceship.health -= 15;
			if(Spaceship.health <= 0) {Spaceship.isAlive = false;}
		}
		
		//=================================================================================
		
		public void teleport() {
			teleportCooldown --;
			
			if(teleportCooldown <= 0) {
				if(teleportRight) {
					x += teleportX;    teleportRight = false;    teleportLeft = true;
					teleportCooldown = OGteleportCooldown;
				}
				else if(teleportLeft) {
					x -= teleportX;    teleportLeft = false;    teleportRight = true;
					teleportCooldown = OGteleportCooldown;
				}
			}
		}
		
		//=================================================================================
		
		public void moveAway() {
			// If the spaceship is close to the UFO
			if(Spaceship.x >= x && Spaceship.x <= x+width) {
				
				// Move away from spaceship depending on its direction
				if     (Spaceship.x < middle) {
					x += (Spaceship.speed - 1);
					if(x > 1200 || x < 5) {
						x = targetX;
					}
				}
				else if(Spaceship.x > middle) {
					x -= (Spaceship.speed - 1);
					if(x > 1200 || x < 5) {
						x = targetX;
					}
				}
			}
		}
		
		//=================================================================================
		
		public void die() {
			isAlive = false;
			dropPowerUp();
			//Deactivate all lasers
			for(int i = 0; i < lasers.length; i++) {
				lasers[i].isShooting = false;
			}
		}
		
		//=================================================================================
		
		public void dropPowerUp() {
			
			// Calculating where the pill should spawn (under enemy ship)
			int midShip   = x + width/2;
			int underShip = y + height;
			
			// Grabbing a random # (chances are 10% to get a powerup 3/30)
			randPowerup = rand.nextInt(powerupDropRate) + 1;
			
			// Health powerup = 1
			if(randPowerup == 1) {
				
				// Placing the pill under enemy ship
				powerupHealth.x = midShip;
				powerupHealth.y = underShip;
				
				// Dropping the pill
				powerupHealth.isActive = true;
				powerupHealth.update();
			}
			
			// Shield powerup = 2
			else if(randPowerup == 2) {
				
				// Placing the pill under enemy ship
				powerupShield.x = midShip;
				powerupShield.y = underShip;
				
				// Dropping the pill
				powerupShield.isActive = true;
				powerupShield.update();
			}
		}
		
		//=================================================================================
		
		public void updatePowerups() {
			
			if(powerupHealth.isActive) {
				powerupHealth.update();
				if(powerupHealth.withinRange(GameEngine.spaceship)) {
					powerupHealth.heal(GameEngine.spaceship);
				}
			}
			
			else if(powerupShield.isActive) {
				powerupShield.update();
				if(powerupShield.withinRange(GameEngine.spaceship)) {
					powerupShield.shieldBoost();
				}
			}
			
		}
		
		//=================================================================================
		
		public void loadImages() {
			String path = ".\\UFO\\";
			
			// Loads all the images into array
			images[blue]    = Toolkit.getDefaultToolkit().getImage(path + "ufoBlue.png");
			images[green]   = Toolkit.getDefaultToolkit().getImage(path + "ufoGreen.png");
			images[red]     = Toolkit.getDefaultToolkit().getImage(path + "ufoRed.png");
			images[yellow]  = Toolkit.getDefaultToolkit().getImage(path + "ufoYellow.png");
			
			path = ".\\UFO\\Explosions\\explosion_";
			for(int i = 0; i < explosions.length; i ++) {
				explosions[i] = Toolkit.getDefaultToolkit().getImage(path + i + ".png");
			}
			
			imagesLoaded = true;
		}
		
		//=================================================================================
		
		public void getUFO(String name) {
				
			if     (name.equals("blue"))      currentUFO = blue;
			else if(name.equals("green"))     currentUFO = green;
			else if(name.equals("red"))       currentUFO = red;
			else if(name.equals("yellow"))    currentUFO = yellow;
				
			else {                            currentUFO = blue;}
				
		}
			
		//=================================================================================
		
		public void drawPowerups(Graphics g) {
			// Draw powerups
			if(powerupHealth.isActive) {powerupHealth.draw(g);}
			if(powerupShield.isActive) {powerupShield.draw(g);}
		}
		
		//=================================================================================
			
		public void draw(Graphics g) {
				
			if(isAlive && imagesLoaded) {
				g.drawImage(images[currentUFO], x, y, null);
			}
			
			if(dying && !explosionFinish) {
				g.drawImage(explosions[currentExplosion], x, y, null);
			}
			
			// Draws the lasers
			for(int i = 0; i < lasers.length; i++) {
				if(lasers[i].isShooting) {lasers[i].draw(g);}
			}
		}
		
		//=================================================================================
		
}
