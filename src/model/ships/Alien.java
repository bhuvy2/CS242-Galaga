package model.ships;

import display.view.GameWindow;
import model.superclasses.GameSprite;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mscislowski on 4/9/17.
 */
public abstract class Alien extends GameSprite {
    protected int health,   //How many shots it takes
            points,         //How many points each alien gets for killing it
            row,            //Keeps the running row so that the alien can revert back to this
            column,         //To keep a running column so that the alien can revert back to this
            count = 0;      //As a mediator count to not go so fast

    public volatile ArrayList<AlienMissile> list; //Like Storage for ship

    protected static boolean isMovingRight;
    public boolean isAttacking;
    public boolean isMoving = true;

    public static int DELAY = 2;
    protected static int baseHealth = 0;
    protected static int basePoints = 100; //Delay is increased to slow the game down
    public static final int MAX_DELAY = 14;

    /**
     * Gamesprite contructor
     *
     * @param image string to image file
     */
    protected Alien(String image) {
        super(image);
    }

    public abstract void attack(); //A specific attack for each alien

    /**
     * Moves the Alien back and forth
     */
    public void move() {
        if(isMoving) {
            if(x <= 0) {
                isMovingRight = true;
            } else if (x >= GameWindow.getBoardWidth() - 16) {
                isMovingRight = false;
            }
            
            if(count % (DELAY) == 0) {
                if(isMovingRight) {
                    x+=2;
                } else {
                    x-=2;
                }
            }
        }
    }

    public void drawSelf(Component c, Graphics g) {
        this.image.paintIcon(c, g, this.x, this.y);
        for (AlienMissile m : list) {
            m.getImage().paintIcon(c, g, m.getX(), m.getY());
        }
    }

    public void tick() {
        // Missile movement
        AlienMissile m;
        for (int i = 0; i < list.size(); i++) {
            m = list.get(i);
            m.move();
            if (!m.isVisible()) {
                list.remove(i);
                i--;
            }
        }

        // Alien move and attack
        move();
        attack();
        this.count++;
    }


    public boolean isAttacking() {
        return isAttacking;
    }

    public ArrayList<AlienMissile> getList() {
        return list;
    }

    public static boolean isIsMovingRight() {
        return isMovingRight;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public static int getBaseHealth() {
        return baseHealth;
    }

    public static int getBasePoints() {
        return basePoints;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public static void setDELAY(int DELAY) {
        Alien.DELAY = DELAY;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
    
    /**
     * Retrieves health of alien
     * @return health value
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets health value of alien
     * @param input new health value
     */
    public void setHealth(int input) {
        health = input;
    }

    /**
     * Retrieves points alien is worth
     * @return points alien is worth
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets points alien is worth
     * @param input new point value
     */
    public void setPoints(int input) {
        points = input;
    }

    /**
     * Returns the alien to the position
     */
    public void returnToPosition() {
        x = column;
        y = row;
    }

    //subtracts one from health
    public void hit() {
        health--;
    }

    /**
     * Fires an Alien Missile in the -2, 0, 2 slope
     */
    public void fire() {
        list.add(new AlienMissile(this, -2));
        list.add(new AlienMissile(this, 0));
        list.add(new AlienMissile(this, 2));
    }

}
