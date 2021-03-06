import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class LevelThree extends Level implements LevelTwoInterface {
	
	private boolean reset;
	private Player player;
	private int WALL_SIZE=3;
	int pipelength=40;
	ArrayList<Switch> switchList;
	ArrayList<Pipe> pipeList;
	private ArrayList<Obstacles> hiddenRamps;
	private ArrayList<PipeConnector> pipeConnectors;
	private ArrayList<PipeConnectorSwitch> pipeSwitches;
	private ArrayList<Obstacles> filledPipes;
	private int timer;
	private Cage cage;
	private BufferedImage background;
	
	
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
		if(player.getItem()!=null)
			player.usedItem();
		player.setCentX(0);
		player.setCentY(0);
		player.setHiddenPlatforms(null);
		timer=0;
		setLevelNumber(2);
		
		reset=false;
		pipeConnectors= new ArrayList<PipeConnector>();
		pipeSwitches= new ArrayList<PipeConnectorSwitch>();
		filledPipes= new ArrayList<Obstacles>();
		pipeList= new ArrayList<Pipe>();
		setObstacleList(setUpEnvironment());
		switchList= setUpSwitches();
		
		player.setPlatforms(getObstacleList());
		cage= new Cage();
		setUpCage();
		setLevelNumber(3);
		
		try {
			background = ImageIO.read(new File("black-background.png"));

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
		ArrayList<LineObject> switch1 = new ArrayList<LineObject>();

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
		
		
		
		ArrayList<LineObject> faux= new ArrayList<LineObject>();
		LineObject f1= new LineObject(0,0,1,1);
		faux.add(f1);
		Obstacles fauxObs= new Obstacles(faux);
		PipeConnector fauxp= new PipeConnector(fauxObs, 0,0);
		PipeConnectorSwitch watersource= new PipeConnectorSwitch((sgeneric.translate(0,0)).getOutlines(), fauxp, new Color(0, 200, 250, 100));
		pipeSwitches.add(watersource);
		
		if(watersource.getContacted()==false)
			watersource.changeContactStatus();
		
		watersource.setSetToFill(true);
		watersource.setWaterSource(true);
		
		ArrayList<LineObject> pipeDownPerim= new ArrayList<LineObject>();
		LineObject top1= new LineObject(0,0,15,0);
		LineObject left1= new LineObject(0,0,0,30);
		LineObject bottom1= new LineObject(0,30,15,30);
		LineObject right1= new LineObject(bottom1.getV2(),top1.getV1());
		
		pipeDownPerim.add(top1);
		pipeDownPerim.add(right1);
		pipeDownPerim.add(bottom1);
		pipeDownPerim.add(left1);
		
		Obstacles pipeC= new Obstacles(pipeDownPerim);
		PipeConnector pipeDown= new PipeConnector(pipeC,gameWidth/5, gameHeight/3);
		pipeConnectors.add(pipeDown);
		
		PipeConnectorSwitch pipeDownSwitch= new PipeConnectorSwitch((sgeneric.translate(0,gameHeight/2)).getOutlines(), pipeConnectors.get(0));
		pipeSwitches.add(pipeDownSwitch);
		
		Pipe p0= new Pipe(watersource, pipeDownSwitch);
		p0.getPipeSwitches().add(watersource);
		p0.getPipeSwitches().add(pipeDownSwitch);
		pipeList.add(p0);
		watersource.getPipes().add(p0);
		pipeDownSwitch.getPipes().add(p0);
		
		
		ArrayList<LineObject> pipeBLeftPerim= new ArrayList<LineObject>();
		top1= new LineObject(10,0, 0,0);
		left1= new LineObject(top1.getV1().getXCoord(),top1.getV1().getYCoord(), 0,30);
		bottom1= new LineObject(0,30,30,30);
		right1= new LineObject(30,30,30,20);
		LineObject top2= new LineObject(10,20,30,20);
		LineObject right2= new LineObject(10,20,10,0);
		
		pipeBLeftPerim.add(top1);
		pipeBLeftPerim.add(left1);
		pipeBLeftPerim.add(bottom1);
		pipeBLeftPerim.add(right1);
		pipeBLeftPerim.add(top2);
		pipeBLeftPerim.add(right2);
		
		pipeC= new Obstacles(pipeBLeftPerim);
		PipeConnector pipeBLeft= new PipeConnector(pipeC, gameWidth/5 + 20, gameHeight-pipelength-20);
		
		pipeConnectors.add(pipeBLeft);
		
		PipeConnectorSwitch pBLSwitch= new PipeConnectorSwitch((sgeneric.translate(0,gameHeight-20-50)).getOutlines(), pipeConnectors.get(1));
		pipeSwitches.add(pBLSwitch);
			
		Pipe p2= new Pipe(pipeDownSwitch,pBLSwitch);
		p2.getPipeSwitches().add(pipeDownSwitch);
		p2.getPipeSwitches().add(pBLSwitch);
		pipeDownSwitch.getPipes().add(p2);
		pBLSwitch.getPipes().add(p2);
		pipeList.add(p2);

		ArrayList<LineObject> pipeTUpPerimeter= new ArrayList<LineObject>();
		top1= new LineObject(0,20,10,20);
		left1= new LineObject(10,20,10,0);
		top2= new LineObject(left1.getV2(), new Vertex(20,0));
		right1= new LineObject(top2.getV2(), new Vertex(20,20));
		LineObject top3= new LineObject(right1.getV2(), new Vertex(30,20));
		right2= new LineObject(top3.getV2(), new Vertex(30,30));
		bottom1= new LineObject(right2.getV2(), new Vertex(0,30));
		LineObject left2= new LineObject(bottom1.getV2(), top1.getV1());
		
		pipeTUpPerimeter.add(top1);
		pipeTUpPerimeter.add(left1);
		pipeTUpPerimeter.add(top2);
		pipeTUpPerimeter.add(right1);
		pipeTUpPerimeter.add(top3);
		pipeTUpPerimeter.add(right2);
		pipeTUpPerimeter.add(bottom1);
		pipeTUpPerimeter.add(left2);
		
		Obstacles pipeTUp= new Obstacles(pipeTUpPerimeter);
		PipeConnector pipeTUpp= new PipeConnector(pipeTUp, gameWidth/2, gameHeight-20);
		pipeConnectors.add(pipeTUpp);
		
		PipeConnectorSwitch pTUpSwitch = new PipeConnectorSwitch((sgeneric.translate(gameWidth/5*2 + gameWidth/12,gameHeight-20-50)).getOutlines(), pipeTUpp);
		pipeSwitches.add(pTUpSwitch);
		
		Pipe p1= new Pipe(pBLSwitch, pTUpSwitch);
		p1.getPipeSwitches().add(pBLSwitch);
		p1.getPipeSwitches().add(pTUpSwitch);
		pBLSwitch.getPipes().add(p1);
		pTUpSwitch.getPipes().add(p1);
		pipeList.add(p1);
		
		ArrayList<LineObject> pipeTRightPerim= new ArrayList<LineObject>();
		top1= new LineObject(0,0,10,0);
		right1= new LineObject(top1.getV2(), new Vertex(10,10));
		top2= new LineObject(right1.getV2(), new Vertex(30,10));
		right2= new LineObject(top2.getV2(), new Vertex(30,20));
		bottom1= new LineObject(right2.getV2(), new Vertex(10,20));
		LineObject right3= new LineObject(bottom1.getV2(), new Vertex(10,30));
		LineObject bottom2= new LineObject(right3.getV2(), new Vertex(0,30));
		left1= new LineObject(bottom2.getV2(), top1.getV1());
		
		pipeTRightPerim.add(top1);
		pipeTRightPerim.add(right1);
		pipeTRightPerim.add(top2);
		pipeTRightPerim.add(right2);
		pipeTRightPerim.add(bottom1);
		pipeTRightPerim.add(right3);
		pipeTRightPerim.add(bottom2);
		pipeTRightPerim.add(left1);
		Obstacles ptr= new Obstacles(pipeTRightPerim);
		
		PipeConnector pTRight= new PipeConnector(ptr, gameWidth/5+ 50, gameHeight/3);
		pipeConnectors.add(pTRight);
		
		PipeConnectorSwitch pTRightSwitch= new PipeConnectorSwitch((sgeneric.translate(gameWidth/5*2 + gameWidth/12,gameHeight/2)).getOutlines(), pTRight);
		pipeSwitches.add(pTRightSwitch);
		
		p1= new Pipe(pTRightSwitch,pTUpSwitch);
		p1.getPipeSwitches().add(pTRightSwitch);
		p1.getPipeSwitches().add(pTUpSwitch);
		pTRightSwitch.getPipes().add(p1);
		pTUpSwitch.getPipes().add(p1);
		pipeList.add(p1);
		
		ArrayList<LineObject> pTDownPerim= new ArrayList<LineObject>();
		top1= new LineObject(0,0,30,0);
		right1= new LineObject(top1.getV2(), new Vertex(30,10));
		bottom1= new LineObject(right1.getV2(), new Vertex(20,10));
		right3= new LineObject(bottom1.getV2(), new Vertex(20,30));
		bottom2 = new LineObject(right3.getV2(), new Vertex(10,30));
		left1= new LineObject(bottom2.getV2(), new Vertex(10,10));
		LineObject bottom3= new LineObject(left1.getV2(), new Vertex(0,10));
		left2= new LineObject(bottom3.getV2(), top1.getV1());
		
		pTDownPerim.add(top1);
		pTDownPerim.add(right1);
		pTDownPerim.add(bottom1);
		pTDownPerim.add(right3);
		pTDownPerim.add(bottom2);
		pTDownPerim.add(left1);
		pTDownPerim.add(bottom3);
		pTDownPerim.add(left2);
		
		PipeConnector pTDown= new PipeConnector(new Obstacles(pTDownPerim), gameWidth/5- 50, gameHeight/3);
		pipeConnectors.add(pTDown);
		
		PipeConnectorSwitch pTDownSwitch= new PipeConnectorSwitch((sgeneric.translate(gameWidth/5*2 + gameWidth/12,0)).getOutlines(), pTDown);
		pipeSwitches.add(pTDownSwitch);
		
		p1= new Pipe(pTDownSwitch,pTRightSwitch);
		p1.getPipeSwitches().add(pTDownSwitch);
		p1.getPipeSwitches().add(pTRightSwitch);
		pTDownSwitch.getPipes().add(p1);
		pTRightSwitch.getPipes().add(p1);
		pipeList.add(p1);
		
		p2= new Pipe(watersource, pTDownSwitch);
		p2.getPipeSwitches().add(watersource);
		p2.getPipeSwitches().add(pTDownSwitch);
		watersource.getPipes().add(p2);
		pTDownSwitch.getPipes().add(p2);
		pipeList.add(p2);
		
		ArrayList<LineObject> pTRCornerperim= new ArrayList<LineObject>();
		top1= new LineObject(0,0,30,0);
		right1= new LineObject(top1.getV2(), new Vertex(30,30));
		bottom1= new LineObject(right1.getV2(), new Vertex(20,30));
		left1= new LineObject(bottom1.getV2(), new Vertex(20,10));
		bottom2= new LineObject(left1.getV2(), new Vertex(0,10));
		left2= new LineObject(bottom2.getV2(), top1.getV1());
		
		pTRCornerperim.add(top1);
		pTRCornerperim.add(right1);
		pTRCornerperim.add(bottom1);
		pTRCornerperim.add(left1);
		pTRCornerperim.add(bottom2);
		pTRCornerperim.add(left2);
		
		PipeConnector pTopRight= new PipeConnector(new Obstacles(pTRCornerperim), gameWidth/5*4, 100);
		pipeConnectors.add(pTopRight);

		PipeConnectorSwitch pTopRightSwitch= new PipeConnectorSwitch((sgeneric.translate(gameWidth-50,0)).getOutlines(), pTopRight);
		pipeSwitches.add(pTopRightSwitch);
		
		p1= new Pipe(pTDownSwitch,pTopRightSwitch);
		p1.getPipeSwitches().add(pTDownSwitch);
		p1.getPipeSwitches().add(pTopRightSwitch);
		pTDownSwitch.getPipes().add(p1);
		pTopRightSwitch.getPipes().add(p1);
		pipeList.add(p1);
		
		
		ArrayList<LineObject> pTLeftperim= new ArrayList<LineObject>();
		top1= new LineObject(20,0,30,0);
		right1= new LineObject(top1.getV2(), new Vertex(30,30));
		bottom1= new LineObject(right1.getV2(), new Vertex(20,30));
		left1= new LineObject(bottom1.getV2(), new Vertex(20,20));
		bottom2= new LineObject(left1.getV2(), new Vertex(0,20));
		left2= new LineObject(bottom2.getV2(), new Vertex(0,10));
		top2= new LineObject(left2.getV2(), new Vertex(20,10));
		LineObject left3= new LineObject(top2.getV2(), top1.getV1());
		
		pTLeftperim.add(top1);
		pTLeftperim.add(right1);
		pTLeftperim.add(bottom1);
		pTLeftperim.add(left1);
		pTLeftperim.add(bottom2);
		pTLeftperim.add(left2);
		pTLeftperim.add(top2);
		pTLeftperim.add(left3);
		
		PipeConnector pTLeft= new PipeConnector(new Obstacles(pTLeftperim), gameWidth/2 + 20, gameHeight-100);
		pipeConnectors.add(pTLeft);

		PipeConnectorSwitch pTLeftSwitch= new PipeConnectorSwitch((sgeneric.translate(gameWidth-50,gameHeight/2)).getOutlines(), pTLeft);
		pipeSwitches.add(pTLeftSwitch);
		
		p1= new Pipe(pTopRightSwitch,pTLeftSwitch);
		p1.getPipeSwitches().add(pTopRightSwitch);
		p1.getPipeSwitches().add(pTLeftSwitch);
		pTopRightSwitch.getPipes().add(p1);
		pTLeftSwitch.getPipes().add(p1);
		pipeList.add(p1);
		
		Pipe p4= new Pipe(pTRightSwitch,pTLeftSwitch);
		p4.getPipeSwitches().add(pTRightSwitch);
		p4.getPipeSwitches().add(pTLeftSwitch);
		pTRightSwitch.getPipes().add(p4);
		pTLeftSwitch.getPipes().add(p4);
		pipeList.add(p4);
		
		
		
		
		
		
		
		
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
			boolean nearSwitch= false;
			for(int i= 0; i< pipeSwitches.size(); i++)
			{
				PipeConnectorSwitch p= pipeSwitches.get(i);
				double d1= (p.getCentX()-player.getCentX());
				double d2=(p.getCentY()-player.getCentY());
				double distance= Math.sqrt(d1*d1 + d2*d2);
				if(distance<3*player.getRadius())
				{
					if(p.getContacted())
					{
						PipeConnector pitem= new PipeConnector(p.getSymbol().getPerimeter(),0,0);
						player.setItem(pitem);
						pipeSwitches.get(i).changeContactStatus();
						nearSwitch=true;
						break;
					}
				}
			}
			if(nearSwitch==false)
			{
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
						
						break;
					}
					
				}
			}
		}
		
		else
		{
			boolean closeToSwitch=false;
			PipeConnectorSwitch s= pipeSwitches.get(0);
			
			for(int i= 0; i< pipeSwitches.size(); i++)
			{
				PipeConnectorSwitch p= pipeSwitches.get(i);
				double d1= (p.getCentX()-player.getCentX());
				double d2=(p.getCentY()-player.getCentY());
				double distance= Math.sqrt(d1*d1 + d2*d2);
				if(distance<3*player.getRadius())
				{
					closeToSwitch=true;
					s=pipeSwitches.get(i);
					break;
				}
				
			}
			if(closeToSwitch==true)
			{
				if(s!=null)
				{
					if(((PipeConnector)(player.getItem())).equals(s.getSymbol()))
					{
						s.changeContactStatus();
						player.usedItem();
					}
				}
			}
			else
			{
				pipeConnectors.add((PipeConnector)(player.getItem()));
				pipeConnectors.get(pipeConnectors.size()-1).setStartX((int)(player.getCentX()));
				pipeConnectors.get(pipeConnectors.size()-1).setStartY((int)(player.getCentY()));
				player.usedItem();
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
		
		timer++;
		int gameHeight = super.getGameHeight();
		int gameWidth = super.getGameWidth();
		
		
		Font f = new Font("Arial", Font.PLAIN, 20);
		g.setFont(f);
		g.setColor(Color.white);
		String t1="Press 'Z' to Pick up and drop water pipe connectors!";
		g.drawString(t1, gameWidth / 17, gameHeight/10);
		double dy= g.getFontMetrics(f).getMaxDescent() + g.getFontMetrics().getHeight();
		String t="Fill the network to remove the lasers and move ahead!";
		g.drawString(t, gameWidth/17, (int)(gameHeight/10 + dy));
		String t2= "Press 'r' to reset";
		g.drawString(t2, gameWidth/17, (int)(gameHeight/10 + 2*dy));
		
		
		if(pipeList!=null)
		{
			filledPipes= new ArrayList<Obstacles>();
			for(int i=0; i< pipeList.size(); i++)
			{
				
				if(getPipeList().get(i).getStartFill()==false)
				{
					getPipeList().get(i).checkFull(pipeSwitches);
					if(getPipeList().get(i).getColor().equals(new Color(0, 200, 250, 100)))
					{
						getPipeList().get(i).setTimeStarted(timer);
					}
				}
				else
				{
					getPipeList().get(i).checkFull(pipeSwitches);
					Pipe p= getPipeList().get(i);
					ArrayList<LineObject> fillperimeter= new ArrayList<LineObject>();
					LineObject pbot= p.getOutlines().get(2);
					double height= 1000*Math.abs(p.getTimeStarted()-timer)*2/300*4/5*p.getHeight();
					if(height/1000>=p.getHeight())
						height=p.getHeight()*1000;
					
					LineObject ptop= new LineObject(pbot.getV1().getXCoord(), (int)(pbot.getV1().getYCoord() - height/1000), pbot.getV1().getXCoord() + p.getWidth(),(int)(pbot.getV1().getYCoord()- height/1000));
					LineObject pleft= new LineObject(ptop.getV1().getXCoord(),ptop.getV1().getYCoord(),pbot.getV1().getXCoord(),pbot.getV1().getYCoord());
					LineObject pright= new LineObject(ptop.getV2().getXCoord(),ptop.getV2().getYCoord(),pbot.getV2().getXCoord(),pbot.getV2().getYCoord());
					
					fillperimeter.add(ptop);
					fillperimeter.add(pright);
					fillperimeter.add(pbot);
					fillperimeter.add(pleft);
					
					Obstacles filledPortion= new Obstacles(fillperimeter);
					filledPipes.add(filledPortion);
				}
				
				getPipeList().get(i).drawSwitch(g);
				
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
		ArrayList<Obstacles> totalObs= new ArrayList<Obstacles>();
		if(getPipeSwitchList()!=null)
		{
			for(int i= 0; i< getPipeSwitchList().size(); i++)
			{
				totalObs.add(getPipeSwitchList().get(i));
				g.setColor(new Color(0,0,0,0));
				Polygon p= new Polygon();
				for(int j=0; j<getPipeSwitchList().get(i).getVertices().size(); j++)
				{
					p.addPoint(getPipeSwitchList().get(i).getVertices().get(j).getXCoord(), getPipeSwitchList().get(i).getVertices().get(j).getYCoord());
				}
				if(p!=null)
				{
					g.fillPolygon(p);
				}
				getPipeSwitchList().get(i).drawSwitch(g);
			}
		}
		if(getSwitchList()!=null)
		{
			for(int i= 0; i< getSwitchList().size(); i++)
			{
				totalObs.add(getPipeSwitchList().get(i));
				g.setColor(Color.green);
				Polygon p= new Polygon();
				for(int j=0; j<getSwitchList().get(i).getVertices().size(); j++)
				{
					p.addPoint(getSwitchList().get(i).getVertices().get(j).getXCoord(), getSwitchList().get(i).getVertices().get(j).getYCoord());
				}
				if(p!=null)
				{
					g.fillPolygon(p);
				}
				getSwitchList().get(i).drawSwitch(g);
			}
		}
		
		
		if(getObstacleList()!=null)
		{
			for(int i= 0; i<getObstacleList().size(); i++)
			{
				g.setColor(Color.magenta);
				totalObs.add(getObstacleList().get(i));
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
		
		if(filledPipes!=null)
		{
			
			for(int i=0; i< filledPipes.size(); i++)
			{
				totalObs.add(filledPipes.get(i));
				g.setColor(new Color(0, 200, 250, 100));
				Polygon p= new Polygon();
				for(int j=0; j<filledPipes.get(i).getVertices().size(); j++)
				{
					p.addPoint(filledPipes.get(i).getVertices().get(j).getXCoord(), filledPipes.get(i).getVertices().get(j).getYCoord());
				}
				if(p!=null)
				{
					g.fillPolygon(p);
				}
				
			}
		}
		if(pipeConnectors!=null)
		{
			for(int i=0; i< pipeConnectors.size(); i++)
			{
				g.setColor(Color.cyan);
				g.drawImage(pipeConnectors.get(i).getImage(), null, pipeConnectors.get(i).getStartX(), pipeConnectors.get(i).getStartY());
			}
		}
		setUpCage();
		if(getCage()!=null)
		{
			if(getCage().getGrid()!=null)
			{
				
				getCage().draw(g);
			}
		}
		player.setPlatforms(totalObs);
		
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
	@Override
	public boolean checkComplete()
	{
		if(getLevelComplete()==true)
		{
			return true;
		}
		else
		{
			Cage c= getCage();
			boolean there=false;
			Switch endGoal= switchList.get(0);
			double d= Math.sqrt((player.getCentX()-endGoal.getCentX())*(player.getCentX()-endGoal.getCentX()) + (player.getCentY()-endGoal.getCentY())*(player.getCentY()-endGoal.getCentY()));
			if(d<=(player.getRadius()+ 50/2*Math.sqrt(2)))
			{
				there=true;
			}
			if(there==true)
			{
				if(c==null || c.getGrid()==null)
					return true;
				else
				{
					setLevelNumber(getLevelNumber()-1);
					return true;
				}
			}
			return there;
		}
	}
	public Cage getCage()
	{
		return cage;
	}
	public void setCage(Cage c)
	{
		cage=c;
	}
	public void setUpCage()
	{
		Cage c= new Cage();
		Switch goal= getSwitchList().get(0);
		int n2= getPipeSwitchList().size();
		for(int i= 0; i< getPipeSwitchList().size(); i++)
		{
			if(getPipeSwitchList().get(i).getContacted()==true)
			{
				n2--;
			}
		}
		double n= (double)(n2);
		int stx= goal.getVertices().get(0).getXCoord() -5;
		int finx= goal.getVertices().get(1).getXCoord() + 5;
		int sty= goal.getVertices().get(0).getYCoord();
		int finy= goal.getVertices().get(2).getYCoord();
		for(int j=0; j< n; j++)
		{
			double i= (double)j;
			double diff= finx-stx;
			Vertex v1= new Vertex((int)(stx + i/n*diff),sty);
			Vertex v2= new Vertex((int)(stx + i/n*diff), finy);
			LineObject l= new LineObject(v1,v2);
			c.getGrid().add(l);
		}
		for(int j=0; j< n; j++)
		{
			double i= (double)j;
			double diff= finy-sty;
			Vertex v1= new Vertex(stx,(int)(sty + i/n*diff));
			Vertex v2= new Vertex(finx, (int)(sty + i/n*diff));
			LineObject l= new LineObject(v1,v2);
			c.getGrid().add(l);
		}
		if(n==0)
			setCage(null);
		else
			setCage(c);
		
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
		int gameWidth= super.getGameWidth();
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
		Switch Switcher1= new Switch(sgeneric.translate(gameWidth*4/5+100, gameHeight-100).getOutlines());
		switches.add(Switcher1);
		
		
		return switches;
	}
	public ArrayList<LineObject> getPerimeter(PipeConnectorSwitch p1, PipeConnectorSwitch p2)
	{
		ArrayList<LineObject> perimeter= new ArrayList<LineObject>();
		int tx1= p1.getVertices().get(0).getXCoord();
		int tx2= p2.getVertices().get(0).getXCoord();
		LineObject top= new LineObject( p1.getVertices().get(0),  p2.getVertices().get(0));
		LineObject bottom= new LineObject(p1.getVertices().get(2), p2.getVertices().get(2));
		LineObject left= p1.getOutlines().get(3);
		LineObject right= p2.getOutlines().get(1);
		
		perimeter.add(top);
		perimeter.add(right);
		perimeter.add(bottom);
		perimeter.add(left);
		
		return perimeter;
	}
	
	
	
	
}
