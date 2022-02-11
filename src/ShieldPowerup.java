import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Objects;

public class ShieldPowerup {
	
	int width  = 22;
	int height = 21;
	
	int x;
	int y;
	int speed = 1;
	
	public boolean isActive = false;
	
	static String path = ".\\PowerUps\\PillYellow.png";
	static Image pillImage = Toolkit.getDefaultToolkit().getImage(path);
	
	
	//=================================================================================
	
	public ShieldPowerup(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//=================================================================================
	public void update() {
		y += speed;
		if(wentOffScreen()) {x = -2000;    y = -2000;    isActive = false;}
	}
	
	//=================================================================================
	
	public void shieldBoost() {	
		
		Spaceship.shield = 25;
		Spaceship.isShielded = true;
		isActive = false;
		
		// Reset the pills coordinates so that its off screen
		x = -20000;
		y = -20000;
	}
	//=================================================================================

	public boolean withinRange(Spaceship spaceship) {
		int yellowPillLeft  = x;
		int yellowPillRight = x + width;

		// Testing blue pill collision
		if((( y + height) >= spaceship.y) && (y <= spaceship.y + spaceship.height) ) {

			//If the pill is to the left of ship
			if(x < spaceship.x) {
				if(yellowPillRight >= spaceship.x) {
					return true;
				}
			}
			// If the pill is to the right of ship
			else {
				if(yellowPillLeft <= spaceship.x + spaceship.width) {
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
