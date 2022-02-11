import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class HealthPowerup {

	int width  = 22;
	int height = 21;
	
	int x;
	int y;
	int speed = 1;
	
	public boolean isActive = false;
	
	static String path = ".\\PowerUps\\PillBlue.png";
	static Image pillImage = Toolkit.getDefaultToolkit().getImage(path);
	
	
	//=================================================================================
	
	public HealthPowerup(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//=================================================================================
	public void update() {
		y += speed;
		if(wentOffScreen()) {x = -2000;    y = -2000;    isActive = false;}
	}
	
	//=================================================================================

	public void heal(Spaceship spaceship) {	
		spaceship.health = 100;
		isActive = false;
		
		// Reset the pills coordinates so that its off screen
		x = -20000;
		y = -20000;
	}
	//=================================================================================

	public boolean withinRange(Spaceship spaceship) {
		int bluePillLeft  = x;
		int bluePillRight = x + width;

		// Testing blue pill collision
		if((( y + height) >= spaceship.y) && (y <= spaceship.y + spaceship.height) ) {

			//If the pill is to the left of ship
			if(x < spaceship.x) {
				if(bluePillRight >= spaceship.x) {
					return true;
				}
			}
			// If the pill is to the right of ship
			else {
				if(bluePillLeft <= spaceship.x + spaceship.width) {
					return true;
				}
			}
		}
		return false;
	}

	//=================================================================================
	
	public boolean wentOffScreen() {
		if(y > 1500) return true;
		return false;
	}
	
	//=================================================================================
	
	public void draw(Graphics g) {
		g.drawImage(pillImage, x, y, null);
	}
	
	//=================================================================================
}
