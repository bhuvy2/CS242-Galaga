package display.view;

import java.util.ArrayList;


import org.junit.Ignore;
import org.junit.Test;

import display.view.GameWindow;
import display.view.panels.GamePanel;
import model.Star;
import static org.mockito.Mockito.*;

public class TestGameStart {

	@Test
	@Ignore ("Brings up GUI")
	public void testPaint() {
		GameWindow window = new GameWindow();
		ArrayList<Star> a = spy(new ArrayList<Star>());
		GamePanel panel = new GamePanel(a, false);
		for(int i = 0; i < 180; i++){
			panel.tick();
		}
		window.switchPanel(panel);
		Object lock = new Object();
		synchronized(lock) {
            while (window.isVisible())
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            System.out.println("Working now");
        }
	}

}
