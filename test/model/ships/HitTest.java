//package model.ships;
//
//import display.view.panels.GamePanel;
//import model.Game;
//import model.Star;
//import model.objects.aliens.Alien;
//import model.objects.aliens.BasicAlien;
//import model.objects.projectile.AlienMissile;
//import model.objects.ship.BasicShip;
//
//import org.junit.Test;
//
//import static org.mockito.Mockito.spy;
//
//import java.util.ArrayList;
//import java.util.Timer;
//
//public class HitTest {
//    GamePanel pnl = new GamePanel(new ArrayList<Star>(), true);
//    Timer timer = new Timer();
//    Game game = new Game();
//
//    @Test
//    public void testGameLoop() {
//        pnl.toggleTimer();
//        game.tick();
//    }
//
//    @Test
//    public void testShipHit() {
//<<<<<<< Updated upstream
//    	Game gm = spy(new Game());
//        BasicShip ship = new BasicShip(gm);
//=======
//        BasicShip ship = new BasicShip(game);
//>>>>>>> Stashed changes
//        ship.setX(10);
//        ship.setY(10);
//        BasicAlien alien = new BasicAlien(10,10);
//        ArrayList<Alien> list = new ArrayList<Alien>();
//        list.add(alien);
//        game.setPlayerShip(ship);
//        game.setEnemies(list);
//        assert game.isHit();
//        game.tick();
//        assert ship.getLives() == 1;
//    }
//
//    @Test
//    public void testShipMissileHit() {
//<<<<<<< Updated upstream
//    	Game gm = spy(new Game());
//        BasicShip ship = new BasicShip(gm);
//=======
//        BasicShip ship = new BasicShip(game);
//>>>>>>> Stashed changes
//        ship.setX(10);
//        ship.setY(20);
//        BasicAlien alien = new BasicAlien(10,10);
//        AlienMissile m = new AlienMissile(alien, AlienMissile.Slope.Down);
//        ArrayList<Alien> list = new ArrayList<Alien>();
//        list.add(alien);
//        alien.fire();
//        game.setPlayerShip(ship);
//        game.setEnemies(list);
//        assert game.isHit();
//        game.tick();
//        assert ship.getLives() == 1;
//    }
//}