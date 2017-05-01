package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import display.view.GameWindow;
import model.objects.ship.BasicShip;
import model.objects.ship.Ship;

public class TestShip {

	@Test
	public void testMovementStop() {
		Ship sp = new BasicShip();
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
		Ship sp = new BasicShip();
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
		Ship sp = new BasicShip();
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
