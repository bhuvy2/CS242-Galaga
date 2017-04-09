package display.view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindow extends JFrame{
	
	protected static final int BOARD_WIDTH = 480; 
	protected static final int BOARD_HEIGHT = 740;
	

	public GameWindow(){
		
		this.add(new GamePanel()); //Add a new Gamefield
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(GameWindow.BOARD_WIDTH, GameWindow.BOARD_HEIGHT); //Used static constants
		this.setLocationRelativeTo(null);
		this.setTitle("Galaga");
		this.setResizable(false);
		this.setVisible(true);
	}

}
