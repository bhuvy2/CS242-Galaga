package model.objects.projectile;

import display.view.GameWindow;
import model.objects.aliens.Alien;
import model.superclasses.Projectile;

/**
 * Created by mscislowski on 4/9/17.
 */
public class AlienMissile extends Missile implements Projectile {

    protected int count; //Mediate how fast the missile gets fired
    private boolean isBounce = false;
    private int yMove = 0, xMove = 0;
    private int bounceCount = 0;
    
    public enum Slope {
    	LeftDiagonal,
    	Left,
    	ShallowLeft,
    	Down,
    	ShallowRight,
    	Right,
    	RightDiagonal;
    	
    	public int xMove, yMove;
    	
    	static{
    		LeftDiagonal.xMove = -4; LeftDiagonal.yMove = 8;
    		ShallowLeft.xMove = -4; ShallowLeft.yMove = 4;
    		Left.xMove = -2; Left.yMove = 4;
    		Down.xMove = 0; Down.yMove = 4;
    		Right.xMove = 2; Right.yMove = 4;
    		ShallowRight.xMove = 4; ShallowRight.yMove = 4;
    		RightDiagonal.xMove = 4; RightDiagonal.yMove = 8;
    	}
    }


    /**
     * Instantiates a new alien missile. This is the second constructor for a specific slope.
     * @param alien the Alien a is the Owner of the missile
     * @param slope the slope is a slope that is presumed to be in the array slopes or within {-3, -2, -1, 0, 0, 1, 2, 3}
     */
    public AlienMissile(Alien alien, Slope slope) {
        super(alien.getXCenter(), alien.getYCenter());
    	xMove = slope.xMove;
    	yMove = slope.yMove;
    }

    /**
     * Handles movement for alien missiles
     */
    public void move() {
        y += yMove;
        x += xMove;
        if (!this.isVisible()) {
            if (this.getY() > GameWindow.BOARD_HEIGHT || this.getY() < 0) {
            	this.setyMove(-1 * this.getyMove());
            	this.decrementBounceCount();
            }
            if (this.getX() > GameWindow.BOARD_WIDTH || this.getX() < 0) {
            	this.setxMove(-1 * this.getxMove());
            	this.decrementBounceCount();
            }
        }
        count++;

    }

    public int getxMove() {
        return xMove;
    }

    public int getyMove() {
        return yMove;
    }

    public boolean doneBounce() {
        return bounceCount == 0;
    }

    public void decrementBounceCount() {
        bounceCount--;
    }

    public void setxMove(int xMove) {
        this.xMove = xMove;
    }

    public void setyMove(int yMove) {
        this.yMove = yMove;
    }

    public boolean isBounce() {
        return isBounce;
    }

    public void setBounce(boolean bounce, int nBounce) {
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
