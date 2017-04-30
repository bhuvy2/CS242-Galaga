package model.superclasses;

import io.SpriteCache;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mscislowski on 4/9/17.
 */
public abstract class GameSprite {

    protected int x, y;
    protected ImageIcon image;

    /**
     * Gamesprite contructor
     * @param image string to image file
     */
    protected GameSprite(String image){
    	this.image = SpriteCache.get(image);
    }

    public void change() {
    	
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
    
    /**
     * @param c, the component to draw
     * @param g, the graphics g
     */
    public void drawSelf(Component c, Graphics g){
    	this.image.paintIcon(c, g, this.x, this.y);
    }

    /**
     * Ticks the sprite along
     */
    public void tick() {

    }

    /**
     * Sets image of sprite
     * @param image to be set as icon
     */
    public void setImage(ImageIcon image) {
        this.image = image;
    }
    
    public void setImage(String path) {
    	this.image = SpriteCache.get(path);
    }

    // X center of icon
    public int getXCenter() {
        return x + image.getIconWidth()/2;
    }

    // Y center of icon
    public int getYCenter() {
        return y + image.getIconHeight()/2;
    }
}
