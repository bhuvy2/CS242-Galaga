package model.ships;

import display.view.GameWindow;
import io.GameConfig;

import java.util.ArrayList;

/**
 * Created by mscislowski on 4/9/17.
 */
public class BasicAlien extends Alien {
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
        super(GameConfig.getGreenPath());
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
        if (isAttacking) {
            if (count % DELAY == 0) {
                y+=4;
            }

            if (this.getY() >= 400 && this.getY() <= 405)
                fire();
            else if (this.getY() >= GameWindow.getBoardHeight())
                y = 0;
            else if (y == row) {
                isAttacking = false;
                isMoving = true;
                if (count % DELAY == 0)
                    Alien.amountAttacking--;
            }
        }
    }

    /**
     * This method should be used instead of just increasing
     * toSpot because it updates all of the other field variables
     * to accurate reflect the state of the alien
     */
    public void startAttack() {
            isAttacking = true;
            isMoving = false;
            Alien.amountAttacking++;
    }
}
