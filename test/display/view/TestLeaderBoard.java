package display.view;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import display.util.TestUtil;

public class TestLeaderBoard {

	public void setUp() throws Exception {
	}

	public void test() {
		LeaderBoardPanel pnl = new LeaderBoardPanel();
		BufferedImage img = TestUtil.createImage(pnl);
		assertTrue(TestUtil.diffImages(img, "test/img/main1.png"));
	}

}
