package flappySquare;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Birds {
	
	public static boolean click = false;
	Button bird1 = new Button (140, 452, 59, 44 , "src/bird2.png"); //bird2GO
	Button bird2 = new Button (200, 452, 59, 37 , "src/birdpink.png"); //birdpinkF
	Button bird3 = new Button (260, 452, 59, 59 , "src/birdgreen.png"); //bird3F
	
	class Button{
		
	//private Color color1;
	private double x;
	private double y;
	private double width;
	private double height;
	//public int i;
	//public String f;
	public String s;
	
	
	public Button(int x, int y, int width, int height, String s) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.s = s;
		//this.f = f;
		//color1 = Color.white;
		
	}
	
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public double getWidth() {
		return this.width;
	}
	public double getHeight() {
		return this.height;
	}
	
	
	public void update() {
		
		
	}
	
	public void draw (Graphics g) {
		
		g.drawImage(new ImageIcon(s).getImage(), (int) x, (int) y, null);
		//	g.drawImage(new ImageIcon(imgplay).getImage(), (int) x, (int) y, null);
		
	/*	g.setColor(color1);
		Font font = new Font("Arial", Font.ITALIC, 30);
		g.setFont(font);
		
		long length = (int) g.getFontMetrics().getStringBounds(f,g).getWidth();
		g.drawString(f, (int) (x+width/2) - (int) (length/2),(int) y+ (int) (height/3)*2);
	*/
	}
	}


}
