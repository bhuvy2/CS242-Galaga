package io;

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
	private static Properties prop = getMappings();
	/**
	 * Path from the root of the sprite
	 */
	private static final String imageFileName = "/config/sprites.config";
	
	/**
	 * All the keys in the config file to the path
	 */
	private static final String shipIconKey = "shipIcon",
		shieldShipKey = "shieldShipIcon",
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


	static void setProperties(Properties p){
		prop = p;
	}
	
    /**
     * @return The path of the image file for the basic ship
     */
	public static String getShipPath(){
		return prop.getProperty(shipIconKey);
	}

    /**
     * @return The path of the image file for the shielded ship
     */
	public static String getShieldShipPath() { return prop.getProperty(shieldShipKey); }

    /**
     * @return The path of the image file for the ship explosion frame 1
     */
	public static String getShipExplode1(){
		return prop.getProperty(explode1Key);
	}

    /**
     * @return The path of the image file for the ship explosion frame 2
     */
	public static String getShipExplode2(){
		return prop.getProperty(explode2Key);
	}

    /**
     * @return The path of the image file for the ship explosion frame 3
     */
	public static String getShipExplode3(){
		return prop.getProperty(explode3Key);
	}

    /**
     * @return The path of the image file for the ship explosion frame 4
     */
	public static String getShipExplode4(){
		return prop.getProperty(explode4Key);
	}

    /**
     * @return The path of the image file for the alien explosion frame 1
     */
	public static String getAlienExplode1(){
		return prop.getProperty(alienExplode1Key);
	}

    /**
     * @return The path of the image file for the alien explosion frame 2
     */
	public static String getAlienExplode2(){
		return prop.getProperty(alienExplode2Key);
	}

    /**
     * @return The path of the image file for the alien explosion frame 3
     */
	public static String getAlienExplode3(){
		return prop.getProperty(alienExplode3Key);
	}

    /**
     * @return The path of the image file for the alien explosion frame 4
     */
	public static String getAlienExplode4(){
		return prop.getProperty(alienExplode4Key);
	}

    /**
     * @return The path of the image file for the alien explosion frame 5
     */
	public static String getAlienExplode5(){
		return prop.getProperty(alienExplode5Key);
	}

    /**
     * @return The path of the image file for the missile
     */
	public static String getMissilePath(){
		return prop.getProperty(missileKey);
	}

    /**
     * @return The path of the image file for the blue alien
     */
	public static String getBluePath(){
		return prop.getProperty(blueKey);
	}

    /**
     * @return The path of the image file for the green alien
     */
	public static String getGreenPath(){
		return prop.getProperty(greenKey);
	}

    /**
     * @return The path of the image file for the red alien
     */
	public static String getRedPath(){
		return prop.getProperty(redKey);
	}

    /**
     * @return The path of the image file for the Boss key
     */
	public static String getBossKey() { return  prop.getProperty(bossKey); }

	/**
	 * @return The path of the image file for the yellow alien
	 */
	public static String getYellowPath(){
		return prop.getProperty(yellowKey);
	}

    /**
     * @return The path of the image file for the Boss alien
     */
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
