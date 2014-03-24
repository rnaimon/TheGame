/*This is the main file where the game comes together. The game is run
 * from this file. */
//Rebecca Naimon
// Michael Katz

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



/***
 * This is the GameMain class. It is a single class that runs the game.
 * @author Rebecca Naimon and Michael Katz
 *
 */
public class GameMain extends Canvas implements Runnable, KeyListener 
{

	//entities created here
	private int[] keysDown=new int[0];

	//Player is the class that indicates a player, rather redundantly
	private Player player;
	

	
	// these are values that need initiating, and control gameplay
	//private int numEnemies;

	ArrayList<Level> levels = new ArrayList();
	
	
	
	//for double buffered graphics
	 private int bufferWidth;
	 private int bufferHeight;
	 private Image bufferImage;
	 private Graphics bufferGraphics; 

	 private long lastDrawTime;
	
	// there are two overall states: playing, and done 
	public static final int STATE_PLAYING = 1; // state values
	public static final int STATE_DONE = 2;

	private int gameState; // current state

	public static final Color BG_COL = new Color(100, 0, 0); // background color
	public static final Color WALL_COL = new Color(0, 80, 200); // walls

	public static final int WALL_SIZE = 3; // wall size

	public static final Font SCORE_FONT = new Font("Lucida Console", Font.BOLD
			& Font.ITALIC, 30);

	// Loads background images (namely, the player image, for now at least)
	private static BufferedImage IMAGE_BG = null;
	{
		try {
			IMAGE_BG = ImageIO.read(new File("bluecircle.png")); // later it will not be a blue circle
		} catch (IOException ex) {

		}
	}

	/**
	 * Creates Frame and Panel, and initiates the game. It sets the size of the frame
	 * to the size of the computer screen.
	 */
	public static void main(String[] args) {

		GameMain game = new GameMain();
		
		int width = Toolkit.getDefaultToolkit().getScreenSize().width; 
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		createGameFrame(game, width, height); 
		game.init();
	}


	
	/**
	 * Test whether a key is currently being pressed down. 
	 * Multiple keys can be pressed simultaneously.
	 * @param keyToTest determines if this particular key is down
	 * @return true if this key is down, otherwise, returns false
	 */
	public boolean isAKeyDown(int keyToTest){
		for (int key : keysDown) {
			if (key==keyToTest) return true;
		}
		return false;
	}
	
	
	/**
	 * Triggered when a key is released (up)
	 * @param keyE returns which key was released.
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
	 * Initializes game and sets up the Player object
	 */
	public void init() {
		player = new Player(30, 300, 100, new Color(0, 0, 200));

		start();

	}

	/**
	 * Starts or restarts game - sets up or resets positions and such. 
	 * 
	 * There is much more to be added, namely involving levels.
	 */
	public void start() {
		gameState = STATE_PLAYING;
	
		player.setSpeed(5);
		
		// will eventually have an actual location relevant to the game
		player.setCentX((int) (getWidth() / 2 - player.getRadius() / 2)); 
	
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
		// if the game is happening, you can move and the game continues
		case STATE_PLAYING:
	//		if (bar.getValue() >= 0) {
			/*	 if(isAKeyDown(KeyEvent.VK_A) && canMove(player,true))
					 player.doMove(true);
				 else if(isAKeyDown(KeyEvent.VK_S) && canMove(player,false))
					 player.doMove(false);
*/
				if (isAKeyDown(KeyEvent.VK_UP) && canMoveV(player, true))
				{
					player.doMoveV(true, 5);
				}
				else if (isAKeyDown(KeyEvent.VK_DOWN) && canMoveV(player, false))
					{
						player.doMoveV(false, 5); // the second variable is the speed, which helps determine and then implement the jumping (or not)
					}
				if (isAKeyDown(KeyEvent.VK_LEFT) && canMove(player, true))
					player.doMoveH(true);
				else if (isAKeyDown(KeyEvent.VK_RIGHT)
						&& canMove(player, false))
					player.doMoveH(false);

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
			//g.setColor(Color.black);
			g.drawString("You have survived the game!", 200, 100);
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
	 * Tests whether a player can move up or down, to be eventually moved to the 
	 * Level class.
	 * 
	 * @param p is the player to test
	 * @param moveUpOrDown is whether we're trying to move up or down
	 */
	
	/*
	  public boolean canMove(PaddleDone p,boolean moveLeftOrRight){
	  if(moveLeftOrRight){ if(p.getTopX()<=WALL_SIZE+p.getSpeed()) return
	  false; else return true; }
	  if(p.getTopX()+p.getSizeX()>=getWidth()-WALL_SIZE-p.getSpeed())return
	  false; else return true;
	  
	  } public boolean canMoveV(PaddleDone p,boolean moveUpOrDown){
	  if(moveUpOrDown){ if(p.getTopY()<=WALL_SIZE+p.getSpeed()) return false;
	  else return true; }
	  if(p.getTopY()+p.getSizeY()>=getHeight()-WALL_SIZE-p.getSpeed())return
	  false; else return true;
	  
	  }
	 */
	
	//method to determine if the player can move left or right (limits of the screen)
	// will be moved to the Level class
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
	// will be moved to the Level class
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
	
	
	
	
	
	/***
	 * This paint method paints the graphics (it has a Graphics object g).
	 */
	public void paint(Graphics g){
		
        //    checks the buffersize with the current panelsize
        //    or initialises the image with the first paint
        if(bufferWidth!=getSize().width || 
          bufferHeight!=getSize().height || 
          bufferImage==null || bufferGraphics==null)
            resetBuffer();
        
        
        if(bufferGraphics!=null){
            //this clears the offscreen image, not the onscreen one
            bufferGraphics.clearRect(0,0,bufferWidth,bufferHeight);

            //calls the paintbuffer method with 
            //the offscreen graphics as a param
            updateFrame((Graphics2D)bufferGraphics);

            //we finally paint the offscreen image onto the onscreen image
            
            g.drawImage(bufferImage,0,0,this);
        }

        
		lastDrawTime=System.currentTimeMillis();
    }
	

    /** 
     * Reinitialize double buffered graphics when canvas changes size
     */
    private void resetBuffer(){
        // always keep track of the image size
        bufferWidth=getSize().width;
        bufferHeight=getSize().height;

        //    clean up the previous image
        
        if(bufferGraphics!=null){
            bufferGraphics.dispose();
            bufferGraphics=null;
        }
        if(bufferImage!=null){
            bufferImage.flush();
            bufferImage=null;
        }
        System.gc();

        //    create the new image with the size of the panel
        bufferImage=createImage(bufferWidth,bufferHeight);
        bufferGraphics=bufferImage.getGraphics();
    }
	
	
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