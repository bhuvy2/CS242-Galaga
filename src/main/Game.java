package main;

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
    private static boolean gameover;

    public static ArrayList<Alien> getEnemies() {
        return enemies;
    }

    public static void setEnemies(ArrayList<Alien> aliens) {
        enemies = aliens;
    }

    public static Ship getPlayerShip() {
        return playerShip;
    }

    public void setPlayerShip(Ship playerShip) {
        this.playerShip = playerShip;
    }

    public static int getShotsFired() {
        return shotsFired;
    }

    public static void setShotsFired(int shotsFired) {
        Game.shotsFired = shotsFired;
    }

    public static int getShotsHit() {
        return shotsHit;
    }

    public static void setShotsHit(int shotsHit) {
        Game.shotsHit = shotsHit;
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        Game.points = points;
    }

    public static int getEnemiesKilled() {
        return enemiesKilled;
    }

    public static void setEnemiesKilled(int enemiesKilled) {
        Game.enemiesKilled = enemiesKilled;
    }

    public static int getToNextLife() {
        return toNextLife;
    }

    public static void setToNextLife(int toNextLife) {
        Game.toNextLife = toNextLife;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Game.level = level;
    }

    public static boolean isGameover() {
        return gameover;
    }

    public static void setGameover(boolean over) {
        gameover = over;
    }
}