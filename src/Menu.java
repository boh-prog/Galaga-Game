import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Menu {

	int gameWidth = 1440;
	int gameHeight = 900;
	Font futureFont;
	
	// Menu Buttons
	public Rectangle PlayButton = new Rectangle(gameWidth / 2 - 50, 350, 100, 50); //(X-coordinates, Y-coordinates,  )
	public Rectangle helpButton = new Rectangle(gameWidth / 2 - 50, 450, 100, 50);
	public Rectangle quitButton = new Rectangle(gameWidth / 2 - 50, 550, 100, 50);

	
	// Color Buttons
	Color mintGreen = new Color(10, 220, 129);
	Color lightBlue = new Color(11, 166, 255);
	Color lightPurple = new Color(174, 11, 255);
	Color red = new Color(255, 0, 0);
	
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
		g.drawString("GALAGA 2.0", gameWidth / 2 - 125, 200);
		
		Font fnt1 = new Font("Arial", Font.BOLD, 30);
		g.setFont(fnt1);
		
		// PLAY Button
		g.setColor(mintGreen);
		g.fillRect(PlayButton.x, PlayButton.y, PlayButton.width, PlayButton.height);
		g.setColor(Color.BLACK);
		g.drawString("PLAY", PlayButton.x + 13, PlayButton.y + 35);
		g2d.draw(PlayButton);
		
		// HELP Button
		g.setColor(lightBlue);
		g.fillRect(helpButton.x, helpButton.y, helpButton.width, helpButton.height);
		g.setColor(Color.BLACK);
		g.drawString("HELP", helpButton.x + 13, helpButton.y + 35);
		g2d.draw(helpButton);
		
		// QUIT Button
		g.setColor(red);
		g.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
		g.setColor(Color.BLACK);
		g.drawString("QUIT", quitButton.x + 13, quitButton.y + 35);
		g2d.draw(quitButton);
	
	}
}
