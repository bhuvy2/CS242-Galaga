package display.view;

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
 *	Manages the interface between the view and the
 *	model
 */
public class SpriteCache{
	
	private static HashMap<String, ImageIcon> icon = new HashMap<>();
	
	public static ImageIcon get(String res){
		ImageIcon cur = icon.get(res);
		if(cur != null){
			return cur;
		}
		
		BufferedImage temp = null;
		try{
			temp = ImageIO.read(new File(res));
		}catch(IOException | NullPointerException e){
			System.out.println("Error");
		}
		
		
		if(temp != null) {
			cur = new ImageIcon((Image)temp);
		}
		icon.put(res, cur);
		return cur;
	}
}