import java.awt.*;
import java.util.ArrayList;

/***
 * This is the Player class, which controls the player character and all of its aspects.
 * @author Michael Katz
 * @author Rebecca Naimon
 *
 */
public class Player implements PlayerInterface
{
	private double  speed_x;
	private double  speed_y;
	private double  acceleration=Toolkit.getDefaultToolkit().getScreenSize().height/100;
	private double radius;
	private int centx;
	private int centy;
	private Color color;
	private Item current_item;
	private boolean isGrounded;
	private char orientation;
	private ArrayList<Obstacles> levelPlatforms;
	private ArrayList<Obstacles> hiddenPlatforms;
	
	/***
	 * This is a constructor for Player without variables.
	 */
	public Player()
	{
		radius= .25;
		centx=0;
		centy=0;
	}
	
	/***
	 * This is the constructor that assumes the Player is a circle for the MVP.
	 * @param r is the radius
	 * @param x is the center x-coordinate
	 * @param y is the center y-coordinate
	 * @param c is the color
	 */
	public Player(double r, int x, int y, Color c)
	{
		radius=r;
		centx=x;
		centy=y;
		color=c;
		speed_x=5;
		speed_y=0;
		isGrounded=true;
		orientation='r';
	}
	/**
	 * This method retrieves the X-Coordinate of the center of the circle shaped avatar.
	 */
	public int getCentX() {
		return centx;
	}
	/**
	 * This method retrieves the Y-Coordinate of the center of the circle shaped avatar.
	 */
	public int getCentY() {
		return centy;
	}
	/**
	 * This method sets the list of platforms in the level for the player
	 * @param l is a list of platforms
	 */
	public void setPlatforms(ArrayList<Obstacles> l)
	{
		levelPlatforms=l;
	}

	/**
	 * Sets the X-Coordinate of the center of the player to 'x'
	 */
	public void setCentX(int x)
	{
		centx=x;
		
	}
	/**
	 * Sets the Y-Coordinate of the center of the player to 'y'
	 */
	public void setCentY(int y) {
		centy=y;
		
	}

	/**
	 * called after using held item to set current_item to null
	 */
	public void usedItem() {
		current_item=null;
		
	}
	
	/**
	 * Returns the color of the player
	 * @return color is the color of the player
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of the player to c
	 * @param c is the new color of the player
	 */
	public void setColor(Color c) {
		color=c;
		
	}
	/**
	 * Sets the new x_speed of the character
	 * @param x the speed at which the player can move horizontally
	 */
	public void setSpeedX(int x)
	{
		speed_x=x;
	}
	/**
	 * Returns the acceleration due to gravity of the player
	 * @return acceleration due to gravity
	 */
	public double getAccel()
	{
		return acceleration;
	}
	/**
	 * Sets the acceleration due to gravity to a
	 * @param a the new accelaeration due to gravtiy
	 */
	public void setAccel(double a)
	{
		acceleration=a;
	}

	/**
	 * Draws the player, a circle
	 */
	public void draw(Graphics2D g){
		g.setColor(color); //inside
		g.fillOval(centx, centy, (int)radius, (int)radius);
		if(current_item!=null)
		{
			g.setColor(current_item.getColor());
			if(orientation=='r')
				g.fillRect(centx, centy, 2, 2);
			else if(orientation=='l')
			{
				g.fillRect(centx-2, centy, 2, 2);
			}
		}
		
	}
	 
	/**
	 * Moves the player right or left. Is called if the player can move left or right and the left
	 * or right keys are pressed.
	 */
	public void doMoveH(boolean moveLeftOrRight){
		 
			if(moveLeftOrRight==true) 
			{ //move left
				setCentX((int)(getCentX()-speed_x));
				setOrientation('l');
			} 
			else 
			{
				setCentX((int)(getCentX()+speed_x));//move right
				setOrientation('r');
			}
		 
	}
	 public void setOrientation(char o)
	 {
		 orientation=o;
	 }
	 public void setHiddenPlatforms(ArrayList<Obstacles> h)
	 {
		 hiddenPlatforms=h;
	 }
	 public ArrayList<Obstacles> getHiddenPlatforms()
	 {
		 return hiddenPlatforms;
	 }
	 public char getOrientation()
	 {
		 return orientation;
	 }
	/***
	 * This method just makes the player move if the player can move
	 * @param moveUpOrDown is the variable to determine which vertical direction to move
	 * @param speed is used to create the physics of jumping or falling
	 */
	 public void doMoveV(boolean moveUpOrDown, int y, ArrayList<LineObject> nearObstacles)
	 {	
		 if(getGrounded()==false)
			 setCentY((int)(getCentY() + speed_y)); //move down
		 
	 }

	 /**
	  * This method sets the item the player is holding to i
	  * @param i the new item the player is holding
	  */
	 public void setItem(Item i) {
			current_item=i;
	}
	 
	/**
	 * Retrieves the current item the player is holding
	 */
	public Item getItem() {
		return current_item;
	}


	/**
	 * Retrieves the speed_x
	 */
	public double getSpeedX() 
	{
		return speed_x;
	}


	/**
	 * Retrieves the speed of the player in the vertical direction
	 */
	public double getSpeedY()
	{
		return speed_y;
	}
	/**
	 * Checks whether the player is within a speed distance of a platform or actually on a platform.
	 * @return
	 */
	public boolean getGrounded()
	{
		ArrayList<LineObject> nearObstacles= new ArrayList<LineObject>();
		for(int i=0; i< levelPlatforms.size(); i++)
		{
			LineObject l= levelPlatforms.get(i).getOutlines().get(0);
			if(getCentX()>= l.getV1().getXCoord() && getCentX()<= l.getV2().getXCoord())
			{
				nearObstacles.add(l);
			}
		}
		if(hiddenPlatforms!=null)
		{
			for(int i=0; i< hiddenPlatforms.size(); i++)
			{
				LineObject l= hiddenPlatforms.get(i).getOutlines().get(0);
				if(getCentX()>= l.getV1().getXCoord() && getCentX()<= l.getV2().getXCoord())
				{
					nearObstacles.add(l);
				}
			}
		}
		
		boolean onPlatform=false;
		for(int i=0; i< nearObstacles.size(); i++)
		{
			
				if((getCentY() + radius - speed_y*2/3)<= (getCentX()*nearObstacles.get(i).getSlope() + nearObstacles.get(i).getConstant()) 
						&& (getCentY() + radius + speed_y*2/3)>= (getCentX()*nearObstacles.get(i).getSlope() + nearObstacles.get(i).getConstant()))
				{
					onPlatform=true;
				}
		}
		return onPlatform;
	}
	/*
	public void setGrounded(boolean b)
	{
		isGrounded=b;
	}
	*/

	/**
	 * Returns the radius of the circle shaped player
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Sets the radius of the circle shaped player
	 */
	public void setRadius(double r) {
		radius=r;
		
	}
	/**
	 * Sets the speed_y to y, the speed the player is moving in the vertical direction
	 * @param y the new speed in the vertical direction
	 */
	public void setSpeedY(double y) {
		speed_y=y;
		
	}

	

	
}