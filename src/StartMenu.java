import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;



public class StartMenu extends Level implements MouseListener{
	
	
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
	
	public void draw(Graphics2D g) {

		Font f = new Font("Candara", Font.PLAIN, 200);
		g.setFont(f);
		
		FontMetrics fm = g.getFontMetrics();
		int nameWidth = fm.stringWidth(name);
		
		
		g.drawString(name, width/2 - nameWidth/2, height/3);
		
		playRect = new Rectangle(160, 460, 235, 50);
		GradientPaint grayscale = new GradientPaint((float)(playRect.getX()), (float)playRect.getY(), Color.DARK_GRAY, (float)(playRect.getX() + playRect.getWidth()/3*2), ((float)(playRect.getY())), Color.LIGHT_GRAY, true);
		g.setPaint(grayscale);
		g.fill(playRect);
		
		Font f2 = new Font("Candara", Font.PLAIN, 70);
		g.setFont(f);
		g.drawString("PLAY", playRect.x, playRect.y);
		
		if(playClicked()) {
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXX");
		}
		
		//	play.setVerticalTextPosition(AbstractButton.CENTER);
	 //   play.setHorizontalTextPosition(AbstractButton.LEADING);
		
	}
	
	/***
	 * Method to determine whether the play button was clicked.
	 * @return the boolean of whether the button was clicked
	 */
	public boolean playClicked() {
		
		if(clickX >= playRect.x && clickX <= playRect.x + playRect.width && clickY >= playRect.y && clickY <= playRect.y + playRect.height) {
			return true;
		}
		return false;
	}
	

	
	
	
	
	
	
	
	
	
	
	
}