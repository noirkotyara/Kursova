package flappySquare;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;


public class Start {
	
	public static boolean click = false;
	Button button1 = new Button (140, 510, 108, 64 , "src/playpressdark.png");
	Button button2 = new Button (140, 580, 108, 90 , "src/birdpressn.png");
	Button exit = new Button (330, 10, 50, 50, "src/exit.png");
	Button ok = new Button (140, 410, 120, 80 , "src/okk.png");  //540
	Button restart = new Button (20, 685, 120, 80 , "src/playpressdark.png"); 
	Button menu = new Button (330, 600, 50, 50, "src/menu.png");
	
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
