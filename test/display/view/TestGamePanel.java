package display.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import display.view.panels.GamePanel;
import model.Game;
import model.Star;
import model.ships.Ship;

public class TestGamePanel {
	
	@InjectMocks GamePanel pnl = new GamePanel(new ArrayList<Star>(), true);
	
	@Mock Timer timer = mock(Timer.class);
	
	@Mock Game game;
	
	private Ship shp;
	
	@Before  
	public void setUp() throws IOException {
		this.shp = mock(Ship.class);
		game = mock(Game.class);
		when(game.getPlayerShip()).thenReturn(shp);
		when(shp.isInvincible()).thenReturn(false);
		game.setPlayerShip(this.shp);
		MockitoAnnotations.initMocks(this); //This could be pulled up into a shared base class
	}
	
	@Test
	public void testTimer(){
		verify(timer).start();
	}

}
