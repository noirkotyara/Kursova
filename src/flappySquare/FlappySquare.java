package flappySquare;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class FlappySquare extends JPanel implements ActionListener, MouseListener, KeyListener{
	
	/**
	 * noirkotyara's project
	 */
	private static final long serialVersionUID = 1L;
	public static FlappySquare flappySquare;
	public final int width = 400, height = 800;
	public Render render;
	public Rectangle bird;
	public ArrayList<Rectangle> columns;
	public Random random;
	public int ticks, yMotion;
	public boolean gameOver;
	public boolean startspace;
	public boolean started;
	public int score;
	public JButton myButton;
	public JTextField textField;
	Start start = new Start();
	public static int mouseX;
	public static int mouseY;
	public String imgplay;
	public int scoretemporary;
	public int startercount ;
	public JFrame jframe = new JFrame();
	
	public FlappySquare() {    
		
		
		render = new Render();
		Timer timer = new Timer(20, this);
		random = new Random();
		
		
		jframe.add(render);
		jframe.setTitle("flappySquare");  
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(width, height);
		jframe.setResizable(false);
		jframe.setVisible(true);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);

		
		
		bird = new Rectangle(100, 200, 59, 44 );
		columns = new ArrayList<Rectangle>();
		columns.clear();
		
		
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		
		
		timer.start();
	}
	
	public void addColumn(boolean a) {
		int space = 320;
		int Width = 60;
		int offset = 60 + random.nextInt(400); //   50    300
		
		if(a) {
		columns.add(new Rectangle(width + Width + columns.size()*150 , height - offset - 145, Width, offset)); 
		columns.add(new Rectangle(width + Width + (columns.size() - 1)*150,0, Width, height - offset - space ));
	}	
		
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

			
			bird = new Rectangle(100, 200, 59, 44 );
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
			startercount++;
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
			int space = 320;
			if(!gameOver && column.y == 0 && bird.x + 10 > column.x + 70 - speed && bird.x + 10< column.x + 70 + speed ) {			
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
		if(bird.y  > height - 250 + bird.height) {
				bird.y  = height - 250 + bird.height;
			}	
		if(bird.y < 0) {
			bird.y = 0;
		}
		}
		
		
		bird.y += yMotion; /// can be changed for another step of the game //bird.y -=yMotion
	}
		else {
			if (   MouseInfo.getPointerInfo().getLocation().x > start.button1.getX()  && MouseInfo.getPointerInfo().getLocation().x  < start.button1.getX() + start.button1.getWidth() && MouseInfo.getPointerInfo().getLocation().y > start.button1.getY()  && MouseInfo.getPointerInfo().getLocation().y < start.button1.getY() + start.button1.getHeight()) {
				start.button1.s = "src/play.png";
				
			}
			else {
				start.button1.s = "src/playpressdark.png";
			}
		}
		
		
		render.repaint();
	}
	
	
	public void repaint(Graphics g) {
		
	//	g.setColor(Color.white);  //background
	//	g.fillRect(0, 0, width, height);
		
		Image imgbackground = new ImageIcon("src/BackNight.png").getImage();
		g.drawImage(imgbackground, 0, 0, null);
		
	/*	if(!started) {            																don`t forget about this shit
			JButton buttonPlay = new JButton("");
		
		buttonPlay.setIcon(new ImageIcon("src/play.png"));
		
		jframe.add(buttonPlay);	
		
	      buttonPlay.setBounds(40,100,110,60);
	      
		}*/
		
		
		//Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
      //  myButton.setCursor(cursor);
		
		for(Rectangle column : columns) {//: in
			paintColumn(g, column);
		}
		g.setColor(Color.lightGray);//platform
		g.fillRect(0, height - 150, width, 150);
		
		g.setColor(Color.black);//grass
		g.fillRect(0, height - 150, width, 15);
		
		//g.setColor(Color.);
		if(!gameOver) {
			Image imgbird2 = new ImageIcon("src/bird2.png").getImage();
		g.drawImage(imgbird2, bird.x, bird.y, null);
		}
		else {
			Image imgbird2GO = new ImageIcon("src/bird2GO.png").getImage();
			g.drawImage(imgbird2GO, bird.x, bird.y, null);
		}
		
				
		
		if(gameOver) {
			//Image imgoops = new ImageIcon("src/oopsBIG.gif").getImage();
			//g.drawImage(imgoops, -60, 40, null);
			
			Image imgGameover = new ImageIcon("src/GameOver.png").getImage();
			g.drawImage(imgGameover, 90, 200, null);
			
		
		}
		if(!started) {
		
			//start.draw(g);
			start.button1.draw(g);
			
		//	Image imgclick = new ImageIcon("src/Egne.gif").getImage();
			//g.drawImage(imgclick, 90, 340, null);
			
		}
		if(started) {
			g.setColor(Color.black);
		g.setFont(new Font("Arial",5,50 ));
			g.drawString(String.valueOf(score), width / 2 -20 ,60);
			
			
		
		}
		
	
		
			if(gameOver) {
				
				
				if(score > scoretemporary) {
					scoretemporary = score;
					g.setColor(Color.black);
					g.setFont(new Font("Arial",5,100 ));
						g.drawString(String.valueOf(scoretemporary), width / 2 -20 ,30);
				}
				else if (score < scoretemporary){
					g.setColor(Color.black);
				g.setFont(new Font("Arial",5,100 ));
					g.drawString(String.valueOf(scoretemporary), width / 2 -20 ,150);
				}
			
			
		}
		
	}
	
	
	


	public static void main(String[] args) {
		flappySquare = new FlappySquare();
		System.out.println("Nice to meet you)\nFlappy Square is glad to see you)\nLet`s try this challenge!\nGood luck!");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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