package model.superclasses;

/**
 * Created by mscislowski on 4/9/17.
 */
public interface Projectile
{

    /**
     * Moves the Missile in specified direction
     */
    void move();

    /**
     * Checks if is visible.
     * @return true, if is visible
     */
    boolean isVisible();
}
