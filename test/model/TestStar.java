package model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import display.view.GameWindow;

public class TestStar {
	
	@Test
	public void testStarMove(){
		Star star = new Star(0, 0);
		star.move();
		assertTrue(star.getY() > 0);
		assertTrue(star.getX() == 0);
	}
	
	@Test
	public void testStarMoveOver(){
		Star star = new Star(0, GameWindow.BOARD_HEIGHT-1);
		star.move();
		assertTrue(star.getY() >= 0 && star.getY() < 10);
		assertTrue(star.getX() == 0);
	}
	
	@Test
	public void testParallax(){
		for(int i = 0; i < 100; ++i){
			Star star = new Star(0, 0);
			star.move();
			int y = star.getY();
			assertTrue(y == 1 || y == 2 || y == 4);
		}
	}

}
