/* Class for Item, which the player can use to solve puzzles.*/
//Rebecca Naimon

import java.awt.*;

public class Item implements ItemInterface {
	
	private Color color;
	private String comment;
	private String description;  
	
	
	
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
	public Item (Color c, String com) {
		color = c;
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
		
		// to hold code to display the comment above the item when appropriate
		
		
	}
	
	/***
	 * This method displays the description (and picture) of what the item is when the player
	 * first picks up an item.
	 */
	public void displayDescription() {
		
		/* to hold code to display the description correctly when the player first
		picks up an item */
		
	}
	
	public void draw(Graphics2D g) {
	
		
	}
	
	
}