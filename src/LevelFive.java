import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class LevelFive extends Level {
	
	
	private boolean reset;
	private Player player;
	private int WALL_SIZE=3;
	ArrayList<Switch> switchList;
	private boolean levelComplete;
	private ArrayList<Rectangle> water = new ArrayList<Rectangle>();
	private int timer;
	private int amountWater;
	private int breath;
	private LifePreserver LP1;
	private BufferedImage background;

	
	/***
	 * This is level five, which features the player trying to get above the rising water in the set amount of 
	 * time in order to get to the goal.
	 * @author Rebecca Naimon
	 *
	 */
	public LevelFive(Player p, int width, int height)
	{
		super(p, width, height);
		player=p;
		player.setCentX(0);
		player.setCentY(getGameHeight()-300);
		setLevelNumber(5);
		reset=false;
		timer = 0;
		amountWater = 0;
		switchList= setUpSwitches();
		setObstacleList(setUpEnvironment());
		player.setPlatforms(getObstacleList());
		levelComplete=false;
		LP1 = new LifePreserver(super.getGameWidth()-200, super.getGameHeight() - amountWater*2, 3);
		breath = 4000;
		for (int y = height; y > 0; y=y-2) 
		{
			
			Rectangle rect = new Rectangle(0, y, width, 2);
			
			water.add(rect);
		}
		
		try {
			background = ImageIO.read(new File("sky-streak-background.jpg"));

		}
		catch (IOException ex) {
			System.out.println("Nope.");
		}
		
	

		
		
	}
	
	/***
	 * Returns the background for the level
	 * @return the background buffered image
	 */
	public BufferedImage getBackground() {
		return background;
	}
	
	
	/***
	 * This method sets up the level environment of Obstacles (like platforms). 
	 * @return an ArrayList of Obstacles in this specific level
	 */
	public ArrayList<Obstacles> setUpEnvironment()
	{
		
		int gameHeight = super.getGameHeight();
		int gameWidth = super.getGameWidth();
		
		ArrayList<Obstacles> obstacleList= new ArrayList<Obstacles>();
		
		/*
		 * Currently, we're pretending the platform arrangement is the same as in level 3, but
		 * that will change.
		 */
		
		ArrayList<LineObject> platformbperimeter= new ArrayList<LineObject>();
		LineObject platform1Topb= new LineObject(0, gameHeight-20, gameWidth, gameHeight-20);
		LineObject platform1SideLb= new LineObject(0,gameHeight-20, 0, gameHeight);
		LineObject platform1Bottomb= new LineObject(0, gameHeight, gameWidth, gameHeight);
		LineObject platform1SideRb= new LineObject(gameWidth, gameHeight-20, gameWidth, gameHeight);
		
		platformbperimeter.add(platform1Topb);
		platformbperimeter.add(platform1SideRb);
		platformbperimeter.add(platform1Bottomb);
		platformbperimeter.add(platform1SideLb);
		
		Obstacles Platformb1= new Obstacles(platformbperimeter);
		
		obstacleList.add(Platformb1);
		
		ArrayList<LineObject> platform1perimeter= new ArrayList<LineObject>();
		LineObject platform1Top= new LineObject(0, 0, gameWidth/5, 0);
		LineObject platform1SideL= new LineObject(0,0, 0, 20);
		LineObject platform1Bottom= new LineObject(0, 20, gameWidth/5, 20);
		LineObject platform1SideR= new LineObject(gameWidth/5, 0, gameWidth/5, 20);
		
		platform1perimeter.add(platform1Top);
		platform1perimeter.add(platform1SideR);
		platform1perimeter.add(platform1Bottom);
		platform1perimeter.add(platform1SideL);
		
		Obstacles Platform1= new Obstacles(platform1perimeter);
		//obstacleList.add(Platform1);
		
		
		Obstacles Platform2 = Platform1.translate(gameWidth/5, (int)(gameHeight*((18.0/20.0))));
		obstacleList.add(Platform2);
		
		Obstacles Platform3 = Platform2.translate(gameWidth/6 + gameWidth/10, -(int)(gameHeight*((1.0/20.0))));
		obstacleList.add(Platform3);
		
		
		Obstacles Platform4 = Platform3.translate(gameWidth/6 + gameWidth/10, -(int)(gameHeight*((1.0/20.0))));
		obstacleList.add(Platform4);
		
		Obstacles Platform5 = Platform4.translate(gameWidth/15, -(int)(gameHeight*((1.0/10.0))));
		obstacleList.add(Platform5);
		
		Obstacles Platform6 = Platform5.translate(-gameWidth/14- gameWidth/6 - gameWidth/11, -(int)(gameHeight*((1.0/10.0))));
		obstacleList.add(Platform6);
		
		Obstacles Platform7 = Platform6.translate(- gameWidth/6 - gameWidth/10, -(int)(gameHeight*((1.0/20.0))));
		obstacleList.add(Platform7);
		
		
		Obstacles Platform7b = Platform7.translate(- gameWidth/6 - gameWidth/10, -(int)(gameHeight*((1.0/20.0))));
		obstacleList.add(Platform7b);
		
		Obstacles Platform8 = Platform7b.translate(gameWidth/15, -(int)(gameHeight*((1.0/10.0))));
		obstacleList.add(Platform8);
		
		Obstacles Platform9 = Platform8.translate(gameWidth/6 + gameWidth/10, -(int)(gameHeight*((1.0/20.0))));
		obstacleList.add(Platform9);	
		
		
		Obstacles Platform11 = Platform9.translate(gameWidth/6 + gameWidth/10, -(int)(gameHeight*((1.0/20.0))));
		obstacleList.add(Platform11);
		
		return obstacleList;

	}
	
	/***
	 * method to determine if the level should reset
	 * @return the boolean of whether the level needs to be reset
	 */
	
	public boolean shouldReset() {
		return reset;
	}
	
	
	/***
	 * This method contains code to allow the level-specific Item to have a certain 
	 * function, if one were to exist. Nothing in this level
	 */
	public void function() {
		

	}
	
	
	/***
	 * Checks whether the level is complete, based on whether the switch at the 
	 * end of the level has been touched.
	 * @return whether the level is complete or not
	 */
	public boolean checkComplete()
	{
		if(getLevelComplete()==true)
		{
			return true;
		}
		else
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
	}
	
	

	
	public void draw(Graphics2D g) 
	{
	
		timer++;
		int gameHeight = super.getGameHeight();
		int gameWidth = super.getGameWidth();
	
		//FontMetrics fm = g.getFontMetrics();
		Font f = new Font("Arial", Font.PLAIN, 20);
		g.setFont(f);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Don't let the water drown you! Get to higher ground. And be careful, it's slippery out there.", gameWidth / 17, gameHeight/10);
	
		
		if(getObstacleList()!=null)
		{
			for(int i= 0; i<getObstacleList().size(); i++)
			{
				/*
				int[] vertx= new int[thingsInLevel.get(i).getVertices().size()];
				int[] verty= new int[thingsInLevel.get(i).getVertices().size()];
				for(int j=0; j< thingsInLevel.get(i).getVertices().size(); j++)
				{
					vertx[j]=(int)(thingsInLevel.get(i).getVertices().get(j).getXCoord());
					verty[j]=(int)(thingsInLevel.get(i).getVertices().get(j).getYCoord());
				}
				g.fillPolygon(vertx, verty, thingsInLevel.get(i).getVertices().size());
				*/
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
		
		Color waterC = new Color(0, 200, 250, 100);
		
		g.setColor(waterC);
		
		manageWater();
	
		for (int x = 0; x < amountWater; x++) 
		{
			g.fill(water.get(x).getBounds2D());
		}
		g.drawImage(LP1.getImage(), (int)LP1.getX(), (int)LP1.getY(), null);
		
		if(player.getCentX() - 4 > LP1.getX() && player.getCentX() + 4 < LP1.getX() + 100 && player.getCentY() + player.getRadius() < LP1.getY() + 30 && player.getCentY() + player.getRadius() > LP1.getY() +10) 
		{
			player.setGrounded(true);
		}
		else
			player.setGrounded(false);
		
		
		
		LP1.doMove(gameHeight - amountWater*2);
		
		
		if(touchingWater()) {
			Font f2 = new Font("Arial", Font.PLAIN, 50);
			g.setFont(f2);
			g.setColor(Color.red);
			int countdown = breath/1000;

			
			String countString = "" + countdown;
			if (countdown >= 0 && countdown <=3)
				g.drawString(countString, (int)player.getCentX(), (int)(player.getCentY() - 25));
			
			if (countdown < 0) {
				reset = true;
			}
			
			
			breath = breath - 40;
			
		}
		else
			breath = 4000;	
		
		if(getSwitchList()!=null)
		{
			for(int i= 0; i< getSwitchList().size(); i++)
			{
				System.out.println("here");
				g.setColor(Color.green);
				if(i!=0)
				g.setColor(Color.white);
				int[] vertx= new int[getSwitchList().get(i).getVertices().size()];
				int[] verty= new int[getSwitchList().get(i).getVertices().size()];
				for(int j=0; j< getSwitchList().get(i).getVertices().size(); j++)
				{
					vertx[j]=(int)(getSwitchList().get(i).getVertices().get(j).getXCoord());
					verty[j]=(int)(getSwitchList().get(i).getVertices().get(j).getYCoord());
				}
				g.fillPolygon(vertx, verty, (getSwitchList().get(i).getVertices().size()));
			}	
		}
	}
	
	/***
	 * This method manages the amount of water that should be filling up the level.
	 */
	public void manageWater() 
	{
		if (timer/5 == ((double)timer)/5.0) {
			amountWater++;
		}
	}
	public ArrayList<Switch> getSwitchList()
	{
		return switchList;
	}
	
	public ArrayList<Switch> setUpSwitches() 
	{
		
		
		ArrayList<Switch> switches= new ArrayList<Switch>();

		
		ArrayList<LineObject> switch1 = new ArrayList<LineObject>();
		
		int x= 500;
		int y=50;
		LineObject Switch1Top= new LineObject(250 + 2*x - y,super.getGameHeight()/6 - 2*y , 250+2*x, super.getGameHeight()/6 - 2*y);
		LineObject Switch1SideL= new LineObject(250 + 2*x - y, super.getGameHeight()/6 - 2* y, 2*x+ 250 - y, super.getGameHeight()/6 - y);
		LineObject Switch1Bottom= new LineObject(250 + 2*x - y,super.getGameHeight()/6 - y, 250+2*x, super.getGameHeight()/6 - y);
		LineObject Switch1SideR= new LineObject(250+2*x,super.getGameHeight()/6 - 2*y, 250+2*x, super.getGameHeight()/6 - y);
		
		switch1.add(Switch1Top);
		switch1.add(Switch1SideR);
		switch1.add(Switch1Bottom);
		switch1.add(Switch1SideL);

		Switch s = new Switch(switch1);
		s= new Switch(s.translate(-105, 25).getOutlines());
		switches.add(s);
		return switches;
		
	}
	/***
	 * This method determines whether the player is in the water
	 * @param p is the Player
	 * @return whether or not the player is in the water
	 */
	public boolean touchingWater() {
		
		int pY = (int)player.getCentY();
		int pY2 = super.getGameHeight() - pY;
		
		int pWater = amountWater *2;		
		if (pY2 - player.getRadius() < pWater) {
			return true;
		}
		return false;
		
	}
		
		
	
	
	
	
}