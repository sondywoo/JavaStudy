package com.java.thread.semaphore;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * java.util.concurrent.Semaphore - 信号量
 * 
 * new Semaphore(n) -- 定义一个拥有n个许可证的信号量。
 * 
 * 为了通过信号量，线程需要调用acquire()请求一个许可证。许可的数目是有限的，这样一来就可以限制通过的线程数。
 * 其他已经获得许可证的线程可以通过调用release()来释放许可证。许可不需要由获得它的线程释放，任何线程都可以释放任意数目的许可release(int permits)，如果它释放的许可大于可用的最大值，则permits被重新设置成最大值
 * 其实没有实际的许可对象，信号量仅仅是维护一个计数器而已。
 * 
 * This program animates a sort algorithm.
 */
public class SemaphoreAndPermitTest {

	public static void main(String[] args) {
		JFrame frame = new AnimationFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/**
 * This frame shows the array as it is sorted, together with buttons to single-step the animation 
 * or to run it without interruption.
 */
class AnimationFrame extends JFrame{
	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 300;
	private static final int VALUES_LENGTH = 7;
	
	public AnimationFrame(){
		ArrayPanel panel = new ArrayPanel();
		this.add(panel, BorderLayout.CENTER);
		
		Double[] values = new Double[VALUES_LENGTH];
		final Sorter sorter = new Sorter(values, panel);
		
		JButton runButton = new JButton("Run");
		runButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					sorter.setRun();
				}
			});
		
		JButton stepButton = new JButton("Step");
		stepButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					sorter.setStep();
				}
			});
		
		JPanel buttons = new JPanel();
		buttons.add(runButton);
		buttons.add(stepButton);
		this.add(buttons, BorderLayout.NORTH);
		
		this.setSize(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
		
		for(int i = 0; i < values.length; i++){
			values[i] = new Double(Math.random());
		}
		
		Thread t = new Thread(sorter);
		t.start();
	}
}

/**
 * This runnable executes a sort algorithm.
 * When two elements are compared, the algorithm pauses andupdates a panel.
 */
class Sorter implements Runnable{
	private Double[] values;
	private ArrayPanel panel;
	private Semaphore gate;
	private static final int DELAY = 100;
	private boolean run;
	
	/**
	 * Constructs a Sorter.
	 * @param values the array to be sorted.
	 * @param panel the panel on which to display the sorting progress.
	 */
	public Sorter(Double[] values, ArrayPanel panel){
		this.values = values;
		this.panel = panel;
		
		/**
		 * new Semaphore(n) -- 定义一个拥有n个许可证的信号量。
		 */
		this.gate = new Semaphore(1);
		this.run = false;
	}

	public void run() {
		Comparator<Double> comp = new 
			Comparator<Double>(){
				public int compare(Double i1, Double i2) {
					panel.setValues(values, i1, i2);
					try{
						if(run){
							Thread.sleep(DELAY);
						}else{
							gate.acquire();
						}
					}catch(InterruptedException e){
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}
					return -i1.compareTo(i2);
				}
			};
		Arrays.sort(values, comp);
		panel.setValues(values, null, null);
	}
	
	/**
	 * Sets the sorter to 'run' mode.
	 */
	public void setRun(){
		this.run = true;
		gate.release();
	}
	
	/**
	 * Sets the sorter to 'step' mode.
	 */
	public void setStep(){
		this.run = false;
		gate.release();
	}
}

/**
 * This panel draws an array and marks two elements in the array.
 */
class ArrayPanel extends JPanel{
	private Double marked1;
	private Double marked2;
	private Double[] values;
	
	public void paintComponent(Graphics g){
		if(values == null){
			return;
		}
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		int width = this.getWidth() / values.length;
		for(int i = 0; i < values.length; i++){
			double height = values[i] * this.getHeight();
			Rectangle2D bar = new Rectangle2D.Double(width * i, 0, width, height);
			if(values[i] == marked1 || values[i] == marked2){
				g2.fill(bar);
			}else{
				g2.draw(bar);
			}
		}
	}
	
	/**
	 * Sets the values to be painted.
	 * @param values the array of values to display.
	 * @param marked1 the first marked element.
	 * @param marked2 the second marked element.
	 */
	public void setValues(Double[] values, Double marked1, Double marked2){
		this.values = values;
		this.marked1 = marked1;
		this.marked2 = marked2;
		repaint();
	}
}