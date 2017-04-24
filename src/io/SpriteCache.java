package io;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import model.superclasses.GameSprite;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author Bhuvan Venkatesh
 *	Caches images so they are not read multiple times
 */
public class SpriteCache {
	
	private static HashMap<String, ImageIcon> icon = new HashMap<>();
	
	/**
	 * @param res, the path to the resource
	 * @return The ImageIcon or null
	 */
	public static ImageIcon get(String res){
		ImageIcon cur = icon.get(res);
		if(cur != null){
			return cur;
		}
		BufferedImage temp = tryReadImage(res);
		
		if(temp != null) {
			cur = new ImageIcon((Image)temp);
		}
		icon.put(res, cur);
		return cur;
	}

	private static BufferedImage tryReadImage(String res) {
		BufferedImage temp = null;
		try{
			temp = ImageIO.read(new File(res));
		}catch(IOException | NullPointerException e){
			System.out.println("Error");
		}
		return temp;
	}
}