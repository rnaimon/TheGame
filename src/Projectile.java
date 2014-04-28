import java.awt.Color;
import java.awt.Graphics2D;
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
	private char orientation;
	
	private boolean isRectangle;
	private double radius;
	private double centx;
	private double centy;
	
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
		isRectangle = true;
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
	public Projectile(double x, double y, double w, double h, double sX, double sY, char o, Boolean r) {
		top_x = x;
		top_y = y;
		width = w;
		height = h;
		speed_X = sX;
		speed_Y = sY;
		orientation=o;
		isRectangle = r;
		
		if (isRectangle == false) {
			radius = 5;
			centx = x;
			centy = y;
			
		}
		else {
			LineObject top = new LineObject((int)x, (int)y, (int)(x+width), (int)y);
			LineObject sideL = new LineObject((int)x, (int)y, (int)x, (int)(y+height));
			LineObject bottom = new LineObject((int)x, (int)(y + height), (int)(x+width), (int)(y+height));
			LineObject sideR = new LineObject((int)(x+width), (int)y, (int)(x+width), (int)(y+height));
		}
		
	}
	public char getOrientation()
	{
		return orientation;
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
	
	/***
	 * This method returns the height of the projectile.
	 * @return the height variable
	 */
	public double getHeight() {
		return height;
	}

	/***
	 * This method returns the radius of the projectile if it's a circle.
	 * @return the radius variable
	 */
	public double getRadius() {
		return radius;
	}
	
	/***
	 * This method returns the center x-coord if the projectile is a circle.
	 * @return the center x-coordinate
	 */
	public double getCentX() {
		return centx;
	}
	
	/***
	 * This method returns the center y-coord if the projectile is a circle.
	 * @return the center y-coordinate
	 */
	public double getCentY() {
		return centy;
	}
	
	/***
	 * This method sets the center x-coord if the projectile is a circle.
	 */
	public void setCentX(double x) {
		centx = x;
	}
	
	/***
	 * This method sets the center y-coord if the projectile is a circle.
	 */
	public void setCentY(double y) {
		centy = y;
	}
	
	
	
	public void draw(Graphics2D g)
	{
		if (isRectangle)
			g.fillRect((int)getTopX(), (int)getTopY(), (int)width, (int)height);
		else
			g.fillOval((int)centx, (int)centy, (int)radius, (int)radius);
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