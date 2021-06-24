import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Game {
	//main method
		public static void main(String[] args) {
			//create main game window
			JFrame mainFrame = new JFrame("Brick Breaker");
			
			//start screen with play button
			JFrame start = new JFrame("Brick Breaker");
			JButton play = new JButton("PLAY");
			JButton rules = new JButton("INSTRUCTIONS");
			JLabel intro = new JLabel("Welcome to Brick Breaker", SwingConstants.CENTER); 
			intro.setFont(new Font("Verdana",1,20));
			
			JFrame end = new JFrame();
			
			play.addActionListener(lister -> {
				start.setVisible(false);
				mainFrame.setVisible(true);
			});
			
			
			rules.addActionListener(lister -> {
				showRules(start);
			});
			
			//----------------------MAIN PANEL-----------------
			//set up parameters for main game window but do not make it visible yet
			JLabel lives = new JLabel("3 Lives | Score 0", SwingConstants.CENTER);
			mainFrame.getContentPane().add(lives, BorderLayout.PAGE_START);
			GamePanel panel = new GamePanel(mainFrame, end, start, lives);
			mainFrame.getContentPane().add(panel);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setVisible(false);
			mainFrame.setSize(600, 600);
			mainFrame.setResizable(false);
			
			//----------------START PANEL----------------------
			//set layout
			GridLayout startLayout = new GridLayout(0,1);
			start.setLayout(startLayout);
			end.setLayout(startLayout);
			//add buttons
			start.getContentPane().add(intro);
			start.getContentPane().add(play);
			start.getContentPane().add(rules);
			start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			start.setVisible(true);
			start.setSize(600, 600);
			start.setResizable(false);
			
			//-----------------END PANEL------------------------------------
			end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			end.setVisible(false);
			end.setSize(600, 600);
			end.setResizable(false);
		}

	private static void showRules(JFrame parent) {
		String info = "Welcome to Brick Breaker, here is how to play:\n\n"
				+ "1. The goal of the game is break the blocks and not let your ball drop below "
				+ "the screen\n"
				+ "2. You can move your paddle with the mouse or arrow keys\n"
				+ "3. Powerups and Handicaps can drop randomly, be ready!\n"
				+ "4. Most blocks break in one hit, but not all of them, be careful ;)\n"
				+ "5. Increase your score by hitting blocks and collecting objects\n"
				+ "6. Press Escape to quit the game at any time\n\n"
				+ "*TIP* If blocks look the same, they behave the same across all levels\n\n"
				+ "Have Fun! - Phil";
		JOptionPane.showMessageDialog(parent, info, "INSTRUCTIONS", 1);
		
	}

}
