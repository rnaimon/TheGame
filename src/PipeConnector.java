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
	private ArrayList<Vertex> vertices;
	private int startx;
	private int starty;
	private Obstacles perimeter;
	/***
	 * This is the constructor without passed-in variables.
	 */
	public PipeConnector(Obstacles o, int startx, int starty) 
	{
		perimeter=o;
		img=new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g=(Graphics2D)(img.getGraphics());
		
		shape=new Polygon();
		vertices= o.getVertices();
		for(int i=0; i<vertices.size(); i++)
		{
			shape.addPoint(vertices.get(i).getXCoord(), vertices.get(i).getYCoord());
		}
		
		setColor(Color.cyan);
		g.setColor(getColor());
		g.drawPolygon(shape);
		
	}
	public Obstacles getPerimeter()
	{
		return perimeter;
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
	public ArrayList<Vertex> getVertices()
	{
		return vertices;
	}
	public void reDraw()
	{
		img=new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=(Graphics2D)(img.getGraphics());
		g.setColor(getColor());
		g.drawPolygon(shape);
	}
	public void changeSize(int x)
	{
		img=new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		shape=new Polygon();
		for(int i=0; i<vertices.size(); i++)
		{
			shape.addPoint(vertices.get(i).getXCoord()*x, vertices.get(i).getYCoord()*x);
		}
		Graphics2D g=(Graphics2D)(img.getGraphics());
		g.setColor(Color.cyan);
		g.fillPolygon(shape);
	}
	public int getStartX()
	{
		return startx;
	}
	public int getStartY()
	{
		return starty;
	}
	public void setStartX(int x)
	{
		startx=x;
	}
	public void setStartY(int y)
	{
		starty=y;
	}
	
	
}