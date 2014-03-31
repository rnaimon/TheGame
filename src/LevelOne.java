import java.awt.Graphics2D;
import java.util.ArrayList;

/***
 * This class is the LevelOne class, a subclass of Level, that sets up the first level
 * of the game and allows the player to travel through it and interact with it.
 * @author Michael Katz
 * @author Rebecca Naimon
 *
 */
public class LevelOne extends Level implements LevelOneInterface
{
	private boolean reset;
	private Player player;
	private int WALL_SIZE=3;
	
	
	
	/***
	 * Constructor for LevelOne passes in the player and preps the level for being 
	 * set up with platforms and obstacles.
	 * @param p is the Player object
	 * @param width is the width of the game window
	 * @param height is the height of the game window
	 */
	public LevelOne(Player p, int width, int height)
	{
		super(p, width, height);
		player=p;
		player.setCentX(0);
		player.setCentY(0);
		setLevelNumber(1);
		reset=false;
		super.setObstacleList(setUpEnvironment());
		player.setPlatforms(getObstacleList());
		setSwitchList(setUpEndGoal());
	}

	/***
	 * This method sets up the level environment of Obstacles (like platforms). 
	 * @return an ArrayList of Obstacles in this specific level
	 */
	public ArrayList<Obstacles> setUpEnvironment()
	{
		ArrayList<LineObject> platform1perimeter= new ArrayList<LineObject>();
		LineObject platformOneTop= new LineObject(0,super.getGameHeight()/2, 250, super.getGameHeight()/2);
		LineObject platformOneSideL= new LineObject(0,super.getGameHeight()/2, 0, super.getGameHeight()/2 + 20);
		LineObject platformOneBottom= new LineObject(0,super.getGameHeight()/2 + 20, 250, super.getGameHeight()/2 + 20);
		LineObject platformOneSideR= new LineObject(250,super.getGameHeight()/2, 250, super.getGameHeight()/2 + 20);
		
		platform1perimeter.add(platformOneTop);
		platform1perimeter.add(platformOneSideR);
		platform1perimeter.add(platformOneBottom);
		platform1perimeter.add(platformOneSideL);
		
		Obstacles Platform1= new Obstacles(platform1perimeter);
		int x=500;
		
		ArrayList<LineObject> platform2perimeter= new ArrayList<LineObject>();
		
		LineObject platform2Top= new LineObject(x,super.getGameHeight()/2, 250+x, super.getGameHeight()/2);
		LineObject platform2SideL= new LineObject(x,super.getGameHeight()/2, x, super.getGameHeight()/2 + 20);
		LineObject platform2Bottom= new LineObject(x,super.getGameHeight()/2 + 20, 250+x, super.getGameHeight()/2 + 20);
		LineObject platform2SideR= new LineObject(250+x,super.getGameHeight()/2, 250+x, super.getGameHeight()/2 + 20);
		
		platform2perimeter.add(platform2Top);
		platform2perimeter.add(platform2SideR);
		platform2perimeter.add(platform2Bottom);
		platform2perimeter.add(platform2SideL);
		Obstacles Platform2= new Obstacles(platform2perimeter);
		
		ArrayList<LineObject> platform3perimeter= new ArrayList<LineObject>();
		
		LineObject platform3Top= new LineObject(2*x,super.getGameHeight()/2, 250+2*x, super.getGameHeight()/2);
		LineObject platform3SideL= new LineObject(2*x,super.getGameHeight()/2, 2*x, super.getGameHeight()/2 + 20);
		LineObject platform3Bottom= new LineObject(2*x,super.getGameHeight()/2 + 20, 250+2*x, super.getGameHeight()/2 + 20);
		LineObject platform3SideR= new LineObject(250+2*x,super.getGameHeight()/2, 250+2*x, super.getGameHeight()/2 + 20);
		
		platform3perimeter.add(platform3Top);
		platform3perimeter.add(platform3SideR);
		platform3perimeter.add(platform3Bottom);
		platform3perimeter.add(platform3SideL);
		Obstacles Platform3= new Obstacles(platform3perimeter);
		
		ArrayList<Obstacles> obstacleList= new ArrayList<Obstacles>();
		obstacleList.add(Platform1);
		obstacleList.add(Platform2);
		obstacleList.add(Platform3);
		
		//System.out.println(obstacleList);
		return obstacleList;
				
	}
	
	@Override
	public boolean checkComplete()
	{
		Switch endGoal= getSwitchList().get(0);
		double d= Math.sqrt((player.getCentX()-endGoal.getCentX())*(player.getCentX()-endGoal.getCentX()) + (player.getCentY()-endGoal.getCentY())*(player.getCentY()-endGoal.getCentY()));
		if(d<=(player.getRadius()+ 50/2*Math.sqrt(2)))
		{
			return true;
		}
		else
			return false;
	}
	
	
	
	public ArrayList<Switch> setUpEndGoal()
	{
		ArrayList<LineObject> goalPerimeter= new ArrayList<LineObject>();
		
		int x= 500;
		int y=50;
		
		LineObject Goal1Top= new LineObject(250 + 2*x - y,super.getGameHeight()/2 - y , 250+2*x, super.getGameHeight()/2 - y);
		LineObject Goal1SideL= new LineObject(250 + 2*x - y, super.getGameHeight()/2 -  y, 2*x+ 250 - y, super.getGameHeight()/2);
		LineObject Goal1Bottom= new LineObject(250 + 2*x - y,super.getGameHeight()/2, 250+2*x, super.getGameHeight()/2);
		LineObject Goal1SideR= new LineObject(250+2*x,super.getGameHeight()/2 - y, 250+2*x, super.getGameHeight()/2);
		
		goalPerimeter.add(Goal1Top);
		goalPerimeter.add(Goal1SideR);
		goalPerimeter.add(Goal1Bottom);
		goalPerimeter.add(Goal1SideL);
		
		Switch s= new Switch(goalPerimeter);
		
		ArrayList<Switch> switchels= new ArrayList<Switch>();
		switchels.add(s);
		
		return switchels;
		
	}
	
	/***
	 * This method contains code to allow the level-specific Item to have a certain 
	 * function, if one were to exist. 
	 */
	public void function() {
		
		
	}

	/***
	 * 
	 */
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	/***
	 * Returns whether the level needs resetting (as in, when the player must start 
	 * over or something similar)
	 */
	

	/*** Method to determine if the player can move up or down (limits of the screen or 
	 * Obstacles)
	 * 
	 * @param p is the Player object
	 * @param moveUpOrDown is the boolean of whether the player can move up or down
	 * @param jumpspeed is the rate of the player's movement vertically
	 */
	public void checkMoveV(Player p, boolean moveUpOrDown, int jumpspeed) 
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
				player.doMoveV(false, 0, nearObstacles);
			} 
			else
				player.doMoveV(true, 5, nearObstacles);
		}
		else
		{
			if((p.getCentY() + p.getRadius()) >= super.getGameHeight() - WALL_SIZE - p.getSpeedY())
			{
				reset=true;
			}
			else
			{
				player.doMoveV(false, 0, nearObstacles);
			}
		}

	}

	public boolean shouldReset() {
		
		return reset;
	}

}