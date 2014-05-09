import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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
	private double centx;
	private double centy;
	private Color color;
	private Item current_item;
	private boolean isGrounded;
	private char orientation;
	private ArrayList<Obstacles> levelPlatforms;
	private ArrayList<Obstacles> hiddenPlatforms;
	
	private BufferedImage bg;
	
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
		
		
		try {
			System.out.println("nice sphere");
			bg = ImageIO.read(new File("blue-sphere-sized.png"));

		}
		catch (IOException ex) {
			System.out.println("Nope. Player image doesn't work.");
		}
	
		
		
	}
	/**
	 * This method retrieves the X-Coordinate of the center of the circle shaped avatar.
	 */
	public double getCentX() {
		return centx;
	}
	/**
	 * This method retrieves the Y-Coordinate of the center of the circle shaped avatar.
	 */
	public double getCentY() {
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
	public void setCentX(double x)
	{
		centx=x;
		
	}
	/**
	 * Sets the Y-Coordinate of the center of the player to 'y'
	 */
	public void setCentY(double y) {
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
		
		
	//	g.fillOval((int)centx, (int)centy, (int)radius, (int)radius);
		
		if (bg != null) {
			//System.out.println("in here now BG player");
			g.drawImage(bg, (int)centx, (int)centy, null);
		}
		
		
		
		
		if(current_item!=null)
		{
			g.setColor(current_item.getColor());
			if(orientation=='r')
				g.fillRect((int)centx, (int)centy, 2, 2);
			else if(orientation=='l')
			{
				g.fillRect((int)(centx-2), (int)centy, 2, 2);
			}
		}
		
	}
	 
	/**
	 * Moves the player right or left. Is called if the player can move left or right and the left
	 * or right keys are pressed.
	 */
	public void doMoveH(boolean moveLeftOrRight){
		 
			if(moveLeftOrRight==true) 
			{
				ArrayList<LineObject> nearObstacles= new ArrayList<LineObject>();
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
					//for(int i=0;i<levelPlat)
				}
				
				boolean throughPlatform=false;
				double slope=0;
				double constant=0;
				boolean vertical=false;
				for(int i=0; i< nearObstacles.size(); i++)
				{
					if(nearObstacles.get(i).getOrientation()=='c')
					{
						if((getCentY() + radius - speed_y*2/3)<= (getCentX()*nearObstacles.get(i).getSlope()/100 + nearObstacles.get(i).getConstant()/100+3) 
								&& (getCentY() + radius + speed_y*2/3)>= (getCentX()*nearObstacles.get(i).getSlope()/100 + nearObstacles.get(i).getConstant()/100-3))
						{
							throughPlatform=true;
							slope= nearObstacles.get(i).getSlope();
							constant= nearObstacles.get(i).getConstant();
						}
					}
					else
					{
						if(getCentX()>nearObstacles.get(i).getV1().getXCoord())
						{
							if((getCentX() - radius - speed_x)<nearObstacles.get(i).getV1().getXCoord())
							{
								vertical=true;
							}
									
						}
					}
				}
				
				if(throughPlatform==false)
				{
					if(vertical!=true)
						setCentX((getCentX()-speed_x));
				}
				else
				{
					setCentX((getCentX()-speed_x));
					setCentY((getCentX()*slope/100 + constant/100 - getRadius()-3));
					
				}
				setOrientation('l');
			} 
			else 
			{
				ArrayList<LineObject> nearObstacles= new ArrayList<LineObject>();
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
				
				boolean throughPlatform=false;
				double slope=0;
				double constant=0;
				for(int i=0; i< nearObstacles.size(); i++)
				{
					
					if((getCentY() + radius - speed_y*2/3)<= (getCentX()*nearObstacles.get(i).getSlope()/100 + nearObstacles.get(i).getConstant()/100+3) 
							&& (getCentY() + radius + speed_y*2/3)>= (getCentX()*nearObstacles.get(i).getSlope()/100 + nearObstacles.get(i).getConstant()/100-3))
					{
						throughPlatform=true;
						slope= nearObstacles.get(i).getSlope();
						constant=nearObstacles.get(i).getConstant();
						break;
					}
				}
				
				if(throughPlatform==false)
					setCentX((getCentX()+speed_x));
				else
				{
					setCentX((getCentX()+ speed_x));
					setCentY((getCentX()*slope/100 + constant/100 - getRadius()-3));
					
				}
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
			 setCentY((getCentY() + speed_y)); //move down
		 
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
				if(getCentX()>= l.getV1().getXCoord() - 1 && getCentX()<= l.getV2().getXCoord() + 1)
				{
					nearObstacles.add(l);
				}
			}
		}
		
		boolean onPlatform=false;
		for(int i=0; i< nearObstacles.size(); i++)
		{
			
				if((getCentY() + radius - speed_y*2/3)<= (getCentX()*nearObstacles.get(i).getSlope()/100 + nearObstacles.get(i).getConstant()/100 + 3) 
						&& (getCentY() + radius + speed_y*2/3)>= (getCentX()*nearObstacles.get(i).getSlope()/100 + nearObstacles.get(i).getConstant()/100 -3))
								
				{
					onPlatform=true;
					
				}
				
				/**
				 * The following if-statement needs to be in terms of slope, not horizontal platforms.
				 Another way to fix it would just be to do the same thing for x as I did for y...
				 I'd appreciate if you not deal with this code below yet.*/
				/*
				if(!onPlatform) {
					
					//&& (getCentX() - radius/2 < nearObstacles.get(i).getV1().getXCoord()) && (getCentX() + radius/2 > nearObstacles.get(i).getV2().getXCoord()))
					if ((getCentX() - radius/2 > nearObstacles.get(i).getV1().getXCoord() && getCentY() + radius - 3 < nearObstacles.get(i).getV1().getYCoord() && !(getCentY() + radius - 3 < nearObstacles.get(i).getV1().getYCoord() - 5) || (getCentX() + radius/2 < nearObstacles.get(i).getV2().getXCoord() && getCentY() + radius - 3 < nearObstacles.get(i).getV2().getYCoord() && !(getCentY() + radius - 3 < nearObstacles.get(i).getV2().getYCoord() - 5) ))){
						onPlatform = true;
					}
				}
				*/
				
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
	
	/***
	 * This method determines whether the player is touching an obstacle so that
	 * the program can determine if the player can move through something or not.
	 * @return the boolean of whether the player is touching an obstacle
	 */
	/*public boolean touchingObstacle() {
		
		
		
		
	}*/

	

	
}