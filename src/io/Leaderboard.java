package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author Bhuvan Venkatesh
 *	Loads and updates the leaderboard file
 */
public class Leaderboard {
	
	/**
	 * Where the data is loaded
	 */
	private static final String leaderboardFilename = "data/leaderboard.txt";
	
	/**
	 * Keeps the scores of the players sorted
	 */
	public ArrayList<Player> scores;
	
	/**
	 * @throws IOException If the file cannot be created or written to
	 * 
	 * Loads the file into memory and prepares for writing.
	 */
	public Leaderboard() throws IOException {
		createIfNotExists();
		scores = loadScores();
	}
	
	/**
	 * @throws IOException, if not writeable
	 */
	private void createIfNotExists() throws IOException {
		File leaderFile = new File(leaderboardFilename);
		leaderFile.mkdirs();
		leaderFile.createNewFile();
	}
	
	/**
	 * @return A descendingly sorted array of players
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private ArrayList<Player> loadScores() 
			throws FileNotFoundException, IOException {
		ArrayList<Player> scores = new ArrayList<Player>();
		CSVReader reader = null;
		try{
			reader = new CSVReader(new FileReader(leaderboardFilename));
			for(String[] record = reader.readNext(); 
					record != null; record = reader.readNext()) {
				scores.add(new Player(record[0], Integer.parseInt(record[1])));
			}
		}
		finally{
			if(reader != null){
				reader.close();
			}
		}
		Collections.sort((List<Player>)scores);
		return scores;
	}
}
