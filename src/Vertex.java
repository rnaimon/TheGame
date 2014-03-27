public class Vertex
 
{
 
	private int xCoord;
	private int yCoord;

	
 
	/**
	 * The constructor that creates the new Vertex object
	 * @param x X-Coordinate of the vertex
	 * @param y Y-Coordinate of the vertex
	 */
	public Vertex(int x, int y)
	{
		xCoord=x;
		yCoord=y;
 
	}

	/**
	 * Retrieves the X-Coordinate
	 * @return xCoord
	 */
	public int getXCoord()
 
	{
 
		return xCoord;	

	 }
	/**
	 * Retrieves the Y-Coordinate
	 * @return xCoord
	 */
	public int getYCoord()
 
	{
 
		return yCoord;	

	 }
	/**
	 * Sets the X-Coordinate
	 * @param x is the new XCoord
	 */
	public void setXCoord(int x)

	{
		xCoord=x;
 
	}
 
	
	/**
	 * Sets the Y-Coordinate
	 * @param y is the new YCoord
	 */
	public void setYCoord(int y)

	 {
 
		yCoord=y;
 
	}
 
	

}
