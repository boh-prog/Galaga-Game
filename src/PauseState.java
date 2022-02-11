import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

public class PauseState {

	int gameWidth = 1440;
	int gameHeight = 900;
	Font futureFont;
	
	// Pause Menu buttons
	public Rectangle resumeButton = new Rectangle(gameWidth / 2 - 70, 400, 160, 50); //(X-coordinates, Y-coordinates,  )
	public Rectangle optionsButton = new Rectangle(gameWidth / 2 - 75, 500, 160, 50);
	public Rectangle quitButton = new Rectangle(gameWidth / 2 - 70, 600, 100, 50);
	
	// Color buttons
	Color darkGray = new Color(53, 57, 57);
	
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
		g.drawString("PAUSED", gameWidth / 2 - 80, 200);
		
		Font fnt1 = new Font("Arial", Font.BOLD, 30);
		g.setFont(fnt1);
		
		// RESUME Button
		g.setColor(darkGray);
		g.fillRect(resumeButton.x, resumeButton.y, resumeButton.width, resumeButton.height);
		g.setColor(Color.WHITE);
		g.drawString("RESUME", resumeButton.x + 19, resumeButton.y + 35);
		g2d.draw(resumeButton);
		
		// OPTIONS Button
		g.setColor(darkGray);
		g.fillRect(optionsButton.x, optionsButton.y, optionsButton.width, optionsButton.height);
		g.setColor(Color.WHITE);
		g.drawString("OPTIONS", optionsButton.x + 19, optionsButton.y + 35);
		g2d.draw(optionsButton);
		
		// QUIT Button
		g.setColor(darkGray);
		g.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
		g.setColor(Color.WHITE);
		g.drawString("QUIT", quitButton.x + 19, quitButton.y + 35);
		g2d.draw(quitButton);
		
		
	}
}