import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
/*
 * This is a class for a laser cage object that blocks exits.
 */
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
	/**
	 * Getter for the laser colors
	 * @return color of lasers
	 */
	public Color getColor()
	{
		return color;
	}
	/**
	 * Setter for Color
	 * @param c new color of the lasers
	 */
	public void setColor(Color c)
	{
		color=c;
	}
	/**
	 * Gets the laser grid
	 * @return laserGrid
	 */
	public ArrayList<LineObject> getGrid()
	{
		return laserGrid;
	}
	/**
	 * Sets the laser grid as the list of lineobjects
	 * @param l
	 */
	public void setGrid(ArrayList<LineObject> l)
	{
		laserGrid=l;
	}
	/**
	 * Draws the laser grid
	 * @param g the GraphicsObject in which the cage will be drawn.
	 */
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