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

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Bhuvan Venkatesh
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
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

	private SpriteManager manager;
	private long prev;
	
	public GamePanel(){
		this(true);
	}
	
	public GamePanel(boolean start){
		super();
		GamePanel self = this;
		ShipController controller = new ShipController();
		controller.setShipControls(self);
		addModelToManager();
		Timer timer = new Timer(1000/60, new TimerListener(this));
		if(start){
			timer.start();
		}
		prev = System.currentTimeMillis();
		setPanelOptions();
	}

	private void setPanelOptions() {
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	private void addModelToManager() {
		manager = new SpriteManager();
		manager.addSprite(Game.getPlayerShip());
		Game.populate();
		for (Alien al : Game.getEnemies()) {
			manager.addSprite(al);
		}
		
		for (int i = 0; i < GameWindow.BOARD_HEIGHT * 1.5; i += Star.length) {
			for (int j = 0; j < GameWindow.BOARD_WIDTH * 1.5; j += Star.length) {
				if (Math.random() > .995)
					manager.addSprite(new Star(i, j));
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		double diff = System.currentTimeMillis() - this.prev;
		if(diff != 0)
			g.drawString(String.valueOf(1000/(diff)), 20, 20);
		manager.draw(this, g);
		this.prev = System.currentTimeMillis();
	}
}
