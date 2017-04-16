package display.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
	JButton playButton;
	
	JButton leaderButton;
	
	/**
	 * Sets up the callbacks and whatnot.
	 */
	public MenuPanel(){
		super();
		MenuPanel self = this;
		this.setBackground(Color.BLACK);
		setUpPlayButton();
		
		leaderButton = new JButton();
		leaderButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				GameWindow f2 = (GameWindow) SwingUtilities.getWindowAncestor(self);
				f2.switchToLeaderBoard();
			}
		});
		leaderButton.setBounds(200, 150, 100, 100);
		leaderButton.setText("Leaderboard");
		this.add(leaderButton);

		ImageIcon img = new ImageIcon("res/galaga.png", "Title");
		JLabel lbl = new JLabel(img);
		lbl.setBounds(20, 20, img.getIconWidth(), img.getIconHeight());
		this.add(lbl);
	}

	private void setUpPlayButton() {
		MenuPanel self = this;
		playButton = new JButton();
		playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				GameWindow f2 = (GameWindow) SwingUtilities.getWindowAncestor(self);
				f2.switchToGame();
			}
		});
		playButton.setBounds(200, 50, 100, 100);
		playButton.setText("Go to game!");
		this.add(playButton);
	}
}
