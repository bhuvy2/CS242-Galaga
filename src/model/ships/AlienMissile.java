package model.ships;

import display.view.GameWindow;
import model.superclasses.Projectile;

/**
 * Created by mscislowski on 4/9/17.
 */
public class AlienMissile extends Missile implements Projectile {
    //Selects a random slope to fire if none is provided
    private static final int[] slopes = {-2, -1, 0, 1, 2};

    protected int slope,                        //What slope the missile is at
            count;                        //Mediate how fast the missile gets fired
    private static final int LENGTH = 2;  //Length of how long the missile is
    private boolean isBounce = false, slopeSet = false;
    private int yMove = 0, xMove = 0;
    private int bounceCount = 0;


    /**
     * Instantiates a new alien missile. This is the second constructor for a specific slope.
     * @param alien the Alien a is the Owner of the missile
     * @param slope the slope is a slope that is presumed to be in the array slopes or within {-3, -2, -1, 0, 0, 1, 2, 3}
     */
    public AlienMissile(Alien alien, int slope) {
        super(alien.getXCenter(), alien.getYCenter());
        this.slope = slope;
    }

    /**
     * Handles movement for alien missiles
     */
    public void move() {
        // Cases denoted by slope angle type
        if(!slopeSet) {
            slopeSet = true;
            switch(slope) {
            case 0:
                yMove = 4;
                break;
            case -1:
                xMove = 2;
                yMove = 2;
                break;
            case 1:
                xMove = -2;
                yMove = 2;
                break;
            case -2:
                xMove = 2;
                yMove = 4;
                break;
            case 2:
                xMove = -2;
                yMove = 4;
                break;
            case -3:
                xMove = 4;
                yMove = 8;
                break;
            case 3:
                xMove = -4;
                yMove = 8;
                break;
            default:
                break;

            }
        }
        y += yMove;
        x += xMove;
        count++;

    }

    int getxMove() {
        return xMove;
    }

    int getyMove() {
        return yMove;
    }

    boolean doneBounce() {
        return bounceCount == 0;
    }

    void decrementBounceCount() {
        bounceCount--;
    }

    void setxMove(int xMove) {
        this.xMove = xMove;
    }

    void setyMove(int yMove) {
        this.yMove = yMove;
    }

    boolean isBounce() {
        return isBounce;
    }

    void setBounce(boolean bounce, int nBounce) {
        isBounce = bounce;
        bounceCount = nBounce;
    }

    /**
     * Checks if missile is visible
     * @return true if missile is visible
     */
    public boolean isVisible() {
        return (y < GameWindow.BOARD_HEIGHT && y > 0) 
        		&& (x < GameWindow.BOARD_WIDTH && x > 0);
    }
}
