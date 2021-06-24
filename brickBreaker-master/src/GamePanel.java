import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//create a panel for the game visual (like game canvas)
public class GamePanel extends JPanel implements KeyListener{
	//create ball
	private Ball ball = new Ball(300, 400, 15, 15, "ball.png");
	//create paddle
	private Paddle paddle = new Paddle(225, 500, 150, 25, "bottom-glow.png");
	//create powerups
	Powerup life, nextLev, die, invincible, back, grow, shrink; 
	double xcur= 0, xprev = -1, xdprev = -2;
	double ycur= 0, yprev = -1, ydprev = -2;
	//how many lives does the player have
	private final Set<Powerup> powerups;
	private int lives = 3; 
	//what level is the player on
	private int curLevel = 1; 
	//has the game began?
	boolean started = false;
        boolean play = false;
	//create reference thread
	Thread thread; 
	//matrix of current blocks
	private Block[][] blocks;
        
        private boolean right;
        private boolean left;

	private int score; 

	private Levels L = new Levels(600, 600);
        private boolean end = false, thru = false;
	//frames for start/end screen
	JFrame mainFrame, endFrame, startFrame;
        JLabel endMessage;
	JLabel livesLabel;
	 
	GamePanel(JFrame mainFrame, JFrame endFrame, JFrame startFrame, JLabel lives) {
            //get levels
            loadLevel(curLevel-1);
            //set focusable
            setFocusable(true);
            this.mainFrame = mainFrame;
            this.endFrame = endFrame; 
            this.startFrame = startFrame;
            this.livesLabel = lives;
            this.powerups = new TreeSet<>();
            
            endMessage = new JLabel("Congrats you beat all " + curLevel + " levels with a "
					+ "score of " + score,
					SwingConstants.CENTER);
            
            endMessage.setFont(new Font("Verdana",1,20));
            
            JButton exit = new JButton("Exit"); 
            
            exit.addActionListener((ActionEvent lister) -> {
                endFrame.setVisible(false);
                startFrame.setVisible(true);  
            });	
            
            this.endFrame.getContentPane().add(endMessage);
            this.endFrame.getContentPane().add(exit);
            
                       
            initKeyListeners();
            initGame();
	}
        
        
        
        private void initKeyListeners(){
            //make this class the key listen
            addKeyListener(this);
        }

	// -------------------- UPDATE GAME STATE -----------------------------------

	public void update() {
            if(play){
                ball.moveBall(xcur, ycur, xprev, yprev, xdprev, ydprev, getWidth());
            }
            
          
            if (ball.y > getHeight()) {
		loseLife();
            }
            
            paddle.movePaddle(right, left, getWidth());
            
            //handle paddle collisions with ball
            if(paddle.intersects(ball)) {
                ball.bouncePaddle(paddle.x,paddle.width);
            }	
            
            //Handle block collisions with ball
            for (Block[] block : blocks) {
                for (int j = 0; j < block.length; j++) {
                    Block curBlock = block[j];
                    if (curBlock != null && ball.intersects(curBlock)) {
                        score += 10;
                        //hit the block
                        curBlock.hit();
                        //unless under a powerup bouce the ball
                        if (!thru) {
                            ball.setvY(ball.getvY() * -1);
                        }
                        //if it has no lives left remove it
                        if (curBlock.getHits() < 1) {
                            block[j] = null;
                        }
                        if(levelStatus()) {
                            nextlevel();
                        }
                        else {
                            powerup(ball.x, ball.y);
                        }
                    }
                }
            }
		//UPDATE BALL POSITION BASED ON VELOCITY

                

            //update state label
            livesLabel.setText(lives + " Lives" +" | Score " + score);

            //Update falling powerups and handle collisions
            powerups.forEach((p) -> {
                if (paddle.intersects(p)) {
                    score += 100;
                    handle(p);
                    powerups.remove(p);
                }
                else {
                    if(p.isGone(getHeight())) {
                        powerups.remove(p);
                    }
                    else {
                        p.fall();
                    }
                }
            });
            repaint();
	}

	void loseLife() {
            lives--; 
            //depending on how many lives left, keep playing or lose game
            if(lives != 0) {
		reset();
            }
            else {
		end(false);
            }
	}

	void handle(Powerup p) {
            if(p == life) {
		lives ++; 
		livesLabel.setText(lives + " Lives");
            }
            else if(p == nextLev) {
		nextlevel();
            }
            else if(p == die) {
                loseLife();
            }
            else if(p == invincible) {
		thru = true;
            }
            else if(p == back) {
		end(false);
            }
            else if(p == grow && !paddle.isBig()) {
		paddle.x-= paddle.getWidth()/2;
		paddle.grow();
            }
            else if(p == shrink && !paddle.isSmall()) {
            	paddle.x+= paddle.getWidth()/2;
            	paddle.shrink();
            }
	}

	//Decide if a powerup should drop
	void powerup(int x, int y) {
		if(!powerups.isEmpty()) {
			return;
		}
		double drop = Math.random();
		//bad powerup
		if (drop <= 0.07) {
			double rand = Math.random();
			if (rand < 0.35) {
				shrink = new Powerup(x, y, 30, 30, 2, "shrink.png");
				powerups.add(shrink);
			}
			else if(rand <= 0.7) {
				die = new Powerup(x, y, 30, 30, 2, "fail.jpg");
				powerups.add(die);
			}
			else {
				back = new Powerup(x, y, 40, 40, 2.5, "erik.jpg");
				powerups.add(back);
			}
		}
		//good powerup
		else if (drop >= 0.93) {
			//decide which powerup
			double rand = Math.random();
			if(rand < 0.3) {
				grow = new Powerup(x, y, 30, 30, 2, "grow.png");
				powerups.add(grow); 
			}
			else if (rand <= 0.6) {
				//extra life
				life = new Powerup(x, y, 30, 30, 2, "steve.jpg");
				powerups.add(life);
			} 
			else if (rand <= 0.9) {
				//why?
				invincible = new Powerup(x, y, 30, 30 , 3, "yrvine.jpg");
				powerups.add(invincible);
			}
			else if (rand > 0.9) {
				//win level automatically
				nextLev = new Powerup(x, y, 30, 30, 4, "swap.jpg");
				powerups.add(nextLev);
			}
		}
	}

	//LOAD A LEVEL
	private void loadLevel(int i) {
            blocks = L.getLevel(i);	
	}

	//check if player has completed level
	boolean levelStatus() {
            for (Block[] block : blocks) {
                for (Block block1 : block) {
                    if (block1 != null && block1.getHits() > 0) {
                        return false;
                    }
                }
            }
            return true;
	}

	//reset the the game if the user loses a life
	void reset() {
            //reset the ball
            ball = new Ball(295, 485, 15, 15, "ball.png");
            //center paddle
            paddle = new Paddle(225, 500, 150, 25, "bottom-glow.png");
            powerups.removeAll(powerups);
            thru = false;
            
            play = false;
	}
        
        void initGame(){
            
            reset();
            started = false;
            curLevel = 1;
            score = 0;
            end = false;

            xcur= 0; xprev = -1; xdprev = -2;
            ycur= 0; yprev = -1; ydprev = -2;
            lives = 3;  
            right = false;
            left = false;

             L = new Levels(600, 600);
             loadLevel(curLevel-1);
        }
        
        
        
	//advance player to next level
	void nextlevel() {
            curLevel++; 
            if (curLevel > L.howManyLevels()) {
		end(true);
            }
            try {
		Thread.sleep(100);
            } catch (InterruptedException e) {
            
            }
            loadLevel(curLevel-1);
            reset();
	}

	//END THE GAME	
	void end(boolean win) {
            thread= null; 
            end = true;
            mainFrame.setVisible(false);
            endFrame.setVisible(true);
                
            if (win) {
                score += 100 * lives; 
                endMessage.setText("Congrats you beat all " + curLevel + " levels with a " + "score of " + score);
            }
            else {
                endMessage.setText("You lost on level " + curLevel + "... Your Score Was " + score);
            }     
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paddle.draw(g, this);
		ball.draw(g, this);
            for (Block[] block : blocks) {
                    for (Block curBlock : block) {
                        if(curBlock != null) {
                            curBlock.draw(g, this);
                        }
                    }
            }
            powerups.forEach((powerup) -> {
                powerup.draw(g, this);
            });
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		//enter key
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
                    if(!started){
                        started = true;
                        play = true;
                        thread= new Thread(()->  {
                            while(!end) {
                                //update thread
                                update();
                                try {
                                    Thread.sleep(8);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            initGame();  
                        });
                        thread.start();
                    }
                    else{
                        play = true;
                    }	
		}

		//right arrow key movement
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < getWidth() - paddle.width && play) {
			//paddle.x+= 30;
                        right = true;
		}
		//left arrow key
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0 && play) {
			//paddle.x-= 30;
                        left = true;
		}
		//exit the game with escape
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) { 
			startFrame.setVisible(false);
			mainFrame.setVisible(false);
			endFrame.setVisible(false);
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
                if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
                    left = false;
                }
                if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
                    right = false;
                }

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}



	//For JUnit testing------------------------
	public int getLives() {
		return lives;
	}

	public Block[][] getBlocks() {
		return blocks;
	}

	public void setBlocks(Block[][] b) {
		blocks = b;
	}

	public boolean getThru() {
		return this.thru;
	}

	public Set<Powerup> getPowerups() {
		return this.powerups;
	}

	public Paddle getPaddle() {
		return this.paddle;
	}

	public Ball getBall() {
		return this.ball;
	}
	public int getCurLevel() {
		return this.curLevel;
	}
	
	public Powerup getLife() {
		return life;	
	}
	public Powerup getDie() {
		return die;
	}
	public Powerup getGrow() {
		return grow;
	}
	//-------------------------------------------
}
