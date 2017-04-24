package io;

import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestLeaderboard {
	
	@InjectMocks Leaderboard brd;
	
	@Mock String leaderBoardfilename;
	
	@Before  
	public void setUp() throws IOException {
		leaderBoardfilename = "data/temp";
		MockitoAnnotations.initMocks(this); //This could be pulled up into a shared base class
		brd = new Leaderboard();
	}
	
	@Test
	public void testLoad() {
		brd.addScore("Bhuvy", 10);
	}

}
