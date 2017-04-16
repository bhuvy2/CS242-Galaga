package display.view;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		this.setBackground(Color.YELLOW);
		leader = null;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		try {
			leader = new Leaderboard();
			int pos = 0, height = 50;
			this.add(new JLabel("Leaderboard!"));
			this.add(Box.createRigidArea(new Dimension(0, 40)));
			for(Player plr: leader.scores){
				String txt = ""+ (pos+1) + ". " + plr.name + ": " + plr.score;
				JLabel lbl = new JLabel(txt);
				lbl.setFont(GameWindow.gameFont);
				pos += 1;
				this.add(lbl);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
