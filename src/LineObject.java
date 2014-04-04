import java.util.ArrayList;

public class LineObject
 
{
 
	private ArrayList<Vertex> Vertices;
	private char orientation;
	private double slope;
	private double constant;

	
 
	/**
	 * The constructor for LineObject. A program that is an object for a line
	 * @param x the x coordinate for the first vertex
	 * @param y the y coordinate for the first vertex
	 * @param x2 the x coordinate for the second vertex
	 * @param y2 the y coordinate for the second vertex
	 */
	public LineObject(int x, int y, int x2, int y2)
	{
		Vertex v1= new Vertex(x, y);
		Vertex v2= new Vertex(x2, y2);
		Vertices= new ArrayList<Vertex>();
		Vertices.add(v1);
		Vertices.add(v2);
		if(x==x2)
		{
			orientation='v';
		}
		else if(y==y2)
			{
				orientation='h';
			}
		else
		{
			orientation='c';
		}
		
		if(orientation=='h' || orientation=='c')
		{
			slope=(v2.getYCoord()-v1.getYCoord())/(v2.getXCoord()-v1.getXCoord());
		}
		else
		{
			slope=0;
		}
		constant=v2.getYCoord()-slope*v2.getXCoord();
 
	}

	/**
	 * Retrieves the first vertex of the line
	 * @return v1
	 */
	public Vertex getV1()
 
	{
 
		return Vertices.get(0);	

	 }

	/**
	 * Retrieves the second vertex of the line
	 * @return v2 the second vertex
	 */
	public Vertex getV2()
	 
	{
 
		return Vertices.get(1);	

	 }
	
	
	/**
	 * Sets the first vertex of the line to v
	 * @param v is the new first vertex of the line
	 */
	public void setV1(Vertex v)

	{
		Vertices.set(0, v);
 
	}
 
	
	/**
	 * Sets the second vertex of the line to v
	 * @param v is the new second vertex of the line
	 */
	public void setV2(Vertex v)

	{
		Vertices.set(1, v);
 
	}
	
	/**
	 * Gets the orientation of the line
	 * @return orientation which could be 'h' for horizontal, 'v' for vertical, and 'c' for crazy/nonhorizontalvertical
	 */
	public char getOrientation()
	{
		return orientation;
	}
	public double getSlope() 
	{
 
		return slope;

	}
	public double getConstant() 
	{
 
		return constant;

	}
 
	

}
