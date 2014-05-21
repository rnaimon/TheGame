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
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class GameMain extends Canvas implements Runnable, KeyListener, MouseListener 
{

	//entities created here
	private int[] keysDown=new int[0];

	//Player is the class that indicates a player, rather redundantly
	private Player player;
	
	

	
	// these are values that need initiating, and control gameplay
	//private int numEnemies;

	//ArrayList<Level> levels = new ArrayList();
	
	private double speedXPlayer;
	//for double buffered graphics
	 private int bufferWidth;
	 private int bufferHeight;
	 private Image bufferImage;
	 private Graphics bufferGraphics; 
	 private Object currentLevel;
	 private long lastDrawTime;
	 private boolean jump=false;
	 private int key;
	 private boolean function=false;
	 private boolean checkerJump;
	 private ArrayList<Object> levelList;
	// there are two overall states: playing, and done 
	public static final int STATE_PLAYING = 1; // state values
	public static final int STATE_DONE = 2;
	public static final int MENU = 0;

	private int gameState; // current state

	public static final Color BG_COL = new Color(100, 0, 0); // background color
	public static final Color WALL_COL = new Color(0, 80, 200); // walls

	public static final int WALL_SIZE = 3; // wall size
	private int timer=1;
	public static final Font SCORE_FONT = new Font("Lucida Console", Font.BOLD
			& Font.ITALIC, 30);
	
	private StartMenu menu; 

	
	
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
	 * Initializes game and sets up the Player object and the Level
	 */
	public void init() {
		player = new Player(30, 10, 0, new Color(0, 0, 200));
		speedXPlayer=Toolkit.getDefaultToolkit().getScreenSize().width*9/1600;

		levelList= new ArrayList<Object>();
		LevelFive lv5 = new LevelFive(player, getWidth(), getHeight());
		LevelFour lv4= new LevelFour(player, getWidth(), getHeight());
		LevelThree lv3= new LevelThree(player, getWidth(), getHeight());
		LevelTwo lv2= new LevelTwo(player, getWidth(), getHeight());
		LevelOne lv1=new LevelOne(player, getWidth(), getHeight());
		
		menu = new StartMenu(player, getWidth(), getHeight());
		
		levelList.add(lv1);
		levelList.add(lv5);
		levelList.add(lv2);
		levelList.add(lv4);
		levelList.add(lv3);
		if(levelList.get(0)!=null)
			currentLevel= (LevelOne)(levelList.get(0));
		start();

	}

	/**
	 * Starts or restarts game - sets up or resets positions and such. 
	 * 
	 * There is much more to be added, namely involving levels.
	 */
	public void start() {
		gameState = MENU;
		
		player.setSpeedX((int)(speedXPlayer));

	}

	/***
	 * double buffered update method
	 */
	public void update(Graphics g) {
		paint(g);
	}
	
	/**
	 * Updates the screen, including drawing the environment and calling the draw()
	 * methods for each sprite. Also deals with movement and jumping situations each
	 * time the board is drawn.
	 * 
	 * @param g is a graphics object
	 */
	public void updateFrame(Graphics2D g) {
		
		switch (gameState) 
		{
		case MENU:
			
			currentLevel = (Level)menu;
			drawBoard(g);
			if(currentLevel!=null)
			{
				if(((Level)(currentLevel)).checkComplete()) 
				{
					gameState = STATE_PLAYING;
					currentLevel = (LevelOne)(levelList.get(0));
				}
			}
				break;
		//when the game has begun, and isn't over
		case STATE_PLAYING:
				if(((Level)(currentLevel)).checkComplete()==true)
				{
					int now= ((Level)(currentLevel)).getLevelNumber();
					switch(now)
					{
					
						case 0: player= new Player(30, 10, 0, new Color(0, 0, 200));
								levelList.set(0, new LevelOne(player, getWidth(), getHeight()));
								currentLevel= levelList.get(0);
								player.setSpeedX((int)(speedXPlayer));
								break;
						case 1:
								player= new Player(30, 10, 0, new Color(0, 0, 200));
								levelList.set(1, new LevelFive(player, getWidth(), getHeight()));
								currentLevel= levelList.get(1);
								player.setSpeedX((int)(speedXPlayer));								break;
						case 2:
							player= new Player(30, 10, 0, new Color(0, 0, 200));
							levelList.set(2, new LevelTwo(player, getWidth(), getHeight()));
							currentLevel= levelList.get(2);
							player.setSpeedX((int)(speedXPlayer));							break;
							
						case 3:
							player= new Player(30, 10, 0, new Color(0, 0, 200));
							levelList.set(3, new LevelFour(player, getWidth(), getHeight()));
							currentLevel= levelList.get(3);
							player.setSpeedX((int)(speedXPlayer));
							break;
							
						case 4:
							currentLevel= levelList.get(3);
							player.setCentX(0);
							player.setCentY(0);
							player.setSpeedY(0);
							player.setSpeedX((int)(speedXPlayer));
							break;
							
						case 5:
							//currentLevel= levelList.get(5);
							player.setCentX(0);
							player.setCentY(0);
							player.setSpeedY(0);
							gameState= STATE_DONE;
							player.setSpeedX((int)(speedXPlayer));
							break;
							
						default:
									gameState= STATE_DONE;
									break;
					}
					
				}
				
				//dealing with player death
					if((player.getCentY() + player.getRadius()+ player.getSpeedY())>= getHeight())
					{
						int n= ((Level)currentLevel).getLevelNumber();
						((Level)currentLevel).setLevelNumber(n-1);
						((Level)currentLevel).setLevelComplete(true);
					}
					
					if (((Level)(currentLevel)).shouldReset() == true) {
						if(((Level)(currentLevel)).getLevelNumber()==1)
							currentLevel=new LevelOne(player, getWidth(), getHeight());
						else if(((Level)(currentLevel)).getLevelNumber()==2)
							currentLevel=new LevelTwo(player, getWidth(), getHeight());
						else if(((Level)(currentLevel)).getLevelNumber()==3)
							currentLevel=new LevelThree(player, getWidth(), getHeight());
						else if(((Level)(currentLevel)).getLevelNumber()==4) 
							currentLevel=new LevelFour(player, getWidth(), getHeight());
						else if(((Level)(currentLevel)).getLevelNumber()==5) 
							currentLevel=new LevelFive(player, getWidth(), getHeight());
						
						
						player.setCentX(0);
						player.setCentY(0);
						player.setSpeedY(0);
					}
					//jumping when it's ok to jump	 
					if (jump==true && player.getGrounded()==true)
					{
							
							player.setSpeedY(-Toolkit.getDefaultToolkit().getScreenSize().height*2/300);
							checkerJump=true;
					}
					else
					{
						
						if(player.getSpeedY()>0 && checkerJump==true)
						{
							checkerJump=false;
							jump=false;
						}
						if(player.getGrounded()==true)
						{
							player.setSpeedY(0);
						}
						else 
						{
							if(player.getSpeedY()<=15)
								player.setSpeedY(player.getSpeedY() + player.getAccel()*20/1000);
						
						}
					}
					
					player.doMoveV(false, 2, null);
					
					
					if (isAKeyDown(KeyEvent.VK_LEFT) && canMove(player, true))
						player.doMoveH(true);
					else if (isAKeyDown(KeyEvent.VK_RIGHT)
							&& canMove(player, false))
						player.doMoveH(false);
					
					if(function==true) 
					{
						((Level)(currentLevel)).function();
						function=false;
							
					}
						
					
						drawBoard(g);
						drawSprites(g);
				
			
				break;
				
				//if the game is over because the player won
		case STATE_DONE:
			Font f3 = new Font("Candara", Font.PLAIN, 40);
			g.setFont(f3);
			g.setColor(Color.white);
			g.drawString("You have escaped with your marble intact!", 100, getWidth()/3);
			break;

		}

	}
	
	
	//method to determine if the player can move left or right (limits of the screen)

	/***
	 * 
	 * @param p is the player
	 * @param moveLeftOrRight is a boolean to describe whether the player can move left
	 * or right
	 * @return the new moveLeftOrRight boolean
	 */
	public boolean canMove(Player p, boolean moveLeftOrRight) {
		if (moveLeftOrRight) {
			if ((p.getCentX()) <= WALL_SIZE + p.getSpeedX())
				return false;
			else
				return true;
		}
		if (p.getCentX() + p.getRadius() >= getWidth() - WALL_SIZE - p.getSpeedX())
			return false;
		else
			return true;

	}
	
	
	/***
	 * This paint method paints the graphics (it has a Graphics object g). Also
	 * includes some code for double buffering.
	 */
	public void paint(Graphics g){
		
        //    checks the buffersize with the current panelsize
        //    or initialises the image with the first paint
        if(bufferWidth!=getSize().width || 
          bufferHeight!=getSize().height || 
          bufferImage==null || bufferGraphics==null)
            resetBuffer();
        
        
        if(bufferGraphics!=null)
        {
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
	 * Characters to draw when we're playing. 
	 * 
	 * @param g
	 */
	private void drawSprites(Graphics2D g) {

		if (((Level)(currentLevel)).getLevelNumber() > 0)
			player.draw(g);
	}

	/**
	 * Draws the board background, and creates ArrayLists of the various obstacles and
	 * platforms in the game (and then draws them).
	 */
	private void drawBoard(Graphics2D g)
	{
		if(currentLevel!=null)
		{
			if (((Level)(currentLevel)).getLevelNumber() > 0) 
			{
				BufferedImage background = ((Level)(currentLevel)).getBackground();
				
				g.drawImage(background, 0, 0, null);
				
				ArrayList<Obstacles> thingsInLevel= ((Level)(currentLevel)).getObstacleList();
				player.setPlatforms(thingsInLevel);
		
	
			}
			((Level)(currentLevel)).draw(g);
		}
			
	}

	// records where the mouse clicked, for the menu level
	public void mouseClicked(MouseEvent e) {
		
		((StartMenu)(currentLevel)).setClicks(e.getX(), e.getY());
		//clickY = e.getY();
		
	}

	/*
	 * Necessary methods to use MouseListener
	 */
	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	// 
	/***
	 * Main method that creates the frame, sets the accessories at the top, and allows 
	 * the window to close
	 * @param game is the GameMain class
	 * @param width is the width of the frame
	 * @param height is the height of the frame
	 */
	public static void createGameFrame(GameMain game, int width, int height) {
		Frame myFrame = new Frame();
		myFrame.setSize(width, height); // frame size
		myFrame.setBackground(Color.black);
		// myFrame.setLayout(mgr);
		myFrame.setLayout(new BorderLayout());
		myFrame.add(game, BorderLayout.CENTER);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		
		game.addKeyListener(game);
		
		game.addMouseListener(game);
		
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

	//to be ignored
	public void keyTyped(KeyEvent arg0) {
		
	}
	

	private Thread thisThread;
	
	/**
	 * Creates a game panel, tests for jumping, starts the thread.
	 */
	public GameMain() {
		super();
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				key=(e.getKeyCode());
				if(key==KeyEvent.VK_UP)
				{
					
					jump=true;
				}
				if(key == KeyEvent.VK_Z)
				{
					function=true;
				}
				if(key == KeyEvent.VK_R)
				{
					int n= ((Level)currentLevel).getLevelNumber();
					((Level)currentLevel).setLevelNumber(n-1);
					((Level)currentLevel).setLevelComplete(true);
				}
			}//end KeyPressed
		});
		thisThread=new Thread(this); //create a thread for an object
		thisThread.start(); 
	}
	
	

	/**
	 * Runs and starts the thread so that the canvas can be repainted over and over,
	 * with the thread sleeping in between.
	 */
	public void run(){
		
		while(Thread.currentThread()== thisThread){ //while the thread is running
			repaint(); //redraw screen
			
			try {
				timer=timer+1;
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
}