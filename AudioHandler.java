    
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.awt.Toolkit;
import java.io.File;

public class AudioHandler implements Runnable
{

    //------------------------------------------------------------------------//
	// https://onlinepngtools.com/resize-png resize images
	// https://www.audacityteam.org/download/ Convert to wav files
	
//		static String path 			= "C:\\Users\\perrytech\\Desktop\\Game\ Programming\\Game\\Galaga\ Game\\src\\Sounds\";
	   	static String path 	 		= ".\\Sounds\\";
	    static File theme 	 		= new File(path + "Industria.wav");//"Industria.wav"
	    static File enemyExplosion	= new File(path + "");
	    static File ShootLaser	 	= new File(path + "sfx_laser1.wav"); //sfx_laser1.wav
	    static File shipExplosion 	= new File(path + ""); //shipExplosion.wav
	    static File teleport 		= new File(path + "");
	    static File lose 	 		= new File(path + "sfx_lose.wav"); //sfx_lose.wav
	    static File playerLaserHit	= new File(path + "playerLaserHit.wav"); //playerLaserHit.wav
	    static File enemyLaserHit	= new File(path + "laserEnemyHit.wav"); //laserEnemyHit.wav
	    static File menuTheme		= new File(path + "gameMenuTheme.wav"); //gameMenuTheme.wav
	    static File pauseTheme		= new File(path + "pauseMenu.wav"); //pauseMenu.wav

		static Clip themeClip; 	//in-game theme
		static Clip menuThemeClip;	//menu theme
		static Clip pauseMenuClip; // pause menu theme

	    //set default sound level
	    static float defaultSoundLev = 80; //sound level ranges from 0 - 100

	    //------------------------------------------------------------------------//

	// let AudioHandler run as its own thread
	    public AudioHandler()
	    {
	        Thread musicThread = new Thread(this);
	        musicThread.start();
	    }
	//------------------------------------------------------------------------//

	    public void run()
	    {
	    }

	    void play(File soundFile)
	    { //play any sound file
	        try
	        {
	            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
	            Clip clip = AudioSystem.getClip();
	            clip.open(sound);
	            //setVolume(clip,defaultSoundLev);//set default sound Level
	            clip.start();//
	        }
	        catch(Exception ex)
	        { }
	    }

	    //============================================================================//
	    //Checks if an audio clip is playing
	    boolean playing(Clip soundClip)
	    {
	        if (soundClip == null || !soundClip.isRunning()) return false;
	        return true;
	    }

	    //=============================================================================//
	    public void playTheme()
	    {//
	        try
	        {
	            if(!playing(themeClip))//Play only if theme is not playing
	            {
	                AudioInputStream sound = AudioSystem.getAudioInputStream(theme);
	                themeClip = AudioSystem.getClip();
	                themeClip.open(sound);
	                setVolume(themeClip, defaultSoundLev);//set default sound Level
	                themeClip.start();
	                themeClip.loop(Clip.LOOP_CONTINUOUSLY);//
	            }
	        }
	        catch(Exception ex)
	        { }
	    }
	    
	    //------------------------------------------------------------------------//
	    
	    public void playMenuTheme()
	    {
	        try
	        {
	            if(!playing(menuThemeClip))//Play only if menu Theme is not playing
	            {
	                AudioInputStream sound = AudioSystem.getAudioInputStream(menuTheme);
	                menuThemeClip = AudioSystem.getClip();
	                menuThemeClip.open(sound);
	                setVolume(menuThemeClip, defaultSoundLev);//set default sound Level
	                menuThemeClip.start();
	                menuThemeClip.loop(Clip.LOOP_CONTINUOUSLY);
	            }
	        }
	        catch(Exception ex)
	        { }
	    }
	    
	    //------------------------------------------------------------------------//
	    
	    public void playPauseMenuTheme()
	    {
	        try
	        {
	            if(!playing(pauseMenuClip))//Play only if menu Theme is not playing
	            {
	                AudioInputStream sound = AudioSystem.getAudioInputStream(pauseTheme);
	                pauseMenuClip = AudioSystem.getClip();
	                pauseMenuClip.open(sound);
	                setVolume(pauseMenuClip, defaultSoundLev);//set default sound Level
	                pauseMenuClip.start();
	                pauseMenuClip.loop(Clip.LOOP_CONTINUOUSLY);
	            }
	        }
	        catch(Exception ex)
	        { }
	    }


	    //------------------------------------------------------------------------//
	    void stop(Clip soundClip)
	    { //stop any given clip
	        if (soundClip != null && soundClip.isRunning())
	        {
	            soundClip.stop();
	        }
	    }
	    
		//-----------------------------------------------------------------------------------//

	 	static void addVol(double value)
	    	{ // change default sound level
	        	if(defaultSoundLev < 99) defaultSoundLev += value;
	        	
	        	setVolume(themeClip, defaultSoundLev);
	        	setVolume(menuThemeClip, defaultSoundLev);
	        	setVolume(pauseMenuClip, defaultSoundLev);
	    	}
	    static void reduceVol(double value)
	    	{
	        	if(defaultSoundLev > 1) defaultSoundLev -= value;
	        	setVolume(themeClip, defaultSoundLev);
	        	setVolume(menuThemeClip, defaultSoundLev);
	        	setVolume(pauseMenuClip, defaultSoundLev);
	    	}

	    //-------------------------------------------------------------------------//
	    public Clip setClip(File file)
	    { // return sound clip of given sound file
	        try
	        {
	            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
	            Clip clip = AudioSystem.getClip();
	            clip.open(sound);
	            return clip;
	        }
	        catch(Exception ex)
	        { }
	        return null;
	    }
	     //-------------------------------------------------------------------------//
	    static void setVolume(Clip soundclip, float volumeLevel)
	    {//

	        FloatControl gainControl = (FloatControl) soundclip.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(volumeLevel/100.0f); // set volume level.
	        //soundclip.start();
	    }
	    
	    //--------------------------------------------------------------------------//

	    public void stopTheme()
	    {
	    	if (themeClip!= null && themeClip.isRunning())
	    	{
	    		themeClip.stop();
	    	}
	    }
	  //------------------------------------------------------------------------//

	    public void stopMenuTheme()
	    {
	    	if (menuThemeClip!= null && menuThemeClip.isRunning())
	    	{
	    		menuThemeClip.stop();
	    	}
	    }
	    
		  //------------------------------------------------------------------------//

	    public void stopPauseMenuTheme()
	    {
	    	if (pauseMenuClip!= null && pauseMenuClip.isRunning())
	    	{
	    		pauseMenuClip.stop();
	    	}
	    }
	    //------------------------------------------------------------------------//
	    //explosion sound
	    void enemyExplosion()
	    {//play sound once
	        
	        play(enemyExplosion);
	    }

	//------------------------------------------------------------------------//

	    void shootLaser()
	    {
	        play(ShootLaser);
	    }

	//------------------------------------------------------------------------//

	    void shipExplosion()
	    {//play sound once
	        play(shipExplosion);
	    }
	    //------------------------------------------------------------------------
	    void levelComplete()
	    {//play end level sound

	    }

	    //------------------------------------------------------------------------
	    void playTeleport()
	    {
	        play(teleport);
	    }
	    
	    //------------------------------------------------------------------------
	    void playLose()
	    {
	    	play(lose);
	    }
	   
	    //------------------------------------------------------------------------
	    void playLaserHitEnemy()
	    {
	        play(enemyLaserHit);
	    }
	    //------------------------------------------------------------------------
	    void playLaserHitPlayer()
	    {
	        play(playerLaserHit);
	    }
}