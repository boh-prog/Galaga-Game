import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Meteor {
	
	// Attributes
    public int width;
    public int height;
	public int x;
	public int y;
	
	// Stats
	public int health;
	public int speed = 2;
	public int damage;
	
	// Misc variables
	public int currentMeteor;
	public boolean imagesLoaded;
	public boolean isAlive = true;
	
	public static Image[] images;
	
	// Route variables
	public int targetX;
	public int targetY;
	
	// Spaceship types
	public int width1 = 51;  public int height1 = 42;
	public int width2 = 60;  public int height2 = 49;
	public int width3 = 45;  public int height3 = 41;
	public int width4 = 49;  public int height4 = 48;
	public int width5 = 22;  public int height5 = 22;
	public int width6 = 23;  public int height6 = 20;
	
	
	
	
	//=================================================================================
	
	public Meteor(int x, int y, String name) {
		this.x = x;
		this.y = y;
		
		images = new Image[6];
		
		loadImages();
		getMeteor(name);
	}
	
	//================================================================================
	
	public void update() {
		if(isAlive) {
			move();
			checkCollision();
		}
	}
	
	//================================================================================
	
	public void setTargetCoords(int targetX, int targetY) {
		this.targetX = targetX;
		this.targetY = targetY;
	}
	
	//================================================================================
	
	public void move() {
		// If target variables are null or meteor reached its destination then exit method
		if(targetX == 0 && targetY == 0) {return;}
		if(Math.abs(x-targetX) <= 5 && Math.abs(y-targetY) <= 5) {return;}
		
		if(x > targetX) {x -= speed;}
		if(x < targetX) {x += speed;}
		
		if(y > targetY) {y -= speed;}
		if(y < targetY) {y += speed;}
	}
	
	//================================================================================
	
	public void checkCollision() {
		if(y + height >= Spaceship.y && y + height <= Spaceship.y + Spaceship.height) {
			
			if(x + width >= Spaceship.x && x + width <= Spaceship.x + Spaceship.width) {
				die();
				Spaceship.health -= damage;
				if(Spaceship.health <= 0) {Spaceship.isAlive = false;}
			}
		}
	}
	
	//================================================================================
	
	
	public void die() {
		isAlive = false;
	}
	
	//================================================================================
	
	public void loadImages() {
		String path = ".\\Meteors\\";
		
		// Loads all the images into array
		images[0] = Toolkit.getDefaultToolkit().getImage(path+ "meteorBig1.png");
		images[1] = Toolkit.getDefaultToolkit().getImage(path+ "meteorBig2.png");
		images[2] = Toolkit.getDefaultToolkit().getImage(path+ "meteorBig3.png");
		images[3] = Toolkit.getDefaultToolkit().getImage(path+ "meteorBig4.png");
		images[4] = Toolkit.getDefaultToolkit().getImage(path+ "meteorMed1.png");
		images[5] = Toolkit.getDefaultToolkit().getImage(path+ "meteorMed2.png");
		
		imagesLoaded = true;
	}
	
	//================================================================================
	
	public void getMeteor(String name) {
		
		String meteorName = name.toUpperCase();
		
		if(meteorName.equals("BIG1"))      {currentMeteor = 0; width = width1; height = height1; damage = 20;}
		else if(meteorName.equals("BIG2")) {currentMeteor = 1; width = width2; height = height2; damage = 20;}
		else if(meteorName.equals("BIG3")) {currentMeteor = 2; width = width3; height = height3; damage = 20;}
		else if(meteorName.equals("BIG4")) {currentMeteor = 3; width = width4; height = height4; damage = 20;}
		else if(meteorName.equals("MED1")) {currentMeteor = 4; width = width5; height = height5; damage = 10;}
		else if(meteorName.equals("MED2")) {currentMeteor = 5; width = width6; height = height6; damage = 10;}
		
		else                               {currentMeteor = 0; width = width1; height = height1; damage = 35;}
	}
	
	//================================================================================
	
	public void draw(Graphics g) {
		g.drawImage(images[currentMeteor], x, y, null);
	}
	
	//================================================================================
	
}
