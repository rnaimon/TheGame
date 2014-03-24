import java.util.ArrayList;

/* This is the class for a switch, or a button, which, when touched by another object,
performs some function (to be outlined in the actual level subclass).

//Rebecca Naimon made this.


/***
 * This is the class for a switch, or a button, which, when touched by another object,
 * performs some function.
 * @author Rebecca Naimon
 *
 */
public class Switch extends Obstacles {
	
	private boolean contacted;
	
	/***
	 * This constructor sets the default contacted value to false and instantiates
	 * the Obstacle.
	 * @param o is the ArrayList of LineOjects that goes into Obstacles
	 */
	public Switch(ArrayList<LineObject> o) {
		super(o);
		contacted = false;
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

