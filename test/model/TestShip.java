package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;

import org.junit.Test;

import display.view.GameWindow;
import model.objects.ship.BasicShip;
import model.objects.ship.Ship;

public class TestShip {

	@Test
	public void testMovementStop() {
		Game gm = spy(new Game());
		Ship sp = new BasicShip(gm);
		sp.setStop();
		for(int i = 0; i < 100; ++i){
			int x = sp.getX();
			int y = sp.getY();
			sp.tick();
			assertEquals(y, sp.getY());
			assertEquals(x, sp.getX());
		}
	}
	
	@Test
	public void testMovementRight() {
		Game gm = spy(new Game());
		Ship sp = new BasicShip(gm);
		sp.setRight();
		for(int i = 0; i < 500; ++i){
			int x = sp.getX();
			int y = sp.getY();
			sp.tick();
			assertEquals(y, sp.getY());
			if(x <= GameWindow.BOARD_WIDTH-32)
				assertTrue(x < sp.getX());
			else
				assertTrue(x == sp.getX());
		}
	}
	
	@Test
	public void testMovementLeft() {
		Game gm = spy(new Game());
		Ship sp = new BasicShip(gm);
		sp.setLeft();
		for(int i = 0; i < 100; ++i){
			int x = sp.getX();
			int y = sp.getY();
			sp.tick();
			assertEquals(y, sp.getY());
			if(x != 0){
				assertTrue(x > sp.getX());
			}else{
				assertTrue(sp.getX() == 0);
			}
		}
	}

}
