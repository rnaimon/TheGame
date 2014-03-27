
import java.awt.*;

/***
 *  Class for Item, which the player can use to solve puzzles, shoot things, and travel.
 * @author Rebecca Naimon
 * @author Michael Katz
 *
 */
public class Item implements ItemInterface {
	
	private Color color;
	private String comment;
	private String description;
	private String name;
	
	
	
	/***
	 * Constructor without passed-in variables.2
	 */
	public Item() {
		color = new Color(0, 0, 0);
		comment = "";
	}
	
	/***
	 * Constructor with the passed-in variables.
	 * @param c
	 */
	public Item (Color c, String n, String com) {
		color = c;
		name=name;
		comment = com;
	}
	
	
	
	/***
	 * This method returns the Color the Item has.
	 */
	public Color getColor() {
		
		return color;
		
	}
	
	/***
	 * This method displays the comment, whatever it may be, in the correct 
	 * location near the item at the correct time. 
	 */
	public void displayComment() {
		
		// will contain code to display the comment above the item when appropriate
		
		
	}
	/*
	/***
	 * This method displays the description (and picture) of what the item is when the player
	 * first picks up an item.
	 
	public void displayDescription() {
		
		/* to hold code to display the description correctly when the player first
		picks up an item **
		
	}
	
	public void draw(Graphics2D g) {
	
		
	}
*/
	
	
	/* A number of getters and setters follow. */
	
	
	public int getXCoord() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getYCoord() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setRadius(int r) {
		// TODO Auto-generated method stub
		
	}

	public void setXCoord(int x) {
		// TODO Auto-generated method stub
		
	}

	public void setYCoord(int y) {
		// TODO Auto-generated method stub
		
	}

	public void setColor(Color c) {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String s) {
		// TODO Auto-generated method stub
		
	}

	public void setDescription(String s) {
		// TODO Auto-generated method stub
		
	}
	
	
}