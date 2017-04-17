package display.view;

import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import model.superclasses.GameSprite;

/**
 * @author Bhuvan Venkatesh
 *	Manages the interface between the view and the
 *	model
 */
public class SpriteManager{
	
	/**
	 * Keeps track of the sprites
	 */
	private ArrayList<GameSprite> sprites;
	
	public SpriteManager() {
		this.sprites = new ArrayList<>();
	}
	
	/**
	 * Updates the view by one motion
	 */
	public void tick(){
		for(GameSprite spr: sprites){
			spr.tick();
		}
	}
	
	/**
	 * @param spr, the sprite to be added
	 * 	Adds a sprite to the manager
	 */
	public void addSprite(GameSprite spr){
		sprites.add(spr);
	}
	
	/**
	 * @param cmp, the component to draw as well
	 * @param g, graphics to draw
	 * 	Draws each of the components on the graphics object
	 */
	public void draw(Component cmp, Graphics g){
		for(GameSprite spr: sprites){
			spr.drawSelf(cmp, g);
		}
	}
}