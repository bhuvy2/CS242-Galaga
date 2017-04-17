package display.view;

import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import model.superclasses.GameSprite;

public class SpriteManager{
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