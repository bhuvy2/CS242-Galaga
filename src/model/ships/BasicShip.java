package model.ships;

import display.view.GameWindow;
import io.GameConfig;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mscislowski on 4/9/17.
 */
public class BasicShip extends Ship {
    private static final int NUM_LIVES = 2;
    private static final int NUM_MISSILES = 2;
    private int imageIndex = 0;

    /**
     * Creates a generic ship for the player
     */
    public BasicShip() {
    	super(GameConfig.getShipPath());
        this.x = 0;
        this.y = GameWindow.getBoardHeight() - this.image.getIconHeight()*3;
        this.storage = new ArrayList<Missile>(NUM_MISSILES);
        this.lives = NUM_LIVES;
    }

    public void change() {
        imageIndex++;
        switch (imageIndex) {
            case 0:
                this.setImage(GameConfig.getShipPath());
                break;
            case 1:
                this.setImage(GameConfig.getBluePath());
                break;
            case 2:
                this.setImage(GameConfig.getGreenPath());
                break;
            case 3:
                this.setImage(GameConfig.getRedPath());
                break;
            case 4:
                this.setImage(GameConfig.getYellowPath());
                imageIndex = -1;
                break;
            default:
                break;
        }
    }

    public void setImage(String path) {
        BufferedImage temp = null;
        try{
            temp = ImageIO.read(new File(path));
        }catch(IOException | NullPointerException e){
            System.out.println("Error");
        }

        if(temp != null) {
            this.image = new ImageIcon(temp);
        } else {
            this.image = null;
        }
    }
}
