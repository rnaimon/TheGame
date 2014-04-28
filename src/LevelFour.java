import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;




public class LevelFour extends Level {
	
	
	private boolean reset;
	private Player player;
	private int WALL_SIZE=3;
	private Item plasmaGun;
	private ArrayList<Projectile> plasma;
	ArrayList<Switch> switchList;
	private boolean levelComplete;
	private ArrayList<Enemy> enemies;
	
	/***
	 * This is level four, which features the player shooting at anti-virus enemies in order to
	 * get to the final goal.
	 * @author Rebecca Naimon
	 *
	 */
	public LevelFour(Player p, int width, int height)
	{
		super(p, width, height);
		player=p;
		player.setCentX(0);
		player.setCentY(0);
		setLevelNumber(4);
		reset=false;
		switchList= setUpSwitches();
		setObstacleList(setUpEnvironment());
		player.setPlatforms(getObstacleList());
		levelComplete=false;
		
		enemies = new ArrayList<Enemy>();
		setUpEnemies();
		
		plasmaGun = new Item(Color.LIGHT_GRAY, "Plasma Gun", "Dude, press Z and hit the anti-virus.");
		plasma = new ArrayList<Projectile>();
		player.setItem(plasmaGun);
		
		
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
		obstacleList.add(Platform1);
		
		
		ArrayList<LineObject> platform2perimeter= new ArrayList<LineObject>();
		LineObject platformTwoTop= new LineObject(0, gameHeight-2*gameHeight/5 - 40, gameWidth/6, gameHeight-2*gameHeight/5 - 40);
		LineObject platformTwoSideL= new LineObject(0,gameHeight-2*gameHeight/5 - 40, 0, gameHeight-2*gameHeight/5 + 20 - 40);
		LineObject platformTwoBottom= new LineObject(0, gameHeight-2*gameHeight/5 + 20 - 40, gameWidth/6, gameHeight-2*gameHeight/5 + 20- 40);
		LineObject platformTwoSideR= new LineObject(gameWidth/6, gameHeight-2*gameHeight/5 - 40, gameWidth/6, gameHeight-2*gameHeight/5 + 20- 40);
		
		platform2perimeter.add(platformTwoTop);
		platform2perimeter.add(platformTwoSideR);
		platform2perimeter.add(platformTwoBottom);
		platform2perimeter.add(platformTwoSideL);
		
		Obstacles Platform2= new Obstacles(platform2perimeter);
		obstacleList.add(Platform2);
		
		ArrayList<LineObject> platform3perimeter= new ArrayList<LineObject>();
		LineObject platform3Top= new LineObject(0,super.getGameHeight()-super.getGameHeight()/5, gameWidth/8, super.getGameHeight()-super.getGameHeight()/5);
		LineObject platform3SideL= new LineObject(0,super.getGameHeight()-super.getGameHeight()/5, 0, super.getGameHeight()-super.getGameHeight()/5 + 20);
		LineObject platform3Bottom= new LineObject(0, super.getGameHeight()-super.getGameHeight()/5 + 20, gameWidth/8, super.getGameHeight()-super.getGameHeight()/5 + 20);
		LineObject platform3SideR= new LineObject(gameWidth/8, super.getGameHeight()-super.getGameHeight()/5, gameWidth/8, super.getGameHeight()-super.getGameHeight()/5 + 20);
		
		platform3perimeter.add(platform3Top);
		platform3perimeter.add(platform3SideR);
		platform3perimeter.add(platform3Bottom);
		platform3perimeter.add(platform3SideL);
		
		Obstacles Platform3= new Obstacles(platform3perimeter);
		
		
		obstacleList.add(Platform3);
		
		
		
		
		
		
		//Now for the platforms on the right side
		
		// should be #4, now is
		ArrayList<LineObject> platform4perimeter= new ArrayList<LineObject>();
		LineObject platformFourTop= new LineObject(gameWidth-(gameWidth/5), gameHeight-4*gameHeight/5, gameWidth, gameHeight-4*gameHeight/5);
		LineObject platformFourSideL= new LineObject(gameWidth-(gameWidth/5),gameHeight-4*gameHeight/5, gameWidth-(gameWidth/5), gameHeight-4*gameHeight/5 + 20);
		LineObject platformFourBottom= new LineObject(gameWidth-(gameWidth/5), gameHeight-4*gameHeight/5 + 20, gameWidth, gameHeight-4*gameHeight/5 + 20);
		LineObject platformFourSideR= new LineObject(gameWidth,gameHeight-4*gameHeight/5, gameWidth, gameHeight-4*gameHeight/5 + 20);
		
		platform4perimeter.add(platformFourTop);
		platform4perimeter.add(platformFourSideR);
		platform4perimeter.add(platformFourBottom);
		platform4perimeter.add(platformFourSideL);
		
		Obstacles Platform4= new Obstacles(platform4perimeter);
		obstacleList.add(Platform4);
	
		
		//this one's number is correct
		ArrayList<LineObject> platform5perimeter= new ArrayList<LineObject>();
		LineObject platformFiveTop= new LineObject(gameWidth-(gameWidth/5), gameHeight-3*gameHeight/5, gameWidth, gameHeight-3*gameHeight/5);
		LineObject platformFiveSideL= new LineObject(gameWidth-(gameWidth/5), gameHeight-3*gameHeight/5, gameWidth-(gameWidth/5), gameHeight-3*gameHeight/5 + 20);
		LineObject platformFiveBottom= new LineObject(gameWidth-(gameWidth/5), gameHeight-3*gameHeight/5 + 20, gameWidth, gameHeight-3*gameHeight/5 + 20);
		LineObject platformFiveSideR= new LineObject(gameWidth,gameHeight-3*gameHeight/5, gameWidth, gameHeight-3*gameHeight/5 + 20);
		
		platform5perimeter.add(platformFiveTop);
		platform5perimeter.add(platformFiveSideR);
		platform5perimeter.add(platformFiveBottom);
		platform5perimeter.add(platformFiveSideL);
		
		Obstacles Platform5= new Obstacles(platform5perimeter);
		obstacleList.add(Platform5);
		
	
		
		//should be platform 6
		ArrayList<LineObject> platform6perimeter= new ArrayList<LineObject>();
		LineObject platformSixTop= new LineObject(gameWidth-(gameWidth/4), gameHeight-2*gameHeight/5, gameWidth, gameHeight-2*gameHeight/5);
		LineObject platformSixSideL= new LineObject(gameWidth-(gameWidth/4),gameHeight-2*gameHeight/5, gameWidth-(gameWidth/4), gameHeight-2*gameHeight/5 + 20);
		LineObject platformSixBottom= new LineObject(gameWidth-(gameWidth/4), gameHeight-2*gameHeight/5 + 20, gameWidth, gameHeight-2*gameHeight/5 + 20);
		LineObject platformSixSideR= new LineObject(gameWidth, gameHeight-2*gameHeight/5, gameWidth, gameHeight-2*gameHeight/5 + 20);
		
		platform6perimeter.add(platformSixTop);
		platform6perimeter.add(platformSixSideR);
		platform6perimeter.add(platformSixBottom);
		platform6perimeter.add(platformSixSideL);
			
		Obstacles Platform6= new Obstacles(platform6perimeter);
		obstacleList.add(Platform6);
		
		
		
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
	 * function, if one were to exist. In this case, it's plasma.
	 */
	public void function() {
		
	//changed projectile speed to be faster (5th input)
		Projectile shot = new Projectile(player.getCentX() + player.getRadius(), player.getCentY(), 7, 4, 8, 1.5, player.getOrientation(), false);
		plasma.add(shot);
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
		
		// end goal switch is always first
		LineObject Switch1Top= new LineObject(250 + 2*x - y,super.getGameHeight()/6 - 2*y , 250+2*x, super.getGameHeight()/6 - 2*y);
		LineObject Switch1SideL= new LineObject(250 + 2*x - y, super.getGameHeight()/6 - 2* y, 2*x+ 250 - y, super.getGameHeight()/6 - y);
		LineObject Switch1Bottom= new LineObject(250 + 2*x - y,super.getGameHeight()/6 - y, 250+2*x, super.getGameHeight()/6 - y);
		LineObject Switch1SideR= new LineObject(250+2*x,super.getGameHeight()/6 - 2*y, 250+2*x, super.getGameHeight()/6 - y);
		
		switch1.add(Switch1Top);
		switch1.add(Switch1SideR);
		switch1.add(Switch1Bottom);
		switch1.add(Switch1SideL);

		Switch s = new Switch(switch1);
		
		switches.add(s);
		
		return switches;
	}

	/***
	 * 
	 */
	public void draw(Graphics2D g) 
	{
	
	
		int gameHeight = super.getGameHeight();
		int gameWidth = super.getGameWidth();
	
		//FontMetrics fm = g.getFontMetrics();
		Font f = new Font("Arial", Font.PLAIN, 20);
		g.setFont(f);
		g.drawString("Press 'Z' to fire plasma at those anti-virus enemies!", gameWidth / 17, gameHeight/10);
	
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
		
		
		if(plasma!=null)
		{
			for(int i=0; i< plasma.size(); i++)
			{
				Projectile p= plasma.get(i);
				/*
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
				*/
				boolean hit= false;
				int numHit=0;
				
				for(int j= 0; j<enemies.size(); j++)
				{
					Enemy e = enemies.get(j);
					
				//	double dxf= p.getTopX()+ p.getWidth() + p.getSpeedX();
					double dCenter = Math.sqrt(Math.pow(e.getCentX()-p.getCentX(), 2) + Math.pow(e.getCentY()-p.getCentY(), 2));
					if (e.getRadius()+p.getRadius() >= dCenter) {
						
						hit=true;
						numHit=j;
						break;
						
					}
					
				}
				if(hit==true)
				{
					
					plasma.remove(i);
					i--;
					enemies.get(numHit).setAlive(false);
					//hiddenRamps=fixObstacles();
					
				}
				else
				{
					if(p.getOrientation()=='r')
					{
						p.setCentX(p.getCentX()+p.getSpeedX());
					}
					else if(p.getOrientation()=='l')
					{
						p.setCentX(p.getCentX()-p.getSpeedX());
					}
					g.setColor(Color.white);
					p.draw(g);
				}
				
			}
		}
		
		
		
		if(enemies != null) {
			manageEnemies();
			if (shouldReset() == false) {
			
				
				//System.out.println(enemies.size());
				for (int x = 0; x < enemies.size(); x++) {
					g.setColor(Color.red);
					g.fillOval((int)enemies.get(x).getCentX(), (int)enemies.get(x).getCentY(), (int)enemies.get(x).getRadius(), (int)enemies.get(x).getRadius());
					g.setColor(Color.black);
					g.drawOval((int)enemies.get(x).getCentX(), (int)enemies.get(x).getCentY(), (int)enemies.get(x).getRadius(), (int)enemies.get(x).getRadius());
				}
				if (enemies.size() < 10) {
					setUpEnemies();
				}
				//setUpEnemies();
		
		}
			
		}
		
		
	}
	
	/***
	 * This method creates the initial enemies that populate the level as it begins.
	 */
	public void setUpEnemies() {
		
		int numEnemies = (int)(Math.random()*6 + 1);
			
		for (int x = 0; x < numEnemies; x++) {
			createEnemy();
		}
		
	}
	
	
	/***
	 * This method generally keeps track of the enemies on the screen, checking if they need to be
	 * removed if hit by a dart or plasma blast, and allowing them to move. It will also occasionally
	 * make new enemies.
	 */
	public void manageEnemies() {
		
		int numEnemies = enemies.size();
		
		for (int x = 0; x < numEnemies; x++) {
			
			Enemy e = enemies.get(x);
			System.out.println("here");

			if (e.getAlive() == false) {
				System.out.println("here1");

				//get rid of enemy
			}
			else if (enemyTouching(e, player)) {
				System.out.println("here2");
				reset = true;
			}
			else {
				System.out.println("here3");
				e.doMove(player);
				
			}
			
		}

		
	}
		
	
	/***
	 * This method creates an enemy and adds it to the arraylist.
	 */
	public void createEnemy() {
		
		double r = (Math.random()*170 + 30);
		int x = super.getGameWidth() - 100;
		
		int y = (int)(Math.random()*super.getGameHeight());
		
		//int y = 0 + 100;
		
		Enemy e = new Enemy(r, x, y);
		
		enemies.add(e);
		
	}
	
	
	public boolean enemyTouching(Enemy e, Player p) {
		
		double dCenter = Math.sqrt(Math.pow(e.getCentX()-p.getCentX(), 2) + Math.pow(e.getCentY()-p.getCentY(), 2));
		System.out.println(e.getCentX() + " " + e.getCentY() + " " + p.getCentX() + " " + p.getCentY());
		System.out.println(dCenter + " distance from centers");
		
		System.out.println(e.getRadius()+p.getRadius() + " is radius addition");
		
		if (e.getRadius()+p.getRadius() >= dCenter) {
			System.out.println("touching");
			return true;
		}
		return false;
		
		//PROBLEM IN HERE
		
	}
	
	
}
	