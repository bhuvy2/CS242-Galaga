package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Bhuvan Venkatesh
 *	Class to manage the configuration file for the paths of the sprites
 */
public class GameConfig {
	/**
	 * The properties file
	 */
	private static final Properties prop = getMappings();
	/**
	 * Path from the root of the sprite
	 */
	private static final String imageFileName = "/config/sprites.config";
	
	/**
	 * All the keys in the config file to the path
	 */
	private static final String shipIconKey = "shipIcon",
		missileKey="shipMissile",
		explode1Key="explodePart1",
		explode2Key="explodePart2",
		explode3Key="explodePart3",
		explode4Key="explodePart4",
		alienMissileKey = "alienMissile",
		blueKey = "blueAlienIcon",
		greenKey = "greenAlienIcon",
		redKey = "redAlienIcon",
		yellowKey = "yellowAlienIcon",
		bossKey = "bossAlienKey",
		level1Key = "level1",
		level5Key = "level5",
		level10Key = "level10",
		level20Key = "level20",
		level30Key = "level40",
		level40Key = "level40",
		alienExplode1Key = "alienExplode1",
		alienExplode2Key = "alienExplode2",
		alienExplode3Key = "alienExplode3",
		alienExplode4Key = "alienExplode4",
		alienExplode5Key = "alienExplode5",
		superKey = "bossAlienIcon";
	
	public static String getShipPath(){
		return prop.getProperty(shipIconKey);
	}
	
	public static String getShipExplode1(){
		return prop.getProperty(explode1Key);
	}
	
	public static String getShipExplode2(){
		return prop.getProperty(explode2Key);
	}
	
	public static String getShipExplode3(){
		return prop.getProperty(explode3Key);
	}
	
	public static String getShipExplode4(){
		return prop.getProperty(explode4Key);
	}
	
	public static String getAlienExplode1(){
		return prop.getProperty(alienExplode1Key);
	}
	
	public static String getAlienExplode2(){
		return prop.getProperty(alienExplode2Key);
	}
	
	public static String getAlienExplode3(){
		return prop.getProperty(alienExplode3Key);
	}
	
	public static String getAlienExplode4(){
		return prop.getProperty(alienExplode4Key);
	}
	
	public static String getAlienExplode5(){
		return prop.getProperty(alienExplode5Key);
	}
	
	public static String getMissilePath(){
		return prop.getProperty(missileKey);
	}
	
	public static String getBluePath(){
		return prop.getProperty(blueKey);
	}
	
	public static String getGreenPath(){
		return prop.getProperty(greenKey);
	}
	
	public static String getRedPath(){
		return prop.getProperty(redKey);
	}

	public static String getBossKey() { return  prop.getProperty(bossKey); }
	
	/**
	 * @return The path of the image file fro the yellow alien
	 */
	public static String getYellowPath(){
		return prop.getProperty(yellowKey);
	}
	
	public static String getBossPath(){
		return prop.getProperty(superKey);
	}
	
	/**
	 * @return The config file that has been loaded into memory
	 */
	private static Properties getMappings(){
		
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = prop.getClass().getResourceAsStream(imageFileName);
			prop.load(input);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return prop;
	}
}
