/* Interface for enemies*/
//Rebecca Naimon

import java.awt.*;

public interface EnemyInterface {
	
/*	
 * variables for Enemy objects
 * 
	double top_x;
	double top_y;
	double width;
	double height;
	double speed_X;
	double speed_Y;
	
*/
	
	public double getTopX();
	public void setTopX();
	
	public double getTopY();
	public void setTopY();
	
	public double getSpeedX();
	public void setSpeedX();
	
	public double getSpeedY();
	public void setSpeedY();
	
	public void draw(Graphics2D g);
	
	
}