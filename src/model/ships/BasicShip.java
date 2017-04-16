package model.ships;

import display.view.GameWindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mscislowski on 4/9/17.
 */
public class BasicShip extends Ship {
    //Keeps a list of the pressed keys to iterate over them
    private Set<Integer> pressed = new HashSet<Integer>();

    public BasicShip() {
    	super("res/img/ship/ship.png");
        this.x = 0;
        this.y = GameWindow.getBoardHeight() - this.image.getIconHeight()*3;
        this.storage = new ArrayList<Missile>(2);
        this.lives = 2;
    }
}
