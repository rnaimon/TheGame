import java.awt.Color;
import java.util.ArrayList;


/***
 * This is the Projectile class to control projectiles that come from items, like darts 
 * or plasma blasts. They all behave like Projectiles.
 * @author Rebecca Naimon
 *
 */
public class Projectile implements ProjectileInterface {
	
	
	private double top_x;
	private double top_y;
	private double width;
	private double height;
	private double speed_X;
	private double speed_Y;
	private Color color;
	
	private ArrayList<LineObject> outline;
	private ArrayList<Vertex> vertices;
	
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
	 * This is the constructor with variables for the Projectile class.
	 * @param x is the top left x-coordinate
	 * @param y is the top left y-coordinate
	 * @param w is the width of the projectile
	 * @param h is the height of the projectile
	 * @param sX is the speed constant in the x direction
	 * @param sY is the speed constant in the y direction
	 */
	public Projectile(double x, double y, double w, double h, double sX, double sY) {
		top_x = x;
		top_y = y;
		width = w;
		height = h;
		speed_X = sX;
		speed_Y = sY;
		
		LineObject top = new LineObject((int)x, (int)y, (int)(x+width), (int)y);
		LineObject sideL = new LineObject((int)x, (int)y, (int)x, (int)(y+height));
		LineObject bottom = new LineObject((int)x, (int)(y + height), (int)(x+width), (int)(y+height));
		LineObject sideR = new LineObject((int)(x+width), (int)y, (int)(x+width), (int)(y+height));
		/*
		outline.add(top);
		outline.add(sideR);
		outline.add(bottom);
		outline.add(sideL);
*/
		
		
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
		top_y=y;
		
	}
	
	/***
	 * This method sets the width of the projectile.
	 * @param w is the width of the projectile
	 */
	public void setWidth(double w) {
		width = w;
	}

	
	/***
	 * This method returns the width of the projectile.
	 * @return width of the projectile
	 */
	public double getWidth() {
		return width;
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
	
	/***
	 * This method causes the projectile to disappear once it hits something.
	 */
	public void selfDestruct() {
		speed_X = 0;
		speed_Y = 0;
		top_x = -100;
		top_y = -100;
		width = 0;
	}
	
	
}