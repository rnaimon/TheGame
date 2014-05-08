import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;



/***
 *  This is the class for a switch, or a button, which, when touched by another object,
 * performs some function (to be outlined in the actual level subclass).
 * @author Rebecca Naimon
 *
 */
public class PipeConnectorSwitch extends Switch {
	
	private boolean contacted;
	private double centX;
	private double centY;
	private boolean inactive;
	private PipeConnector symbol;
	private boolean setToFill;
	private ArrayList<Pipe> pipeList;
	private boolean isWaterSource;
	
	/***
	 * This constructor sets the default contacted value to false and instantiates
	 * the Obstacle.
	 * @param o is the ArrayList of LineOjects that goes into Obstacles
	 */
	public PipeConnectorSwitch(ArrayList<LineObject> o, PipeConnector p) {
		super(o);
		symbol=new PipeConnector(p.getPerimeter(),0,0);
		int[] vertx= new int[getVertices().size()];
		int[] verty= new int[getVertices().size()];
		for(int j=0; j< getVertices().size(); j++)
		{
			vertx[j]=(int)(getVertices().get(j).getXCoord());
			verty[j]=(int)(getVertices().get(j).getYCoord());
		}
		centX=Math.abs(vertx[1]- vertx[0]) + vertx[0];
		centY=Math.abs(verty[1]- verty[0]) + verty[0];
		setColor(Color.white);
		isWaterSource=false;
		pipeList= new ArrayList<Pipe>();
	}
	public PipeConnectorSwitch(ArrayList<LineObject> o, PipeConnector p, Color c) {
		super(o);
		symbol=new PipeConnector(p.getPerimeter(),0,0);
		int[] vertx= new int[getVertices().size()];
		int[] verty= new int[getVertices().size()];
		for(int j=0; j< getVertices().size(); j++)
		{
			vertx[j]=(int)(getVertices().get(j).getXCoord());
			verty[j]=(int)(getVertices().get(j).getYCoord());
		}
		centX=Math.abs(vertx[1]- vertx[0]) + vertx[0];
		centY=Math.abs(verty[1]- verty[0]) + verty[0];
		setColor(c);
		setToFill=false;
		isWaterSource=false;
		pipeList= new ArrayList<Pipe>();
	}
	public void setWaterSource(boolean b)
	{
		isWaterSource=b;
	}
	public boolean isWaterSource()
	{
		return isWaterSource;
	}
	public boolean getSetToFill()
	{
		return setToFill;
	}
	
	public void setSetToFill(boolean b)
	{
		setToFill=b;
	}
	public PipeConnector getSymbol()
	{
		return symbol;
	}
	public ArrayList<Pipe> getPipes()
	{
		return pipeList;
	}
	public void setPipes(ArrayList<Pipe> p)
	{
		pipeList=p;
	}
	public void setSymbol(PipeConnector p)
	{
		symbol=p;
	}
	public boolean isConnectedToSource()
	{
		if(isWaterSource==true)
		{
			//System.out.println("here");
			return true;
			
		}
		else
		{
			if(getContacted()==false)
				return false;
			for(int i=0; i<getPipes().size(); i++)
			{
				Pipe p= getPipes().get(i);
				for(int j=0; j<p.getPipeSwitches().size(); j++)
				{
					if(p.getPipeSwitches().get(j).equals(this))
					{
						if(getContacted()==false)
							return false;
						else
							continue;
					}
					else 
					{
						if(p.getPipeSwitches().get(j).getContacted()==false)
						{
							return false;
						}
						else
						{
							return p.getPipeSwitches().get(j).isConnectedToSource();
						}
					}
					
				}
			}
		}
		return false;
	}
	@Override
	public void drawSwitch(Graphics2D g)
	{
		int[] vertx= new int[getVertices().size()];
		int[] verty= new int[getVertices().size()];
		for(int j=0; j< getVertices().size(); j++)
		{
			vertx[j]=(int)(getVertices().get(j).getXCoord());
			verty[j]=(int)(getVertices().get(j).getYCoord());
		}
		g.setColor(getColor());
		g.fillPolygon(vertx, verty, (getVertices().size()));
		int length= Math.abs(vertx[1]- vertx[0]);
		if(symbol!=null)
		{
			if(getContacted())
			{
				symbol.setColor(Color.cyan);
			}
			else
			{
				symbol.setColor(Color.magenta);
			}
			symbol.reDraw();
			symbol.setStartX(vertx[0] + length/3);
			symbol.setStartY(verty[0]);
			g.drawImage(symbol.getImage(),null, symbol.getStartX(), symbol.getStartY());
		}
	}
	/*
	public boolean equals(PipeConnectorSwitch s1)
	{
		if(this.getSymbol().equals(s1.getSymbol()))
		{
			Obstacles o1= new Obstacles(getOutlines());
			Obstacles o2= new Obstacles(s1.getOutlines());
			if(o1.equals(o2))
			{
				return true;
			}
		}
		return false;
	}
	*/
	 
	
	
	
	
}

