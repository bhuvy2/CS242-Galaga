package display.view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import display.view.panels.GamePanel;
import display.view.panels.LeaderBoardPanel;
import display.view.panels.MenuPanel;

/**
 * @author Bhuvan Venkatesh
 *	Subclass window frame
 */
@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	
	/**
	 * Unresizable width of the board
	 */
	public static final int BOARD_WIDTH = 480; 
	/**
	 * Unresizable height of the board
	 */
	public static final int BOARD_HEIGHT = 740;
	
	/**
	 * Used in systems that offer impact font
	 */
	public static final Font gameFont = new Font("impact", 0, 16);
	
	/**
	 * Reference to the current panel
	 */
	private JPanel panel;

	/**
	 * Starts the window with the menu panel and sets the size and lodation
	 */
	public GameWindow(){
		
		this.panel = new MenuPanel();
		
		this.add(this.panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(GameWindow.BOARD_WIDTH, GameWindow.BOARD_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setTitle("Galaga");
		this.setResizable(false);
		this.setVisible(true);
		this.setFocusable(true);
	}
	
	/**
	 * Starts a new game
	 */
	public void switchToGame(){
		this.switchPanel((JPanel)new GamePanel());
	}
	
	/**
	 * Goes back to the menu
	 */
	public void switchToMenu(){
		this.switchPanel((JPanel)new MenuPanel());
	}
	
	/**
	 * Shows the leaderboard screen
	 */
	public void switchToLeaderBoard(){
		this.switchPanel((JPanel)new LeaderBoardPanel());
	}
	
	/**
	 * @param pnl to switch to
	 * Switches the current panel to the current JPanel
	 */
	public void switchPanel(JPanel pnl){
		this.remove(this.panel);
		this.panel = pnl;
		this.add(this.panel);
		this.revalidate();
	}

	public static int getBoardHeight() {
		return BOARD_HEIGHT;
	}

	public static int getBoardWidth() {
		return BOARD_WIDTH;
	}
}
