package display.view;

import org.junit.Test;

import io.SpriteCache;
import model.ships.BasicShip;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class TestSpriteCache {
	
	private static final String resourceString = "res/galaga.png";
	
	@Test
	public void testResourceExists(){
		assertNotEquals(null, SpriteCache.get(resourceString));
	}
	
	@Test
	public void testResourceExistsCache() {
		ImageIcon img = SpriteCache.get(resourceString);
		assertTrue(img == SpriteCache.get(resourceString));
	}

	@Test
	public void testResourceNotExists() {
		assertEquals(null, SpriteCache.get("asfdasdfasdf"));
	}
}
