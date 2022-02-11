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

public class Options {

	int gameWidth = 1440;
	int gameHeight = 900;
	Font futureFont;
	static double volumeLevel = 80;
	
	Image leftKey = Toolkit.getDefaultToolkit().getImage( ".\\keyboardImages\\leftKey.png");
	Image rightKey = Toolkit.getDefaultToolkit().getImage( ".\\keyboardImages\\rightKey.png");
	
	// Pause Menu buttons
	public Rectangle backButton = new Rectangle(gameWidth / 2 - 70, 550, 130, 50); //(X-coordinates, Y-coordinates,  )
		
	// Color buttons
	Color darkRed = new Color(162, 7, 7);
	
	// Add and Decrease Game Volume
	static void addVol(double value){if((int)volumeLevel < 100 ) volumeLevel += value;}
	static void reduceVol(double value){if((int)volumeLevel > 0) volumeLevel -= value;}
	
	public void render(Graphics g) {
			
	Graphics2D g2d = (Graphics2D) g;
			
		try {
		    String path 	 		= ".\\Font\\";
			futureFont = Font.createFont(Font.TRUETYPE_FONT, new File(path + "kenvector_future.ttf")); // .deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path + "kenvector_future.ttf")));
		} catch(IOException | FontFormatException e) {
			
		}
		
		Font fnt0 = new Font("futureFont", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("OPTIONS", gameWidth / 2 - 100, 100);
		
		g.drawString("VOLUME CONTROL", gameWidth / 2 - 225, 250);
		g.drawString(Float.toString((int)volumeLevel), 650, 350);
		g.drawImage(leftKey, 500, 300, null);
		g.drawImage(rightKey, 850, 300, null);
		
		Font fnt1 = new Font("Arial", Font.BOLD, 30);
		g.setFont(fnt1);
			
		// BACK Button
		g.setColor(darkRed);
		g.fillRect(backButton.x, backButton.y, backButton.width, backButton.height);
		g.setColor(Color.WHITE);
		g.drawString("BACK", backButton.x + 19, backButton.y + 35);
		g2d.draw(backButton);
		}
}