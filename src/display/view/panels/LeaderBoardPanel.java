package display.view.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import display.view.GameWindow;
import io.Leaderboard;
import io.Player;

/**
 * @author Bhuvan Venkatesh
 *	Shows the leaderboard from the file
 */
@SuppressWarnings("serial")
public class LeaderBoardPanel extends JPanel {
	
	/**
	 * Keeps track of the IO
	 */
	private Leaderboard leader;
	
	private static final int leaderHeight = 50;
	private static final int labelHeight = 30, labelPadding = 10;
	
	/**
	 * Constructs the leaderboard from the file
	 */
	public LeaderBoardPanel(){
		super();
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		addTopLeaderLabel();
		int below = addScores();
		addReturnButton(below);
	}

	private void addReturnButton(int below) {
		this.add(Box.createRigidArea(new Dimension(0, 40)));
		JButton ret = MenuPanel.createSimpleButton("Menu");
		JPanel self = this;
		ret.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				GameWindow parent = (GameWindow) SwingUtilities.getWindowAncestor(self);
				parent.switchToMenu();
			}
		});
		ret.setBounds(80, below, 100, 30);
		this.add(ret);
	}

	/**
	 * Adds the jlabel to the view
	 */
	private int addScores() {
		leader = null;
		int pos = 1;
		try {
			leader = new Leaderboard();
			for(Player plr: leader.scores){
				if(pos == 11){
					break;
				}
				String txt = ""+ (pos) + ". " + plr.name + ": " + plr.score;
				JLabel lbl = new JLabel(txt);
				lbl.setFont(GameWindow.gameFont);
				lbl.setForeground(Color.YELLOW);
				lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
				lbl.setBounds(80, leaderHeight+(labelHeight+labelPadding)*(pos), 
						600, labelHeight);
				pos += 1;
				this.add(lbl);
			}
		} catch (IOException e) {
			this.add(new JLabel("Unable to load"));
		}
		return leaderHeight+(labelHeight+labelPadding)*(pos);
	}

	/**
	 * Puts the leaderboard at the top
	 */
	private void addTopLeaderLabel() {
		JLabel label = new JLabel("---- Leaderboard ----");
		label.setForeground(Color.RED);
		label.setFont(new Font("impact", 0, 32));
		label.setBounds(80, 0, 500, leaderHeight);
		this.add(label);
	}

}
