package Ships;

import display.view.GameWindow;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mscislowski on 4/9/17.
 */
public class BasicShip extends Ship {
    //Keeps a list of the pressed keys to iterate over them
    private Set<Integer> pressed = new HashSet<Integer>();

    public BasicShip() {
        toRight = 50; //An integer between 0 and 100 that is a percent of how far over the ship is
        this.setToEdge((int) ((GameWindow.getBoardHeight()-(double)getImage().getIconHeight())*100.0/GameWindow.getBoardHeight()));
        storage = new ArrayList<Missile>(2);
        setLives(2);
    }

    /**
     * Key released.
     * @param e given by Java
     * It gets rid of the pressed Key queue
     */
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
    }

    // TODO MOVE ALL TO CONTROLLER
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
                    if(toRight <= 90)
                        toRight+=16;
                }
                if(pressed.contains(KeyEvent.VK_LEFT)) {
                    if(toRight >= 4)
                        toRight -= 16;
                }
            }

            // Movement handler + other ship codes
            for(int in: pressed) {
                switch(in) {
                    case KeyEvent.VK_RIGHT:
                        if(toRight <= 90)
                            toRight+=4;
                        break;
                    case KeyEvent.VK_LEFT:
                        if(toRight >= 4)
                            toRight -= 4;
                        break;
                    case KeyEvent.VK_SPACE:
                        fire();
                        break;
//                    case KeyEvent.VK_M:
//                        toggleMax();
//                        break;
//                    case KeyEvent.VK_I:
//                        toggleInvincible();
//                        break;
//                    case KeyEvent.VK_T:
//                        toggleThrottle();
//                        break;
//                    case KeyEvent.VK_C:
//                        toggleRainbow();
//                        break;
//                    case KeyEvent.VK_N:
//                        toggleShots();
//                        break;
                    default:
                        break;
                }

            }
        }

    }

//
//    private void toggleShots() {
//        setMultipleShots(!isMultipleShots());
//    }
//
//    /**
//     * Toggle throttle.
//     */
//    private void toggleThrottle() {
//        setCanThrottle(!canThrottle());
//
//    }
//
//
//    /**
//     * Toggle invincible.
//     */
//    private void toggleInvincible() {
//        setInvincible(!isInvincible());
//    }
//
//    /**
//     * Toggle max.
//     */
//    private void toggleMax() {
//        if(MAX_SHOTS == 2)
//            MAX_SHOTS = 500;
//        else
//            MAX_SHOTS = 2;
//    }


    public void keyTyped(KeyEvent e) {//No Implementation Needed}
    }
}
