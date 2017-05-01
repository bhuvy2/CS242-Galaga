package display;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import display.view.GameWindow;
import display.view.panels.GamePanel;
import model.Game;
import model.Star;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import display.view.panels.TimerListener;
import io.Leaderboard;

import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.easymock.PowerMock.mockStatic;
@PowerMockIgnore("javax.swing.*")
@RunWith(PowerMockRunner.class)
public class TestTimer {

	@Test
	public void testGameLoop() {
		GamePanel panel = mock(GamePanel.class);
		Game gm = mock(Game.class);
		when(panel.getGame()).thenReturn(gm);
		TimerListener list = new TimerListener(panel);
		list.actionPerformed(null);
		verify(panel).tick();
		verify(panel).updateLabels();
		verify(panel).repaint();
	}
	
	@Test
	public void testGameOver() {
		ArrayList<Star> str = new ArrayList<>();
		GameWindow parent = spy(new GameWindow());
		GamePanel panel = spy(new GamePanel(str, false));
		Game gm = spy(new Game());
		Leaderboard brd = mock(Leaderboard.class);
		when(panel.getBoard()).thenReturn(brd);
		when(panel.getGame()).thenReturn(gm);
		when(gm.isGameOver()).thenReturn(true);
		mockStatic(SwingUtilities.class);

	    when(SwingUtilities.getWindowAncestor(panel)).thenReturn(parent);
		parent.removeAll();
		parent.add(panel);
		parent.revalidate();
		TimerListener list = new TimerListener(panel);
		list.actionPerformed(null);
		verify(panel).getBoard();
		verify(brd).addScore(anyString(), anyInt());
	}

}
