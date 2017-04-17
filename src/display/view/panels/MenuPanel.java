package display.view.panels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import display.view.GameWindow;

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
	JButton playButton;
	
	/**
	 * Switches to the leaderboard
	 */
	JButton leaderButton;
	
	/**
	 * Sets up the callbacks and whatnot.
	 */
	public MenuPanel(){
		super();
		this.setBackground(Color.BLACK);
		setUpLogo();
		setUpPlayButton();
		setUpLeaderButton();
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
	private static JButton createSimpleButton(String text) {
	  JButton button = new JButton(text);
	  button.setForeground(Color.BLACK);
	  button.setBackground(Color.WHITE);
	  Border line = new LineBorder(Color.BLACK);
	  Border margin = new EmptyBorder(5, 15, 5, 15);
	  Border compound = new CompoundBorder(line, margin);
	  button.setBorder(compound);
	  return button;
	}
}
