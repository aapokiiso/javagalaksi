import java.awt.*;
import javax.swing.*;
public class applet extends JApplet{
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.YELLOW);
		g.fillOval(1, 1, 235, 220);
		
		g.setColor(Color.MAGENTA);
		g.fillOval(10, 10, 100, 100);
		
		g.setColor(Color.BLACK);
		g.fillOval(50, 50, 20, 20);
		
		g.setColor(Color.MAGENTA);
		g.fillOval(120, 10, 100, 100);
		
		g.setColor(Color.BLACK);
		g.fillOval(160, 50, 20, 20);
		
		g.setColor(Color.ORANGE);
		g.fillOval(95, 100, 40, 20);
		
		g.setColor(Color.RED);
		g.fillRoundRect(40, 150, 148, 30, 20, 20);
		
		g.setColor(Color.WHITE);
		g.fillRect(46, 150, 136, 5);
		
		g.setColor(Color.WHITE);
		g.fillRect(60, 175, 105, 5);
		
		
	}
	
}
