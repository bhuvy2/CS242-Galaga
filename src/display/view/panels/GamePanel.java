package display.view.panels;

import controller.GameController;
import controller.ShipController;
import display.view.GameWindow;
import io.Leaderboard;
import model.Game;
import model.Star;
import model.objects.ship.Ship;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Bhuvan Venkatesh
 *	Is the view in the MVC
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	/**
	 * The timer that fires off the event to update and redraw
	 */
	private Timer timer;

	/**
	 * Model for the game
	 */
	private Game game;
	
	/**
	 * Points to next life
	 */
	private JLabel toNext;
	
	/**
	 * Scores
	 */
	private JLabel scoreLabel;
	
	/**
	 * Displays the high score
	 */
	private JLabel highScoreLabel;

	/**
	 * Displays any power ups
	 */
	private JLabel powers;
	
	/**
	 * Keeps track of the stars
	 */
	private ArrayList<Star> stars;
	
	/**
	 * An instance to the totals
	 */
	private Leaderboard leaderbrd;
	
	/**
	 * Keeps track of the point totals and powerups
	 */
	public void updateLabels(){
	    // Set point texts
		toNext.setText("" + game.getToNextLife());
		scoreLabel.setText("" + game.getPoints());

		// Set string to display active power ups
		String powerUps = "";
		if (game.getPlayerShip().isInvincible())
		    powerUps = powerUps.concat("INVINCIBLE ");
        if (game.getPlayerShip().isMultipleShots())
		    powerUps = powerUps.concat("MULTI-SHOT ");
		if (Ship.getMaxShots() > 2)
		    powerUps = powerUps.concat("MAX-SHOTS");
        powers.setText(powerUps);
	}
	
	/**
	 * Constructs a new gamepanel with bindings and
	 * sets up the timer
	 * @param start, whether to start the game or not.
	 */
	public GamePanel(ArrayList<Star> strs, boolean start){
		super();
		game = new Game();

		// Set Keybinds
		GameController gameController = new GameController();
		ShipController controller = new ShipController();
		gameController.setGameControls(this);
		controller.setShipControls(this);

		// Set timer
		this.setLayout(null);

		// Create stars
		stars = strs;

		// Set Options
		setPanelOptions();
		setUpInfoLabels();
		
		timer = new Timer(1000/60, new TimerListener(this));
		if(start){
			timer.start();
		}
	}

	// Sets up labels for game panel
	private void setUpInfoLabels() {
		addOneUpLabel();
		addScoreLabel();
		addHighScoreLabel();
		addPowerUpNotifications();
	}

    // Adds high score label to panel
	private void addHighScoreLabel() {
		JLabel lbl = createSimpleLabel("High Score");
		lbl.setBounds(GameWindow.BOARD_WIDTH-100, 0, 100, 30);
		this.add(lbl);
		String score = "";
		try {
			leaderbrd = new Leaderboard();
			score = "" + leaderbrd.getMaxScore();
		} catch (IOException e) {
			
		}
		highScoreLabel = createSimpleLabel(score);
		highScoreLabel.setForeground(Color.WHITE);
		highScoreLabel.setBounds(GameWindow.BOARD_WIDTH-100, 25, 100, 30);
		this.add(highScoreLabel);
	}

    // Adds game score label to panel
	private void addScoreLabel() {
		JLabel score = createSimpleLabel("Score");
		score.setBounds(220, 0, 100, 30);
		this.add(score);

		scoreLabel = createSimpleLabel("");
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setBounds(220, 25, 100, 30);
		this.add(scoreLabel);
	}

    // Adds points to oneup label to panel
	private void addOneUpLabel() {
        JLabel oneUp = createSimpleLabel("1up");
        oneUp.setBounds(0, 0, 100, 25);
        this.add(oneUp);

        toNext = createSimpleLabel("");
        toNext.setForeground(Color.WHITE);
        toNext.setBounds(0, 25, 100, 30);
        this.add(toNext);
    }

    // Adds power up label to panel
	private void addPowerUpNotifications() {
        // Display power up notifications
        powers = createSimpleLabel("");
        powers.setForeground(Color.WHITE);
        powers.setHorizontalAlignment(SwingConstants.RIGHT);
        powers.setBounds(170, 680, 300,30);
        this.add(powers);
    }

	
	/**
	 * @param lbl, the string to put inside the label
	 * @return The default 
	 */
	public JLabel createSimpleLabel(String lbl){
		JLabel jlbl = new JLabel(lbl);
		jlbl.setFont(new Font("impact", 0, 16));
		jlbl.setForeground(Color.RED);
		return jlbl;
	}
	
	/**
	 * Starts the game if not started before
	 */
	public void start(){
		timer.start();
	}
	
	/**
	 * Forces the manager to update
	 */
	public void tick(){
		game.tick();
		for(Star str: this.stars){
			str.tick();
		}
	}

	/**
	 * Customizes the panel for game playing
	 */
	private void setPanelOptions() {
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		game.draw(this, g);
		for(Star str: this.stars){
			str.drawSelf(this, g);
		}
	}
	
	/**
	 * @return The model in the MVC
	 */
	public Game getGame(){
		return this.game;
	}

	// Toggles timer run time effectively pausing the game
	public void toggleTimer() {
		if (timer.isRunning())
			timer.stop();
		else
			timer.start();
	}
	
	/**
	 * @return The leader board
	 */
	public Leaderboard getBoard(){
		return this.leaderbrd;
	}


}
