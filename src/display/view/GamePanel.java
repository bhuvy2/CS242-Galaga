package display.view;

import controller.ShipController;
import model.Game;
import model.ships.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.ships.Ship;

/**
 * @author Bhuvan Venkatesh
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private Timer timer;
	private SpriteManager manager;
	private long prev;
	public Ship ship;
	
	public GamePanel(){
		super();
		GamePanel self = this;
		ShipController controller = new ShipController();
		controller.setShipControls(self);
		addModelToManager();
		timer = new Timer(1000/60, new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (Game.isGameover()) {
					// TODO kill me
				}
				self.manager.tick();
				self.repaint();
			}
		});
		timer.start();
		
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
		ship = Game.getPlayerShip();
		manager.addSprite(ship);
		Game.populate();
		for (Alien al : Game.getEnemies()) {
			manager.addSprite(al);
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
