package com.wyjsusan.snake;

import java.awt.*;
import java.awt.event.*;

public class Yard extends Frame {

	

	public static final int ROWS = 50;
	public static final int COLS = 50;
	
	public static final int BLOCK_SIZE = 10;
	
	Snake s = new Snake();
	Egg e = new Egg();
	
	Image offScreenImage = null;
	
	public void launch() {
		this.setLocation(200, 200);
		this.setSize(COLS * BLOCK_SIZE, COLS * BLOCK_SIZE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
		this.addKeyListener(new KeyMonitor());
		new Thread(new PaintThread()).start();
	}
	
	

	public static void main(String[] args) {
		new Yard().launch();
	}
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, COLS * BLOCK_SIZE, COLS * BLOCK_SIZE);
		g.setColor(Color.DARK_GRAY);
		for (int i = 1; i < ROWS; i++) {
			g.drawLine(0, BLOCK_SIZE * i, COLS * BLOCK_SIZE, BLOCK_SIZE * i);
			g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, BLOCK_SIZE * ROWS);
		}
		g.setColor(c);
		s.eat(e);
		e.draw(g);
		s.draw(g);
	}
	
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	//make the sanke move.
	private class PaintThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	private class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			s.keyPressed(e);
		}
		
	}

}
