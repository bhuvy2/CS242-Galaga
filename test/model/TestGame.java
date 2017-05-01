package model;

import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import org.junit.Test;

import model.objects.aliens.Alien;
import model.objects.aliens.BasicAlien;
import model.objects.ship.BasicShip;

public class TestGame {
	
	@Test
	public void testGameReset(){
		Game gm = new Game();
		BasicShip ship = mock(BasicShip.class);
		gm.setPlayerShip(ship);
		for(int i = 0; i < 100; ++i)
			gm.tick();
		gm.setPlayerShip(ship);
		gm.resetGame();
		assertTrue(gm.getPoints() == 0);
		assertTrue(gm.getLevel() == 1);
		verify(ship, atLeast(1)).getStorage();
		assertTrue(gm.getShotsFired() == 0);
		assertTrue(gm.getShotsHit() == 0);
		assertTrue(gm.getEnemiesKilled() == 0);
		assertTrue(gm.getToNextLife() == 5000);
	}
	
	@Test
	public void testDraw(){
		Game gm = new Game();
		BasicShip ship = mock(BasicShip.class);
		ArrayList<Alien> mies = new ArrayList<>();
		Component c = mock(Component.class);
		Graphics g = mock(Graphics.class);
		for(int i = 0; i < 100; ++i){
			BasicAlien al = mock(BasicAlien.class);
			mies.add(al);
		}
		gm.setPlayerShip(ship);
		gm.setEnemies(mies);
		gm.draw(c, g);
		verify(ship).drawSelf(c, g);
		for(Alien al: mies){
			verify(al).drawSelf(c, g);
		}
	}
	
	@Test
	public void testResetAttack(){
		Game gm = new Game();
		BasicShip ship = mock(BasicShip.class);
		ArrayList<Alien> mies = new ArrayList<>();
		Component c = mock(Component.class);
		Graphics g = mock(Graphics.class);
		for(int i = 0; i < 100; ++i){
			BasicAlien al = mock(BasicAlien.class);
			mies.add(al);
		}
		gm.setPlayerShip(ship);
		gm.setEnemies(mies);
		gm.resetAttack();
		for (Alien a : mies){
			verify(a, atLeast(1)).getList();
			verify(a).setAttacking(false);
			verify(a).setMoving(true);
			verify(a).reset();
			verify(a).returnToPosition();
        }
	}
	
	// TODO: Put in a tick test

}
