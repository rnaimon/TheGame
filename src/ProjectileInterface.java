import java.awt.Color;

// Interface to deal with the projectile class
//	Rebecca Naimon

public interface ProjectileInterface {
	
	/* The following methods deal with getting and setting the top left 
	 * coordinates of the projectile, which is especially important
	 * because it will be moving, so these variables will be regularly
	 * updated within the program. */
	
	public double getTopX();
	public double getTopY();
	public void setTopX(double x);
	public void setTopY(double y);
	
	
	/***
	 * The following methods deal with getting and setting the speed
	 * of the projectile, and will include dealing with acceleration
	 * and the like.
	 * @return
	 */
	public double getSpeedX();
	public double getSpeedY();
	public void setSpeedX(double x);
	public void setSpeedY(double y);
	
	/***
	 * The following methods deal with getting and setting the color
	 * of the projectile.
	 * @return
	 */
	
	public Color getColor();
	public void setColor(Color c);
	
	
	
	
}