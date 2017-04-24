package display.view.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import display.view.GameWindow;
import model.Game;

/**
 * @author Bhuvan Venkatesh
 *	Performs the main game loop in the panel
 */
final class TimerListener implements ActionListener {
	
	/**
	 * JPANELS
	 */
	GamePanel pnl;
	
	/**
	 * @param pnl, sets the panel for the timer
	 */
	public TimerListener(GamePanel pnl){
		this.pnl = pnl;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		Game gm = pnl.getGame();
		if (gm.isGameOver()) {
			
			GameWindow parent = (GameWindow) SwingUtilities.getWindowAncestor(pnl);
			if (parent != null){
				String name = JOptionPane.showInputDialog("You lost! Put your name in!");
				pnl.getBoard().addScore(name, gm.getPoints());
				parent.switchToLeaderBoard();
			}
		}
		pnl.tick();
		pnl.updateLabels();
		pnl.repaint();
	}
}