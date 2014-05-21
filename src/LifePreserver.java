import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;



/***
 * This item class is used in the LEvelFive drowning level in order to avoid not drowning. It floats
 * on top of the water.
 * @author Rebecca Naimon
 *
 */
public class LifePreserver extends Item { //Obstacles {
	
	private int locX;
	private int locY;
	
	private BufferedImage bg;
	
	private double speedX;
	private double speedY;
	boolean left;
	
	
	public LifePreserver(int x, int y, double sX) {
		//super();
		
		locX = x;
		locY = y;
		
		speedX = sX;
		//speedY = sY;
		
		left = true;
		
		try {
			bg = ImageIO.read(new File("life-preserver.png"));

		}
		catch (IOException ex) {
			System.out.println("Nope.");
		}
		
	}
	
	/***
	 * This method returns the center x-coordinate of the life preserver.
	 */
	public double getX() {
		return locX;
	}
	
	/***
	 * This method sets the top left x-coordinate of the life preserver.
	 * @param x is the top left x-coordinate of the life preserver.
	 */
	public void setX(int x) {
		locX = x;
	}
	
	/***
	 * This method returns the top left y-coordinate of the life preserver.
	 */
	public double getY() {
		return locY;
		
	}
	
	/***
	 * This method sets the top left y-coordinate of the life preserver.
	 * @param y is the top left y-coordinate of the life preserver.
	 */
	public void setY(int y) {
		locY = y;
		
	}
	
	/***
	 * This method returns the component of the speed in the x direction
	 * of the life preserver.
	 */
	public double getSpeedX() {
		
		return speedX;
	}
	
	/***
	 * This method sets the component of the speed in the x direction
	 * of the life preserver object.
	 * @param x is the component of the speed in the x direction.
	 */
	public void setSpeedX(double x) {
		
		speedX = x;
	}
	
	/***
	 * This method returns the component of the speed in the y direction
	 * of the life preserver object.
	 */
	public double getSpeedY() {
		return speedY;
		
	}
	
	/***
	 * This method sets the component of the speed in the y direction
	 * of the life preserver object. 
	 * @param y is the component of the speed in the y direction
	 */
	public void setSpeedY(double y) {
		speedY = y;
		
	}
	
	
	/***
	 * Gets the image of the life preserver.
	 * @return the .png image of the life preserver
	 */
	public BufferedImage getImage() {
		return bg;
	}
	
	
	/***
	 * This method draws the life preserver object on the canvas. Currently, it's an oval, and should 
	 * probably be a circle.
	 */
	public void draw(Graphics2D g) {
		

		
		

		if (bg != null) {
			g.drawImage(bg, locX, (int)locY, null);
		}
		

	}
	
	
	/***
	 * This method causes the life preserver to move in order to help the player.
	 */
	public void doMove(int heightWater) {
		

		
		int amountH = 1;
				
			//	(int)(Math.random()*(5));
	
		
		if (locX < 250) 
			left = false;
		if(locX > 1000)
			left = true;
		
		if (left == false)
			amountH = 0 - amountH;
		

		
		
		int amountY = heightWater - 40;

		setX((int)(getX()-(speedX*amountH)));
		setY(amountY);
		
		
	}
	
	
	
	public boolean playerOnLP(Player p) {
		int pLocX = (int)p.getCentX();
		int pLocY = (int)p.getCentY();
		
		//Working on this
		
		return true;
	}
	
}