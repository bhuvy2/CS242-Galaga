package display.view;

import org.junit.Test;

import display.view.panels.GamePanel;

public class TestGameStart {

	@Test
	public void testPaint() {
		GameWindow window = new GameWindow();
		GamePanel panel = new GamePanel(false);
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
