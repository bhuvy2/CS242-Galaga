package display.view.panels;

import controller.GameController;
import controller.ShipController;
import display.view.GameWindow;
import model.Game;
import model.Star;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Bhuvan Venkatesh
 *	Is the view in the MVC
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	/**
	 * @author Bhuvan Venkatesh
	 *	Performs the main game loop in the panel
	 */
	private final class TimerListener implements ActionListener {
		GamePanel pnl;
		public TimerListener(GamePanel pnl){
			this.pnl = pnl;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			if (pnl.getGame().isGameOver()) {
				GameWindow parent = (GameWindow) SwingUtilities.getWindowAncestor(pnl);
				if (parent != null)
					parent.switchToLeaderBoard();
			}
			pnl.tick();
			pnl.updateLabels();
			pnl.repaint();
		}
	}
	
	/**
	 * The timer that fires off the event to update and redraw
	 */
	private Timer timer;

	/**
	 * Model for the game
	 */
	private Game game;
	
	/**
	 * Points to next life
	 */
	private JLabel toNext;
	
	private JLabel scoreLabel;
	
	private JLabel highScoreLabel;
	
	private ArrayList<Star> stars;
	
	public void updateLabels(){
		toNext.setText("" + game.getToNextLife());
		scoreLabel.setText("" + game.getPoints());
	}
	
	/**
	 * Constructs a new gamepanel with bindings and
	 * sets up the timer
	 * @param start, whether to start the game or not.
	 */
	public GamePanel(ArrayList<Star> strs, boolean start){
		super();
		game = new Game();

		// Set Keybinds
		GameController gameController = new GameController();
		ShipController controller = new ShipController();
		gameController.setGameControls(this);
		controller.setShipControls(this);

		// Set timer
		this.setLayout(null);
		timer = new Timer(1000/60, new TimerListener(this));
		if(start){
			timer.start();
		}

		// Create stars
		stars = strs;

		// Set Options
		setPanelOptions();

		// Display 1up
		JLabel oneUp = createSimpleLabel("1up");
		oneUp.setBounds(0, 0, 100, 25);
		this.add(oneUp);

		toNext = createSimpleLabel("");
		toNext.setForeground(Color.WHITE);
		toNext.setBounds(0, 25, 100, 30);
		this.add(toNext);

		// Display Score
		JLabel score = createSimpleLabel("Score");
		score.setBounds(220, 0, 100, 30);
		this.add(score);

		scoreLabel = createSimpleLabel("");
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setBounds(220, 25, 100, 30);
		this.add(scoreLabel);

		// Display high score
		JLabel lbl = createSimpleLabel("High Score");
		lbl.setBounds(GameWindow.BOARD_WIDTH-100, 0, 100, 30);
		this.add(lbl);
		
		highScoreLabel = createSimpleLabel("0");
		highScoreLabel.setForeground(Color.WHITE);
		highScoreLabel.setBounds(GameWindow.BOARD_WIDTH-100, 25, 100, 30);
		this.add(highScoreLabel);
	}
	
	public JLabel createSimpleLabel(String lbl){
		JLabel jlbl = new JLabel(lbl);
		jlbl.setFont(new Font("impact", 0, 16));
		jlbl.setForeground(Color.RED);
		return jlbl;
	}
	
	/**
	 * Starts the game if not started before
	 */
	public void start(){
		timer.start();
	}
	
	/**
	 * Forces the manager to update
	 */
	public void tick(){
		game.tick();
		for(Star str: this.stars){
			str.tick();
		}
	}

	/**
	 * Customizes the panel for game playing
	 */
	private void setPanelOptions() {
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		game.draw(this, g);
		for(Star str: this.stars){
			str.drawSelf(this, g);
		}
	}
	
	public Game getGame(){
		return this.game;
	}

	public void toggleTimer() {
		if (timer.isRunning())
			timer.stop();
		else
			timer.start();
	}
}
