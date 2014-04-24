/* Interface for enemies*/
//Rebecca Naimon

import java.awt.*;

public interface EnemyInterface {
	
/*	
 * variables for Enemy objects are all about top left coordinates, sizes,
 * and speed
 * 
	double top_x;
	double top_y;
	double width;
	double height;
	double speed_X;
	double speed_Y;
	
*/
	
	public double getCentX();
	public void setCentX(double x);
	
	public double getCentY();
	public void setCentY(double y);
	
	public double getSpeedX();
	public void setSpeedX(double x);
	
	public double getSpeedY();
	public void setSpeedY(double y);
	
	public void draw(Graphics2D g);
	
	
}