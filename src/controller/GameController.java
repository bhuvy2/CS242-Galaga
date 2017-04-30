package controller;
import display.view.panels.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by mscislowski on 4/16/17.
 */
public class GameController {
    private final String PAUSE = "pause",
            RESET = "reset";
    private Action pause,
            reset;
    private boolean isPaused = false;
    GamePanel pnl;

    /**
     * Sets up keybinds for game
     * @param pnl to hold keybinds
     */
    public void setGameControls(GamePanel pnl) {
        this.pnl = pnl;
        setControls();
        setMappings(pnl);
    }

    /**
     * Distributed caller
     */
    private void setControls() {
        setPause();
        setReset();
    }

    /**
     * Function to pause game
     */
    private void setPause() {
        pause = new AbstractAction(PAUSE) {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPaused = !isPaused;
                pnl.toggleTimer();
            }
        };
    }

    /**
     * Function to reset game
     */
    private void setReset() {
        reset = new AbstractAction(RESET) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPaused) {
                    pnl.getGame().resetGame();
                }
            }
        };
    }

    private void setMappings(JPanel jpl) {
        this.addKey(jpl, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), PAUSE, pause);
        this.addKey(jpl,KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK), RESET, reset);
    }

    private void addKey(JPanel jpl, KeyStroke key, String mapKey, Action value){
        jpl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, mapKey);
        jpl.getActionMap().put(mapKey, value);
    }
}
