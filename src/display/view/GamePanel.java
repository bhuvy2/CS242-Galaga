package display.view;

import controller.ShipController;
import main.Game;
import model.ships.*;
import model.superclasses.GameSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * @author Bhuvan Venkatesh
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private Timer timer;
	private double time;
	private SpriteManager manager;
	private long prev;
	private Ship ship;
	
	public GamePanel(){
		super();
		GamePanel self = this;
		this.setBackground(Color.BLACK);
		ShipController controller = new ShipController();
		controller.setShipControls(self);
		timer = new Timer(1000/60, new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (Game.isGameover()) {
					// TODO kill me
				}
				self.tick();
				self.repaint();
			}
		});
		timer.start();
		this.time = 0;
		
		manager = new SpriteManager();
		prev = System.currentTimeMillis();
		ship = Game.getPlayerShip();
		manager.addSprite(ship);
		populate();
		for (int i = 0; i < Game.getEnemies().size(); i++) {
			manager.addSprite(Game.getEnemies().get(i));
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		double diff = System.currentTimeMillis() - this.prev;
		if(diff != 0)
			g.drawString(String.valueOf(1000/(diff)), 20, 20);
		manager.draw(this, g);
//		ship.setX(ship.getX()+1);
		this.prev = System.currentTimeMillis();
	}
	
	public void tick(){
		manager.tick();
	}
	
	private class SpriteManager{
		private ArrayList<GameSprite> sprites;
		
		public SpriteManager() {
			this.sprites = new ArrayList<>();
		}
		
		public void tick(){
			for(GameSprite spr: sprites){
				spr.tick();
			}
		}
		
		public void addSprite(GameSprite spr){
			sprites.add(spr);
		}
		
		public void draw(Component cmp, Graphics g){
			for(GameSprite spr: sprites){
				spr.drawSelf(cmp, g);
			}
		}
	}

	public void populate() {
		ArrayList<Alien> enemies = new ArrayList<>();
		Alien in;
		for (int[] a : Alien.BasicPosition) {
			in = new BasicAlien(a[1], a[0]);
			in.isMoving = true;
			enemies.add(in);
		}
		for (int[] a : Alien.RedPosition) {
			in = new RedAlien(a[1], a[0]);
			in.isMoving = true;
			enemies.add(new RedAlien(a[1], a[0]));
		}
		for (int[] a : Alien.BossPosition) {
			in = new BlueAlien(a[1], a[0]);
			in.isMoving = true;
			enemies.add(new BlueAlien(a[1], a[0]));
		}
		Game.setEnemies(enemies);

	}
}
