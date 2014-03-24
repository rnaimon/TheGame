import java.awt.*;
import java.util.ArrayList;

public class Player implements PlayerInterface
{
	private double  speed_x;
	private double  speed_y;
	private double  deltaspeed_y=0;
	private double radius;
	private int centx;
	private int centy;
	private Color color;
	private Item current_item;
	private boolean isGrounded;
	
	public Player()
	{
		radius= .25;
		centx=0;
		centy=0;
	}
	
	public Player(double r, int x, int y, Color c)
	{
		radius=r;
		centx=x;
		centy=y;
		color=c;
		speed_x=5;
		speed_y=5;
		isGrounded=true;
	}
	
	public int getCentX() {
		return centx;
	}

	public int getCentY() {
		return centy;
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

	
	public void draw(Graphics2D g){
		g.setColor(color); //inside
		//draw the bat at xpos, ypos
		//g.fillRect(getTopX(), getTopY(), getSizeX(), getSizeY());
		//g.drawImage(BAT,getTopX(),getTopY(),null);
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
	 public void doMoveV(boolean moveUpOrDown, int y, ArrayList<LineObject> nearObstacles){
		 
			if(moveUpOrDown==true) //move up
			{ 
				deltaspeed_y=y;
				setCentY((int)(getCentY() + speed_y - deltaspeed_y));
			} 
			else 
			{
				boolean notOnAir=false;
				for(int i=0; i<nearObstacles.size(); i++)
				{
					if(((int)(getCentY() + getRadius()))<=(int)(nearObstacles.get(i).getV1().getYCoord()) && ((int)(getCentY() + getRadius() + speed_y - deltaspeed_y))>=(int)(nearObstacles.get(i).getV1().getYCoord()))
					{
						notOnAir=true;
						setGrounded(true);
						deltaspeed_y=0;
						break;
					}
				}
				if(notOnAir==false)
				{
					setGrounded(false);
					setCentY((int)(getCentY() + speed_y - deltaspeed_y)); //move down
				}
			}
		 
	}

	 public void setItem(Item i) {
			current_item=i;
	}
	 
	public Item getItem() {
		return current_item;
	}


	public double getSpeedX() {
		return speed_x;
	}


	public double getCurrentSpeedY() {
		return speed_y + deltaspeed_y;
	}
	public double getSpeedY()
	{
		return speed_y;
	}
	
	public boolean getGrounded(){
		return isGrounded;
	}
	public void setGrounded(boolean b)
	{
		isGrounded=b;
	}

	@Override
	public double getRadius() {
		return radius;
	}

	@Override
	public void setRadius(double r) {
		radius=r;
		
	}

	
}