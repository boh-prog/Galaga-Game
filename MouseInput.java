import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	int gameWidth = 1440;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		// While in the MENU State
		
		if(GameEngine.state == GameEngine.STATE.MENU) {
		// PLAY Button
		if(mx >= gameWidth / 2 -50 && mx <= gameWidth / 2 + 50) {
			if(my >= 350 && my <= 400) {GameEngine.state = GameEngine.STATE.GAME;}}// pressed play button
		// HELP Button
		if(mx >= gameWidth / 2 - 50 && mx <= gameWidth / 2 + 50) {
			if(my >= 450 && my <= 500) {GameEngine.state = GameEngine.STATE.HELP;}} //pressed help button
		
		// QUIT Button
		if(mx >= gameWidth / 2 - 50 && mx <= gameWidth / 2 + 50) {
			if(my >= 550 && my <= 600) {System.exit(0);}} //pressed options button

		}
		
		// While in the PAUSE State
		
		else if(GameEngine.state == GameEngine.STATE.PAUSE) {
			// RESUME Button
			if(mx >= gameWidth / 2 - 75 && mx <= gameWidth / 2 + 75) {
				if(my >= 400 && my <= 450) {GameEngine.state = GameEngine.STATE.GAME;}}// pressed play button
			
			// OPTIONS Button
			if(mx >= gameWidth / 2 - 50 && mx <= gameWidth / 2 + 50) {
				if(my >= 500 && my <= 550) {GameEngine.state = GameEngine.STATE.OPTIONS;}} //pressed options button
			
			// QUIT Button
			if(mx >= gameWidth / 2 - 50 && mx <= gameWidth / 2 + 50) {
				if(my >= 600 && my <= 650) {System.exit(0);}} //pressed options button
		}
		
		// While in the HELP State
		
		else if (GameEngine.state == GameEngine.STATE.HELP) {
			// BACK BUTTON
			if(mx >= gameWidth / 2 - 50 && mx <= gameWidth / 2 + 50) {
				if(my >= 700 && my <= 750) {GameEngine.state = GameEngine.STATE.MENU;}}// pressed play button
		}
		
		// While in the OPTIONS State
		
		else if(GameEngine.state == GameEngine.STATE.OPTIONS) {
			// LEFT VOLUME DOWN KEY
			if(mx >= gameWidth / 2 - 220 && mx <= gameWidth / 2 - 150) 
			{
				if(my >= 300 && my <= 350) 
				{
					Options.reduceVol(0.015);//increase volume in option menu
					AudioHandler.reduceVol(0.015); //set gameSound Volume to the new set value
					//System.out.println("Audio Level = " + AudioHandler.defaultSoundLev);
				}
			}
			// RIGHT VOLUME UP KEY
			if(mx >= gameWidth / 2 + 130 && mx <= gameWidth / 2 + 200) {
				if(my >= 300 && my <= 350) {
					Options.addVol(0.015);		//increase volume in option menu
					AudioHandler.addVol(0.015); //set gameSound Volume to the new set value
					//System.out.println("Audio Level = " + AudioHandler.defaultSoundLev);
				}
			}
			
			// BACK Button
			if(mx >= gameWidth / 2 - 50 && mx <= gameWidth / 2 + 50) {
				if(my >= 550 && my <= 600) {GameEngine.state = GameEngine.STATE.PAUSE;}}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
