package controller;

import display.audio.GameSoundboard;
import display.view.GameWindow;
import display.view.panels.GamePanel;
import model.ships.Alien;
import model.ships.Ship;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ShipController {
    private Ship player;
    private final String LEFT = "left",
            RIGHT = "right",
            KULEFT = "kuleft",
            KURIGHT = "kuright",
            JLEFT = "jleft",
            JRIGHT = "jright",
            MAX = "max",
            FIRE = "fire",
            INVINCIBLE = "invincible",
            THROTTLE = "throttle",
            THROTTLEUP = "throttleup",
            THROTTLEDOWN = "throttledown",
            SHOTS = "shots";
    private Action right,
            jright,
            left,
            jleft,
            max,
            fire,
            invincible,
            throttle,
            throttleup,
            throttledown,
            kuleft,
            kuright,
            shots;
    
    GameSoundboard brd = new GameSoundboard();
    
    private Boolean rightPressed = false, leftPressed = false;
    
    int jump = 64;

    public void setShipControls(GamePanel pnl) {
        setControls();
        setMappings(pnl);
        player = pnl.getGame().getPlayerShip();
    }

    private void setControls() {
        setBasicMovement();
        setShipMods();
        setThrottling();
    }

    /**
     * Sets basic movement keybinds including left/right, jumping left/right, and firing
     */
    private void setBasicMovement() {
    	GameSoundboard brd = new GameSoundboard();
    	
    	ShipController self = this;
        right = new AbstractAction(RIGHT) {
            public void actionPerformed(ActionEvent e) {
            	synchronized(player){
	            	self.rightPressed = true;
	                player.setRight();
            	}
            }
        };

        left = new AbstractAction(LEFT) {
            public void actionPerformed(ActionEvent e) {
            	synchronized(player){
	            	self.leftPressed = true;
	                player.setLeft();
            	}
            }
        };
        
        kuright = new AbstractAction(KURIGHT) {
            public void actionPerformed(ActionEvent e) {
            	synchronized(player){
	            	self.rightPressed = false;
	            	if(!self.leftPressed)
	            		player.setStop();
	            	else
	            		player.setLeft();
            	}
            }
        };
        
        kuleft = new AbstractAction(KULEFT) {
            public void actionPerformed(ActionEvent e) {
            	synchronized(player){
	            	self.leftPressed = false;
	            	if(!self.rightPressed)
	            		player.setStop();
	            	else
	            		player.setRight();
            	}
            }
        };

        jright = new AbstractAction(JRIGHT) {
            public void actionPerformed(ActionEvent e) {
            	synchronized(player){
                if (player.getX() <= GameWindow.getBoardWidth() - player.getImage().getIconWidth() - jump)
                    player.setX(player.getX()+jump);
            	}
            }
        };

        jleft = new AbstractAction(JLEFT) {
            public void actionPerformed(ActionEvent e) {
            	synchronized(player){
                if (player.getX() >= jump)
                    player.setX(player.getX()-jump);
            	}
            }
        };

        fire = new AbstractAction(FIRE) {
            public void actionPerformed(ActionEvent e) {
                if(player.fire())
                	brd.playMissile();
            }
        };
    }

    /**
     * Sets Power modification key binds for ship
     */
    private void setShipMods() {
        invincible = new AbstractAction(INVINCIBLE) {
            public void actionPerformed(ActionEvent e) {
                toggleInvincible();
            }
        };

        throttle = new AbstractAction(THROTTLE) {
            public void actionPerformed(ActionEvent e) {
                toggleThrottle();
            }
        };

        shots = new AbstractAction(SHOTS) {
            public void actionPerformed(ActionEvent e) {
                toggleShots();
            }
        };

        max = new AbstractAction(MAX) {
            public void actionPerformed(ActionEvent e) {
                toggleMax();
            }
        };
    }

    /**
     * Sets Throttling key binds for ship. Changes game delay.
     */
    private void setThrottling() {
        throttleup = new AbstractAction(THROTTLEUP) {
            public void actionPerformed(ActionEvent e) {
                if (player.canThrottle())
                    if (Alien.DELAY >= 2)
                        Alien.DELAY--;
            }
        };

        throttledown = new AbstractAction(THROTTLEDOWN) {
            public void actionPerformed(ActionEvent e) {
                if (player.canThrottle())
                    Alien.DELAY++;
            }
        };
    }

    /**
     * Creates mappings between actions and key presses for provided JPanel
     * @param jpl panel to react with key presses
     */
    private void setMappings(JPanel jpl) {
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false) , LEFT, left);
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), RIGHT, right);
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true) , KULEFT, kuleft);
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), KURIGHT, kuright);
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.SHIFT_DOWN_MASK), JLEFT, jleft);
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.SHIFT_DOWN_MASK), JRIGHT, jright);
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0) , FIRE, fire);
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_M, 0), MAX, max);
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_I, 0), INVINCIBLE, invincible);
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), THROTTLE, throttle);
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), SHOTS, shots);
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), THROTTLEUP, throttleup);
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), THROTTLEDOWN, throttledown);
    }

    /**
     * Adds key bind to panel
     * @param jpl panel to add key to
     * @param key key to be added
     * @param mapKey mapped action for key
     * @param value action taken
     */
    private void addKey(JPanel jpl, KeyStroke key, String mapKey, Action value){
        jpl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, mapKey);
        jpl.getActionMap().put(mapKey, value);
    }

    /**
     * Toggles multiple projectile mode.
     */
    private void toggleShots() {
        player.setMultipleShots(!player.isMultipleShots());
    }

    /**
     * Toggle throttle.
     */
    private void toggleThrottle() {
        player.setCanThrottle(!player.canThrottle());
    }

    /**
     * Toggle invincible.
     */
    private void toggleInvincible() {
        player.setInvincible(!player.isInvincible());
    }

    /**
     * Toggle max shots.
     */
    private void toggleMax() {
        if(Ship.getMaxShots() == 2)
            Ship.setMaxShots(500);
        else
            Ship.setMaxShots(2);
    }
}