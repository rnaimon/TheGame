import java.awt.Graphics2D;

public interface PlayerInterface{
	/*
	double top_x; 	// the x-coordinate of the top-left corner pixel of the avatar
	double top_y; 	// the y-coordinate of the top-left corner pixel of the avatar
	double width; 	// width of avater
	double height; 	// height of avatar
	Item current_item; //held item object
	double speed_x;
	double speed_y;
	
	public double getTopX();
	
	public double getTopY();
	*/
	
	public double getRadius();
	public void setRadius(double r);
	
	//Retrieves center of avatar
	public int getCentX();
	public int getCentY();
	
	//Sets center of avatar
	public void setCentX(int x);
	public void setCentY(int y);
	
	
	public Item getItem();
	
	public void doMoveH(boolean moveLeftOrRight);
	
	public void doMoveV(boolean moveUpOrDown);
	
	public double getSpeedX();
	
	public double getSpeedY();
	
	public void draw(Graphics2D g);
	
	
	
}