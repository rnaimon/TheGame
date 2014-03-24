import java.awt.Graphics2D;
import java.util.ArrayList;

public class LevelOne extends Level implements LevelOneInterface
{
	private boolean reset;
	private Player player;
	private int WALL_SIZE=3;
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
	public boolean canMove(Player p, boolean moveLeftOrRight) {
		if (moveLeftOrRight) {
			if ((p.getCentX()) <= WALL_SIZE + p.getSpeed())
				return false;
			else
				return true;
		}
		if (p.getCentX() + p.getRadius() >= getWidth() - WALL_SIZE
				- p.getSpeed())
			return false;
		else
			return true;

	}

	// method to determine if the player can move up or down (limits of the screen)
	public void checkMoveV(Player p, boolean moveUpOrDown) 
	{
		ArrayList<LineObject> nearObstacles= new ArrayList<LineObject>();
		for(int i=0; i< getObstacleList().size(); i++)
		{
			LineObject l= getObstacleList().get(i).getOutlines().get(0);
			if(l.getOrientation()=='h')
			{
				if(p.getCentX()>= l.getV1().getXCoord() && p.getCentX()<= l.getV2().getXCoord())
				{
					nearObstacles.add(l);
				}
			}

			
		}
		if (moveUpOrDown) 
		{
			if ((p.getCentY() - p.getRadius()) <= WALL_SIZE + p.getSpeedY()) 
			{
				player.doMoveV(false, 0);
			} 
			else
				player.doMoveV(true, 5);
		}
		else
		{
			player.doMoveV(false, 0);
		}

	}

}