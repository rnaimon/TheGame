import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
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
	private boolean levelComplete;
	private boolean reset;
	private ArrayList<Switch> switchList;
	private int levelNum;
	private BufferedImage background;
	
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
		levelComplete=false;
		reset=false;
		levelComplete=false;
	}
	
	/*  The following are getters and setters. For more comments, see the 
	 * LevelInterface.*/
	
	
	public void draw(Graphics2D g)
	{
		
	}
	
	/***
	 * Returns the background for the level
	 * @return the background buffered image
	 */
	public BufferedImage getBackground() {
		return background;
	}
	
	
	/***
	 * method to determine if the level should reset
	 * @return the boolean of whether the level needs to be reset
	 */
	public boolean shouldReset() {
		return reset;
	}
	
	
	/***
	 * method to determine if the level should reset
	 * @return the boolean of whether the level needs to be reset
	 */
	public void setReset(boolean r) {
		reset = r;
	}
	
	
	public int getLevelNumber() {
		return levelNum;
	}
	public void setSwitchList(ArrayList<Switch> s){
		switchList= s;
	}
	
	public ArrayList<Switch> getSwitchList(){
		return switchList;
	}
	public boolean checkComplete()
	{
		return levelComplete;
	}
	public void setLevelComplete(boolean b)
	{
		levelComplete=b;
	}
	public void setLevelNumber(int n) 
	{
		levelNum=n;
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

	
	public void setObstacleList(ArrayList<Obstacles> o) {
		obstacleList=o;
		
	}
	
	public int getGameWidth() {
		return gameWidth;
	}
	
	public int getGameHeight() {
		return gameHeight;
	}
	public boolean getLevelComplete()
	{
		return levelComplete;
	}

	/***
	 * This method contains code to allow the level-specific Item to have a certain 
	 * function, if one were to exist. 
	 */
	public void function() {
		System.out.println("LEVEL, WRONG ONE");
		
	}
	
}