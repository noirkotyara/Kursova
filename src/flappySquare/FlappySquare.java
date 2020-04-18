package flappySquare;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappySquare implements ActionListener, MouseListener, KeyListener{
	
	public static FlappySquare flappySquare;
	public final int width = 400, height = 800;
	public Render render;
	public Rectangle bird;
	public ArrayList<Rectangle> columns;
	public Random random;
	public int ticks, yMotion;
	public boolean gameOver;
	public boolean started;
	public int score;
	
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
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		
		bird = new Rectangle(100, 200, 20, 20 );
		columns = new ArrayList<Rectangle>();
		columns.clear();
		
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		
		
		timer.start();
	}
		
		
	
	public void addColumn(boolean a) {
		int space = 300;
		int Width = 60;
		int offset = 60 + random.nextInt(400); //   50    300
		
		if(a) {
		columns.add(new Rectangle(width + Width + columns.size()*150 , height - offset - 145, Width, offset)); 
		columns.add(new Rectangle(width + Width + (columns.size() - 1)*150,0, Width, height - offset - space ));
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
	 
	public void fly() {
		
		
		
		if(!gameOver) {
			yMotion = 0;
		}
		yMotion -=10;
	}
	
	public void start(){
		if(gameOver) {

			
			bird = new Rectangle(100, 200, 20, 20 );
			columns = new ArrayList<Rectangle>();
			columns.clear();
			
			yMotion = 0;
			score = 0;
			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);
			gameOver = false;
		}
		if(!started) {
			started = true;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ticks ++;
		if(started) {
		float speed = 5;
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
		
		for (Rectangle column : columns) {
			int space = 300;
			if(column.y == 0 && bird.x + bird.width / 2 > column.x + column.width/2 - speed && bird.x + bird.width / 2 < column.x + column.width/2 + speed ) {			
				score++;
				
			}
			if (column.intersects(bird) ) {
				gameOver = true;
				
				
				if(bird.x <= column.x)	{
				bird.x = column.x - bird.width;
				}
			
			if (bird.x >= column.x) {
				if (column.y != 0 ) {
					bird.y = column.y  - bird.height ;
				}
				if(column.y == 0 && column.height > bird.y) {
					bird.y = column.height;
				}
			}
			
				if(bird.y > column.y + column.height ) {
					bird.y = column.y + column.height;
			if(column.y == 0 && bird.y + bird.height > column.y + column.height + space) {
				
					bird.y = column.y -bird.height + column.height + space;
				
			}
			}
				
			}
				
		}
		
		
		if(bird.y > height - 150 || bird.y < 0) {
				gameOver = true;
				
			}
		if(gameOver) {
		if(bird.y  > height - 205 + bird.height) {
				bird.y  = height - 205 + bird.height;
			}	
		}
		
		
		
		bird.y += yMotion; /// can be changed for another step of the game //bird.y -=yMotion
	}
		render.repaint();
	}
	
	
	
	public void repaint(Graphics g) {
		
		g.setColor(Color.white);  //background
		g.fillRect(0, 0, width, height);
		
		for(Rectangle column : columns) {//: in
			paintColumn(g, column);
		}
		g.setColor(Color.DARK_GRAY);//platform
		g.fillRect(0, height - 150, width, 150);
		
		g.setColor(Color.blue);//grass
		g.fillRect(0, height - 150, width, 15);
		
		g.setColor(Color.black);//bird
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
		
		
		
		
		if(gameOver) {
			g.setColor(Color.black);
		g.setFont(new Font("Arial",5,50 )); // 5 - жирність 50 - size
		
			g.drawString("Game Over", 50, height/3 );
		}
		if(!started) {
			g.setColor(Color.black);
			g.setFont(new Font("Arial",5,20 ));
			g.drawString("Press space to play", 0, height/2);
			
		}
		g.setColor(Color.black);
		g.setFont(new Font("Arial",5,50 ));
			g.drawString(String.valueOf(score), width / 2 -20 ,60);
			
		
		
	}
	
	
	public static void main(String[] args) {
		flappySquare = new FlappySquare();
		System.out.println("Nice to meet you)\nFlappy Square is glad to see you)\nLet`s try this challenge!\nGood luck!");
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	//	fly();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			fly();
			
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			start();
			
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}
	
	
			


	
}