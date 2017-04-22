package display.view.panels;

import controller.ShipController;
import display.view.GameWindow;
import display.view.SpriteManager;
import model.Game;
import model.Star;
import model.ships.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

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
				parent.switchToLeaderBoard();
			}
			pnl.manager.tick();
			pnl.repaint();
		}
	}

	/**
	 * Abstraction layer from the view
	 */
	private SpriteManager manager;
	
	/**
	 * The timer that fires off the event to update and redraw
	 */
	private Timer timer;

	private Game game;
	
	/**
	 * Constructs a new gamepanel with bindings and
	 * sets up the timer
	 * @param start, whether to start the game or not.
	 */
	public GamePanel(ArrayList<Star> strs, boolean start){
		super();
		game = new Game();
		ShipController controller = new ShipController();
		controller.setShipControls(this);
		addModelToManager(strs);
		this.setLayout(null);
		timer = new Timer(1000/60, new TimerListener(this));
		if(start){
			timer.start();
		}

		setPanelOptions();
		
		JLabel oneUp = createSimpleLabel("1up");
		oneUp.setBounds(0, 0, 100, 30);
		JLabel score = createSimpleLabel("Score");
		score.setBounds(220, 0, 100, 30);
		JLabel lbl = createSimpleLabel("High Score");
		lbl.setBounds(GameWindow.BOARD_WIDTH-100, 0, 100, 30);
		this.add(oneUp);
		this.add(score);
		this.add(lbl);
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
		manager.tick();
	}

	/**
	 * Customizes the panel for game playing
	 */
	private void setPanelOptions() {
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	/**
	 * Adds all the sprites in the model to manager
	 */
	private void addModelToManager(ArrayList<Star> strs) {
		manager = new SpriteManager();
		manager.addSprite(game.getPlayerShip());
		game.populate();
		for (Alien al : game.getEnemies()) {
			manager.addSprite(al);
		}
		this.addStars(strs);
	}
	
	/**
	 * @param strs, from the previous panel
	 */
	public void addStars(ArrayList<Star> strs) {
		for(Star str: strs){
			manager.addSprite(str);
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		manager.draw(this, g);
	}
	
	public Game getGame(){
		return this.game;
	}
}
