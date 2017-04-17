package model.ships;

import display.view.GameWindow;
import io.GameConfig;

import java.util.ArrayList;

/**
 * Created by mscislowski on 4/9/17.
 */
public class BasicShip extends Ship {
    private static final int NUM_LIVES = 2;
    private static final int NUM_MISSILES = 2;

    public BasicShip() {
    	super(GameConfig.getShipPath());
        this.x = 0;
        this.y = GameWindow.getBoardHeight() - this.image.getIconHeight()*3;
        this.storage = new ArrayList<Missile>(NUM_MISSILES);
        this.lives = NUM_LIVES;
    }
}
