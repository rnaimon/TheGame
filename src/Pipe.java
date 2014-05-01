import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Pipe extends Switch
{
	ArrayList<PipeConnectorSwitch> pipeSwitches;
	public Pipe(ArrayList<LineObject> o)
	{
		super(o);
		pipeSwitches=new ArrayList<PipeConnectorSwitch>();
	}
	
	public ArrayList<PipeConnectorSwitch> getPipeSwitches()
	{
		return pipeSwitches;
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
			setColor(Color.cyan);
		else
			setColor(new Color(50,50,50));
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
	}
	
}