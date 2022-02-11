import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

public class HelpState {

	int gameWidth = 1440;
	int gameHeight = 900;
	Font futureFont;
	
	// Pill Images
	Image bluePill = Toolkit.getDefaultToolkit().getImage( ".\\PowerUps\\pill_blue.png");
	Image redPill = Toolkit.getDefaultToolkit().getImage( ".\\PowerUps\\pill_red.png");
	Image yellowPill = Toolkit.getDefaultToolkit().getImage( ".\\PowerUps\\pill_yellow.png");
	Image greenPill = Toolkit.getDefaultToolkit().getImage( ".\\PowerUps\\pill_green.png");
	
	// Keyboard Images
	Image upKey = Toolkit.getDefaultToolkit().getImage( ".\\keyboardImages\\upKey.png");
	Image downKey = Toolkit.getDefaultToolkit().getImage( ".\\keyboardImages\\downKey.png");
	Image leftKey = Toolkit.getDefaultToolkit().getImage( ".\\keyboardImages\\leftKey.png");
	Image rightKey = Toolkit.getDefaultToolkit().getImage( ".\\keyboardImages\\rightKey.png");
	Image spacebar = Toolkit.getDefaultToolkit().getImage( ".\\keyboardImages\\spacebar.png");
	
	// Help Menu button
	public Rectangle backButton = new Rectangle(gameWidth / 2 - 50, 700, 120, 50); //(X-coordinates, Y-coordinates,)
	
	// Color buttons
	Color darkRed = new Color(162, 7, 7);
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt1 = new Font("Arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.setColor(Color.WHITE);
		g.drawString("HOW TO PLAY", gameWidth / 2 - 100, 50);

		g.drawImage(spacebar, 350, 100, null);
		g.drawString(" =          Shoot laser", gameWidth / 2 - 10, 150);
		
		g.drawImage(leftKey, 250, 200, null);
		g.drawImage(rightKey, 350, 200, null);
		g.drawImage(upKey, 450, 200, null);
		g.drawImage(downKey, 550, 200, null);
		g.drawString(" =          Move left, up, down, right", gameWidth / 2 - 10, 250);
		
		g.drawString("POWERUPS", gameWidth / 2 - 100, 350);
		g.drawImage(bluePill, 550, 380, null);
		g.drawString(" =          Heal back to full health", gameWidth / 2 - 10, 400);
		
		g.drawImage(redPill, 550, 430, null);
		g.drawString(" =          Increase ship speed", gameWidth / 2 - 10, 450);
		
		g.drawImage(yellowPill, 550, 480, null);
		g.drawString(" =          Activates Shield", gameWidth / 2 - 10, 500);
		
		g.drawImage(greenPill, 550, 530, null);
		g.drawString(" =          TBD", gameWidth / 2 - 10, 550);
		
		// BACK Button
		g.setColor(darkRed);
		g.fillRect(backButton.x, backButton.y, backButton.width, backButton.height);
		g.setColor(Color.WHITE);
		g.drawString("BACK", backButton.x + 19, backButton.y + 35);
		g2d.draw(backButton);
	}
}