import java.awt.Graphics2D;

public interface LevelOneInterface extends LevelInterface
{
	
	/**
	 * This method induces the action of whatever the item the Player is holding
	 */
	public void function();
	
	/**
	 * This method draws the level. 
	 * It goes through the list of obstacles and items and draws the images of all the items
	 * @param g
	 */
	public void draw(Graphics2D g);
	
	/**
	 * This method checks if the level should be reset in such cases as if the player "dies"
	 * @return reset is a variable that is true if the level needs to be reset
	 */
	public boolean shouldReset();
}