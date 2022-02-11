
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Objects;

public class EnemyShip {
	
	// Attributes
	int width, height;
	int health = 100; 

	// Position
	int x;
	int y;
	
	// Useful variables
	public int speed = 1;
	public int currentShip;
	boolean imagesLoaded;
	boolean isAlive = true;
	public static Image[] images;
	
	// Powerups
	public HealthPowerup   powerupHealth = new HealthPowerup(-2000, -2000);
	public ShieldPowerup   powerupShield = new ShieldPowerup(-2000, -2000);
	public int powerupDropRate = 20; // 20 = 10% because there are 2 power ups
	public int randPowerup;
	
	// Randomizer variables
	Random rand = new Random();
	
	// Behavior variables
	public boolean movingLeft = false;
	public boolean movingRight = false;
	public int targetX;
	public int targetY;
	
	// State variables
	public enum BEHAVIOR{DEFAULT, SHOOTER, CHASE, DEFAULT2CHASE, SIDE2SIDE};
	public BEHAVIOR behavior;
	public boolean hasSpawned = false;
	
	// Cool down variables
	long lastShotTime;
	// coolDownTime in miliseconds (1000 = 1 second)
	long coolDownTime = 1250; 
	
	//Ammunition
	LaserEnemyShip[] lasers = new LaserEnemyShip[20]; 
	int currentLaser = 0;

	// Dimensions for every type of ship
	int width1 = 47;   int width2 = 52;   int width3 = 52;   int width4 = 41;   int width5 = 49;
	int height1 = 42;  int height2 = 42;  int height3 = 42;  int height4 = 42;  int height5 = 42;

	// Spaceship types
	int black1    = 0;    int blue1     = 5;    int green1    = 10;    int red1      = 15;
	int black2    = 1;    int blue2     = 6;    int green2    = 11;    int red2      = 16;
	int black3    = 2;    int blue3     = 7;    int green3    = 12;    int red3      = 17;
	int black4    = 3;    int blue4     = 8;    int green4    = 13;    int red4      = 18;
	int black5    = 4;    int blue5     = 9;    int green5    = 14;    int red5      = 19;
	
	
	//=================================================================================

	public EnemyShip(int x, int y, String name, String behaviorName) {
		this.x = x;
		this.y = y;
		
		images = new Image[20];
		loadImages();
		getShip(name);
		createLasers();
		setBehavior(behaviorName);
	}

	//================================================================================
	
	public void update() {
		if(isAlive) {
			
			updatePowerups();
			updateLasers();
			
			if (health <= 0) {die();}
			
			if(!hasSpawned) {move();}
			else if (hasSpawned){
				if     (behavior == BEHAVIOR.DEFAULT)      {shoot();}
				else if(behavior == BEHAVIOR.SHOOTER)      {shootAlways();}
				else if(behavior == BEHAVIOR.CHASE)        {chase(); shoot();}
				else if(behavior == BEHAVIOR.DEFAULT2CHASE){ if(health<=40) {chase();} shoot(); }
				else if(behavior == BEHAVIOR.SIDE2SIDE)    {side2side(); shootAlways();}
			}
		}
	}
	
	//================================================================================
	
	public void setBehavior(String input) {
		
		String behaviorName = input.toUpperCase();
		
		if     (behaviorName.equals("DEFAULT"))        {behavior = BEHAVIOR.DEFAULT;}
		else if(behaviorName.equals("SHOOTER"))        {behavior = BEHAVIOR.SHOOTER;}
		else if(behaviorName.equals("CHASE"))          {behavior = BEHAVIOR.CHASE;}
		else if(behaviorName.equals("DEFAULT2CHASE"))  {behavior = BEHAVIOR.DEFAULT2CHASE;}
		else if(behaviorName.equals("SIDE2SIDE"))      {behavior = BEHAVIOR.SIDE2SIDE;}
		
		else {behavior = BEHAVIOR.SHOOTER;}
	}
	
	//================================================================================

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	//================================================================================
	
	public void setTargetCoords(int targetX, int targetY) {
		this.targetX = targetX;
		this.targetY = targetY;
	}
	
	//================================================================================

	public void move() {
		// If spawn-point reached, exit spawn state
		if(targetX == 0 && targetY == 0) {hasSpawned = true; return;}
		if(Math.abs(x-targetX) <= 5 && Math.abs(y-targetY) <= 5) {hasSpawned = true; return;}
		
		if(x > targetX) {x -= speed*3;}
		if(x < targetX) {x += speed*3;}
		
		if(y > targetY) {y -= speed*2;}
		if(y < targetY) {y += speed*2;}
	}

	//=================================================================================
	
	public void die() {
		isAlive = false;
		dropPowerUp();
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

	public void shoot() {
		if ((Spaceship.x-x >=-250 && Spaceship.x-x <=250) && isAlive) {

			// Tracking the time, only shooting if cool down time has passed
			if(Objects.isNull(lastShotTime)) {lastShotTime = Calendar.getInstance().getTimeInMillis();}
			long currentTime = Calendar.getInstance().getTimeInMillis();

			long difference = Math.abs(currentTime - lastShotTime);

			if(difference >= coolDownTime) {

				// Calculating where the laser should spawn (under enemy ship)
				int laserX = x + width/2;
				int laserY = y + height;

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
		}
	}


	//=================================================================================
	
	public void shootAlways() {

			// Tracking the time, only shooting if cool down time has passed
			if(Objects.isNull(lastShotTime)) {lastShotTime = Calendar.getInstance().getTimeInMillis();}
			long currentTime = Calendar.getInstance().getTimeInMillis();

			long difference = Math.abs(currentTime - lastShotTime);
			
			if(difference >= coolDownTime) {

				// Calculating where the laser should spawn (under enemy ship)
				int laserX = x + width/2;
				int laserY = y + height;

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
	}


	//=================================================================================

	public void loadImages() {
		String path = ".\\EnemyImages\\";
		int count = 1;

		// Loads all the images into array
		for(int i = 0; i < 5; i ++) {
			images[i] = Toolkit.getDefaultToolkit().getImage(path + "black" + count + ".png");
			count ++;
		}
		count = 1;
		for(int i = 5; i < 10; i ++) {
			images[i] = Toolkit.getDefaultToolkit().getImage(path + "blue" + count + ".png");
			count ++;
		}
		count = 1;
		for(int i = 10; i < 15; i ++) {
			images[i] = Toolkit.getDefaultToolkit().getImage(path + "green" + count + ".png");
			count ++;
		}
		count = 1;
		for(int i = 15; i < 20; i ++) {
			images[i] = Toolkit.getDefaultToolkit().getImage(path + "red" + count + ".png");
			count ++;
		}
		imagesLoaded = true;
	}

	//=================================================================================

	public void getShip(String name) {

		if     (name.equals("black1")) {currentShip = black1; width=width1; height=height1;}
		else if(name.equals("black2")) {currentShip = black2; width=width2; height=height2;}
		else if(name.equals("black3")) {currentShip = black3; width=width3; height=height3;}
		else if(name.equals("black4")) {currentShip = black4; width=width4; height=height4;}
		else if(name.equals("black5")) {currentShip = black5; width=width5; height=height5;}

		else if(name.equals("blue1")) {currentShip = blue1; width=width1; height=height1;}
		else if(name.equals("blue2")) {currentShip = blue2; width=width2; height=height2;}
		else if(name.equals("blue3")) {currentShip = blue3; width=width3; height=height3;}
		else if(name.equals("blue4")) {currentShip = blue4; width=width4; height=height4;}
		else if(name.equals("blue5")) {currentShip = blue5; width=width5; height=height5;}

		else if(name.equals("green1")) {currentShip = green1; width=width1; height=height1;}
		else if(name.equals("green2")) {currentShip = green2; width=width2; height=height2;}
		else if(name.equals("green3")) {currentShip = green3; width=width3; height=height3;}
		else if(name.equals("green4")) {currentShip = green4; width=width4; height=height4;}
		else if(name.equals("green5")) {currentShip = green5; width=width5; height=height5;}

		else if(name.equals("red1")) {currentShip = red1; width=width1; height=height1;}
		else if(name.equals("red2")) {currentShip = red2; width=width2; height=height2;}
		else if(name.equals("red3")) {currentShip = red3; width=width3; height=height3;}
		else if(name.equals("red4")) {currentShip = red4; width=width4; height=height4;}
		else if(name.equals("red5")) {currentShip = red5; width=width5; height=height5;}

		else {currentShip = black1; width=width1; height=height1;}

	}

	//=================================================================================

	public void createLasers() {
		int laserSpeed = 6;

		for(int i = 0; i < lasers.length; i++) {
			lasers[i]   = new LaserEnemyShip(-30000, -30000, laserSpeed);
		}
	}
	
	//===========================================================================================
	
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
	
	//========================================================================================
	
	public void chase() {
			if(Spaceship.x > x)  { x += speed;}
			if(Spaceship.x < x)  { x -= speed;}
			if(x <= Spaceship.x + Spaceship.width || x >= Spaceship.x + Spaceship.width) {
				shoot();
			}
			
			if(Spaceship.y - Spaceship.height - 100 > y) { y += speed;}
			if(Spaceship.y - Spaceship.height - 100 < y) { y -= speed;}
	}
	
	//========================================================================================
	
	public void side2side() {
		if(movingLeft) {
			if(x > 50) { x -= speed*2; shoot();}
			else if(x <= 50) {movingLeft = false; movingRight = true;}
		}
		else if(movingRight) {
			if(x < 1200) { x += speed*2; shoot();}
			else if(x >= 1200) {movingRight = false; movingLeft = true;}
		}
	}
	//========================================================================================
	
	public void drawPowerups(Graphics g) {
		// Draw powerups
		if(powerupHealth.isActive) {powerupHealth.draw(g);}
		if(powerupShield.isActive) {powerupShield.draw(g);}
	}
	
	//========================================================================================
	
	public void draw(Graphics g) {

		if(imagesLoaded) {
			g.drawImage(images[currentShip], (int)x, (int)y, null);
		}
		
		// Draws the lasers
		for(int i = 0; i < lasers.length; i++) {
			if(lasers[i].isShooting) {lasers[i].draw(g);}
		}

	}
}