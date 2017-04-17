package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameConfig {
	private static final Properties prop = getMappings();
	private static final String imageFileName = "config/sprites.config";
	
	private static final String shipIconKey = "shipIcon",
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
	
	public static String getYellowPath(){
		return prop.getProperty(yellowKey);
	}
	
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
