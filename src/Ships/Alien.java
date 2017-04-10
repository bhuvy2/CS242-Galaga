package Ships;

import Gametypes.GameSprite;
import display.view.GameWindow;

/**
 * Created by mscislowski on 4/9/17.
 */
public abstract class Alien extends GameSprite {
    protected int health,   //How many shots it takes
            points,         //How many points each alien gets for killing it
            row,            //Keeps the running row so that the alien can revert back to this
            column,         //To keep a running column so that the alien can revert back to this
            count = 0;      //As a mediator count to not go so fast

    protected volatile java.util.ArrayList<AlienMissile> list; //Like Storage for ship

    protected static boolean isMovingRight;
    protected boolean isAttacking;
    protected boolean isMoving = true;

    protected volatile static int amountAttacking = 0, //Running count of how many are attacking so not too many are attacking
            DELAY = 16,
            baseHealth = 0,
            basePoints = 100; //Delay is increased to slow the game down
    public static final int MAX_DELAY = 14;

    public static final int[][] BasicPosition =
            {{18,15}, {26, 15}, {34,15},
                    {42,15}, {50, 15}, {58, 15},
                    {66, 15}, {74, 15},{82, 15},
            }; //The X,Y Coordinates of the Basic Alien formation
    public static final int[][] RedPosition =
            {{15, 20}, {23, 20}, {31, 20},
                    {39, 20}, {47, 20}, {55, 20},
                    {63, 20}, {71, 20}, {79, 20},
                    {87, 20}}; //Same but Red Formation
    public static final int[][] BossPosition =
            {{15, 10}, {25, 10}, {35, 10},
                    {45, 10}, {55, 10}, {65, 10},
                    {75, 10}, {85, 10}}; //Same but boss Formation

    public abstract void attack(); //A specific attack for each alien


    public int getHealth() {
        return health;
    }

    public void setHealth(int input) {
        health = input;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int input) {
        points = input;
    }

    /**
     * Returns the alien to the position
     */
    public void returnToPosition() {
        toRight = column;
        toEdge = row;
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

    /**
     * Checks if is hit.
     *
     * @param storage is not Null
     *            the storage variable comes from the ship class.
     *            The array scans all missiles to see if it would work
     * @return true, if is hit
     */
    public boolean isHit(java.util.ArrayList<Missile> storage) {
        if(storage.size() == 0)
            return false;

        int right = this.getToRight() / 100 * GameWindow.getBoardWidth();
        int edge = this.getToEdge() / 100 * GameWindow.getBoardHeight();

        for(Missile a: storage) {
            if(a.getToRight() >= right && a.getToRight() <= right + image.getIconWidth())
                if(a.getToEdge() >= edge &&  a.getToEdge() <= edge + image.getIconHeight()) {
                    storage.remove(a);
                    if(isAttacking)
                        Alien.amountAttacking--;
                    return true;
                }
        }
        return false;
    }

    /**
     * Moves the Alien back and forth
     */
    public void move() {
        if(isMoving) {
            if(count % (DELAY*1.5) == 0) {
                if(isMovingRight) {
                    toRight++;
                    column++;
                } else {
                    toRight--;
                    column--;
                }

                if(toRight <= 3) {
                    isMovingRight = true;
                    toRight+= 2;
                } else if (toRight >= 97) {
                    isMovingRight = false;
                }
            }
        }
        this.count++;
    }
}
