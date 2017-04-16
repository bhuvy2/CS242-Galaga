package model.ships;

import model.superclasses.GameSprite;
import model.superclasses.Projectile;

import java.awt.*;

/**
 * Created by mscislowski on 4/9/17.
 */
public class Missile extends GameSprite implements Projectile {
    // Height and width of missile
    public final static int HEIGHT = 10, WIDTH = 3;

    //The color of the missile which defaults to green
    private Color myColor;

    /**
     * Instantiates a new missile.
     * @param right the right coordinate NOT A 0-100
     * @param edge the edge coordinate NOT A 0-100
     */
    public Missile(int right, int edge) {
        super("res/img/alien/alien_lazer.png");
        x = right;
        y = edge;
        setMyColor(Color.GREEN);
    }

    /**
     * Moves missile towards edge
     */
    public void move() {
        y-=2;
    }

    /**
     * Checks if missile is visible
     * @return true if is visible
     */
    public boolean isVisible() {
        return y > 0;
    }

    /**
     * Gets color of missile
     * @return color of missile
     */
    public Color getMyColor() {
        return myColor;
    }

    /**
     * Sets missile color
     * @param myColor new color of missile
     */
    public void setMyColor(Color myColor) {
        this.myColor = myColor;
    }
}
