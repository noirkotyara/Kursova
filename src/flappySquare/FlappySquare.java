package flappySquare;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappySquare implements ActionListener{
	
	public static FlappySquare flappySquare;
	public final int width = 400, height = 800;
	public Render render;
	public Rectangle bird;
	public ArrayList<Rectangle> columns;
	public Random random;
	public int ticks, yMotion;
	
	public FlappySquare() {
		JFrame jframe = new JFrame();
		render = new Render();
		Timer timer = new Timer(20, this);
		random = new Random();
		
		jframe.add(render);
		jframe.setTitle("flappySquare");  
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(width, height);
		jframe.setResizable(true);
		jframe.setVisible(true);
		
		bird = new Rectangle(100, 200, 20, 20 );
		columns = new ArrayList<Rectangle>();
		
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		
		timer.start();
	}
	public void addColumn(boolean a) {
		int space = 300;
		int Width = 70;
		int offset = 60 + random.nextInt(400); //   50    300
		
		if(a) {
		columns.add(new Rectangle(width + Width + columns.size()*125 , height - offset - 145, Width, offset));
		columns.add(new Rectangle(width + Width + (columns.size() - 1)*125,0, Width, height - offset - space ));
	}	
		//else {
			//columns.add(new Rectangle(columns.get(columns.size() - 1).x, height - Height - 145, Width, Height));
			//columns.add(new Rectangle(columns.get(columns.size() - 1).x,0, Width, height - Height - space ));
		//}
		}
		
	public void paintColumn(Graphics g, Rectangle column) {
		
		g.setColor(Color.orange);
		g.fillRect(column.x, column.y,column.width, column.height);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ticks ++;
		float speed = 6;
		for(int i = 0; i < columns.size(); i++) {
			Rectangle column = columns.get(i);
			column.x -= speed;
			
		}
		
		if ( ticks % 2 == 0 && yMotion < 15) {
			yMotion += 2;
		}
		
		for(int i = 0; i < columns.size(); i++) {
			Rectangle column = columns.get(i);
			
			if(column.x + column.width < 500 ) {
				
				if(column.y != 0) 
				addColumn(true);
				if (columns.size()<=4) {
					addColumn(false);
				}
			}
		
			
		}
		
		bird.y += yMotion; /// can be changed for another step of the game //bird.y -=ymotion
		render.repaint();
	}
	
	
	
	public void repaint(Graphics g) {
		
		g.setColor(Color.white);  //background
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.black);//bird
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
		
		for(Rectangle column : columns) {//: in
			paintColumn(g, column);
		}
		g.setColor(Color.DARK_GRAY);//platform
		g.fillRect(0, height - 150, width, 150);
		
		g.setColor(Color.black);//grass
		g.fillRect(0, height - 150, width, 15);
		
		
	}
	
	
	public static void main(String[] args) {
		flappySquare = new FlappySquare();
	}

	
}