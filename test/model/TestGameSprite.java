package model;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

import model.objects.ship.BasicShip;
import model.objects.ship.Ship;
import model.superclasses.GameSprite;

import static org.mockito.Mockito.*;

import java.awt.Component;
import java.awt.Graphics;

public class TestGameSprite {

	@Test
	public void testDraw() {
		GameSprite sp = new BasicShip();
		ImageIcon icon = mock(ImageIcon.class);
		Graphics g = mock(Graphics.class);
		Component c = mock(Component.class);
		sp.setImage(icon);
		sp.drawSelf(c, g);
		verify(icon).paintIcon(c, g, sp.getX(), sp.getY());
	}
	
	@Test
	public void testCenterY() {
		GameSprite sp = new BasicShip();
		sp.setX(0);
		sp.setY(0);
		ImageIcon icon = mock(ImageIcon.class);
		when(icon.getIconHeight()).thenReturn(new Integer(20));
		when(icon.getIconWidth()).thenReturn(new Integer(40));
		sp.setImage(icon);
		assertEquals(sp.getYCenter(),10);
		assertEquals(sp.getXCenter(),20);
	}

}
