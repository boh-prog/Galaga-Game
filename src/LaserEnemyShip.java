import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

	public class LaserEnemyShip {
	
		// Enemy Laser is 7 X 19
		int width = 7;
		int height = 19;
		
		//Sound
		AudioHandler loseSound = new AudioHandler();
		
		public int x;
		public int y;

		public int speed;

		boolean isShooting = false;
		static Image laserImage;
		
		//=================================================================================
		public LaserEnemyShip(int x, int y, int speed) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			
			String path = ".\\Lasers\\";
			laserImage  = Toolkit.getDefaultToolkit().getImage(path + "laserBlue02.png");
		}

		//=================================================================================

		public void move() {
			y += speed;
		}

		//=================================================================================

		public void shoot() {
			if(isShooting) {move();}
		}

		//=================================================================================

		public boolean withinRange(Spaceship spaceship) {
			int laserLeft  = x;
			int laserRight = x + width;

			// Testing laser collision
			if( ( (y+height) >= spaceship.y) && (y <= spaceship.y + spaceship.height) ) {

				//If laser is to the left of ship
				if(x < spaceship.x) {
					if(laserRight >= spaceship.x) {
						return true;
					}
				}
				// If laser is to the right of ship
				else {
					if(laserLeft <= spaceship.x + spaceship.width) {
						return true;
					}
				}
			}
			return false;
		}

		//=================================================================================
		
		public void hit(Spaceship spaceship) {
			int damage = 5;
			if(spaceship.isShielded)  {spaceship.shield -= damage;}
			else {                     spaceship.health -= damage;}
			if(spaceship.shield <= 0) {spaceship.isShielded = false;}
			
			// Kills the spaceship
			if (spaceship.health <= 0) {
				spaceship.isAlive = false;
				loseSound.playLose();
			}
			// Makes the laser disappear once it hits the spaceship
			isShooting = false;

			// Reset laser's coordinates so that its off screen
			x = -30000;
			y = -30000;
		}

		//=================================================================================

		public boolean wentOffScreen() {
			if(y < -50) return true;
			return false;
		}

		//=================================================================================

		public void draw(Graphics g) {
			g.drawImage(laserImage, (int)x, (int)y, null);
		}

}
