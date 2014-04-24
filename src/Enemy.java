import java.awt.*;

// Rebecca Naimon

/* This class, Enemy, is the object class for the generic enemies that
 * attack the player, like bugs or anti-virus security. They follow the 
 * same pattern, and so are the same class. */

public class Enemy implements EnemyInterface {
	
	
	/* The following are the variables for the Enemy class.*/
	
	private double centx;
	private double centy;
	
	private double radius;
	
	private double speed_x;
	private double speed_y;
	
	private Color color = new Color(255, 0, 0);
	
	
	private boolean isAlive = true;
	
	public Enemy(double r, int x, int y)
	{
		radius=r;
		centx=x;
		centy=y;
		speed_x=4;
		speed_y=4;
	}
	
	/***
	 * This method returns the boolean variable of whether the enemy is alive or not.
	 * @return the boolean of whether the enemy is alive or not
	 */
	public boolean getAlive() {
		return isAlive;
	}
	
	/***
	 * This method returns the center x-coordinate of the Enemy.
	 */
	public double getCentX() {
		return centx;
	}
	
	/***
	 * This method sets the top left x-coordinate of the Enemy.
	 * @param x is the top left x-coordinate of the Enemy.
	 */
	public void setCentX(double x) {
		centx = x;
	}
	
	/***
	 * This method returns the top left y-coordinate of the Enemy.
	 */
	public double getCentY() {
		return centy;
		
	}
	
	/***
	 * This method sets the top left y-coordinate of the Enemy.
	 * @param y is the top left y-coordinate of the Enemy.
	 */
	public void setCentY(double y) {
		centy = y;
		
	}
	
	/***
	 * This method returns the component of the speed in the x direction
	 * of the Enemy.
	 */
	public double getSpeedX() {
		
		return speed_x;
	}
	
	/***
	 * This method sets the component of the speed in the x direction
	 * of the Enemy object.
	 * @param x is the component of the speed in the x direction.
	 */
	public void setSpeedX(double x) {
		
		speed_x = x;
	}
	
	/***
	 * This method returns the component of the speed in the y direction
	 * of the Enemy object.
	 */
	public double getSpeedY() {
		return speed_y;
		
	}
	
	/***
	 * This method sets the component of the speed in the y direction
	 * of the Enemy object. 
	 * @param y is the component of the speed in the y direction
	 */
	public void setSpeedY(double y) {
		speed_y = y;
		
	}
	
	/***
	 * This method returns the radius of the enemy.
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}
	
	
	/***
	 * This method draws the Enemy object on the canvas. Currently, it's a rectangle, and should 
	 * probably be a circle.
	 */
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)centx, (int)centy, (int)radius, (int)radius);
		
	}
	
	/***
	 * This method causes the enemy virus to move in a threatening manner toward the player.
	 */
	public void doMove(Player p) {
		
		double pLocX = p.getCentX();
		double pLocY = p.getCentY();
		
		int amountH = (int)(Math.random()*(1 + 1));
		if (centx < pLocX)
			amountH = 0-amountH;
		
		int amountY = (int)(Math.random()*(1+1));
		if (centy < pLocY) 
			amountY = 0 - amountY;
		
		setCentX((getCentX()-(speed_x*amountH)));
		setCentY((getCentY()-(speed_y*amountY)));
		
		
	}


	
	
}
	