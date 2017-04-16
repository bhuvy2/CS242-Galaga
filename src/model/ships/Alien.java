package model.ships;

import display.view.GameWindow;
import main.Game;
import model.superclasses.GameSprite;

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

    public volatile java.util.ArrayList<AlienMissile> list; //Like Storage for ship

    protected static boolean isMovingRight;
    public boolean isAttacking;
    public boolean isMoving = true;

    protected volatile static int amountAttacking = 0; //Running count of how many are attacking so not too many are attacking
    public volatile static int DELAY = 10;
    protected volatile static int baseHealth = 0;
    protected volatile static int basePoints = 100; //Delay is increased to slow the game down
    public static final int MAX_DELAY = 14;

    public static final int[][] BasicPosition =
            {{80,72}, {112, 72}, {144,72},
                    {176,72}, {208, 72}, {240, 72},
                    {272, 72}, {304, 72},{336, 72},
            }; //The X,Y Coordinates of the Basic Alien formation
    public static final int[][] RedPosition =
            {{80,96}, {112, 96}, {144,96},
                    {176,96}, {208, 96}, {240, 96},
                    {272, 96}, {304, 96},{336, 96},
            }; //Same but Red Formation
    public static final int[][] BossPosition =
            {{80,48}, {112, 48}, {144,48},
                    {176,48}, {208, 48}, {240, 48},
                    {272, 48}, {304, 48},{336, 48},
            }; //Same but boss Formation

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

    /**
     * Checks if is hit.
     *
     * @param storage is not Null
     *            the storage variable comes from the ship class.
     *            The array scans all missiles to see if it would work
     * @return true, if is hit
     */
    public boolean isHit(ArrayList<Missile> storage) {
        if(storage.size() == 0)
            return false;

        int right = this.getX() / 100 * GameWindow.getBoardWidth();
        int edge = this.getY() / 100 * GameWindow.getBoardHeight();

        for(Missile a: storage) {
            if(a.getX() >= right && a.getX() <= right + image.getIconWidth())
                if(a.getY() >= edge &&  a.getY() <= edge + image.getIconHeight()) {
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
            if(x <= 0) {
                isMovingRight = true;
            } else if (x >= GameWindow.getBoardWidth() - 16) {
                isMovingRight = false;
            }
            
            if(count % (DELAY) == 0) {
                if(isMovingRight) {
                    x+=2;
                } else {
                    x -= 2;
                }
            }
        }
        this.count++;
    }

    public void tick() {
//        System.out.println(DELAY);
        System.out.println(amountAttacking);
        setAttackers();
        move();
        attack();
    }

    public void setAttackers() {
        Alien a;
        for (int i = 0; i < Game.getEnemies().size(); i++) {
            a = Game.getEnemies().get(i);
            if ((getAmountAttacking() < Game.getLevel() + 1 && Math.random() > .8 ||
                    amountAttacking == 0) && !a.isAttacking()) {
                System.out.println("Sending atk with: " + amountAttacking);
                if (a instanceof BasicAlien) {
                    ((BasicAlien) a).startAttack();
                    System.out.println("BASIC : " + i);
                } else if (a instanceof BlueAlien) {
                    ((BlueAlien) a).startAttack();
                    System.out.println("BLUE : " + i);
                } else if (a instanceof RedAlien) {
                    ((RedAlien) a).startAttack();
                    System.out.println("RED : " + i);
                }
            }
            a.attack();
            boolean removed = checkHit(a);
            if (removed)
                i--;
        }

    }

    private boolean checkHit(Alien alien) {
        //Not hit
        if (!alien.isHit(Game.getPlayerShip().getStorage())) {
            move();
        } else if ((alien.getHealth() > 1)) { // Blue alien with more than one health
            alien.hit();
            alien.move();
            if (alien instanceof BlueAlien)
                ((BlueAlien) alien).change();
        } else { // Killed alien
            //Update points
            Game.setPoints(alien.getPoints());
            Game.setToNextLife(Game.getToNextLife() + alien.getPoints());
            if (Game.getToNextLife() >= 5000) {
                Game.setToNextLife(Game.getToNextLife() - 5000);
                if (Game.getPlayerShip().getLives() <= 16)
                    Game.getPlayerShip().setLives(Game.getPlayerShip().getLives() + 1);
            }
            // Remove alien
            Game.getEnemies().get(0).getList().addAll(alien.getList());
            Game.getEnemies().remove(alien);
            Game.getEnemies().trimToSize();
            Alien.DELAY++;
            Game.setEnemiesKilled(Game.getEnemiesKilled() + 1);
            return true;
        }
        return false;
    }

    /**
     * Reset aliens a enemies back to attack formation after being hit
     */
    public static void resetAttack() {
        for (Alien a : Game.getEnemies()) {
            a.getList().removeAll(a.getList());
            if (a.isAttacking())
                Alien.setAmountAttacking(Alien.getAmountAttacking() - 1);
            a.setAttacking(false);
            a.setMoving(true);
            a.returnToPosition();
        }

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

    public static int getAmountAttacking() {
        return amountAttacking;
    }

    public static int getBasePoints() {
        return basePoints;
    }

    public static void setAmountAttacking(int amountAttacking) {
        Alien.amountAttacking = amountAttacking;
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
}
