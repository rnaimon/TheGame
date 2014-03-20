import java.util.ArrayList;

public class Level implements LevelInterface
{
	private Player player;
	private ArrayList<Item> itemlist;
	private ArrayList<Obstacles> obstacleList;
	private int gameWidth;
	private int gameHeight;
	
	public Level(Player p, int w, int h)
	{
		player=p;
		obstacleList=null;
		gameWidth=w;
		gameHeight=h;
	}
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
		return null;
	}

	public ArrayList<Item> getItemList() {
		// TODO Auto-generated method stub
		return null;
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