package display.view;

import org.junit.Test;

import model.ships.BasicShip;

import static org.mockito.Mockito.*;

import java.awt.Component;
import java.awt.Graphics;

public class TestSpriteManager {
	
	@Test
	public void testTickCalled(){
		BasicShip mockedShip = mock(BasicShip.class);
		SpriteCache mng = new SpriteCache();
		mng.addSprite(mockedShip);
		mng.tick();
		verify(mockedShip).tick();
	}
	
	@Test
	public void testTickMultiple() {
		BasicShip mockedShip1 = mock(BasicShip.class);
		BasicShip mockedShip2 = mock(BasicShip.class);
		SpriteCache mng = new SpriteCache();
		mng.addSprite(mockedShip1);
		mng.addSprite(mockedShip2);
		mng.tick();
		verify(mockedShip1).tick();
		verify(mockedShip2).tick();
	}

	@Test
	public void testDrawCalled() {
		Component cmp = mock(Component.class);
		Graphics gr = mock(Graphics.class);
		BasicShip mockedShip1 = mock(BasicShip.class);
		BasicShip mockedShip2 = mock(BasicShip.class);
		SpriteCache mng = new SpriteCache();
		mng.addSprite(mockedShip1);
		mng.addSprite(mockedShip2);
		mng.draw(cmp, gr);
		verify(mockedShip1).drawSelf(cmp, gr);
		verify(mockedShip2).drawSelf(cmp, gr);
	}
}
