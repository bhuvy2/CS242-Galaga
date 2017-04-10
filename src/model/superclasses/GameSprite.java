package model.superclasses;

import display.view.GameWindow;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by mscislowski on 4/9/17.
 */
public class GameSprite {

    protected int x, y;

    protected ImageIcon image;

    /**
     * Gamesprite contructor
     * @param image string to image file
     */
    protected GameSprite(String image){
    	BufferedImage temp = null;
		try{
			temp = ImageIO.read(new File(image));
		}catch(IOException | NullPointerException e){
			System.out.println("Error");
			e.printStackTrace();
		}
		
		if(temp != null){
			this.image = new ImageIcon((Image)temp);
		}else{
			this.image = null;
		}
    }

    /**
     * Retrieves toEdge coordinate of sprite
     * @return toEdge
     */
    public int getY() {
        return y;
    }

    /**
     * Sets toEdge coordinate of sprite
     * @param toEdge int to be set as new toEdge
     */
    public void setY(int toEdge) {
        this.y = toEdge;
    }

    /**
     * Retrieves toRight coordinate of sprite
     * @return toRight
     */
    public int getX() {
        return x;
    }

    /**
     * Sets toRight coordinate of sprite
     * @param toRight int to be set as new toRight
     */
    public void setX(int toRight) {
        this.x = toRight;
    }

    /**
     * Retrieves image of sprite
     * @return
     */
    public ImageIcon getImage() {
        return image;
    }
    
    public void drawSelf(Component c, Graphics g){
    	this.image.paintIcon(c, g, this.x, this.y);
    }

    /**
     * Sets image of sprite
     * @param image to be set as icon
     */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getFormattedY() {
        return this.getFormattedY(this.y);
    }

    public int getFormattedX() {
        return this.getFormattedX(this.x);
    }

    public int getFormattedY(int toEdge) {
        return toEdge / GameWindow.getBoardHeight() * 100;
    }

    public int getFormattedX(int toRight) {
        return toRight / GameWindow.getBoardWidth() * 100;
    }
}
