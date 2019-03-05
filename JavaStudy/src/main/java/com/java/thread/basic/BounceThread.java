package com.java.thread.basic;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

/**
 * Shows an animated bouncing ball.
 */
public class BounceThread {
	public static void main(String[] args){
		JFrame frame = new BounceFrame2();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/**
 * A ball that moves and bounces off the edges of a rectangle
 */
class Ball2{
	private static final int XSIZE = 15;
	private static final int YSIZE = 15;
	private double x = 0;
	private double y = 0;
	private double dx = 1;
	private double dy = 1;
	
	/**
	 * Moves the Ball2 to the next position, reversing
	 * direction if it hits one of the edges.
	 */
	public void move(Rectangle2D bounds){
		x += dx;
		y += dy;
		
		if(x <= bounds.getMinX()){
			x = bounds.getMinX();
			dx = -dx;
		}
		if(x + XSIZE >= bounds.getMaxX()){
			x = bounds.getMaxX() - XSIZE;
			dx = -dx;
		}
		if(y <= bounds.getMinY()){
			y = bounds.getMinY();
			dy = -dy;
		}
		if(y + YSIZE >= bounds.getMaxY()){
			y = bounds.getMaxY() - YSIZE;
			dy = -dy;
		}
	}
	
	/**
	 * Gets the shape of the Ball2 at its current position
	 */
	public Ellipse2D getShape(){
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}
}

/**
 * The panel that draws the Ball2s.
 */
class Ball2Panel extends JPanel{
	private ArrayList<Ball2> Ball2s = new ArrayList<Ball2>();
	
	/**
	 * Add a Ball2 to the panel.
	 * @param b the Ball2 to add.
	 */
	public void add(Ball2 b){
		Ball2s.add(b);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(Ball2 b : Ball2s){
			g2.fill(b.getShape());
		}
	}
}

/**
 * The frame with panel and buttons.
 */
class BounceFrame2 extends JFrame{
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 350;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
	private Ball2Panel panel;
	
	/**
	 * Constructs the frame with the panel for showing the 
	 * bouncing Ball2 and Start and Close buttons.
	 */
	public BounceFrame2(){
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Sondy's Bounce");
		
		panel = new Ball2Panel();
		add(panel, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start",
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					addBall2();
				}
			});
		addButton(buttonPanel, "Close",
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					System.exit(0);
				}
			});
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Adds a button to a container
	 * @param c the container
	 * @param title the button title
	 * @param listener the action listener for the button.
	 */
	public void addButton(Container c, String title, ActionListener listener){
		JButton button =  new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	/**
	 * Add a bouncing Ball2 to the panel and 
	 * makes it bounce 1,000 times.
	 */
	public void addBall2(){
		Ball2 b = new Ball2();
		panel.add(b);
		Runnable r = new Ball2Runnable(b, panel);
		Thread t = new Thread(r);
		t.start();
		
//		try{
//			Ball2 Ball2 = new Ball2();
//			panel.add(Ball2);
//			
//			for(int i = 1; i <= STEPS; i++){
//				Ball2.move(panel.getBounds());
//				panel.paint(panel.getGraphics());
//				Thread.sleep(DELAY);
//			}
//		}catch(InterruptedException e){
//			
//		}
	}
}

/**
 * A runnable that animates a bouncing Ball2.
 */
class Ball2Runnable implements Runnable{
	private Ball2 Ball2;
	private Component component;
	public static final int STEPS = 100000;
	public static final int DELAY = 8;
	
	/**
	 * Constructs the runnable
	 * @param b the Ball2 to bounce
	 * @param c the component in which the Ball2 bounces
	 */
	public Ball2Runnable(Ball2 b, Component c){
		Ball2 = b;
		component = c;
	}

	public void run() {
		try{
			for(int i = 1; i <= STEPS; i++){
				Ball2.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		}catch(InterruptedException e){
			
		}
	}
}