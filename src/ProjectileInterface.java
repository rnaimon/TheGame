// Interface to deal with the projectile class
//	Rebecca Naimon

public interface ProjectileInterface {
	
	/***
	 * The following methods deal with getting and setting the top left 
	 * coordinates of the projectile, which is especially important
	 * because it will be moving.
	 * @return
	 */
	double getTopX();
	double getTopY();
	void setTopX();
	void setTopY();
	
	
	/***
	 * The following methods deal with getting and setting the speed
	 * of the projectile, and will include dealing with acceleration
	 * and the like.
	 * @return
	 */
	double getSpeedX();
	double getSpeedY();
	void setSpeedX();
	void setSpeedY();
	
	
	
	
	
}