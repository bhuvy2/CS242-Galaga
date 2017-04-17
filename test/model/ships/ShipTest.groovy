package model.ships

import controller.ShipController
import display.view.panels.GamePanel
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
        ship.setInvincible(!ship.isInvincible())
        assert ship.isInvincible()
    }

    void testCanThrottle() {
        assert !ship.canThrottle()
        ship.setCanThrottle(!ship.canThrottle())
        assert ship.canThrottle()
    }

    void testMaxShots() {
        assert ship.getMaxShots() == 2
        ship.setMaxShots(500)
        assert ship.getMaxShots() == 500
    }

    void testMultiShot() {
        assert !ship.isMultipleShots()
        ship.setMultipleShots(!ship.isMultipleShots())
        assert ship.isMultipleShots()
    }
}
