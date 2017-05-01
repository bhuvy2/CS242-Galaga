package io;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TestLeaderboard {
	
	@Test
	public void testMaxScore() throws IOException {
		Leaderboard brd = spy(new Leaderboard());
		ArrayList<Player> mylist = spy(new ArrayList<>());
		int score = -1;
		for(int i = 0; i < 100; i++){
			int temp = (int)(Math.random()*100);
			if(score < temp){
				score = temp;
			}
			Player plr = new Player(""+i, temp);
			mylist.add(plr);
		}
		Collections.sort(mylist);
		brd.scores = mylist;
		assertEquals(score, brd.getMaxScore());
		Mockito.doNothing().when(brd).write();
	}
	
	@Test
	public void testAddScore() throws IOException {
		Leaderboard brd = spy(new Leaderboard());
		ArrayList<Player> mylist = spy(new ArrayList<>());
		int score = -1;
		for(int i = 0; i < 100; i++){
			int temp = (int)(Math.random()*100);
			if(score < temp){
				score = temp;
			}
			Player plr = new Player(""+i, temp);
			mylist.add(plr);
		}
		Collections.sort(mylist);
		brd.scores = mylist;
		Mockito.doNothing().when(brd).write();
		
		brd.addScore("Herro", 102);
		verify(mylist).add(anyInt(), anyObject());
		verify(brd).write();
		assertEquals(102, brd.getMaxScore());
	}

}
