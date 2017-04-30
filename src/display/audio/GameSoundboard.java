package display.audio;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;


/**
 * @author Bhuvan Venkatesh
 *	Represents a soundboard that can play sounds asynchronously
 */
public class GameSoundboard {
	
	/**
	 * Stores the keys that the property file has mapped to
	 */
	private static final String firingKey = "lazerFiring",
			flyingKey = "enemyFly",
			killKey = "enemyKill",
			levelUpKey = "levelUp",
			mainKey = "mainTheme";
	
	/**
	 * Stores all the sound keys
	 */
	private static final String[] sounds = 
		{firingKey, flyingKey, killKey, levelUpKey, mainKey};

	/**
	 * Opens all of the files for the duration of the game
	 */
	private HashMap<String, URL> soundMaps;
	
	/**
	 * Config file name
	 */
	public static final String audioConfigName = "/config/audio.config";
	
	/**
	 * Reads the properties files and opens all the files for playing
	 */
	public GameSoundboard(){
		soundMaps = new HashMap<>();
		
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = this.getClass().getResourceAsStream(audioConfigName);
			prop.load(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(String key: sounds){
			URL inp = getClass().getResource(prop.getProperty(key));
			soundMaps.put(key, inp);
		}
		
	}
	
	
	/**
	 * Plays the alien laser sound
	 */
	public void playLazer(){
		this.playKey(GameSoundboard.firingKey);
	}
	
	/**
	 * Plays the missile sound effect
	 */
	public void playMissile(){
		this.playKey(GameSoundboard.firingKey);
	}
	
	/**
	 * Plays the alien flying down sound effect
	 */
	public void playFlying(){
		if(Math.random() < .3)
			this.playKey(GameSoundboard.flyingKey);
	}
	
	/**
	 * Plays the alien killed sound effect
	 */
	public void playKill(){
		this.playKey(GameSoundboard.killKey);
	}
	
	/**
	 * Plays the level up theme
	 */
	public void playLevelUp(){
		this.playKey(GameSoundboard.levelUpKey);
	}

	/**
	 * Plays the main theme
	 */
	public void playMain(){
		this.playKey(GameSoundboard.mainKey);
	}
	
	/**
	 * @param Plays the sound at one of the keys in sound
	 */
	private void playKey(String key){
		Thread th = new Thread(new Runnable() {
			public void run(){
				Player p = null;
				try {
					URL f = soundMaps.get(key);
					if(f == null){
						System.out.println("File not open "+key);
						return;
					}
					p = Manager.createRealizedPlayer(f);
					p.realize();
					p.start();
					Thread.sleep(8000);
					p.stop();
					p.deallocate();
				} catch (NoPlayerException | CannotRealizeException | IOException e) {
					System.out.println("Could not play " + key);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		th.start();
	}

}
