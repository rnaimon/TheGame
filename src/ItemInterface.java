import java.awt.*;

/*Interface for items in the game */




public interface ItemInterface {

	/**
	 * This method "gets" the XCoordinate of the item's center.
	 * @return xCoord
	 */
	public int getXCoord();
	
	/**
	 * This method "gets" the YCoordinate of the item's center.
	 * @return yCoord
	 */
	public int getYCoord();
	
	/**
	 * This method "gets" the radius of the item
	 * @return radius
	 */
	public int getRadius();
	
	/**
	 * This method "sets" the radius of the item
	 * @param r is the new radius of the item 
	 */
	public void setRadius(int r);
	
	/**
	 * This method sets the xCoordinate to a new value, x
	 * @param x
	 */
	public void setXCoord(int x);
	
	/**
	 * This method sets the yCoordinate to a new value, y
	 * @param y
	 */
	public void setYCoord(int y);
	
	/**
	 * This method "gets" the color associated with the item
	 * @return color
	 */
	public Color getColor();
	
	/**
	 * This method "sets" the color associated with the item
	 * @param c is the new color
	 */
	public void setColor(Color c);
	
	/**
	 * This method returns the name of the item
	 * @return name is the name of the item
	 */
	public String getName();
	
	/**
	 * This method "gets" the description of the item (etc. how the item works)
	 * @return s the description of the item
	 */
	public String getDescription();
	
	/**
	 * This method sets the name of the item to s
	 * @param s is the new name of the item
	 */
	public void setName(String s);
	
	/**
	 * This method sets the description of the item to s, includes how it works
	 * @param s is the new description of the item
	 */
	public void setDescription(String s);

	//public void displayComment();
	
	

	
	
}

