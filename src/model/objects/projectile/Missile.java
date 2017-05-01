package model.objects.projectile;

import model.superclasses.GameSprite;
import model.superclasses.Projectile;

import io.GameConfig;

/**
 * Created by mscislowski on 4/9/17.
 */
public class Missile extends GameSprite implements Projectile {
    
    /**
     * Instantiates a new missile.
     * @param right the right coordinate NOT A 0-100
     * @param edge the edge coordinate NOT A 0-100
     */
    public Missile(int right, int edge) {
        super(GameConfig.getMissilePath());
        x = right;
        y = edge;
    }

    /**
     * Moves missile towards edge
     */
    public void move() {
        y -= 12;
    }

    /**
     * Checks if missile is visible
     * @return true if is visible
     */
    public boolean isVisible() {
        return y > 0;
    }
}
