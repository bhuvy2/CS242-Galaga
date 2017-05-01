package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.objects.aliens.Alien;
import model.objects.aliens.BasicAlien;
import model.objects.projectile.AlienMissile;
import static org.mockito.Mockito.*;
public class TestAlienMissile {

	@Test
	public void testDown() {
		Alien al = new BasicAlien(0, 0);
		AlienMissile am = new AlienMissile(al, AlienMissile.Slope.Down);
		int x = am.getX();
		int y = am.getY();
		am.move();
		assertEquals(x, am.getX());
		assertTrue(y < am.getY());
	}
	
	@Test
	public void testShallow() {
		Alien al = new BasicAlien(0, 0);
		AlienMissile am = new AlienMissile(al, AlienMissile.Slope.ShallowLeft);
		int x = am.getX();
		int y = am.getY();
		am.move();
		assertTrue(x > am.getX());
		assertTrue(y < am.getY());
		assertTrue(Math.abs(x - am.getX()) == Math.abs(y - am.getY()));
		
		am = new AlienMissile(al, AlienMissile.Slope.ShallowRight);
		x = am.getX();
		y = am.getY();
		am.move();
		assertTrue(x < am.getX());
		assertTrue(y < am.getY());
		assertTrue(Math.abs(x - am.getX()) == Math.abs(y - am.getY()));
	}

	@Test
	public void testDirection() {
		Alien al = new BasicAlien(50, 50);
		AlienMissile am = new AlienMissile(al, AlienMissile.Slope.Left);
		int x = am.getX();
		int y = am.getY();
		am.move();
		assertTrue(x > am.getX());
		assertTrue(y < am.getY());
		assertTrue(Math.abs(x - am.getX()) < Math.abs(y - am.getY()));
		
		am = new AlienMissile(al, AlienMissile.Slope.Right);
		x = am.getX();
		y = am.getY();
		am.move();
		assertTrue(x < am.getX());
		assertTrue(y < am.getY());
		assertTrue(Math.abs(x - am.getX()) < Math.abs(y - am.getY()));
	}
	
	@Test
	public void testDiagonal() {
		Alien al = new BasicAlien(50, 50);
		AlienMissile am = new AlienMissile(al, AlienMissile.Slope.LeftDiagonal);
		int x = am.getX();
		int y = am.getY();
		am.move();
		assertTrue(x > am.getX());
		assertTrue(y < am.getY());
		assertTrue(Math.abs(x - am.getX()) < Math.abs(y - am.getY()));
		
		am = new AlienMissile(al, AlienMissile.Slope.RightDiagonal);
		x = am.getX();
		y = am.getY();
		am.move();
		assertTrue(x < am.getX());
		assertTrue(y < am.getY());
		assertTrue(Math.abs(x - am.getX()) < Math.abs(y - am.getY()));
	}
	
	@Test
	public void testFlip() {
		Alien al = new BasicAlien(50, 50);
		AlienMissile am = spy(new AlienMissile(al, AlienMissile.Slope.LeftDiagonal));
		when(am.isVisible()).thenReturn(false);
		int x = am.getX();
		int y = am.getY();
	}
}
