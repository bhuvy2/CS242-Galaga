package Ships;

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
        this.toEdge = toEdge;
        this.toRight = toRight;
        column = toRight;
        row = toEdge;
        isMovingRight = true;
        points = basePoints;
        list = new ArrayList<AlienMissile>();
        health = baseHealth + 1;
    }

    public void attack() {
        if (isAttacking) {
            if (count % DELAY == 0)
                toEdge++;

            if (toEdge == row + 1) {
                Alien.amountAttacking++;
                toEdge++;
            }

            if (this.getFormattedEdge() >= GameWindow.getBoardHeight() * .6 &&
                    this.getFormattedEdge() <= GameWindow.getBoardHeight() * .61)
                fire();
            else if (this.getFormattedEdge() >= GameWindow.getBoardHeight())
                toEdge = 0;
            else if (toEdge == row) {
                isAttacking = false;
                if (count % DELAY == 0)
                    Alien.amountAttacking--;
            }
        }
    }
}
