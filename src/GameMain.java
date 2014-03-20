/*This is the main file where the game comes together. */
//Rebecca Naimon

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 
 @author Michael Katz
 @author Rebecca Naimon
 * 
 */



// main class of the game in which most things happen
public class GameMain extends Canvas implements Runnable, KeyListener {

	//entities created here
	private int[] keysDown=new int[0];

	//Player is the class that indicates a player, rather redundantly
	private Player player;
	
	
	//for double buffered graphics
	 private int bufferWidth;
	 private int bufferHeight;
	 private Image bufferImage;
	 private Graphics bufferGraphics; 

	 private long lastDrawTime;
	
	
	// these are values that need initiating, and control gameplay
	//private int numEnemies;

	// ,p1;
	
	// these variables deal with the timer on the screen, and the length of the game
	private static JLabel timer;
	private static int gameLength = 3600;
	
	// there are two states: playing, and done 
	public static final int STATE_PLAYING = 1; // state values
	public static final int STATE_DONE = 2;

	private int gameState; // current state

	public static final Color BG_COL = new Color(100, 0, 0); // background
	public static final Color WALL_COL = new Color(0, 80, 200); // walls

	public static final int WALL_SIZE = 3; // wall size

	public static final Font SCORE_FONT = new Font("Lucida Console", Font.BOLD
			& Font.ITALIC, 30);

	// Load background images (namely, the player image)
	private static BufferedImage IMAGE_BG = null;
	{
		try {
			IMAGE_BG = ImageIO.read(new File("bluecircle.png"));
		} catch (IOException ex) {

		}
	}

	/**
	 * Create Frame and Panel, and initiate the game
	 */
	public static void main(String[] args) {

		GameMain game = new GameMain();
		int width = Toolkit.getDefaultToolkit().getScreenSize().width; 
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		createGameFrame(game, width, height); //to be changed to be entire screen
		game.init();
	}


	
	/**
	 * Test whether a key is currently being pressed down 
	 * Multiple keys can be pressed simultaneously
	 * @param keyToTest determine if this key is down (see VK_ constants in java.awt.event.KeyEvent)
	 * @return true if this keys is down otherwise false
	 */
	public boolean isAKeyDown(int keyToTest){
		for (int key : keysDown) {
			if (key==keyToTest) return true;
		}
		return false;
	}
	
	/**
	 * Triggered when a key is released (up)
	 * @param keyE which key
	 */
		public void keyReleased(KeyEvent keyE) {
			// Remove key released
			int[] newDown=new int[keysDown.length-1];
			int atLoc=0;
		 	for (int i = 0; i < keysDown.length; i++) {
				if(keysDown[i]!=keyE.getKeyCode()){
					newDown[atLoc]=keysDown[i];
					atLoc++;
				}
			}
		 	keysDown=newDown;
		}

		/**
		 * Triggered when a key is pressed (up)
		 * @param keyE which key
		 */

		public void keyPressed(KeyEvent keyE) {
			boolean add=true;
			int[] newDown=new int[keysDown.length+1];
		 	for (int i = 0; i < keysDown.length; i++) {
		 		if(keysDown[i]==keyE.getKeyCode())	{add=false;break;}
		 		newDown[i]=keysDown[i];
			}
		 
		 	if(add){
		 		
			 	newDown[keysDown.length]=keyE.getKeyCode();
			 	keysDown=newDown;
		 	}
		}
	
	
	
	/**
	 * Initialize game - insert code to set up (not create!) entities
	 */
	public void init() {
		// p1=new PaddleDone(20,100);
		// player=new PaddleDone(20,100);
		player = new Player(30, 300, 100, new Color(0, 0, 200));

		start();

	}

	/**
	 * (re)start game - reset positions, scores etc
	 */
	public void start() {
		gameState = STATE_PLAYING;
	
		player.setSpeed(5);
		player.setCentX((int) (getWidth() / 2 - player.getRadius() / 2)); // reset
																			// positions
		player.setCentY(getHeight() - 40);

	}

	/**
	 * Updates the screen, including drawing the environment and calling the draw()
	 * methods for each sprite
	 * 
	 * @param g is a graphics object
	 */
	public void updateFrame(Graphics2D g) {

		switch (gameState) {
		// if the game is happening, you can move and spheres can move, and the game's mechanics happen
		case STATE_PLAYING:
	//		if (bar.getValue() >= 0) {
			/*	 if(isAKeyDown(KeyEvent.VK_A) && canMove(player,true))
					 player.doMove(true);
				 else if(isAKeyDown(KeyEvent.VK_S) && canMove(player,false))
					 player.doMove(false);
*/
				if (isAKeyDown(KeyEvent.VK_UP) && canMoveV(player, true))
					player.doMoveV(true, 5);
				else if (isAKeyDown(KeyEvent.VK_DOWN)
						&& canMoveV(player, false))
					player.doMoveV(false, 5); // the second variable is the speed, which helps determine and then implement the jumping (or not)

				if (isAKeyDown(KeyEvent.VK_LEFT) && canMove(player, true))
					player.doMoveH(true);
				else if (isAKeyDown(KeyEvent.VK_RIGHT)
						&& canMove(player, false))
					player.doMoveH(false);

				int newEnemies = 0;
				int newHealth = 0;
				
				/*
				while (this.getChecker() != this.getKing()) {
					int p = this.getChecker();
					if (p % 61 == 0 && numEnemies <= 40) {
						newEnemies++;
					}
					if (p % 50 == 0 && numHealth <= 20)
						newHealth++;
					this.setChecker(this.getChecker() + 1);
				}
*/
				// createHealth(newHealth);
				/*
				
				findAndRemove(objects, player, bar);
				for (int i = 0; i < objects.size(); i++) {
					int y = objects.get(i).getY();
					objects.get(i).setY(y + objects.get(i).getSpeed());
				}
				createEnemies(newEnemies);
				createHealth(newHealth);
				*/
				
				// sets the time text to display
		//		timer.setText("Timer: " + (gameLength - this.getTimer()));
				
				// lowers health and increases difficulty
				
				/*
				if (this.getTimer() % 90 == 0)
					bar.setValue(bar.getValue() - 10);
				if (this.getTimer() % 901 == 0) {
					evilSpeed++;
					healthSpeed++;
					constant = evilSpeed - 4;
				}
				if (this.getTimer() % gameLength == 0) {
					gameState = STATE_DONE;
				}
*/
				drawBoard(g);
				drawSprites(g);
				// System.out.println(bar.getValue());
	//		} 
			// in the case that the game is over and you died
//			else {

//				g.setColor(Color.white);
//				g.fillRect(0, 0, getWidth(), getHeight());
//				g.setColor(Color.black);
//				g.drawString("You have lost all of your health and died.", 200, 100);
			
				
//			}
			break;
			//if the game is over because the player won
		case STATE_DONE:
			g.setColor(Color.black);
	//		g.drawString("You have survived the game!", 200, 100);
			/*
			g.drawString("Play again? Press Y or N", 200, 100);
			// System.out.println("here");
			
			// here we check for keys y or n
			if (playAgain == 'Y') {
				start();
			} else if (playAgain == 'N') {

			}
			*/
			break;

		}

	}
	
	
//this method creates the enemy spheres and sets their speeds and red color
	/*public void createEnemies(int n) {
		for (int i = 0; i < n; i++) {
			int x = (int) (Math.random() * getWidth());
			int r = 10;
			Sphere newest = new Sphere(x, r, r, -20, (int) (Math.random()
					* evilSpeed + constant), new Color(250, 0, 0));
			objects.add(newest);
			numEnemies++;

		}
	}
*/
	//this method creates the friendly spheres and sets their speeds and green color
/*	public void createHealth(int n) {
		for (int i = 0; i < n; i++) {
			int x = (int) (Math.random() * getWidth());
			int r = 10;
			Sphere newest = new Sphere(x, r, r, 10, (int) (Math.random()
					* healthSpeed + constant), new Color(0, 250, 0));
			objects.add(newest);

		}
	}
*/
	/**
	 * Test whether a player can move up or down
	 * 
	 * @param p
	 *            player to test
	 * @param moveUpOrDown
	 *            trying to move up or down
	 */
	/*
	 * public boolean canMove(PaddleDone p,boolean moveLeftOrRight){
	 * if(moveLeftOrRight){ if(p.getTopX()<=WALL_SIZE+p.getSpeed()) return
	 * false; else return true; }
	 * if(p.getTopX()+p.getSizeX()>=getWidth()-WALL_SIZE-p.getSpeed())return
	 * false; else return true;
	 * 
	 * } public boolean canMoveV(PaddleDone p,boolean moveUpOrDown){
	 * if(moveUpOrDown){ if(p.getTopY()<=WALL_SIZE+p.getSpeed()) return false;
	 * else return true; }
	 * if(p.getTopY()+p.getSizeY()>=getHeight()-WALL_SIZE-p.getSpeed())return
	 * false; else return true;
	 * 
	 * }
	 */
	
	//method to determine if the player can move left or right (limits of the screen)
	public boolean canMove(Player p, boolean moveLeftOrRight) {
		if (moveLeftOrRight) {
			if ((p.getCentX()) <= WALL_SIZE + p.getSpeed())
				return false;
			else
				return true;
		}
		if (p.getCentX() + p.getRadius() >= getWidth() - WALL_SIZE
				- p.getSpeed())
			return false;
		else
			return true;

	}

	// method to determine if the player can move up or down (limits of the screen)
	public boolean canMoveV(Player p, boolean moveUpOrDown) {
		if (moveUpOrDown) {
			if ((p.getCentY() - p.getRadius()) <= getHeight() / 2
					+ p.getSpeed()) {
				return false;
			} else
				return true;
		}
		if ((p.getCentY() + p.getRadius()) >= getHeight() - WALL_SIZE
				- p.getSpeed())
			return false;
		else
			return true;

	}

	// this method detects whether a sphere has touched the player circle, determines its enemy status, and carries out the relevant action
/*	public void findAndRemove(ArrayList<Sphere> objects, Avatar player,
			HealthBar bar) {
		for (int i = 0; i < objects.size(); i++) {
			Sphere s = objects.get(i);
			double distance = Math.sqrt((player.getCentX() - s.getX())
					* (player.getCentX() - s.getX())
					+ (player.getCentY() - s.getY())
					* (player.getCentY() - s.getY()));
			if (distance < (player.getRadius() - s.getRadius())) {
				bar.changeBar(s.getStatus());
				if (s.getStatus() <= -1) {
					numEnemies--;
				} else {
					numHealth--;
				}
				objects.remove(i);
				i--;
			} else if ((s.getY() + s.getRadius() / 2) >= getHeight()) {
				if (s.getStatus() <= -1) {
					numEnemies--;
				} else {
					numHealth--;
				}
				objects.remove(i);
				i--;
			}
		}
	}
*/
	/**
	 * Stuff to draw when we're playing
	 * 
	 * @param g
	 */
	private void drawSprites(Graphics2D g) {

		// p1.draw(g);
		player.draw(g);
/*		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).draw(g);
		}
		*/
		// player.draw(g);
	}

	/**
	 * Draw the board background
	 */
	private void drawBoard(Graphics2D g) {
		g.setColor(BG_COL);
		g.fillRect(0, 0, getWidth(), getHeight());
		// g.drawImage(IMAGE_BG,0,0,null);
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		// draw top and bottom walls
		g.setColor(WALL_COL);
		g.fillRect(0, 0, getWidth(), WALL_SIZE);
		g.fillRect(0, getHeight() - WALL_SIZE, getWidth(), WALL_SIZE);

	}

	// main method that creates the frame, sets the accessories at the top, and allows the window to close
	public static void createGameFrame(GameMain game, int width, int height) {
		Frame myFrame = new Frame();

		myFrame.setSize(width, height); // frame size
		myFrame.setBackground(Color.white);
		// myFrame.setLayout(mgr);
		myFrame.setLayout(new BorderLayout());
		myFrame.add(game, BorderLayout.CENTER);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
	//	HealthBarCanvas h = new HealthBarCanvas(bar);
//		h.setBounds(0, 0, 300, 20);

	//	topPanel.add(h, BorderLayout.WEST);
	
	//	topPanel.add(b, BorderLayout.CENTER);
		myFrame.add(topPanel, BorderLayout.NORTH);

		
		timer = new JLabel("                                              ");
		topPanel.add(timer, BorderLayout.EAST);
	//	JLabel time = new JLabel(this.getTimer());
		
		game.addKeyListener(game);
		// Make sure program ends when window is closed
		WindowAdapter d = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}

		};

		myFrame.addWindowListener(d);
		myFrame.setVisible(true); // see frame
		game.requestFocus(); // make sure the game is selected

	}

	//to be ignored for now
	public void keyTyped(KeyEvent arg0) {
		
	}
	

	private Thread thisThread;
	
	/**
	 * Create a game panel
	 */
	public GameMain() {
		super();
		thisThread=new Thread(this); //create a thread for an object
		thisThread.start(); 
	}
	
	

	/**
	 * Start the thread
	 */
	public void run(){
		
		while(Thread.currentThread()== thisThread){ //while the thread is running
			repaint(); //redraw screen
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}