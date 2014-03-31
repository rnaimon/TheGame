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
	//Projectile dart = new Projectile(player.getCentX() + player.getRadius(), player.getCentY(), 7, 4, 5, 1.5);
	
	ArrayList<Switch> switchList;
	private boolean levelComplete;

	
	
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
		switchList= setUpSwitches();
		setObstacleList(setUpEnvironment());
		player.setPlatforms(getObstacleList());
		levelComplete=false;
	}

	
	/***
	 * This method sets up the level environment of Obstacles (like platforms). 
	 * Unfinished. 
	 * @return an ArrayList of Obstacles in this specific level
	 */
	public ArrayList<Obstacles> setUpEnvironment()
	{
		
		int gameHeight = super.getGameHeight();
		int gameWidth = super.getGameWidth();
		
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
		
		Obstacles Platform2= new Obstacles(platform2perimeter);
		obstacleList.add(Platform2);
		
		
		ArrayList<LineObject> platform3perimeter= new ArrayList<LineObject>();
		LineObject platformThreeTop= new LineObject(0, gameHeight-3*gameHeight/5, 300, gameHeight-3*gameHeight/5);
		LineObject platformThreeSideL= new LineObject(0,gameHeight-3*gameHeight/5, 0, gameHeight-3*gameHeight/5 + 20);
		LineObject platformThreeBottom= new LineObject(0, gameHeight-3*gameHeight/5 + 20, 300, gameHeight-3*gameHeight/5 + 20);
		LineObject platformThreeSideR= new LineObject(300,gameHeight-3*gameHeight/5, 300, gameHeight-3*gameHeight/5 + 20);
		
		platform3perimeter.add(platformThreeTop);
		platform3perimeter.add(platformThreeSideL);
		platform3perimeter.add(platformThreeBottom);
		platform3perimeter.add(platformThreeSideR);
		
		Obstacles Platform3= new Obstacles(platform3perimeter);
		obstacleList.add(Platform3);
		
		//Now for the platforms on the right side
		
		
		ArrayList<LineObject> platform4perimeter= new ArrayList<LineObject>();
		LineObject platformFourTop= new LineObject(gameWidth-300, gameHeight-gameHeight/5 - 150, gameWidth, gameHeight-gameHeight/5 -150);
		LineObject platformFourSideL= new LineObject(gameWidth-300,gameHeight-gameHeight/5 - 150, gameWidth-300, gameHeight-gameHeight/5 + 20 -150);
		LineObject platformFourBottom= new LineObject(gameWidth-300, gameHeight-gameHeight/5 + 20 - 150, gameWidth, gameHeight-gameHeight/5 + 20 - 150);
		LineObject platformFourSideR= new LineObject(gameWidth,gameHeight-gameHeight/5 - 150, gameWidth, gameHeight-gameHeight/5 + 20 - 150);
		
		platform4perimeter.add(platformFourTop);
		platform4perimeter.add(platformFourSideL);
		platform4perimeter.add(platformFourBottom);
		platform4perimeter.add(platformFourSideR);
			
		Obstacles Platform4= new Obstacles(platform4perimeter);
		obstacleList.add(Platform4);
		
		
		ArrayList<LineObject> platform5perimeter= new ArrayList<LineObject>();
		LineObject platformFiveTop= new LineObject(gameWidth-300, gameHeight-2*gameHeight/5 - 150, gameWidth, gameHeight-2*gameHeight/5 -150);
		LineObject platformFiveSideL= new LineObject(gameWidth-300,gameHeight-2*gameHeight/5 - 150, gameWidth-300, gameHeight-2*gameHeight/5 + 20 -150);
		LineObject platformFiveBottom= new LineObject(gameWidth-300, gameHeight-2*gameHeight/5 + 20 - 150, gameWidth, gameHeight-2*gameHeight/5 + 20 - 150);
		LineObject platformFiveSideR= new LineObject(gameWidth,gameHeight-2*gameHeight/5 - 150, gameWidth, gameHeight-2*gameHeight/5 + 20 - 150);
		
		platform5perimeter.add(platformFiveTop);
		platform5perimeter.add(platformFiveSideL);
		platform5perimeter.add(platformFiveBottom);
		platform5perimeter.add(platformFiveSideR);
		
		Obstacles Platform5= new Obstacles(platform5perimeter);
		obstacleList.add(Platform5);
		
		
		ArrayList<LineObject> platform6perimeter= new ArrayList<LineObject>();
		LineObject platformSixTop= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 -150);
		LineObject platformSixSideL= new LineObject(gameWidth-300,gameHeight-3*gameHeight/5 - 150, gameWidth-300, gameHeight-3*gameHeight/5 + 20 -150);
		LineObject platformSixBottom= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 + 20 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
		LineObject platformSixSideR= new LineObject(gameWidth,gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
		
		platform6perimeter.add(platformSixTop);
		platform6perimeter.add(platformSixSideL);
		platform6perimeter.add(platformSixBottom);
		platform6perimeter.add(platformSixSideR);
		
		Obstacles Platform6= new Obstacles(platform6perimeter);
		obstacleList.add(Platform6);
		
		/*
		 * The following if-statements test to see whether the hidden platforms
		 * should be shown and added to the environment, based on whether the
		 * switches were contacted.
		 * 
		 * WARNING: coordinates are not correct. I am waiting to put in real values
		 * until I can see where they are, because I don't want to do that twice.
		 */
		
		if (switchList.get(1).getContacted() == true) {
			
			ArrayList<LineObject> hidden1perimeter= new ArrayList<LineObject>();
			LineObject hidden1Top= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 -150);
			LineObject hidden1SideL= new LineObject(gameWidth-300,gameHeight-3*gameHeight/5 - 150, gameWidth-300, gameHeight-3*gameHeight/5 + 20 -150);
			LineObject hidden1Bottom= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 + 20 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
			LineObject hidden1SideR= new LineObject(gameWidth,gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
			
			hidden1perimeter.add(hidden1Top);
			hidden1perimeter.add(hidden1SideL);
			hidden1perimeter.add(hidden1Bottom);
			hidden1perimeter.add(hidden1SideR);
			
			Obstacles hidden1= new Obstacles(hidden1perimeter);
			obstacleList.add(hidden1);
			
		}
		
		if (switchList.get(2).getContacted() == true) {
			
			ArrayList<LineObject> hidden2perimeter= new ArrayList<LineObject>();
			LineObject hidden2Top= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 -150);
			LineObject hidden2SideL= new LineObject(gameWidth-300,gameHeight-3*gameHeight/5 - 150, gameWidth-300, gameHeight-3*gameHeight/5 + 20 -150);
			LineObject hidden2Bottom= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 + 20 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
			LineObject hidden2SideR= new LineObject(gameWidth,gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
			
			hidden2perimeter.add(hidden2Top);
			hidden2perimeter.add(hidden2SideL);
			hidden2perimeter.add(hidden2Bottom);
			hidden2perimeter.add(hidden2SideR);
			
			Obstacles hidden2= new Obstacles(hidden2perimeter);
			obstacleList.add(hidden2);
			
		}
		
		if (switchList.get(3).getContacted() == true) {
			
			ArrayList<LineObject> hidden3perimeter= new ArrayList<LineObject>();
			LineObject hidden3Top= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 -150);
			LineObject hidden3SideL= new LineObject(gameWidth-300,gameHeight-3*gameHeight/5 - 150, gameWidth-300, gameHeight-3*gameHeight/5 + 20 -150);
			LineObject hidden3Bottom= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 + 20 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
			LineObject hidden3SideR= new LineObject(gameWidth,gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
			
			hidden3perimeter.add(hidden3Top);
			hidden3perimeter.add(hidden3SideL);
			hidden3perimeter.add(hidden3Bottom);
			hidden3perimeter.add(hidden3SideR);
			
			Obstacles hidden3= new Obstacles(hidden3perimeter);
			obstacleList.add(hidden3);
			
		}
		
		if (switchList.get(4).getContacted() == true) {
			
			ArrayList<LineObject> hidden4perimeter= new ArrayList<LineObject>();
			LineObject hidden4Top= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 -150);
			LineObject hidden4SideL= new LineObject(gameWidth-300,gameHeight-3*gameHeight/5 - 150, gameWidth-300, gameHeight-3*gameHeight/5 + 20 -150);
			LineObject hidden4Bottom= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 + 20 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
			LineObject hidden4SideR= new LineObject(gameWidth,gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
			
			hidden4perimeter.add(hidden4Top);
			hidden4perimeter.add(hidden4SideL);
			hidden4perimeter.add(hidden4Bottom);
			hidden4perimeter.add(hidden4SideR);
			
			Obstacles hidden4= new Obstacles(hidden4perimeter);
			obstacleList.add(hidden4);
			
		}
		
		if (switchList.get(5).getContacted() == true) {
			
			ArrayList<LineObject> hidden5perimeter= new ArrayList<LineObject>();
			LineObject hidden5Top= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 -150);
			LineObject hidden5SideL= new LineObject(gameWidth-300,gameHeight-3*gameHeight/5 - 150, gameWidth-300, gameHeight-3*gameHeight/5 + 20 -150);
			LineObject hidden5Bottom= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 + 20 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
			LineObject hidden5SideR= new LineObject(gameWidth,gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
			
			hidden5perimeter.add(hidden5Top);
			hidden5perimeter.add(hidden5SideL);
			hidden5perimeter.add(hidden5Bottom);
			hidden5perimeter.add(hidden5SideR);
			
			Obstacles hidden5= new Obstacles(hidden5perimeter);
			obstacleList.add(hidden5);
			
		}
		
		if (switchList.get(6).getContacted() == true) {
		
			ArrayList<LineObject> hidden6perimeter= new ArrayList<LineObject>();
			LineObject hidden6Top= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 -150);
			LineObject hidden6SideL= new LineObject(gameWidth-300,gameHeight-3*gameHeight/5 - 150, gameWidth-300, gameHeight-3*gameHeight/5 + 20 -150);
			LineObject hidden6Bottom= new LineObject(gameWidth-300, gameHeight-3*gameHeight/5 + 20 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
			LineObject hidden6SideR= new LineObject(gameWidth,gameHeight-3*gameHeight/5 - 150, gameWidth, gameHeight-3*gameHeight/5 + 20 - 150);
			
			hidden6perimeter.add(hidden6Top);
			hidden6perimeter.add(hidden6SideL);
			hidden6perimeter.add(hidden6Bottom);
			hidden6perimeter.add(hidden6SideR);
			
			Obstacles hidden6= new Obstacles(hidden6perimeter);
			obstacleList.add(hidden6);
			
		}
		
		
		
		
		
		
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
	
	/***
	 * Checks whether the level is complete, based on whether the switch at the 
	 * end of the level has been touched.
	 * @return whether the level is complete or not
	 */
	public boolean checkComplete()
	{
		Switch endGoal= switchList.get(0);
		double d= Math.sqrt((player.getCentX()-endGoal.getCentX())*(player.getCentX()-endGoal.getCentX()) + (player.getCentY()-endGoal.getCentY())*(player.getCentY()-endGoal.getCentY()));
		if(d<=(player.getRadius()+ 50/2*Math.sqrt(2)))
		{
			return true;
		}
		else
			return false;
	}
	
	/***
	 * Getter for the switchList arraylist
	 * @return arraylist of switches in this level
	 */
	public ArrayList<Switch> getSwitchList()
	{
		return switchList;
	}
	
	public ArrayList<Switch> setUpSwitches() {
		
		
		/*
		 * All of the values where the LineObjects are being created need to change. 
		 * It's very difficult to figure out where they go without being able to test
		 * it in the window, so for now, they're all set to level one's end goal. I 
		 * could approximate, but I'd have to end up changing them all anyway, so I'd
		 * rather only do it once.
		 */
		
		ArrayList<Switch> switches= new ArrayList<Switch>();

		
		
		ArrayList<LineObject> switch1 = new ArrayList<LineObject>();
		
		int x= 500;
		int y=50;
		
		// end goal switch is always first
		LineObject Switch1Top= new LineObject(250 + 2*x - y,super.getGameHeight()/2 - y , 250+2*x, super.getGameHeight()/2 - y);
		LineObject Switch1SideL= new LineObject(250 + 2*x - y, super.getGameHeight()/2 -  y, 2*x+ 250 - y, super.getGameHeight()/2);
		LineObject Switch1Bottom= new LineObject(250 + 2*x - y,super.getGameHeight()/2, 250+2*x, super.getGameHeight()/2);
		LineObject Switch1SideR= new LineObject(250+2*x,super.getGameHeight()/2 - y, 250+2*x, super.getGameHeight()/2);
		
		switch1.add(Switch1Top);
		switch1.add(Switch1SideL);
		switch1.add(Switch1Bottom);
		switch1.add(Switch1SideR);

		Switch s = new Switch(switch1);
		
		switches.add(s);
		
		
		// This level has a bunch of switches, so here they are, after the end goal.
		// There should be six extra switches in this level, making seven total.
		ArrayList<LineObject> switch2 = new ArrayList<LineObject>();

		
		LineObject Switch2Top= new LineObject(250 + 2*x - y,super.getGameHeight()/2 - y , 250+2*x, super.getGameHeight()/2 - y);
		LineObject Switch2SideL= new LineObject(250 + 2*x - y, super.getGameHeight()/2 -  y, 2*x+ 250 - y, super.getGameHeight()/2);
		LineObject Switch2Bottom= new LineObject(250 + 2*x - y,super.getGameHeight()/2, 250+2*x, super.getGameHeight()/2);
		LineObject Switch2SideR= new LineObject(250+2*x,super.getGameHeight()/2 - y, 250+2*x, super.getGameHeight()/2);
		
		switch2.add(Switch2Top);
		switch2.add(Switch2SideL);
		switch2.add(Switch2Bottom);
		switch2.add(Switch2SideR);

		Switch s2 = new Switch(switch2);

		switches.add(s2);
		
		// new switch
	
		ArrayList<LineObject> switch3 = new ArrayList<LineObject>();

		
		LineObject Switch3Top= new LineObject(250 + 2*x - y,super.getGameHeight()/2 - y , 250+2*x, super.getGameHeight()/2 - y);
		LineObject Switch3SideL= new LineObject(250 + 2*x - y, super.getGameHeight()/2 -  y, 2*x+ 250 - y, super.getGameHeight()/2);
		LineObject Switch3Bottom= new LineObject(250 + 2*x - y,super.getGameHeight()/2, 250+2*x, super.getGameHeight()/2);
		LineObject Switch3SideR= new LineObject(250+2*x,super.getGameHeight()/2 - y, 250+2*x, super.getGameHeight()/2);
		
		switch3.add(Switch3Top);
		switch3.add(Switch3SideL);
		switch3.add(Switch3Bottom);
		switch3.add(Switch3SideR);

		Switch s3 = new Switch(switch3);

		switches.add(s3);
		
		// new switch
		
		ArrayList<LineObject> switch4 = new ArrayList<LineObject>();

		
		LineObject Switch4Top= new LineObject(250 + 2*x - y,super.getGameHeight()/2 - y , 250+2*x, super.getGameHeight()/2 - y);
		LineObject Switch4SideL= new LineObject(250 + 2*x - y, super.getGameHeight()/2 -  y, 2*x+ 250 - y, super.getGameHeight()/2);
		LineObject Switch4Bottom= new LineObject(250 + 2*x - y,super.getGameHeight()/2, 250+2*x, super.getGameHeight()/2);
		LineObject Switch4SideR= new LineObject(250+2*x,super.getGameHeight()/2 - y, 250+2*x, super.getGameHeight()/2);
		
		switch4.add(Switch4Top);
		switch4.add(Switch4SideL);
		switch4.add(Switch4Bottom);
		switch4.add(Switch4SideR);

		Switch s4 = new Switch(switch4);

		switches.add(s4);
		
		
		// new switch
		
		ArrayList<LineObject> switch5 = new ArrayList<LineObject>();

				
		LineObject Switch5Top= new LineObject(250 + 2*x - y,super.getGameHeight()/2 - y , 250+2*x, super.getGameHeight()/2 - y);
		LineObject Switch5SideL= new LineObject(250 + 2*x - y, super.getGameHeight()/2 -  y, 2*x+ 250 - y, super.getGameHeight()/2);
		LineObject Switch5Bottom= new LineObject(250 + 2*x - y,super.getGameHeight()/2, 250+2*x, super.getGameHeight()/2);
		LineObject Switch5SideR= new LineObject(250+2*x,super.getGameHeight()/2 - y, 250+2*x, super.getGameHeight()/2);
				
		switch5.add(Switch5Top);
		switch5.add(Switch5SideL);
		switch5.add(Switch5Bottom);
		switch5.add(Switch5SideR);

		Switch s5 = new Switch(switch5);

		switches.add(s5);
			
		// new switch
		
		ArrayList<LineObject> switch6 = new ArrayList<LineObject>();

				
		LineObject Switch6Top= new LineObject(250 + 2*x - y,super.getGameHeight()/2 - y , 250+2*x, super.getGameHeight()/2 - y);
		LineObject Switch6SideL= new LineObject(250 + 2*x - y, super.getGameHeight()/2 -  y, 2*x+ 250 - y, super.getGameHeight()/2);
		LineObject Switch6Bottom= new LineObject(250 + 2*x - y,super.getGameHeight()/2, 250+2*x, super.getGameHeight()/2);
		LineObject Switch6SideR= new LineObject(250+2*x,super.getGameHeight()/2 - y, 250+2*x, super.getGameHeight()/2);
				
		switch6.add(Switch6Top);
		switch6.add(Switch6SideL);
		switch6.add(Switch6Bottom);
		switch6.add(Switch6SideR);

		Switch s6 = new Switch(switch6);

		switches.add(s6);
		
		
		// new switch
		
		ArrayList<LineObject> switch7 = new ArrayList<LineObject>();

				
		LineObject Switch7Top= new LineObject(250 + 2*x - y,super.getGameHeight()/2 - y , 250+2*x, super.getGameHeight()/2 - y);
		LineObject Switch7SideL= new LineObject(250 + 2*x - y, super.getGameHeight()/2 -  y, 2*x+ 250 - y, super.getGameHeight()/2);
		LineObject Switch7Bottom= new LineObject(250 + 2*x - y,super.getGameHeight()/2, 250+2*x, super.getGameHeight()/2);
		LineObject Switch7SideR= new LineObject(250+2*x,super.getGameHeight()/2 - y, 250+2*x, super.getGameHeight()/2);
				
		switch7.add(Switch7Top);
		switch7.add(Switch7SideL);
		switch7.add(Switch7Bottom);
		switch7.add(Switch7SideR);

		Switch s7 = new Switch(switch7);

		switches.add(s7);
		
		return switches;
	}
	
	
	
	/***
	 * This method determines whether a projectile, which is an element that appears
	 * multiple times in the game, is touching a switch, also an element that appears
	 * throughout the game. 
	 * @param dart is the Projectile object that is going to possibly hit a switch
	 * @param switch1 is the Switch object that will change its contact status if hit
	 * by a dart
	 */
	 public void isProjectileTouchingSwitch(Projectile dart, Switch switch1) {
	 
		 
		if(((dart.getTopX()+dart.getWidth()) >= switch1.getOutlines().get(0).getV2().getXCoord()) && dart.getSpeedX()>0) {
			switch1.changeContactStatus();
			dart.selfDestruct();
		}
		else if (((dart.getTopX()) <= switch1.getOutlines().get(0).getV1().getXCoord()) && dart.getSpeedX()<0) {
			switch1.changeContactStatus();
			dart.selfDestruct();
		}
		
	}
	
	
}