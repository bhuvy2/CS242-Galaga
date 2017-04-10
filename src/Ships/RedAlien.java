package Ships;

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

    public RedAlien(int toEdge, int toRight) {
        this.toEdge = toEdge;
        this.toRight = toRight;
        row = toEdge;
        column = toRight;
        angle = 0;
        isMovingRight = true;

        points = basePoints + 100;
        list = new ArrayList<AlienMissile>();
        health = baseHealth+ 1;
        toSpot = 0;
    }

    public void attack() {
        if(isAttacking) {
            double radTemp;
            switch(toSpot) {
                case 0:
                    break;
                case 1:
                    if(count % (DELAY*1.5) == 0)
                        toEdge++;
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
                    toRight = column + circleDiameter/2+ (int)(Math.cos(radTemp)*circleDiameter);
                    toEdge = 95 - (int)(Math.sin(radTemp)*circleDiameter/2);
                    if(angle >= 180) {
                        angle = 0;
                        toEdge = 0;
                        toSpot++;
                    }
                    break;
                case 3:
                    if(count%DELAY == 0)
                        toEdge++;
                    if(toEdge == row) {
                        if(count % DELAY == 0)
                            Alien.amountAttacking--;
                        isAttacking = false;
                        isMoving = true;
                        toSpot = 0;
                        toRight = column;
                    }
                    break;
                default:
                    break;
            }
        }
    }

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
