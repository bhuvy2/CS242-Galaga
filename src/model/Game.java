package model;

import model.ships.*;
import model.superclasses.GameSprite;

import java.awt.*;
import java.util.ArrayList;

/**
 * Stores basic information and data for current game.
 */
public class Game {
    private ArrayList<Alien> enemies;
    private Ship playerShip = new BasicShip();
    private int shotsFired,
            shotsHit,
            points,
            enemiesKilled,
            toNextLife = 5000;
    private volatile int level = 1;
    private boolean gameOver;
    private int deathAni = 0, deathCount = 0;
	public static final int[][] BasicPosition =
	{{70,88}, {110, 88}, {150,88},
	        {190,88}, {230, 88}, {270, 88},
	        {310, 88}, {350, 88}, {390, 88},
	        {430, 88},
	};
	
	public static final int[][] RedPosition =
        {{90,112}, {130, 112}, {170,112},
                {210,112}, {250, 112}, {290, 112},
                {330, 112}, {370, 112},{410, 112},
        };
	
	public static final int[][] BossPosition =
        {{84, 48}, {124, 48}, {164,48},
                {204,48}, {244, 48}, {284, 48},
                {324, 48}, {364, 48},{404, 48},
        };
    
    enum State {
    	GameIntro,
    	NormalStage,
    	BonusStage,
    };

    // Creates initial game
    public Game(){
    	this.populate();
    }

    /**
     * Handles each action that should take place per tick of the game
     */
    public void tick(){
        checkLevelClear();
        checkDead();
    	playerShip.tick();
    	this.setAttackers();
    	Alien al;
    	for (int i = 0; i< this.enemies.size(); i++) {
    	    al = getEnemies().get(i);
    		al.tick();
    		if(checkKilled(al)) {
    			this.enemies.remove(i);
                i--;
            }
    	}
    	this.checkShipHit();
    }
    
    /**
     * Reset aliens a enemies back to attack formation after being hit
     */
    public void resetAttack() {
        for (Alien a : this.getEnemies()) {
            a.getList().removeAll(a.getList());
            a.setAttacking(false);
            a.setMoving(true);
            a.returnToPosition();
        }
    }

    /**
     * Checks if ship was hit. Decrements lives and starts death animation.
     * Sets gameover to true if last life.
     * @return true if ship was hit
     */
    private boolean checkShipHit(){
    	if (!playerShip.isInvincible() && isHit()) {
            if (playerShip.getLives() > 0) {
            	playerShip.setLives(playerShip.getLives() - 1);
            	deathAni = 1;
            } else {
                this.gameOver = true;
            }
        }
    	return true;
    }

    /**
     * Checks if ship is performing death animation and updates ship icon.
     * Ship is set to invincible and cannot move during this time.
     */
    private void checkDead() {
        if (deathAni == 1) {
            deathCount++;
            // Set animation states
            if (deathCount == 1) {
                playerShip.setCanMove(false);
                playerShip.setInvincible(true);
                playerShip.change();
            } else if (deathCount == 6) {
                playerShip.change();
            } else if (deathCount == 11) {
                playerShip.change();
            } else if (deathCount == 16) {
                playerShip.change();
            } else if (deathCount == 21) {
                playerShip.change();
                deathCount = 0;
                deathAni = 0;
                playerShip.setCanMove(true);
                playerShip.setInvincible(false);
                this.resetAttack();
            }
        }
    }

    /**
     * Checks if alien is killed, removes alien and missiles if it was killed.
     * Updates sprite if damaged.
     * Accumulates points and checks if ship gets additional life for new points.
     * @param alien alien being checked
     * @return true if dead
     */
    private boolean checkKilled(Alien alien) {
        Missile m;
        // Checks missilses
        for (int i = 0; i < playerShip.getStorage().size(); i++) {
            m = playerShip.getStorage().get(i);
            if (checkBounds(alien, m)) {
                // Change alien if health is more than 1
                if (alien.getHealth() > 1) {
                    alien.setHealth(alien.getHealth() - 1);
                    playerShip.getStorage().remove(i);
                    i--;
                    shotsHit++;
                    alien.change();
                } else {
                    // Accumulate points if ship is not invincible
                    if (!playerShip.isInvincible()) {
                        points += alien.getPoints();
                        toNextLife -= alien.getPoints();
                        check1up();
                    }
                    // Remove missile and increment stats
                    playerShip.getStorage().remove(i);
                    enemiesKilled++;
                    shotsHit++;
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Randomly determines which aliens should be attacking
     */
    public void setAttackers() {
        Alien a;
        int attacking = getAmountAttacking();
        for (int i = 0; i < getEnemies().size() && attacking < this.getLevel() + 1; i++) {
            a = getEnemies().get(i);
            double rand = Math.random();
            // If chosen call attack
            if ((rand > .99 || attacking == 0) && !a.isAttacking()) {
                if (a instanceof BasicAlien) {
                    attacking++;
                    ((BasicAlien) a).startAttack();
                } else if (a instanceof AdvancedAlien) {
                    attacking++;
                    ((AdvancedAlien) a).startAttack();
                } else if (a instanceof RedAlien) {
                    attacking++;
                    ((RedAlien) a).startAttack();
                }
            }
        }
    }

    /**
     * Checks amount of aliens attacking
     * @return number of attackers
     */
    private int getAmountAttacking() {
		int i = 0;
		for(Alien al: this.getEnemies()){
			if(al.isAttacking){
				i += 1;
			}
		}
		return i;
	}

    /**
     * Checks if ship is hit. Compared to center of ship rather than entire sprite
     * @return
     */
	public boolean isHit() {
        Alien a;
        for (int i = 0; i < getEnemies().size(); i++) {
            a = getEnemies().get(i);
            AlienMissile m;
            for (int j = 0; j < a.getList().size(); j++) {
                m = a.getList().get(j);
                if (checkShipBounds(playerShip, m)) {
                    a.getList().remove(j);
                    j--;
                    return true;
                }
            }
            if (checkShipBounds(playerShip, a))
                return true;
        }
        return false;
    }

    /**
     * Checks if sprite overlaps center of ship sprite
     * @param a ship
     * @param g enemy sprite or missile
     * @return true if overlap
     */
    private boolean checkShipBounds(GameSprite a, GameSprite g) {
        return a.getXCenter() >= g.getX() &&
                a.getXCenter() <= g.getX() + g.getImage().getIconWidth() &&
                a.getYCenter() >= g.getY() &&
                a.getYCenter() <= g.getY() + g.getImage().getIconHeight();
    }

    /**
     * Checks if any part of the sprites overlap
     * @param a enemy alien
     * @param g ship missiles
     * @return true if overlap
     */
    private boolean checkBounds(GameSprite a, GameSprite g) {
        return a.getX() < g.getX() + g.getImage().getIconWidth() &&
                a.getX() + a.getImage().getIconWidth() > g.getX() &&
                a.getY() < g.getY() + g.getImage().getIconHeight() &&
                a.getY() + a.getImage().getIconHeight() > g.getY();
    }

    /**
     * Populates level if level cleared and increments level
     */
    private void checkLevelClear() {
        if (getEnemies().size() == 0) {
            setLevel(level++);
            playerShip.getStorage().removeAll(playerShip.getStorage());
            populate();
        }
    }

    /**
     * Adds ship life if 5000 points are earned then resets counter
     */
    private void check1up() {
        if (toNextLife <= 0) {
            toNextLife = toNextLife+5000;
            playerShip.setLives(playerShip.getLives() + 1);
        }
    }
    /**
     * Populates game window with aliens in their given positions.
     */
    private void populate() {
		enemies = new ArrayList<>();
		Alien in;
		for (int[] a : Game.BasicPosition) {
			in = new BasicAlien(a[1], a[0]);
			in.isMoving = true;
			enemies.add(in);
		}
		for (int[] a : Game.RedPosition) {
			in = new RedAlien(a[1], a[0]);
			in.isMoving = true;
			enemies.add(new RedAlien(a[1], a[0]));
		}
		for (int[] a : Game.BossPosition) {
			in = new AdvancedAlien(a[1], a[0]);
			in.isMoving = true;
			enemies.add(new AdvancedAlien(a[1], a[0]));
		}
	}

    /**
     * Retrievves list of aliens present
     * @return list of aliens
     */
    public ArrayList<Alien> getEnemies() {
        return enemies;
    }

    /**
     * Sets aliens to be displayed
     * @param aliens alien list
     */
    public void setEnemies(ArrayList<Alien> aliens) {
        enemies = aliens;
    }

    /**
     * Retrieves player's ship
     * @return player ship
     */
    public Ship getPlayerShip() {
        return playerShip;
    }

    /**
     * Sets player ship
     * @param playerShip ship to be set
     */
    public void setPlayerShip(Ship playerShip) {
        this.playerShip = playerShip;
    }

    /**
     * Shots fired during game
     * @return total fired
     */
    public int getShotsFired() {
        return shotsFired;
    }

    /**
     * Updates shots fired in game
     * @param shotsFired new value of fired shots
     */
    public void setShotsFired(int shotsFired) {
        this.shotsFired = shotsFired;
    }

    /**
     * Number of connected shots
     * @return shots hit
     */
    public int getShotsHit() {
        return this.shotsHit;
    }

    /**
     * Updates shots hit
     * @param shotsHit total shots hit
     */
    public void setShotsHit(int shotsHit) {
        this.shotsHit = shotsHit;
    }

    /**
     * Retrieves points earned
     * @return points earned
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets points earned
     * @param points new value of earned points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Retrieves total enemies killed
     * @return enemeies killed
     */
    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    /**
     * Sets enemies killed
     * @param enemiesKilled new value of enemies killed
     */
    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    /**
     * Points needed to get another life, currently set at every 5000
     * @return points needed for new life
     */
    public int getToNextLife() {
        return this.toNextLife;
    }

    /**
     * Set points needed for next life
     * @param toNextLife points needed
     */
    public void setToNextLife(int toNextLife) {
        this.toNextLife = toNextLife;
    }

    /**
     * Retrieves current game level
     * @return level of game
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level of the game
     * @param level to be set
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
    public boolean isGameOver(){
    	return this.gameOver;
    }

    /**
     * Draws all aliens
     * @param c
     * @param g
     */
    public void draw(Component c, Graphics g){
    	this.playerShip.drawSelf(c, g);
    	for(Alien al: this.getEnemies()){
    		al.drawSelf(c, g);
    	}
    }

    /**
     * Resets game to level 1 and clears all stats
     */
    public void resetGame() {
        points = 0;
        level = 1;
        shotsFired = 0;
        shotsHit = 0;
        enemiesKilled = 0;
        toNextLife = 5000;
        playerShip.getStorage().removeAll(playerShip.getStorage());
        getEnemies().removeAll(getEnemies());
        populate();
        resetAttack();
    }
}