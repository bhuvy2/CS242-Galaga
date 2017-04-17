package main;

import display.audio.GameSoundboard;
import display.view.GameWindow;

/**
 * @author Bhuvan Venkatesh
 *	Plays the game
 */
public class Runner {

	/**
	 * @param args, None
	 */
	public static void main(String[] args) {
		//GameSoundboard brd = new GameSoundboard();
		//brd.playFlying();
		//brd.playMissile();
		new GameWindow();
	}

}
