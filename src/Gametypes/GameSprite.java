package Gametypes;

import display.view.GameWindow;

import javax.swing.*;

/**
 * Created by mscislowski on 4/9/17.
 */
public class GameSprite {
    // x, y coordinates
    protected int toRight, toEdge;
    // Icon for game sprite
    protected ImageIcon image;

    /**
     * Retrieves toEdge coordinate of sprite
     * @return toEdge
     */
    public int getToEdge() {
        return toEdge;
    }

    /**
     * Sets toEdge coordinate of sprite
     * @param toEdge int to be set as new toEdge
     */
    public void setToEdge(int toEdge) {
        this.toEdge = toEdge;
    }

    /**
     * Retrieves toRight coordinate of sprite
     * @return toRight
     */
    public int getToRight() {
        return toRight;
    }

    /**
     * Sets toRight coordinate of sprite
     * @param toRight int to be set as new toRight
     */
    public void setToRight(int toRight) {
        this.toRight = toRight;
    }

    /**
     * Retrieves image of sprite
     * @return
     */
    public ImageIcon getImage() {
        return image;
    }

    /**
     * Sets image of sprite
     * @param image to be set as icon
     */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getFormattedEdge() {
        return this.toEdge / GameWindow.getBoardHeight() * 100;
    }

    public int getFormattedRight() {
        return this.toRight / GameWindow.getBoardWidth() * 100;
    }

    public int getFormattedEdge(int toEdge) {
        return toEdge / GameWindow.getBoardHeight() * 100;
    }

    public int getFormattedRight(int toRight) {
        return toRight / GameWindow.getBoardWidth() * 100;
    }
}
