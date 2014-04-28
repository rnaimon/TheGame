import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/***
 * This is the Projectile class to control projectiles that come from items, like darts 
 * or plasma blasts. They all behave like Projectiles.
 * @author Rebecca Naimon
 *
 */
public class PipeConnector extends Item{
	private Polygon shape;
	private BufferedImage img;
	private int centx;
	private int centy;
	/***
	 * This is the constructor without passed-in variables.
	 */
	public PipeConnector(Obstacles o, int startx, int starty) 
	{
		img=new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g=(Graphics2D)(img.getGraphics());
		
		shape=new Polygon();
		ArrayList<Vertex> v= new ArrayList<Vertex>();
		for(int i=0; i<v.size(); i++)
		{
			shape.addPoint(v.get(i).getXCoord(), v.get(i).getYCoord());
		}
		g.setColor(Color.cyan);
		g.drawPolygon(shape);
		
	}
	
	
	public BufferedImage getImage()
	{
		return img;
	}
	/***
	 * This method returns the top left x-coordinate.
	 */
	public Polygon getShape() {
		return shape;
	}
	
	
}