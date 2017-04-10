package model.ships;

import display.view.GameWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mscislowski on 4/9/17.
 */
public class RedAlien extends Alien {
    private double angle;
    private int toSpot;
    private static final int circleDiameter = 30;

    /**
     * Constructor for red alien
     * @param toEdge distance to edge
     * @param toRight distance to right side
     */
    public RedAlien(int toEdge, int toRight) {
        this.y = toEdge;
        this.x = toRight;
        row = toEdge;
        column = toRight;
        angle = 0;
        isMovingRight = true;

        points = basePoints + 100;
        list = new ArrayList<AlienMissile>();
        health = baseHealth+ 1;
        toSpot = 0;
    }

    /**
     * Handles attack patterns for Red alien
     */
    public void attack() {
        if(isAttacking) {
            double radTemp;
            switch(toSpot) {
                case 0:
                    break;
                case 1:
                    if(count % (DELAY*1.5) == 0)
                        y++;
                    if(this.getFormattedEdge() >= GameWindow.getBoardHeight()*.6 &&
                            this.getFormattedEdge() <= GameWindow.getBoardHeight() * .61)
                        fire();
                    if(this.getFormattedEdge() >= GameWindow.getBoardHeight())
                        toSpot++;
                    break;
                case 2:
                    if(count % DELAY == 0)
                        angle +=2;
                    if(isLeft())
                        radTemp = Math.toRadians(angle);
                    else
                        radTemp = Math.toRadians(180-angle);
                    x = column + circleDiameter/2+ (int)(Math.cos(radTemp)*circleDiameter);
                    y = 95 - (int)(Math.sin(radTemp)*circleDiameter/2);
                    if(angle >= 180) {
                        angle = 0;
                        y = 0;
                        toSpot++;
                    }
                    break;
                case 3:
                    if(count%DELAY == 0)
                        y++;
                    if(y == row) {
                        if(count % DELAY == 0)
                            Alien.amountAttacking--;
                        isAttacking = false;
                        isMoving = true;
                        toSpot = 0;
                        x = column;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Starts attack for alien
     */
    public void startAttack() {
        if(toSpot == 0) {
            toSpot++;
            Alien.amountAttacking++;
            isMoving = false;
            isAttacking = true;
        }
    }

    /**
     * Checks if is left.
     * @return true, if is left
     */
    private boolean isLeft() {
        return column < 50;
    }
}
