package model;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import model.superclasses.*;
import model.ships.Alien;
import display.view.GameWindow;
import io.GameConfig;

/**
 * The Class Star s a star to be painted.
 * @author Bhuvan Venkatesh
 */
public class Star extends GameSprite
{
	/**
	 * Colors that the star could take on
	 */
	private static final Color[] colors = 
		{Color.BLACK, Color.BLUE, Color.CYAN, 
			Color.DARK_GRAY, Color.GRAY, 
			Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
			Color.PINK, Color.RED, Color.YELLOW
	};
	private Color starColor;
	public static final int length = 1;
	private static final int[] parallaxLengths = {1, 2, 4};
	private int step;
	
	/**
	 * @param x, the x position of the start
	 * @param y, the y position of the start
	 */
	public Star(int x, int y){
		this(x, y, colors[(int) (Math.random()*colors.length)]);
	}
	

	/**
	 * Constructs a start with a specific color
	 * @param x
	 * @param y
	 * @param a
	 */
	public Star(int x, int y, Color a){
		super(GameConfig.getBluePath());
		this.x = x;
		this.y = y;
		this.starColor = a;
		image = null;
		step = parallaxLengths[(int)(Math.random() * parallaxLengths.length)];
	}
	
	/**
	 * Moves the alien given a delay
	 */
	public void move(){
		this.y += step;
		if(this.y > GameWindow.BOARD_HEIGHT)
			this.y = 0;
	}
	
	/**
	 * Fades the star randomly
	 */
	public void fade(){
		int choice = (int)(Math.random() * 10);
		if(choice <= 4){
			this.starColor = this.starColor.brighter();
		}else if(choice <= 6){
			this.starColor = this.starColor.darker();
		}
	}
	
	/* (non-Javadoc)
	 * @see model.superclasses.GameSprite#tick()
	 */
	public void tick(){
		this.move();
		if (Math.random() > .5)
			this.fade();
	}
	
	/* (non-Javadoc)
	 * @see model.superclasses.GameSprite#drawSelf(java.awt.Component, java.awt.Graphics)
	 */
	public void drawSelf(Component c, Graphics g){
		g.setColor(starColor);
		g.drawRect(x, y, Star.length, Star.length);
		g.fillRect(x, y, Star.length, Star.length);
	}
}