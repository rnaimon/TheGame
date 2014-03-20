import java.awt.Color;

//Projectile class to control projectiles that come from items
//Rebecca Naimon

public class Projectile implements ProjectileInterface {
	
	
	private double top_x;
	private double top_y;
	private double width;
	private double height;
	private double speed_X;
	private double speed_Y;
	private Color color;
	
	/***
	 * This is the constructor without passed-in variables.
	 */
	public Projectile() {
		top_x = 0;
		top_y = 0;
		width = 1;
		height = 1;
		speed_X = 5;
		speed_Y = 5;
		color=new Color(1f, 0f, 0f);
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
		top_x=x;
	}

	/***
	 * This method sets the top left x-coordinate of the projectile.
	 * @param y is an y-coordinate for the top left 
	 */
	public void setTopY(double y) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method returns the component of the speed of the projectile 
	 * in the x-direction
	 */
	public double getSpeedX() {
		return speed_X;
	}
	
	/**
	 * This method returns the component of the speed of the projectile 
	 * in the y-direction
	 */
	public double getSpeedY() {
		return speed_Y;
	}

	/**
	 * This method sets the x-component of the speed of the projectile
	 * @param x is the x-component of the speed
	 */
	public void setSpeedX(double x) {
		speed_X=x;
		
	}
	
	/**
	 * This method sets the y-component of the speed of the projectile
	 * @param y is the y-component of the speed
	 */
	public void setSpeedY(double y) {
		speed_Y=y;
		
	}

	/**
	 * This method returns the color of the projectile
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * This method sets the color of the projectile
	 * @param c is the new color of the projectile
	 */
	public void setColor(Color c) {
		color=c;
		
	}
	
	
	
}