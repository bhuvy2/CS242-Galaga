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

import javax.swing.JPanel;
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
			if (Game.isGameover()) {
				// TODO kill me
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
	 * The previous time the frame was drawn
	 */
	private long prev;
	/**
	 * The timer that fires off the event to update and redraw
	 */
	private Timer timer;

	
	/**
	 * Constructs a new gamepanel with bindings and
	 * sets up the timer
	 * @param start, whether to start the game or not.
	 */
	public GamePanel(ArrayList<Star> strs, boolean start){
		super();
		GamePanel self = this;
		ShipController controller = new ShipController();
		controller.setShipControls(self);
		addModelToManager();
		timer = new Timer(1000/60, new TimerListener(this));
		if(start){
			timer.start();
		}
		prev = System.currentTimeMillis();
		setPanelOptions();
		this.addStars(strs);
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
	private void addModelToManager() {
		manager = new SpriteManager();
		manager.addSprite(Game.getPlayerShip());
		Game.populate();
		for (Alien al : Game.getEnemies()) {
			manager.addSprite(al);
		}
	}
	
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
		double diff = System.currentTimeMillis() - this.prev;
		if(diff != 0)
			g.drawString(String.valueOf(1000/(diff)), 20, 20);
		manager.draw(this, g);
		this.prev = System.currentTimeMillis();
	}
}
