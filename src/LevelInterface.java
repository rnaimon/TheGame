import java.util.ArrayList;

/*This interface is for the generic level class. 
 * There will be an actual level class for each level.
 */
public interface LevelInterface
{
	/**
	 * This method returns the Player object
	 * @return player
	 */
	public Player getPlayer();
	
	/**
	 * This method sets the player to p1
	 * @param p1 is the new Player object player is set to
	 */
	public void setPlayer(Player p1);
	
	/**
	 * This method returns the level number of the level
	 * @return
	 */
	public int getLevelNumber();
	
	/**
	 * This method sets the number of the level to n
	 * @param n is the new level number
	 */
	public void setLevelNumber(int n);
	
	/**
	 * This method "gets" the width of the gameframe
	 * @return gameWidth
	 */
	public int getGameWidth();
	
	/**
	 * This method "gets" the height of the gameframe
	 * @return gameHeight
	 */
	public int getGameHeight();
	
	
	/**
	 * This method returns an ArrayList of the obstacles/platforms found in the level
	 * @return obstacles is the list of Obstacle Objects that includes barriers and platforms
	 */
	public ArrayList<Obstacles> getObstacleList();
	
	/**
	 * This method sets the ObstacleList to the ArrayList of obstacles, o
	 * @param o is the new arraylist of obstacles, which may include barriers and platforms
	 */
	public void setObstacleList(ArrayList<Obstacles> o);
	
	/**
	 * This method returns an ArrayList of Item objects found in the level
	 * @return itemlist is the list of all items found in the level
	 */
	public ArrayList<Item> getItemList();
	
}