package display.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.ships.BasicShip;
import model.ships.Ship;
import model.superclasses.GameSprite;


@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private Timer timer;
	private double time;
	private SpriteManager manager;
	private long prev;
	private Ship ship;
	
	public GamePanel(){
		GamePanel self = this;
		this.setBackground(Color.BLACK);
		timer = new Timer(1000/60, new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				self.repaint();
			}
		});
		timer.start();
		this.time = 0;
		
		manager = new SpriteManager();
		prev = System.currentTimeMillis();
		ship = new BasicShip();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		double diff = System.currentTimeMillis() - this.prev;
		if(diff != 0)
			g.drawString(String.valueOf(1000/(diff)), 20, 20);
		ship.drawSelf(this, g);
		ship.setX(ship.getX()+1);
		this.prev = System.currentTimeMillis();
	}
	
	private class SpriteManager{
		private ArrayList<GameSprite> sprites;
		
		public SpriteManager() {
			this.sprites = new ArrayList<>();
		}
		
		public void tick(){
			for(GameSprite spr: sprites){
				//spr.tick();
			}
		}
	}
}
