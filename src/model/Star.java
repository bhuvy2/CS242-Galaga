package model;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import model.superclasses.*;
import model.ships.Alien;
import display.view.GameWindow;

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
	private int count;
	public static final int length = 1;
	
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
		super("");
		this.x = x;
		this.y = y;
		count = 0;
		setStarColor(a);
		image = null;
	}
	
	/**
	 * Moves the alien given a delay
	 */
	public void move(){
		if(count%(Alien.DELAY) == 0)
			this.y++;
		if(this.y > GameWindow.BOARD_HEIGHT)
			this.y = 0;
		count++;
	}
	
	/**
	 * Fades the star randomly
	 */
	public void fade(){
		if(!(count%(Alien.DELAY*2) == 0))
			return;
		
		switch(count/10 % 10)
		{
		case 0:
			break;
		case 1:
			setStarColor(getStarColor().brighter());
			break;
		case 2:
			break;
		case 3:
			setStarColor(getStarColor().brighter());
		case 4:
			break;
		case 5:
			setStarColor(getStarColor().brighter());
		case 6:
			break;
		case 7:
			setStarColor(getStarColor().darker());
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

	/**
	 * @return The color of the star
	 */
	public Color getStarColor() {
		return starColor;
	}
	/**
	 * @param starColor, sets the color to what the star has
	 */
	public void setStarColor(Color starColor) {
		this.starColor = starColor;
	}
}