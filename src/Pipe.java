import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Pipe extends Switch
{
	private boolean startFill;
	private int timeStarted;
	ArrayList<PipeConnectorSwitch> pipeSwitches;
	private int width;
	private int height;
	
	public Pipe(ArrayList<LineObject> o)
	{
		super(o);
		pipeSwitches=new ArrayList<PipeConnectorSwitch>();
		startFill=false;
		timeStarted=0;
		int[] vertx= new int[getVertices().size()];
		int[] verty= new int[getVertices().size()];
		for(int j=0; j< getVertices().size(); j++)
		{
			vertx[j]=(int)(getVertices().get(j).getXCoord());
			verty[j]=(int)(getVertices().get(j).getYCoord());
		}
		width= Math.abs(vertx[0]-vertx[1]);
		height= Math.abs(verty[1]-verty[2]);
	}
	public Pipe(PipeConnectorSwitch s1, PipeConnectorSwitch s2)
	{
		super(createPerimeter( s1, s2));
		
		pipeSwitches=new ArrayList<PipeConnectorSwitch>();
		startFill=false;
		timeStarted=0;
		int[] vertx= new int[getVertices().size()];
		int[] verty= new int[getVertices().size()];
		for(int j=0; j< getVertices().size(); j++)
		{
			vertx[j]=(int)(getVertices().get(j).getXCoord());
			verty[j]=(int)(getVertices().get(j).getYCoord());
		}
		width= Math.abs(vertx[0]-vertx[1]);
		height= Math.abs(verty[1]-verty[2]);
	}
	public static ArrayList<LineObject> createPerimeter(PipeConnectorSwitch s1, PipeConnectorSwitch s2)
	{
		ArrayList<LineObject> perim= new ArrayList<LineObject>();
		int s1tx=s1.getOutlines().get(0).getV1().getXCoord();
		int s2tx= s2.getOutlines().get(0).getV1().getXCoord();
		int s1ty=s1.getOutlines().get(0).getV1().getYCoord();
		int s2ty= s2.getOutlines().get(0).getV1().getYCoord();
		LineObject top= new LineObject(s1.getOutlines().get(0).getV1(),s2.getOutlines().get(0).getV1());
		LineObject bottom= new LineObject(s1.getOutlines().get(2).getV2(),s2.getOutlines().get(2).getV2());

		if(s1tx!=s2tx)
		{
			if(s1tx<s2tx)
			{
				top= new LineObject(s1.getOutlines().get(0).getV2(),s2.getOutlines().get(0).getV1());
				bottom= new LineObject(s1.getOutlines().get(2).getV2(),s2.getOutlines().get(2).getV1());

			}
			else
			{
				top= new LineObject(s1.getOutlines().get(0).getV1(),s2.getOutlines().get(0).getV2());
				bottom= new LineObject(s1.getOutlines().get(2).getV1(),s2.getOutlines().get(2).getV2());

			}
		}
		else
		{
			if(s1ty<s2ty)
			{
				top= new LineObject(s1.getOutlines().get(2).getV1(),s1.getOutlines().get(2).getV2());
				bottom= new LineObject(s2.getOutlines().get(0).getV1(),s2.getOutlines().get(0).getV2());
			}
			else
			{
				top= new LineObject(s1.getOutlines().get(0).getV1(),s1.getOutlines().get(0).getV2());
				bottom= new LineObject(s2.getOutlines().get(2).getV1(),s2.getOutlines().get(2).getV2());
			}
		}
		LineObject left= new LineObject(top.getV1(),bottom.getV1());
		LineObject right= new LineObject(top.getV2(),bottom.getV2());
		
		if(top.getOrientation()=='h')
		{
			perim.add(top);
			perim.add(right);
			perim.add(bottom);
			perim.add(left);
		}
		else
		{
			perim.add(left);
			perim.add(bottom);
			perim.add(right);
			perim.add(top);
		}
		
		
		return perim;
	}
	public ArrayList<PipeConnectorSwitch> getPipeSwitches()
	{
		return pipeSwitches;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	public void setPipeSwitches(ArrayList<PipeConnectorSwitch> p)
	{
		pipeSwitches=p;
	}
	
	public void checkFull()
	{
		boolean full=true;
		for(int i=0; i< pipeSwitches.size(); i++)
		{
			if(pipeSwitches.get(i).getContacted()==false)
				full=false;
		}
		if(full==true)
		{
			setColor(Color.cyan);
			startFill=true;
		}
		else
		{
			setColor(new Color(50,50,50));
			startFill=false;
			timeStarted=0;
		}
	}
	public boolean getStartFill()
	{
		return startFill;
	}
	public void setTimeStarted(int t)
	{
		timeStarted=t;
	}
	public int getTimeStarted()
	{
		return timeStarted;
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
		g.setColor(new Color(50,50,50));
		g.fillPolygon(vertx, verty, (getVertices().size()));
	}
	
}