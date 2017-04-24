package model.ships;

import display.view.GameWindow;
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
    
    enum ShipState {
    	LEFT,
    	RIGHT,
    	STOP
    };
    
    ShipState current;

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
    	current = ShipState.STOP;
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
     * Draws and moves ship's missiles each tick then removes 
     * them once they leave the screen
     */
    public void tick() {
        for (int i = 0; i < storage.size(); i++) {
        	Missile m = storage.get(i);
            m.move();
            if (m.getY() < 0) {
                storage.remove(i);
                i--;
            }
        }
        switch(current){
		case LEFT:
			if(this.x > 0)
				this.x -= 3;
			break;
		case RIGHT:
			if(this.x < GameWindow.BOARD_WIDTH-this.image.getIconWidth())
				this.x += 3;
			break;
		case STOP:
			break;
		default:
			break;
        }
    }
    
    public void setLeft(){
    	current = ShipState.LEFT;
    }
    
    public void setRight(){
    	current = ShipState.RIGHT;
    }
    
    public void setStop(){
    	current = ShipState.STOP;
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
