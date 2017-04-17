package model;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import model.superclasses.*;
import model.ships.Alien;
import display.view.GameWindow;

/**
 * This is for the Galaga Project in APCS
 * The Class Star s a star to be painted.
 * @author Bhuvan Venkatesh
 * @since 5/31/13
 */
public class Star extends GameSprite
{
	private static final Color[] colors = 
		{Color.BLACK, Color.BLUE, Color.CYAN, 
			Color.DARK_GRAY, Color.GRAY, 
			Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
			Color.PINK, Color.RED, Color.YELLOW
	};
	private Color starColor;
	private int count;
	public static final int length = 1;
	
	public Star(int toRight, int toEdge){
		super("");
		this.x = toRight;
		this.y = toEdge;
		count = 0;
		image = null;
		setStarColor(colors[(int) (Math.random()*colors.length)]);
	}
	

	public Star(int toRight, int toEdge, Color a){
		super("");
		this.x = toRight; //IN THIS CASE TO RIGHT ARE PIXELS NOT 1 TO 100
		this.y = toEdge; //SAME
		count = 0;
		setStarColor(a);
		image = null;
	}
	
	public void move()
	{
		if(count%(Alien.DELAY) == 0)
			this.y++;
		if(this.y > GameWindow.BOARD_HEIGHT)
			this.y = 0;
		count++;
	}
	
	public void fade()
	{
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
	
	public void tick(){
		this.move();
		if (Math.random() > .5)
			this.fade();
	}
	
	public void drawSelf(Component c, Graphics g){
		g.setColor(starColor);
		g.drawRect(x, y, Star.length, Star.length);
		g.fillRect(x, y, Star.length, Star.length);
	}

	public Color getStarColor() {
		return starColor;
	}
	public void setStarColor(Color starColor) {
		this.starColor = starColor;
	}
}