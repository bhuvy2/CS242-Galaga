package model.ships;

import display.view.GameWindow;
import io.GameConfig;

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
        super(GameConfig.getRedPath());
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
        if (isAttacking) {
            double radTemp;
            switch (toSpot) {
                case 0:
                    break;
                case 1: // Moving to attack and firing along path
                    if (count % (DELAY) == 0)
                        y += 4;
                    if (this.getYCenter() >= 400 && this.getYCenter() <= 403)
                        this.fire();
                    if (this.getYCenter() >= 200 && this.getYCenter() <= 203)
                        this.fire();
                    if (this.getY() >= GameWindow.BOARD_HEIGHT)
                        toSpot++;
                    break;
                case 2: // delay
                    if (count % DELAY == 0)
                        angle += 4;
//                    if (isLeft())
//                        radTemp = Math.toRadians(angle);
//                    else
//                        radTemp = Math.toRadians(180 - angle);
//                    x = column + circleDiameter / 2 + (int) (Math.cos(radTemp) * circleDiameter);
//                    y = 644 - (int)(Math.sin(radTemp)*circleDiameter/2);
                    if (angle >= 180) {
                        angle = 0;
                        y = 0;
                        toSpot++;
                    }
                    break;
                case 3: // Returns
                    if (count % DELAY == 0)
                        y += 4;
                    if (y == row) {
                        isAttacking = false;
                        isMoving = true;
                        toSpot = 0;
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
            isAttacking = true;
        }
    }

    /**
     * Fires an Alien Missile in the -1, 0, 1 slope
     */
    public void fire() {
        for (int i = -1; i < 2; i++) {
            AlienMissile m = new AlienMissile(this, i);
            m.setBounce(true, health/5);
            list.add(m);
        }
    }

    public void reset() {
        toSpot = 0;
    }

    @Override
    public void change() {

    }
}
