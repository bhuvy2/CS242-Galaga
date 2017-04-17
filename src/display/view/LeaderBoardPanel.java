package display.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import io.Leaderboard;
import io.Player;

/**
 * @author Bhuvan Venkatesh
 *	Shows the leaderboard from the file
 */
@SuppressWarnings("serial")
public class LeaderBoardPanel extends JPanel {
	
	private Leaderboard leader;
	
	/**
	 * Constructs the leaderboard from the file
	 */
	public LeaderBoardPanel(){
		super();
		JPanel self = this;
		this.setBackground(Color.BLACK);
		addScores();
		JButton ret = new JButton();
		ret.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				GameWindow parent = (GameWindow) SwingUtilities.getWindowAncestor(self);
				parent.switchToMenu();
			}
		});
		ret.setText("Menu");
		this.add(ret);
	}

	private void addScores() {
		leader = null;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		int pos = 0;
		addTopLeaderLabel();
		try {
			leader = new Leaderboard();
			for(Player plr: leader.scores){
				String txt = ""+ (pos+1) + ". " + plr.name + ": " + plr.score;
				JLabel lbl = new JLabel(txt);
				lbl.setFont(GameWindow.gameFont);
				lbl.setForeground(Color.WHITE);
				pos += 1;
				this.add(lbl);
			}
		} catch (IOException e) {
			this.add(new JLabel("Unable to load"));
		}
	}

	private void addTopLeaderLabel() {
		JLabel label = new JLabel("Leaderboard!");
		label.setForeground(Color.WHITE);
		this.add(label);
		this.add(Box.createRigidArea(new Dimension(0, 40)));
	}

}
