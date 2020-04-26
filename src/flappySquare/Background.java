package flappySquare;


import java.awt.Image;
import java.awt.Point;


import javax.swing.ImageIcon;

public class Background {
	
	class Background2{
		public Image back;
		public Point location;
		
		public Background2( Point pointnext) {
			back = new ImageIcon ("src/BackNight.png").getImage();
			location = pointnext;
		}
	}
	
	public int speed;
	public Background2[] back;
	public Background() {
		speed = 2;
		back = new Background2[2];
		back[0] = new Background2(new Point (0,0));
		back[1] = new Background2(new Point(893,0));
	}

		
		
		
		
}
