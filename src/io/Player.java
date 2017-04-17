package io;

/**
 * @author Bhuvan Venkatesh
 *	Represents one of the csv object
 */
public class Player implements Comparable<Player> {
	
	/**
	 * Name of the player
	 */
	public final String name;
	/**
	 * The score that they achieved
	 */
	public final int score;
	
	/**
	 * @param name, player name
	 * @param score, score that they achieved
	 */
	public Player(String name, int score){
		this.name = name;
		this.score = score;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Player o1) {
		Player o2 = (Player)o1;
		return o2.score - this.score;
	}

}