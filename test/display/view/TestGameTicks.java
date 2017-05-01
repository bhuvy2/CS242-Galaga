package display.view;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import display.view.panels.GamePanel;

import model.Star;

public class TestGameTicks {

	@Test
	@Ignore ("Brings up GUI")
	public void testPaint() {
		GameWindow window = new GameWindow();
		GamePanel panel = new GamePanel(new ArrayList<Star>(), false);
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
