import java.awt.Color;
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
	}

	
	/***
	 * This method sets up the level environment of Obstacles (like platforms). 
	 * Unfinished. 
	 * @return an ArrayList of Obstacles in this specific level
	 */
	public ArrayList<Obstacles> setUpEnvironment()
	{
		
		
		
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
		
		
		return obstacleList;
				
	}
	
	
	
	
	
	
}