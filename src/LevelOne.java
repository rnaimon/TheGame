import java.awt.Graphics2D;
import java.util.ArrayList;

public class LevelOne extends Level implements LevelOneInterface
{
	private boolean reset;
	private Player player;
	public LevelOne(Player p, int width, int height)
	{
		super(p, width, height);
		player=p;
		player.setCentX(0);
		player.setCentY(0);
		setLevelNumber(1);
		reset=false;
		setObstacleList(setUpEnvironment());
	}

	public ArrayList<Obstacles> setUpEnvironment()
	{
		ArrayList<LineObject> platform1perimeter= new ArrayList<LineObject>();
		LineObject platformOneTop= new LineObject(0,super.getGameHeight()/2, 500, super.getGameHeight()/2);
		LineObject platformOneSideL= new LineObject(0,super.getGameHeight()/2, 0, super.getGameHeight()/2 + 20);
		LineObject platformOneBottom= new LineObject(0,super.getGameHeight()/2 + 20, 500, super.getGameHeight()/2 + 20);
		LineObject platformOneSideR= new LineObject(500,super.getGameHeight()/2, 500, super.getGameHeight()/2 + 20);
		
		platform1perimeter.add(platformOneTop);
		platform1perimeter.add(platformOneSideL);
		platform1perimeter.add(platformOneBottom);
		platform1perimeter.add(platformOneSideR);
		
		Obstacles Platform1= new Obstacles(platform1perimeter);
		
		ArrayList<Obstacles> obstacleList= new ArrayList<Obstacles>();
		obstacleList.add(Platform1);
		
		
		return obstacleList;
				
	}
	public void function() {
		
		
	}

	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	public boolean shouldReset() {
		return reset;
	}
}