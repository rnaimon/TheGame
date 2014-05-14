import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Cage
{
	private ArrayList<LineObject> laserGrid;
	private Color color;
	
	public Cage(ArrayList<LineObject> l)
	{
		laserGrid=l;
		color= new Color(150, 25,25);
	}
	public Cage()
	{
		laserGrid= new ArrayList<LineObject>();
		color= new Color(150, 25,25);
	}
	public Color getColor()
	{
		return color;
	}
	public void setColor(Color c)
	{
		color=c;
	}
	public ArrayList<LineObject> getGrid()
	{
		return laserGrid;
	}
	public void setGrid(ArrayList<LineObject> l)
	{
		laserGrid=l;
	}
	public void draw(Graphics2D g)
	{
		if(laserGrid!=null)
		{
			for(int i=0; i< laserGrid.size(); i++)
			{
				Vertex v1= laserGrid.get(i).getV1();
				Vertex v2= laserGrid.get(i).getV2();
				g.setColor(getColor());
				g.drawLine(v1.getXCoord(), v1.getYCoord(), v2.getXCoord(), v2.getYCoord());
			}
		}
	}
	
}