package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.objects.aliens.Alien;
import model.objects.aliens.BasicAlien;
import model.objects.projectile.AlienMissile;

import static org.mockito.Mockito.*;

import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class TestAlien {
	
	@Test
	public void testDying(){
		Alien b = new BasicAlien(0, 0);
		b.die();
		assertTrue(b.isDying());
	}

	@Test
	public void testFire() {
		Alien al = new BasicAlien(0, 0);
		ArrayList<AlienMissile> mis = spy(new ArrayList<AlienMissile>());
		al.setList(mis);
		al.fire();
		verify(mis, atLeast(3)).add((AlienMissile)anyObject());
	}
	
	@Test
	public void testDrawSelf(){
		Alien al = new BasicAlien(0, 0);
		ArrayList<AlienMissile> mis = spy(new ArrayList<AlienMissile>());
		for(int i = 0; i < 100; i++){
			AlienMissile m = spy(new AlienMissile(al, AlienMissile.Slope.Down));
			m.setImage(spy(m.getImage()));
			mis.add(m);
		}
		ImageIcon im = mock(ImageIcon.class);
		Component c = mock(Component.class);
		Graphics g = mock(Graphics.class);
		al.setList(mis);
		al.setImage(im);
		al.drawSelf(c, g);
		verify(im).paintIcon(c, g, al.getX(), al.getY());
		for(AlienMissile m : mis)
			verify(m.getImage()).paintIcon(c, g, m.getX(), m.getY());
	}
	
	@Test
	public void testTickDisappear(){
		Alien al = new BasicAlien(0, 0);
		ArrayList<AlienMissile> mis = spy(new ArrayList<AlienMissile>());
		for(int i = 0; i < 10; i++){
			AlienMissile m = spy(new AlienMissile(al, AlienMissile.Slope.Down));
			when(m.isBounce()).thenReturn(false);
			when(m.isVisible()).thenReturn(false);
			m.setImage(spy(m.getImage()));
			mis.add(m);
		}
		ImageIcon im = mock(ImageIcon.class);
		Component c = mock(Component.class);
		Graphics g = mock(Graphics.class);
		al.setList(mis);
		al.tick();
		assertTrue(mis.size() == 0);
	}
	
	@Test
	public void testTickMove(){
		Alien al = new BasicAlien(0, 0);
		ArrayList<AlienMissile> mis = spy(new ArrayList<AlienMissile>());
		for(int i = 0; i < 10; i++){
			AlienMissile m = spy(new AlienMissile(al, AlienMissile.Slope.Down));
			when(m.isBounce()).thenReturn(false);
			when(m.isVisible()).thenReturn(true);
			m.setImage(spy(m.getImage()));
			mis.add(m);
		}
		ImageIcon im = mock(ImageIcon.class);
		Component c = mock(Component.class);
		Graphics g = mock(Graphics.class);
		al.setList(mis);
		al.tick();
		for(AlienMissile m : mis){
			verify(m).move();
		}
	}

}
