import java.awt.*;

public class Player implements PlayerInterface
{
	double speed_x;
	double speed_y;
	private double radius;
	private int centx;
	private int centy;
	private Color color;
	private Item current_item;
	
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
	public double getSpeed() {
		return speed_x;
	}
	
	/**
	 * @return  Speed to move with each frame
	 */
	public void setSpeed(int speed) {
		this.speed_x = speed;
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
	 public void doMoveV(boolean moveUpOrDown, int speed_y){
		 
			if(moveUpOrDown==true) { //move up
				setCentY((int)(getCentY()-speed_y));
			} else {
				setCentY((int)(getCentY()+speed_y)); //move down
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

	@Override
	public double getSpeedY() {
		return speed_y;
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