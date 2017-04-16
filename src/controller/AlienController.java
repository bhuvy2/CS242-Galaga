package controller;

import enums;

public class AlienController {
    public void createAliens(AlienSetup setup) {
        // Determines stage steup patterm
        switch (setup) {
            case BASIC:
                basicSetup();
                break;
            case BASIC_RED:
                redSetup();
                break;
            case BASIC_BLUE:
                blueSetup();
                break;
            case BASIC_RGB:
                rgbSetup();
                break;
            case BOSS:
                bossSetup();
                break;
            default:
                // Shouldn't get here
                break;
        }
    }

    public void setAttackers() {

    }

    /**
     * Reset aliens in enemies back to attack formation after being hit
     */
    public void resetAttack(ArrayList<Alien> enemies) {
        for (Alien a : enemies) {
            a.list.removeAll(a.list);
            if (a.isAttacking)
                Alien.amountAttacking--;
            a.isAttacking = false;
            a.isMoving = true;
            a.returnToPosition();
        }

    }
}