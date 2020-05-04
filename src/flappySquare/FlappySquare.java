package flappySquare;
 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import flappySquare.Start.Button;

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
	public JTextField textField;
	public boolean choosebirds = false;
	public boolean spacepressed = false;
	
	public boolean bird1 = false;
	public boolean bird2 = false;
	public boolean bird3 = false;
	public boolean	havechoosen = false;

	
	Start start = new Start();
	Background background = new Background();
	Birds birds = new Birds();
	
	public static int mouseX;
	public static int mouseY;
	public String imgplay;
	public int scoretemporary = 0;
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
	//	if(started == true && gameOver == false) {
			g.setColor(Color.orange);
		g.fillRect(column.x, column.y,column.width, column.height);
	//	}
		
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
	
	//public void chooseBird(){
		
				
	//		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		ticks ++;
		if (choosebirds == false) {
			
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
		
		touchButton(start.restart);
		touchButton(start.menu);
		}
		
		
		bird.y += yMotion; 
		}
		else {
			touchButton(start.button1);
			touchButton(start.button2);
			touchButton(start.exit);
			
			
			
		
			
		}
		
		
		}
		else if (choosebirds == true) {
			touchBird(birds.bird1);
			touchBird(birds.bird2);
			touchBird(birds.bird3);
			touchButton(start.ok);
		}
		//background is moving
		for(int i = 0; i < 2; i++) {
				background.back[i].location.x = (int) (background.back[i].location.x - background.speed);	
			}
			
			if(background.back[0].location.x < -893) {
				background.back[0].location.x = 0;
				background.back[1].location.x = 893;
			}
		/////////////////////////////////////////////
		render.repaint();
	}
	
	 
	
	public void touchButton(Start.Button b) {
				if (MouseInfo.getPointerInfo().getLocation().x > b.getX()  && MouseInfo.getPointerInfo().getLocation().x  < b.getX() + b.getWidth() && MouseInfo.getPointerInfo().getLocation().y > b.getY()  && MouseInfo.getPointerInfo().getLocation().y < b.getY() + b.getHeight()) {
				
				if(b.equals(start.button1)) { b.s = "src/play.png";}
				if(b.equals(start.button2)) { b.s = "src/birdpressndark.png";}
				if(b.equals(start.exit)) { b.s = "src/exitpress.png";}
				
			}
			else {
				if(b.equals(start.button1)) { b.s = "src/playpressdark.png";}
				if(b.equals(start.button2)) { b.s = "src/birdpressn.png";}
				if(b.equals(start.exit)) { b.s = "src/exit.png";}
				
			}
				if(choosebirds == true) {
					if (MouseInfo.getPointerInfo().getLocation().x > b.getX()  && MouseInfo.getPointerInfo().getLocation().x  < b.getX() + b.getWidth() && MouseInfo.getPointerInfo().getLocation().y > b.getY()  && MouseInfo.getPointerInfo().getLocation().y < b.getY() + b.getHeight()) {
					if(b.equals(start.ok)) { b.s = "src/okkk.png";}
					}
					else {
						if(b.equals(start.ok)) { b.s = "src/okk.png";}
					}
				}
				if(gameOver) {
					if (MouseInfo.getPointerInfo().getLocation().x > b.getX()  && MouseInfo.getPointerInfo().getLocation().x  < b.getX() + b.getWidth() && MouseInfo.getPointerInfo().getLocation().y > b.getY()  && MouseInfo.getPointerInfo().getLocation().y < b.getY() + b.getHeight()) {
						
						if(b.equals(start.restart)) { b.s = "src/play.png";}
						if(b.equals(start.menu)) { b.s = "src/menu.png";}
					}
					else {
						if(b.equals(start.restart)) { b.s = "src/playpressdark.png";}
						if(b.equals(start.menu)) { b.s = "src/menu.png";}
					}
					
				}
				
			}
	
	
	public void touchBird(Birds.Button bb) {
		if (MouseInfo.getPointerInfo().getLocation().x > bb.getX()  && MouseInfo.getPointerInfo().getLocation().x  <  bb.getX() + bb.getWidth() && MouseInfo.getPointerInfo().getLocation().y > bb.getY()  && MouseInfo.getPointerInfo().getLocation().y < bb.getY() + bb.getHeight()) {
			if(bb.equals(birds.bird1)) { bb.s = "src/bird2.png";}
			if(bb.equals(birds.bird2)) { bb.s = "src/birdpink.png";}
			if(bb.equals(birds.bird3)) { bb.s = "src/birdgreen.png";}
		}
		else {
			if(bb.equals(birds.bird1)) { bb.s = "src/bird2transp.png";}
			if(bb.equals(birds.bird2)) { bb.s = "src/birdpinktransp2.png";}
			if(bb.equals(birds.bird3)) { bb.s = "src/birdgreentransp2.png";}
			
			
		}		
	}
	
	public void repaint(Graphics g) {
		
		
		for( int i = 0; i < 2; i++) {
			g.drawImage(background.back[i].back, background.back[i].location.x,background.back[i].location.y, null);
		}
		
		if(started == true) {
			for(Rectangle column : columns) {//: in
			paintColumn(g, column);
		}
		}
		
		
		g.setColor(Color.black);//platform
		g.fillRect(0, height - 150, width, 150);
		
		g.setColor(Color.DARK_GRAY);//grass
		g.fillRect(0, height - 150, width, 15);
		
		
	
		
		if(choosebirds == false) {
			
		
		
		if(!gameOver) {
			if(bird1 == true) {
				Image imgbird2 = new ImageIcon("src/bird2.png").getImage();
		g.drawImage(imgbird2, bird.x, bird.y, null);
			}
			else if (bird2 == true) {
				Image imgbird2 = new ImageIcon("src/birdpink.png").getImage();
				g.drawImage(imgbird2, bird.x, bird.y, null);
			}
			else if (bird3 == true) {
				Image imgbird2 = new ImageIcon("src/birdgreen.png").getImage();
				g.drawImage(imgbird2, bird.x, bird.y, null);
			}
			else {
				Image imgbird2 = new ImageIcon("src/bird2.png").getImage();
				g.drawImage(imgbird2, bird.x, bird.y, null);
			}
		}
		else {
			if(bird1 == true) {
				Image imgbird2GO = new ImageIcon("src/bird2GO.png").getImage();
			g.drawImage(imgbird2GO, bird.x, bird.y, null);
			}
			else if (bird2 == true) {
				Image imgbird2 = new ImageIcon("src/birdpinkF.png").getImage();
				g.drawImage(imgbird2, bird.x, bird.y, null);
			}
			else if( bird3 == true) {
				Image imgbird2 = new ImageIcon("src/bird3F.png").getImage();
				g.drawImage(imgbird2, bird.x, bird.y, null);
			}else {
				Image imgbird2GO = new ImageIcon("src/bird2GO.png").getImage();
				g.drawImage(imgbird2GO, bird.x, bird.y, null);
			}
		}	
		
		if(gameOver) {
					
			Image imgGameover = new ImageIcon("src/GameOverwhite.png").getImage();
			g.drawImage(imgGameover, 50, 100, null);
			
			
			Image imgscoretable = new ImageIcon("src/tbrechor.png").getImage();
		//	Image imgscoretable = new ImageIcon("src/tsrechorpink.png").getImage();
			g.drawImage(imgscoretable, 20, 210, null);
			
		Image imgclick = new ImageIcon("src/fire.gif").getImage();
			g.drawImage(imgclick, 90, 450, null);
		}
		start.exit.draw(g);
		if(!started) {
		
			start.button1.draw(g);
			start.button2.draw(g);
			
			
			Image imgfb = new ImageIcon("src/fblastp.png").getImage();
			g.drawImage(imgfb, 30, 180, null);
		}
		if(started) {
			g.setColor(Color.black);
			g.setFont(new Font("Arial",5,50 ));
			g.drawString(String.valueOf(score), width / 2 -20 ,60);
		
			Image imglogof = new ImageIcon("src/flappyNight.png").getImage();
			g.drawImage(imglogof, 220,680, null);
			
			Image imglogob = new ImageIcon("src/birdNight.png").getImage();
			g.drawImage(imglogob, 305, 705, null);
			if(spacepressed == false) {
				Image imgspace = new ImageIcon("src/space2.png").getImage();
			g.drawImage(imgspace, 14, 620, null);
			}
			
			
		}
			if(gameOver) {
				if(score >= 10) {
					g.setColor(Color.cyan);
					g.setFont(new Font("Arial",5,35 ));
					g.drawString(String.valueOf(score), 240 ,305);
				}else {
					g.setColor(Color.cyan);
			g.setFont(new Font("Arial",5,40 ));
			g.drawString(String.valueOf(score), 248 ,305);
				}
				
			
			if(scoretemporary == 0) {
				scoretemporary = score;
				if(scoretemporary >= 10) {
					g.setColor(Color.orange);
					g.setFont(new Font("Arial",5,35 ));
						g.drawString(String.valueOf(scoretemporary), 105 ,367);
				}else {
					g.setColor(Color.orange);
				g.setFont(new Font("Arial",5,40 ));
					g.drawString(String.valueOf(scoretemporary), 115 ,367);
				}
				
			}
			else	if(score >= scoretemporary) {
					scoretemporary = score;
					if(scoretemporary > 10) {
						g.setColor(Color.orange);
						g.setFont(new Font("Arial",5,35 ));
							g.drawString(String.valueOf(scoretemporary), 105 ,367);
					}else {
						g.setColor(Color.orange);
					g.setFont(new Font("Arial",5,40 ));
						g.drawString(String.valueOf(scoretemporary), 115 ,367);
					}
					
				}
				else if (score < scoretemporary){
					if(scoretemporary > 10) {
						g.setColor(Color.orange);
						g.setFont(new Font("Arial",5,35 ));
							g.drawString(String.valueOf(scoretemporary), 105,367);
					}else {
						g.setColor(Color.orange);
				g.setFont(new Font("Arial",5,40 ));
					g.drawString(String.valueOf(scoretemporary), 115,367);
					}
					
				}
			start.restart.draw(g);
			start.menu.draw(g);
			
		}
		}
		else if(choosebirds == true) {
			if(bird1 == false && bird2 == false && bird3 == false ) {
				Image imgchoose = new ImageIcon("src/choosebird2.png").getImage();
			g.drawImage(imgchoose, 65, 130, null);
			}
			
			
			birds.bird1.draw(g);
			birds.bird2.draw(g);
			birds.bird3.draw(g);
			start.ok.draw(g);
			
			if(bird1 == true) {
				Image img1111 = new ImageIcon("src/1111.png").getImage();
				g.drawImage(img1111, 65, 75, null);
	
				
				
			}
			else if (bird2 == true){
				Image img2222 = new ImageIcon("src/2222.png").getImage();
				g.drawImage(img2222, 65, 90, null);
				
			
				
			}
			else if(bird3 == true) {
				Image img3333 = new ImageIcon("src/3333.png").getImage();
				g.drawImage(img3333, 65, 65, null);
				
				
				
			}
			
			Image imglogof = new ImageIcon("src/flappyNight.png").getImage();
			g.drawImage(imglogof, 220,680, null);
			Image imglogob = new ImageIcon("src/birdNight.png").getImage();
			g.drawImage(imglogob, 305, 705, null);
		}
		
		
	}
	public static void main(String[] args) {
		flappySquare = new FlappySquare();
		System.out.println("Nice to meet you)\nFlappy Square is glad to see you)\nLet`s try this challenge!\nGood luck!");
	}

	@Override
	public void mouseClicked(MouseEvent e) {////////////////edit the space of click
		if(!started) {
			
			if ( MouseInfo.getPointerInfo().getLocation().x > 140  && MouseInfo.getPointerInfo().getLocation().x  < 140 + 108 && MouseInfo.getPointerInfo().getLocation().y > 510  && MouseInfo.getPointerInfo().getLocation().y < 530 + 64) {
			System.out.println("Play");
			started = true;
			
			
		}
			if(MouseInfo.getPointerInfo().getLocation().x > 140  && MouseInfo.getPointerInfo().getLocation().x  < 140 + 108 && MouseInfo.getPointerInfo().getLocation().y > 610  && MouseInfo.getPointerInfo().getLocation().y < 610 + 64) {
				System.out.println("BIRD_CHOOSE");	
				choosebirds = true;
		}
				
			
	}
		if ((MouseInfo.getPointerInfo().getLocation().x > 310  && MouseInfo.getPointerInfo().getLocation().x  < 390) && (MouseInfo.getPointerInfo().getLocation().y > 0  && MouseInfo.getPointerInfo().getLocation().y < 80)) {
				System.out.println("EXIT");
				System.exit(0);
			}
		if(choosebirds == true) {
			
			if(MouseInfo.getPointerInfo().getLocation().x > 85  && MouseInfo.getPointerInfo().getLocation().x  < 85 + 59 && MouseInfo.getPointerInfo().getLocation().y > 360  && MouseInfo.getPointerInfo().getLocation().y < 340 + 90) {
				System.out.println("-BIRD1-");
				bird1 = true;
				bird2 = false;
				bird3 = false;
			}
			else if(MouseInfo.getPointerInfo().getLocation().x > 170  && MouseInfo.getPointerInfo().getLocation().x  < 170 + 59 && MouseInfo.getPointerInfo().getLocation().y > 340  && MouseInfo.getPointerInfo().getLocation().y < 360 + 50) {
				System.out.println("-BIRD2-");
				bird2 = true;
				bird1 = false;
				bird3 = false;
			}
			else if(MouseInfo.getPointerInfo().getLocation().x > 250  && MouseInfo.getPointerInfo().getLocation().x  < 250 + 59 && MouseInfo.getPointerInfo().getLocation().y > 340  && MouseInfo.getPointerInfo().getLocation().y < 360 + 70) {
				System.out.println("-BIRD3-");
				bird3 = true;
				bird1 = false;
				bird2 = false;
			}
			else if ((MouseInfo.getPointerInfo().getLocation().x > 140  && MouseInfo.getPointerInfo().getLocation().x  < 140 + 120) && (MouseInfo.getPointerInfo().getLocation().y > 420  && MouseInfo.getPointerInfo().getLocation().y < 430 + 80)) {
				System.out.println("OK");
				
			//havechoosen = true;
				choosebirds = false;
			}
			
			
		}
		if (gameOver) {
			if(MouseInfo.getPointerInfo().getLocation().x > 20  && MouseInfo.getPointerInfo().getLocation().x  < 20 + 120 && MouseInfo.getPointerInfo().getLocation().y > 685  && MouseInfo.getPointerInfo().getLocation().y < 685 + 90) {
				System.out.println("-Restart-");
				start();
			}
			if(MouseInfo.getPointerInfo().getLocation().x > 330  && MouseInfo.getPointerInfo().getLocation().x  < 330 + 50 && MouseInfo.getPointerInfo().getLocation().y > 630  && MouseInfo.getPointerInfo().getLocation().y < 630 + 50) {
				System.out.println("-Back to menu-");
				started = false;
				gameOver = false;
			}
		}
		
		
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
			spacepressed = true;
			
		}	
	if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			start();
			
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}
	
	
			


	
}