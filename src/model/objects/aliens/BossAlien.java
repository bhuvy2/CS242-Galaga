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

    public BossAlien(int toEdge, int toRight, Game game1) {
        super(GameConfig.getBossPath());
        this.y = toEdge;
        this.x = toRight;
        attack = BossStates.NotAttacking;
        game = game1;
        column = toRight;
        row = toEdge;
        isMoving = true;
        points = 3000;
        list = new ArrayList<>();
        health = 5 * baseHealth;
    }

    @Override
    public void change() {
    }

    @Override
    public void attack() {
//        System.out.println("numAttacks:" + numAttacks + " Count:" + count + " Health:" + health + " Attack:" + attack + " Base:" + baseHealth + " isAttacking:" + isAttacking);
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

	private void returnPosition() {
		if(count % DELAY == 0) {
		    y -= 4;
		}
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

	private void movePosition() {
		if (count % DELAY == 0)
		    y+=4;
		if (getYCenter() == 312 || getYCenter() == 380)
		    fire(1);
		if (getYCenter() == 400)
		    attack = attack.next;
	}

	private void fireMissiles() {
		if (count % 5 == 0) {
		    numAttacks++;
		    fire(3);
		}
		if (numAttacks == 5) {
		    numAttacks = 0;
		    attack = attack.next;
		}
	}

    private void fire(int type) {
        for (AlienMissile.Slope sl : AlienMissile.Slope.class.getEnumConstants()) {
            AlienMissile m = new AlienMissile(this, sl);
            m.setBounce(true, type);
            list.add(m);
        }
    }

    public void reset() {
        attack = BossStates.NotAttacking;
    }

    public void startAttack() {
        if (!isAttacking()) {
            isAttacking = true;
            attack = BossStates.FireMissiles;
        }
    }
}
