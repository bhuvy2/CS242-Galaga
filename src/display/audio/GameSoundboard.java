package display.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;


public class GameSoundboard {
	
	private static final String firingKey = "lazerFiring",
			flyingKey = "enemyFly",
			killKey = "enemyKill",
			levelUpKey = "levelUp",
			mainKey = "mainTheme";
	
	private static final String[] sounds = {firingKey, flyingKey, killKey, levelUpKey, mainKey};

	private HashMap<String, File> soundMaps;
	
	public GameSoundboard(){
		soundMaps = new HashMap<>();
		
		Properties prop = new Properties();
		InputStream input = null;


		try {
			input = new FileInputStream("./config/audio.config");
			prop.load(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(String key: sounds){
			File f = new File(prop.getProperty(key));
			soundMaps.put(key, f);
		}
		
	}
	
	public void playLazer(){
		this.playKey(GameSoundboard.firingKey);
	}
	
	public void playMissile(){
		this.playKey(GameSoundboard.firingKey);
	}
	
	public void playFlying(){
		this.playKey(GameSoundboard.flyingKey);
	}
	
	public void playKill(){
		this.playKey(GameSoundboard.killKey);
	}
	
	public void playLevelUp(){
		this.playKey(GameSoundboard.levelUpKey);
	}

	public void playMain(){
		this.playKey(GameSoundboard.mainKey);
	}
	
	private void playKey(String key){
		Player p = null;
		try {
			File f = soundMaps.get(key);
			p = Manager.createRealizedPlayer(f.toURI().toURL());
			p.realize();
			p.start();
		} catch (NoPlayerException | CannotRealizeException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not play " + key);
		}
	}

}
