package model.objects.aliens;

import io.GameConfig;
import model.Game;
import model.objects.projectile.AlienMissile;

import java.util.ArrayList;

/**
 * Created by mscislowski on 4/29/17.
 */
public class BossAlien extends Alien {

	private enum BossStates {
		NotAttacking,
		FireMissiles,
		MovePosition,
		AttemptCapture,
		ReturnPosition;

		public BossStates next;

		static {
			NotAttacking.next = FireMissiles;
			FireMissiles.next = MovePosition;
			MovePosition.next = AttemptCapture;
			AttemptCapture.next = ReturnPosition;
			ReturnPosition.next = NotAttacking;
		}

	};

    private BossStates attack;
    private int numAttacks = 0;
    private Game game;

    /**
     * Constructs a Boss alien with 5 times the base alien health,
     * 3000 times the base alien health in points, and allows for
     * missile bouncing as well as creating other aliens.
     * @param toEdge y position
     * @param toRight x position
     * @param game1 game instantiation needed for alien creation
     */
    public BossAlien(int toEdge, int toRight, Game game1) {
        super(GameConfig.getBossPath());
        this.y = toEdge;
        this.x = toRight;
        attack = BossStates.NotAttacking;
        game = game1;
        column = toRight;
        row = toEdge;
        isMoving = true;
        points = 3000 * baseHealth;
        list = new ArrayList<>();
        health = 5 * baseHealth;
    }

    @Override
    public void change() {
    }

    /**
     * Handles attack pattern for Boss
     */
    @Override
    public void attack() {
        switch (attack) {
        case NotAttacking:
            break;
        case FireMissiles:
            // Fire Missiles
            fireMissiles();
            break;
        case MovePosition:
            // Move into position
            movePosition();
            break;
        case AttemptCapture:
            // Attempt capture
            attack = attack.next;
            break;
        case ReturnPosition:
            // Return to position
            returnPosition();
            break;
        }

    }

    /**
     * Returns boss to original y coordinate row
     */
	private void returnPosition() {
		if(count % DELAY == 0) {
		    y -= 4;
		}

		// Creates a new advanced alien with 3 health and 0 points on return
		if(y == row) {
		    Alien a = new AdvancedAlien(y, x);
		    a.startAttack();
		    a.setMoving(false);
		    a.setHealth(3);
		    a.setPoints(0);
		    game.addAlien(a);
		    isAttacking = false;
		    attack = attack.next;
		}
	}

    /**
     * Moves to attacking position
     */
	private void movePosition() {
		if (count % DELAY == 0)
		    y+=4;
		// Fires missiles with one bounce
		if (getYCenter() == 312 || getYCenter() == 380)
		    fire(1);
		if (getYCenter() == 400)
		    attack = attack.next;
	}

    /**
     * Fires missiles
     */
	private void fireMissiles() {
	    // Fires missiles with 3 bounces
		if (count % 5 == 0) {
		    numAttacks++;
		    fire(3);
		}
		// Fires 5 rounds
		if (numAttacks == 5) {
		    numAttacks = 0;
		    attack = attack.next;
		}
	}

    /**
     * Fires missiles with a set number of bounces
     * @param type number of bounces for the missile
     */
    private void fire(int type) {
        for (AlienMissile.Slope sl : AlienMissile.Slope.class.getEnumConstants()) {
            AlienMissile m = new AlienMissile(this, sl);
            m.setBounce(true, type);
            list.add(m);
        }
    }

    /**
     * Resets boss alien on ship death
     */
    public void reset() {
        attack = BossStates.NotAttacking;
    }

    /**
     * Begins the attack pattern for boss
     */
    public void startAttack() {
        if (!isAttacking()) {
            isAttacking = true;
            attack = BossStates.FireMissiles;
        }
    }
}
