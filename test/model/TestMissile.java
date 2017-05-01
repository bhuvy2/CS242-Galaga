package model;

import model.objects.projectile.Missile;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestMissile {
	
	@Test
	public void testMove(){
		Missile mis = new Missile(0, 40);
		mis.move();
		assertTrue(mis.getX() == 0);
		assertTrue(mis.getY() < 40);
	}
	
	@Test
	public void testIsVisible(){
		Missile mis = new Missile(0, 40);
		assertTrue(mis.isVisible());
		for(int i = 0; i < 100; i++)
			mis.move();
		assertFalse(mis.isVisible());
	}

}
