package io;

import java.util.Comparator;

public class Player implements Comparable<Player> {
	public String name;
	public int score;
	
	public Player(String name, int score){
		this.name = name;
		this.score = score;
	}

	public int compareTo(Player o1) {
		Player o2 = (Player)o1;
		return o2.score - this.score;
	}

}