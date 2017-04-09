package display.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private Timer timer;
	private int time;
	private SpriteManager manager;
	
	
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
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString(String.valueOf(this.time), 50, 50);
		this.time += 1;
	}
	
	private class SpriteManager{
		private ArrayList<Sprites> sprites;
		
		public SpriteManager() {
			this.sprites = new ArrayList<>();
		}
		
		public void tick(){
			
		}
	}
}
