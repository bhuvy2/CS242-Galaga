package model.ships;

import display.view.GameWindow;

import java.awt.image.BufferedImage;
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
        super("res/img/alien/green_alien.png");
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
            if (count % DELAY == 0)
                y++;

            if (y == row + 1) {
                Alien.amountAttacking++;
                y++;
            }

            if (this.getFormattedY() >= GameWindow.getBoardHeight() * .6 &&
                    this.getFormattedY() <= GameWindow.getBoardHeight() * .61)
                fire();
            else if (this.getFormattedY() >= GameWindow.getBoardHeight())
                y = 0;
            else if (y == row) {
                isAttacking = false;
                if (count % DELAY == 0)
                    Alien.amountAttacking--;
            }
        }
    }
}
