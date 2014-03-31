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
	}

	/***
	 * This method sets up the level environment of Obstacles (like platforms). 
	 * @return an ArrayList of Obstacles in this specific level
	 */
	public ArrayList<Obstacles> setUpEnvironment()
	{
		ArrayList<LineObject> platform1perimeter= new ArrayList<LineObject>();
		LineObject platformOneTop= new LineObject(0,super.getGameHeight()/2, 500, super.getGameHeight()/2);
		LineObject platformOneSideL= new LineObject(0,super.getGameHeight()/2, 0, super.getGameHeight()/2 + 20);
		LineObject platformOneBottom= new LineObject(0,super.getGameHeight()/2 + 20, 500, super.getGameHeight()/2 + 20);
		LineObject platformOneSideR= new LineObject(500,super.getGameHeight()/2, 500, super.getGameHeight()/2 + 20);
		
		platform1perimeter.add(platformOneTop);
		platform1perimeter.add(platformOneSideR);
		platform1perimeter.add(platformOneBottom);
		platform1perimeter.add(platformOneSideL);
		
		Obstacles Platform1= new Obstacles(platform1perimeter);
		
		ArrayList<LineObject> platform2perimeter= new ArrayList<LineObject>();
		
		LineObject platform2Top= new LineObject(200,super.getGameHeight()/2, 700, super.getGameHeight()/2);
		LineObject platform2SideL= new LineObject(200,super.getGameHeight()/2, 200, super.getGameHeight()/2 + 20);
		LineObject platform2Bottom= new LineObject(200,super.getGameHeight()/2 + 20, 700, super.getGameHeight()/2 + 20);
		LineObject platform2SideR= new LineObject(700,super.getGameHeight()/2, 700, super.getGameHeight()/2 + 20);
		
		platform2perimeter.add(platform2Top);
		platform2perimeter.add(platform2SideR);
		platform2perimeter.add(platform2Bottom);
		platform2perimeter.add(platform2SideL);
		Obstacles Platform2= new Obstacles(platform2perimeter);
		Platform2.translate(200, 5);
		
		ArrayList<Obstacles> obstacleList= new ArrayList<Obstacles>();
		obstacleList.add(Platform1);
		obstacleList.add(Platform2);
		
		//System.out.println(obstacleList);
		return obstacleList;
				
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
	public boolean shouldReset() {
		return reset;
	}

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
				System.out.println("here");
				reset=true;
			}
			else
			{
				player.doMoveV(false, 0, nearObstacles);
			}
		}

	}

}