package model.ships;

import display.view.GameWindow;
import model.Game;
import model.superclasses.GameSprite;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mscislowski on 4/9/17.
 */
public class Ship extends GameSprite {
    // Missile storage
    ArrayList<Missile> storage;

    // Number of lives ship has
    protected int lives;

    // Maximum number of shots allowed
    protected static int MAX_SHOTS = 2;

    // Valid options for ship
    protected boolean canMove, canFire,
	    isInvincible,
	    canThrottle,
	    multipleShots;

    /**
     * Ship constructor
     * @param str String to image file passed to Gamesprite
     */
    public Ship(String str){
    	super(str);
    	this.lives = 2;
    	this.canMove = true;
    	this.canFire = true;
    	this.isInvincible = false;
    	this.canThrottle = false;
    	this.multipleShots = false;
    }


    public boolean fire() {
        if (canFire && storage.size() < MAX_SHOTS) {
            if (!multipleShots) {
                addMissile(-4, -35);
            } else {
                addMissile(-4, 0);
                addMissile(-4, -35);
                addMissile(-4, 70);
                addMissile(-35, 35);
                addMissile(25, 35);
                addMissile(-20, 35);
                addMissile(10, 35);
            }
            return true;
        }
        return false;
    }

    private void addMissile(int offset1, int offset2) {
        Missile missile = new Missile(this.x +
                image.getIconWidth()/2+offset1+3, GameWindow.getBoardHeight()-this.image.getIconHeight()+offset2 - Missile.HEIGHT);
        storage.add(missile);
    }

    public void drawSelf(Component c, Graphics g) {
        this.image.paintIcon(c, g, this.x, this.y);
        for (Missile m : storage) {
            m.getImage().paintIcon(c, g, m.getX(), m.getY());
        }
        for(int i = 0; i < this.lives; i++){
        	this.image.paintIcon(c, g, 
        			i*this.image.getIconWidth(), 
        			GameWindow.BOARD_HEIGHT-this.image.getIconHeight()-30);
        }
    }

    /**
     * Removes the invisible Missiles that have reached the end of the screen.
     * @return All Missiles that have flew off the screen have been gotten rid of
     */
    public void removeInvisible() {
        for(int i = 0; i < storage.size(); i++)  {
            if(!storage.get(i).isVisible()) {
                storage.remove(i);
                i--;
            }
        }
    }

    public void tick() {
        for (int i = 0; i < storage.size(); i++) {
        	Missile m = storage.get(i);
            m.move();
            if (m.getY() < 0) {
                storage.remove(i);
                i--;
            }
        }
    }

    public boolean canFire() {
        return canFire;
    }

    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean isInvincible) {
        this.isInvincible = isInvincible;
    }

    public boolean canThrottle() {
        return canThrottle;
    }

    public void setCanThrottle(boolean canThrottle) {
        this.canThrottle = canThrottle;
    }

    public ArrayList<Missile> getStorage() {
        return storage;
    }

    public void setStorage(ArrayList<Missile> input) {
        storage = input;
    }

    public boolean isMultipleShots() {
        return multipleShots;
    }

    public void setMultipleShots(boolean multipleShots) {
        this.multipleShots = multipleShots;
    }

    public static int getMaxShots() {
        return MAX_SHOTS;
    }

    public static void setMaxShots(int maxShots) {
        MAX_SHOTS = maxShots;
    }
}
