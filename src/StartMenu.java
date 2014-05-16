import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;



public class StartMenu extends Level {
	
	
	private boolean levelComplete;
	
	private BufferedImage bg1;
	private int height;
	private int width;
	private Player player;
	private String name;
	
	private boolean playPressed;
	private int clickX;
	private int clickY;
	private Rectangle playRect;
	
	public StartMenu(Player p, int w, int h) {
		
		super(p, w, h);
		
		try {
			bg1 = ImageIO.read(new File("blue-sphere-sized.png"));
		}
		catch (IOException ex) {
			System.out.println("Nope.");
		}
		
		height = h;
		width = w;
		levelComplete = false;
		setLevelNumber(-1); //ALWAYS SET THIS CORRECTLY
		
		playPressed = false;
		name = "Marble"; //just temporary!
		
		player = p;
		
		clickX = -1;
		clickY = -1;
		
	}
	
	public void setClicks(int x, int y) {
		clickX = x;
		clickY = y;
	}
	
	public void draw(Graphics2D g) {

		Font f = new Font("Candara", Font.PLAIN, 200);
		g.setFont(f);
		
		FontMetrics fm = g.getFontMetrics();
		int nameWidth = fm.stringWidth(name);

		
		g.drawString(name, width/2 - nameWidth/2, height/3);
		
		Font f2 = new Font("Candara", Font.PLAIN, 70);
		g.setFont(f2);
		FontMetrics fm2 = g.getFontMetrics();
		
		
		playRect = new Rectangle(width/2-100, height/3+200, 190, fm2.getAscent());
		GradientPaint grayscale = new GradientPaint((float)(playRect.getX()), (float)playRect.getY(), Color.DARK_GRAY, (float)(playRect.getX() + playRect.getWidth()/3*2), ((float)(playRect.getY())), Color.LIGHT_GRAY, true);
		g.setPaint(grayscale);
		g.fill(playRect);
		g.setPaint(null);
		g.setColor(Color.black);
		
		
		
	
		g.setColor(Color.black);
		g.drawString("PLAY", playRect.x, playRect.y + playRect.height);
		
		Font f3 = new Font("Candara", Font.PLAIN, 40);
		g.setFont(f3);
		g.setColor(Color.white);
		g.drawString("If you play the game, it'll be nearly impossible to get out.", width/20, playRect.y + 220);
		g.drawString("You'll be trapped. You'll have to find the portal to each new level. ", width/20, playRect.y + 260);
		g.drawString("Sometimes, they've left items to help you. Use them wisely.", width/20, playRect.y + 300);
		g.drawString("They don't want you to escape. They don't think you can.", width/20, playRect.y + 340);
		g.drawString("Do your best to prove them wrong.", width/20, playRect.y + 380);
		
		if(playClicked()) {
			levelComplete = true;
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
		}
		
		//	play.setVerticalTextPosition(AbstractButton.CENTER);
	 //   play.setHorizontalTextPosition(AbstractButton.LEADING);
		
	}
	
	public boolean checkComplete() {
		if(levelComplete == true) 
			return true;
		return false;
			
	}
	
	/***
	 * Method to determine whether the play button was clicked.
	 * @return the boolean of whether the button was clicked
	 */
	public boolean playClicked() {
		System.out.println("in play clicked");
		System.out.println(playRect.x + " is x");
		if(clickX >= playRect.x && clickX <= playRect.x + playRect.width && clickY >= playRect.y && clickY <= playRect.y + playRect.height) {
			return true;
		}
		return false;
	}
	

	
	
	
	
	
	
	
	
	
	
	
}