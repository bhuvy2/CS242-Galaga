package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;

public class Leaderboard {
	public static final String filename = "data/leaderboard.txt";
	
	public ArrayList<Player> scores;
	public Leaderboard() throws IOException {
		createIfNotExists();
		scores = loadScores();
	}
	private void createIfNotExists() throws IOException {
		File leaderFile = new File(filename);
		leaderFile.mkdirs();
		leaderFile.createNewFile();
	}
	private ArrayList<Player> loadScores() throws FileNotFoundException, IOException {
		ArrayList<Player> scores = new ArrayList<Player>();
		CSVReader reader = null;
		try{
			reader = new CSVReader(new FileReader(filename));
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
		return scores;
	}
}
