package model.ships;

import display.view.panels.GamePanel;
import model.Game;
import model.Star;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Timer;

public class HitTest {
    GamePanel pnl = new GamePanel(new ArrayList<Star>(), true);
    Timer timer = new Timer();
    Game game = new Game();

    @Test
    public void testGameLoop() {
        pnl.toggleTimer();
        game.tick();
    }

    @Test
    public void testShipHit() {
        BasicShip ship = new BasicShip();
        ship.setX(10);
        ship.setY(10);
        BasicAlien alien = new BasicAlien(10,10);
        ArrayList<Alien> list = new ArrayList<Alien>();
        list.add(alien);
        game.setPlayerShip(ship);
        game.setEnemies(list);
        assert game.isHit();
        game.tick();
        assert ship.getLives() == 1;
    }

    @Test
    public void testShipMissileHit() {
        BasicShip ship = new BasicShip();
        ship.setX(10);
        ship.setY(20);
        BasicAlien alien = new BasicAlien(10,10);
        AlienMissile m = new AlienMissile(alien, 0);
        ArrayList<Alien> list = new ArrayList<Alien>();
        list.add(alien);
        alien.fire();
        game.setPlayerShip(ship);
        game.setEnemies(list);
        assert game.isHit();
        game.tick();
        assert ship.getLives() == 1;
    }
}