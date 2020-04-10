package flappySquare;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Render  extends JPanel{
	
	/**
	 * for renderring the objects which will be supported
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent (Graphics g) {
		super.paintComponent (g);
		
		FlappySquare.flappySquare.repaint(g);
	}
}
