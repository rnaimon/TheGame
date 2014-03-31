import java.util.ArrayList;



/***
 *  This is the class for a switch, or a button, which, when touched by another object,
 * performs some function (to be outlined in the actual level subclass).
 * @author Rebecca Naimon
 *
 */
public class Switch extends Obstacles {
	
	private boolean contacted;
	private double centX;
	private double centY;
	
	/***
	 * This constructor sets the default contacted value to false and instantiates
	 * the Obstacle.
	 * @param o is the ArrayList of LineOjects that goes into Obstacles
	 */
	public Switch(ArrayList<LineObject> o) {
		super(o);
		centX=(o.get(0).getV2().getXCoord() - o.get(0).getV1().getXCoord())/2 + o.get(0).getV1().getXCoord();
		centY=(o.get(1).getV2().getYCoord() - o.get(0).getV1().getYCoord())/2 + o.get(1).getV1().getYCoord();
		contacted = false;
	}
	
	public double getCentX()
	{
		return centX;
	}
	
	public double getCentY()
	{
		return centY;
	}
	
	
	/***
	 * This method changes the status of whether the switch has been contacted or not,
	 * so that the level object knows how its environment should be, according to the
	 * switch.
	 * 
	 */
	public void changeContactStatus(){
		
		if (contacted == false)
			contacted = true;
		else
			contacted = false;
	}
	
	
	
	 
	
	
	
	
	
}

