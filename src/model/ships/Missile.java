package model.ships;

import java.awt.*;

import model.superclasses.GameSprite;
import model.superclasses.Projectile;

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
        x = right;
        y = edge;
        image = null;
        setMyColor(Color.GREEN);
    }

    public void move() {
        y-=2;
    }

    public boolean isVisible() {
        return y > 0;
    }

    public Color getMyColor() {
        return myColor;
    }

    public void setMyColor(Color myColor) {
        this.myColor = myColor;
    }
}
