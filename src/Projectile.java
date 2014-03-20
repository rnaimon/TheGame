//Projectile class to control projectiles that come from items
//Rebecca Naimon

public class Projectile implements ProjectileInterface {
	
	
	private double top_x;
	private double top_y;
	private double width;
	private double height;
	private double speed_X;
	private double speed_Y;
	
	/***
	 * This is the constructor without passed-in variables.
	 * @Rebecca
	 */
	public Projectile() {
		top_x = 0;
		top_y = 0;
		width = 1;
		height = 1;
		speed_X = 5;
		speed_Y = 5;
	}
	
	/***
	 * This is the constructor with variables for the projectile class.
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param sX
	 * @param sY
	 */
	public Projectile(double x, double y, double w, double h, double sX, double sY) {
		top_x = x;
		top_y = y;
		width = w;
		height = h;
		speed_X = sX;
		speed_Y = sY;
	}
	
	/***
	 * This method returns the top left x-coordinate.
	 */
	public double getTopX() {
		return top_x;
	}
	
	
	/***
	 * This method returns the top left y-coordinate.
	 */
	public double getTopY() {
		return top_y;
	}
	
	
	/***
	 * This method sets the top left x-coordinate of the projectile.
	 * @param x is an x-coordinate for the top left 
	 */
	public void setTopX(double x) {
		
	}

	public void setTopX() {
		// TODO Auto-generated method stub
		
	}

	public void setTopY() {
		// TODO Auto-generated method stub
		
	}

	public double getSpeedX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getSpeedY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setSpeedX() {
		// TODO Auto-generated method stub
		
	}

	public void setSpeedY() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}