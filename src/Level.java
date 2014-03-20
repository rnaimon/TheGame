import java.util.ArrayList;

public class Level implements LevelInterface
{
	private Player player;
	
	public Level(Player p)
	{
		player=p;
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
	
}