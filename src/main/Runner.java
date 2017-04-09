package main;

import java.io.File;

import display.audio.GameSoundboard;
import display.view.GameWindow;

public class Runner {

	public static void main(String[] args) {
		GameSoundboard brd = new GameSoundboard();
		brd.playFlying();
		for(int i = 0; i < 100; i++)
			brd.playMissile();
		new GameWindow();
	}

}
