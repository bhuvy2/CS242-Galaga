package display.view;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import display.util.TestUtil;
import display.view.panels.LeaderBoardPanel;

public class TestLeaderBoard {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		LeaderBoardPanel pnl = new LeaderBoardPanel();
		pnl.setSize(GameWindow.BOARD_WIDTH, GameWindow.BOARD_HEIGHT);
		BufferedImage img = TestUtil.createImage(pnl);
		assertTrue(TestUtil.diffImages(img, "test/img/main1.png"));
	}

}


