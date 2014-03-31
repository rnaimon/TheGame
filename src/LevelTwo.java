import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;


/***
 * This is level two, which features the player launching projectiles at switches
 * in order to activate platforms that allow movement to the end of the level. It 
 * is unfinished, so far, but will be a part of the MVP.
 * @author Rebecca Naimon
 *
 */
public class LevelTwo extends Level implements LevelTwoInterface {
	
	private boolean reset;
	private Player player;
	private int WALL_SIZE=3;
	
	Item dartItem = new Item(Color.BLUE, "Darts", "Dude, press Z and hit the bullseye.");
	Projectile dart = new Projectile(player.getCentX() + player.getRadius(), player.getCentY(), 7, 4, 5, 1.5);
	
	/***
	 * Constructor for LevelTwo sets up standard Level values, including obstacles.
	 * @param p is the Player object
	 * @param width is the width of the game window
	 * @param height is the width of the game window
	 */
	public LevelTwo(Player p, int width, int height)
	{
		super(p, width, height);
		player=p;
		player.setCentX(0);
		player.setCentY(0);
		setLevelNumber(2);
		reset=false;
		setObstacleList(setUpEnvironment());
		player.setPlatforms(getObstacleList());
	}

	
	/***
	 * This method sets up the level environment of Obstacles (like platforms). 
	 * Unfinished. 
	 * @return an ArrayList of Obstacles in this specific level
	 */
	public ArrayList<Obstacles> setUpEnvironment()
	{
		
		int gameHeight = super.getGameHeight();
		
		ArrayList<LineObject> platform1perimeter= new ArrayList<LineObject>();
		LineObject platformOneTop= new LineObject(0,super.getGameHeight()-super.getGameHeight()/5, 300, super.getGameHeight()-super.getGameHeight()/5);
		LineObject platformOneSideL= new LineObject(0,super.getGameHeight()-super.getGameHeight()/5, 0, super.getGameHeight()-super.getGameHeight()/5 + 20);
		LineObject platformOneBottom= new LineObject(0, super.getGameHeight()-super.getGameHeight()/5 + 20, 300, super.getGameHeight()-super.getGameHeight()/5 + 20);
		LineObject platformOneSideR= new LineObject(300,super.getGameHeight()-super.getGameHeight()/5, 300, super.getGameHeight()-super.getGameHeight()/5 + 20);
		
		platform1perimeter.add(platformOneTop);
		platform1perimeter.add(platformOneSideL);
		platform1perimeter.add(platformOneBottom);
		platform1perimeter.add(platformOneSideR);
		
		Obstacles Platform1= new Obstacles(platform1perimeter);
		
		ArrayList<Obstacles> obstacleList= new ArrayList<Obstacles>();
		obstacleList.add(Platform1);
		
		ArrayList<LineObject> platform2perimeter= new ArrayList<LineObject>();
		LineObject platformTwoTop= new LineObject(0, gameHeight-2*gameHeight/5, 300, gameHeight-2*gameHeight/5);
		LineObject platformTwoSideL= new LineObject(0,gameHeight-2*gameHeight/5, 0, gameHeight-2*gameHeight/5 + 20);
		LineObject platformTwoBottom= new LineObject(0, gameHeight-2*gameHeight/5 + 20, 300, gameHeight-2*gameHeight/5 + 20);
		LineObject platformTwoSideR= new LineObject(300,gameHeight-2*gameHeight/5, 300, gameHeight-2*gameHeight/5 + 20);
		
		platform2perimeter.add(platformTwoTop);
		platform2perimeter.add(platformTwoSideL);
		platform2perimeter.add(platformTwoBottom);
		platform2perimeter.add(platformTwoSideR);
		
		ArrayList<LineObject> platform3perimeter= new ArrayList<LineObject>();
		LineObject platformThreeTop= new LineObject(0, gameHeight-3*gameHeight/5, 300, gameHeight-3*gameHeight/5);
		LineObject platformThreeSideL= new LineObject(0,gameHeight-3*gameHeight/5, 0, gameHeight-3*gameHeight/5 + 20);
		LineObject platformThreeBottom= new LineObject(0, gameHeight-3*gameHeight/5 + 20, 300, gameHeight-3*gameHeight/5 + 20);
		LineObject platformThreeSideR= new LineObject(300,gameHeight-3*gameHeight/5, 300, gameHeight-3*gameHeight/5 + 20);
		
		platform3perimeter.add(platformThreeTop);
		platform3perimeter.add(platformThreeSideL);
		platform3perimeter.add(platformThreeBottom);
		platform3perimeter.add(platformThreeSideR);
		
		return obstacleList;
			
		
		//Now for the platforms on the right side
		
		
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