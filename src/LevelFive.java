import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;


public class LevelFive extends Level {
	
	
	private boolean reset;
	private Player player;
	private int WALL_SIZE=3;
	ArrayList<Switch> switchList;
	private boolean levelComplete;
	private ArrayList<Rectangle> water = new ArrayList<Rectangle>();
	private int timer;
	private int amountWater;
	
	
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
		player.setCentY(0);
		setLevelNumber(5); //ALWAYS SET THIS CORRECTLY
		reset=false;
		timer = 0;
		amountWater = 0;
		switchList= setUpSwitches();
		setObstacleList(setUpEnvironment());
		player.setPlatforms(getObstacleList());
		levelComplete=false;
		

		for (int y = height; y > 0; y=y-2) {
			
			Rectangle rect = new Rectangle(0, y, width, 2);
			System.out.println(0 + " " +  y + " " + width + " " + 2);

			
			water.add(rect);
		}

		
		
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
		
		ArrayList<LineObject> platform1perimeter= new ArrayList<LineObject>();
		LineObject platform1Top= new LineObject(0, gameHeight-4*gameHeight/5, gameWidth/5, gameHeight-4*gameHeight/5);
		LineObject platform1SideL= new LineObject(0,gameHeight-4*gameHeight/5, 0, gameHeight-4*gameHeight/5 + 20);
		LineObject platform1Bottom= new LineObject(0, gameHeight-4*gameHeight/5 + 20, gameWidth/5, gameHeight-4*gameHeight/5 + 20);
		LineObject platform1SideR= new LineObject(gameWidth/5, gameHeight-4*gameHeight/5, gameWidth/5, gameHeight-4*gameHeight/5 + 20);
		
		platform1perimeter.add(platform1Top);
		platform1perimeter.add(platform1SideR);
		platform1perimeter.add(platform1Bottom);
		platform1perimeter.add(platform1SideL);
		
		Obstacles Platform1= new Obstacles(platform1perimeter);
	//	obstacleList.add(Platform1);
		
		
		Obstacles Platform2 = Platform1.translate(0, 700);
		obstacleList.add(Platform2);
		
		Obstacles Platform3 = Platform1.translate(700, 0);
		obstacleList.add(Platform3);
		
		Obstacles Platform4 = Platform1.translate(0, 70);
	//	obstacleList.add(Platform4);
		
		Obstacles Platform5 = Platform1.translate(350, 70);
		obstacleList.add(Platform5);
		
		Obstacles Platform6 = Platform1.translate(700, 70);
		obstacleList.add(Platform6);
		
	//	Obstacles Platform7 = Platform1.translate(0, 140);
	//	obstacleList.add(Platform7);
		
		Obstacles Platform8 = Platform1.translate(350, 140);
		obstacleList.add(Platform8);
		
		Obstacles Platform9 = Platform1.translate(700, 140);
		obstacleList.add(Platform9);	
		
//		Obstacles Platform10 = Platform1.translate(0, 210);
//		obstacleList.add(Platform10);
		
//		Obstacles Platform11 = Platform1.translate(350, 210);
//		obstacleList.add(Platform11);
		
		Obstacles Platform12 = Platform1.translate(700, 210);
		obstacleList.add(Platform12);
		
//		Obstacles Platform13 = Platform1.translate(0, 280);
//		obstacleList.add(Platform13);
		
		Obstacles Platform14 = Platform1.translate(350, 280);
		obstacleList.add(Platform14);
		
		Obstacles Platform15 = Platform1.translate(700, 280);
		obstacleList.add(Platform15);
		
//		Obstacles Platform16 = Platform1.translate(0, 350);
//		obstacleList.add(Platform16);
		
//		Obstacles Platform17 = Platform1.translate(0, 420);
//		obstacleList.add(Platform17);		
		
		Obstacles Platform18 = Platform1.translate(700, 420);
		obstacleList.add(Platform18);
		
		Obstacles Platform19 = Platform1.translate(350, 350);
		obstacleList.add(Platform19);

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
	 * function, if one were to exist. Nothing in this level, so far.
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
		
		
		ArrayList<Switch> switches= new ArrayList<Switch>();

		
		ArrayList<LineObject> switch1 = new ArrayList<LineObject>();
		
		int x= 500;
		int y=50;
		
		int gameHeight = super.getGameHeight();
		int gameWidth = super.getGameWidth();
		
		// end goal switch is always first
		LineObject Switch1Top= new LineObject(gameWidth-100, gameHeight - 15*(gameHeight/16), gameWidth-50, gameHeight - 15*(gameHeight/16));
		LineObject Switch1SideL= new LineObject(gameWidth-100, gameHeight - 15*(gameHeight/16), gameWidth-100, gameHeight - 15*(gameHeight/16) + 50);
		LineObject Switch1Bottom= new LineObject(gameWidth-100, gameHeight - 15*(gameHeight/16) + 50, gameWidth-50, gameHeight - 15*(gameHeight/16) + 50);
		LineObject Switch1SideR= new LineObject(gameWidth-50, gameHeight - 15*(gameHeight/16), gameWidth-50, gameHeight - 15*(gameHeight/16) + 50);
		
		switch1.add(Switch1Top);
		switch1.add(Switch1SideR);
		switch1.add(Switch1Bottom);
		switch1.add(Switch1SideL);

		Switch s = new Switch(switch1);
		
		switches.add(s);
		
		return switches;
	}

	
	public void draw(Graphics2D g) 
	{
	
		timer++;
		int gameHeight = super.getGameHeight();
		int gameWidth = super.getGameWidth();
	
		//FontMetrics fm = g.getFontMetrics();
		Font f = new Font("Arial", Font.PLAIN, 20);
		g.setFont(f);
		g.drawString("Don't let the water touch you! Get to higher ground.", gameWidth / 17, gameHeight/10);
	
		
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
	
		for (int x = 0; x < amountWater; x++) {
			g.fill(water.get(x).getBounds2D());
		}
		
		
		
		
		
		
		
		if(getSwitchList()!=null)
		{
			for(int i= 0; i< getSwitchList().size(); i++)
			{
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
	
	
	public void manageWater() {
		if (timer/5 == ((double)timer)/5.0) {
			amountWater++;
		}
	}
		
		
	
	
	
	
}