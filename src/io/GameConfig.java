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
	private static final String imageFileName = "config/sprites.config";
	
	/**
	 * All the keys in the config file to the path
	 */
	public static final String shipIconKey = "shipIcon",
		missileKey="shipMissile",
		explode1Key="explodePart1",
		explode2Key="explodePart2",
		explode3Key="explodePart3",
		explode4Key="explodePart4",
		alienMissileKey = "alienMissile",
		blueKey = "blueAlienIcon",
		greenKey = "greenAlienIcon",
		lazerKey = "lazersIcon",
		redKey = "redAlienIcon",
		yellowKey = "yellowAlienIcon";
	
	public static String getShipPath(){
		return prop.getProperty(shipIconKey);
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
	
	/**
	 * @return The path of the image file fro the yellow alien
	 */
	public static String getYellowPath(){
		return prop.getProperty(yellowKey);
	}
	
	/**
	 * @return The config file that has been loaded into memory
	 */
	private static Properties getMappings(){
		
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(imageFileName);
			prop.load(input);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return prop;
	}
}
