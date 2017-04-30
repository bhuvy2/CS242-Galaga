package model.objects.aliens;

import display.view.GameWindow;
import io.GameConfig;
import model.objects.projectile.AlienMissile;
import model.objects.projectile.AlienMissile.Slope;

import java.util.ArrayList;

/**
 * Created by mscislowski on 4/9/17.
 */
public class BasicAlien extends Alien {
    private static final int attackingSpeed = 2;

	/**
     * Instantiates a new basic alien, saves the column
     * and row as a backup in case need to be reset
     * as well as loads the image, initializes points,
     * creates a new list of Alien Missiles and set health
     *
     * @param toEdge  the to edge is a percentage from
     *                left to right, of 100, how up or
     *                down the alien is the y coordinate.
     * @param toRight the to right is the same as toEdge but
     *                it is the x coordinate.
     */
    public BasicAlien(int toEdge, int toRight) {
        super(GameConfig.getYellowPath());
        this.y = toEdge;
        this.x = toRight;
        column = toRight;
        row = toEdge;
        isMovingRight = true;
        points = basePoints;
        list = new ArrayList<AlienMissile>();
        health = baseHealth + 1;
    }

    /**
     * Handles attacks for aliens
     */
    public void attack() {
        if (!isAttacking) {
        	return;
        }
        
    	y += attackingSpeed;
        if (inFiringRange())
            this.fire();
        else if (this.getY() >= GameWindow.BOARD_HEIGHT)
            y = 0;
        else if (y == row) {
            isAttacking = false;
        }
    }

	private boolean inFiringRange() {
		return this.getYCenter() >= 403 && this.getYCenter() <= 406;
	}

    /**
     * Fires an Alien Missile in the -2, 0, 2 slope
     */
    public void fire() {
        list.add(new AlienMissile(this, AlienMissile.Slope.Left));
        list.add(new AlienMissile(this, AlienMissile.Slope.Down));
        list.add(new AlienMissile(this, AlienMissile.Slope.Right));
    }

    /**
     * This method should be used instead of just increasing
     * toSpot because it updates all of the other field variables
     * to accurate reflect the state of the alien
     */
    public void startAttack() {
        isAttacking = true;
        y += attackingSpeed;
    }

    @Override
    public void reset() {}
}
