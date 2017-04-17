package model.ships

import controller.ShipController
import display.view.panels.GamePanel
import java.awt.event.KeyEvent

/**
 * Created by mscislowski on 4/17/17.
 */
class ShipTest extends GroovyTestCase {
    Ship ship = new BasicShip()
    ShipController controller = new ShipController()
    GamePanel panel = new GamePanel()

    void testCanFire() {
        assert ship.canFire()
    }

    void testCanMove() {
        assert ship.canMove()
    }

    void testIsInvincible() {
        assert !ship.isInvincible()
        controller.setShipControls(panel)
        KeyEvent keyEvent = new KeyEvent(panel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP);
        panel.getKeyListeners()[0].keyPressed(keyEvent);
        assert testShip.isInvincible();
    }

    void testCanThrottle() {

    }

    void testShip() {
        assert ship.is(BasicShip)
    }
}
