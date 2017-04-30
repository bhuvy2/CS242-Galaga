package model.objects.aliens;

import io.GameConfig;
import model.objects.Delta;
import model.objects.projectile.AlienMissile;
import model.objects.projectile.AlienMissile.Slope;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by mscislowski on 4/9/17.
 */
public class RedAlien extends Alien {
    private double angle;
    private int toSpot;

    
    RedAlienState st;

    /**
     * Constructor for red alien
     * @param toEdge distance to edge
     * @param toRight distance to right side
     */
    public RedAlien(int toEdge, int toRight) {
        super(GameConfig.getRedPath());
        this.y = toEdge;
        this.x = toRight;
        row = toEdge;
        column = toRight;
        angle = 0;
        st = new RedAlienState.Normal();
        isMovingRight = true;
        points = basePoints + 100;
        list = new ArrayList<AlienMissile>();
        health = baseHealth+ 1;
        toSpot = 0;
    }

    /**
     * Handles attack patterns for Red alien
     */
    public void attack() {
        if (st instanceof RedAlienState.Attacking) {
        	RedAlienState.Attacking att = (RedAlienState.Attacking)st;
        	try{
        		Delta d = att.path.remove();
        		this.x += d.xd;
        		this.y += d.yd;
        		if(Math.random() > .99){
        			this.fire();
        		}
        	}catch(NoSuchElementException e){
        		this.x = this.column;
        		this.y = this.row;
        		st = new RedAlienState.Normal();
        		this.isAttacking = false;
        	}
        }
    }

	private boolean inRange() {
		return this.getYCenter() >= 400 && this.getYCenter() <= 403 
				|| this.getYCenter() >= 200 && this.getYCenter() <= 203;
	}

    /**
     * Starts attack for alien
     */
    public void startAttack() {
        if(toSpot == 0) {
        	st = new RedAlienState.Attacking(this.x, this.y);
            toSpot++;
            this.column = this.x;
            this.row = this.y;
            isAttacking = true;
        }
    }

    /**
     * Fires an Alien Missile in the -1, 0, 1 slope
     */
    public void fire() {
    	AlienMissile.Slope slps[] = {AlienMissile.Slope.ShallowLeft, 
                AlienMissile.Slope.Down,
                AlienMissile.Slope.ShallowRight};
        for (AlienMissile.Slope i : slps) {
            AlienMissile m = new AlienMissile(this, i);
            m.setBounce(true, health/5);
            list.add(m);
        }
    }

    public void reset() {
        toSpot = 0;
    }

}
