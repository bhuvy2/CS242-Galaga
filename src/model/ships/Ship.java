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


    public void fire() {
        if (!canFire)
            return;
        if (storage.size() < MAX_SHOTS) {
            if (!multipleShots) {
                addMissile(-4, -35);
                Game.setShotsFired(Game.getShotsFired() + 1);
            } else {
                addMissile(-4, 0);
                addMissile(-4, -35);
                addMissile(-4, 70);
                addMissile(-35, 35);
                addMissile(25, 35);
                addMissile(-20, 35);
                addMissile(10, 35);
                Game.setShotsFired(Game.getShotsFired() + 7);
            }
        }
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

    /**
     * Checks if is hit.
     * @param input the input isn't null and does has all of the aliens
     * @return true if is hit
     */

    //TODO FIX this from fromatted
    public boolean isHit(ArrayList<Alien> input) {
        int right = this.getFormattedX();
        int edge = this.getFormattedY();
        int nextRight;
        int nextEdge;

        for(Alien b : input) {
            for(AlienMissile a : b.list) {
                nextRight = this.getFormattedX(a.toRightNext);
                nextEdge = this.getFormattedY(a.toEdgeNext);
                if(nextRight >= right && nextRight <= right + image.getIconWidth()) {
                    if(nextEdge >= edge &&  nextEdge <= edge + image.getIconHeight()) {
                        b.list.remove(a);
                        return true;
                    }
                }
            }

            nextRight = b.getFormattedX();
            nextEdge = b.getFormattedY();

            if(nextRight >= right && nextRight <= right + image.getIconWidth() ||
                    nextRight + b.getImage().getIconWidth() >= right && nextRight +
                            b.getImage().getIconWidth() <= right + image.getIconWidth())
                if(nextEdge >= edge &&  nextEdge <= edge + image.getIconHeight() ||
                        nextEdge + b.getImage().getIconHeight() >= edge &&  nextEdge +
                                b.getImage().getIconHeight() <= edge + image.getIconHeight()) {
                    input.remove(b);
//                    Alien.amountAttacking--;
                    return true;
                }
        }
        return false;
    }

    public void tick() {
        Missile m;
        for (int i = 0; i < storage.size(); i++) {
            m = storage.get(i);
            m.setY(m.getY() - 4);
            if (m.getY() < 0) {
                storage.remove(i);
                i--;
            }
        }
        if (!isInvincible() && isHit(Game.getEnemies())) {
            if (lives > 0) {
                lives--;
                Alien.resetAttack();
            } else {
                Game.setGameover(true);
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
