package model.ships;

import display.view.GameWindow;
import main.Game;

import java.util.ArrayList;

/**
 * Created by mscislowski on 4/9/17.
 */
public class RedAlien extends Alien {
    private double angle;
    private int toSpot;
    private static final int circleDiameter = 100;

    /**
     * Constructor for red alien
     * @param toEdge distance to edge
     * @param toRight distance to right side
     */
    public RedAlien(int toEdge, int toRight) {
        super("res/img/alien/red_alien.png");
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
                    if(count % (DELAY) == 0)
                        y+=2;
                    if(this.getY() >= 400 && this.getY() <= 405)
                        fire();
                    if(this.getY() >= GameWindow.getBoardHeight())
                        toSpot++;
                    break;
                case 2:
                    if(count % DELAY == 0)
                        angle +=4;
                    if(isLeft())
                        radTemp = Math.toRadians(angle);
                    else
                        radTemp = Math.toRadians(180-angle);
                    x = column + circleDiameter/2+ (int)(Math.cos(radTemp)*circleDiameter);
                    y = Game.getPlayerShip().getY() - (int)(Math.sin(radTemp)*circleDiameter/2);
                    if(angle >= 180) {
                        angle = 0;
                        y = 0;
                        toSpot++;
                    }
                    break;
                case 3:
                    if(count%DELAY == 0)
                        y+=2;
                    if(y == row) {
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
        return x < GameWindow.getBoardWidth()/2;
    }
}
