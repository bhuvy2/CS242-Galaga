package model.objects.aliens;

import io.GameConfig;

import java.util.ArrayList;

/**
 * Created by mscislowski on 4/9/17.
 */
public class AdvancedAlien extends Alien {
    private static final int rowAttack = 400; //The Row in 0-100 where they attack fully
    private int fireCount = 0; //Another count if needed
    
    private static final int attackMovingSpeed = 1;
	private static final int fireLimit = 100;

    /**
     * Keeps a finite state machine of attacking states
     *
     */
    private enum AttackingState {
    	AtSpot,
    	MovingToward,
    	Attacking,
    	MovingBack;
    	
    	public AttackingState next;
    	
    	static {
    		AtSpot.next = MovingToward;
    		MovingToward.next = Attacking;
    		Attacking.next = MovingBack;
    		MovingBack.next = AtSpot;
    	}
    }
    
    /**
     * The current state of the alien
     */
    private AttackingState toSpot;

    /**
     * Instantiates a new blue alien, saves the column and row as a backup in case need to be reset
     * as well as loads the image, initializes points, creates a new list of Alien Missiles and set health
     * @param toEdge the to edge is a percentage from left to right, of 100, how up or down the alien is the y coordinate.
     * @param toRight the to right is the same as toEdge but it is the x coordinate.
     */
    public AdvancedAlien(int toEdge, int toRight) {
        super(GameConfig.getGreenPath());
        this.y = toEdge;
        this.x = toRight;
        column = toRight;
        row = toEdge;
        isMovingRight = true;
        toSpot = AttackingState.AtSpot;


        points = basePoints + 200*baseHealth;
        list = new ArrayList<>();
        health = baseHealth*2 + 2;
    }

    /**
     * Handles attack pattern for blue alien
     */
    public void attack() {
        switch(toSpot) {
        case AtSpot:
            break;
        case MovingToward:
            moveToward();
            break;
        case Attacking:
            doAttack();
            break;
        case MovingBack:
            moveBack();
            break;
        }
    }

    /**
     * Returns alien to original position
     */
	private void moveBack() {
		y -= attackMovingSpeed;
		if(y == row) {
		    toSpot = toSpot.next;
		    isAttacking = false;
		}
	}

    /**
     * Performs attack
     */
	private void doAttack() {
		fireCount++;
		if(count % DELAY == 0)
		    fire();
		if(fireCount == fireLimit) {
		    fireCount = 0;
		    toSpot = toSpot.next;
		}
	}

    /**
     * Moves into position
     */
	private void moveToward() {
		y += attackMovingSpeed;
		if(getYCenter() == rowAttack) {
		    toSpot = toSpot.next;
		}
	}

    /**
     * This method should be used instead of just increasing
     * toSpot because it updates all of the other field variables
     * to accurate reflect the state of the alien
     */
    public void startAttack() {
        if(toSpot == AttackingState.AtSpot) {
        	toSpot = AttackingState.MovingToward;
        }
    }

    /**
     * Resets toSpot on game reset/attack reset
     */
    public void reset() {
        toSpot = AttackingState.AtSpot;
    }


    /**
     * Changes the Image from a fully healthy blue alien to an
     * injured Blue Alien
     */
    public void change() {
    	this.setImage(GameConfig.getBluePath());
    }
}
