package model.ships;

import io.GameConfig;
import model.Game;

import java.util.ArrayList;

/**
 * Created by mscislowski on 4/29/17.
 */
public class BossAlien extends Alien {
    private int attack = 0;
    private int numAttacks = 0;
    private Game game;

    public BossAlien(int toEdge, int toRight, Game game1) {
        super(GameConfig.getShipPath());
        this.y = toEdge;
        this.x = toRight;
        game = game1;
        column = toRight;
        row = toEdge;
        isMoving = true;
        points = 3000;
        list = new ArrayList<>();
        health = (int)(1.5 * baseHealth);
    }

    @Override
    public void change() {
    }

    @Override
    public void attack() {
        System.out.println("numAttacks:" + numAttacks + " Count:" + count + " Health:" + health + " Attack:" + attack + " Base:" + baseHealth + " isAttacking:" + isAttacking);
        if (isAttacking) {
            switch (attack) {
                case 0:
                    break;
                case 1:
                    // Fire Missiles
                    if (count % 5 == 0) {
                        numAttacks++;
                        fire(10);
                    }
                    if (numAttacks == 5) {
                        numAttacks = 0;
                        attack++;
                    }
                    break;
                case 2:
                    // Move into position
                    if (count % DELAY == 0)
                        y+=4;
                    if (getYCenter() == 312 || getYCenter() == 380)
                        fire(3);
                    if (getYCenter() == 400)
                        attack++;
                    break;
                case 3:
                    // Attempt capture
                    attack++;
                    break;
                case 4:
                    // Return to position
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
                        attack = 0;
                    }
                    break;
                default:
                    break;
            }
        }

    }

    public void fire(int type) {
        if (type == 10) {
            // Create 6 missiles that bounce 10 times
            for (int i = -3; i < 4; i++) {
                AlienMissile m = new AlienMissile(this, i);
                m.setBounce(true, 10);
                list.add(m);
            }
        }

        if (type == 3) {
            for (int i = -3; i < 4; i++) {
                AlienMissile m = new AlienMissile(this, i);
                m.setBounce(true, 3);
                list.add(m);
            }
        }
    }

    public void reset() {
        attack = 0;
    }

    @Override
    public void startAttack() {
        isAttacking = true;
        attack++;
    }
}
