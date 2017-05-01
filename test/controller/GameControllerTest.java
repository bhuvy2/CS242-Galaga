package controller;

import display.view.panels.GamePanel;
import model.Game;
import model.Star;
import model.objects.ship.BasicShip;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by mscislowski on 4/24/17.
 */
public class GameControllerTest {
    GamePanel pnl = new GamePanel(new ArrayList<Star>(), true);
    Timer timer = new Timer();
    Game game = new Game();
    GameController controller = new GameController();

    @Test
    public void testController() {
        BasicShip ship = new BasicShip(game);
        game.setPlayerShip(ship);
        controller.setGameControls(pnl);
        Action a = (Action) pnl.getActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        assert a.isEnabled();
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}