package controller;

public class ShipController {
    public void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());

        if(canThrottle() && pressed.contains(KeyEvent.VK_UP)) {
            Alien.DELAY++;
        }

        if(canThrottle() && pressed.contains(KeyEvent.VK_DOWN)) {
            if(Alien.DELAY >=2)
                Alien.DELAY--;
        }

        // Jump handler
        if(canMove()) {
            if(pressed.contains(KeyEvent.VK_SHIFT)) {
                if(pressed.contains(KeyEvent.VK_RIGHT)) {
                    if(x <= 90)
                        x+=16;
                }
                if(pressed.contains(KeyEvent.VK_LEFT)) {
                    if(x >= 4)
                        x -= 16;
                }
            }

            // Movement handler + other ship codes
            for(int in: pressed) {
                switch(in) {
                    case KeyEvent.VK_RIGHT:
                        if(x <= 90)
                            x+=4;
                        break;
                    case KeyEvent.VK_LEFT:
                        if(x >= 4)
                            x -= 4;
                        break;
                    case KeyEvent.VK_SPACE:
                        fire();
                        break;
                    case KeyEvent.VK_M:
                        toggleMax();
                        break;
                    case KeyEvent.VK_I:
                        toggleInvincible();
                        break;
                    case KeyEvent.VK_T:
                        toggleThrottle();
                        break;
                    case KeyEvent.VK_C:
                        toggleRainbow();
                        break;
                    case KeyEvent.VK_N:
                        toggleShots();
                        break;
                    default:
                        break;
                }

            }
        }

    }

    /**
     * Toggles multiple projectile mode.
     */
    private void toggleShots() {
        setMultipleShots(!isMultipleShots());
    }

    /**
     * Toggle throttle.
     */
    private void toggleThrottle() {
        setCanThrottle(!canThrottle());

    }

    /**
     * Toggle invincible.
     */
    private void toggleInvincible() {
        setInvincible(!isInvincible());
    }

    /**
     * Toggle max shots.
     */
    private void toggleMax() {
        if(MAX_SHOTS == 2)
            MAX_SHOTS = 500;
        else
            MAX_SHOTS = 2;
    }
}