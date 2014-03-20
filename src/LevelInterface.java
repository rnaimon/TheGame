/*This interface is for the generic level class. 
 * There will be an actual level class for each level.
 */
public interface LevelInterface
{
	public int getLevelNumber();
	
	public int setLevelNumber();
	
	public ArrayList<Obstacles> getObstacleList();
	
	public ArrayList<Item> getItemList();
	
	
}