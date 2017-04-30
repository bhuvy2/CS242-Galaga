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
    	AlienMissile.Slope slps[] = {AlienMissile.Slope.ShallowLeft, 
                AlienMissile.Slope.Down,
                AlienMissile.Slope.ShallowRight};
        for (AlienMissile.Slope i : slps) {
            AlienMissile m = new AlienMissile(this, i);
            m.setBounce(true, health/5);
            list.add(m);
        }
    }

    public void reset() {
        toSpot = 0;
    }

}
