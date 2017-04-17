package display.view;

import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import display.util.TestUtil;

public class TestGamePanel {

	@Before
	public void setUp() throws Exception {
	}
	
	public BufferedImage createImage(JPanel panel) {

	    int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.paint(g);
	    return bi;
	}

	@Test
	public void testPaint() {
		GamePanel panel = new GamePanel();
		panel.setSize(GameWindow.BOARD_WIDTH, GameWindow.BOARD_HEIGHT);
		BufferedImage img = createImage(panel);
		//TestUtil.diffImages(img, "res/walks");
	}

}
