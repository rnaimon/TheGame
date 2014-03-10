import java.awt.*;

/*Interface for items in the game */
//Rebecca Naimon



public interface ItemInterface {
	
//	Color color;
	
	//comment text that appears when the player draws close to an item
//	String commentText;
	
	public Color getColor();
	public void displayComment();
	
	public void displayDescription();
	
	public void draw(Graphics2D g);
	

	
	
}

