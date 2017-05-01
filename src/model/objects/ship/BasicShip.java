package model.objects.ship;

import display.view.GameWindow;
import io.GameConfig;
import model.Game;
import model.objects.projectile.Missile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mscislowski on 4/9/17.
 */
public class BasicShip extends Ship {
	
    private static final int NUM_LIVES = 2;
    private static final int NUM_MISSILES = 2;
    private int imageIndex = 0;
    
    private ImageIcon stable;


    /**
     * Creates a generic ship for the player
     */
    public BasicShip(Game game) {
    	super(GameConfig.getShipPath(), game);
    	stable = this.image;
        this.x = 0;
        this.y = GameWindow.BOARD_HEIGHT - this.image.getIconHeight()*3;
        this.storage = new ArrayList<Missile>(NUM_MISSILES);
        this.lives = NUM_LIVES;
    }
    
    public void drawSelf(Component c, Graphics g) {
        this.image.paintIcon(c, g, this.x, this.y);
        for (Missile m : storage) {
            m.getImage().paintIcon(c, g, m.getX(), m.getY());
        }
        for(int i = 0; i < this.lives; i++){
        	this.stable.paintIcon(c, g, 
        			i*this.image.getIconWidth(), 
        			GameWindow.BOARD_HEIGHT-this.image.getIconHeight()-30);
        }
    }

    /**
     * Change order for dying ship
     */
    public void change() {
    	imageIndex++;
        switch (imageIndex) {
        case 0:
            this.setImage(GameConfig.getShipPath());
            this.getImage().setDescription("Basic Ship");
            break;
        case 1:
            this.setImage(GameConfig.getShipExplode1());
            this.getImage().setDescription("Explode 1");
            break;
        case 2:
            this.setImage(GameConfig.getShipExplode2());
            this.getImage().setDescription("Explode 2");
            break;
        case 3:
            this.setImage(GameConfig.getShipExplode3());
            this.getImage().setDescription("Explode 3");
            break;
        case 4:
            this.setImage(GameConfig.getShipExplode4());
            this.getImage().setDescription("Explode 4");
            imageIndex = -1;
            break;
        default:
            break;
        }
    }
}
