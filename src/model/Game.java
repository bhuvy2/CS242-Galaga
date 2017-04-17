package model;

import model.ships.*;
import java.util.ArrayList;

/**
 * Stores basic information and data for current game.
 */
public abstract class Game {
    private static ArrayList<Alien> enemies;
    private static Ship playerShip = new BasicShip();
    private static int shotsFired,
            shotsHit,
            points,
            enemiesKilled,
            toNextLife;
    private static volatile int level = 1;
    private static boolean gameover; // For game ending condition

    /**
     * Populates game window with aliens in their given positions.
     */
    public static void populate() {
		ArrayList<Alien> enemies = new ArrayList<>();
		Alien in;
		for (int[] a : Alien.BasicPosition) {
			in = new BasicAlien(a[1], a[0]);
			in.isMoving = true;
			enemies.add(in);
		}
		for (int[] a : Alien.RedPosition) {
			in = new RedAlien(a[1], a[0]);
			in.isMoving = true;
			enemies.add(new RedAlien(a[1], a[0]));
		}
		for (int[] a : Alien.BossPosition) {
			in = new BlueAlien(a[1], a[0]);
			in.isMoving = true;
			enemies.add(new BlueAlien(a[1], a[0]));
		}
		Game.setEnemies(enemies);
	}

    /**
     * Retrievves list of aliens present
     * @return list of aliens
     */
    public static ArrayList<Alien> getEnemies() {
        return enemies;
    }

    /**
     * Sets aliens to be displayed
     * @param aliens alien list
     */
    public static void setEnemies(ArrayList<Alien> aliens) {
        enemies = aliens;
    }

    /**
     * Retrieves player's ship
     * @return player ship
     */
    public static Ship getPlayerShip() {
        return playerShip;
    }

    /**
     * Sets player ship
     * @param playerShip ship to be set
     */
    public void setPlayerShip(Ship playerShip) {
        Game.playerShip = playerShip;
    }

    /**
     * Shots fired during game
     * @return total fired
     */
    public static int getShotsFired() {
        return shotsFired;
    }

    /**
     * Updates shots fired in game
     * @param shotsFired new value of fired shots
     */
    public static void setShotsFired(int shotsFired) {
        Game.shotsFired = shotsFired;
    }

    /**
     * Number of connected shots
     * @return shots hit
     */
    public static int getShotsHit() {
        return shotsHit;
    }

    /**
     * Updates shots hit
     * @param shotsHit total shots hit
     */
    public static void setShotsHit(int shotsHit) {
        Game.shotsHit = shotsHit;
    }

    /**
     * Retrieves points earned
     * @return points earned
     */
    public static int getPoints() {
        return points;
    }

    /**
     * Sets points earned
     * @param points new value of earned points
     */
    public static void setPoints(int points) {
        Game.points = points;
    }

    /**
     * Retrieves total enemies killed
     * @return enemeies killed
     */
    public static int getEnemiesKilled() {
        return enemiesKilled;
    }

    /**
     * Sets enemies killed
     * @param enemiesKilled new value of enemies killed
     */
    public static void setEnemiesKilled(int enemiesKilled) {
        Game.enemiesKilled = enemiesKilled;
    }

    /**
     * Points needed to get another life, currently set at every 5000
     * @return points needed for new life
     */
    public static int getToNextLife() {
        return toNextLife;
    }

    /**
     * Set points needed for next life
     * @param toNextLife points needed
     */
    public static void setToNextLife(int toNextLife) {
        Game.toNextLife = toNextLife;
    }

    /**
     * Retrieves current game level
     * @return level of game
     */
    public static int getLevel() {
        return level;
    }

    /**
     * Sets level of the game
     * @param level to be set
     */
    public static void setLevel(int level) {
        Game.level = level;
    }

    /**
     * Gameover identifier
     * @return if game has ended
     */
    public static boolean isGameover() {
        return gameover;
    }

    /**
     * Sets whether game has ended - ship has no more lives
     * @param over true if game ended
     */
    public static void setGameover(boolean over) {
        gameover = over;
    }
}