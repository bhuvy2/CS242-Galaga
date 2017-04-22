package tests

import model.ships.BasicAlien
import model.ships.BasicShip
import model.ships.BlueAlien
import model.ships.RedAlien

class ShipTests extends GroovyTestCase {
    void testBasicShip() {
        BasicShip ship = new BasicShip()
        assert ship.getLives() == 2
        assert ship.getX() == 0
        assert ship.getY() == 692
        assert ship.getStorage() == []
        assert ship.canFire()
        assert ship.canMove()
        assert !ship.isInvincible()
        assert !ship.isMultipleShots()
        assert !ship.canThrottle()
    }

    void testAlien() {
        BasicAlien alien = new BasicAlien(50, 50)
        assert alien.health == 1
        assert alien.points == 100
        assert alien.x == 50
        assert alien.y == 50
    }

    void testRedAlien() {
        RedAlien alien = new RedAlien(50, 50)
        assert alien.health == 1
        assert alien.points == 200
        assert alien.x == 50
        assert alien.y == 50
    }

    void testBlueAlien() {
        BlueAlien alien = new BlueAlien(50, 50)
        assert alien.health == 2
        assert alien.points == 300
        assert alien.x == 50
        assert alien.y == 50
    }
}