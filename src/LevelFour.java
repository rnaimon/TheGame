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
	//	obstacleList.add(Platform1);
		
		
		Obstacles Platform2 = Platform1.translate(350, 0);
		obstacleList.add(Platform2);
		
		Obstacles Platform3 = Platform1.translate(700, 0);
		obstacleList.add(Platform3);
		
		Obstacles Platform4 = Platform1.translate(0, 70);
		obstacleList.add(Platform4);
		
	//	Obstacles Platform5 = Platform1.translate(350, 70);
	//	obstacleList.add(Platform5);
		
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
		
//		Obstacles Platform12 = Platform1.translate(700, 210);
//		obstacleList.add(Platform12);
		
		Obstacles Platform13 = Platform1.translate(0, 280);
		obstacleList.add(Platform13);
		
//		Obstacles Platform14 = Platform1.translate(350, 280);
//		obstacleList.add(Platform14);
		
		Obstacles Platform15 = Platform1.translate(700, 280);
		obstacleList.add(Platform15);
		
//		Obstacles Platform16 = Platform1.translate(0, 350);
//		obstacleList.add(Platform16);
		
		Obstacles Platform17 = Platform1.translate(0, 420);
		obstacleList.add(Platform17);		
		
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
		
		int gameHeight = super.getGameHeight();
		int gameWidth = super.getGameWidth();
		
		// end goal switch is always first
		LineObject Switch1Top= new LineObject(250 + 2*x - y, gameHeight - gameHeight/8, 250+2*x, gameHeight - gameHeight/8);
		LineObject Switch1SideL= new LineObject(250 + 2*x - y, gameHeight - gameHeight/8, 2*x+ 250 - y, gameHeight - gameHeight/8 + 50);
		LineObject Switch1Bottom= new LineObject(250 + 2*x - y,gameHeight - gameHeight/8 + 50, 250+2*x, gameHeight - gameHeight/8 + 50);
		LineObject Switch1SideR= new LineObject(250+2*x,gameHeight - gameHeight/8, 250+2*x, gameHeight - gameHeight/8 + 50);
		
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
					double centerXP = p.getCentX() + p.getRadius()/2;
					double centerYP = p.getCentY() + p.getRadius()/2;
					
					double centerXE = e.getCentX() + e.getRadius()/2;
					double centerYE = e.getCentY() + e.getRadius()/2;
					
					double dCenter = Math.sqrt(Math.pow(centerXE - centerXP, 2) + Math.pow(centerYE - centerYP, 2));
					if (e.getRadius()/2+p.getRadius()/2 >= dCenter) {
						
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

					g.drawImage(enemies.get(x).getFace(), (int)enemies.get(x).getCentX(), (int)enemies.get(x).getCentY(), null);
				
				}
				if (enemies.size() < 8) {
					setUpEnemies();
				}
		
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

			if(e.getCentX() + e.getRadius() < 0)
				e.setAlive(false);
			
			if (e.getAlive() == false) {
				System.out.println("here1");
				enemies.remove(x);
				x--;
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
		
		
	//	Math.sqrt(Math.pow(e.getCentX() - p.getCentX(), 2) + Math.pow(e.getCentY() - p.getCentY(), 2));
		
		double centerXP = p.getCentX() + p.getRadius()/2;
		double centerYP = p.getCentY() + p.getRadius()/2;
		
		double centerXE = e.getCentX() + e.getRadius()/2;
		double centerYE = e.getCentY() + e.getRadius()/2;
		
	//	System.out.println(centerXP + " is the center x of player and radius is " + p.getRadius()/2);
		double dCenter = Math.sqrt(Math.pow(centerXE - centerXP, 2) + Math.pow(centerYE - centerYP, 2));
	//	System.out.println(e.getCentX() + " " + e.getCentY() + " " + p.getCentX() + " " + p.getCentY());
	//	System.out.println(dCenter + " distance from centers");
		
	//	System.out.println(e.getRadius()+p.getRadius() + " is radius addition");
		
		if (e.getRadius()/2+p.getRadius()/2 > dCenter) {
	//		System.out.println("touching");
			return true;
		}
		return false;
		
		//PROBLEM IN HERE
		
	}
	
	
}
	