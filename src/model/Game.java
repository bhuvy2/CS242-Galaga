package model;

import model.ships.*;
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
            toNextLife;
    private volatile int level = 1;
    private boolean gameOver;
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
    
    public void tick() {
    	playerShip.tick();
    	this.setAttackers();
    	for(Alien al: enemies){
    		al.tick();
    	}
//    	this.checkShipHit();
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
    
    private boolean checkShipHit(){
    	if (!playerShip.isInvincible() && isHit(this.getEnemies())) {
            if (playerShip.getLives() > 0) {
            	playerShip.setLives(playerShip.getLives() - 1);
                this.resetAttack();
            } else {
                this.gameOver = true;
            }
        }
    	return true;
    }
    
    private boolean checkHit(Alien alien) {
        if (!alien.isHit(getPlayerShip().getStorage())) {
            alien.move();
        } else if ((alien.getHealth() > 1)) { // Blue alien with more than one health
            alien.hit();
            alien.move();
            if (alien instanceof AdvancedAlien)
                ((AdvancedAlien) alien).change();
        } else {

            setPoints(alien.getPoints());
            setToNextLife(getToNextLife() + alien.getPoints());
            if (getToNextLife() >= 5000) {
                setToNextLife(getToNextLife() - 5000);
                if (getPlayerShip().getLives() <= 16)
                    getPlayerShip().setLives(getPlayerShip().getLives() + 1);
            }
            // Remove alien
            getEnemies().get(0).getList().addAll(alien.getList());
            getEnemies().remove(alien);
            getEnemies().trimToSize();
            Alien.DELAY++;
            setEnemiesKilled(getEnemiesKilled() + 1);
            return true;
        }
        return false;
    }
    
    public void setAttackers() {
        Alien a;
        int attacking = getAmountAttacking();
        for (int i = 0; i < getEnemies().size(); i++) {
            a = getEnemies().get(i);
            double rand = Math.random();
            if ((attacking < this.getLevel() + 1 && rand > .8 || attacking == 0) && !a.isAttacking()) {
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
//            boolean removed = checkHit(a);
//            if (removed)
//                i--;
        }

    }
    
    private int getAmountAttacking() {
		int i = 0;
		for(Alien al: this.getEnemies()){
			if(al.isAttacking){
				i += 1;
			}
		}
		return i;
	}

	public boolean isHit(ArrayList<Alien> input) {
    	/*
        int nextRight;
        int nextEdge;

        for(Alien b : input) {
            for(AlienMissile a : b.list) {
                nextRight = this.getFormattedX(a.toRightNext);
                nextEdge = this.getFormattedY(a.toEdgeNext);
                if(nextRight >= right && nextRight <= right + image.getIconWidth()) {
                    if(nextEdge >= edge &&  nextEdge <= edge + image.getIconHeight()) {
                        b.list.remove(a);
                        return true;
                    }
                }
            }

            nextRight = b.getFormattedX();
            nextEdge = b.getFormattedY();

            if(nextRight >= right && nextRight <= right + image.getIconWidth() ||
                    nextRight + b.getImage().getIconWidth() >= right && nextRight +
                            b.getImage().getIconWidth() <= right + image.getIconWidth())
                if(nextEdge >= edge &&  nextEdge <= edge + image.getIconHeight() ||
                        nextEdge + b.getImage().getIconHeight() >= edge &&  nextEdge +
                                b.getImage().getIconHeight() <= edge + image.getIconHeight()) {
                    input.remove(b);
//                    Alien.amountAttacking--;
                    return true;
                }
        }
        return false;*/
    	return false;
    }

    /**
     * Populates game window with aliens in their given positions.
     */
    public void populate() {
		ArrayList<Alien> enemies = new ArrayList<>();
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
		this.setEnemies(enemies);
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
}