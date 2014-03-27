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
	private double  acceleration=9.8;
	private double radius;
	private int centx;
	private int centy;
	private Color color;
	private Item current_item;
	private boolean isGrounded;
	private ArrayList<Obstacles> levelPlatforms;
	
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
	}
	
	public int getCentX() {
		return centx;
	}

	public int getCentY() {
		return centy;
	}
	public void setPlatforms(ArrayList<Obstacles> l)
	{
		levelPlatforms=l;
	}

	public void setCentX(int x) {
		centx=x;
		
	}

	public void setCentY(int y) {
		centy=y;
		
	}

	/**
	 * called after using held item to set current_item to null
	 */
	public void usedItem() {
		current_item=null;
		
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		color=c;
		
	}
	public void setSpeedX(int x)
	{
		speed_x=x;
	}
	
	public double getAccel()
	{
		return acceleration;
	}
	
	public void setAccel(double a)
	{
		acceleration=a;
	}

	
	public void draw(Graphics2D g){
		g.setColor(color); //inside
		g.fillOval(centx, centy, (int)radius, (int)radius);
		if(current_item!=null)
		{
			g.setColor(current_item.getColor());
			g.fillRect(centx, centy, 2, 2);
		}
		
	}
	 
	public void doMoveH(boolean moveLeftOrRight){
		 
			if(moveLeftOrRight==true) { //move left
				setCentX((int)(getCentX()-speed_x));
			} else {
				setCentX((int)(getCentX()+speed_x)); //move right
			}
		 
	}
	 
	/***
	 * This method deals with vertical movement, which includes climbing up and down
	 * ladders, or with jumping and falling, which are more often used.
	 * @param moveUpOrDown is the variable to determine which vertical direction to move
	 * @param speed is used to create the physics of jumping or falling
	 */
	 public void doMoveV(boolean moveUpOrDown, int y, ArrayList<LineObject> nearObstacles)
	 {	
		 if(getGrounded()==false)
			 setCentY((int)(getCentY() + speed_y)); //move down
		 
	 }

	 public void setItem(Item i) {
			current_item=i;
	}
	 
	public Item getItem() {
		return current_item;
	}


	public double getSpeedX() 
	{
		return speed_x;
	}


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
			if(l.getOrientation()=='h')
			{
				if(getCentX()>= l.getV1().getXCoord() && getCentX()<= l.getV2().getXCoord())
				{
					nearObstacles.add(l);
				}
			}
		}
		boolean onPlatform=false;
		for(int i=0; i< nearObstacles.size(); i++)
		{
				if((getCentY() + radius- speed_y*2/3)<= nearObstacles.get(i).getV1().getYCoord() && (getCentY() + radius + speed_y*2/3)>= nearObstacles.get(i).getV1().getYCoord())
				{
					onPlatform=true;
					break;
				}
			
		}
		return onPlatform;
	}
	public void setGrounded(boolean b)
	{
		isGrounded=b;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double r) {
		radius=r;
		
	}
	public void setSpeedY(double y) {
		speed_y=y;
		
	}

	

	
}