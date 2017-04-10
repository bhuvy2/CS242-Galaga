package model.ships;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by mscislowski on 4/9/17.
 */
public class BlueAlien extends Alien {
    private static final int rowAttack = 60; //The Row in 0-100 where they attack fully
    private int otherCount = 0; //Another count if needed
    private int toSpot = 0; //if 0, it is at spot, if 1 it is moving toward spot, if 2 if attacking, 3 if moving back

    /**
     * Instantiates a new blue alien, saves the column and row as a backup in case need to be reset
     * as well as loads the image, initializes points, creates a new list of Alien Missiles and set health
     * @param toEdge the to edge is a percentage from left to right, of 100, how up or down the alien is the y coordinate.
     * @param toRight the to right is the same as toEdge but it is the x coordinate.
     */
    public BlueAlien(int toEdge, int toRight) {
        super("res/img/alien/blue_alien.png");
        this.y = toEdge;
        this.x = toRight;
        column = toRight;
        row = toEdge;
        isMovingRight = true;

        points = basePoints + 200;
        list = new ArrayList<AlienMissile>();
        health = baseHealth+2;
    }

    /**
     * Handles attack pattern for blue alien
     */
    public void attack() {
        if(isAttacking) {
            if(toSpot == 4)
                toSpot = 0;
            switch(toSpot) {
                case 0:
                    break;
                case 1:
                    if(count % DELAY == 0)
                        y++;
                    if(y == rowAttack) {
                        isMoving = false;
                        toSpot++;
                    }
                    break;
                case 2:
                    otherCount++;
                    if(count % DELAY == 0)
                        fire();
                    if(otherCount == 500) {
                        otherCount = 0;
                        toSpot++;
                    }
                    break;
                case 3:
                    if(count % DELAY == 0)
                        y--;
                    if(y == row) {
                        toSpot++;
                        isMoving = true;
                        isAttacking = false;
                        Alien.amountAttacking--;
                    }
                    break;
            }
        }
    }

    /**
     * This method should be used instead of just increasing
     * toSpot because it updates all of the other field variables
     * to accurate reflect the state of the alien
     */
    public void startAttack() {
        if(toSpot == 0) {
            toSpot++;
            isAttacking = true;
            isMoving = false;
            Alien.amountAttacking++;
        }
    }
}
