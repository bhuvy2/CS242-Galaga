package display.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author Bhuvan Venkatesh
 *	Shows the MenuPanel
 */
public class MenuPanel extends JPanel {
	
	/**
	 * Galaga log image
	 */
	Image logo;
	/**
	 * The "Go to game" button
	 */
	JButton btn;
	
	/**
	 * Sets up the callbacks and whatnot.
	 */
	public MenuPanel(){
		super();
		MenuPanel self = this;
		this.setBackground(Color.BLACK);
		btn = new JButton();
		btn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				GameWindow f2 = (GameWindow) SwingUtilities.getWindowAncestor(self);
				f2.switchToGame();
			}
			
		});
		btn.setBounds(50, 50, 100, 100);
		btn.setText("Go to game!");
		this.add(btn);
	}
}
