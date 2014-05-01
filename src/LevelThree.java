import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/***
 * Cheat sheet in code for how the switches and platforms should be handled:
 * Hit switch one, directly across from the landing platform. Go down the ramp and
 * hit the next lowest switch on the right, and then the lowest switch on the right.
 * Travel across to the lowest platform on the left and hit that switch. Go back up
 * to the lowest platform on the right, and then back up the ramp to the very first
 * platform. Hit the first switch again and make the first ramp disappear. Fall down
 * to the middle platform on the left, hit that switch, and then travel happily
 * up the ramps, making the small jump, to the top right platform and the end of 
 * the level. 
 */



/***
 * This is level two, which features the player launching projectiles at switches
 * in order to activate platforms that allow movement to the end of the level. It 
 * is unfinished, so far, but will be a part of the MVP.
 * @author Rebecca Naimon
 *
 */
public class LevelThree extends Level implements LevelTwoInterface {
	
	private boolean reset;
	private Player player;
	private int WALL_SIZE=3;
	int pipelength=40;
	private Item dartgun;
	private ArrayList<Projectile> darts;
	ArrayList<Switch> switchList;
	ArrayList<Pipe> pipeList;
	private boolean levelComplete;
	private ArrayList<Obstacles> hiddenRamps;
	private ArrayList<PipeConnector> pipeConnectors;
	private ArrayList<PipeConnectorSwitch> pipeSwitches;
	
	
	/***
	 * Constructor for LevelTwo sets up standard Level values, including obstacles.
	 * @param p is the Player object
	 * @param width is the width of the game window
	 * @param height is the width of the game window
	 */
	public LevelThree(Player p, int width, int height)
	{
		super(p, width, height);
		player=p;
		player.setCentX(0);
		player.setCentY(0);
		setLevelNumber(2);
		
		reset=false;
		pipeConnectors= new ArrayList<PipeConnector>();
		pipeSwitches= new ArrayList<PipeConnectorSwitch>();
		pipeList= new ArrayList<Pipe>();
		setObstacleList(setUpEnvironment());
		switchList= setUpSwitches();
		
		player.setPlatforms(getObstacleList());
		levelComplete=false;
		dartgun = new Item(Color.GREEN, "Darts", "Dude, press Z and hit the bullseye.");
		darts= new ArrayList<Projectile>();
		player.setItem(dartgun);
		setLevelNumber(3);
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
		
		ArrayList<Obstacles> obstacleList= new ArrayList<Obstacles>();
		
		ArrayList<LineObject> platform1perimeter= new ArrayList<LineObject>();
		LineObject platform1Top= new LineObject(0, gameHeight-20, gameWidth, gameHeight-20);
		LineObject platform1SideL= new LineObject(0,gameHeight-20, 0, gameHeight);
		LineObject platform1Bottom= new LineObject(0, gameHeight, gameWidth, gameHeight);
		LineObject platform1SideR= new LineObject(gameWidth, gameHeight-20, gameWidth, gameHeight);
		
		platform1perimeter.add(platform1Top);
		platform1perimeter.add(platform1SideR);
		platform1perimeter.add(platform1Bottom);
		platform1perimeter.add(platform1SideL);
		
		Obstacles Platform1= new Obstacles(platform1perimeter);
		obstacleList.add(Platform1);
		
		
		ArrayList<LineObject> platform2perimeter= new ArrayList<LineObject>();
		LineObject platformTwoTop= new LineObject(gameWidth/5, (int)(pipelength+2*player.getRadius()+2), 2*gameWidth/5, (int)(pipelength+2*player.getRadius()+2));
		LineObject platformTwoSideL= new LineObject(platformTwoTop.getV1().getXCoord(),platformTwoTop.getV1().getYCoord(), platformTwoTop.getV1().getXCoord(), platformTwoTop.getV1().getYCoord() + 20);
		LineObject platformTwoBottom= new LineObject(platformTwoTop.getV1().getXCoord(), platformTwoTop.getV1().getYCoord() + 20, platformTwoTop.getV2().getXCoord(), platformTwoTop.getV2().getYCoord() + 20);
		LineObject platformTwoSideR= new LineObject(platformTwoTop.getV2().getXCoord(), platformTwoTop.getV2().getYCoord(), platformTwoTop.getV2().getXCoord(), platformTwoTop.getV2().getYCoord() + 20);
		
		platform2perimeter.add(platformTwoTop);
		platform2perimeter.add(platformTwoSideR);
		platform2perimeter.add(platformTwoBottom);
		platform2perimeter.add(platformTwoSideL);
		
		Obstacles Platform2= new Obstacles(platform2perimeter);
		//obstacleList.add(Platform2);
		
		Obstacles GenericPlatform = Platform2.translate(-gameWidth/5,-(int)(pipelength+2*player.getRadius()+2));
		
		
		Obstacles Platform3=  GenericPlatform.translate(pipelength + 20, gameHeight/3);
		obstacleList.add(Platform3);
		
		Obstacles Platform4 = Platform3.translate(gameWidth/5-20,-gameHeight/3+ gameHeight/2);
		obstacleList.add(Platform4);
	
		Obstacles Platform5= Platform3.translate(0, -gameHeight/3+ gameHeight/2 + gameHeight/8 );
		obstacleList.add(Platform5);
		
		
		//should be platform 6
		Obstacles Platform6= Platform2.translate(gameWidth*2/5, 0);
		obstacleList.add(Platform6);
		
		Obstacles Platform7= Platform6.translate(0, gameHeight/2);
		obstacleList.add(Platform7);
		
		
		ArrayList<LineObject> pipeBLeftPerim= new ArrayList<LineObject>();
		LineObject top1= new LineObject(10,0, 0,0);
		LineObject left1= new LineObject(top1.getV1().getXCoord(),top1.getV1().getYCoord(), 0,30);
		LineObject bottom1= new LineObject(0,30,30,30);
		LineObject right1= new LineObject(30,30,30,20);
		LineObject top2= new LineObject(10,20,30,20);
		LineObject right2= new LineObject(10,20,10,0);
		
		pipeBLeftPerim.add(top1);
		pipeBLeftPerim.add(left1);
		pipeBLeftPerim.add(bottom1);
		pipeBLeftPerim.add(right1);
		pipeBLeftPerim.add(top2);
		pipeBLeftPerim.add(right2);
		
		Obstacles pipeOne= new Obstacles(pipeBLeftPerim);
		PipeConnector pipeBLeft= new PipeConnector(pipeOne, gameWidth/2, gameHeight-20);
		
		pipeConnectors.add(pipeBLeft);
		
		
		
		
		/*
		 * The following are the obstacles for the pipeline
		 * 
		 * WARNING: coordinates are not correct. I am waiting to put in real values
		 * until I can see where they are, because I don't want to do that twice.
		 */
		
		
		
		
		
		
		
		
		
		
		
		return obstacleList;

	}
	
	
	/***
	 * This method contains code to allow the level-specific Item to have a certain 
	 * function, if one were to exist. 
	 */
	public void function() 
	{
		if(player.getItem()==null)
		{
			boolean nearItem= false;
			for(int i= 0; i< pipeConnectors.size(); i++)
			{
				PipeConnector p= pipeConnectors.get(i);
				double d1= (p.getStartX()-player.getCentX());
				double d2=(p.getStartY()-player.getCentY());
				double distance= Math.sqrt(d1*d1 + d2*d2);
				if(distance<3*player.getRadius())
				{
					player.setItem(p);
					pipeConnectors.remove(p);
					nearItem=true;
					break;
				}
			}
			if(nearItem==false)
			{
				for(int i= 0; i< pipeSwitches.size(); i++)
				{
					PipeConnectorSwitch p= pipeSwitches.get(i);
					double d1= (p.getCentX()-player.getCentX());
					double d2=(p.getCentY()-player.getCentY());
					double distance= Math.sqrt(d1*d1 + d2*d2);
					if(distance<2*player.getRadius())
					{
						if(p.getContacted())
						{
							PipeConnector pitem= new PipeConnector(p.getSymbol().getPerimeter(),0,0);
							player.setItem(pitem);
							p.changeContactStatus();
							break;
						}
					}
				}
			}
		}
		else
		{
			for(int i= 0; i< pipeSwitches.size(); i++)
			{
				PipeConnectorSwitch p= pipeSwitches.get(i);
				double d1= (p.getCentX()-player.getCentX());
				double d2=(p.getCentY()-player.getCentY());
				double distance= Math.sqrt(d1*d1 + d2*d2);
				if(distance<2*player.getRadius())
				{
					if(((PipeConnector)(player.getItem())).equals(p.getSymbol()))
					{
						p.changeContactStatus();
						player.usedItem();
					}
				}
				else
				{
					pipeConnectors.add((PipeConnector)(player.getItem()));
					pipeConnectors.get(pipeConnectors.size()-1).setStartX((int)(player.getCentX()));
					pipeConnectors.get(pipeConnectors.size()-1).setStartY((int)(player.getCentY()));
					player.usedItem();
					
				}
					break;
				
			}
		}
	}
	
	public ArrayList<Pipe> getPipeList()
	{
		return pipeList;
	}
	/***
	 * 
	 */
	@Override
	public void draw(Graphics2D g) 
	{
		
		
		int gameHeight = super.getGameHeight();
		int gameWidth = super.getGameWidth();
		
		//FontMetrics fm = g.getFontMetrics();
		Font f = new Font("Arial", Font.PLAIN, 20);
		g.setFont(f);
		g.drawString("Press 'Z' to fire darts at those white targets!", gameWidth / 17, gameHeight/10);
		
		if(pipeConnectors!=null)
		{
			for(int i=0; i< pipeConnectors.size(); i++)
			{
				g.setColor(Color.cyan);
				g.drawImage(pipeConnectors.get(i).getImage(),null, pipeConnectors.get(i).getStartX(), pipeConnectors.get(i).getStartY());
			}
		}
		if(pipeList!=null)
		{
			for(int i=0; i< pipeList.size(); i++)
			{
				getPipeList().get(i).checkFull();
				getPipeList().get(i).drawSwitch(g);
				if(getPipeList().get(i).getColor().equals(Color.cyan))
				{
			
				}
			}
		}
		if(pipeSwitches!=null)
		{
			for(int i= 0; i< getPipeSwitchList().size(); i++)
			{
				g.setColor(Color.white);
				getPipeSwitchList().get(i).drawSwitch(g);
			}
		}
		if(getSwitchList()!=null)
		{
			for(int i= 0; i< getSwitchList().size(); i++)
			{
				g.setColor(Color.green);
				if(i!=0)
					g.setColor(Color.white);
				getSwitchList().get(i).drawSwitch(g);
			}
		}
		if(darts!=null)
		{
			for(int i=0; i< darts.size(); i++)
			{
				Projectile d= darts.get(i);
				ArrayList<Switch> nearbySwitches= new ArrayList<Switch>();
				for(int j= 0; j<getSwitchList().size(); j++)
				{
					double dyi= d.getTopY();
					double dyf= d.getTopY()+ d.getHeight();
					LineObject side= getSwitchList().get(j).getOutlines().get(1);
					if((dyi>side.getV1().getYCoord() && dyi<side.getV2().getYCoord()) ||
							(dyf>side.getV2().getYCoord() && dyf<side.getV1().getYCoord()))
					{
						nearbySwitches.add(getSwitchList().get(j));
					}
				}
				boolean hit= false;
				int numHit=0;
				
				for(int j= 0; j<nearbySwitches.size(); j++)
				{
					double dxf= d.getTopX()+ d.getWidth() + d.getSpeedX();
					LineObject side1= nearbySwitches.get(j).getOutlines().get(1);
					LineObject side2= nearbySwitches.get(j).getOutlines().get(3);
					if((dxf>side2.getV1().getXCoord() && dxf<side1.getV1().getXCoord()))
					{
						
						hit=true;
						numHit=j;
						break;
						
					}
				}
				if(hit==true)
				{
					
					darts.remove(i);
					i--;
					nearbySwitches.get(numHit).changeContactStatus();
					hiddenRamps=fixObstacles();
					
				}
				else
				{
					if(d.getOrientation()=='r')
					{
						d.setTopX(d.getTopX()+d.getSpeedX());
					}
					else if(d.getOrientation()=='l')
					{
						d.setTopX(d.getTopX()-d.getSpeedX());
					}
					d.draw(g);
				}
				
			}
		}
		player.setHiddenPlatforms(hiddenRamps);
		if(hiddenRamps!=null)
		{
			for(int i= 0; i<hiddenRamps.size(); i++)
			{
				g.setColor(Color.magenta);
				Polygon p= new Polygon();
				for(int j=0; j<hiddenRamps.get(i).getVertices().size(); j++)
				{
					p.addPoint(hiddenRamps.get(i).getVertices().get(j).getXCoord(), hiddenRamps.get(i).getVertices().get(j).getYCoord());
				}
				if(p!=null)
				{
					g.drawPolygon(p);
				}
			}
		}
		if(getObstacleList()!=null)
		{
			for(int i= 0; i<getObstacleList().size(); i++)
			{
				g.setColor(Color.magenta);
				Polygon p= new Polygon();
				for(int j=0; j<getObstacleList().get(i).getVertices().size(); j++)
				{
					p.addPoint(getObstacleList().get(i).getVertices().get(j).getXCoord(), getObstacleList().get(i).getVertices().get(j).getYCoord());
				}
				if(p!=null)
				{
					g.drawPolygon(p);
				}
			}
			
		}
		
	}
	
	public ArrayList<Obstacles> fixObstacles()
	{
		int gameHeight = super.getGameHeight();
		int gameWidth = super.getGameWidth();
		ArrayList<Obstacles> hidden= new ArrayList<Obstacles>();
		
		if (switchList.get(1).getContacted() == true) 
		{
				ArrayList<LineObject> hidden1perimeter= new ArrayList<LineObject>();
				LineObject hidden1Top= new LineObject(300, gameHeight-4*gameHeight/5 , gameWidth-400, gameHeight-gameHeight/5-200);
				LineObject hidden1SideL= new LineObject(300,gameHeight-4*gameHeight/5, 300, gameHeight-4*gameHeight/5 + 5);
				LineObject hidden1Bottom= new LineObject(300, gameHeight-4*gameHeight/5 + 5, gameWidth-400, gameHeight-gameHeight/5 -200 + 5);
				LineObject hidden1SideR= new LineObject(gameWidth-400, gameHeight-gameHeight/5-200, gameWidth-400, gameHeight-gameHeight/5-200+5);
				
				System.out.println(hidden1Top.getOrientation());
				
				hidden1perimeter.add(hidden1Top);
				hidden1perimeter.add(hidden1SideR);
				hidden1perimeter.add(hidden1Bottom);
				hidden1perimeter.add(hidden1SideL);
				
				Obstacles hidden1= new Obstacles(hidden1perimeter);
				hidden.add(hidden1);
		
		}
		
		if (switchList.get(2).getContacted() == true) {
			
			ArrayList<LineObject> hidden2perimeter= new ArrayList<LineObject>();
			LineObject hidden2Top= new LineObject(100, gameHeight-(gameHeight/5), 600, gameHeight-2*gameHeight/5 + 50);
			LineObject hidden2SideL= new LineObject(100, gameHeight-(gameHeight/5), 100, gameHeight-(gameHeight/5) + 5);
			LineObject hidden2Bottom= new LineObject(100, gameHeight-(gameHeight/5) + 5, 600, gameHeight-2*gameHeight/5 + 55);
			LineObject hidden2SideR= new LineObject(600,gameHeight-2*gameHeight/5 + 50, 600, gameHeight-2*gameHeight/5 + 55);
			
			hidden2perimeter.add(hidden2Top);
			hidden2perimeter.add(hidden2SideR);
			hidden2perimeter.add(hidden2Bottom);
			hidden2perimeter.add(hidden2SideL);
			
			Obstacles hidden2= new Obstacles(hidden2perimeter);
			hidden.add(hidden2);
			
		}
		
		if (switchList.get(3).getContacted() == true) {
			
			ArrayList<LineObject> hidden3perimeter= new ArrayList<LineObject>();
			LineObject hidden3Top= new LineObject(600, gameHeight-2*gameHeight/5 + 50, 800, gameHeight-2*gameHeight/5 + 50);
			LineObject hidden3SideL= new LineObject(600, gameHeight-2*gameHeight/5 + 50, 600, gameHeight-2*gameHeight/5 + 50 + 5);
			LineObject hidden3Bottom= new LineObject(600, gameHeight-2*gameHeight/5 + 50 + 5, 800, gameHeight-2*gameHeight/5 + 50 + 5);
			LineObject hidden3SideR= new LineObject(800, gameHeight-2*gameHeight/5 + 50, 800, gameHeight-2*gameHeight/5 + 50 + 5);
			
			hidden3perimeter.add(hidden3Top);
			hidden3perimeter.add(hidden3SideR);
			hidden3perimeter.add(hidden3Bottom);
			hidden3perimeter.add(hidden3SideL);
			
			Obstacles hidden3= new Obstacles(hidden3perimeter);
			hidden.add(hidden3);
			
		}
		
		
		
		
		if (switchList.get(4).getContacted() == true) {
			
			ArrayList<LineObject> hidden4perimeter= new ArrayList<LineObject>();
			LineObject hidden4Top= new LineObject(250, gameHeight-2*gameHeight/5 - 100, 530, gameHeight-2*gameHeight/5 - 200 + 30);
			LineObject hidden4SideL= new LineObject(250,gameHeight-2*gameHeight/5 - 100, 250, gameHeight-2*gameHeight/5 - 100 + 5);
			LineObject hidden4Bottom= new LineObject(250, gameHeight-2*gameHeight/5 - 100 + 5, 530, gameHeight-2*gameHeight/5 - 200 + 5 + 30);
			LineObject hidden4SideR= new LineObject(530, gameHeight-2*gameHeight/5 - 200 + 30, 530, gameHeight-2*gameHeight/5 - 200 + 5 + 30);
			
			hidden4perimeter.add(hidden4Top);
			hidden4perimeter.add(hidden4SideR);
			hidden4perimeter.add(hidden4Bottom);
			hidden4perimeter.add(hidden4SideL);
			
			Obstacles hidden4= new Obstacles(hidden4perimeter);
			hidden.add(hidden4);
			
		}
		
		if (switchList.get(5).getContacted() == true) {
			
			ArrayList<LineObject> hidden5perimeter= new ArrayList<LineObject>();
			LineObject hidden5Top= new LineObject(620, gameHeight-2*gameHeight/5 - 210, gameWidth - 300, gameHeight-3*gameHeight/5 - 150);
			LineObject hidden5SideL= new LineObject(620, gameHeight-2*gameHeight/5 - 210, 620, gameHeight-2*gameHeight/5 - 210 +5);
			LineObject hidden5Bottom= new LineObject(620, gameHeight-2*gameHeight/5 - 210 + 5, gameWidth - 300, gameHeight-3*gameHeight/5 - 150 + 5);
			LineObject hidden5SideR= new LineObject(gameWidth - 300, gameHeight-3*gameHeight/5 - 150, gameWidth - 300, gameHeight-3*gameHeight/5 - 150 + 5);
			
			hidden5perimeter.add(hidden5Top);
			hidden5perimeter.add(hidden5SideR);
			hidden5perimeter.add(hidden5Bottom);
			hidden5perimeter.add(hidden5SideL);
			
			Obstacles hidden5= new Obstacles(hidden5perimeter);
			hidden.add(hidden5);
			
		}
		
	
		return hidden;
	}
	
	public ArrayList<Projectile> getDarts()
	{
		return darts;
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
	public ArrayList<PipeConnectorSwitch> getPipeSwitchList()
	{
		return pipeSwitches;
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
		int gameHeight= super.getGameHeight();
		// end goal switch is always first
		LineObject Switch1Top= new LineObject(0,0 , 50, 0);
		LineObject Switch1SideL= new LineObject(0, 0, 0, 50);
		LineObject Switch1Bottom= new LineObject(0,50, 50, 50);
		LineObject Switch1SideR= new LineObject(50,50, 50, 0);
		
		switch1.add(Switch1Top);
		switch1.add(Switch1SideR);
		switch1.add(Switch1Bottom);
		switch1.add(Switch1SideL);

		Switch sgeneric = new Switch(switch1);
		Switch Switcher1= new Switch(sgeneric.translate(1000, gameHeight-50).getOutlines());
		switches.add(Switcher1);
		
		
		// This level has a bunch of switches, so here they are, after the end goal.
		// There should be six extra switches in this level, making seven total.
		
		//New note: I'm ordering the switches in the order they should ideally be 
		// hit, while playing this level, instead of by position. Makes more sense to me,
		// hope that's ok.
			PipeConnectorSwitch pBLSwitch= new PipeConnectorSwitch((sgeneric.translate(0,gameHeight-20-50)).getOutlines(), pipeConnectors.get(0));
			pipeSwitches.add(pBLSwitch);
			
		Pipe p1= new Pipe(pBLSwitch.translate(50,0).getOutlines());
		p1.getPipeSwitches().add(pBLSwitch);
		
	// new switch, actual switch 3 if numbered correctly
		
		ArrayList<LineObject> switch3 = new ArrayList<LineObject>();

		
		LineObject Switch3Top= new LineObject(250 + 2*x - y,super.getGameHeight()/2 -3*y, 250+2*x, super.getGameHeight()/2 -3*y);
		LineObject Switch3SideL= new LineObject(250 + 2*x - y, super.getGameHeight()/2 -3*y, 2*x+ 250 - y, super.getGameHeight()/2 - 2*y);
		LineObject Switch3Bottom= new LineObject(250 + 2*x - y,super.getGameHeight()/2 - 2*y, 250+2*x, super.getGameHeight()/2 - 2*y);
		LineObject Switch3SideR= new LineObject(250+2*x,super.getGameHeight()/2 -3*y, 250+2*x, super.getGameHeight()/2 - 2*y);
		
		switch3.add(Switch3Top);
		switch3.add(Switch3SideR);
		switch3.add(Switch3Bottom);
		switch3.add(Switch3SideL);

		Switch s3 = new Switch(switch3);

		switches.add(s3);
		
		
	// new switch, should be #4
		
		ArrayList<LineObject> switch4 = new ArrayList<LineObject>();

				
		LineObject Switch4Top= new LineObject(250 + 2*x - y,super.getGameHeight()/2, 250+2*x, super.getGameHeight()/2);
		LineObject Switch4SideL= new LineObject(250 + 2*x - y, super.getGameHeight()/2 , 2*x+ 250 - y, super.getGameHeight()/2 - y);
		LineObject Switch4Bottom= new LineObject(250 + 2*x - y,super.getGameHeight()/2 - y, 250+2*x, super.getGameHeight()/2 - y);
		LineObject Switch4SideR= new LineObject(250+2*x,super.getGameHeight()/2, 250+2*x, super.getGameHeight()/2 - y);
				
		switch4.add(Switch4Top);
		switch4.add(Switch4SideR);
		switch4.add(Switch4Bottom);
		switch4.add(Switch4SideL);

		Switch s4 = new Switch(switch4);

		switches.add(s4);
			
		
	
		// new switch, should be #5
		
		ArrayList<LineObject> switch5 = new ArrayList<LineObject>();

				
		LineObject Switch5Top= new LineObject(20,super.getGameHeight()/2 +125, y+20, super.getGameHeight()/2 +125);
		LineObject Switch5SideL= new LineObject(20, super.getGameHeight()/2 +125, 20, super.getGameHeight()/2 +175);
		LineObject Switch5Bottom= new LineObject(20,super.getGameHeight()/2 +175, y+20, super.getGameHeight()/2 +175);
		LineObject Switch5SideR= new LineObject(y+20,super.getGameHeight()/2 +125, y+20, super.getGameHeight()/2 +175);
		
		switch5.add(Switch5Top);
		switch5.add(Switch5SideR);
		switch5.add(Switch5Bottom);
		switch5.add(Switch5SideL);

		Switch s5 = new Switch(switch5);

		switches.add(s5);
		
		
		// new switch, should be #6
		
		ArrayList<LineObject> switch6 = new ArrayList<LineObject>();

		
		LineObject Switch6Top= new LineObject(20,super.getGameHeight()/2 - y +20, y+20, super.getGameHeight()/2 - y +20);
		LineObject Switch6SideL= new LineObject(20, super.getGameHeight()/2 -  y +20, 20, super.getGameHeight()/2 +20);
		LineObject Switch6Bottom= new LineObject(20,super.getGameHeight()/2 +20, y+20, super.getGameHeight()/2 +20);
		LineObject Switch6SideR= new LineObject(y+20,super.getGameHeight()/2 - y +20, y+20, super.getGameHeight()/2 +20);
		
		switch6.add(Switch6Top);
		switch6.add(Switch6SideR);
		switch6.add(Switch6Bottom);
		switch6.add(Switch6SideL);

		Switch s6 = new Switch(switch6);

		switches.add(s6);
		
		
		
		return switches;
	}
	
	
	
	
	
}