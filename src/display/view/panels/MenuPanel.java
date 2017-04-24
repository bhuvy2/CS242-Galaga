package display.view.panels;

import display.view.GameWindow;
import model.Star;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Bhuvan Venkatesh
 *	Shows the MenuPanel
 */
public class MenuPanel extends JPanel {
	
	/**
	 * Galaga log image
	 */
	Image logo;
	/**
	 * The "Go to game" button
	 */
	private JButton playButton;
	
	/**
	 * Switches to the leaderboard
	 */
	private JButton leaderButton;
	
	/**
	 * COntains the stars on the page
	 */
	private ArrayList<Star> stars;
	
	/**
	 * Sets up the callbacks and whatnot.
	 */
	public MenuPanel(){
		super();
		stars = new ArrayList<Star>();
		this.setBackground(Color.BLACK);
		setUpLogo();
		setUpPlayButton();
		setUpLeaderButton();
		addStars();
	}
	
	/**
	 * Add stars randomly
	 */
	private void addStars() {
		for (int i = 0; i < GameWindow.BOARD_HEIGHT * 1.5; i += Star.length) {
			for (int j = 0; j < GameWindow.BOARD_WIDTH * 1.5; j += Star.length) {
				if (Math.random() > .999){
					Star str = new Star(i, j);
					stars.add(str);
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Star str: stars){
			str.drawSelf(this, g);
		}
	}

	/**
	 * Adds the galaga logo to the screen
	 */
	private void setUpLogo() {
		BufferedImage temp = null;
		try{
			temp = ImageIO.read(new File("res/galaga.png"));
		}catch(IOException | NullPointerException e){
			System.out.println("res/galaga.png");
			e.printStackTrace();
		}
		ImageIcon img = null;
		if(temp != null){
			img = new ImageIcon((Image)temp);
		}
		JLabel lbl = new JLabel(img);
		lbl.setBounds(20, 20, img.getIconWidth(), img.getIconHeight());
		this.add(lbl);
	}

	/**
	 * Constructs the go to leaderboard button
	 * With a specific style
	 */
	private void setUpLeaderButton() {
		MenuPanel self = this;
		leaderButton = createSimpleButton("Leaderboard");
		leaderButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				GameWindow f2 = (GameWindow) SwingUtilities.getWindowAncestor(self);
				f2.switchToLeaderBoard();
			}
		});
		leaderButton.setBounds(200, 150, 100, 100);
		this.add(leaderButton);
	}

	/**
	 * Sets up the play button with a specific style
	 */
	private void setUpPlayButton() {
		MenuPanel self = this;
		playButton = createSimpleButton("Go to game!");
		playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				GameWindow f2 = (GameWindow) SwingUtilities.getWindowAncestor(self);
				f2.switchToGame();
			}
		});
		playButton.setBounds(200, 50, 100, 100);
		this.add(playButton);
	}
	
	/*
	 * Stack overflow 
	 * http://stackoverflow.com/questions/1839074/howto-make-jbutton-with-simple-flat-style/1839826
	 */
	public static JButton createSimpleButton(String text) {
	  JButton button = new JButton(text);
	  button.setForeground(Color.BLACK);
	  button.setBackground(Color.WHITE);
	  button.setBorderPainted(false);
	  button.setContentAreaFilled(false);
	  button.setOpaque(true);
	  Border line = new LineBorder(Color.BLACK);
	  Border margin = new EmptyBorder(5, 15, 5, 15);
	  Border compound = new CompoundBorder(line, margin);
	  button.setBorder(compound);
	  return button;
	}
	
	/**
	 * @return the stars
	 */
	public ArrayList<Star> getStars(){
		return this.stars;
	}
}
