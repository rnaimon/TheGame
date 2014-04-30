import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;



/***
 *  This is the class for a switch, or a button, which, when touched by another object,
 * performs some function (to be outlined in the actual level subclass).
 * @author Rebecca Naimon
 *
 */
public class PipeConnectorSwitch extends Switch {
	
	private boolean contacted;
	private double centX;
	private double centY;
	private boolean inactive;
	private PipeConnector symbol;
	
	/***
	 * This constructor sets the default contacted value to false and instantiates
	 * the Obstacle.
	 * @param o is the ArrayList of LineOjects that goes into Obstacles
	 */
	public PipeConnectorSwitch(ArrayList<LineObject> o, PipeConnector p) {
		super(o);
		symbol=new PipeConnector(p.getPerimeter(),0,0);
		int[] vertx= new int[getVertices().size()];
		int[] verty= new int[getVertices().size()];
		for(int j=0; j< getVertices().size(); j++)
		{
			vertx[j]=(int)(getVertices().get(j).getXCoord());
			verty[j]=(int)(getVertices().get(j).getYCoord());
		}
		centX=Math.abs(vertx[1]- vertx[0]) + vertx[0];
		centY=Math.abs(verty[1]- verty[0]) + verty[0];
	}
	
	public PipeConnector getSymbol()
	{
		return symbol;
	}
	
	public void setSymbol(PipeConnector p)
	{
		symbol=p;
	}
	
	@Override
	public void drawSwitch(Graphics2D g)
	{
		int[] vertx= new int[getVertices().size()];
		int[] verty= new int[getVertices().size()];
		for(int j=0; j< getVertices().size(); j++)
		{
			vertx[j]=(int)(getVertices().get(j).getXCoord());
			verty[j]=(int)(getVertices().get(j).getYCoord());
		}
		g.fillPolygon(vertx, verty, (getVertices().size()));
		int length= Math.abs(vertx[1]- vertx[0]);
		if(getContacted())
		{
			symbol.setColor(Color.cyan);
			System.out.println("here");
		}
		else
			symbol.setColor(Color.magenta);
		symbol.reDraw();
		symbol.setStartX(vertx[0] + length/3);
		symbol.setStartY(verty[0]);
		g.drawImage(symbol.getImage(),null, symbol.getStartX(), symbol.getStartY());
	
	}
	
	 
	
	
	
	
}

