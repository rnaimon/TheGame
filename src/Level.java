import java.util.ArrayList;

/***
 * This class is the Level class, which is the superclass of individual level classes
 * that make up the gameplay from level to level. This class contains all of the
 * commonalities from level to level. It implements LevelInterface.
 * @author Michael Katz
 * @author Rebecca Naimon
 *
 */
public class Level implements LevelInterface
{
	private Player player;
	private ArrayList<Item> itemlist;
	private ArrayList<Obstacles> obstacleList;
	private int gameWidth;
	private int gameHeight;
	
	/***
	 * Constructor for Level.
	 * @param p is the Player object
	 * @param w is the width of the game window
	 * @param h is the height of the game window
	 */
	public Level(Player p, int w, int h)
	{
		player=p;
		obstacleList=null;
		gameWidth=w;
		gameHeight=h;
	}
	
	/*  The following are getters and setters. For more comments, see the 
	 * LevelInterface.*/
	
	
	
	public int getLevelNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int setLevelNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Obstacles> getObstacleList() {
		// TODO Auto-generated method stub
		return obstacleList;
	}

	public ArrayList<Item> getItemList() {
		// TODO Auto-generated method stub
		return itemlist;
	}

	public Player getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

	public void setPlayer(Player p1) {
		// TODO Auto-generated method stub
		player=p1;
	}

	
	public void setLevelNumber(int n) {
		// TODO Auto-generated method stub
		
	}
	
	public void setObstacleList(ArrayList<Obstacles> o) {
		obstacleList=o;
		
	}
	
	public int getGameWidth() {
		return gameWidth;
	}
	
	public int getGameHeight() {
		return gameHeight;
	}

	
	
}