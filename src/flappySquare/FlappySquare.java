package flappySquare;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappySquare implements ActionListener{
	int ImMalukh_Diana;
	public static FlappySquare flappySquare;
	public final int width = 400, height = 800;
	public Render render;
	public Rectangle bird;
	
	public FlappySquare() {
		JFrame jframe = new JFrame();
		render = new Render();
		Timer timer = new Timer(20, this);
		
		
		jframe.add(render);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(width, height);
		jframe.setResizable(false);
		jframe.setVisible(true);
		
		bird = new Rectangle(100, 200, 20, 20 );
		
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		render.repaint();
	}
	
	
	public void repaint(Graphics g) {
		
		g.setColor(Color.white);  //background
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.black);//bird
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
		
		g.setColor(Color.DARK_GRAY);//platform
		g.fillRect(0, height - 150, width, 150);
		
		g.setColor(Color.black);//grass
		g.fillRect(0, height - 150, width, 15);
	}
	
	
	public static void main(String[] args) {
		flappySquare = new FlappySquare();
	}

	
}