import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Objects;

public class Spaceship {
	
	AudioHandler laserSound = new AudioHandler();

	// Attributes
    public static int width;
    public static int height;
	public static int x;
	public static int y;
	public static int speed = 5;
	
	// Screen Resolution
	int screenWidth = 1440;
	int screenHeight = 900;
	
	//Ammunition
	Laser[] lasers = new Laser[20];
	int currentLaser = 0;
	
	// Health related variables
	public static int health = 100;
	public static Image[] healthbar;
	public int healthIndex= 15;
	
	// Useful variables
	long lastShotTime;
	static int currentShip;
	static int currentDmg;
	boolean imagesLoaded;
	public static Image[] images;
	public static Image[] damages;
	static boolean isAlive = true;
	
    //Shield powerUp
    public static int shield = 25;
    public static boolean isShielded = false;
	public static Image[] shields = new Image[3];
	public int shieldIndex = 0;
	
	// Dimensions for every type of ship
	int width1 = 50;   int width2 = 56;   int width3 = 50;
	int height1 = 38;  int height2 = 38;  int height3 = 38;
	
	// Spaceship types
	int blue1 = 0;    int green1 = 3;    int orange1 = 6;    int red1 = 9;
	int blue2 = 1;    int green2 = 4;    int orange2 = 7;    int red2 = 10;
	int blue3 = 2;    int green3 = 5;    int orange3 = 8;    int red3 = 11;
	
	//=================================================================================
	
	public Spaceship(int x, int y, String name) {
		this.x = x;
		this.y = y;
		
		images = new Image[12];
		damages= new Image[9];
		healthbar = new Image[16];
		
		loadImages();
		
		getShip(name);
		createLasers();
	}
	
	//================================================================================
	
	public void moveUp() {
		if(y - speed > 0) {
			y -= speed;
		}
	}
	
	//=================================================================================
	
	public void moveDown() {
		if(y + 4*height + speed < screenHeight) {
			y += speed;
		}
	}
	
	//=================================================================================
	
	public void moveLeft() {
		x -= speed;
		teleport();
	}
	
	//=================================================================================
	
	public void moveRight() {
		x += speed;
		teleport();
	}
	
	//=================================================================================
	
	public void shoot() {
		if (isAlive) {
		// Tracking the time, only shooting if cool down time has passed
		if(Objects.isNull(lastShotTime)) {lastShotTime = Calendar.getInstance().getTimeInMillis();}
		long currentTime = Calendar.getInstance().getTimeInMillis();
		
		long difference = Math.abs(currentTime - lastShotTime);
		
		// coolDownTime 250 = 1/4th of a second
		long coolDownTime = 250;
		
		if(difference >= coolDownTime) {
			
			// Calculating where the laser should spawn (on top of ship)
			int laserX = x + width / 2;
			int laserY = y - height;
			
			// Placing the laser on top of ship
			lasers[currentLaser].x = laserX;
			lasers[currentLaser].y = laserY;
		
			// Shooting the laser
			lasers[currentLaser].isShooting = true;
			lasers[currentLaser].shoot();
		
			// Update currentLaser value and lastShotTime
			currentLaser ++;
			laserSound.shootLaser();
		
			if(currentLaser >= lasers.length) {currentLaser = 0;}
			lastShotTime = Calendar.getInstance().getTimeInMillis();
		}
	}
	}
	//=================================================================================
	
	public void loadImages() {
		String path = ".\\SpaceshipImages\\";
		
		// Loads all the images into array
		images[blue1] = Toolkit.getDefaultToolkit().getImage(path+ "blue1.png");
		images[blue2] = Toolkit.getDefaultToolkit().getImage(path+ "blue2.png");
		images[blue3] = Toolkit.getDefaultToolkit().getImage(path+ "blue3.png");
		
		images[green1] = Toolkit.getDefaultToolkit().getImage(path+ "green1.png");
		images[green2] = Toolkit.getDefaultToolkit().getImage(path+ "green2.png");
		images[green3] = Toolkit.getDefaultToolkit().getImage(path+ "green3.png");
		
		images[orange1] = Toolkit.getDefaultToolkit().getImage(path+ "orange1.png");
		images[orange2] = Toolkit.getDefaultToolkit().getImage(path+ "orange2.png");
		images[orange3] = Toolkit.getDefaultToolkit().getImage(path+ "orange3.png");
		
		images[red1] = Toolkit.getDefaultToolkit().getImage(path+ "red1.png");
		images[red2] = Toolkit.getDefaultToolkit().getImage(path+ "red2.png");
		images[red3] = Toolkit.getDefaultToolkit().getImage(path+ "red3.png");
		
		path = ".\\ShipDamage\\";
		
		damages[0] = Toolkit.getDefaultToolkit().getImage(path+ "ship1_dmg1.png");
		damages[1] = Toolkit.getDefaultToolkit().getImage(path+ "ship1_dmg2.png");
		damages[2] = Toolkit.getDefaultToolkit().getImage(path+ "ship1_dmg3.png");
		damages[3] = Toolkit.getDefaultToolkit().getImage(path+ "ship2_dmg1.png");
		damages[4] = Toolkit.getDefaultToolkit().getImage(path+ "ship2_dmg2.png");
		damages[5] = Toolkit.getDefaultToolkit().getImage(path+ "ship2_dmg3.png");
		damages[6] = Toolkit.getDefaultToolkit().getImage(path+ "ship3_dmg1.png");
		damages[7] = Toolkit.getDefaultToolkit().getImage(path+ "ship3_dmg2.png");
		damages[8] = Toolkit.getDefaultToolkit().getImage(path+ "ship3_dmg3.png");

		path = ".\\HealthBar\\";
		for(int i =0; i<healthbar.length;i++) {
			healthbar[i] = Toolkit.getDefaultToolkit().getImage(path+ "Health"+i+".png");
		}
		
		path = ".\\PowerUps\\";
		shields[0] = Toolkit.getDefaultToolkit().getImage(path+ "Shield1.png");
		shields[1] = Toolkit.getDefaultToolkit().getImage(path+ "Shield2.png");
		shields[2] = Toolkit.getDefaultToolkit().getImage(path+ "Shield3.png");
		
		imagesLoaded = true;
	}
	
	//=================================================================================

	public void getShip(String name) {

		if     (name.equals("blue1"))   {currentShip = blue1;   width = width1; height = height1; currentDmg=0;}
		else if(name.equals("blue2"))   {currentShip = blue2;   width = width2; height = height2; currentDmg=3;}
		else if(name.equals("blue3"))   {currentShip = blue3;   width = width3; height = height3; currentDmg=6;}
		else if(name.equals("green1"))  {currentShip = green1;  width = width1; height = height1; currentDmg=0;}
		else if(name.equals("green2"))  {currentShip = green2;  width = width2; height = height2; currentDmg=3;}
		else if(name.equals("green3"))  {currentShip = green3;  width = width3; height = height3; currentDmg=6;}
		else if(name.equals("orange1")) {currentShip = orange1; width = width1; height = height1; currentDmg=0;}
		else if(name.equals("orange2")) {currentShip = orange2; width = width2; height = height2; currentDmg=3;}
		else if(name.equals("orange3")) {currentShip = orange3; width = width3; height = height3; currentDmg=6;}
		else if(name.equals("red1"))    {currentShip = red1;    width = width1; height = height1; currentDmg=0;}
		else if(name.equals("red2"))    {currentShip = red2;    width = width2; height = height2; currentDmg=3;}
		else if(name.equals("red3"))    {currentShip = red3;    width = width3; height = height3; currentDmg=6;}

		else { currentShip = blue1; width = width1; height = height1;}
	}
	
	//=================================================================================
	
	public void createLasers() {
		for(int i = 0; i < lasers.length; i++) {
			lasers[i] = new Laser(-20000, -20000, 5);
		}
	}
	
	//=================================================================================
	
	public void resetLasers() {
		for(int i = 0; i < lasers.length; i++) {
			lasers[i].isShooting = false;
			lasers[i].x = -20000;
			lasers[i].y = -20000;
		}
	}
	
	//=================================================================================
	
	public void updateLasers() {
		int currLvl = GameEngine.currLvl;
		
		// Extracting enemy ships from other classes
		EnemyShip[] enemies = GameEngine.level[currLvl].enemies;
		int currentEnemy = GameEngine.level[currLvl].currentEnemy;
		
		// Extracting UFO's from other classes
		UFO[] ufos = GameEngine.level[currLvl].ufos;
		int currentUFO = GameEngine.level[currLvl].currentUFO;
		
		// Extracting meteor's from other classes
		Meteor[] meteors = GameEngine.level[currLvl].meteors;
		int currentMeteor = GameEngine.level[currLvl].currentMeteor;
		
		
		
		// Loop updates the lasers
		for(int i = 0; i < lasers.length; i++) {
			
			// If the laser was shot, place it on top of spaceship
			if(lasers[i].isShooting) {
				lasers[i].shoot();
			}
			
			// If laser went off screen, reset its location
			if(lasers[i].wentOffScreen()) {
				lasers[i].isShooting = false;
				lasers[i].x = -20000;
				lasers[i].y = -20000;
			}
			
			//Checking if laser hit enemy ship
			if(enemies != null && currentEnemy > 0) {
				for(int j = 0; j <= currentEnemy; j ++) {
					if(enemies[j] != null && lasers[i].withinRange(enemies[j]) && enemies[j].isAlive) {
						lasers[i].hit(enemies[j]);
					}
				}
			}// Closes check for enemy ships
			
			//Checking if laser hit UFO
			if(ufos != null && currentUFO > 0) {
				for(int j = 0; j <= currentUFO; j ++) {
					if(ufos[j] != null && lasers[i].withinRange(ufos[j]) && ufos[j].isAlive) {
						lasers[i].hit(ufos[j]);
					}
				}
			}// Closes check for ufo
			
			//Checking if laser hit Meteor
			if(meteors != null && currentMeteor > 0) {
				for(int j = 0; j <= currentMeteor; j ++) {
					if(meteors[j] != null && lasers[i].withinRange(meteors[j]) && meteors[j].isAlive) {
						lasers[i].hit(meteors[j]);
					}
				}
			}// Closes check for meteor
		}
	}
	
	//=================================================================================
	
	public void teleport() {
		// If spaceship goes all the way to the left
		if(x + width < 0) {
			x = screenWidth - width / 2;
		}
		// If spaceship goes all the way to the right
		if(x > screenWidth) {
			x = -width / 2;
		}
	}
	
	//==================================================================================
	
	public void draw(Graphics g) {
		
		if(imagesLoaded) {
			g.drawImage(images[currentShip], (int)x, (int)y, null);
			
			// Health indicator
			if (health >= 50)      { g.setColor(Color.GREEN); }
			else if (health > 25) { g.setColor(Color.YELLOW); }

			else if (health <= 25) { g.setColor(Color.RED); }
			g.setFont(new Font("Avenir", Font.PLAIN,20));   //FIND PERFECT FONT
					//Font.ITALIC,20));
			
			g.drawString("Health : "+ health + " / 100", 1250, 20);
		}
		
		// Draws spaceship lasers
		for(int i = 0; i < lasers.length; i ++) {
			if(lasers[i].isShooting) {lasers[i].draw(g);}
		}
		
		if(health > 50 && health <=75 ) {g.drawImage(damages[currentDmg], (int)x, (int)y, null);}
		else if(health > 25 && health <=50 ) {g.drawImage(damages[currentDmg+1], (int)x, (int)y, null);}
		else if(health >= 0 && health <=25 ) {g.drawImage(damages[currentDmg+2], (int)x, (int)y, null);}


		// Healthbar
		     if (health >= 94) healthIndex =15;
		else if (health >= 88) healthIndex =14;
		else if (health >= 82) healthIndex =13;
		else if (health >= 76) healthIndex =12;
		else if (health >= 70) healthIndex =11;
		else if (health >= 64) healthIndex =10;
		else if (health >= 58) healthIndex = 9;
		else if (health >= 52) healthIndex = 8;
		else if (health >= 46) healthIndex = 7;
		else if (health >= 40) healthIndex = 6;
		else if (health >= 34) healthIndex = 5;
		else if (health >= 28) healthIndex = 4;
		else if (health >= 22) healthIndex = 3;
		else if (health >= 16) healthIndex = 2;
		else if (health >= 10) healthIndex = 1;
		else                   healthIndex  = 0;

		g.drawImage(healthbar[healthIndex],0,0,null);
		
		//shield Images
		if (isShielded) {
		if (shield >= 70)  shieldIndex =0;
		else if (shield >= 40)  shieldIndex =1;
		else shieldIndex = 2;
		
		// Draws shield
		g.drawImage(shields[shieldIndex],x -11 ,y-15 , null);}
		
		// Draws shield indicator for shield health
		if (isShielded) {
			if (shield > 10) {g.setColor(Color.GREEN);}
			else if (shield <= 10) {g.setColor(Color.RED);}
			g.drawString("Shield HP: "+ shield + "", 360, 22);
		}
	}
	
	//==================================================================================
}
